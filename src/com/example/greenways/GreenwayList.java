package com.example.greenways;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class GreenwayList extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greenwaylist);
        
        View v = findViewById(android.R.id.title);
        v.setClickable(true);
        v.setBackgroundColor(Color.parseColor("#FF007300"));
        v.setMinimumHeight(1500);
                
        v.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(GreenwayList.this, "Works!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.greenwaylist, menu);
        return true;
    }
}
