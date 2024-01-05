package com.example.bookbub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutMe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutMe extends Fragment {
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
    SessionManager session;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutMe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutMe.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutMe newInstance(String param1, String param2) {
        AboutMe fragment = new AboutMe();
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
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.activity_new, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        viewPager = (ViewPager) v.findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) v.findViewById(R.id.layoutDots);
        btnSkip = (Button) v.findViewById(R.id.btn_skip);
        btnNext = (Button) v.findViewById(R.id.btn_next);
        btnSkip.setVisibility(View.GONE);
        session=new SessionManager(getContext());
        pid=session.getProfile();
        path=session.getPhysical();
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


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                    viewPager.getAdapter().notifyDataSetChanged();

                }
//                else {
//                    launchHomeScreen();
//                }
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(-1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                }
//                else {
//                    launchHomeScreen();
//                }
            }
        });

        return v;
    }
    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnNext.setVisibility(View.GONE);
            }
            else
            {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);

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
                btnSkip.setVisibility(View.GONE);

            }
            if (position == 3) {

                LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    private void addBottomDots(int currentPage)
    {
        dots = new TextView[layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
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
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);

            container.addView(view);
            if(position == 0)
            {


                profile_image=view.findViewById(R.id.profile_image);
                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.bookcover2)
                        .error(R.drawable.bookcover2);

                Glide.with(getContext()).load(path).apply(options).into(profile_image);

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
                System.out.println("here exception111==>"+ur+" ppii=="+path);
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
                                        et_gender.setText((object.getString("gender")=="M" ||object.getString("gender").equalsIgnoreCase("Male" ))?"Male":"Female");
                                        et_dob.setText(object.getString("dob_place"));
                                        et_place_of_birth.setText(object.getString("dob_time"));
                                        et_profile_status.setText(object.getString("profile_status"));
                                    }
                                }catch (Exception e){
                                    System.out.println("here exception111==>"+e.getMessage());
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

                Volley.newRequestQueue(getContext()).add(stringRequest);



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

                Volley.newRequestQueue(getContext()).add(stringRequest);



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

                Volley.newRequestQueue(getContext()).add(stringRequest);



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

                Volley.newRequestQueue(getContext()).add(stringRequest);


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

                Volley.newRequestQueue(getContext()).add(stringRequest);


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

                Volley.newRequestQueue(getContext()).add(stringRequest);


            }
            return view;
        }
        private void uploadPdfToServer()
        {
            retrofit2.Call<ResponsePOJO> call = RetrofitClient.getInstance().getAPI().uploadDocument(encodedpdf);
            call.enqueue(new retrofit2.Callback<ResponsePOJO>() {
                @Override
                public void onResponse( retrofit2.Call<ResponsePOJO> call, Response<ResponsePOJO> response) {


                    Toast.makeText(getContext(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure( retrofit2.Call<ResponsePOJO> call, Throwable t) {
                    Toast.makeText(getContext(), "Network Failed", Toast.LENGTH_SHORT).show();

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
    }