package com.example.hassan.projecta1;

import android.app.Activity;
import android.content.Intent;
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
    final String CITY = "location";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button sf = findViewById(R.id.button1);
        final Button ny = findViewById(R.id.button2);

        sf.setOnClickListener(new View.OnClickListener() {  //Checking if san francisco was clicked
            @Override
            public void onClick(View v) {
                checkPermissionAndBroadcast(sanFran);

            }
        });
        ny.setOnClickListener(new View.OnClickListener(){   //Was new york button clicked
            @Override
            public void onClick(View v) {
                checkPermissionAndBroadcast(newYork);

            }
        });

    }

    private void checkPermissionAndBroadcast(String c) { // Handles to see if permission was granted before or needs to be granted
        if (ContextCompat.checkSelfPermission(this,hazPermission) == PackageManager.PERMISSION_GRANTED) {
            Intent i = new Intent(c);
            sendOrderedBroadcast(i,hazPermission);
        }
        else if (c.equalsIgnoreCase(newYork)){
            ActivityCompat.requestPermissions(this,new String[]{hazPermission}, 5);

        }
        else if (c.equalsIgnoreCase(sanFran)) {
            ActivityCompat.requestPermissions(this,new String[]{hazPermission}, 6);

        }
    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                if (code == 5) {        // if it is for new york
                    Intent i = new Intent(newYork);
                    sendOrderedBroadcast(i,hazPermission);
//                    Log.i("MainActivity","Sent ordered broadcast in new york");
                }
                else if (code == 6) {           //if it is for san francisco
                    Intent i = new Intent(sanFran);
                    sendOrderedBroadcast(i,hazPermission);
//                    Log.i("MainActivity","Sent ordered broadcast in san francisco");
                }

            }
            else {      //If not granted come here
                Toast.makeText(this, "Bummer: No permission", Toast.LENGTH_SHORT).show();
            }
        }
        // if the result was not passed correctly

    }


}
