package com.example.bookbub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bookbub.model.Book;
import com.github.barteksc.pdfviewer.PDFView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsActivity extends AppCompatActivity {
    WebView wv1;
    public static final String UPLOAD_URL = "https://myimon.000webhostapp.com/insert_new_book.php";
    public static final String UPLOAD_KEY = "image";
    TextView hindi,tamil,gujarati,english;
    CardView cardtamil,cardhindi,cardenglish,cardgujarati;
    CardView cardscifi,cardhorror,cardcomedy,cardstudy;
    String first_name, middle_name, last_name, gender, dob, place_of_birth, str_profile_image, height, str_weight, complexion, str_spectacles, physial_handicap, education, occupation, income, origin_of_family, no_of_brothers, no_of_sisters, no_of_brothers_married, no_of_sisters_married, no_of_brothers_unmarried, no_of_sisters_unmarried, hobbies, manglik, required_education_of_partner, age_group_preference_for_partner, required_height_of_partner, required_weight_of_partner, email_address, mobile_number, landline_number, residence_number, address_line_1, address_line_2, aadhar_card_or_pan_card, latest_education_qualification, string_income_proof, fee_submitted_2000_proof,profile_status;


    Uri filePath;
    private int PICK_IMAGE_REQUEST = 1;
    private LinearLayout btnchoose;
    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;
    private TextView tvchoose;
    private ImageView imageView;
    String booknm;
    private Bitmap bitmap;
    Button bt;
    String displayName = null;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private ViewPager.OnPageChangeListener mPageChangeListener;
    TextView bookname,desc,authorname;
    String ur="https://pbmabad.000webhostapp.com/Php_user_detail.php";
    RelativeLayout rv;
    private TextView[] dots;
    String strname=null,strdesc=null;
    ImageView book_img,profile_image;
    Button btn_login;
    String strnm,pid;
    String path="";
    private int[] layouts;
    EditText tvname = null,tvdesc = null;

    private Button btnSkip, btnNext;
    private String encodedpdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_book_details);
        setContentView(R.layout.activity_new);
        getSupportActionBar().hide();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                path= null;
            } else {
                path= extras.getString("physical_path");
                pid=extras.getString("pid");
            }
        } else {
            path= (String) savedInstanceState.getSerializable("physical_path");
            pid= (String) savedInstanceState.getSerializable("pid");

        }
