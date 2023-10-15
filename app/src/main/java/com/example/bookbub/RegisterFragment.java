package com.example.bookbub;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    Button btn_register;
    EditText mobno,edname,edpasswd;
    String name,no,passwd;
    private TextView[] dots;
    ViewGroup viewGroup;
    private int[] layouts;
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private Button btnSkip, btnNext;
    private RegisterFragment.MyViewPagerAdapter myViewPagerAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    char[] otp;

    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
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

//                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

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
    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
    //    private void launchHomeScreen() {
//       // prefManager.setFirstTimeLaunch(false);
//        startActivity(new Intent(NewActivity.this, NewActivity.class));
//        finish();
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View v=inflater.inflate(R.layout.fragment_register
                ,container,false);
        System.out.println("ghgh");
        viewPager = (ViewPager) v.findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) v.findViewById(R.id.layoutDots);
        btnSkip = (Button) v.findViewById(R.id.btn_skip);
        btnNext = (Button) v.findViewById(R.id.btn_next);
        layouts = new int[]{
                R.layout.publish_book_pg2_new,
                R.layout.publish_book_pg1_new,
                R.layout.publish_book_pg4_new,
                R.layout.publish_book_pg5_new,
                R.layout.publish_book_pg3_new,
                R.layout.publish_book_pg6_new, R.layout.publish_book_pg7_new};

        addBottomDots(0);
        changeStatusBarColor();
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // launchHomeScreen();
//                if (v == buttonChoose) {
////                    showFileChooser();
//                }
//
//                if(v == buttonUpload){
//                }

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

        viewGroup = v.findViewById(android.R.id.content);
//        btn_register.setOnClickListener(this);
        return v;
        //return inflater.inflate(R.layout.fragment_register, container, false);
    }
    @Override
    public void onClick(View view) {

    registerUser();
































        //OTP
//        Random random = new Random();
//        otp = new char[4];
//        for (int i=0; i<4; i++)
//        {
//            otp[i]= (char)(random.nextInt(10)+48);
//        }
//
////        Toast.makeText(getApplicationContext(), String.valueOf(otp), Toast.LENGTH_SHORT).show();
//
//        String number  = mobno.getText().toString();
//
//        if(!(mobno.getText().toString().equals(""))) {
//            Toast.makeText(getContext(), "un1", Toast.LENGTH_SHORT).show();
//            new MyAsyncTask(view).execute("https://api.msg91.com/api/sendhttp.php?route=4&sender=TESTIN&message=OTP for your OTP Verification App is : "+String.valueOf(otp)+"&country=91&flash=&unicode=&mobiles="+number+"&authkey=297116AFCGQdLuvdm25d96f3f7");
//        }else{
//            Toast.makeText(getContext(), "un2", Toast.LENGTH_SHORT).show();
//
//            mobno.setError("Please Enter your mobile number");
//        }

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
            layoutInflater = (LayoutInflater)  getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);

            container.addView(view);
            if(position == 0)
            {

                //   Toast.makeText(NewActivity.this, "opopopopopooooo1", Toast.LENGTH_SHORT).show();
//                profile_image=view.findViewById(R.id.profile_image);
                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.bookcover2)
                        .error(R.drawable.bookcover2);

//                Glide.with(RegisterFragment.this.getContext()).load(path).apply(options).into(profile_image);

//                profile_image.se
//                english=view.findViewById(R.id.english);
//                gujarati=view.findViewById(R.id.gujarati);
//                tamil=view.findViewById(R.id.tamil);
//                cardhindi=view.findViewById(R.id.cardhindi);
//                cardenglish=view.findViewById(R.id.cardenglish);
//                cardgujarati=view.findViewById(R.id.cardgujarati);
//                cardtamil=view.findViewById(R.id.cardtamil);


