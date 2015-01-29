package com.sridhar.devicelossdetector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mobiletracker";

    private static final int DATABASE_VERSION =1;

    // Database creation sql statement
    private static final String my_device_info = "create table if not exists my_device_info("
    		+ "phno1 BIGINT, email_id text, passcode text,device_info_id BIGINT,device_imei text,admin_status BIGINT);";
    
    

    private static final String poweroff_device_info_table = "create table if not exists poweroff_device_info("
    		+ "poweroff_alaram BIGINT, poweroff_capturepic BIGINT, poweroff_lock BIGINT,poweroff_sendsms BIGINT,poweroff_service_state BIGINT);";
    
    


    private static final String unlock_device_info = "create table if not exists unlock_device_info("
    		+ "unlock_failed_attempts BIGINT, unlock_alaram BIGINT, unlock_capturepic BIGINT,unlock_lock BIGINT,unlock_sendsms BIGINT,unlock_device_service_state BIGINT);";
    
    
    
	    private static final String usb_device_info_table = "create table if not exists usb_device_info("
	    		+ "usb_device_alaram BIGINT, usb_device_capturepic BIGINT, usb_device_lock BIGINT,usb_device_send_sms BIGINT,usb_device_service_state BIGINT);";
	    
	    
		
		 private static final String fall_device_info_table = "create table if not exists fall_device_info("
		    		+ "fall_alaram BIGINT, fall_capturepic BIGINT, fall_lock BIGINT,fall_sendsms BIGINT,fall_device_service_state BIGINT);";
		    
		    
		
    
    	
   /* private static final String profiles_table = "create table if not exists profiles_table("
    		+ "_id BIGINT PRIMARY KEY,sensor_intervaltime BIGINT, location_safezone BIGINT);";
    
    
    
    private static final String direct_community_contacts = "create table if not exists direct_community_contacts("
    		+ "id integer,phno BIGINT primary key, email_id text, bluetoothinfo text);";
    
    
    private static final String in_direct_community_contacts = "create table if not exists in_direct_community_contacts("
    		+ "id integer  ,phno BIGINT primary key, email_id text, bluetoothinfo text);";
    
    private static final String emergency_contacts = "create table if not exists emergency_contacts("
    		+ "id integer  ,phno BIGINT primary key);";
    
    
    private static final String safe_locations = "create table if not exists safe_locations("
    		+ "id integer ,latitude text,longtitude text);";
    

    private static final String sensor_settings = "create table if not exists sensor_settings("
    		+ "id integer ,property text,value text);";
    */
    
    public MyDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
    	
    	        database.execSQL(my_device_info);
    	        database.execSQL(unlock_device_info);
    	        database.execSQL(poweroff_device_info_table);
    	        database.execSQL(fall_device_info_table);
    	        database.execSQL(usb_device_info_table);
    	       /* database.execSQL(direct_community_contacts);
    	        
    	        database.execSQL(in_direct_community_contacts);
    	        
    	        database.execSQL(emergency_contacts);
    	        
    	        database.execSQL(safe_locations);
    	        
      	        database.execSQL(sensor_settings);
      	        database.execSQL(profiles_table);*/
    	        
    	        System.out.println("XXXXXXXXXXXXXXXXXXXXX tables are created..................");
       
     
        }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
            int newVersion) {
       
        //database.execSQL("DROP TABLE IF EXISTS MyEmployees");
        onCreate(database);
    }
}