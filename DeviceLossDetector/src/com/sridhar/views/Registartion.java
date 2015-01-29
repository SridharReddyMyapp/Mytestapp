package com.sridhar.views;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.internal.pj;
import com.sridhar.devicelossdetector.MyDB;
import com.sridhar.devicelossdetector.MyDeviceInfo;
import com.sridhar.devicelossdetector.R;

public class Registartion extends Activity {
	private AdView adView1;
	private AdView adView2;
	private AdView adView3;
	private AdView adView4;

	private AdView adView5;
	private AdView adView6;

	private AdView adView7;
	private InterstitialAd interstitial;
	private String header_add_id="ca-app-pub-3153655904585394/1494327261";

	private String footer_add_id="ca-app-pub-3153655904585394/1553202863";

	private String InterstitialAd_add_id="ca-app-pub-3153655904585394/9216070465";
	static final String TAG = "DevicePolicyDemoActivity";
	static final int ACTIVATION_REQUEST = 47; // identifies our request id
	DevicePolicyManager devicePolicyManager;
	ComponentName sridharDeviceAdmin;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);





		TextView tv=new TextView(this);
		tv.setText("Sridhar Reddy!!");
		// setContentView(tv);
		devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		sridharDeviceAdmin = new ComponentName(this, DemoDeviceAdminReceiver.class);
		LinearLayout layout=new LinearLayout(this);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		layout.setOrientation(LinearLayout.VERTICAL);

		adView1 = new AdView(this);
		adView1.setAdSize(AdSize.BANNER);
		adView1.setAdUnitId(header_add_id);

		System.out.println("IS add is loading or not????");

		AdRequest adRequest = new AdRequest.Builder()
		.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		.build();

		adView1.loadAd(adRequest);

		layout.addView(adView1);

		ScrollView reg_layout=(ScrollView) findViewById(R.layout.registration);




		adView2 = new AdView(this);
		adView2.setAdSize(AdSize.BANNER);
		adView2.setAdUnitId(header_add_id);

		AdRequest adRequest2 = new AdRequest.Builder()
		.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		.build();

		adView2.loadAd(adRequest2);




		View view; 
		LayoutInflater inflater = (LayoutInflater)   getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		view = inflater.inflate(R.layout.registration, null);
		layout.addView(view);


		Button activate=(Button) view.findViewById(R.id.activate);

		activate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/*	Intent im = new Intent(getApplicationContext(), ServicesActivity.class);
				im.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getApplicationContext().startActivity(im);*/

				System.out.println("It should launch the device admin");

				Intent intent = new Intent(
						DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
				intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,sridharDeviceAdmin);
				intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
						"Activate admin");



				startActivityForResult(intent, ACTIVATION_REQUEST);
			}
		});



		layout.addView(adView2);


		setContentView(layout);
	}



	/**
	 * Called when startActivityForResult() call is completed. The result of
	 * activation could be success of failure, mostly depending on user okaying
	 * this app's request to administer the device.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case ACTIVATION_REQUEST:
			if (resultCode == Activity.RESULT_OK) {
				Log.i(TAG, "Administration enabled!");
				//toggleButton.setChecked(true);
				Toast.makeText(getApplicationContext(), "Sucessfully Registered.. Start the services now!!!", Toast.LENGTH_SHORT).show();

				final MyDB mm=new MyDB(getApplicationContext());


				EditText email_id1=(EditText)findViewById(R.id.email_address);
				String email_id=email_id1.getText().toString();

				EditText phno11=(EditText)findViewById(R.id.emergency_contact1);
				long phno1=Long.parseLong(phno11.getText().toString());






				EditText pasword123=(EditText)findViewById(R.id.app_lock_password1);

				long password1=Long.parseLong(pasword123.getText().toString());


				MyDeviceInfo mdi=new MyDeviceInfo();

				TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
				String device_id=telephonyManager.getDeviceId();



				mdi.setAdmin_status(1);
				if(device_id!=null)
					mdi.setDevice_imei(device_id);
				else
					mdi.setDevice_imei("Device IMEI Not available");
				mdi.setEmail_id(email_id);
				mdi.setPhno1(phno1);
				mdi.setPasscode(password1);

				//	System.out.println("Got all the values...."+email_id+"....phno1.."+phno1+"....Phno2.."+phno2+"...password.."+password1+"...failedattempts..."+failed_attempts1);

				Long is_inserted=	MyDB.create_device_info(mdi);

				//(String email_id,long phno11,long phno22,long password1,long failed_attempts)


				Log.d("Sridhar","Got all the values...."+email_id+"....Phno"+phno1+"...Imei.."+device_id);



				System.out.println("Is database values are inserting?,,,,"+is_inserted);

				Registartion.this.finish();
				Intent i = new Intent();
				i.setClass(getApplicationContext(), ServicesActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);

			} else {
				Log.i(TAG, "Administration enable FAILED!");

				Toast.makeText(getApplicationContext(), "Registration failed.. Activate it again!!!", Toast.LENGTH_SHORT).show();


				//	toggleButton.setChecked(false);
			}
			return;
		}
		super.onActivityResult(requestCode, resultCode, data);
		//super.onActivityResult(requestCode, resultCode, data);
	}




	@Override
	protected void onDestroy() {

		super.onDestroy();

	}
}