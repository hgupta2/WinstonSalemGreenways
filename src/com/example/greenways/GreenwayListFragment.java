package com.example.greenways;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GreenwayListFragment extends ListFragment {

	ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	LocationManager locationManager;
	public static Location curLocation;

	// flag for GPS status
	public boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;
	
	// flag for GPS status
	boolean canGetLocation = false;
	
	LocationListener loclis = new LocationListener() {

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		public void onProviderEnabled(String provider) {
			curLocation = getCurrentLocation();

		}

		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		public void onLocationChanged(Location location) {
			curLocation = getCurrentLocation();
		}
	};
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		SimpleAdapter adapter = new SimpleAdapter(
				this.getActivity(),
				list,
				R.layout.custome_row_view,
				new String[] {"title","accessPointName","distance"},
				new int[] {R.id.greenwayName,R.id.accessPointName, R.id.distance}
				);/*{
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View v = super.getView(position, convertView,   parent);
				v.setBackgroundResource(R.drawable.custom_button); //or whatever is your default color
				//if the position exists in that list the you must set the background to BLUE
				return v;
			}
		};*/
		
		populateList();
		setListAdapter(adapter);


	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Do something with the data
		Intent intent = new Intent(this.getActivity(), Greenway_Description.class);

		@SuppressWarnings("unchecked")
		HashMap<String, String> item = (HashMap<String, String>) l.getItemAtPosition(position);
		intent.putExtra("str", item.get("accessPointName"));
		System.out.println(item.get("accessPointName"));
		startActivity(intent);
	}


	/**
	 * Populate the array list using the hashmap greenways.
	 */
	private void populateList() {

		if(GreenwayLocation.greenways == null){ // For calling parser just once
			AccessptParse parseXMLTask = new AccessptParse(this.getActivity());
			try {
				GreenwayLocation.greenways = parseXMLTask.execute("accesspt").get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		curLocation = getCurrentLocation();
		/*
		Location curLocation = new Location("dummy");
		curLocation.setLatitude(36.160642);
		curLocation.setLongitude(-80.305375);
		 */
		for(String key : GreenwayLocation.greenways.keySet()) {

			String[] accessLocation = GreenwayLocation.greenways.get(key).getLocation();
			String title = GreenwayLocation.greenways.get(key).getTitle();
			String accesspt = GreenwayLocation.greenways.get(key).getAccesspt();

			double latitudeValue = Double.parseDouble(accessLocation[1]); //converting string latitude value to double
			double longitudeValue = Double.parseDouble(accessLocation[0]); //converting string longitude value to double

			double distanceDouble = getDistance(latitudeValue, longitudeValue, curLocation.getLatitude(), curLocation.getLongitude());

			DecimalFormat df = new DecimalFormat("##.##");
			Double distanceInMiles = distanceDouble*0.000621371;
			distanceInMiles = Double.valueOf(df.format(distanceInMiles));
			final String distanceString = String.valueOf(distanceInMiles);    

			HashMap<String,String> temp = new HashMap<String,String>();
			temp.put("distance", distanceString+" miles");
			temp.put("title", title);
			temp.put("accessPointName", accesspt);

			list.add(temp);

		}

		Collections.sort(list, new Comparator<HashMap<String, String>>() {

			public int compare(HashMap<String, String> lhs,
					HashMap<String, String> rhs) {
				// TODO Auto-generated method stub
				return lhs.get("distance").compareTo(rhs.get("distance"));
			}
		});
		locationManager.removeUpdates(loclis);
	}

	/**
	 * To fetch the current location of the user
	 * @return The location object
	 */
	/*
	private Location getCurrentLocation() {
		locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);

		
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);

		String provider = locationManager.getBestProvider(criteria, true);
		//String gpsProvider = LocationManager.GPS_PROVIDER;
		//Location location = locationManager.getLastKnownLocation(gpsProvider);
		
		Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, loclis); 

		return location;
	}
*/

	/**
	 * Function to get the user's current location
	 * @return
	 */
	public Location getCurrentLocation() {
	    Location location = null;
		try {
	        locationManager = (LocationManager)this.getActivity().getSystemService(Context.LOCATION_SERVICE);

	        // getting GPS status
	        isGPSEnabled = locationManager
	                .isProviderEnabled(LocationManager.GPS_PROVIDER);

	        Log.v("isGPSEnabled", "=" + isGPSEnabled);

	        // getting network status
	        isNetworkEnabled = locationManager
	                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

	        Log.v("isNetworkEnabled", "=" + isNetworkEnabled);

	        if (isGPSEnabled == false && isNetworkEnabled == false) {
	            // no network provider is enabled
	        } else {
	            this.canGetLocation = true;
	            if (isNetworkEnabled) {
	                locationManager.requestLocationUpdates(
	                        LocationManager.NETWORK_PROVIDER,
	                        10, 1000*60*1, loclis);
	                Log.d("Network", "Network");
	                if (locationManager != null) {
	                    location = locationManager
	                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	                    /*if (location != null) {
	                        latitude = location.getLatitude();
	                        longitude = location.getLongitude();
	                    }*/
	                }
	            }
	            // if GPS Enabled get lat/long using GPS Services
	            if (isGPSEnabled) {
	                if (location == null) {
	                    locationManager.requestLocationUpdates(
	                            LocationManager.GPS_PROVIDER,
	                            10, 1000*60*1, loclis);
	                    Log.d("GPS Enabled", "GPS Enabled");
	                    if (locationManager != null) {
	                        location = locationManager
	                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
	                        /*if (location != null) {
	                            latitude = location.getLatitude();
	                            longitude = location.getLongitude();
	                        }*/
	                    }
	                }
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return location;
	}

	/**
	 * Finds distance between two coordinate pairs.
	 *
	 * @param lat1 First latitude in degrees
	 * @param lon1 First longitude in degrees
	 * @param lat2 Second latitude in degrees
	 * @param lon2 Second longitude in degrees
	 * @return distance in meters
	 */
	public static double getDistance(double lat1, double lon1, double lat2, double lon2) {

		final double Radius = 6371 * 1E3; // Earth's mean radius

		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return Radius * c;
	}

}
