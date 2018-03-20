package com.example.osvaldoairon.pojoalug;

import android.content.Context;
import android.net.ConnectivityManager;
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
import android.widget.Toast;

public class SplashAct extends AppCompatActivity {
    private static int TIME_SPLASH=5000; // Tempo do splash em milisegundos é equivalente a 5seg;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar =(ProgressBar)findViewById(R.id.progressBar2);
        exibir_Conexao(verificaConexao());


        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                Intent i = new Intent(SplashAct.this, LogEnter.class);
                startActivity(i);

                // Fecha esta activity
                finish();
            }
        }, TIME_SPLASH);

    }

    public void exibir_Conexao(boolean conexao){
        if(!conexao){
            Toast.makeText(SplashAct.this, "SmartPhone não conectado a internet", Toast.LENGTH_SHORT).show();
        }
    }

    public  boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
