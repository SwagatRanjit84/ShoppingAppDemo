package com.example.shoppingapp.onlineshopping;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.shoppingapp.onlineshopping.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by YubRaj on 10/7/2016.
 */

public class GoogleMap extends AppCompatActivity {
    static final LatLng IT_TRAINING = new LatLng(27.7070883,85.3224615);
    static final LatLng SINGHA_DARABAR = new LatLng(27.698127,85.325248);
    private com.google.android.gms.maps.GoogleMap map;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        Marker it_training = map.addMarker(new MarkerOptions().
                position(IT_TRAINING)
                .title("IT Training Nepal")//title diney
                .snippet("Training for IT Courses"));//description

        Marker singha_darbar = map.addMarker(new MarkerOptions()
                .position(SINGHA_DARABAR)
                .title("Singha Darbar")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.places_ic_search)));

        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(IT_TRAINING, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);//

    }
}