//        fillDetails();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);
        layouts = new int[]{
                R.layout.publish_book_pg1,
                R.layout.publish_book_pg2,
                R.layout.publish_book_pg4,
                R.layout.publish_book_pg5,
                R.layout.publish_book_pg3,
                R.layout.publish_book_pg6, R.layout.publish_book_pg7};

        addBottomDots(0);
        changeStatusBarColor();
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        viewPager.setOffscreenPageLimit(layouts.length - 1);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // launchHomeScreen();
                if (v == buttonChoose) {
                    showFileChooser();
                }

                if(v == buttonUpload){
                }

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                }
//                else {
//                    launchHomeScreen();
//                }
            }
        });
        /*setContentView(R.layout.activity_new);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_new);
        getSupportActionBar().hide();
        viewPager = findViewById(R.id.pagerIntroSlider);
        TabLayout tabLayout = findViewById(R.id.tabs);
        button = findViewById(R.id.button);
        // init slider pager adapter
        adapter = new SliderPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        // set adapter
        viewPager.setAdapter(adapter);
        // set dot indicators
        tabLayout.setupWithViewPager(viewPager);
        // make status bar transparent
        changeStatusBarColor();
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (viewPager.getCurrentItem() < adapter.getCount()) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });*/

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//            @Override public void onPageSelected(int position) {
//                if (position == adapter.getCount() - 1) {
//                    button.setText(R.string.get_started);
//                } else {
//                    button.setText(R.string.next);
//                }
//            }
//            @Override public void onPageScrollStateChanged(int state) {
//            }
//        });
    }



    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    private String convertToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    private void addBottomDots(int currentPage)
    {
        dots = new TextView[layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }
    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
    //    private void launchHomeScreen() {
//       // prefManager.setFirstTimeLaunch(false);
//        startActivity(new Intent(NewActivity.this, NewActivity.class));
//        finish();
//    }
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnNext.setVisibility(View.GONE);
                btnSkip.setVisibility(View.GONE);
            } else
            {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
            if (position == 2) {
//                tvname = findViewById(R.id.name);
//                tvdesc = findViewById(R.id.desc);
//                strname = tvname.getText().toString();
//                System.out.println("thaii=="+strname);
//                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
//
//                SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//                myEdit.putString("name", strname);
//
//                myEdit.commit();
//                booknm=strname;
//                strdesc = tvdesc.getText().toString();
            }
            if(position == 0)
            {

            }
            if (position == 3) {

                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // View v=inflater.inflate(R.layout.make_cover,null,false);
                //Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
                //Canvas c = new Canvas(b);
                // if(strname!=null)Toast.makeText(NewActivity.this, "val=="+strname, Toast.LENGTH_SHORT).show();
                /// v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                // bn.setText(tvname.getText().toString());
                //d.setText(tvdesc.getText().toString());
                //v.draw(c);

                //book_img.setImageBitmap(b);



            }
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }









        @Override
        public void onPageScrollStateChanged(int arg0) {


        }
    };
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        public MyViewPagerAdapter() {
        }
        @Override
        public void startUpdate(@NonNull ViewGroup container) {
            super.startUpdate(container);
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);

            container.addView(view);
            if(position == 0)
            {


                profile_image=view.findViewById(R.id.profile_image);
                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.bookcover2)
                        .error(R.drawable.bookcover2);

                Glide.with(getApplicationContext()).load(path).apply(options).into(profile_image);

            }
            if(position == 1)
            {
                TextView et_first_name,et_middle_name,et_last_name,et_gender,et_dob,et_place_of_birth,et_profile_status;
                et_first_name=view.findViewById(R.id.et_first_name);
                et_middle_name=view.findViewById(R.id.et_middle_name);
                et_last_name=view.findViewById(R.id.et_last_name);
                et_gender = view.findViewById(R.id.et_gender);
                et_dob = view.findViewById(R.id.et_dob);
                et_place_of_birth = view.findViewById(R.id.et_place_of_birth);
                et_profile_status = view.findViewById(R.id.et_profile_status);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, ur,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray array = new JSONArray(response);
                                    for (int i = 0; i<array.length(); i++){
                                        JSONObject object = array.getJSONObject(i);
                                        et_first_name.setText(object.getString("first_name"));
                                        et_middle_name.setText(object.getString("middle_name"));
                                        et_last_name.setText(object.getString("last_name"));
                                        et_gender.setText((object.getString("gender")=="M")?"Male":"Female");
                                        et_dob.setText(object.getString("dob_place"));
                                        et_place_of_birth.setText(object.getString("dob_time"));
                                        et_profile_status.setText(object.getString("profile_status"));
                                    }
                                }catch (Exception e){
                                    System.out.println("here exception==>"+e.getMessage());
                                }
//                        mAdapter = new RecyclerAdapter(HomeActivity.this,products);
//                        recyclerView.setAdapter(mAdapter);

                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("pid", pid);
                        return params;

                    }
                };

                Volley.newRequestQueue(BookDetailsActivity.this).add(stringRequest);



            }
            if(position == 2)
            {

                TextView et_height,et_weight,et_complexion,et_spectacles,et_physical_handicap;
                et_height = view.findViewById(R.id.et_height);
                et_weight = view.findViewById(R.id.et_weight);
                et_complexion = view.findViewById(R.id.et_complexion);
                et_spectacles = view.findViewById(R.id.et_spectacles);
                et_physical_handicap = view.findViewById(R.id.et_physical_handicap);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, ur,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray array = new JSONArray(response);
                                    for (int i = 0; i<array.length(); i++){
                                        JSONObject object = array.getJSONObject(i);
                                        et_height.setText(object.getString("height"));
                                        et_weight.setText(object.getString("weight"));
                                        et_complexion.setText(object.getString("complexion"));
                                        et_spectacles.setText(object.getString("spectacles"));
                                        et_physical_handicap.setText(object.getString("pysical_handicap"));
                                    }
                                }catch (Exception e){
                                    System.out.println("here exception==>"+e.getMessage());
                                }
