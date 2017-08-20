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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setUpListViewItens();

        setUpNavigation();

    }

    private void setUpListViewItens() {
        ListView list = (ListView)findViewById(android.R.id.list);
        CustomAdapter adapter = CustomAdapter.createAdapter(this);
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
                        iniciarActivity(ActivityOne.class);
                        break;

                    case R.id.navigation_mapa:
                        iniciarActivity(MapsActivity.class);
                        break;

                    case R.id.navigation_qrcode:
                        iniciarActivity(QRCodeActivity.class);
                        break;
                }
                return false;
            }
        });
    }

    private void iniciarActivity(final Class<?> activityQueVaiSerIniciada) {
        Intent intent = new Intent(MainActivity.this, activityQueVaiSerIniciada);

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        startActivity(intent);
    }

}
