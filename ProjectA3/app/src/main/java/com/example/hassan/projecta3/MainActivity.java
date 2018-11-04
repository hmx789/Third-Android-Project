package com.example.hassan.projecta3;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String hazPermission = "edu.uic.cs478.f18.project3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,hazPermission) == PackageManager.PERMISSION_DENIED) {
           ActivityCompat.requestPermissions(this,new String[]{hazPermission},0);
        }
    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this,"Permission not granted A3",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
