package com.example.email;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    ImageView fondi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    fondi = findViewById(R.id.splfond);
        Glide.with(getApplicationContext()).load(R.drawable.fondopo).into(fondi);
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent pasar = new Intent(MainActivity.this,MainPrincipal.class);
                startActivity(pasar);
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea,4000);
    }
}