package com.sridhar.views;

import com.sridhar.devicelossdetector.MyDB;

public class ActivityDecision {

	
	public static boolean decision()
	{
		final boolean is_enrolled=MyDB.isenrolled();
		return is_enrolled;
		
			
	}
	 
     
	
}
