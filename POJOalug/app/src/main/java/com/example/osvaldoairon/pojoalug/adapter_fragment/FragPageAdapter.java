package com.example.osvaldoairon.pojoalug.adapter_fragment;

import android.support.v4.app.Fragment;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.example.osvaldoairon.pojoalug.*;
import com.example.osvaldoairon.pojoalug.Fragmentos.FragmentoAnucio;
import com.example.osvaldoairon.pojoalug.Fragmentos.FragmentoCadastro;
import com.example.osvaldoairon.pojoalug.Fragmentos.FragmentoContato;
import android.support.fragment.*;


/**
 * Created by osvaldoairon on 28/02/18.
 */

public class FragPageAdapter extends FragmentStatePagerAdapter {

    private String[] tabTitulos;


    public FragPageAdapter(FragmentManager fm, String[] tabTitulos) {
        super(fm);
        this.tabTitulos=tabTitulos;

    }


    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new FragmentoAnucio();

            case 1:
                return new FragmentoCadastro();

            case 2:
                return new FragmentoContato();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabTitulos.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabTitulos[position];
    }

}
