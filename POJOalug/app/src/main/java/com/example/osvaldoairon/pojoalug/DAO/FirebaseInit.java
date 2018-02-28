package com.example.osvaldoairon.pojoalug.DAO;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by osvaldoairon on 28/02/18.
 */

public class FirebaseInit {

    public FirebaseDatabase fireBaseDatabase;
    public DatabaseReference databaseReference;
    private Context ctx;

    public FirebaseInit(Context ctx){
        this.ctx=ctx;
        FirebaseApp.initializeApp(ctx);
        fireBaseDatabase = fireBaseDatabase.getInstance();
        //fireBaseDatabase.setPersistenceEnabled(true);
        databaseReference = fireBaseDatabase.getReference();
    }

    public DatabaseReference getDatabaseReference(){
        return databaseReference;
    }
    public void referenceDatabase(){

    }

}
