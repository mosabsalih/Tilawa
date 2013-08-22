package org.tilawa.internal.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateTimeUtil {


	// TODO [M] We should add better support for time zones
	
	@SuppressWarnings("deprecation")
	public static boolean isBefore(Date currentDate, Date futureDate) {
		
		Calendar futureCalendar = Calendar.getInstance();
		
		futureCalendar.set(futureDate.getYear(), futureDate.getMonth(), futureDate.getDay());
		
	    Calendar nowCalendar = Calendar.getInstance();
	    
	    nowCalendar.set(currentDate.getYear(), currentDate.getMonth(), currentDate.getDay());

	    return nowCalendar.before(futureCalendar);

	}
	
	@SuppressWarnings("deprecation")
	public static boolean isAfter(Date currentDate, Date futureDate) {
		
		Calendar futureCalendar = Calendar.getInstance();
		
		futureCalendar.set(futureDate.getYear(), futureDate.getMonth(), futureDate.getDay());
		
	    Calendar nowCalendar = Calendar.getInstance();
	    
	    nowCalendar.set(currentDate.getYear(), currentDate.getMonth(), currentDate.getDay());

	    return nowCalendar.after(futureCalendar);

	}
		
	public static void main(String[] args) {
				
		System.out.println(DateTimeUtil.isBefore(new Date(2011,01,01),new Date(2012,01,01)));
		System.out.println(DateTimeUtil.isBefore(new Date(2012,03,01),new Date(2012,02,01)));
		
		System.out.println(DateTimeUtil.isAfter(new Date(2011,01,01),new Date(2012,01,01)));
		System.out.println(DateTimeUtil.isAfter(new Date(2012,03,01),new Date(2012,02,01)));
		
	}
}
