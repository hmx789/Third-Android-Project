package com.example.hassan.projecta2;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String hazPermission = "edu.uic.cs478.f18.project3";
    final String newYork = "New York, NY";
    final String sanFran = "San Francisco, CA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button rButton = findViewById(R.id.button1);

        rButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        BroadcastReceiver NYReceiver = new NYReceiver();
        BroadcastReceiver CAReceiver = new CAReceiver();
        IntentFilter nyFilter = new IntentFilter(newYork);
        IntentFilter caFilter = new IntentFilter(sanFran);

        registerReceiver(NYReceiver,nyFilter);
        registerReceiver(CAReceiver,caFilter);

    }

    
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,hazPermission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{hazPermission},0);
        }
    }

    public void onRequestPermissionsResult(int code, String[] permissions,int[] results) {
        if (results.length > 0) {
            if (results[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Bummer: no permission given in A2", Toast.LENGTH_SHORT).show();

            }
        }
    }

}
