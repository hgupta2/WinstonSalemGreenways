package com.example.greenways;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Greenway_Description extends FragmentActivity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direction_desc);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.greenwaylist, menu);
        return true;
    }*/

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.menu_map:
			Intent map = new Intent(Greenway_Description.this, GreenwayMap.class);     
			startActivity(map);
			break;
		case R.id.menu_weather:
			Weather.inital(this);
			break;
		case R.id.menu_citylink:
			Intent citylink = new Intent(Greenway_Description.this, CityLink.class);     
			startActivity(citylink);
			break;
		default:
			break;
		}
		return true;
	}*/
}