//                cardhindi.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        hindi.setTextColor(getResources().getColor(R.color.white));
//                        cardhindi.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        english.setTextColor(getResources().getColor(R.color.startblue));
//                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
//                        gujarati.setTextColor(getResources().getColor(R.color.startblue));
//                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.white));
//                        tamil.setTextColor(getResources().getColor(R.color.white));
//                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
//                    }
//                });
//                cardenglish.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        english.setTextColor(getResources().getColor(R.color.white));
//                        cardenglish.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        gujarati.setTextColor(getResources().getColor(R.color.startblue));
//                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.white));
//                        hindi.setTextColor(getResources().getColor(R.color.startblue));
//                        cardhindi.setBackgroundColor(getResources().getColor(R.color.white));
//                        tamil.setTextColor(getResources().getColor(R.color.white));
//                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        english.setTextColor(getResources().getColor(R.color.startblue));
//                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
//                        //selected="english";
//                    }
//                });
//                cardgujarati.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        gujarati.setTextColor(getResources().getColor(R.color.white));
//                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        //selected="english";
//                        hindi.setTextColor(getResources().getColor(R.color.startblue));
//                        cardhindi.setBackgroundColor(getResources().getColor(R.color.white));
//                        tamil.setTextColor(getResources().getColor(R.color.white));
//                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        english.setTextColor(getResources().getColor(R.color.startblue));
//                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
//                    }
//                });
//                cardtamil.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        tamil.setTextColor(getResources().getColor(R.color.white));
//                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        english.setTextColor(getResources().getColor(R.color.startblue));
//                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
//                        gujarati.setTextColor(getResources().getColor(R.color.startblue));
//                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.white));
//                        hindi.setTextColor(getResources().getColor(R.color.startblue));
//                        cardhindi.setBackgroundColor(getResources().getColor(R.color.white));
//                        //selected="english";
//                    }
//                });
//
//
//
//
//
//                TextView txt_choose=view.findViewById(R.id.txt_choose);
//                txt_choose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                    }
//                });
            }
            if(position == 1)
            {
                TextView txt_choose=view.findViewById(R.id.txt_choose);
//                TextView scifi,horror,comedy,study;
//                scifi=view.findViewById(R.id.scifi);
//                horror=view.findViewById(R.id.horror);
//                comedy=view.findViewById(R.id.comedy);
//                study=view.findViewById(R.id.study);
//                cardscifi=view.findViewById(R.id.cardscifi);
//                cardhorror=view.findViewById(R.id.cardcomedy);
////                cardstudy=view.findViewById(R.id.cardstudy);ew.findViewById(R.id.cardhorror);
//                cardcomedy=vi
                txt_choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

//
//                cardhorror.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        horror.setTextColor(getResources().getColor(R.color.white));
//                        cardhorror.setBackgroundColor(getResources().getColor(R.color.startblue));
//
//                    }
//                });
//                cardcomedy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        comedy.setTextColor(getResources().getColor(R.color.white));
//                        cardcomedy.setBackgroundColor(getResources().getColor(R.color.startblue));
//
//                        //selected="english";
//                    }
//                });
//                cardstudy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        study.setTextColor(getResources().getColor(R.color.white));
//                        cardstudy.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        //selected="english";
//                    }
//                });
//                cardscifi.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        scifi.setTextColor(getResources().getColor(R.color.white));
//                        cardscifi.setBackgroundColor(getResources().getColor(R.color.startblue));
//
//                        //selected="english";
//                    }
//                });
//



            }
            if(position == 2)
            {
//                tvname=findViewById(R.id.name);
//                tvdesc=findViewById(R.id.desc);
//                tvname.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        strnm=tvname.getText().toString();
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable editable) {
//
//                    }
//                });

            }

            if(position==3){
//                View v2=layoutInflater.inflate(R.layout.make_cover,null,false);
//                bookname=v2.findViewById(R.id.bookname);
//                desc=v2.findViewById(R.id.desc);
//                authorname=v2.findViewById(R.id.authorname);
//                bookname.setText(strname);
//                desc.setText(strdesc);
//                btn_login= view.findViewById(R.id.btn_login);
//                btn_login.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // uploadImage();
//                        //  uploadretrofit();
//                        selectImage();
//                    }
//                });
//                authorname.setText("Peehu");
////                book_img=view.findViewById(R.id.book_img);
//                rv=v2.findViewById(R.id.rv);
//                book_img.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        selectImage();
//                    }
//                });
//                View v=layoutInflater.inflate(R.layout.make_cover,null,false);
//                TextView bn=v.findViewById(R.id.bookname);
//                TextView d=v.findViewById(R.id.desc);
//                if(strname!=null)Toast.makeText(NewActivity.this, "val=="+strname, Toast.LENGTH_SHORT).show();
//                v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
//               // bn.setText(tvname.getText().toString());
//                //d.setText(tvdesc.getText().toString());
//                Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
//                Canvas c = new Canvas(b);
//                v.draw(c);
            }
            if(position==4)
            {
//                bt=view.findViewById(R.id.bt);
//                System.out.println("here="+booknm);
//                tvchoose=view.findViewById(R.id.tvchoose);
//                btnchoose=view.findViewById(R.id.btnchoose);
//                bt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        uploadretrofit(strnm);
//                        Toast.makeText(BookDetailsActivity.this, "Book Uploaded", Toast.LENGTH_SHORT).show();
//                        Intent i=new Intent(getApplicationContext(),AllBooks.class);
//                        startActivity(i);
//
//
//                    }
//                });
//                btnchoose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//
//                        Intent choosefile = new Intent(Intent.ACTION_GET_CONTENT);
//                        choosefile.setType("application/pdf");
//                        choosefile = Intent.createChooser(choosefile, "Choose a file");
//                        startActivityForResult(choosefile, 21);
//                        bt.setVisibility(View.VISIBLE);
//                    }
//                });

            }
            if(position==5)
            {
//                bt=view.findViewById(R.id.bt);
//                System.out.println("here="+booknm);
//                tvchoose=view.findViewById(R.id.tvchoose);
//                btnchoose=view.findViewById(R.id.btnchoose);
//                bt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        uploadretrofit(strnm);
//                        Toast.makeText(BookDetailsActivity.this, "Book Uploaded", Toast.LENGTH_SHORT).show();
//                        Intent i=new Intent(getApplicationContext(),AllBooks.class);
//                        startActivity(i);
//
//
//                    }
//                });
//                btnchoose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//
//                        Intent choosefile = new Intent(Intent.ACTION_GET_CONTENT);
//                        choosefile.setType("application/pdf");
//                        choosefile = Intent.createChooser(choosefile, "Choose a file");
//                        startActivityForResult(choosefile, 21);
//                        bt.setVisibility(View.VISIBLE);
//                    }
//                });

            }
            if(position==6)
            {
                btn_register=view.findViewById(R.id.btn_register);
                btn_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                    }
                });
