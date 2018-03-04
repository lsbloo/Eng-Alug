package com.example.osvaldoairon.pojoalug;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class LogEnter extends AppCompatActivity {

    private ProgressBar progressBar;
    private CardView acessar;
    private TextView criarConta;
    private EditText emailLogin;
    private EditText senhaLogin;
    private FirebaseAuth mAutentica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_enter);


        criarConta =(TextView)findViewById(R.id.acessar);
        acessar =(CardView)findViewById(R.id.card);
        emailLogin =(EditText) findViewById(R.id.edt_email);
        senhaLogin=(EditText)findViewById(R.id.edt_pass);

        //progressBar = (ProgressBar)findViewById(R.id.progressBar);


        mAutentica = FirebaseAuth.getInstance();


        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(emailLogin.getText().toString().isEmpty() || senhaLogin.getText().toString().isEmpty()){
                    Toast.makeText(LogEnter.this, "Preencha os campos corretamente!", Toast.LENGTH_SHORT).show();

                }

               else{
                   String email = emailLogin.getText().toString();
                   String password = senhaLogin.getText().toString();



                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        emailLogin.setError("Por favor coloque um email valido!");
                        emailLogin.requestFocus();
                    }
                    if(password.length() <6){
                        senhaLogin.setError("Password maior que 6 digitos porfavor");
                        senhaLogin.requestFocus();
                    }
                    progressBar.setVisibility(View.VISIBLE);

                    mAutentica.createUserWithEmailAndPassword(email,password).addOnCompleteListener(LogEnter.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                Log.d("Criando Usuario","CriandoUsuarioEmail:sucesso");
                                //progressBar.setVisibility(View.GONE);
                                Toast.makeText(LogEnter.this, "Conta Criada!", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAutentica.getCurrentUser();
                            }
                            else{
                                if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(LogEnter.this, "Você já esta cadastrado", Toast.LENGTH_SHORT).show();
                                }
                                //Toast.makeText(LogEnter.this, "Falha na criação do usuario", Toast.LENGTH_SHORT).show();
                                Log.d("Falha Criação Usuario", "Criação do Usuario com Falha");

                            }


                        }
                    });
                    limparCampos();
                }

            }

        });

        acessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (emailLogin.getText().toString().isEmpty() || senhaLogin.getText().toString().isEmpty()) {
                    Toast.makeText(LogEnter.this, "Preencha os campos corretamente!", Toast.LENGTH_SHORT).show();

                } else {
                    String email = emailLogin.getText().toString();
                    String password = senhaLogin.getText().toString();


                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailLogin.setError("Por favor coloque um email valido!");
                        emailLogin.requestFocus();
                    }
                    if (password.length() < 6) {
                        senhaLogin.setError("Password maior que 6 digitos porfavor");
                        senhaLogin.requestFocus();
                    }
                   //progressBar.setVisibility(View.VISIBLE);


                    mAutentica.signInWithEmailAndPassword(email, password).addOnCompleteListener(LogEnter.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                //progressBar.setVisibility(View.GONE);
                                Log.d("Tag Login de Usuario", "Login de usuario REALIZADO COM SUCESSO");
                                Intent at = new Intent(LogEnter.this, MainActivity.class);
                                at.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                Toast.makeText(LogEnter.this, "Bem vindo!", Toast.LENGTH_SHORT).show();
                                startActivity(at);
                            } else {
                                Log.d("Tag Login de Usuario", "Login de usuario NAO REALIZADO COM SUCESSO");
                                Toast.makeText(LogEnter.this, "Cadastre-se Novamente!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }


    public void limparCampos(){
        emailLogin.setText("");
        senhaLogin.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAutentica.getCurrentUser();
        //updateUI(currentUser);
    }
}
