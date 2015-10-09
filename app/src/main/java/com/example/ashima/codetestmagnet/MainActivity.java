package com.example.ashima.codetestmagnet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView rView;
    DataAdapter dataAdapter;
    RequestQueue requestQueue;
    String url = "https://api.github.com/emojis";
    DataPojo dataPojo;
    private ArrayList<DataPojo> dataPojoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rView = (RecyclerView) findViewById(R.id.recycler_view);
        requestQueue = VolleySingleton.getInstance().getRequestQueue();
        sendJsonRequest();

    }

    public void sendJsonRequest() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        parseJsonData(response.toString());
                    }
                }, new Response.ErrorListener() {

            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjReq);
    }

    private void updateView(List<DataPojo> dList) {

        dataAdapter = new DataAdapter(this, dList);
        rView.setAdapter(dataAdapter);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1000);
        animator.setRemoveDuration(1000);
        rView.setItemAnimator(animator);
        rView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void parseJsonData(String res) {
        if (res == null || res.length() == 0) {
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(res);
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                // Log.i("TAG","key:"+key +"--Value::"+jsonObject.optString(key));
                dataPojo = new DataPojo();
                dataPojo.setEmotText(key);
                dataPojo.setIconLink(jsonObject.optString(key));
                dataPojoList.add(dataPojo);
                updateView(dataPojoList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}