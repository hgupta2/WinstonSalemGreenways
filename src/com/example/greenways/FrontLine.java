package com.example.greenways;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Window;

public class FrontLine extends Activity {
    /** Called when the activity is first created. */
	LocationManager locationManager;
	public static Location curLocation;
	//To check if the connection to the server is okay
	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    
    	//Put the following code for disabling window title after onCreate and before setContentView, else it won't work
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.frontline);
      
        new CountDownTimer(1000, 1000) {

		     @Override
			public void onTick(long millisUntilFinished) {
		     }

		     @Override
			public void onFinish() {
		 		if(isNetworkAvailable())
		 		{		
		 			curLocation = getCurrentLocation();
		 			Intent intent=new Intent(FrontLine.this, GreenwayList.class);
		 			startActivity(intent);
		 		}
		 		else
		 		{
		 			Log.d("network error", "Oops.. unable to connect. Check your network settings!");
		 	    	Intent errorIntent = new Intent(FrontLine.this, NetworkError.class);
 		        	startActivity(errorIntent);
		 		}
		     }
		  }.start();
        
		
    }
    
    /**
	 * To fetch the current location of the user
	 * @return The location object
	 */

	private Location getCurrentLocation() {
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);

		final String provider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(provider);
		LocationListener loclis = new LocationListener() {

			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub

			}

			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				
			}
		};

		locationManager.requestLocationUpdates(provider, 120000, 10, loclis); 
		return location;
	}

	/*
	private void updateWithNewLocation(Location location) {
		
	}
	*/
}
