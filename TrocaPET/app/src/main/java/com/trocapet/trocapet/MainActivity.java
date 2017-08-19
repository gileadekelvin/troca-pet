package com.trocapet.trocapet;

import android.app.ListActivity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    String[] itemname ={
            "Impressora Preto e Branco",
            "Celular Moto G1",
            "Headphone Samsung",
            "Monitor 21' AOC",
            "Coca-Cola 2L",
            "Pen-drive Kingston 16GB",
            "Mouse Sem Fio Multilaser",
            "HD Externo 500 GB Seagate"
    };

    Integer[] imgDosBrindes ={
            R.drawable.ic_mapa,
            R.drawable.ic_android,
            R.drawable.ic_arrow_back,
            R.drawable.ic_dashboard_black_24dp,
            R.drawable.ic_assignment,
            R.drawable.ic_perfil,
            R.drawable.ic_backup,
            R.drawable.ic_autorenew,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setUpListViewItens();

        setUpNavigation();

    }

    private void setUpListViewItens() {
        ListView list = (ListView)findViewById(android.R.id.list);
        CustomAdapter adapter = new CustomAdapter(this, itemname, imgDosBrindes);
        list.setAdapter(adapter);
    }

    private void setUpNavigation() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.ic_arrow);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:

                        break;

                    case R.id.navigation_perfil:
                        Intent intent1 = new Intent(MainActivity.this, ActivityOne.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_mapa:
                        Intent intent2 = new Intent(MainActivity.this, MapsActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_qrcode:
                        Intent intent3 = new Intent(MainActivity.this, QRCodeActivity.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }


}
