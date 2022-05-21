package com.amdocs.backend;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SLAAnalyzer 
{
    /**
    * Method will receive a particular problem opening date and amount of working hours (business hours) it should be solved and return the maximum date and time it should be solved.
    * It should be considered:
    * 	- Business hours are from 8AM to 5PM excluding weekends and holidays
    *  - Logic should consider only month of August 2019, Sao Carlos location
    *  - Method signature cannot be changed
    *  
    * @param iOpeningDateTime - Problem opening date
    * @param iSLA - Quantity of hours to solve the problem
    * @return Maximum date and time that problem should be solved
    */
    
    public LocalDateTime calculateSLA(LocalDateTime iOpeningDateTime, Integer iSLA)
    {
    	int daysPass = iSLA/9;
    	int hoursPass = iSLA%9;
    	
    	LocalDateTime businessOpenHourMin = LocalDateTime.of(iOpeningDateTime.getYear(), iOpeningDateTime.getMonthValue(), iOpeningDateTime.getDayOfMonth(), 8, 00);
    	LocalDateTime businessOpenHourMax = LocalDateTime.of(iOpeningDateTime.getYear(), iOpeningDateTime.getMonthValue(), iOpeningDateTime.getDayOfMonth(), 17, 00);

    	if (iOpeningDateTime.isBefore(businessOpenHourMin)) {
	    	iOpeningDateTime = businessOpenHourMin;
    	}else if (iOpeningDateTime.isAfter(businessOpenHourMax)) {
    		iOpeningDateTime = businessOpenHourMin.plusDays(1);
    	}

    	LocalDateTime iFinishingDateTime = iOpeningDateTime.plusDays(daysPass);
    	
       	LocalDateTime businessDayFinishMin = LocalDateTime.of(iFinishingDateTime.getYear(), iFinishingDateTime.getMonthValue(), iFinishingDateTime.getDayOfMonth(), 8, 00);
       	LocalDateTime businessDayFinishMax = LocalDateTime.of(iFinishingDateTime.getYear(), iFinishingDateTime.getMonthValue(), iFinishingDateTime.getDayOfMonth(), 17, 00);

       	iFinishingDateTime = iFinishingDateTime.plusHours(hoursPass);
    	

       	if (iFinishingDateTime.isAfter(businessDayFinishMax)) {
       		int minutesPass =  (int) businessDayFinishMax.until(iFinishingDateTime,  ChronoUnit.MINUTES);
       		iFinishingDateTime = businessDayFinishMin.plusDays(1);		
       		iFinishingDateTime = iFinishingDateTime.plusMinutes(minutesPass);
       	}
    	
        return iFinishingDateTime;
    }
    
      
}
