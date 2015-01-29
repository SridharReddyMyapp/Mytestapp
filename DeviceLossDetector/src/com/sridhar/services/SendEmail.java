package com.sridhar.services;

import java.io.File;

import android.os.AsyncTask;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;


public class SendEmail extends AsyncTask<Void, Void, Void>{
	TelephonyManager telephonyManager;
	
	public static int sendEmailStatus=0;
	String filename;
	/*public SendEmail(String filename) {
		// TODO Auto-generated constructor stub
		
		this.filename=filename;
	}
	*/
	@Override 
	protected  Void doInBackground(Void... params)
	{
		
		sendEmailStatus=1;
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			CameraView.is_camera_opened=0;
		}
		
		File f = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "test.jpg");

		String filename=f.getPath();
		
		/* File f=new File(CameraView.getFilepath());
		
		*/
		
		System.out.println("XXXXXXXXXXXXXXXXXXXXXx FileName ...."+filename);
		
		// TODO Auto-generated method stub
		//Mail m = new Mail("alliswellgoodfellas@gmail.com", "Admin@225"); 
		Mail m = new Mail("alliswellgoodfellas@gmail.com", "dwgd rkda wuao eehp");
		
		//Mail m = new Mail(MyDB.getEmailid(),MyDB.getPassword()); 
		System.out.println("XXXXXXXXXXXXXXXXXX Mail object created-----");
		String[] toArr = {"alliswellgoodfellas@gmail.com"};
		m.setTo(toArr); 
		String toemail="alliswellgoodfellas@gmail.com";
		
		//String toemail=MyDB.getEmailID();
		System.out.println("Recieved Email id.... "+toemail);
		
		if(toemail.equalsIgnoreCase(" ")||toemail==null)
		{
			String[] toArr1 = {toemail};
			m.setTo(toArr1);
		}
		else
		{
			String[] toArr2 = {"suram.sridhar@gmail.com",toemail};
			m.setTo(toArr2);
			
		}
		
	
			
		m.setFrom("alliswellgoodfellas@gmail.com");
		
		

		
		String msgdetails="Hi, Caution!!! Seems to be your device is not with you or someone tried to Unlock/Power off your device!!!! ";
		
		System.out.println("XXXXXXXXXXXXXXXXXXx Got Message details");
	
//sString callslist=getCallsData();
	System.out.println("XXXXXXXXXXXXXXXXXXx Got missed or recieved calls info-----");


//String outcallslist=getoutCallsData();
System.out.println("XXXXXXXXXXXXXXXXXXx Got dailled calls info-----");

String locationData="";
//String locationData=getLocationData();
	System.out.println("XXXXXXXXXXXXXXXXXXx Got Location Info----");
	

//String mynumber = getMyPhoneNumber();

		m.setSubject("============High Important!!!! Your Mobile accessed by intruder !!!!! ============");
		
		

//		m.setBody("Your device Details \n"+mynumber+"\n\n"+"Your Message List \n "+msgdetails+"\n\n <B>Missed & Recieved Calls list</B> \n"+callslist+"\n\n Dailled calls list\n"+outcallslist+"\n\n<i>Thanks for using my app</i>");

		//m.setBody("\n\n Your Message List \n "+msgdetails+"\n\n Missed & Recieved Calls list \n"+callslist+" Dailled calls list\n"+outcallslist+"\n \n Location Details"+locationData+"\n\n Thanks for using my app");
		m.setBody(msgdetails);
		
		try { 
			m.addAttachment(filename); 

			if(m.send()) 
			{ 
				System.out.println("XXXXXXXXXXXXXxxxxx Mail sent successfully");	
				CameraView.is_camera_opened=0;
			} else 
			{ 
				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXX  Problem in sending email");
				CameraView.is_camera_opened=0;
			} 
			} 
		catch(Exception e) { 
			//Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
			System.out.println("XXXXXXXXXXXXX Exception....."+ e);
			e.printStackTrace();
			Log.e("com.sridhar.important", "Could not send email", e); 
			CameraView.is_camera_opened=0;
			}
			finally {
			
					//f.delete();
				CameraView.is_camera_opened=0;
			}
		return null;
		
}
		


