package com.trocapet.trocapet;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_qrcode);

        final Activity activity = this;

        // Ajustando fonte
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/FuturaBookBT.ttf");

        qrcode_message = (TextView) findViewById(R.id.qrcode_message);
        qrcode_message.setTypeface(custom_font);

        TextView qrcode_message2 = (TextView) findViewById(R.id.qrcode_message2);
        qrcode_message2.setTypeface(custom_font);

        // Ajustando ação de leitura do qrcode
        qrcode_img = (ImageView) findViewById(R.id.qrcode_img);

        qrcode_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Recicla Point");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:
                        Intent intent0 = new Intent(QRCodeActivity.this, MainActivity.class);
                        intent0.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent0);
                        break;

                    case R.id.navigation_perfil:
                        Intent intent1 = new Intent(QRCodeActivity.this, ActivityOne.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_mapa:
                        Intent intent2 = new Intent(QRCodeActivity.this, MapsActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_qrcode:

                        break;
                }


                return false;
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
