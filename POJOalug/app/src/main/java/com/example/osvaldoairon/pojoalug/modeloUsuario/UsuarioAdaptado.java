package com.example.osvaldoairon.pojoalug.modeloUsuario;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.util.zip.Inflater;

/**
 * Created by osvaldoairon on 07/02/18.
 */

/*
    Classe UsuarioAdaptado criar o layout dos  dados de usuario no fragmentAnuncio
*/

public class UsuarioAdaptado extends BaseAdapter{

    private Context ctx;
    private ArrayList<Usuario> list_usuarios = new ArrayList<Usuario>();

    public UsuarioAdaptado(Context context , ArrayList<Usuario> arrayList) {
        super();
        ctx=context;
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
            view = LayoutInflater.from(ctx).inflate(R.layout.fragment_listiewcasa,viewGroup,false);
        }

        TextView endereco;
        TextView telefone;
        ImageView fotos;
        TextView inf_casa;
        TextView quntQuartos;

        //View saida = LayoutInflater.from(ctx).inflate(R.layout.fragment_anucio,viewGroup,false);

        endereco=(TextView)view.findViewById(R.id.endCasaUser);
        telefone=(TextView)view.findViewById(R.id.telefoneCasaUser);
        inf_casa=(TextView)view.findViewById(R.id.infoCasaUser);
        quntQuartos=(TextView)view.findViewById(R.id.quartosUser);
        fotos=(ImageView)view.findViewById(R.id.imgCasaUser);

        byte[] fotoArray;
        Bitmap raw;

        fotoArray = usuario.getFotos();

        Resources res = ctx.getResources();

        if(fotoArray != null){
            raw = BitmapFactory.decodeByteArray(fotoArray,0,fotoArray.length);
            fotos.setImageBitmap(raw);
        }
        quntQuartos.setText("Quantidade de Quartos: " +usuario.getQuant_quartos());
        inf_casa.setText("Informações Casa:" +usuario.getInformacoesCasa());
        telefone.setText("Telefone:" + usuario.getTelefone());
        endereco.setText("Endereço: " + usuario.getEndereco());

        return view;


    }
}
