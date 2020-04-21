package com.example.coursework;
//Andrew Hart S1616276

import android.util.Log;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class Processing {

    private LinkedList <Incident> alist;
    private LinkedList <Roadworks> alist2;
    private LinkedList <Plannedroadworks> alist3;
    private TextView txt;
    private GoogleMap googleMap;



    public Processing(){

    }

    public void parseAllSearch(MainActivity ma, String incidentData, String roadworkData, String plannedRoadworkData, final GoogleMap gm, String userSearch){

        txt = ma.findViewById(R.id.acknowledgement);
        alist = null;
        alist2 = null;
        alist3 = null;
        Incident incident = new Incident();
        Roadworks roadwork = new Roadworks();
        Plannedroadworks plannedRw = new Plannedroadworks();
        final String search = userSearch;


        try
        {
            System.out.println(incidentData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( incidentData ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist  = new LinkedList<Incident>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        incident = new Incident();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        incident.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();
                        incident.setCoordinates(temp);
                        incident.splitCoords(temp);
                    }
                    else
                        if (xpp.getName().equalsIgnoreCase("pubDate"))
                        {
                            String temp = xpp.nextText();
                            incident.setPublishDate(temp);
                        }
                }
                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + incident.toString());
                        alist.add(incident);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        try
        {
            System.out.println(roadworkData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( roadworkData ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist2 = new LinkedList<Roadworks>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        roadwork = new Roadworks();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        roadwork.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();
                        roadwork.setCoordinates(temp);
                        roadwork.splitCoords(temp);
                    }
                    else
                        if (xpp.getName().equalsIgnoreCase("pubDate"))
                        {
                            String temp = xpp.nextText();
                            roadwork.setPublishDate(temp);
                        }
                }
                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + roadwork.toString());
                        alist2.add(roadwork);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist2.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        try
        {
            System.out.println(plannedRoadworkData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( plannedRoadworkData ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist3 = new LinkedList<Plannedroadworks>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        plannedRw = new Plannedroadworks();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        plannedRw.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();
                        plannedRw.setCoordinates(temp);
                        plannedRw.splitCoords(temp);
                    }
                    else
                        if (xpp.getName().equalsIgnoreCase("pubDate"))
                        {
                            String temp = xpp.nextText();
                            plannedRw.setPublishDate(temp);
                        }
                }
                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + plannedRw.toString());
                        alist3.add(plannedRw);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist3.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }


        System.out.println(alist);
        System.out.println(alist2);
        System.out.println(alist3);

        ma.runOnUiThread(new Runnable()
        {
            public void run() {
                Log.d("UI thread", "I am the UI thread");

                StringBuilder builder = new StringBuilder();
                gm.clear();
                    for(Incident incident : alist){

                        if(incident.getTitle().contains(search.toUpperCase())){
                            builder.append(incident.getTitle()).append("\n").
                                    append(incident.getLatitude()).append("\n").
                                    append(incident.getLongitude()).append("\n").
                                    append(incident.getPublishDate()).append("\n\n");
                            LatLng newMarkerCoords = new LatLng(incident.getLatitude(), incident.getLongitude());
                            gm.addMarker(new MarkerOptions().position(newMarkerCoords).title(incident.getTitle()));
                        }
                    }
                    for(Roadworks roadworks : alist2){

                        if(roadworks.getTitle().contains(search.toUpperCase())){
                            builder.append(roadworks.getTitle()).append("\n").
                                    append(roadworks.getLatitude()).append("\n").
                                    append(roadworks.getLongitude()).append("\n").
                                    append(roadworks.getPublishDate()).append("\n\n");
                            LatLng newMarkerCoords = new LatLng(roadworks.getLatitude(), roadworks.getLongitude());
                            gm.addMarker(new MarkerOptions().position(newMarkerCoords).title(roadworks.getTitle()));
                        }

                    }
                    for(Plannedroadworks plannedroadworks : alist3){

                        if(plannedroadworks.getTitle().contains(search.toUpperCase())){
                            builder.append(plannedroadworks.getTitle()).append("\n").
                                    append(plannedroadworks.getLatitude()).append("\n").
                                    append(plannedroadworks.getLongitude()).append("\n").
                                    append(plannedroadworks.getPublishDate()).append("\n\n");

                            LatLng newMarkerCoords = new LatLng(plannedroadworks.getLatitude(), plannedroadworks.getLongitude());
                            gm.addMarker(new MarkerOptions().position(newMarkerCoords).title(plannedroadworks.getTitle()));
                        }
                    }

                txt.setText(builder.toString());
            }
        });

    }

    public void parseIncidents(MainActivity ma, String incidentData, final GoogleMap gm){

        txt = ma.findViewById(R.id.acknowledgement);
        alist = null;
        Incident incident = new Incident();

        try
        {
            System.out.println(incidentData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( incidentData ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist  = new LinkedList<Incident>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        incident = new Incident();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        incident.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();

                        incident.setCoordinates(temp);
                        incident.splitCoords(temp);
                    }
                    else
                        if (xpp.getName().equalsIgnoreCase("pubDate"))
                        {
                            String temp = xpp.nextText();
                            incident.setPublishDate(temp);
                        }
                }
                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + incident.toString());
                        alist.add(incident);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            } // End of while
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }
        System.out.println("End document");


        ma.runOnUiThread(new Runnable()
        {
            public void run() {
                Log.d("UI thread", "I am the UI thread");

                StringBuilder builder = new StringBuilder();
                gm.clear();
                if(alist.isEmpty()){
                    //checks if there are any incidents at the time.
                    System.out.println(alist);
                    txt.setText("There are no incidents right now!");
                }else{
                    for(Incident incident : alist){
                        builder.append(incident.getTitle()).append("\n").
                                append(incident.getLatitude()).append("\n").
                                append(incident.getLongitude()).append("\n").
                                append(incident.getPublishDate()).append("\n\n");
                        LatLng newMarkerCoords = new LatLng(incident.getLatitude(), incident.getLongitude());
                        gm.addMarker(new MarkerOptions().position(newMarkerCoords).title(incident.getTitle()));

                    }

                    txt.setText(builder.toString());
                }

            }
        });
    }

    public void parseRoadworks(MainActivity ma, String roadworkData, final GoogleMap gm){

        txt = ma.findViewById(R.id.acknowledgement);
        alist2 = null;
        Roadworks roadwork = new Roadworks();
        try
        {
            System.out.println(roadworkData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( roadworkData ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist2 = new LinkedList<Roadworks>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        roadwork = new Roadworks();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        roadwork.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();

                        roadwork.setCoordinates(temp);
                        roadwork.splitCoords(temp);
                    }
                    else
                        if (xpp.getName().equalsIgnoreCase("pubDate"))
                        {
                            String temp = xpp.nextText();
                            roadwork.setPublishDate(temp);
                        }
                }
                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + roadwork.toString());
                        alist2.add(roadwork);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist2.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }
        System.out.println("End document");


        ma.runOnUiThread(new Runnable()
        {
            public void run() {
                Log.d("UI thread", "I am the UI thread");

                StringBuilder builder = new StringBuilder();
                gm.clear();
                if(alist2==null){
                    System.out.println(alist2);
                    txt.setText("There are no roadworks just now!");
                }else{
                    for(Roadworks roadworks : alist2){
                        builder.append(roadworks.getTitle()).append("\n").
                                append(roadworks.getLatitude()).append("\n").
                                append(roadworks.getLongitude()).append("\n").
                                append(roadworks.getPublishDate()).append("\n\n");
                        LatLng newMarkerCoords = new LatLng(roadworks.getLatitude(), roadworks.getLongitude());
                        gm.addMarker(new MarkerOptions().position(newMarkerCoords).title(roadworks.getTitle()));
                    }
                    txt.setText(builder.toString());
                }
            }
        });
    }

    public void parsePlannedRoadworks(MainActivity ma, final String plannedRoadworkData, final GoogleMap gm){
        //planned roadworks are parsed if the user clicks planned roadworks
        txt = ma.findViewById(R.id.acknowledgement);
        alist3 = null;
        Plannedroadworks plannedRw = new Plannedroadworks();
        try
        {
            System.out.println(plannedRoadworkData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( plannedRoadworkData ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist3 = new LinkedList<Plannedroadworks>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        plannedRw = new Plannedroadworks();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        plannedRw.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();

                        plannedRw.setCoordinates(temp);
                        plannedRw.splitCoords(temp);
                    }
                    else
                        if (xpp.getName().equalsIgnoreCase("pubDate"))
                        {

                            String temp = xpp.nextText();

                            plannedRw.setPublishDate(temp);
                        }
                }
                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + plannedRw.toString());
                        alist3.add(plannedRw);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist3.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }
        System.out.println("End document");


        ma.runOnUiThread(new Runnable()
        {
            public void run() {
                Log.d("UI thread", "I am the UI thread");

                StringBuilder builder = new StringBuilder();
                gm.clear();
                if(alist3==null){
                    System.out.println(alist3);
                    txt.setText("There are no planned roadworks just now!");
                }else{
                    for(Plannedroadworks plannedroadworks : alist3){
                        builder.append(plannedroadworks.getTitle()).append("\n").
                                append(plannedroadworks.getLatitude()).append("\n").
                                append(plannedroadworks.getLongitude()).append("\n").
                                append(plannedroadworks.getPublishDate()).append("\n\n");

                        LatLng newMarkerCoords = new LatLng(plannedroadworks.getLatitude(), plannedroadworks.getLongitude());
                        gm.addMarker(new MarkerOptions().position(newMarkerCoords).title(plannedroadworks.getTitle()));
                    }
                    txt.setText(builder.toString());
                }
            }
        });
    }
}

