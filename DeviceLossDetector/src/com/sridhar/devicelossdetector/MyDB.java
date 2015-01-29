package com.sridhar.devicelossdetector;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyDB{  

	private static final String TAG = "sridharReddy";
	private static MyDatabaseHelper dbHelper;  
	static Context context;

	private static SQLiteDatabase database;  

	public final static String mobiletracker="mobiletracker"; 


	//MyDevice Info table infomation....

	public final static String my_device_info_table="my_device_info";
	public final static String my_device_info_email_id="email_id";
	public final static String my_device_info_phno1="phno1";
	public final static String my_device_info_passcode="passcode";
	public final static String my_device_info_imei="device_imei";
	public final static String my_device_info_admin_status="admin_status";
	public final static String my_device_info_id="device_info_id";




	//Unlock Detection Table
	public final static String unlock_device_info_table="unlock_device_info";

	public final static String unlock_device_failedAttempts="unlock_failed_attempts";
	public final static String unlock_device_alaram="unlock_alaram";
	public final static String unlock_device_capturepic="unlock_capturepic";
	public final static String unlock_device_lock="unlock_lock";
	public final static String unlock_device_send_sms="unlock_sendsms";
	public final static String unlock_device_service_state="unlock_device_service_state";


	//Unlock Detection Table
	public final static String poweroff_device_info_table="poweroff_device_info";

	public final static String poweroff_device_alaram="poweroff_alaram";
	public final static String poweroff_device_capturepic="poweroff_capturepic";
	public final static String poweroff_device_lock="poweroff_lock";
	public final static String poweroff_device_send_sms="poweroff_sendsms";
	public final static String poweroff_service_state="poweroff_service_state";

	//USB Unplug Detection Table
	public final static String usb_device_info_table="usb_device_info";

	public final static String usb_device_alaram="usb_alaram";
	public final static String usb_device_capturepic="usb_capturepic";
	public final static String usb_device_lock="usb_lock";
	public final static String usb_device_send_sms="usb_sendsms";
	public final static String usb_device_service_state="usb_device_service_state";


	// Fall Detection Table
	public final static String fall_device_info_table="fall_device_info";

	public final static String fall_device_alaram="fall_alaram";
	public final static String fall_device_capturepic="fall_capturepic";
	public final static String fall_device_lock="fall_lock";
	public final static String fall_device_send_sms="fall_sendsms";
	public final static String fall_device_service_state="fall_device_service_state";





	/** 
	 * 
	 * @param context 
	 */  
	public MyDB(Context context){  
		dbHelper = new MyDatabaseHelper(context);  
		this.context=context;


		if(database==null)
		{
			database = dbHelper.getWritableDatabase();

		}
		else
			database=getDB();


	}

	public static SQLiteDatabase getDB()

	{
		return database;

	}




	public static long create_device_info(MyDeviceInfo mdi)
	{


		database.execSQL("delete from "+ my_device_info_table);


		ContentValues values = new ContentValues();  




		values.put(my_device_info_email_id, mdi.getEmail_id());  

		values.put(my_device_info_phno1,mdi.getPhno1());


		values.put(my_device_info_passcode,mdi.getPasscode());
		//	new MainActivity().lockDevice();
		//values.put(my_device_info_failed_attempts,failed_attempts);
		values.put(my_device_info_admin_status,mdi.getAdmin_status());
		values.put(my_device_info_imei,mdi.getDevice_imei());

		values.put(my_device_info_id, 1);
		return database.insert(my_device_info_table, null, values);  

	}


	/*
	 * Updating a Device Info
	 */
	public int updateDeviceInfo(MyDeviceInfo mdi) {


		ContentValues values = new ContentValues();
		values.put(my_device_info_email_id, mdi.getEmail_id());  

		values.put(my_device_info_phno1,mdi.getPhno1());


		values.put(my_device_info_passcode,mdi.getPasscode());
		//	new MainActivity().lockDevice();
		//values.put(my_device_info_failed_attempts,failed_attempts);
		values.put(my_device_info_admin_status,mdi.getAdmin_status());
		values.put(my_device_info_imei,mdi.getDevice_imei());





		// updating row
		// return database.update(my_device_info_table, values, null, null);
		return database.update(my_device_info_table, values, my_device_info_id + " = ?", new String[]{"1"});


	}


	/* Inserting into Unlock Device table */

	public static long createunlock(int failed_attempts,int alaram, int capturepic,int lockdevice,int sendsms,int unlock_service)
	{
		ContentValues values = new ContentValues();  


		database.execSQL("delete from "+ unlock_device_info_table);


		values.put(unlock_device_alaram, alaram);  
		values.put(unlock_device_capturepic,capturepic);
		values.put(unlock_device_failedAttempts,failed_attempts);
		values.put(unlock_device_lock,lockdevice);
		values.put(unlock_device_send_sms,sendsms);
		values.put(unlock_device_service_state,unlock_service);
		
		return database.insert(unlock_device_info_table, null, values);  

	} 






	//Inserting into MyDevice ino table
	public static long insertinto_my_device_info(String email_id,long phno11,long password1,String device_imei, long is_activated)
	{  

		database.execSQL("delete from "+ unlock_device_info_table);

		ContentValues values = new ContentValues();  
		values.put(my_device_info_email_id, email_id);  

		values.put(my_device_info_phno1,phno11);


		values.put(my_device_info_passcode,password1);
		//	new MainActivity().lockDevice();
		//values.put(my_device_info_failed_attempts,failed_attempts);
		values.put(my_device_info_admin_status,is_activated);
		values.put(my_device_info_imei,device_imei);
		values.put(my_device_info_id, 1);

		return database.insert(my_device_info_table, null, values);  
	}    


	/*

	//Sensor settings insertion.

	public static long insertinto_sensor_settings(String prop,String val)
	{  
		ContentValues values = new ContentValues();  
		values.put(sensor_settings_property, prop);  

		values.put(sensor_settings_value, val);

		return database.insert(sensor_settings_table, null, values);  
	}    

	 */
	public static Cursor getDeviceState()
	{
		Cursor mCursor= database.rawQuery("select device_ from mobiletracker", null);
		if ( mCursor != null && mCursor.getCount() > 0 )
		{	
			mCursor.moveToFirst();  


		}  
		return mCursor; // iterate to get each value.
	}

	///Get peer info and Emergency Contact number so that we can say device is enrolled or not...If it is registered 
	// then it should return the true ... if it is true then enable the start and stop button on the home screen

	//http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/
	public static boolean isenrolled()
	{
		//Cursor mCursor1= database.rawQuery("select is_activated from my_device_info", null);
		Cursor mCursor1= database.rawQuery("select admin_status from my_device_info", null);
		//Cursor mCursor2= database.rawQuery("select count(*) from emergency_contacts", null);
		Log.d(TAG,"mCusro1 value..."+mCursor1);
		//Log.d(TAG,"mCusro1 value..."+mCursor2);
		Log.d(TAG,"XXXXXXXXXXXX count...."+mCursor1.getCount());
		//Log.d(TAG,"XXXXXXXXXXXX count...."+mCursor2.getCount());
		//Log.d(TAG,"check mcursor is null..."+(mCursor2==null));

		Log.d(TAG, "CHECK THE cursors has values or not...??");
		if (mCursor1 != null ) 
		{
			if  (mCursor1.moveToFirst()) {

				Log.d(TAG, "Registration details are available!!!");
				return true;
			}

		}

		return false;
	}

	/*public static List getDirectcontacts()
	{
		Cursor mCursor1= database.rawQuery("select * from direct_community_contacts", null);

		List<Integer> myDirectContacts = new ArrayList<Integer>();
		Log.d(TAG, "Getting direct contacts list....");
		Log.d(TAG,"Is Cursor is null???"+mCursor1);

		if (mCursor1 != null ) {
			if  (mCursor1.moveToFirst()) {
				do {
					//String firstName = mCursor1.getString(mCursor1.getColumnIndex("phno"));
					int phno = mCursor1.getInt(mCursor1.getColumnIndex("phno"));
					Log.d(TAG,"Existing contacts are ..."+phno);

					myDirectContacts.add((Integer)phno);
				}
			while (mCursor1.moveToNext());
			}
			return myDirectContacts;

		}
		else
			return myDirectContacts;
		//return null;

	}*/




	public static List getEmergencyContacts()
	{
		Cursor mCursor1= database.rawQuery("select * from emergency_contacts", null);
		List a=null;
		List<Integer> existing_contacts = new ArrayList<Integer>();
		Log.d(TAG, "Getting Emergrency contacts contacts list....");
		Log.d(TAG,"Is Cursor is null???"+mCursor1);

		if (mCursor1 != null ) {
			if  (mCursor1.moveToFirst()) {
				do {
					Log.d(TAG,"Yes, it has few emergency contact numbers");
					//String firstName = mCursor1.getString(mCursor1.getColumnIndex("phno"));
					int phno = mCursor1.getInt(mCursor1.getColumnIndex("phno"));
					Log.d(TAG,"Existing contacts are ..."+phno);
					existing_contacts.add((Integer)phno);
				}while (mCursor1.moveToNext());
				return existing_contacts;

			}
			return null;

		}
		else
			return null;
		//return null;

	}

	public static int getUnlockServiceState()
	{
		int state=0;
		//Cursor mCursor1= database.rawQuery("select email_id from my_device_info", null);
		Cursor mCursor1= database.rawQuery("select unlock_device_service_state from unlock_device_info", null);
		//Cursor mCursor2= database.rawQuery("select count(*) from emergency_contacts", null);
		Log.d(TAG,"mCusro1 value..."+mCursor1);
		//Log.d(TAG,"mCusro1 value..."+mCursor2);
		Log.d(TAG,"XXXXXXXXXXXX count...."+mCursor1.getCount());
		//Log.d(TAG,"XXXXXXXXXXXX count...."+mCursor2.getCount());
		//Log.d(TAG,"check mcursor is null..."+(mCursor2==null));

		Log.d(TAG, "CHECK THE cursors has values or not...??");
		if (mCursor1 != null ) 
		{
			if  (mCursor1.moveToFirst()) {

				state=mCursor1.getInt(mCursor1.getColumnIndex("unlock_device_service_state"));
				Log.d(TAG, "Unlock device status..."+state);
				return state;
			}

		}

		return state;
	}
	
	public static void deleteUnlockData()
	{

		database.execSQL("delete from "+ unlock_device_info_table);
		//return state;
	}

	
	

	/*public static long getNoofFailedattempts()
	{
		//Cursor mCursor1= database.rawQuery("select email_id from my_device_info", null);
		Cursor mCursor1= database.rawQuery("select failed_attempts from my_device_info", null);
		System.out.println("mCursor value...."+mCursor1);
		//Cursor mCursor2= database.rawQuery("select count(*) from emergency_contacts", null);
		Log.d(TAG,"mCusro1 value..."+mCursor1);
		//Log.d(TAG,"mCusro1 value..."+mCursor2);
		Log.d(TAG,"XXXXXXXXXXXX count...."+mCursor1.getCount());
		//Log.d(TAG,"XXXXXXXXXXXX count...."+mCursor2.getCount());
		//Log.d(TAG,"check mcursor is null..."+(mCursor2==null));

		Log.d(TAG, "CHECK THE cursors has values or not...??");
		if (mCursor1 != null ) 
		{
			if  (mCursor1.moveToFirst()) {

				Long failed_attempts=mCursor1.getLong(mCursor1.getColumnIndex("failed_attempts"));


				Log.d(TAG, "failed_attempts available .. so that returning failed_attempts.."+failed_attempts);
				return failed_attempts;
			}

		}

		return 1000;
	}*/





	public static long getPassword()
	{
		//Cursor mCursor1= database.rawQuery("select email_id from my_device_info", null);
		Cursor mCursor1= database.rawQuery("select passcode from my_device_info", null);
		System.out.println("mCursor value...."+mCursor1);
		//Cursor mCursor2= database.rawQuery("select count(*) from emergency_contacts", null);
		Log.d(TAG,"mCusro1 value..."+mCursor1);
		//Log.d(TAG,"mCusro1 value..."+mCursor2);
		Log.d(TAG,"XXXXXXXXXXXX count...."+mCursor1.getCount());
		//Log.d(TAG,"XXXXXXXXXXXX count...."+mCursor2.getCount());
		//Log.d(TAG,"check mcursor is null..."+(mCursor2==null));

		Log.d(TAG, "CHECK THE cursors has values or not...??");
		if (mCursor1 != null ) 
		{
			if  (mCursor1.moveToFirst()) {

				Long passcode1=mCursor1.getLong(mCursor1.getColumnIndex("passcode"));


				Log.d(TAG, "passcode1 available .. so that returning failed_attempts.."+passcode1);
				return passcode1;
			}

		}

		return 1000;
	}











	/*	public static String getSettings()
	{
		Cursor mCursor1= database.rawQuery("select value from sensor_settings where property ='freq_time' ", null);


		Log.d(TAG, "Getting Property value from settings table....");
		Log.d(TAG,"Is Cursor is null???"+mCursor1);

		String value=null;
		if (mCursor1 != null ) {
			if  (mCursor1.moveToFirst()) {

				Log.d(TAG,"Cursor moved to first.. so fetching the value..");
				value=  mCursor1.getString(mCursor1.getColumnIndex("value"));

					Log.d(TAG,"Existing contacts are ..."+value);

					return value;
				}

		}

		return value;

	}





	public static String getLocationData()

	{


		String locationDetails="Location Info";
		LocationManager lm;
		//Context c=this.getApplicationContext();

		lm= (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		if(lm==null)
		{
			Log.d(TAG,"location Manager is null");
		}
		Location loc=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		Log.d(TAG,"Location Object..."+loc);
		if (loc != null) 
		{
			double latitude = loc.getLatitude();
			double  longitude = loc.getLongitude();
			locationDetails=locationDetails+"latitude: "+latitude+"    longitude: "+longitude;
			Log.d(TAG,"Location Details" +locationDetails);
		}
		else 
		{
			Log.d(TAG,"Location Not available");
		}
		return locationDetails;

		//cursor.getString(1);
	}
	 */
}

