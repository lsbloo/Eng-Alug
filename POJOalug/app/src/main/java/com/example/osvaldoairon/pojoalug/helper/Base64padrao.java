package com.example.osvaldoairon.pojoalug.helper;
import android.util.Base64;
/**
 * Created by osvaldoairon on 27/02/18.
 */

public class Base64padrao {

    public static String codificarBase64 (String texto){
        return android.util.Base64.encodeToString(texto.getBytes(), Base64.DEFAULT

        );
    }
    public static String decodificarBase64(String textoCodificado){
        return new String (Base64.decode(textoCodificado, Base64.DEFAULT));

    }
}
