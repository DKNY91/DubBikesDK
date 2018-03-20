
//David Kenny 15761
package com.example.eireog.dubbikesdk;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DownloadTask task = new DownloadTask();
        task.execute("https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=090219beb9eddd610924ac169c74736ab9c0eb9f");
        for (int t=0;t<4; t++) {
            Toast.makeText(getApplicationContext(), "Loading----", Toast.LENGTH_SHORT).show();

        }

     class DownloadTask extends AsyncTask<String, Void, String> {

      private String result = "";
        @Override
        protected String doInBackground(String... urls) {

            URL url;
            HttpURLConnection urlConnection;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {

                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }

                return result;

            } catch (Exception e) {

                Toast.makeText(getApplicationContext(), "Download Failed", Toast.LENGTH_LONG);

            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) { /// will create a list
            super.onPostExecute(result);


                try {
                    final JSONArray = new JSONArray(result);
                    ListView myListView = (ListView)findViewById(R.id.myListView);
                    final ArrayList<String> StationList = new ArrayList<String>();


                    JSONObject jsonArray;
                    for (int i = 0; i < jsonArray.length(); i++) { // length of array
                    JSONObject jsonPart = jsonArray.getJSONObject(i);
                    String stationName = jsonPart.getString("name");
                    Log.i("stationName String" , stationName);
                    Log.i("station", jsonPart.getString("name"));
                    stationList.add(stationName);
                    JSONObject pos = jsonPart.getJSONObject("position")
                     Log.i("lat", pos.getString("lat"));
;

                     ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListActivity.this, android.R.layout.simple_list_item_1, StationList}
                myListView.setAdapter(arrayAdapter);

                    myListView.setOnItemClickListener((adapterView, view, i 1)

                            Log.i("Station Clciked", stationList.get(i)));

                    try {
                    JSONObject object = jsonArray.getJSONObject(i);
                        String name = object.getString("name");
                        Log.i("status test", name);
                        String status = object.getString("status");
                        Log.i("status test", status);
                        String address = object.getString("address");
                        Log.i("address test", address);
                         String bikeStands = object.getString("bike_stands");
                         Log.i("bike stands test", bikeStands);
                         String availableBikeStands = object.getString("available_bike_stands");
                         Log.i("availableBikeStandsTest", availableBikeStands);
                        String availableBikes = object.getString("available_bikes");
                        Log.i("availableBikesTest", availableBikes);


                        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                         Bundle extras = new Bundle();
                         extras.putString("STATIONNAME", name);
                        extras.putString("STATUS", status);
                        extras.putString("ADDRESS", address);
                        extras.putString("BIKESTANDS", bikeStands);
                        extras.putString("AVAILABLEBIKESTANDS", availableBikeStands);
                        extras.putString("AVAILABLEBIKES", availableBikes);
                        intent.putExtras(extras);
                        startActivity(intent);



                } catch (JSONException el) {
                        el.printStackTrace();
                    }




        };
        catch (JSONException e) {

            Toast.makeText(getApplicationContext(), "Download Failed", Toast.LENGTH_LONG);
            }

        }
              public String getData() (return result );

