package com.trocapet.trocapet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by User on 4/15/2017.
 */

public class ActivityOne extends AppCompatActivity {

    private String userName = "Gileade Kelvin";

    @Override
    protected void onResume(){
        super.onResume();
        // atualiza pontuação com a activity em resumo (em segundo plano)
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int points =  prefs.getInt("POINTS", 0);
        Button but_points = (Button) findViewById(R.id.points);
        but_points.setText(points + " pontos");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_one);

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
                        startPassedActivity(MainActivity.class);
                        break;

                    case R.id.navigation_perfil:
                        break;

                    case R.id.navigation_mapa:
                        startPassedActivity(MapsActivity.class);
                        break;

                    case R.id.navigation_qrcode:
                        startPassedActivity(QRCodeActivity.class);
                        break;
                }
                return false;
            }
        });

    }


    private void startPassedActivity(final Class<?> activityQueVaiSerIniciada) {
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
        CircleImageView mImageView = (CircleImageView) findViewById(R.id.profileImg);

        mImageView.setImageResource(pictureIdLocation);
        mImageView.setAdjustViewBounds(false);
        mImageView.setMaxHeight(800);
        mImageView.setMaxWidth(800);

    }

    private void setUpTextViews() {

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/FuturaBookBT.ttf");
        TextView user_name = (TextView) findViewById(R.id.activityTitle);
        user_name.setTypeface(custom_font);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int points =  prefs.getInt("POINTS", 0);

        TextView premiacoes = (TextView) findViewById(R.id.premiacoes);
        premiacoes.setTypeface(custom_font);

        Button but_points = (Button) findViewById(R.id.points);
        but_points.setText(points + " pontos");
        but_points.setTypeface(custom_font);
    }
}
