package com.example.nafees.matcode;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Leaderboard extends AppCompatActivity {

    ArrayList<Info> rank;
    ListView listView;
    RankAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        rank = new ArrayList<>();
        listView = findViewById(R.id.listview);

        adapter = new RankAdapter(this,rank);
        listView.setAdapter(adapter);

        new GetRank(this).execute();
    }

    class GetRank extends AsyncTask<Void,Void,Void>{

        Context context;
        ProgressDialog dialog;

        GetRank(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            dialog = new ProgressDialog(context);
            dialog.setMessage("Working ...");
            dialog.setCancelable(false);
            dialog.show();

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            HttpHelper helper = new HttpHelper();

            String data = helper.Get("https://Decodeapp1.herokuapp.com/api/leaderboard/");

            data = data.substring(4,data.length());
            data = "{ \"data\": " + data + "}";

            JSONObject json = null;
            try {

                json = new JSONObject(data);
                JSONArray array = json.getJSONArray("data");

                for(int i=0;i<array.length();i++) {

                    Info info = new Info();

                    JSONObject j = array.getJSONObject(i);

                    info.setName(j.getString("name"));
                    info.setRank(Integer.toString(i+1));
                    info.setXp(j.getString("score"));

                    rank.add(info);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            adapter.addAll(rank);

            super.onPostExecute(aVoid);
        }
    }

}

