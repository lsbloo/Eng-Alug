package com.example.osvaldoairon.pojoalug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

public class SplashAct extends AppCompatActivity {
    private static int TIME_SPLASH=5000; // Tempo do splash em milisegundos é equivalente a 5seg;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar =(ProgressBar)findViewById(R.id.progressBar2);


        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
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
