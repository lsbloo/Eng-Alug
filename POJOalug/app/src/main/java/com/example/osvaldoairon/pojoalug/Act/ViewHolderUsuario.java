package com.example.osvaldoairon.pojoalug.Act;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.osvaldoairon.pojoalug.R;

/**
 * Created by osvaldoairon on 15/03/18.
 */

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
