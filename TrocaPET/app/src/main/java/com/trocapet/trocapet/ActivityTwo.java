package com.trocapet.trocapet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by User on 4/15/2017.
 */

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        TextView title = (TextView) findViewById(R.id.activityTitle2);
        title.setText("This is ActivityTwo");

        setUpNavigation();
    }

    private void setUpNavigation() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_mapa);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:
                        iniciarActivity(MainActivity.class);
                        break;

                    case R.id.navigation_perfil:
                        iniciarActivity(ActivityOne.class);
                        break;

                    case R.id.navigation_mapa:
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
        Intent intent = new Intent(ActivityTwo.this, activityQueVaiSerIniciada);

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        startActivity(intent);
    }

}
