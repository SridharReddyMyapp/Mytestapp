package com.sridhar.services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

public class PowerButtonService extends Service {
	PowerButtonReceiver mReceiver=null;
	public static long last_screen_on;
	public static boolean is_screen_on=true;
	
	
	
	@Override
	public void onCreate() {
		super.onCreate();


		System.out.println("Power Button Service Started..");
		last_screen_on=System.currentTimeMillis();
		is_screen_on=true;
		// Toast.makeText(getBaseContext(), "Service on create", Toast.LENGTH_SHORT).show();

		// Register receiver that handles screen on and screen off logic
		IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
		filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
		mReceiver = new PowerButtonReceiver();
		registerReceiver(mReceiver, filter);
		
	
		
		
		
		
	}

	@Override
	public void onStart(Intent intent, int startId) { 
		boolean powerbutton_Pressed = false;

		try{
			// Get ON/OFF values sent from receiver ( AEScreenOnOffReceiver.java ) 
			powerbutton_Pressed = intent.getBooleanExtra("powerbutton_Pressed", false);

		}catch(Exception e){}

		if(powerbutton_Pressed==true)
		{

			System.out.println("Power Button Pressed...in Services..... so checling now...");


			//System.out.println("Power Button Pressed....Sending Email action status?.."+SendEmail.sendingEmail);

			Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
			getApplicationContext().sendBroadcast(closeDialog);


		/*		Intent i = new Intent(MainActivity.getAppContext(), CameraView.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				MainActivity.getAppContext().startActivity(i);
			*/
			
			/*		Intent i = new Intent(MainActivity.getAppContext(), CameraView.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			MainActivity.getAppContext().startActivity(i);
			 */

			//new SendEmail().execute();



	/*		if(ActivityStatus.isWarningActiviyLaunched==0)
			{
				System.out.println("In PowerButton Service Class");
				Intent im = new Intent(getApplicationContext(), WarningActivity.class);
				im.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getApplicationContext().startActivity(im);
			}

			else
			{
				System.out.println("In Warning activity is already Opened!!");
			}

			System.out.println("Send email status from Powerbutton services..."+SendEmail.sendEmailStatus);

			if(SendEmail.sendEmailStatus==0)
			{
				System.out.println("Check is this activity is processing???");
				CameraView cv=new CameraView();
				Intent i11 = new Intent(MainActivity.getAppContext(), CameraView.class);
				i11.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				MainActivity.getAppContext().startActivity(i11);
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
		}*/

		//  Toast.makeText(getBaseContext(), "Service on start :"+screenOn, 
		//Toast.LENGTH_SHORT).show();
		}
		

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {

		Log.i("ScreenOnOff", "Service  distroy");
		if(mReceiver!=null)
			unregisterReceiver(mReceiver);

	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);

		Log.d("AES SCREEN SERICE ","On StartCommand...at PowerButton Service ..");
		//startForeground(1, new Notification());

		startForeground(Process.myPid(), new Notification());
		//startForeground(Process.myPid(), new Notification());
		//registerListener();
		//mWakeLock.acquire();



		return START_STICKY;
	}


	

}