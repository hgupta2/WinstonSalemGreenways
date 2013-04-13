package com.example.greenways;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class GreenwayList extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greenwaylist);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.greenwaylist, menu);
        return true;
    }
}
