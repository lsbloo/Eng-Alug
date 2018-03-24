package com.example.osvaldoairon.pojoalug.Fragmentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.osvaldoairon.pojoalug.Act.Adapter_Recycler_Casas;
import com.example.osvaldoairon.pojoalug.R;
import com.example.osvaldoairon.pojoalug.helper.HelperUsuario;
import com.example.osvaldoairon.pojoalug.modeloUsuario.Usuario;
import com.example.osvaldoairon.pojoalug.modeloUsuario.UsuarioAdaptado;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

/**
 * Created by osvaldoairon on 28/02/18.
 */

public class FragmentoCasasCadastradas extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private static HelperUsuario helperUsuario;
    private static Adapter_Recycler_Casas adapter_recycler_casas;
    private static ArrayList<Usuario> listuser_db;
    private RecyclerView recyclerView;
    private static View view;
    private static ListView list;
    private static UsuarioAdaptado adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        helperUsuario = new HelperUsuario(getActivity());
        helperUsuario.pegarDadosSQL();

        listuser_db = new ArrayList<Usuario>();
        //listView=(ListView)getActivity().findViewById(R.id.listviewCasa);
        //view = inflater.inflate(R.layout.content_fragmento_contato, container,false);
        //loadLayout_Cas(view);

        adapter = new UsuarioAdaptado(getActivity(),helperUsuario.getReturnList());
        list = new ListView(getActivity());
        list.setAdapter(adapter);
        deletarCasas(list);
        return list;

    }
    public void deletarCasas(final ListView listview){
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /*
                Deletar Casas do Sql como tambem Firebase;
                 */
                Usuario usuarioPosicaoArray = helperUsuario.getPosition(position);
                deletarFirebase(usuarioPosicaoArray.getId());
                deletarSQL(usuarioPosicaoArray);
                helperUsuario.list_usuarios.remove(usuarioPosicaoArray);
                listview.setAdapter(adapter);
            }
        });
    }

    public void resetSQL(){
        helperUsuario.resetTable();
    }

    public void deletarFirebase(String id){
        Log.v("ID_TESTE", "ID_TESTE" + id);
        FirebaseApp.initializeApp(getActivity());
        database = database.getInstance();
        databaseReference = database.getReference("Casas-Usuario").child(id);
        databaseReference.removeValue();
    }
    public void deletarSQL(Usuario usuario){
        helperUsuario.deleteUser(usuario);
    }

    public void loadLayout_Cas(View view){
        /*
        CRIEI UM RECYCLER VIEW PARA O FRAGMENTO DE SUAS CASAS PORÉM , NÃO É VIAVEL UMA VEZ QUE É NECESSARIO
        QUE O USUARIO CLICE NOS "ITENS" PARA PODER REALIZAR ALGUMAS OPERACOES E COMO O MESMO FRAGMENT
        CORRESPONDE A POUCOS DADOS CRIADOS É MAIS VIAVEL USAR LISTVIEW PARA TRABALHAR COM O INDICE
        DAS POSIÇÕES - CLASSE - ADAPTER_RECYCLER_CASAS, FRAGMENT_CONTATO, CONTENT_FRAGMENT_CONTATO.. NAO USADAS;

         */

        recyclerView=view.findViewById(R.id.teste2);

        Log.d("XEDXDXDXD" , "XDXDXDXD" +helperUsuario.getTamanholistUsuario());
        adapter_recycler_casas = new Adapter_Recycler_Casas(getActivity(),helperUsuario.getReturnList());
        Log.v("AKII_CONTATO"," AQUI_CONTATO: "+adapter_recycler_casas.getItemCount());


        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);


        recyclerView.setAdapter(adapter_recycler_casas);
        recyclerView.setLayoutManager(layout);
    }

}
