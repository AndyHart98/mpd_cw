package com.example.coursework;
//Andrew Hart S1616276

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private EditText editTxt;
    private TextView txt;
    private String result;
    private String result1;
    private String result2;
    private Button startButton;
    private Button rwBtn;
    private Button plndrwBtn;
    private Button searchButton;
    private ScrollView textResults;
    // Traffic Scotland URLs
    private String roadworksURL = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
    private String plannedroadworksURL = "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx";
    private String urlSource = "https://trafficscotland.org/rss/feeds/currentincidents.aspx";
    public int i;
    private GoogleMap googleMap;
    private String userSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResults = (ScrollView)findViewById(R.id.textResults);

        editTxt = (EditText)findViewById(R.id.editTxt);

        txt = (TextView)findViewById(R.id.acknowledgement);

        searchButton = (Button)findViewById(R.id.searchButton);

        startButton = (Button)findViewById(R.id.startButton);

        rwBtn = (Button)findViewById(R.id.roadworksbtn);

        plndrwBtn = (Button)findViewById(R.id.plannedroadworksbtn);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onClick1(View aview) {
        i = 0;
        startProgress1();
    }

    public void onClick2(View aview) {
        i = 1;
        startProgress2();
    }

    public void onClick3(View aview) {
        i = 2;
        startProgress3();
    }

    public void onClick4(View aview) {
        i = 3;
        startProgress4();
    }

    public void startProgress1() {
        // Run network access on a separate thread;
        new Thread(new Task(urlSource, this)).start();
    }

    public void startProgress2() {
        // Run network access on a separate thread
        new Thread(new Task(roadworksURL, this)).start();
    }

    public void startProgress3() {
        // Run network access on a separate thread;
        new Thread(new Task(plannedroadworksURL, this)).start();
    }

    public void startProgress4() {
        // Run network access on a separate thread;
        new Thread(new Task(urlSource, roadworksURL, plannedroadworksURL, this)).start();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(this);
        LatLngBounds scotland = new LatLngBounds(new LatLng(56, 4), new LatLng(56, 4));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(scotland.getCenter(), 3));
        this.googleMap = googleMap;
    }

    private class Task implements Runnable {
        private String url;
        private String url1;
        private String url2;
        private MainActivity ma;
        Processing processing = new Processing();

        public Task(String aurl, MainActivity ma) {
            url = aurl;
            this.ma = ma;
        }

        public Task(String aurl1, String aurl2, String aurl3, MainActivity ma){
            url = aurl1;
            url1 = aurl2;
            url2 = aurl3;
            this.ma = ma;
        }

        @Override
        public void run() {

            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";
            String search = editTxt.getText().toString();

            Log.e("MyTag","in run");

            if(i==3){
                try
                {
                    Log.e("MyTag","in try");
                    aurl = new URL(url);
                    yc = aurl.openConnection();
                    in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                    //
                    // Throw away the first 2 header lines before parsing
                    //
                    //

                    result = in.readLine();
                    while ((inputLine = in.readLine()) != null)
                    {
                        result = result + inputLine;
                        Log.e("MyTag",inputLine);

                    }
                    in.close();
                }
                catch (IOException ae)
                {
                    Log.e("MyTag", "ioexception");
                }

                try
                {
                    Log.e("MyTag","in try");
                    aurl = new URL(url1);
                    yc = aurl.openConnection();
                    in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                    //
                    // Throw away the first 2 header lines before parsing
                    //
                    //

                    result1 = in.readLine();
                    while ((inputLine = in.readLine()) != null)
                    {
                        result1 = result1 + inputLine;
                        Log.e("MyTag",inputLine);

                    }
                    in.close();
                }
                catch (IOException ae)
                {
                    Log.e("MyTag", "ioexception");
                }

                try
                {
                    Log.e("MyTag","in try");
                    aurl = new URL(url2);
                    yc = aurl.openConnection();
                    in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                    //
                    // Throw away the first 2 header lines before parsing
                    //
                    //

                    result2 = in.readLine();
                    while ((inputLine = in.readLine()) != null)
                    {
                        result2 = result2 + inputLine;
                        Log.e("MyTag",inputLine);

                    }
                    in.close();
                }
                catch (IOException ae)
                {
                    Log.e("MyTag", "ioexception");
                }


            }else{
                try
                {
                    Log.e("MyTag","in try");
                    aurl = new URL(url);
                    yc = aurl.openConnection();
                    in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                    //
                    // Throw away the first 2 header lines before parsing
                    //
                    //

                    result = in.readLine();
                    while ((inputLine = in.readLine()) != null)
                    {
                        result = result + inputLine;
                        Log.e("MyTag",inputLine);

                    }
                    in.close();
                }
                catch (IOException ae)
                {
                    Log.e("MyTag", "ioexception");
                }
            }



            System.out.println("Test pls work");

            if(i == 0){
                processing.parseIncidents(this.ma, result, googleMap);
            }else if(i == 1){
                processing.parseRoadworks(this.ma, result, googleMap);
            }else if(i == 2){
                processing.parsePlannedRoadworks(this.ma, result, googleMap);
            }else if(i == 3){
                processing.parseAllSearch(this.ma, result, result1, result2, googleMap, search);
            }


        }

    }


} // End of MainActivity