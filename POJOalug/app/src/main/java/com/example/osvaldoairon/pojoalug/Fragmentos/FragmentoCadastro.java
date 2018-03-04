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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.osvaldoairon.pojoalug.Blind.MaskEditText;
import com.example.osvaldoairon.pojoalug.Comunicador.ComunicadorEvent;
import com.example.osvaldoairon.pojoalug.Manifest;
import com.example.osvaldoairon.pojoalug.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import static android.app.Activity.RESULT_OK;

/**
 * Created by osvaldoairon on 28/02/18.
 */

public class FragmentoCadastro extends Fragment {

    private ComunicadorEvent comunicadorEvent;
    private EditText edt_telefone, edt_endereco , dados_casa;
    private CheckBox check1,check2,check3,check4 , checkFoto;
    private ImageView imgbtn;




    private static final int IMAGEM_ESCOLHIDA = 101;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_casa, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        imgbtn = (ImageView)getActivity().findViewById(R.id.img);
        edt_telefone = (EditText)getActivity().findViewById(R.id.edt_tel);
        edt_endereco = (EditText)getActivity().findViewById(R.id.edt_end);
        dados_casa = (EditText)getActivity().findViewById(R.id.dados_casa);

        check1 = (CheckBox)getActivity().findViewById(R.id.check1);
        check2 = (CheckBox)getActivity().findViewById(R.id.check2);
        check3 = (CheckBox)getActivity().findViewById(R.id.check3);
        check4 = (CheckBox)getActivity().findViewById(R.id.check4);
        checkFoto =(CheckBox)getActivity().findViewById(R.id.up_foto);

        edt_telefone.addTextChangedListener(MaskEditText.mask(edt_telefone, MaskEditText.FORMAT_FONE));


        checkFoto.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View view) {
                exibirImg();

                if(checkFoto.isChecked()){
                    checkFoto.setChecked(false);
                }
            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGEM_ESCOLHIDA && resultCode == RESULT_OK && data.getData() != null) {
            try {
                Uri imagemSelecionada = data.getData();

                //String[] colunas = {MediaStore.Images.Media.DATA};

                //Cursor cursor = getActivity().getContentResolver().query(imagemSelecionada,colunas,null,null,null);

        //            cursor.moveToFirst();

                //int indexColuna = cursor.getColumnIndex(colunas[0]);
                //String path_img = cursor.getString(indexColuna);
                //cursor.close();

                Bitmap bit = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imagemSelecionada);
                Log.d("Tag bit" ,"lol" +bit);



                /*
                Usar picasso para carregamento de imagens

                    Picasso.with(context).load(R.drawable.landing_screen).into(imageView1);
                    Picasso.with(context).load("file:///android_asset/DvpvklR.png").into(imageView2);
                    Picasso.with(context).load(new File(...)).into(imageView3);

                 */
                imgbtn.setImageBitmap(
                        redimensionarImagemBitmap(getActivity(),bit,120,120));



            } catch(Exception e){
                e.printStackTrace();

            }

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