//                System.out.println("here="+booknm);
//                tvchoose=view.findViewById(R.id.tvchoose);
//                btnchoose=view.findViewById(R.id.btnchoose);
//                bt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        uploadretrofit(strnm);
//                        Toast.makeText(BookDetailsActivity.this, "Book Uploaded", Toast.LENGTH_SHORT).show();
//                        Intent i=new Intent(getApplicationContext(),AllBooks.class);
//                        startActivity(i);
//
//
//                    }
//                });
//                btnchoose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//
//                        Intent choosefile = new Intent(Intent.ACTION_GET_CONTENT);
//                        choosefile.setType("application/pdf");
//                        choosefile = Intent.createChooser(choosefile, "Choose a file");
//                        startActivityForResult(choosefile, 21);
//                        bt.setVisibility(View.VISIBLE);
//                    }
//                });

            }
            return view;
        }
//        private void uploadPdfToServer()
//        {
//            retrofit2.Call<ResponsePOJO> call = RetrofitClient.getInstance().getAPI().uploadDocument(encodedpdf);
//            call.enqueue(new retrofit2.Callback<ResponsePOJO>() {
//                @Override
//                public void onResponse( retrofit2.Call<ResponsePOJO> call, retrofit2.Response<ResponsePOJO> response) {
//
//                    Toast.makeText(RegisterFragment.this.getContext(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure( retrofit2.Call<ResponsePOJO> call, Throwable t) {
//                    Toast.makeText(RegisterFragment.this.getContext(), "Network Failed", Toast.LENGTH_SHORT).show();
//
//                }
//            });
////            Toast.makeText(NewActivity.this, "upppppp", Toast.LENGTH_SHORT).show();
//        }

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

    private void registerUser() {
        name=edname.getText().toString();
        no=mobno.getText().toString();
        passwd=edpasswd.getText().toString();
        String regurl="https://myimon.000webhostapp.com/register_book_user.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, regurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                       // progressBar.setVisibility(View.GONE);
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");
                                //creating a new user object
                                User user = new User(
                                        userJson.getString("no"),
                                        userJson.getString("passwd"),
                                        userJson.getString("name"),0,0
                                );
                                if(response=="1") {
                                    Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(getActivity(),NewActivity.class);
                                    startActivity(i);
                                }
                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getContext()).userLogin(user);

                                //starting the profile activity
                                getActivity().finish();
                                startActivity(new Intent(getContext(), MainActivity.class));
                            }
                        } catch (Exception e)
                        {
                            System.out.println("error---"+e.getMessage());
                           // Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("no", no);
                params.put("passwd", passwd);
                return params;
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
    class MyAsyncTask extends AsyncTask<String, String, String>
    {
        View v;
        URL Url;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream;
        MyAsyncTask(View v){this.v=v;}
        protected String doInBackground(String... urls)
        {
            try {
                Url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection)Url.openConnection();
                inputStream = httpURLConnection.getInputStream();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            openDialog(v);
        }
        }
    private void openDialog(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_layout, viewGroup, false);
        builder.setView(dialogView);
        Button verify=dialogView.findViewById(R.id.verify);
        EditText otp_text=dialogView.findViewById(R.id.otp_text);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        boolean checkOtpNumber = TextUtils.isEmpty(otp_text.getText().toString());
        if(checkOtpNumber == false){
            if(otp_text.equals(otp_text.getText().toString())){
                otp_text.setVisibility(View.INVISIBLE);
                verify.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Verification Successful!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "OTP does not matches!", Toast.LENGTH_SHORT).show();
            }
        }else{
            otp_text.setError("Please Enter OTP First!");
        }
    }
}