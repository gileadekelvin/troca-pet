package com.trocapet.trocapet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by User on 4/15/2017.
 */


public class ActivityOne extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        TextView title = (TextView) findViewById(R.id.activityTitle1);
        title.setText("Perfil");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_perfil);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:
                        Intent intent0 = new Intent(ActivityOne.this, MainActivity.class);
                        intent0.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent0);
                        break;

                    case R.id.navigation_perfil:

                        break;

                    case R.id.navigation_mapa:
                        Intent intent2 = new Intent(ActivityOne.this, MapsActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_qrcode:
                        Intent intent3 = new Intent(ActivityOne.this, ActivityThree.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent3);
                        break;

                }


                return false;
            }
        });
    }
}
