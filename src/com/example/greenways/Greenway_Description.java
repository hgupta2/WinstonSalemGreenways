package com.example.greenways;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class Greenway_Description extends FragmentActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.direction_desc);
		View v = findViewById(android.R.id.title);
		v.setPadding(5, 0, 0, 0);
	}
}