/*
	public  static String getLocationData()
	{
		String locationdetails=MyDB.getLocationData();

		return locationdetails;
	}
	
	
*/

	/*public  static String getDBData()
	{

		String phonenumberfrom ;
		String messagetext;
		String msgrecieveddatetime;
		
	

		String msgdetails="";
		Cursor c=MyDB.getMSGdetails();
		

		if(c==null||c.getCount()<=0)
		{
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXxx cursor is null "+c+" cursor.movetofirst--"+c.moveToFirst());
			return msgdetails;
		}
		else
		{
			do{

				phonenumberfrom = c.getString(c.getColumnIndex("phnumber"));
				messagetext = c.getString(c.getColumnIndex("msg"));
				msgrecieveddatetime = c.getString(c.getColumnIndex("msgdatetime"));
				System.out.println("Message date and Time"+msgrecieveddatetime);
				msgdetails=msgdetails+phonenumberfrom+"::"+messagetext+"::"+msgrecieveddatetime+"::\n\n\n";
				
				// do what ever you want here
			}while(c.moveToNext());

			c.close();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXX DB DATA ----------- "+msgdetails);

		}

		return msgdetails;
	}



*/	
	/*public  static String getCallsData()
	{

		String phno;
		String calldatetime;

		String calldata="";
		Cursor c1=MyDB.selectRecords();

		if(c1==null||c1.getCount()<=0)
		{
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXxx cursor is null "+c1+" cursor.movetofirst--"+c1.moveToFirst());
			return calldata;
		}
		else
		{
			do{

				phno = c1.getString(c1.getColumnIndex("phno"));
				calldatetime = c1.getString(c1.getColumnIndex("calldatetime"));
				
				calldata=calldata+phno+"::"+calldatetime+"::\n\n\n";
				
				// do what ever you want here
			}while(c1.moveToNext());

			c1.close();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXX DB DATA ----------- "+calldata);

		}

		return calldata;
	}

	


	public  static String getoutCallsData()
	{

		String outphno;
		String outcalldatetime;

		String outcalldata="";
		Cursor c1=MyDB.getOutcalls();

		if(c1==null||c1.getCount()<=0)
		{
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXxx cursor is null "+c1+" cursor.movetofirst--"+c1.moveToFirst());
			return outcalldata;
		}
		else
		{
			do{

				outphno = c1.getString(c1.getColumnIndex("outphno"));
				outcalldatetime = c1.getString(c1.getColumnIndex("outcalldatetime"));
				
				outcalldata=outcalldata+outphno+"::"+outcalldatetime+"::\n\n\n";
				
				// do what ever you want here
			}while(c1.moveToNext());

			c1.close();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXX DB DATA ----------- "+outcalldata);

		}

		return outcalldata;
	}
	

	public String getMyPhoneNumber()
	{
		String deviceinfo="";
		Cursor c1=MyDB.getmyphno();
		System.out.println("XXXXXXXXXXXX count...."+c1.getCount());
		if(c1==null)
		{
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXxx  in send email cursor is null c1 is null "+c1+" cursor.movetofirst--"+c1.moveToFirst());
		}
		if(c1==null||c1.getCount()<=0)
		{
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXxx  in Get My Phone Number send email cursor is null "+c1+" cursor.movetofirst--"+c1.moveToFirst());
			return deviceinfo;
		}
		else
		{
			do{

				deviceinfo = c1.getString(c1.getColumnIndex("myphonenumber"));
				
				deviceinfo=deviceinfo+deviceinfo+"::\n\n\n";
				
				// do what ever you want here
			}while(c1.moveToNext());

			c1.close();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXX DB DATA Device Info----------- "+deviceinfo);

		}

		return deviceinfo;
	}
	
*/
	
}


