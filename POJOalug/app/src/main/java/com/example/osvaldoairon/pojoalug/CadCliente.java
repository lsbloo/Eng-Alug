package com.example.osvaldoairon.pojoalug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osvaldoairon.pojoalug.Blind.MaskEditText;
import com.example.osvaldoairon.pojoalug.modeloUsuario.Usuario;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.UUID;
import com.example.osvaldoairon.pojoalug.DAO.FirebaseInit;


public class CadCliente extends AppCompatActivity {

    private static FirebaseInit firebaseInit;
    private Button btn_cadastar;
    private static EditText edt_nome;
    private static EditText edt_endereco;
    private static EditText edt_telefone;
    private static EditText edt_login;
    private static EditText edt_senha;
    private static EditText edt_conf_senha;
    private static EditText edt_email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cliente);

        firebaseInit = new FirebaseInit(CadCliente.this);

        edt_nome = (EditText) findViewById(R.id.edt_nome);
        edt_endereco = (EditText) findViewById(R.id.edt_end);
        edt_telefone = (EditText) findViewById(R.id.edt_tel);
        edt_login = (EditText) findViewById(R.id.edt_log);
        edt_senha = (EditText) findViewById(R.id.edt_senha);
        edt_conf_senha = (EditText)findViewById(R.id.edt_confSenha);
        edt_email = (EditText)findViewById(R.id.edt_email);

        btn_cadastar = (Button) findViewById(R.id.btn_cadastrar);


        // Mascara no EditTExt de login , telefone e senha (Limitar entrada do usuario!);

        edt_login.addTextChangedListener(MaskEditText.mask(edt_login, MaskEditText.FORMAT_LOGIN));
        edt_telefone.addTextChangedListener(MaskEditText.mask(edt_telefone, MaskEditText.FORMAT_FONE));
        edt_senha.addTextChangedListener(MaskEditText.mask(edt_senha, MaskEditText.FORMAT_PASS));


    }

    public boolean limparCampos() {
        edt_senha.setText("");
        edt_nome.setText("");
        edt_endereco.setText("");
        edt_login.setText("");
        edt_telefone.setText("");
        edt_conf_senha.setText("");
        edt_email.setText("");
        return true;

    }
    public boolean limparPass(){
        edt_conf_senha.setText("");
        edt_senha.setText("");
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.salvar) {

            try {

                if (edt_nome.getText().toString().isEmpty() || edt_endereco.getText().toString().isEmpty() || edt_telefone.getText().toString().isEmpty() || edt_login.getText().toString().isEmpty() || edt_senha.getText().toString().isEmpty()) {
                    Toast.makeText(CadCliente.this, "Preencha todos os campos !", Toast.LENGTH_SHORT).show();
                }
                 else
                 {


                     if(edt_conf_senha.getText().toString().equals(edt_senha.getText().toString())){

                         /*
                    Criar Objeto user e cadastrar no Firebase;
                    <- Database;
                     */
                         Usuario user = new Usuario();
                         user.setNome(edt_nome.getText().toString());
                         user.setEndereco(edt_endereco.getText().toString());
                         user.setTelefone(edt_telefone.getText().toString());
                         user.setLogin(edt_login.getText().toString());
                         user.setSenha(edt_senha.getText().toString());
                         user.setEmail(edt_email.getText().toString());
                         user.setId(UUID.randomUUID());

                    /*
                    Adicionando informaçeõs (cadastro) dos Usuarios no database;
                     */
                         firebaseInit.getDatabaseReference().child("Usuarios").child(String.valueOf(user.getId())).setValue(user);


                         Toast.makeText(CadCliente.this, "Cadastrado!..", Toast.LENGTH_SHORT).show();
                         limparCampos();

                    /*
                    Redirecionar para tela de logs !
                     */
                         // Intent at = new !

                         Intent at = new Intent(CadCliente.this, LoginAct.class);
                         startActivity(at);
                     }
                     else{
                         Toast.makeText(CadCliente.this, "Senhas Diferentes.", Toast.LENGTH_SHORT).show();
                         limparPass();
                     }


                }
            }catch(Exception e) {
                    e.printStackTrace();
            }
            }
        return super.onOptionsItemSelected(item);
        }

    }
