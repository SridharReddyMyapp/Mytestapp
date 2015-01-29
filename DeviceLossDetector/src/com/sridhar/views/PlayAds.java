package com.sridhar.views;

import android.app.Activity;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
//import com.unity3d.player.UnityPlayer;

public class PlayAds {
    private String adUnitID = "ca-app-pub-YOUR-AD_UNIT-ID-GOES-HERE";
    private Activity activity; //Store the android main activity
    private AdView adView; //The AdView we will display to the user
    private LinearLayout layout; //The layout the AdView will sit on
    
    public PlayAds () {
    	 // activity = UnityPlayer.currentActivity;
          
    	    activity.runOnUiThread(new Runnable() {
    	        public void run(){
    	            adView = new AdView(activity);
    	            adView.setAdUnitId(adUnitID);
    	            adView.setAdSize(AdSize.SMART_BANNER);
    	      
    	            AdRequest request = new AdRequest.Builder().build();
    	            
    	            //Add adListner Code in next step here
    	      
    	            adView.loadAd(request);
    	        }
    	    });
    }
}