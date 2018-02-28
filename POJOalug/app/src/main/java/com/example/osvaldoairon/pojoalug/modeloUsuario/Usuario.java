package com.example.osvaldoairon.pojoalug.modeloUsuario;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by osvaldoairon on 07/02/18.
 */

public class Usuario implements Serializable{
    private String nome;
    private String endereco;
    private String telefone;
    private String login;
    private String senha;
    private String email;

    private UUID id;


    public void setId(UUID id){
        this.id=id;
    }
    public UUID getId(){
        return id;
    }

    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    /*
    Padrao para usar o SNap shot < construtor sem argumentos \/
     */
    public Usuario(){}

    public Usuario(String nome ,String endereco, String telefone , String login , String senha , String email){
        setNome(nome);
        setEndereco(endereco);
        setTelefone(telefone);
        setLogin(login);
        setSenha(senha);
        setEmail(email);
    }
}
