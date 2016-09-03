package com.example.sergio.pruebaandroid_sergiogomez;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class menu extends AppCompatActivity{
    TextView label;
    private VolleyS volley;
    protected RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        label = (TextView) findViewById(R.id.contenido);
        volley = VolleyS.getInstance(getApplicationContext());
        requestQueue = volley.getRequestQueue();
        makerequest();
    }
    public void addToQueue(Request request){
        if(request != null){
            request.setTag(this);
            if(requestQueue == null){
                requestQueue = volley.getRequestQueue();
                request.setRetryPolicy(new DefaultRetryPolicy(
                        60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                //onPreStartConnection();
                requestQueue.add(request);
            }
        }
    }
    //peticion del web service mediante la libreria volley
    private void makerequest(){
        String url = "http://bit.ly/MenuEleinco";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                label.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"An error Ocurred!!",Toast.LENGTH_SHORT).show();
            }
        });
        addToQueue(request);
    }
}
