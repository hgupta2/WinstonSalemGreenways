package com.example.greenways;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;

public class GreenwayList extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.greenwaylist);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
		
		View v = findViewById(android.R.id.title);
		v.setPadding(5, 0, 0, 0);
		/*
		Button map = (Button) findViewById(R.id.head);
		
		
		v.setClickable(true);
		
		
		map.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final ProgressDialog myPd_ring = ProgressDialog.show(GreenwayList.this, "", "Loading please wait..", true);
				myPd_ring.setCancelable(true);
				new Thread(new Runnable() {
					public void run() {

						Intent intent = new Intent(GreenwayList.this, GreenwayMap.class);

						startActivity(intent);
						try {
							Thread.sleep(10000);
						} catch (Exception e) {
						}
						myPd_ring.dismiss();
					}
				}).start();

			}
			
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.greenwaylist, menu);
		return true;
	}
}
