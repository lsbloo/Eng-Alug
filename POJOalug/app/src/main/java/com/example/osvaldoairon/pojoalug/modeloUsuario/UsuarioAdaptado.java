package com.example.osvaldoairon.pojoalug.modeloUsuario;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.osvaldoairon.pojoalug.Fragmentos.FragmentoAnucio;
import com.example.osvaldoairon.pojoalug.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by osvaldoairon on 07/02/18.
 */

/*
    Classe UsuarioAdaptado criar o layout dos  dados de usuario no fragmentAnuncio
*/

public class UsuarioAdaptado extends BaseAdapter{

    private Context ctx;
    private ArrayList<Usuario> list_usuarios = new ArrayList<Usuario>();

    public UsuarioAdaptado(Context ctx , ArrayList<Usuario> arrayList) {
        super();
        ctx=ctx;
        list_usuarios=arrayList;
    }

    @Override
    public int getCount() {
        return list_usuarios.size();
    }

    @Override
    public Object getItem(int i) {
        return list_usuarios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Usuario usuario = list_usuarios.get(position); // pega a posicao do item array especificado

        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater)ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.fragment_anucio, null);
        }

        //View saida = LayoutInflater.from(ctx).inflate(R.layout.fragment_anucio,viewGroup,false);

        ImageView imgCasa =(ImageView)view.findViewById(R.id.imgCasa);
        TextView  endCasa = (TextView)view.findViewById(R.id.enderecoCasa);
        TextView  infCasa = (TextView)view.findViewById(R.id.infoCasa);
        TextView qntQuartos = (TextView)view.findViewById(R.id.qntQuartos);
        TextView tel = (TextView)view.findViewById(R.id.telefoneCasa);


        return view;


    }
}
