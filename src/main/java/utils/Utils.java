package utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {
	
	private Utils() {}

	public static LocalDate convertDateToLocalDate(Date date) {
	    if (date == null) {
	        return null; 
	    }
	    
	    return Instant.ofEpochMilli(date.getTime())
	                  .atZone(ZoneId.systemDefault())
	                  .toLocalDate();
	}
	 
	public static Date convertLocalDateToDate(LocalDate localDate) {
	    if (localDate == null) {
	        return null;
	    }
	    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}