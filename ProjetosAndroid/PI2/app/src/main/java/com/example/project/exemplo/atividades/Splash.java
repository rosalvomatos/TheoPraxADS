package com.example.project.exemplo.atividades;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.project.exemplo.Activity.HomeActivity;
import com.example.project.exemplo.R;
import com.example.project.exemplo.util.CGuideWS;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Splash extends Activity implements Runnable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }


    public void run(){
        startActivity(new Intent(this, HomeActivity.class));
        finish();

    }

}
