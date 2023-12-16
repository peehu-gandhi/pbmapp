package com.example.bookbub;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static android.app.Activity.RESULT_OK;
import static nl.dionsegijn.konfetti.models.Shape.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText edno, edpasswd;
    Button btn_login;
    SessionManager sessionManager=null;
    KonfettiView konfettiView;
    public LoginFragment() {
        // Required empty public constructor
    }
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login, container, false);
        if (SharedPrefManager.getInstance(getContext()).isLoggedIn()) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
         sessionManager = new SessionManager(getContext());

        edno = v.findViewById(R.id.edno);
        edpasswd = v.findViewById(R.id.edpasswd);
        btn_login=v.findViewById(R.id.btn_login);
        btn_login.setBackgroundColor(Color.parseColor("#2052e8"));
          konfettiView = v.findViewById(R.id.viewKonfetti);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();

            }
        });
        if (sessionManager.isLoggedIn()) {
            // User is already logged in, redirect to the main activity or wherever needed
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        }

        return v;

    }

    private void userLogin() {
        final String no = edno.getText().toString();
        final String passwd = edpasswd.getText().toString();
        //validating inputs
        if (TextUtils.isEmpty(no)) {
            edno.setError("Please enter your Mobile no");
            edno.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(passwd)) {
            edpasswd.setError("Please enter your password");
            edpasswd.requestFocus();
            return;
        }
        login(no,passwd);
    }
    private void login(String no,String passwd)
    {
        String urllogin="https://pbmabad.000webhostapp.com/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urllogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object

                            //if no error in response
                                JSONObject jsonResult = new JSONObject(response);
                                System.out.println("response=="+response);
                                String status = jsonResult.getString("status");
                                if ("success".equals(status)) {
                                    System.out.println("lllllll=="+jsonResult.getInt("pid"));
                                    sessionManager.createLoginSession(edno.getText().toString(),String.valueOf(jsonResult.getInt("pid")),jsonResult.getString("gender"),jsonResult.getString("physical_path"),jsonResult.getString("profile_id")); // Save login state

                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.putExtra("gender",jsonResult.getString("gender"));
                                    intent.putExtra("pid",jsonResult.getInt("pid"));
                                    System.out.println("pid=="+jsonResult.getInt("pid"));
                                    System.out.println("gen=="+jsonResult.getString("gender"));
                                    konfettiView.build()
                                            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                                            .setDirection(0.0, 359.0)
                                            .setSpeed(1f, 5f)
                                            .setFadeOutEnabled(true)
                                            .setTimeToLive(2000L)
                                            .addShapes(RECT, CIRCLE)
                                            .addSizes(new Size(12,5))
                                            .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                                            .stream(300, 5000L);


                                    startActivityForResult(intent,1);
                                    getActivity().setResult(RESULT_OK, intent);
                                    getActivity().finish();


                                } else {
                                    // Login failed
                                    String message = jsonResult.optString("message", "Login failed");
                                    Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                                }
                                //storing the user in shared preferences
//                                SharedPrefManager.getInstance(getContext()).userLogin(user);
//                                //starting the profile activity
//                                getActivity().finish();




                        } catch (Exception e) {
                            System.out.println("error=="+e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("error==>>"+error.getMessage());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", no);
                params.put("password", passwd);
                return params;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
}