package com.example.osvaldoairon.pojoalug.Fragmentos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.osvaldoairon.pojoalug.Blind.MaskEditText;
import com.example.osvaldoairon.pojoalug.Comunicador.ComunicadorEvent;
import com.example.osvaldoairon.pojoalug.Manifest;
import com.example.osvaldoairon.pojoalug.R;
import com.example.osvaldoairon.pojoalug.modeloUsuario.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * Created by osvaldoairon on 28/02/18.
 */

public class FragmentoCadastro extends Fragment {

    private ComunicadorEvent comunicadorEvent;
    private EditText edt_telefone, edt_endereco , dados_casa;
    private CheckBox check1,check2,check3,check4 , checkFoto;
    private ImageView imgbtn;
    private static StorageReference mStorageRef;
    private static Uri imagemSelecionada;
    String  fotoStringurl;
    private Button btn_saveCad;

    private static FirebaseAuth autenticacao;
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    private int quantidade_quartos = 0;



    private static final int IMAGEM_ESCOLHIDA = 101;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_casa, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        autenticacao = FirebaseAuth.getInstance();

        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = firebaseDatabase.getInstance();
        //fireBaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();

        imgbtn = (ImageView)getActivity().findViewById(R.id.img);
        edt_telefone = (EditText)getActivity().findViewById(R.id.edt_tel);
        edt_endereco = (EditText)getActivity().findViewById(R.id.edt_end);
        dados_casa = (EditText)getActivity().findViewById(R.id.dados_casa);

        check1 = (CheckBox)getActivity().findViewById(R.id.check1);
        check2 = (CheckBox)getActivity().findViewById(R.id.check2);
        check3 = (CheckBox)getActivity().findViewById(R.id.check3);
        check4 = (CheckBox)getActivity().findViewById(R.id.check4);
        //checkFoto =(CheckBox)getActivity().findViewById(R.id.up_foto);

        btn_saveCad = (Button)getActivity().findViewById(R.id.btn_savecad);
        edt_telefone.addTextChangedListener(MaskEditText.mask(edt_telefone, MaskEditText.FORMAT_FONE));


        imgbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View view) {
                exibirImg();
            }
        });

        btn_saveCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edt_endereco.getText().toString().isEmpty() || edt_telefone.getText().toString().isEmpty() || dados_casa.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Preencha todos os campos !", Toast.LENGTH_SHORT).show();

                }else{

                    if(check1.isChecked()){
                        quantidade_quartos = 1;
                        check1.setChecked(false);
                    }
                    if(check2.isChecked()){
                        quantidade_quartos = 2;
                        check2.setChecked(false);
                    }
                    if(check3.isChecked()){
                        quantidade_quartos = 3;
                        check3.setChecked(false);
                    }
                    if(check4.isChecked()){
                        quantidade_quartos = 4;
                        check4.setChecked(false);
                    }

                    Usuario usuario = new Usuario();
                    usuario.setEndereco(edt_endereco.getText().toString());
                    usuario.setInformacoesCasa(dados_casa.getText().toString());
                    usuario.setTelefone(edt_telefone.getText().toString());
                    usuario.setQuant_quartos(quantidade_quartos);
                    usuario.setId(UUID.randomUUID().toString());

                    databaseReference.child("Casas-Usuario").child(usuario.getId()).setValue(usuario);
                    salvarInformacoesUser();
                }

            }
        });




    }

    private void salvarInformacoesUser() {

        FirebaseUser user = autenticacao.getCurrentUser();

        if(user != null){
            UserProfileChangeRequest profile  = new UserProfileChangeRequest.Builder().setDisplayName("osvaldo").setPhotoUri(imagemSelecionada).build();

            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        subirImagemFirebaseSTORAGE();
                        Toast.makeText(getActivity(), "Upload Phooto", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getActivity(), "Not Upload PHoto!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGEM_ESCOLHIDA && resultCode == RESULT_OK && data.getData() != null) {
            try {
                imagemSelecionada = data.getData();

                //String[] colunas = {MediaStore.Images.Media.DATA};

                //Cursor cursor = getActivity().getContentResolver().query(imagemSelecionada,colunas,null,null,null);

        //            cursor.moveToFirst();

                //int indexColuna = cursor.getColumnIndex(colunas[0]);
                //String path_img = cursor.getString(indexColuna);
                //cursor.close();

                Bitmap bit = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imagemSelecionada);
                Log.d("Tag bit" ,"lol" +bit);


                imgbtn.setImageBitmap(
                        redimensionarImagemBitmap(getActivity(),bit,100,100));



            } catch(Exception e){
                e.printStackTrace();

            }

        }
    }

    private void subirImagemFirebaseSTORAGE() {

        StorageReference profileImagemref = FirebaseStorage.getInstance().getReference("FotosPerfilUsuario/"+System.currentTimeMillis() + "jpg");

        if(imagemSelecionada != null ){
            profileImagemref.putFile(imagemSelecionada).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fotoStringurl = taskSnapshot.getDownloadUrl().toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {


                }
            });
        }

    }

    @Override
    public void onAttach(Context context) {
        /*
        Pega o contexto da Fragment e converte no comunicador
        !
         */

        comunicadorEvent = (ComunicadorEvent)context;
        super.onAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void exibirImg(){
        Intent it = new Intent();
        it.setType("image/*");
        it.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(it , "Casa Imagem Escolhida"),IMAGEM_ESCOLHIDA);
    }

     /*

     public static void verificarPermissao(Activity activity){

        int permissao = ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissao != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSION_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

      */


    private static Bitmap redimensionarImagemBitmap(Context context, Bitmap bmpOriginal,
                                                    float newWidth, float newWeight) {
        Bitmap novoBmp = null;
        int w = bmpOriginal.getWidth();
        int h = bmpOriginal.getHeight();
        float densityFactor = context.getResources().getDisplayMetrics().density;
        float novoW = newWidth * densityFactor;
        float novoH = newWeight * densityFactor;
        float scalaW = novoW / w;
        float scalaH = novoH / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scalaW, scalaH);
        novoBmp = Bitmap.createBitmap(bmpOriginal, 0, 0, w, h, matrix, true);
        return novoBmp;
    }

    /*
    Metodo de transformacao de imagens da API PICASSO ;z
     */
    public class CropSquareTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override public String key() { return "square()"; }

    }
}
