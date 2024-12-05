import dao.DealershipDAO;
import models.Dealership;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Scanner;

public class SQLMain {
    public static void main(String[] args) {
        Scanner inputSc = new Scanner(System.in);

        // -- Try catch with resources ()
        /* Able to assign resources into try catch blocks. The advantage of this would be that a resource can be known throughout
           the entire try catch block and automatically close/terminate resources for you.
        * */
        try (BasicDataSource dataSource = new BasicDataSource()) {

            //Retrieving properties from .env file
            String url = dotenv.get("DB_URL");
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");

            //Setting data source connection using .env values
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);

            System.out.print("Enter the dealership id to search for individual dealership: ");
            int dealershipId = inputSc.nextInt();
            inputSc.nextLine();

            //Creating dealership DAO (Data Access Object) to manage data retrieval from dealership table
            DealershipDAO dealershipDAO = new DealershipDAO(dataSource);

            Dealership d = dealershipDAO.findDealershipById(dealershipId);

            System.out.printf("""
                Dealership:
                   id = %d
                   name = %s
                   phone = %s
                   address = %s
                """, d.getId(), d.getName(), d.getPhone(), d.getAddress());

            //Establish a new connection to MySQL database
            try (Connection conn = dataSource.getConnection()) {

                //A statement object that holds a SQL query (PreparedStatements always preferred to protect against SQL injection)
                PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM vehicles WHERE make = ? ");
//                PreparedStatement joinStatement = conn.prepareStatement("""
//                        SELECT * FROM dealerships
//                        JOIN inventory ON inventory.dealership_id = dealerships.dealership_id,
//                        WHERE vin = 10003;
//                        """);

                System.out.println("Enter vehicle make to search for: ");
                String vehicleMake = inputSc.nextLine();
                selectStatement.setString(1, vehicleMake);

                //Table that contains returned data from executing SQL statements
                ResultSet resultSet = selectStatement.executeQuery();
//                ResultSet resultSet2 = joinStatement.executeQuery();

                while (resultSet.next()) {
                    int vin = resultSet.getInt("vin");
                    String make = resultSet.getString("make");
                    String model = resultSet.getString("model");
                    double vehiclePrice = resultSet.getDouble("vehicle_price");
                    System.out.printf("%d, %s %s, $%.2f\n", vin, make, model, vehiclePrice);
                }

//                while (resultSet2.next()) {
//                    String name = resultSet2.getString("name");
//                    String address = resultSet2.getString("address");
//                    System.out.printf("%s, %s", name, address);
//                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