//                        mAdapter = new RecyclerAdapter(HomeActivity.this,products);
//                        recyclerView.setAdapter(mAdapter);

                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("pid", pid);
                        return params;

                    }
                };

                Volley.newRequestQueue(BookDetailsActivity.this).add(stringRequest);



            }

            if(position==3){
                TextView et_income,et_occ,et_edu;

                et_income = view.findViewById(R.id.et_income);
                et_occ = view.findViewById(R.id.et_occ);
                et_edu = view.findViewById(R.id.et_edu);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, ur,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray array = new JSONArray(response);
                                    for (int i = 0; i<array.length(); i++){
                                        JSONObject object = array.getJSONObject(i);
                                        et_income.setText(object.getString("income"));
                                        et_occ.setText(object.getString("occupation"));
                                        et_edu.setText(object.getString("education"));

                                    }
                                }catch (Exception e){
                                    System.out.println("here exception==>"+e.getMessage());
                                }
//                        mAdapter = new RecyclerAdapter(HomeActivity.this,products);
//                        recyclerView.setAdapter(mAdapter);

                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("pid", pid);
                        return params;

                    }
                };

                Volley.newRequestQueue(BookDetailsActivity.this).add(stringRequest);



            }
            if(position==4)
            {
                TextView et_origin_of_family,et_number_of_brothers,et_number_of_sisters,et_number_of_brothers_married,et_number_of_sisters_married,et_number_of_brothers_unmarried,et_number_of_sisters_unmarried;

                et_origin_of_family = view.findViewById(R.id.et_origin_of_family);
                et_number_of_brothers = view.findViewById(R.id.et_number_of_brothers);
                et_number_of_sisters = view.findViewById(R.id.et_number_of_sisters);
                et_number_of_brothers_married = view.findViewById(R.id.et_number_of_brothers_married);
                et_number_of_sisters_married = view.findViewById(R.id.et_number_of_sisters_married);
                et_number_of_brothers_unmarried = view.findViewById(R.id.et_number_of_brothers_unmarried);
                et_number_of_sisters_unmarried = view.findViewById(R.id.et_number_of_sisters_unmarried);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, ur,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray array = new JSONArray(response);
                                    for (int i = 0; i<array.length(); i++){
                                        JSONObject object = array.getJSONObject(i);
                                        et_origin_of_family.setText(object.getString("origin_family"));
                                        et_number_of_brothers.setText(object.getString("brothers"));
                                        et_number_of_sisters.setText(object.getString("sisters"));
                                        et_number_of_brothers_married.setText(object.getString("brothers_married"));
                                        et_number_of_sisters_married.setText(object.getString("sisters_married"));
                                        et_number_of_brothers_unmarried.setText(object.getString("brothers_unmarried"));
                                        et_number_of_sisters_unmarried.setText(object.getString("sisters_unmarried"));
                                    }
                                }catch (Exception e){
                                    System.out.println("here exception==>"+e.getMessage());
                                }
//                        mAdapter = new RecyclerAdapter(HomeActivity.this,products);
//                        recyclerView.setAdapter(mAdapter);

                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("pid", pid);
                        return params;

                    }
                };

                Volley.newRequestQueue(BookDetailsActivity.this).add(stringRequest);


            }
            if(position==5)
            {
                TextView et_hobbies,et_manglik,et_required_education,et_age_group_preference,et_required_height,et_required_weight;
                et_hobbies = view.findViewById(R.id.et_hobbies);
                et_manglik = view.findViewById(R.id.et_manglik);
                et_required_education = view.findViewById(R.id.et_required_education);
                et_age_group_preference = view.findViewById(R.id.et_age_group_preference);
                et_required_height = view.findViewById(R.id.et_required_height);
                et_required_weight = view.findViewById(R.id.et_required_weight);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, ur,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray array = new JSONArray(response);
                                    for (int i = 0; i<array.length(); i++){
                                        JSONObject object = array.getJSONObject(i);
                                        et_hobbies.setText(object.getString("hobbies"));
                                        et_manglik.setText(object.getString("manglik"));
                                        et_required_education.setText(object.getString("required_education"));
                                        et_age_group_preference.setText(object.getString("origin_family"));
                                        et_required_height.setText(object.getString("age_group"));
//                                        et_required_weight.setText(object.getString("origin_family"));


                                    }
                                }catch (Exception e){
                                    System.out.println("here exception==>"+e.getMessage());
                                }
