package com.trocapet.trocapet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by User on 4/15/2017.
 */


public class ActivityOne extends AppCompatActivity {

    private String usuario = "Gilekel";

    private int pontos;

    private final String msgInformandoPontuacao = "Você possui: " + pontos + " ecopoints";

    @Override
    protected void onResume(){
        super.onResume();
        // atualiza pontuação com a activity em resumo (em segundo plano)
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        pontos =  prefs.getInt("POINTS", 0);
        setTextFor(R.id.ecopoints, msgInformandoPontuacao);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_one);

        setProfilePicture(R.drawable.gilekel);

        setUpTextViews();

        setUpNavigation();
    }

    private void setUpNavigation() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_perfil);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:
                        iniciarActivity(MainActivity.class);
                        break;

                    case R.id.navigation_perfil:
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
        Intent intent = new Intent(ActivityOne.this, activityQueVaiSerIniciada);

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        startActivity(intent);
    }


    private TextView setTextFor(final int textViewToSetUpTheText, final String textContent){
        TextView view = (TextView) findViewById(textViewToSetUpTheText);

        view.setText(textContent);

        return view;
    }


    private void setProfilePicture(final int pictureIdLocation) {
        ImageView mImageView = (ImageView) findViewById(R.id.profileImg);

        mImageView.setImageResource(pictureIdLocation);
        mImageView.setAdjustViewBounds(true);
        mImageView.setMaxHeight(650);
        mImageView.setMaxWidth(650);
    }


    private void setUpTextViews() {

        TextView titulo = setTextFor(R.id.activityTitle1, "Olá, " + usuario);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        pontos =  prefs.getInt("POINTS", 0);

        TextView ecopoints = setTextFor(R.id.ecopoints, msgInformandoPontuacao);
    }
}
