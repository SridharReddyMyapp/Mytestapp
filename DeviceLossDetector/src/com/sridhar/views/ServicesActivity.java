package com.sridhar.views;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.sridhar.devicelossdetector.MyDB;
import com.sridhar.devicelossdetector.R;
import com.sridhar.services.PowerButtonService;
import com.sridhar.services.USBDetector;

public class ServicesActivity extends Activity  implements OnClickListener{
	private AdView adView1;
	private AdView adView2;
	private String header_add_id="ca-app-pub-3153655904585394/1494327261";

	private String footer_add_id="ca-app-pub-3153655904585394/1553202863";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		LinearLayout layout=new LinearLayout(getApplicationContext());
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

		adView2 = new AdView(this);
		adView2.setAdSize(AdSize.BANNER);
		adView2.setAdUnitId(header_add_id);

		AdRequest adRequest2 = new AdRequest.Builder()
		.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		.build();

		adView2.loadAd(adRequest2);




		View view; 
		LayoutInflater inflater = (LayoutInflater)   getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		view = inflater.inflate(R.layout.servicesview, null);
		layout.addView(view);





		ImageView unlock=(ImageView) view.findViewById(R.id.unlock);
		ImageView poweroff=(ImageView) view.findViewById(R.id.poweroff);
		ImageView usb=(ImageView) view.findViewById(R.id.usbimage);
		//ImageView safezone_button=(ImageView) findViewById(R.id.safezone_button);
		ImageView fall=(ImageView) view.findViewById(R.id.fall);


		unlock.setOnClickListener(this);
		poweroff.setOnClickListener(this);
		usb.setOnClickListener(this);
		//	safezone_button.setOnClickListener(this);
		fall.setOnClickListener(this);
		System.out.println("Sridhar Reddy....");
		
/*
   	 IntentFilter filter1;
   	 IntentFilter filter2;
   	 filter1 = new IntentFilter("android.bluetooth.BluetoothDevice.ACTION_ACL_CONNECTED");
   	 filter2 = new IntentFilter("android.bluetooth.BluetoothDevice.ACTION_ACL_CONNECTED");

		

        getApplicationContext().registerReceiver(com.sridhar.services.USBDetector.class, filter1);
    	registerReceiver(USBDetector.class, filter2);
    	
    	*/
		

