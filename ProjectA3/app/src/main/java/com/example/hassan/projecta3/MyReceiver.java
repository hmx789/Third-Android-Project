package com.example.hassan.projecta3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    final String ny = "New York, NY";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i("A3","Inside broadcast receiver A3");
        if (intent.getAction().equalsIgnoreCase(ny)) {
            Toast.makeText(context,"NY Under Construction",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(context, CAActivity.class);
            context.startActivity(i);
        }
    }
}
