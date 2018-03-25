package com.example.osvaldoairon.pojoalug.Comunicador;

/**
 * Created by osvaldoairon on 03/03/2018.
 */

/*
    Interface que faz a comunicação das Fragments(Cadastro,Anucio,CasasCadastradas)
    para a activity Main. !
    Joga os dados na main Activity

 */
public interface ComunicadorEvent {

    public void comunicadorFragment(String dado);
}
