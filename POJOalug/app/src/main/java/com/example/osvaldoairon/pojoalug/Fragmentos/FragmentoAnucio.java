package com.example.osvaldoairon.pojoalug.Fragmentos;

import com.example.osvaldoairon.*;
import com.example.osvaldoairon.pojoalug.R;
import com.example.osvaldoairon.pojoalug.modeloUsuario.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.ContentValues.TAG;

/**
 * Created by osvaldoairon on 28/02/18.
 */

public class FragmentoAnucio extends Fragment {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_anucio, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FirebaseApp.initializeApp(getActivity());
        database = database.getInstance();
        //fireBaseDatabase.setPersistenceEnabled(true);
        databaseReference = database.getReference();



        // Read from the database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                Download dados do DatabaseReference
                 */
                
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

}
