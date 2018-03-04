package com.example.osvaldoairon.pojoalug.modeloUsuario;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by osvaldoairon on 07/02/18.
 */

public class Usuario implements Serializable{

    private String endereco;
    private String telefone;



    private UUID id;


    public void setId(UUID id){
        this.id=id;
    }
    public UUID getId(){
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
    public Usuario(){}

    public Usuario(String endereco, String telefone ){

        setEndereco(endereco);
        setTelefone(telefone);

    }
}
