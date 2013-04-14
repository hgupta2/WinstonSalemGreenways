package com.example.greenways;

import java.util.HashMap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.description, container, false);

		greenwayHashMap = GreenwayLocation.greenways;

		String str = getActivity().getIntent().getStringExtra("str");

		TextView nameGreenWay = (TextView) view.findViewById(R.id.nameGreenWay);
		nameGreenWay.setText(greenwayHashMap.get(str).getTitle()+ " at " + greenwayHashMap.get(str).getAccesspt());

		/*TextView nameAccessPoint = (TextView) view.findViewById(R.id.accessPointName);
		System.out.println("AP : " +nameAccessPoint);
*/
		String[] l = greenwayHashMap.get(str).getLocation();
		final double lattitudeValue = Double.parseDouble(l[1]); //converting string latitude value to double
		final double longitudeValue=Double.parseDouble(l[0]); //converting string longitude value to double

		Button buttonOne = (Button) view.findViewById(R.id.get_direction);
		buttonOne.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {

				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				final CharSequence[] items = {"Foo", "Bar", "Baz"};

				builder.setTitle("Make your selection");
				builder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
								Uri.parse("http://maps.google.com/maps?daddr="+lattitudeValue+","+longitudeValue+
										"&saddr="+GreenwayListFragment.curLocation.getLatitude()+","+GreenwayListFragment.curLocation.getLongitude()+"&mode=b"));
						intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
						startActivity(intent);
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
		

		nameGreenWay.setText(greenwayHashMap.get(str).getTitle()); 
		nameGreenWay.setTextColor(Color.argb(90, 00, 150, 00));
		
		TextView nameAccessPoint = (TextView) view.findViewById(R.id.nameAccessPoint);
		nameAccessPoint.setText("\n" + "Access Point at " +greenwayHashMap.get(str).getAccesspt()
				+ "\n" + "\n" +greenwayHashMap.get(str).getDescription());
		nameAccessPoint.setTextColor(Color.argb(90, 255, 00, 00));
		
		return view; 
	}

}
