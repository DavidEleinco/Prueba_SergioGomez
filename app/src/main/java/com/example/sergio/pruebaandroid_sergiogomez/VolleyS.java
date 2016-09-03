package com.example.sergio.pruebaandroid_sergiogomez;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Sergio on 3/09/2016.
 */
public class VolleyS {
    private static VolleyS mVolleyS = null;
    //This object is the application's queue
    private RequestQueue mRequestQueue;

    private VolleyS(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
    }
    public static VolleyS getInstance(Context context){
        if(mVolleyS == null){
            mVolleyS = new VolleyS(context);
        }
        return mVolleyS;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
