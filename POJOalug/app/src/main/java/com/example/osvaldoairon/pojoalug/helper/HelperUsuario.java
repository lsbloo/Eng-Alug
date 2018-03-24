package com.example.osvaldoairon.pojoalug.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.osvaldoairon.pojoalug.modeloUsuario.Usuario;

import java.util.ArrayList;

/**
 * Created by osvaldoairon on 22/03/2018.
 */
/*
Criar os metodos inserir , deletar e editar
    pegar o ID do usuario UUIID e deletar do firebase por consulta
    ao mesmo tempo que deleta do SQL;
 */

public class HelperUsuario {

    private SqlUsuario usuarioDB;

    public ArrayList<Usuario> list_usuarios = new ArrayList<Usuario>();

    public HelperUsuario(Context ctx){

        usuarioDB = new SqlUsuario(ctx);
    }


    public long inserir(Usuario usuario){

        SQLiteDatabase db = usuarioDB.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(SqlUsuario.COLUNA_ENDERECO,usuario.getEndereco());
        cv.put(SqlUsuario.COLUNA_INFORMACOES_CASA,usuario.getInformacoesCasa());
        cv.put(SqlUsuario.COLUNA_ID_USUARIO,usuario.getId());
        cv.put(SqlUsuario.COLUNA_TELEFONE,usuario.getTelefone());
        cv.put(SqlUsuario.COLUNA_QNTQUARTOS,usuario.getQuant_quartos());
        cv.put(SqlUsuario.COLUNA_FOTO,usuario.getFotos());


        long id = db.insert(SqlUsuario.NOME_TABELA,null,cv);



            if(id != -1){
                usuario.setId_sql(id);
            }
            db.close();
            Log.v("INSERINDO" , "INSERIDOO");
            return id;

    }


    public void pegarDadosSQL(){

        SQLiteDatabase db = usuarioDB.getWritableDatabase();
        String sql = "SELECT * FROM " + SqlUsuario.NOME_TABELA;
        Cursor cursor = db.rawQuery(sql,null);

        cursor.moveToFirst();

        while(cursor.moveToNext()){
            int indexColunaendereco = cursor.getColumnIndex(SqlUsuario.COLUNA_ENDERECO);
            int indexColunatelefone = cursor.getColumnIndex(SqlUsuario.COLUNA_TELEFONE);
            int indexColunaIdUsuario = cursor.getColumnIndex(SqlUsuario.COLUNA_ID_USUARIO);
            int indexColunaQuartos = cursor.getColumnIndex(SqlUsuario.COLUNA_QNTQUARTOS);
            int indexColunaFotos = cursor.getColumnIndex(SqlUsuario.COLUNA_FOTO);
            int indexColunaInformacoesCasa = cursor.getColumnIndex(SqlUsuario.COLUNA_INFORMACOES_CASA);

            String endereco = cursor.getString(indexColunaendereco);
            String telefone = cursor.getString(indexColunatelefone);
            String idUsuario = cursor.getString(indexColunaIdUsuario);
            String quartos = cursor.getString(indexColunaQuartos);
            String informacao = cursor.getString(indexColunaInformacoesCasa);
            byte[] foto = cursor.getBlob(indexColunaFotos);

            Usuario usuariocreatedb = new Usuario();

            usuariocreatedb.setEndereco(endereco);
            usuariocreatedb.setTelefone(telefone);
            usuariocreatedb.setId(idUsuario);
            usuariocreatedb.setQuant_quartos(Integer.parseInt(quartos));
            usuariocreatedb.setInformacoesCasa(informacao);
            usuariocreatedb.setFotos(foto);


            list_usuarios.add(usuariocreatedb);

        }
        cursor.close();
        db.close();




    }
    public ArrayList<Usuario>getReturnList(){
        return list_usuarios;
    }

    public int getTamanholistUsuario(){
        return list_usuarios.size();
    }

    public int deleteUser(Usuario usuario){
        SQLiteDatabase db = usuarioDB.getWritableDatabase();

        int line = db.delete(SqlUsuario.NOME_TABELA, SqlUsuario.COLUNA_ID_BANCO + " = ?", new String[]{String.valueOf(usuario.getId_sql())});
        db.close();
        return line;
    }
    public void resetTable(){
            SQLiteDatabase db = usuarioDB.getWritableDatabase();
            String sql_del = "DELETE FROM " + SqlUsuario.NOME_TABELA;
            db.execSQL(sql_del);
            db.close();

    }
}

