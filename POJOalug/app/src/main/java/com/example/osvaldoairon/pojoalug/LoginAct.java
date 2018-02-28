package com.example.osvaldoairon.pojoalug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginAct extends AppCompatActivity {

    private Button entrar;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entrar =(Button)findViewById(R.id.btn_entrar);
        cadastrar=(Button)findViewById(R.id.btn_cadastrar);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent at = new Intent(
                        LoginAct.this,LogEnter.class
                );
                //Toast.makeText(LoginAct.this, "", Toast.LENGTH_SHORT).show();
                startActivity(at);
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent at = new Intent(
                        LoginAct.this,CadCliente.class
                );
                startActivity(at);

            }
        });
    }
}
