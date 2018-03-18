package com.example.osvaldoairon.pojoalug.helper;

import android.content.Context;
import android.widget.ImageView;

import com.example.osvaldoairon.pojoalug.R;
import com.squareup.picasso.Picasso;

/**
 * Created by osvaldoairon on 18/03/18.
 */

public class PicassoCliente {

    public static void downloading(Context c , String url , ImageView view){

        if(url != null && url.length() > 0 ){
            Picasso.with(c).load(url).placeholder(R.drawable.imgcasapadrao).into(view);
        }
        else{
            Picasso.with(c).load(R.drawable.imgcasapadrao).into(view);
        }
    }
}
