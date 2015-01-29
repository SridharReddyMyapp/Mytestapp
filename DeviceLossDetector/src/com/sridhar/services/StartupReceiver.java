package com.sridhar.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class StartupReceiver extends BroadcastReceiver {

	static final String TAG = "SR";
	
	final int startupID = 1111111;
	 AlarmManager alarmManager;
	 public static int alaram_started=0;
	Intent i7 ;
	PendingIntent ServiceManagementIntent;
	@Override
	public void onReceive(Context context, Intent intent) {
		
		System.out.println("in Startup Receiver...");
		
			
		System.out.println("Alaram will start now..");
		
	System.out.println("Is alaram started???"+alaram_started);
		
		
		alarmManager = (AlarmManager) context
					.getSystemService(Context.ALARM_SERVICE);
		try{
			
			
			if(alaram_started==0)
			{
			System.out.println("Alaram Starting Now.... ");
				 i7 = new Intent(context, CheckRunningApplicationReceiver.class);
				 ServiceManagementIntent = PendingIntent.getBroadcast(context,
						startupID, i7, 0);
				alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
						SystemClock.elapsedRealtime(), 
						500, ServiceManagementIntent);
				alaram_started=1;
			}
			} catch (Exception e) {
				alaram_started=0;
				Log.i(TAG, "Exception : "+e);
			}
			
		}
	
	
	
	
	
	}

