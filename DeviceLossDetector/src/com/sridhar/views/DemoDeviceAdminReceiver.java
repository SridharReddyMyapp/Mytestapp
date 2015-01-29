package com.sridhar.views;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sridhar.devicelossdetector.MyDB;
import com.sridhar.services.CameraView;
import com.sridhar.services.SendEmail;


/**
 * This is the component that is responsible for actual device administration.
 * It becomes the receiver when a policy is applied. It is important that we
 * subclass DeviceAdminReceiver class here and to implement its only required
 * method onEnabled().
 */
public class DemoDeviceAdminReceiver extends DeviceAdminReceiver {
	static final String TAG = "DemoDeviceAdminReceiver";
	static int no_failed_attempts=0;

	/** Called when this application is approved to be a device administrator. */
	@Override
	public void onEnabled(Context context, Intent intent) {
		super.onEnabled(context, intent);
		/*Toast.makeText(context, R.string.device_admin_enabled,
				Toast.LENGTH_LONG).show();*/
		Log.d(TAG, "onEnabled");
	}

	/** Called when this application is no longer the device administrator. */
	@Override
	public void onDisabled(Context context, Intent intent) {
		super.onDisabled(context, intent);
	/*	Toast.makeText(context, R.string.device_admin_disabled,
				Toast.LENGTH_LONG).show();*/
		Log.d(TAG, "onDisabled");
	}

	@Override
	public void onPasswordChanged(Context context, Intent intent) {
		super.onPasswordChanged(context, intent);
		Log.d(TAG, "onPasswordChanged");
	}

	@Override
	public void onPasswordFailed(Context context, Intent intent) {
		super.onPasswordFailed(context, intent);
		Log.d(TAG, "onPasswordFailed");
		super.onPasswordFailed(context, intent);
MyDB mm=new MyDB(context);
if(mm.getUnlockServiceState()==1)
	
{
	System.out.println("Password Attempt is Failed...");

		DemoDeviceAdminReceiver.no_failed_attempts=DemoDeviceAdminReceiver.no_failed_attempts+1;

		
		
		Intent im = new Intent(context, WarningScreen.class);
		im.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(im);



		if(CameraView.is_camera_opened==0)
		{
			Intent i = new Intent(context, CameraView.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);


			new SendEmail().execute();


		}
		else
			System.out.println("Skipping the camera action due to old one is in progress"); 

		
		
}		
		
		
//		System.out.println("Sridhar Reddy,warning activity status,,,"+ActivityStatus.isWarningActiviyLaunched);
	//	System.out.println("Sridhar Reddy,DemoDeviceAdminReceiver.no_failed_attempts,,,"+DemoDeviceAdminReceiver.no_failed_attempts);

		//System.out.println("Send email status from Powerbutton services..."+SendEmail.sendEmailStatus);


/*
		if(ActivityStatus.isWarningActiviyLaunched==0 &&DemoDeviceAdminReceiver.no_failed_attempts==2)
		{
			System.out.println("Sridhar Reddy,warning activity status,,,"+ActivityStatus.isWarningActiviyLaunched);
			System.out.println("Sridhar Reddy,DemoDeviceAdminReceiver.no_failed_attempts,,,"+DemoDeviceAdminReceiver.no_failed_attempts);

			Intent im = new Intent(context, WarningActivity.class);
			im.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(im);

		}
		else
			System.out.println("Warning activity already launched...."+ActivityStatus.isWarningActiviyLaunched);
		
		
		if(CameraView.is_camera_opened==0)
		{
			Intent i = new Intent(context, CameraView.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
			new SendEmail().execute();
		}
		else
			System.out.println("Taking camera pic failed due already camera is in progress....Password failed... ");
*/
		/*
		if(SendEmail.sendEmailStatus==0)
		{

			System.out.println("Check is this activity is processing???");
			Intent i = new Intent(context, CameraView.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
			String filenameee=CameraView.getFilepath();
			if(filenameee!=null)
			{
				new SendEmail(filenameee).execute();	
			}
		}*/

			
	}





	@Override
	public void onPasswordSucceeded(Context context, Intent intent) {
		super.onPasswordSucceeded(context, intent);
		Log.d(TAG, "onPasswordSucceeded");
		DemoDeviceAdminReceiver.no_failed_attempts=0;
	}

}