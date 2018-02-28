package com.example.osvaldoairon.pojoalug.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by osvaldoairon on 27/02/18.
 */

public class Preference {

    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQ = "pojoalug.preferences";
    private int MODER = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_INDENTIFICADOR = "identificarUsuarioLogado";
    private final String CHAVE_USUARIO = "nomeUsuarioLogado";


    public Preference(Context context){
        this.context=context;
        preferences = context.getSharedPreferences(NOME_ARQ, MODER);

        editor = preferences.edit();

    }
    public void salvarUsuariopreference(String indenficadorUsuario , String nomeUsuario){
        editor.putString(CHAVE_INDENTIFICADOR,indenficadorUsuario);
        editor.putString(CHAVE_USUARIO,nomeUsuario);
        editor.commit();
    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_INDENTIFICADOR,null);
    }

    public String getNome(){
        return preferences.getString(CHAVE_USUARIO,null);
    }


}
