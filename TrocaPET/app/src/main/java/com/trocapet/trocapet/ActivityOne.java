package com.trocapet.trocapet;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by User on 4/15/2017.
 */


public class ActivityOne extends AppCompatActivity {

    private long ecoPoints = 10;
    private String userName = "Gilekel";


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


    private void startPassedActivity(final Class<?> activityToStart) {
        Intent intent = new Intent(ActivityOne.this, activityToStart);

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

        TextView title = setTextFor(R.id.activityTitle1, "Olá, " + userName);

        TextView ecopoints = setTextFor(R.id.ecopoints, "Você possui: " + ecoPoints + " ecopoints");
    }
}
