package com.example.osvaldoairon.pojoalug.Fragmentos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.osvaldoairon.pojoalug.Blind.MaskEditText;
import com.example.osvaldoairon.pojoalug.Comunicador.ComunicadorEvent;
import com.example.osvaldoairon.pojoalug.R;

/**
 * Created by osvaldoairon on 28/02/18.
 */

public class FragmentoCadastro extends Fragment {

    private ComunicadorEvent comunicadorEvent;
    private EditText edt_telefone;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_casa, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        edt_telefone = (EditText)getActivity().findViewById(R.id.edt_tel);


        edt_telefone.addTextChangedListener(MaskEditText.mask(edt_telefone, MaskEditText.FORMAT_FONE));
    }

    @Override
    public void onAttach(Context context) {
        /*
        Pega o contexto da Fragment e converte no comunicador
        !
         */

        comunicadorEvent = (ComunicadorEvent)context;
        super.onAttach(context);
    }
}
