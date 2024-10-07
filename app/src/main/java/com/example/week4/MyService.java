package com.example.week4;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    MediaPlayer MP;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Show toast message when service is started
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        // Initialize and start MediaPlayer
        MP = MediaPlayer.create(this, R.raw.music1); // Corrected reference to audio file
        MP.setLooping(true);
        MP.start();

        // Return sticky to restart service if it's killed
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Show toast message when service is stopped
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();

        // Stop and release MediaPlayer when service is destroyed
        if (MP != null) {
            MP.stop();
            MP.release();
            MP = null; // Set to null to avoid memory leaks
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }
}
