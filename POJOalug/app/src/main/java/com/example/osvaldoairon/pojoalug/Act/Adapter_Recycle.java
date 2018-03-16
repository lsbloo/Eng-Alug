package com.example.osvaldoairon.pojoalug.Act;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.osvaldoairon.pojoalug.R;
import com.example.osvaldoairon.pojoalug.modeloUsuario.Usuario;
import com.example.osvaldoairon.pojoalug.Act.ViewHolderUsuario;


import java.util.ArrayList;

public class Adapter_Recycle extends RecyclerView.Adapter {

    private Context ctx;
    private ArrayList<Usuario> mUsuario;
    private int lenDados = 0;

    public Adapter_Recycle(Context ctx , ArrayList<Usuario> user){
        this.ctx=ctx;
        this.mUsuario=user;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*
        esse inflate, carrega o xml do anuncio porem nao encarrega o storage do Firebase;
         */
        View v = LayoutInflater.from(ctx).inflate(R.layout.fragment_anucio,parent,false);

        ViewHolderUsuario viewHolderUsuario = new ViewHolderUsuario(v); //Construtor HolderUsuario pega referencia da View;

        return  viewHolderUsuario;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*
        Pega a posicao do arrayList e carrega no Holder;
         */
        mUsuario.get(position);




    }

    @Override
    public int getItemCount() {
        if(mUsuario != null){
            lenDados = mUsuario.size();
            return mUsuario.size();
        }
        Log.d("TAMANHO LISTA USER" , "tamanho lista usuario = " +lenDados);
        return 0;
    }



}
