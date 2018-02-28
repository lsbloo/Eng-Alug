package com.example.osvaldoairon.pojoalug;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.osvaldoairon.pojoalug.adapter_fragment.FragPageAdapter;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));


        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.view_pager);

        viewPager.setAdapter(new FragPageAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.secoes)));

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_act_,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        try{

            if (id == R.id.search){
            /*
            Ação da barra de busca
             */
                Toast.makeText(this, "Ação da barra de busca!", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Log.d("NAO ENCONTRADO", "ID DE BUSCA NAO ENCONTRADO");
            e.printStackTrace();
        }

        return super.onOptionsItemSelected(item);
    }
}
