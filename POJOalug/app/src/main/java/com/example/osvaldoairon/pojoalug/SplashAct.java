package com.example.osvaldoairon.pojoalug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

public class SplashAct extends AppCompatActivity {
    private static int TIME_SPLASH=4000; // Tempo do splash em milisegundos é equivalente a 6seg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                Intent i = new Intent(SplashAct.this, LoginAct.class);
                startActivity(i);

                // Fecha esta activity
                finish();
            }
        }, TIME_SPLASH);

    }
}
