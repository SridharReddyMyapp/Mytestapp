package com.sridhar.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class USBDetector extends BroadcastReceiver {
	@Override
	public void onReceive(Context context , Intent intent) {
	    String action = intent.getAction();

/*	    
	    if (intent.getAction().equalsIgnoreCase( "android.intent.action.UMS_CONNECTED"))
		{
	    	Toast.makeText(context, "Charging..UMSSSSS..", Toast.LENGTH_SHORT).show();
		}

		if (intent.getAction().equalsIgnoreCase( "android.intent.action.UMS_DISCONNECTED"))
		{
			Toast.makeText(context, "Disconnected..UMSSSSS..", Toast.LENGTH_SHORT).show();
		}
	    */
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
	        // Do something when power connected
	    	
	    	System.out.println("Power Connected");
	    	
	    	Toast.makeText(context, "Charging....", Toast.LENGTH_SHORT).show();
	    }
	    else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
	        // Do something when power disconnected
	    	Toast.makeText(context, "Disconnected....", Toast.LENGTH_SHORT).show();
	    }
	}
}
