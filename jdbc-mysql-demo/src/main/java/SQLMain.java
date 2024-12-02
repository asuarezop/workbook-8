import java.sql.*;

public class SQLMain {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/car_dealership";
        String user = "root";
        String password = "new_password";

        try {
            //Establish a new connection to MySQL database
            Connection conn = DriverManager.getConnection(url, user, password);

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicles");
            ResultSet resultSet2 = statement.executeQuery("""
                    SELECT * FROM dealerships
                    JOIN inventory ON inventory.dealership_id = dealerships.dealership_id,
                    WHERE vin = 10003;
                    """);

            while (resultSet.next()) {
                int vinNum = resultSet.getInt("vin");
                String vehicleMake = resultSet.getString("make");
                String vehicleModel = resultSet.getString("model");

                double vehiclePrice = resultSet.getDouble("vehicle_price");
                System.out.printf("%d, %s %s, %.2f \n", vinNum, vehicleMake, vehicleModel, vehiclePrice);
            }

            while (resultSet2.next()) {
                String name = resultSet2.getString("name");
                String address = resultSet2.getString("address");
                System.out.printf("%s, %s", name, address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