		layout.addView(adView2);
		
		
		setContentView(layout);

		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		
switch(v.getId()){
    

        
        case R.id.unlock:
        
        	
        	
        	
        	
        	
        	
        	if(MyDB.getUnlockServiceState()==0)
        	{
        		AlertDialog dialog; 

        	//sa.startSensors();
        	  //startService(new Intent(this, MyService.class));
        	//SensorService.sensor_started=true;
        	 //startService(new Intent(this,BluetoothDeviceService.class));
        	 //startService(new Intent(this, SensorService.class));
        	final CharSequence[] items = {"Lock Device"," Enable Alaram ","Capture Picture & Send Email", "Send SMS to Emergency Contact","Trigger when Unlock Failed attempts>=2 "};
        	
        	
        	
        	
            // arraylist to keep the selected items
            final ArrayList<Integer> seletedItems=new ArrayList<Integer>();
           
            AlertDialog.Builder builder = new AlertDialog.Builder(ServicesActivity.this);
            builder.setTitle("Activate Device Unlock Detection!!");
           builder.setIcon(R.drawable.start_icon2);
            builder.setMultiChoiceItems(items, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
             // indexSelected contains the index of item (of which checkbox checked)
             @Override
             public void onClick(DialogInterface dialog, int indexSelected,
                     boolean isChecked) {
                 if (isChecked) {
                     // If the user checked the item, add it to the selected items
                     // write your code when user checked the checkbox 
                     seletedItems.add(indexSelected);
                     
                     
                 } else if (seletedItems.contains(indexSelected)) {
                     // Else, if the item is already in the array, remove it 
                     // write your code when user Uchecked the checkbox 
                     seletedItems.remove(Integer.valueOf(indexSelected));
                 }
             }
         })
          // Set the action buttons
         .setPositiveButton("Start", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int id) {
            	           	 
            	// services_started=true;
            	 int lock=0;
            	 int alaram=0;
            	 int capturepic=0;
            	 int sendsms=0;
            	
            	 //Lock Device 
            	 if(seletedItems.contains(new Integer(0)))
            		 lock=1;
               
               if(seletedItems.contains(new Integer(1)))
            	alaram=1;
            	   
            	   //startService(new Intent(this, SensorService.class));
            	  /* System.out.println("Start Community Service");
            	   startService(new Intent(getApplicationContext(),BluetoothDeviceService.class));*/
               
               if(seletedItems.contains(new Integer(2)))
            	   capturepic=1;
            	   
               if(seletedItems.contains(new Integer(3)))
            	   sendsms=1;
               
               
               
               MyDB.createunlock(2, alaram, capturepic, lock, sendsms,1);
              
             }
         	})
         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int id) {
                //  Your code when user clicked on Cancel
            	
            	 Toast.makeText(getApplicationContext(), "Services not started", Toast.LENGTH_SHORT).show();
            	 
             }
         });
   
            dialog = builder.create();//AlertDialog dialog; create like this outside onClick
            dialog.show();
        	}
        	
        	else
        	{
        		AlertDialog dialog2;
        		AlertDialog.Builder builder = new AlertDialog.Builder(ServicesActivity.this);
                builder.setTitle("Stop Device Unlock Detection!!");
               builder.setIcon(R.drawable.start_icon2);
               builder.setMessage("Do you want to stop Device Unlock detection?");
               builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						MyDB.deleteUnlockData();
						 Toast.makeText(getApplicationContext(), "Unlock Detection Stopped!", Toast.LENGTH_SHORT).show();
					}
				});
               
               dialog2 = builder.create();//AlertDialog dialog; create like this outside onClick
               dialog2.show();
        	}
        	
        		
        	
        	
            break;	
	




    case R.id.poweroff:

    	AlertDialog dialog2; 
    	//sa.startSensors();
    	  //startService(new Intent(this, MyService.class));
    	//SensorService.sensor_started=true;
    	 //startService(new Intent(this,BluetoothDeviceService.class));
    	 //startService(new Intent(this, SensorService.class));
    	final CharSequence[] items2 = {"Lock Device","Enable Alaram on Poweroff request! ","Capture Picture & Send Email", "Send SMS to Emergency Contact"};
        // arraylist to keep the selected items
        final ArrayList<Integer> seletedItems2=new ArrayList<Integer>();
       
        AlertDialog.Builder builder2 = new AlertDialog.Builder(ServicesActivity.this);
        builder2.setTitle("Activate Power off Detection!!");
       builder2.setIcon(R.drawable.start_icon2);
        builder2.setMultiChoiceItems(items2, null,
                new DialogInterface.OnMultiChoiceClickListener() {
         // indexSelected contains the index of item (of which checkbox checked)
         @Override
         public void onClick(DialogInterface dialog, int indexSelected,
                 boolean isChecked) {
             if (isChecked) {
                 // If the user checked the item, add it to the selected items
                 // write your code when user checked the checkbox 
                 seletedItems2.add(indexSelected);
                 
                 
             } else if (seletedItems2.contains(indexSelected)) {
                 // Else, if the item is already in the array, remove it 
                 // write your code when user Uchecked the checkbox 
                 seletedItems2.remove(Integer.valueOf(indexSelected));
             }
         }
     })
      // Set the action buttons
     .setPositiveButton("Start", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int id) {
        	// services_started=true;
        	   startService(new Intent(getApplicationContext(),PowerButtonService.class));
        	 int lock=0;
        	 int alaram=0;
        	 int capturepic=0;
        	 int sendsms=0;
        	
        	 //Lock Device 
        	 if(seletedItems2.contains(new Integer(0)))
        		 lock=1;
           
           if(seletedItems2.contains(new Integer(1)))
        	alaram=1;
        	   
        	   //startService(new Intent(this, SensorService.class));
        	  /* System.out.println("Start Community Service");
        	   startService(new Intent(getApplicationContext(),BluetoothDeviceService.class));*/
           
           if(seletedItems2.contains(new Integer(2)))
        	   capturepic=1;
        	   
           if(seletedItems2.contains(new Integer(3)))
        	   sendsms=1;
          
         }
     	})
     .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int id) {
            //  Your code when user clicked on Cancel
        	 stopService(new Intent(getApplicationContext(),PowerButtonService.class));
        	 Toast.makeText(getApplicationContext(), "Services not started", Toast.LENGTH_SHORT).show();
        	 
         }
     });

        dialog2 = builder2.create();//AlertDialog dialog; create like this outside onClick
        dialog2.show();
        break;	
        
        

    case R.id.usbimage:

    	AlertDialog dialog3; 
    	//sa.startSensors();
    	  //startService(new Intent(this, MyService.class));
    	//SensorService.sensor_started=true;
    	 //startService(new Intent(this,BluetoothDeviceService.class));
    	 //startService(new Intent(this, SensorService.class));
    	final CharSequence[] items3 = {"Lock Device","Enable Alaram on USB unplug ","Capture Picture & Send Email", "Send SMS to Emergency Contact"};
        // arraylist to keep the selected items
        final ArrayList<Integer> seletedItems3=new ArrayList<Integer>();
       
        AlertDialog.Builder builder3 = new AlertDialog.Builder(ServicesActivity.this);
        builder3.setTitle("Activate USB unplug Detection!!");
       builder3.setIcon(R.drawable.start_icon2);
        builder3.setMultiChoiceItems(items3, null,
                new DialogInterface.OnMultiChoiceClickListener() {
         // indexSelected contains the index of item (of which checkbox checked)
         @Override
         public void onClick(DialogInterface dialog, int indexSelected,
                 boolean isChecked) {
             if (isChecked) {
                 // If the user checked the item, add it to the selected items
                 // write your code when user checked the checkbox 
                 seletedItems3.add(indexSelected);
                 
                 
             } else if (seletedItems3.contains(indexSelected)) {
                 // Else, if the item is already in the array, remove it 
                 // write your code when user Uchecked the checkbox 
                 seletedItems3.remove(Integer.valueOf(indexSelected));
             }
         }
     })
      // Set the action buttons
     .setPositiveButton("Start", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int id) {
        	// services_started=true;
        	 
        	 int lock=0;
        	 int alaram=0;
        	 int capturepic=0;
        	 int sendsms=0;
        	
        	 //Lock Device 
        	 if(seletedItems3.contains(new Integer(0)))
        		 lock=1;
           
           if(seletedItems3.contains(new Integer(1)))
        	alaram=1;
        	   
        	   //startService(new Intent(this, SensorService.class));
        	  /* System.out.println("Start Community Service");
        	   startService(new Intent(getApplicationContext(),BluetoothDeviceService.class));*/
           
           if(seletedItems3.contains(new Integer(2)))
        	   capturepic=1;
        	   
           if(seletedItems3.contains(new Integer(3)))
        	   sendsms=1;
        	 
        	 
          
         }
     	})
     .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int id) {
            //  Your code when user clicked on Cancel
        	
        	 Toast.makeText(getApplicationContext(), "Services not started", Toast.LENGTH_SHORT).show();
        	 
         }
     });

        dialog3 = builder3.create();//AlertDialog dialog; create like this outside onClick
        dialog3.show();
        break;	
        
        
        

        
        
        

    case R.id.fall:

    	AlertDialog dialog4; 
    	//sa.startSensors();
    	  //startService(new Intent(this, MyService.class));
    	//SensorService.sensor_started=true;
    	 //startService(new Intent(this,BluetoothDeviceService.class));
    	 //startService(new Intent(this, SensorService.class));
    	final CharSequence[] items4 = {"Enable Alaram when Device falls ","Capture Picture & Send Email", "Send SMS to Emergency Contact"};
        // arraylist to keep the selected items
        final ArrayList<Integer> seletedItems4=new ArrayList<Integer>();
       
        AlertDialog.Builder builder4 = new AlertDialog.Builder(ServicesActivity.this);
        builder4.setTitle("Activate Device Fall Detection!!");
        builder4.setIcon(R.drawable.start_icon2);
        builder4.setMultiChoiceItems(items4, null,
                new DialogInterface.OnMultiChoiceClickListener() {
         // indexSelected contains the index of item (of which checkbox checked)
         @Override
         public void onClick(DialogInterface dialog, int indexSelected,
                 boolean isChecked) {
             if (isChecked) {
                 // If the user checked the item, add it to the selected items
                 // write your code when user checked the checkbox 
            	 seletedItems4.add(indexSelected);
                 
                 
             } else if (seletedItems4.contains(indexSelected)) {
                 // Else, if the item is already in the array, remove it 
                 // write your code when user Uchecked the checkbox 
            	 seletedItems4.remove(Integer.valueOf(indexSelected));
             }
         }
     })
      // Set the action buttons
     .setPositiveButton("Start", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int id) {
        	// services_started=true;
        	 
           if(seletedItems4.contains(new Integer(0))){
        	   //startService(new Intent(this, SensorService.class));
        	   System.out.println("Start Power Button  Service");
        	   
        	//   startService(new Intent(getApplicationContext(),PowerButtonService.class));
        	   
        		
        	   
          	// 	startService(new Intent(getApplicationContext(),SensorService.class));
          	// 	startService(new Intent(getApplicationContext(),GPSLocationService.class));
           }
           if(seletedItems4.contains(new Integer(1))){
        	   //startService(new Intent(this, SensorService.class));
        	  /* System.out.println("Start Community Service");
        	   startService(new Intent(getApplicationContext(),BluetoothDeviceService.class));*/
           }
           if(seletedItems4.contains(new Integer(2))){
        	   //startService(new Intent(this, SensorService.class));
        	/*   System.out.println("Start Location Service");
        	   startService(new Intent(getApplicationContext(),GPSLocationService.class));*/
           }
            
          
         }
     	})
     .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int id) {
            //  Your code when user clicked on Cancel
        	
        	 Toast.makeText(getApplicationContext(), "Services not started", Toast.LENGTH_SHORT).show();
        	 
         }
     });

        dialog4 = builder4.create();//AlertDialog dialog; create like this outside onClick
        dialog4.show();
        break;	
        
        
        
        
        
        
        
        
        
        
        
}




























}
}
