package com.example.osvaldoairon.pojoalug.Fragmentos;

import com.example.osvaldoairon.*;
import com.example.osvaldoairon.pojoalug.Comunicador.ComunicadorEvent;
import com.example.osvaldoairon.pojoalug.R;
import com.example.osvaldoairon.pojoalug.modeloUsuario.Usuario;
import com.example.osvaldoairon.pojoalug.modeloUsuario.UsuarioAdaptado;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;
import java.util.ArrayList;
/**
 * Created by osvaldoairon on 28/02/18.
 */

public class FragmentoAnucio extends Fragment {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private static ArrayList<Usuario> list_user;
    private Usuario user;
    private ImageView test;
    ComunicadorEvent comunicadorEvent;
    private static UsuarioAdaptado userAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_anucio, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list_user = new ArrayList<Usuario>();

        init_firebase();
        carregarDados();
        exibir_view();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        comunicadorEvent = (ComunicadorEvent)context;
    }
    private void carregarDados(){

        // Read from the database
        try{

            databaseReference.child("Casas-Usuario").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                Download dados Database do DatabaseReference
                 */
                list_user.clear();
                    for(DataSnapshot obj:dataSnapshot.getChildren()){

                        Usuario usuario = (Usuario) obj.getValue(Usuario.class);
                        list_user.add(usuario);
                    }
                    /*
                    O dado do snapshot ja esta retornando e sendo adicionado no array
                    e preciso carregar o mesmo na view e agrupar o download da foto no storage
                    de forma sincronizada;
                     */
                    //Toast.makeText(getActivity(), "jahaush: "+list_user.size(), Toast.LENGTH_SHORT).show();

                    //                String value = dataSnapshot.getValue(String.class);
                    //              Log.d(TAG, "Value is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });


        }catch(ExceptionInInitializerError inInitializerError){
            inInitializerError.printStackTrace();

        }

        Log.d("ARRAY USUARIO", "ARRAY USUARIO"+list_user.size());

    }

    private void exibir_view(){
        userAdapter = new UsuarioAdaptado(getActivity(),list_user);
        //setListAdapter(userAdapter);
        ListView list = new ListView(getActivity());
        list.setAdapter(userAdapter);

    }

    private void init_firebase(){
        FirebaseApp.initializeApp(getActivity());
        database = database.getInstance();
        //fireBaseDatabase.setPersistenceEnabled(true);
        databaseReference = database.getReference();
    }
}
