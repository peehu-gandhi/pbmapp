package com.example.bookbub;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccess(String response);
    void onError(Exception error);

}
