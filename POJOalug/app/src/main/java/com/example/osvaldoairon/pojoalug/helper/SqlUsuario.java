package com.example.osvaldoairon.pojoalug.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by osvaldoairon on 22/03/2018.
 */

public class SqlUsuario extends SQLiteOpenHelper {

    private static String NOME_BANCO = "CasaUsuarioSQL";
    private static int VERSAO_BANCO = 2;

    public static final String NOME_TABELA = "CasasUsuariosSQL";
    public static final String COLUNA_ID_BANCO = "_id";
    public static final String COLUNA_ID_USUARIO = "_idUsuario";
    public static final String COLUNA_ENDERECO = "endereco";
    public static final String COLUNA_TELEFONE = "telefone";
    public static final String COLUNA_QNTQUARTOS = "quartos";
    public static final String COLUNA_INFORMACOES_CASA = "informacoesCasa";
    public static final String COLUNA_FOTO = "fotos";


    public SqlUsuario(Context context) {
        super(context,NOME_BANCO,null,VERSAO_BANCO);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + NOME_TABELA + " (  " + COLUNA_ID_BANCO + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUNA_ID_USUARIO + " TEXT , " + COLUNA_ENDERECO + " TEXT , "
        + COLUNA_TELEFONE + " TEXT , " + COLUNA_QNTQUARTOS + " TEXT , " + COLUNA_INFORMACOES_CASA + " TEXT , " + COLUNA_FOTO   + " blob )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
