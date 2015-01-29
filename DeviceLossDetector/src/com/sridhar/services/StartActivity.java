package com.sridhar.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class StartActivity {
	static StartupReceiver activity_list=null;
	static int is_manager_running=0;
	static Context context;
	public StartActivity(Context context) {
	
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	
	
	public static void Start_Manager()
	
	{

		System.out.println("Start manager.....");
	IntentFilter ff=new IntentFilter("startManager");
	//	IntentFilter ff=new IntentFilter(Intent.ACTION_DEFAULT);
		activity_list=new StartupReceiver();
		System.out.println("Is StartupReciever broadcast started???");
		context.registerReceiver(activity_list,ff);
		
		is_manager_running=1;
		
		
	}
	public static void Stop_Manager()
	
	{
		//Intent in=new Intent(CheckRunningApplicationReceiver.class);
/*		  Intent intent = new Intent(context, CheckRunningApplicationReceiver.class);
		     PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1253, intent, 0);
		     
		     AlarmManager alarmManager = (AlarmManager)context.getSystemService(Service.ALARM_SERVICE);
		 //    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		     alarmManager.cancel(pendingIntent);*/
		is_manager_running=0;
	
	/*	IntentFilter ff=new IntentFilter("stopManager");
		//	IntentFilter ff=new IntentFilter(Intent.ACTION_DEFAULT);
			activity_list=new StartupReceiver();
			
			context.registerReceiver(activity_list, ff);*/
		System.out.println("Should stop the manager now..");
		
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

	    Intent intent=new Intent(context,CheckRunningApplicationReceiver.class);
	    PendingIntent pi=PendingIntent.getBroadcast(context,1111111,intent,0);
	    alarmManager.cancel(pi);
		
		context.unregisterReceiver(activity_list);
		
	}
}
