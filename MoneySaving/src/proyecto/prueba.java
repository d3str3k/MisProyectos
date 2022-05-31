package proyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class prueba {
	public static void main(String[] args) throws ParseException {
		LocalDateTime dateTime = LocalDateTime.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
//	    int yearRes = date[2] - dateTime.getYear();
//	    int monthRes = date[1] - dateTime.getMonthValue();
//	    int dayRes = date[0] - dateTime.getDayOfMonth();
//	    int hourRes = 24 - dateTime.getHour();
//	    int minRes = 60 - dateTime.getMinute();
	//
//	    int minTotales =    yearRes*525600 + 
//	                        monthRes*43800 +
//	                        dayRes*1440 +
//	                        hourRes*60 +
//	                        minRes;
	    
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	    Date secondDate = sdf.parse("5/31/2022");
	    Date firstDate = sdf.parse(dateTime.getMonthValue() + "/" + dateTime.getDayOfMonth() + "/" + dateTime.getYear());

	    long diff = secondDate.getTime() - firstDate.getTime();

	    TimeUnit time = TimeUnit.MINUTES; 
	    long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
	    System.out.println("The difference in minutes is : "+diffrence);
	}
	
}
