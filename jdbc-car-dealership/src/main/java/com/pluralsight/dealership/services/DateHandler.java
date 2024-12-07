package com.pluralsight.dealership.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHandler {
   public static String getContractDate(LocalDateTime contractDate) {
       DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       return contractDate.format(formattedDate);
   }
}
