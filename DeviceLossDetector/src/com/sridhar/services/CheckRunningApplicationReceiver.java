package com.sridhar.services;

import java.util.List;

import com.sridhar.views.WarningScreen;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CheckRunningApplicationReceiver extends BroadcastReceiver {

	public final String TAG = "CRAR"; // CheckRunningApplicationReceiver

	@Override
	public void onReceive(Context aContext, Intent anIntent) {


		

			try {

				// Using ACTIVITY_SERVICE with getSystemService(String) 
				// to retrieve a ActivityManager for interacting with the global system state.

				ActivityManager am = (ActivityManager) aContext
						.getSystemService(Context.ACTIVITY_SERVICE);

				// Return a list of the tasks that are currently running, 
				// with the most recent being first and older ones after in order.
				// Taken 1 inside getRunningTasks method means want to take only 
				// top activity from stack and forgot the olders.

				List<ActivityManager.RunningTaskInfo> alltasks = am
						.getRunningTasks(1);

				// 
				for (ActivityManager.RunningTaskInfo aTask : alltasks) {

					System.out.println(" which activity is running!????"+aTask.topActivity.getClassName());

					if(aTask.topActivity.getClassName().equals("com.sridhar.services.CameraView"))
					{
						Log.d(TAG, "camera is in progress...");
					}
					else
						if(!aTask.topActivity.getClassName().equals("com.sridhar.views.WarningScreen"))
						{
							Intent im = new Intent(aContext, WarningScreen.class);
							im.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							aContext.startActivity(im);

						}
						
							

					/*	if (!aTask.topActivity.getClassName().equals("com.example.checkcurrentrunningapplication.Main")
				    || aTask.topActivity.getClassName().equals("com.android.contacts.DialtactsActivity"))
				{
					// When user on call screen show a alert message
                 //   Toast.makeText(aContext, "You are not in my app screen", Toast.LENGTH_SHORT).show();	
                    Intent i=new Intent(aContext,Main.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    aContext.startActivity(i);
				}

                // Used to check for CALL screen  

				if (aTask.topActivity.getClassName().equals("com.android.phone.InCallScreen")
				    || aTask.topActivity.getClassName().equals("com.android.contacts.DialtactsActivity"))
				{
					// When user on call screen show a alert message
                    Toast.makeText(aContext, "Phone Call Screen.", Toast.LENGTH_LONG).show();	
				}

				// Used to check for SMS screen

				if (aTask.topActivity.getClassName().equals("com.android.mms.ui.ConversationList")
					    || aTask.topActivity.getClassName().equals("com.android.mms.ui.ComposeMessageActivity"))
				{
					// When user on Send SMS screen show a alert message
                    Toast.makeText(aContext, "Send SMS Screen.", Toast.LENGTH_LONG).show();	
				}


				// Used to check for CURRENT example main screen

				String packageName = "com.example.checkcurrentrunningapplication";

				if (aTask.topActivity.getClassName().equals(
						packageName + ".Main"))
				{
                   Toast.makeText(aContext, "Current Example Screen.", Toast.LENGTH_LONG).show();	
				}
					 */

					// These are showing current running activity in logcat with 
					// the use of different methods

					Log.i(TAG, "===============================");

					Log.i(TAG, "aTask.baseActivity: "
							+ aTask.baseActivity.flattenToShortString());

					Log.i(TAG, "aTask.baseActivity: "
							+ aTask.baseActivity.getClassName());

					Log.i(TAG, "aTask.topActivity: "
							+ aTask.topActivity.flattenToShortString());

					Log.i(TAG, "aTask.topActivity: "
							+ aTask.topActivity.getClassName());

					Log.i(TAG, "===============================");


				}

			} catch (Throwable t) {
				Log.i(TAG, "Throwable caught: "
						+ t.getMessage(), t);
			}
		

	}

}
