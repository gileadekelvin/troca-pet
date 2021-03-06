package com.trocapet.trocapet;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // markers
        LatLng ufcg = new LatLng(-7.215192, -35.909692);
        mMap.addMarker(new MarkerOptions().position(ufcg).title("UFCG").snippet("Praça de Alimentação"));

        LatLng shopping = new LatLng(-7.235197, -35.870166);
        mMap.addMarker(new MarkerOptions().position(shopping).title("Shopping Partage").snippet("Entrada Principal"));

        LatLng bandeira = new LatLng(-7.2193144, -35.8872787);
        mMap.addMarker(new MarkerOptions().position(bandeira).title("Praça da bandeira").snippet("Centro"));

        LatLng luizaMotta = new LatLng(-7.234705, -35.8814747);
        mMap.addMarker(new MarkerOptions().position(luizaMotta).title("Shopping Luiza Motta").snippet("Entrada Principal"));

        LatLng cea = new LatLng(-7.2180039, -35.8859999);
        mMap.addMarker(new MarkerOptions().position(cea).title("Loja C&A").snippet("Entrada Principal"));

        LatLng cg = new LatLng(-7.2428323, -35.9716054);

        mMap.setOnInfoWindowClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bandeira, 12));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Ponto verde de coleta",
                Toast.LENGTH_SHORT).show();
    }
}
