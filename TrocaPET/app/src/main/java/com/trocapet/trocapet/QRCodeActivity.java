package com.trocapet.trocapet;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by User on 4/15/2017.
 */

public class QRCodeActivity extends AppCompatActivity {

    public final static String QRCODE_VALIDO = "valido";
    private TextView qrcode_message;
    private ImageView qrcode_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        final Activity activityAtual = this;

        setUpQRCodeMsg();

        setUpQRCodeImg(activityAtual);

        setUpNavigation();
    }

    private void setUpNavigation() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_qrcode);
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
                        iniciarActivity(MapsActivity.class);
                        break;

                    case R.id.navigation_qrcode:
                        break;
                }

                return false;
            }
        });
    }

    private void iniciarActivity(final Class<?> activityQueVaiSerIniciada) {
        Intent intent = new Intent(QRCodeActivity.this, activityQueVaiSerIniciada);

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        startActivity(intent);
    }


    private void setUpQRCodeMsg() {
        qrcode_message = (TextView) findViewById(R.id.qrcode_message);
        qrcode_message.setText("Conecte-se ao coletor através do seu celular e troque suas garrafas PET. Clique no QRcode para iniciar leitura.");
    }

    private void setUpQRCodeImg(final Activity activity) {
        qrcode_img = (ImageView) findViewById(R.id.qrcode_img);

        qrcode_img.setImageResource(R.drawable.qrcode_img);
        qrcode_img.setAdjustViewBounds(true);
        qrcode_img.setMaxHeight(750);
        qrcode_img.setMaxWidth(750);

        qrcode_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("SCAN");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        System.out.println("Entrou no onActivityResult");

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() == null){
                System.out.println("Content null");
            } else {
                System.out.println("Qrcode lido");
                if (result.getContents().equals(QRCODE_VALIDO)){

                    //Atualizando pontos
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                    int points =  prefs.getInt("POINTS", 0);
                    SharedPreferences.Editor prefEditor = prefs.edit();
                    prefEditor.putInt("POINTS", points + 1);
                    prefEditor.commit();

                    // Exibindo mensagem de confirmação
                    new AlertDialog.Builder(QRCodeActivity.this)
                            .setTitle("Parabéns!")
                            .setMessage("Você adicionou uma garrafa e mais um ponto a sua conta.")
                            .setPositiveButton("Nova garrafa", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    qrcode_img.performClick();
                                }
                            })
                            .setNeutralButton("Pontuação", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent1 = new Intent(QRCodeActivity.this, ActivityOne.class);
                                    intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    startActivity(intent1);
                                }
                            })
                            .show();
                } else {
                    new AlertDialog.Builder(QRCodeActivity.this)
                            .setTitle("Não foi dessa vez!")
                            .setMessage("O qrcode lido não é válido.")
                            .setPositiveButton("Tentar novamente", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    qrcode_img.performClick();
                                }
                            })
                            .show();
                }
            }




        } else {
            System.out.println("Qrcode null");
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
