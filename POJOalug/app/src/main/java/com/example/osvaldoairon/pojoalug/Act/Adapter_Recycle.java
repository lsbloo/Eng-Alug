package com.example.osvaldoairon.pojoalug.Act;

import android.app.Activity;
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

public class Adapter_Recycle extends RecyclerView.Adapter<Adapter_Recycle.ViewHolderUsuario> {

    private Activity ctx;
    private ArrayList<Usuario> mUsuario;
    private int lenDados = 0;

    public Adapter_Recycle(Activity ctx , ArrayList<Usuario> user){
        this.ctx=ctx;
        this.mUsuario=user;

    }

    @Override
    public Adapter_Recycle.ViewHolderUsuario onCreateViewHolder(ViewGroup parent, int viewType) {
        /*
        esse inflate, carrega o xml do anuncio porem nao encarrega o storage do Firebase;
         */
        View v = LayoutInflater.from(ctx).inflate(R.layout.content_fragment_anuncio,parent,false);

        ViewHolderUsuario viewHolderUsuario = new ViewHolderUsuario(v); //Construtor HolderUsuario pega referencia da View;

        return viewHolderUsuario;
    }



    @Override
    public void onBindViewHolder(Adapter_Recycle.ViewHolderUsuario holder, int position) {
        /*
        Pega a posicao do arrayList e carrega no Holder;
         */
        //Toast.makeText(ctx, "TAMANHO MUSUARIO" + mUsuario.size(), Toast.LENGTH_SHORT).show();
        Usuario usuario = mUsuario.get(position);

        holder.txtTelefone.setText("Telefone: " + usuario.getTelefone());
        holder.txtQntQuartos.setText("Quantidade de Quartos: " + String.valueOf(usuario.getQuant_quartos()));
        holder.txtInfoCasa.setText("Informaçoes Adicionais: " +usuario.getInformacoesCasa());
        holder.txtEndereco.setText("Endereço: " + usuario.getEndereco());


    }

    @Override
    public int getItemCount() {
        if(mUsuario.size() != 0){
            lenDados = mUsuario.size();
            return mUsuario.size();
        }
        Log.d("TAMANHO LISTA USER" , "tamanho lista usuario = " +lenDados);
        return 0;
    }

    public class ViewHolderUsuario extends RecyclerView.ViewHolder{

        /*
            Classe responsavel por criar o ViewHolder similar ListView dentro do Recycle View;
         */
        private TextView txtEndereco;
        private TextView txtInfoCasa;
        private TextView txtQntQuartos;
        private TextView txtTelefone;
        private ImageView imgCasa;

        public ViewHolderUsuario(View v) {
            /*
            Carrega a referencia dos dados da view no viewHolder;
             */
            super(v);
            txtEndereco = v.findViewById(R.id.enderecoCasa);
            txtInfoCasa = v.findViewById(R.id.infoCasa);
            txtQntQuartos = v.findViewById(R.id.qntQuartos);
            txtTelefone = v.findViewById(R.id.telefoneCasa);
            imgCasa = v.findViewById(R.id.imgCasa);

        }
    }


}