//                        mAdapter = new RecyclerAdapter(HomeActivity.this,products);
//                        recyclerView.setAdapter(mAdapter);

                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("pid", pid);
                        return params;

                    }
                };

                Volley.newRequestQueue(BookDetailsActivity.this).add(stringRequest);


            }
            if(position==6)
            {
                TextView et_email_address,et_mobile_number,et_landline_number,et_residence_number,et_address_line1,et_address_line2;
                et_email_address = view.findViewById(R.id.et_email_address);
                et_mobile_number = view.findViewById(R.id.et_mobile_number);
                et_landline_number = view.findViewById(R.id.et_landline_number);
                et_residence_number = view.findViewById(R.id.et_residence_number);
                et_address_line1 = view.findViewById(R.id.et_address_line1);
                et_address_line2 = view.findViewById(R.id.et_address_line2);


                StringRequest stringRequest = new StringRequest(Request.Method.POST, ur,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray array = new JSONArray(response);
                                    for (int i = 0; i<array.length(); i++){
                                        JSONObject object = array.getJSONObject(i);
                                        et_email_address.setText(object.getString("mail_id"));
                                        et_mobile_number.setText(object.getString("mobile_number"));
                                        et_landline_number.setText(object.getString("land_line"));
                                        et_residence_number.setText(object.getString("resi_number"));
                                        et_address_line1.setText(object.getString("address1"));
                                        et_address_line2.setText(object.getString("address2"));


                                    }
                                }catch (Exception e){
                                    System.out.println("here exception==>"+e.getMessage());
                                }
//                        mAdapter = new RecyclerAdapter(HomeActivity.this,products);
//                        recyclerView.setAdapter(mAdapter);

                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("pid", pid);
                        return params;

                    }
                };

                Volley.newRequestQueue(BookDetailsActivity.this).add(stringRequest);


            }
            return view;
        }
        private void uploadPdfToServer()
        {
            retrofit2.Call<ResponsePOJO> call = RetrofitClient.getInstance().getAPI().uploadDocument(encodedpdf);
            call.enqueue(new retrofit2.Callback<ResponsePOJO>() {
                @Override
                public void onResponse( retrofit2.Call<ResponsePOJO> call, Response<ResponsePOJO> response) {

                    Toast.makeText(BookDetailsActivity.this, response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure( retrofit2.Call<ResponsePOJO> call, Throwable t) {
                    Toast.makeText(BookDetailsActivity.this, "Network Failed", Toast.LENGTH_SHORT).show();

                }
            });
//            Toast.makeText(NewActivity.this, "upppppp", Toast.LENGTH_SHORT).show();
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private void uploadretrofit(String strnm) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String image = convertToString();

// Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        String nm = sh.getString("name", "");



        retrofit2.Call<Img_Pojo> call = apiInterface.uploadImage(strnm,image,strnm,"peehu"+strnm,"90","Hindi",

                "study" ,"900",encodedpdf);
        call.enqueue(new Callback<Img_Pojo>() {
            @Override
            public void onResponse(Call<Img_Pojo> call, Response<Img_Pojo> response) {

                Img_Pojo img_pojo = response.body();
                Toast.makeText(BookDetailsActivity.this, img_pojo.getResponse(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Img_Pojo> call, Throwable t) {
                Log.d("Server Response",""+t.toString());


            }
        });


    }
//
//    private String getBookName() {
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
        if(requestCode== 100 && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);

                book_img.setImageBitmap(bitmap);
            } catch (IOException e) {
                System.out.println("err=="+e.getMessage());
            }
        }
        filePath = data.getData();

        if(requestCode==21 && resultCode==RESULT_OK && data!=null)
        {



            Uri path=data.getData();
            String uriString = path.toString();
            File myFile = new File(uriString);
            displayName = myFile.getName();
            tvchoose.setText(displayName);
            byte[] pdfinbytes= new byte[0];
            try {
                InputStream is=BookDetailsActivity.this.getContentResolver().openInputStream(path);
                pdfinbytes = new byte[is.available()];
                is.read(pdfinbytes);
                encodedpdf=Base64.encodeToString(pdfinbytes,Base64.DEFAULT);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        viewPager.setOnPageChangeListener(mPageChangeListener);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if(position==2)
                {

                    strname=tvname.getText().toString();
                    strdesc=tvdesc.getText().toString();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


}
