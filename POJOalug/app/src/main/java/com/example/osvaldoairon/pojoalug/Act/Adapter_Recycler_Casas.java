package com.example.osvaldoairon.pojoalug.Act;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.osvaldoairon.pojoalug.R;
import com.example.osvaldoairon.pojoalug.modeloUsuario.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 22/03/2018.
 */

public class Adapter_Recycler_Casas extends RecyclerView.Adapter<Adapter_Recycler_Casas.ViewHolderUsuarioCasa> {

    private Context ctx;
    public ArrayList<Usuario> list_usuario;


    public Adapter_Recycler_Casas(Context ctx , ArrayList<Usuario> user){
        this.ctx=ctx;
        this.list_usuario=user;

    }

    @Override
    public Adapter_Recycler_Casas.ViewHolderUsuarioCasa onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(ctx).inflate(R.layout.fragment_contato,parent,false);

        ViewHolderUsuarioCasa holderUsuarioCasa = new ViewHolderUsuarioCasa(v);



        return holderUsuarioCasa;
    }

    @Override
    public void onBindViewHolder(ViewHolderUsuarioCasa holder, int position) {

        Usuario user = list_usuario.get(position);

        byte[] fotoArray;
        Bitmap raw;

        fotoArray = user.getFotos();

        Resources res = ctx.getResources();

        if(fotoArray != null){
            raw = BitmapFactory.decodeByteArray(fotoArray,0,fotoArray.length);
            holder.fotos.setImageBitmap(raw);
        }
        holder.quntQuartos.setText("Quantidade de Quartos: " +user.getQuant_quartos());
        holder.inf_casa.setText("Informações Casa:" +user.getInformacoesCasa());
        holder.telefone.setText("Telefone:" + user.getTelefone());
        holder.endereco.setText("Endereço: " + user.getEndereco());





    }

    @Override
    public int getItemCount() {
        return list_usuario.size();
    }


    public class ViewHolderUsuarioCasa extends RecyclerView.ViewHolder{

        TextView endereco;
        TextView telefone;
        ImageView fotos;
        TextView inf_casa;
        TextView quntQuartos;


        public ViewHolderUsuarioCasa(View itemView) {
            super(itemView);

            endereco=(TextView)itemView.findViewById(R.id.endCasaUser);
            telefone=(TextView)itemView.findViewById(R.id.telefoneCasaUser);
            inf_casa=(TextView)itemView.findViewById(R.id.infoCasaUser);
            quntQuartos=(TextView)itemView.findViewById(R.id.quartosUser);
            fotos=(ImageView)itemView.findViewById(R.id.imgCasaUser);




        }
    }
}
