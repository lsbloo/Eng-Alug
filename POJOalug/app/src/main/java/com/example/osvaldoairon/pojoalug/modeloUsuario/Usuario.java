package com.example.osvaldoairon.pojoalug.modeloUsuario;

import android.support.v4.app.FragmentActivity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by osvaldoairon on 07/02/18.
 */

public class Usuario implements Serializable{

    private String endereco;
    private String telefone;
    private int quant_quartos;
    private String informacoesCasa;
    private String id;
    private Object context;



    public String getInformacoesCasa() {
        return informacoesCasa;
    }

    public int getQuant_quartos() {
        return quant_quartos;
    }

    public void setInformacoesCasa(String informacoesCasa) {
        this.informacoesCasa = informacoesCasa;
    }

    public void setQuant_quartos(int quant_quartos) {
        this.quant_quartos = quant_quartos;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return id;
    }


    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }



    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }



    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /*
    Padrao para usar o SNap shot < construtor sem argumentos \/
     */
    public Usuario(){

    }

    /*
    public Usuario(String endereco, String telefone ){

        setEndereco(endereco);
        setTelefone(telefone);

    }
     */

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.context = null;
    }
}
