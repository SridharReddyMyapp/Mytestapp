package com.sridhar.views;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.sridhar.devicelossdetector.MyDB;
import com.sridhar.devicelossdetector.R;
import com.sridhar.devicelossdetector.R.layout;
 
public class SplashScreen extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MyDB m1m=new MyDB(getApplicationContext());
       // final boolean is_enrolled=m1m.isenrolled();
        final boolean is_enrolled=ActivityDecision.decision();
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
         
// METHOD 1     
         
         /****** Create Thread that will sleep for 5 seconds *************/        
        Thread background = new Thread() {
            public void run() {
                 
                try {
                    // Thread will sleep for 5 seconds
                    sleep(6*1000);
                    System.out.println("Thread Started???");
                    
                    
                     
                    
                    if(is_enrolled==false)
                    {
                    	Intent i=new Intent(getBaseContext(),Registartion.class);
                        startActivity(i);
                         
                        //Remove activity
                        finish();
                           
                          //Remove activity
                          finish();
                    }
                    else
                    	
                    {
                    	Intent i=new Intent(getBaseContext(),ServicesActivity.class);
                        startActivity(i);
                         
                        //Remove activity
                        finish();
                    	
                    }
                     
                    // After 5 seconds redirect to another intent
                    
                 /*   final MyDB m1m=new MyDB(getApplicationContext());
                    
                    System.out.println(m1m.isenrolled());
        			if(m1m.isenrolled()==false)
        			{
        					
        				Intent i=new Intent(getBaseContext(),Registartion.class);
                        startActivity(i);
                         
                        //Remove activity
                        finish();
                           
                          //Remove activity
                          finish();
                	
        			}
        			else
        			{
        				Intent i=new Intent(getBaseContext(),ServicesActivity.class);
                        startActivity(i);
                         
                        //Remove activity
                        finish();
                	
        			}*/
                  
                     
                } catch (Exception e) {


                	e.printStackTrace();
                }
            }
        };
         
        // start thread
        background.start();
         
//METHOD 2  
         
        /*
        new Handler().postDelayed(new Runnable() {
              
            // Using handler with postDelayed called runnable run method
  
            @Override
            public void run() {
                Intent i = new Intent(MainSplashScreen.this, FirstScreen.class);
                startActivity(i);
  
                // close this activity
                finish();
            }
        }, 5*1000); // wait for 5 seconds
        */
    }
     
    @Override
    protected void onDestroy() {
         
        super.onDestroy();
         
    }
}