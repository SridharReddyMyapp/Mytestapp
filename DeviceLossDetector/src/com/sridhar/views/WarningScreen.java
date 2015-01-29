package com.sridhar.views;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import com.sridhar.devicelossdetector.R;
import com.sridhar.services.ActivityStatus;
import com.sridhar.services.CheckRunningApplicationReceiver;
import com.sridhar.services.StartupReceiver;

public class WarningScreen extends Activity{
	private MediaPlayer mediaPlayer;
	public Context context;
	public static int is_media_started=0;
	public static int warning_opened=0;
	int is_password_worked=0;
	  BroadcastReceiver mReceiver=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//mp = MediaPlayer.create(WarningActivity.this,RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI);
		ActivityStatus.isWarningActiviyLaunched=1;
		   getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		   warning_opened=1;
			initialize();
			

		context=getApplicationContext();

		setContentView(R.layout.warning);
		Button b=(Button)findViewById(R.id.warning_button);
		
	/*	mediaPlayer=MediaPlayer.create(this, R.raw.sound);
		mediaPlayer.setVolume(1.0f, 1.0f);
		if(is_media_started==0)
		{
		mediaPlayer.start();
		mediaPlayer.setLooping(true);
		is_media_started=1;
	
	
		}*/
		
	/*	final StartActivity sa=new StartActivity(context);
	//	StartActivity sa=new StartActivity(context);
		sa.Start_Manager();*/
		
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				WarningScreen.this.finish();
				 ActivityStatus.isWarningActiviyLaunched=1;
				stopBoradcast();
				Intent i = new Intent();
				i.setClass(getApplicationContext(), ServicesActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);
				
				// TODO Auto-generated method stub
			/*	EditText et=(EditText)findViewById(R.id.warning_password);
				String pass=et.getText().toString();
			//	int pas=Integer.parseInt(pass);
				MyDB mmm=new MyDB(context);
				long passss=mmm.getPassword();

				if(pas==mmm.getPassword())
				{
					mediaPlayer.stop();
					is_password_worked=1;
					WarningScreen.this.finish();
					ActivityStatus.isWarningActiviyLaunched=0;
					
					System.out.println("It should stop the activity Manager....");
					
					//new StartActivity(getBaseContext()).Stop_Manager();
					//StartActivity.Stop_Manager();
					//unregisterReceiver(new Intent("StartupReceiver_Manual_Start"));
					sa.Stop_Manager();
				}
*/
				
			}
		});






	}
/*	public void AlertDialog(final MediaPlayer mp){
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
		alertBuilder.setMessage("Are you there ?");
		alertBuilder.setCancelable(false);
		alertBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, close
				// current activity



				WarningScreen.this.finish();
				ActivityStatus.isWarningActiviyLaunched=0;
				

								Intent i = new Intent();
				i.setClass(getApplicationContext(), MainActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);
				 			}
		});
		alertBuilder.create();
		alertBuilder.show();
	}*/

	@Override
	protected void onPause() {

		System.out.println("On Pause");
		super.onPause();
		
		moveTaskToBack(false);
		
	//	initialize();
		//  notify("onPause");
		/*if(is_password_worked==1)
		{
			super.onPause();
		}
		else
		{
			Intent im = new Intent(getApplicationContext(), WarningScreen.class);
			im.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
			context.startActivity(im);
		}*/
	}

	@Override
	protected void onResume() {
		super.onResume();

		/*if(is_password_worked==1)
		{
			super.onResume();
		}
		else
		{
			Intent im = new Intent(getApplicationContext(), WarningActivity.class);
			im.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(im);
		}*/
	}


	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("On Stop....");
		 moveTaskToBack(false);
		/*
		if(is_password_worked==1)
		{
			super.onStop();
		}
		else
		{

			WarningScreen.this.finish();	
			Intent im = new Intent(getApplicationContext(), WarningScreen.class);
			
			if(mediaPlayer.isPlaying())
			{
				mediaPlayer.stop();
			}
			im.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			im.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			im.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			//im.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
			
			
			context.startActivity(im);
		}*/
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("On Destroy....");

	/*	if(is_password_worked==1)
		{
			super.onDestroy();
		}
		else
		{
			WarningScreen.this.finish();
			Intent im = new Intent(getApplicationContext(), WarningScreen.class);
			im.setFlags(Intent.);
			context.startActivity(im);
		}*/
	}
	
	@Override
	public void onBackPressed() {
	}
	
	 public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
	  {
	    return true;
	  }
/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if (keyCode == KeyEvent.KEYCODE_BACK) {
	     //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
	     return true;
	     }
	     if(keyCode==KeyEvent.KEYCODE_HOME)
	     {
	    	 System.out.println("Home Key Pressed...");
	    	 return false;
	     }
	     
	     return super.onKeyDown(keyCode, event);    
	}
	

	 @Override
	 public boolean dispatchKeyEvent(KeyEvent event) {

	  if ( (event.getKeyCode() == KeyEvent.KEYCODE_HOME) ) {
	//   mTextView.setText("KEYCODE_HOME");
	   return false;
	   
	  } else {
	   return super.dispatchKeyEvent(event);
	  }
	   
	 }*/
	 
	 
	 
	 
	 private void initialize() {
			
			// Start receiver with the name StartupReceiver_Manual_Start
			// Check AndroidManifest.xml file
	/*		getBaseContext().getApplicationContext().sendBroadcast(
					new Intent("StartupReceiver_Manual_Start"));
			IntentFilter intentFilter=new IntentFilter("Sridhar");
			
			getApplicationContext().sendBroadcast(intent);(StartupReceiver.class, intentFilter);*/
			System.out.println("Warning activity launched... then it should start the reciever as well...");
			
			   IntentFilter filter = new IntentFilter();
	           filter.addAction("sridhar");
	           mReceiver = new StartupReceiver();
	           registerReceiver(mReceiver, filter);
	        
			
			
		   Intent intent = new Intent();
		      intent.setAction("sridhar");
		      sendBroadcast(intent);
		}
		
	private void stopBoradcast() {
			
			// Start receiver with the name StartupReceiver_Manual_Start
			// Check AndroidManifest.xml file
	/*		getBaseContext().getApplicationContext().sendBroadcast(
					new Intent("StartupReceiver_Manual_Start"));
			IntentFilter intentFilter=new IntentFilter("Sridhar");
			
			getApplicationContext().sendBroadcast(intent);(StartupReceiver.class, intentFilter);*/
		StartupReceiver.alaram_started=0;
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

	    Intent intent=new Intent(getApplicationContext(),CheckRunningApplicationReceiver.class);
	    PendingIntent pi=PendingIntent.getBroadcast(getApplicationContext(),1111111,intent,0);
	    alarmManager.cancel(pi);
	    ActivityStatus.isWarningActiviyLaunched=1;
	    System.out.println("Is mreciever null.."+mReceiver);
	    warning_opened=0;
			if(mReceiver!=null)
			unregisterReceiver(mReceiver);
		  
			
		}
		

}
