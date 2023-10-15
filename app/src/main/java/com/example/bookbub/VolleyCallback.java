package com.example.bookbub;

import com.android.volley.VolleyError;

public interface VolleyCallback {
    void onSuccess(String response);
    void onError(Exception error);
}
