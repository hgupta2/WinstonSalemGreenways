package com.example.greenways;

import java.util.HashMap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DescFragment extends Fragment{

	HashMap<String, GreenwayLocation> greenwayHashMap;
	String provider;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.description, container, false);

		greenwayHashMap = GreenwayLocation.greenways;

		String str = getActivity().getIntent().getStringExtra("str");

		TextView nameGreenWay = (TextView) view.findViewById(R.id.nameGreenWay);
		nameGreenWay.setText(greenwayHashMap.get(str).getTitle()+ " at " + greenwayHashMap.get(str).getAccesspt());

		TextView nameAccessPoint = (TextView) view.findViewById(R.id.accessPointName);
		System.out.println(nameAccessPoint);
		//nameAccessPoint.setText();

		String[] l = greenwayHashMap.get(str).getLocation();
		final double lattitudeValue = Double.parseDouble(l[1]); //converting string latitude value to double
		final double longitudeValue=Double.parseDouble(l[0]); //converting string longitude value to double

		Button buttonOne = (Button) view.findViewById(R.id.get_direction);
		buttonOne.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						Uri.parse("http://maps.google.com/maps?daddr="+lattitudeValue+","+longitudeValue+
								"&saddr="+GreenwayListFragment.curLocation.getLatitude()+","+GreenwayListFragment.curLocation.getLongitude()));
				intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
				startActivity(intent);
			}
		});
		return view; 
	}

}
