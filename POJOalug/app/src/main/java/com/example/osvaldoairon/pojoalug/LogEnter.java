package com.example.osvaldoairon.pojoalug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class LogEnter extends AppCompatActivity {

    private Button acessar;
    private EditText nomeLogin;
    private EditText senhaLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_enter);
        acessar=(Button)findViewById(R.id.btn_acessar);
        nomeLogin=(EditText) findViewById(R.id.edt_login);
        senhaLogin=(EditText)findViewById(R.id.edt_senha);

        acessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nomeLogin.getText().toString().isEmpty() || senhaLogin.getText().toString().isEmpty()){
                    Toast.makeText(LogEnter.this, "Preencha os campos corretamente!", Toast.LENGTH_SHORT).show();

                }else if(true){
             /*
             Se a senha e o login for igual ao do banco de dados entao aqui inicia a acitvy main
              */

                }
                else{
                    limparCampos();
                    Toast.makeText(LogEnter.this, "Senha incorreta!", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    public void limparCampos(){
        nomeLogin.setText("");
        senhaLogin.setText("");
    }
}
