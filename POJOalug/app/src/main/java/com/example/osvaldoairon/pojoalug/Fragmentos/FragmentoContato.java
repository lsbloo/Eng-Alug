package com.example.osvaldoairon.pojoalug.Fragmentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.osvaldoairon.pojoalug.R;

/**
 * Created by osvaldoairon on 28/02/18.
 */

public class FragmentoContato extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_contato, container,false);
    }
}
