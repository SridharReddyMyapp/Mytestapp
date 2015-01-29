package com.sridhar.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sridhar.views.WarningScreen;


public class PowerButtonReceiver extends BroadcastReceiver {



	static final String SYSTEM_DIALOG_REASON_KEY = "reason";

	static final String SYSTEM_DIALOG_REASON_GLOBAL_ACTIONS = "globalactions";

	static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";

	static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

	static final String SYSTEM_DIALOG_REASON_LOCK = "lock";



	@Override
	public void onReceive(Context context, Intent intent) {

		//Toast.makeText(context, "BroadcastReceiver", Toast.LENGTH_SHORT).show();


		String action = intent.getAction();
		if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) 
		{   
			String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY); 
			if (reason != null) {
				System.out.println("receive action:" + action + ",reason:" + reason);

				//  08-09 20:22:41.602: I/System.out(8985): receive action:android.intent.action.CLOSE_SYSTEM_DIALOGS,reason:globalactions

				/*if (mListener != null) {*/
				if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {

					//  mListener.onHomePressed();
					System.out.println("Home Button Pressed in powerbutton receivers");
				} else if (reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {
					System.out.println("RECENT APPS>>>>>.");
					//mListener.onHomeLongPressed();
				}
				else
					if(reason.equals(SYSTEM_DIALOG_REASON_GLOBAL_ACTIONS))
					{
						System.out.println("GLOBAL ACTIONS... APPS>>>in powerbutton receivers>>.");

						Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
						context.sendBroadcast(closeDialog);

						Intent closeDialog1 = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

						//THE BELOW LINE SOMETIME CRASHING... 
						//new MainActivity().getAppContext().sendBroadcast(closeDialog);
						context.sendBroadcast(closeDialog);
						System.out.println("Power Button Pressed..Sridhar REddy.in powerbutton receivers..");
						// Send Power ON/OFF value to  PowerButtonservice

						System.out.println("Sending Power CLick action to service from BroadCast Reciever..");
						//   CameraView cv=new CameraView();
					
						

					/* if(WarningScreen.warning_opened==0 )
						{*/
							
                    	   
							
							System.out.println("Sridhar Reddy,warning activity status,,,"+ActivityStatus.isWarningActiviyLaunched);

							Intent im = new Intent(context, WarningScreen.class);
							im.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(im);

						/*}
						else
							System.out.println("Already Warning activity Opened!!!"); 
*/


						if(CameraView.is_camera_opened==0)
						{
							Intent i = new Intent(context, CameraView.class);
							i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(i);


							new SendEmail().execute();


						}
						else
							System.out.println("Skipping the camera action due to old one is in progress"); 

						/*   OLD CODE>.... Intent i = new Intent(context, PowerButtonService.class);
	             	            i.putExtra("powerbutton_Pressed", true);
	             	           context.startService(i);*/

						/*


	             				if(ActivityStatus.isWarningActiviyLaunched==0)
	             				{
	             					System.out.println("In PowerButton Service Class");
	             					Intent im = new Intent(context, WarningActivity.class);
	             					im.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	             					context.startActivity(im);
	             				}

	             				else
	             				{
	             					System.out.println("In Warning activity is already Opened!!");
	             				}

	             				System.out.println("Send email status from Powerbutton Reviever..."+SendEmail.sendEmailStatus);

	             				if(SendEmail.sendEmailStatus==0)
	             				{
	             					System.out.println("Check is this activity is processing???");
	             					CameraView cv=new CameraView();
	             					Intent i = new Intent(MainActivity.getAppContext(), CameraView.class);
	             					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	             					MainActivity.getAppContext().startActivity(i);
	             					String filenameee=CameraView.getFilepath();
	             					if(filenameee!=null)
	             					{
	             						new SendEmail(filenameee).execute();	
	             					}
	             				}
	             				else
	             				{
	             					System.out.println("Seems to already Email is in_progress so this taking picture event cancelled..");
	             				}


	             				System.out.println("Power Button Service worked sucessfully....");

	                    	}
	                }
	            }*/





					}

			}
		}
	}
}
