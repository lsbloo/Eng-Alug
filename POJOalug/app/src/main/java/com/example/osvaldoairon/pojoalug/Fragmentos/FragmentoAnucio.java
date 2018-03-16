package com.example.osvaldoairon.pojoalug.Fragmentos;

import com.example.osvaldoairon.*;
import com.example.osvaldoairon.pojoalug.Act.Adapter_Recycle;
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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Created by osvaldoairon on 28/02/18.
 */

public class FragmentoAnucio extends Fragment {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    public static ArrayList<Usuario> list_user;
    private static ArrayList<Usuario> lista_usuarios;
    private Usuario user;
    private ImageView test;
    ComunicadorEvent comunicadorEvent;
    private static UsuarioAdaptado userAdapter;
    private static Adapter_Recycle userAdapterRecycler;
    private static RecyclerView recyclerView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anucio, container, false);
        exibir_view(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lista_usuarios = new ArrayList<Usuario>();
        list_user = new ArrayList<Usuario>();
        init_firebase();
        carregarDados();

        //userAdapter = new UsuarioAdaptado(getActivity(),list_user);
        //setListAdapter(userAdapter);
        //ListView list = new ListView(getActivity());
        //list.setAdapter(userAdapter);
        //this.getActivity().setContentView(list);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        comunicadorEvent = (ComunicadorEvent)context;
    }
    public void carregarDados(){

        // Read from the database

            databaseReference.child("Casas-Usuario").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                Download dados Database do DatabaseReference
                 */
                    for(DataSnapshot obj:dataSnapshot.getChildren()){

                        Usuario usuario = (Usuario) obj.getValue(Usuario.class);
                        list_user.add(usuario);
                    }
                    Toast.makeText(getActivity(), "jahaush: "+list_user.size(), Toast.LENGTH_SHORT).show();
                    /*
                    o arraylist esta chegando vazio em outras partes do codigo;
                    tentei usar o set e o get pra redirecionar
                    criei outro array auxiliar fora do snapsht
                    --------------------------------------------
                    nada resolvido;
                    
                     */
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        Log.d("ARRAY USUARIO", "ARRAY USUARIO"+list_user.size());

    }

    private void exibir_view(View v){
        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        //setListAdapter(userAdapter);
        //ListView list = new ListView(getActivity());
        //list.setAdapter(userAdapter);

        recyclerView=v.findViewById(R.id.teste);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(mLayoutManager);
        userAdapterRecycler = new Adapter_Recycle(getActivity(),list_user);
        Log.d("AKSS", "kapskapsk" +lista_usuarios.size());
        Log.d("AKIII"," AQUI: "+userAdapterRecycler.getItemCount());
        recyclerView.setAdapter(userAdapterRecycler);



    }

    private void init_firebase(){
        FirebaseApp.initializeApp(getActivity());
        database = database.getInstance();
        //fireBaseDatabase.setPersistenceEnabled(true);
        databaseReference = database.getReference();
    }


}
