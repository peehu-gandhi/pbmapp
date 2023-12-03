package com.example.bookbub;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.provider.MediaStore;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
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
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    Button btn_register;
    Uri filePath;
    EditText mobno,edname,edpasswd;
    String name,no,passwd;
    private TextView[] dots;
    ViewGroup viewGroup;
    private int[] layouts;
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private Button btnSkip, btnNext;
    Spinner et_gender,et_profile_status;
    Button displayDate,btn_aadhar_pan;
    private NumberPicker picker1;
    private Bitmap bitmap,bitmap_aadhar,bitmap_edu_quali,bitmap_income_proof,bitmap_proof_2000
            ;
    ImageView aadhar_pan,proof_2000,income_proof,edu_quali;
    private RegisterFragment.MyViewPagerAdapter myViewPagerAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    char[] otp;

    String[] gender_items = {"Male", "Female", "NA"};
    String [] profile_status_items={"Single","Engaged","Divorcee"};
    String [] spectacles_items={"No","Yes"};
    String [] complex_items={"Fair","Very Fair","Weatish","Dark"};
    private String mParam1;
    private String mParam2;
Button btn_login,btn_proof_2000,btn_income_proof,btn_edu_quali;;
    ImageView book_img;
    EditText et_first_name,et_middle_name,et_last_name,et_place_of_birth,et_edu,et_occ,et_income,et_origin_of_family, et_hobbies,et_required_education,et_email_address,et_mobile_number,et_landline_number,et_residence_number,et_address_line1,et_address_line2;
    ;
    public RegisterFragment() {
    }
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://docs.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    String first_name, middle_name, last_name, gender, dob, place_of_birth, profile_image, height, str_weight, complexion, str_spectacles, physial_handicap, education, occupation, income, origin_of_family, no_of_brothers, no_of_sisters, no_of_brothers_married, no_of_sisters_married, no_of_brothers_unmarried, no_of_sisters_unmarried, hobbies, manglik, required_education_of_partner, age_group_preference_for_partner, required_height_of_partner, required_weight_of_partner, email_address, mobile_number, landline_number, residence_number, address_line_1, address_line_2, aadhar_card_or_pan_card, latest_education_qualification, string_income_proof, fee_submitted_2000_proof,profile_status;
    {
        first_name = middle_name = last_name = gender = dob = place_of_birth = profile_image = height = str_weight = complexion = str_spectacles = physial_handicap = education = occupation = income = origin_of_family = no_of_brothers = no_of_sisters = no_of_brothers_married = no_of_sisters_married = no_of_brothers_unmarried = no_of_sisters_unmarried = hobbies = manglik = required_education_of_partner = age_group_preference_for_partner = required_height_of_partner = required_weight_of_partner = email_address = mobile_number = landline_number = residence_number = address_line_1 = address_line_2 = aadhar_card_or_pan_card = latest_education_qualification = string_income_proof = fee_submitted_2000_proof =profile_status= "";
    }
    private GoogleFormService api = retrofit.create(GoogleFormService.class);


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
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
            if(position!=0)
            {
            }

            if (position == 2) {
            }
            if(position == 0)
            {

            }
            if (position == 3) {


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
        return viewPager.getCurrentItem() ;
    }
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
        View v=inflater.inflate(R.layout.fragment_register
                ,container,false);
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
                R.layout.publish_book_pg6_new, R.layout.publish_book_pg7_new,
                R.layout.publish_book_pg8_new,
                R.layout.publish_book_pg9_new,
                R.layout.publish_book_pg10_new,
                R.layout.publish_book_pg11_new};

        addBottomDots(0);
        changeStatusBarColor();
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        viewPager.setOffscreenPageLimit(layouts.length - 1);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            }
        });

        viewGroup = v.findViewById(android.R.id.content);
        return v;
    }
    @Override
    public void onClick(View view) {
    registerUser();
    }
    public class MyViewPagerAdapter extends PagerAdapter {



        private LayoutInflater layoutInflater;
        public MyViewPagerAdapter() {
        }
        @Override
        public void startUpdate(@NonNull ViewGroup container) {
            super.startUpdate(container);
        }
        @SuppressLint("WrongViewCast")
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)  getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);

            container.addView(view);
            if(position == 0)
            {
                et_first_name= view.findViewById(R.id.et_first_name);
                first_name=et_first_name.getText().toString();
                et_middle_name= view.findViewById(R.id.et_middle_name);
                middle_name=et_middle_name.getText().toString();
                et_last_name=view.findViewById(R.id.et_last_name);
                last_name=et_last_name.getText().toString();
                et_place_of_birth=view.findViewById(R.id.et_place_of_birth);
                place_of_birth=et_place_of_birth.getText().toString();
                et_gender =(Spinner) view.findViewById(R.id.et_gender);
                if(et_gender.getSelectedItem()!=null)
                gender=et_gender.getSelectedItem().toString();
//                instantiatePersonal();

//                picker=(Button)view.findViewById(R.id.et_dob);
                displayDate=(Button)view.findViewById(R.id.button1);

                et_profile_status=(Spinner) view.findViewById(R.id.et_profile_status);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, gender_items);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                et_gender.setAdapter(adapter);

                et_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        String selectedValue = gender_items[position];
                        // Do something with the selected value
                        gender= et_gender.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, profile_status_items);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                et_profile_status.setAdapter(adapter2);
                et_profile_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        String selectedValue = gender_items[position];
                        profile_status= et_profile_status.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // Do nothing here
                    }
                });



                displayDate.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        final Calendar c = Calendar.getInstance();

                        // on below line we are getting
                        // our day, month and year.
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                // on below line we are passing context.
                                getContext(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        // on below line we are setting date to our text view.
                                        displayDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    }
                                },
                                // on below line we are passing year,
                                // month and day for selected date in our date picker.
                                year, month, day);
                        // at last we are calling show to
                        // display our date picker dialog.
                        datePickerDialog.show();

                    }

                });

                //   Toast.makeText(NewActivity.this, "opopopopopooooo1", Toast.LENGTH_SHORT).show();
//                profile_image=view.findViewById(R.id.profile_image);
                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.bookcover2)
                        .error(R.drawable.bookcover2);

            }
            if(position == 1)
            {
                btn_login =(Button) view.findViewById(R.id.btn_login);
                book_img=(ImageView) view.findViewById(R.id.profile_image);

                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage(1);
                    }
                });
                book_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage(1);
                    }
                });
            }
            if(position == 2)
            {
                NumberPicker feetPicker = view.findViewById(R.id.et_height_feet);
                NumberPicker inchPicker = view.findViewById(R.id.et_height_inch);
                Spinner complex=view.findViewById(R.id.et_complexion);
                Spinner spectacles=view.findViewById(R.id.et_spectacles);
                Spinner phyical=view.findViewById(R.id.et_physical_handicap);

                Slider weight = view.findViewById(R.id.et_weight);
                weight.setValueFrom(0);
                weight.setValueTo(300);
                weight.setStepSize(1);


                feetPicker.setMinValue(0);
                feetPicker.setMaxValue(10);

                inchPicker.setMinValue(0);
                inchPicker.setMaxValue(11); // 0 to 11 inches
                feetPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        height =feetPicker.getValue()+"'"+" ";
                    }
                });
                inchPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        height+=inchPicker.getValue()+"''";
                    }
                });
                weight.addOnChangeListener(new Slider.OnChangeListener() {
                    @Override
                    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                        str_weight =slider.getValue()+" kgs";

                    }
                });


                ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, complex_items);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                complex.setAdapter(adapter3);

                complex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        String selectedValue = complex_items[position];
                        String data= complex.getItemAtPosition(position).toString();
                        complexion=selectedValue;

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

                ArrayAdapter<String> adapter4 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spectacles_items);
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spectacles.setAdapter(adapter4);

                spectacles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        String selectedValue = spectacles_items[position];
                        String data= spectacles.getItemAtPosition(position).toString();
                        str_spectacles=selectedValue;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // Do nothing here
                    }
                });


                ArrayAdapter<String> adapter5 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spectacles_items);
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                phyical.setAdapter(adapter5);

                phyical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        String selectedValue = spectacles_items[position];
                        // Do something with the selected value
                        String data= phyical.getItemAtPosition(position).toString();
                        physial_handicap=selectedValue;


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // Do nothing here
                    }
                });

            }
            if(position==3)
            {
                et_edu=view.findViewById(R.id.et_edu);
                et_occ=view.findViewById(R.id.et_occ);
                et_income=view.findViewById(R.id.et_income);
                education=et_edu.getText().toString();
                occupation=et_occ.getText().toString();
                income=et_income.getText().toString();

            }
            if(position==4)
            {
                et_origin_of_family= view.findViewById(R.id.et_origin_of_family);

                Slider et_number_of_brothers = view.findViewById(R.id.et_number_of_brothers);
                et_number_of_brothers.setValueFrom(0);
                et_number_of_brothers.setValueTo(20);
                et_number_of_brothers.setStepSize(1);

                Slider et_number_of_sisters = view.findViewById(R.id.et_number_of_sisters);
                et_number_of_sisters.setValueFrom(0);
                et_number_of_sisters.setValueTo(20);
                et_number_of_sisters.setStepSize(1);

                Slider et_number_of_sisters_married = view.findViewById(R.id.et_number_of_sisters_married);
                et_number_of_sisters_married.setValueFrom(0);
                et_number_of_sisters_married.setValueTo(20);
                et_number_of_sisters_married.setStepSize(1);

                Slider et_number_of_brothers_married = view.findViewById(R.id.et_number_of_brothers_married);
                et_number_of_brothers_married.setValueFrom(0);
                et_number_of_brothers_married.setValueTo(20);
                et_number_of_brothers_married.setStepSize(1);

                Slider et_number_of_sisters_unmarried = view.findViewById(R.id.et_number_of_sisters_unmarried);
                et_number_of_sisters_unmarried.setValueFrom(0);
                et_number_of_sisters_unmarried.setValueTo(20);
                et_number_of_sisters_unmarried.setStepSize(1);

                Slider et_number_of_brothers_unmarried = view.findViewById(R.id.et_number_of_brothers_unmarried);
                et_number_of_brothers_unmarried.setValueFrom(0);
                et_number_of_brothers_unmarried.setValueTo(20);
                et_number_of_brothers_unmarried.setStepSize(1);
                et_number_of_brothers.addOnChangeListener(new Slider.OnChangeListener() {
                    @Override
                    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                        no_of_brothers=String.valueOf(slider.getValue());

                    }
                });
                et_number_of_sisters.addOnChangeListener(new Slider.OnChangeListener() {
                    @Override
                    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                        no_of_sisters=String.valueOf(slider.getValue());

                    }
                });
                et_number_of_brothers_married.addOnChangeListener(new Slider.OnChangeListener() {
                    @Override
                    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                        no_of_brothers_married=String.valueOf(slider.getValue());

                    }
                });
                et_number_of_brothers_unmarried.addOnChangeListener(new Slider.OnChangeListener() {
                    @Override
                    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                        no_of_brothers_unmarried=String.valueOf(slider.getValue());

                    }
                });
                et_number_of_sisters_married.addOnChangeListener(new Slider.OnChangeListener() {
                    @Override
                    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                        no_of_sisters_married=String.valueOf(slider.getValue());

                    }
                });
                et_number_of_sisters_unmarried.addOnChangeListener(new Slider.OnChangeListener() {
                    @Override
                    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                        no_of_sisters_unmarried=String.valueOf(slider.getValue());

                    }
                });



                origin_of_family=et_origin_of_family.getText().toString();

            }
            if(position==5)
            {et_hobbies= view.findViewById(R.id.et_hobbies);
                et_required_education=view.findViewById(R.id.et_required_education);
                    Spinner et_manglik = view.findViewById(R.id.et_manglik);
                    NumberPicker feetPickerMin=view.findViewById(R.id.feetPickerMin);
                NumberPicker inchPickerMin=view.findViewById(R.id.inchPickerMin);
                NumberPicker feetPickerMax=view.findViewById(R.id.feetPickerMax);
                NumberPicker inchPickerMax=view.findViewById(R.id.inchPickerMax);
                RangeSlider et_age_grp=view.findViewById(R.id.et_age_group_preference);
                et_age_grp.addOnChangeListener(new RangeSlider.OnChangeListener() {
                    @Override
                    public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                        age_group_preference_for_partner=slider.getValues().get(0)+" to " +slider.getValues().get(1);

                    }
                });

                hobbies=et_hobbies.getText().toString();
                required_education_of_partner=et_required_education.getText().toString();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spectacles_items);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                inchPickerMin.setMinValue(0);
                inchPickerMin.setMaxValue(11);
                inchPickerMax.setMinValue(0);
                inchPickerMax.setMaxValue(11);
                feetPickerMin.setMinValue(0);
                feetPickerMin.setMaxValue(10);
                feetPickerMax.setMinValue(0);
                feetPickerMax.setMaxValue(10);
                et_manglik.setAdapter(adapter);

                et_manglik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        String selectedValue = spectacles_items[position];
                        String data= et_manglik.getItemAtPosition(position).toString();
                        manglik=selectedValue;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });
                feetPickerMin.setOnScrollListener(new NumberPicker.OnScrollListener() {
                    @Override
                    public void onScrollStateChange(NumberPicker picker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            // The user has stopped scrolling, handle the selected value
                            required_height_of_partner=String.valueOf(picker.getValue())+"'";
                        }
                    }
                });
                inchPickerMin.setOnScrollListener(new NumberPicker.OnScrollListener() {
                    @Override
                    public void onScrollStateChange(NumberPicker picker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            // The user has stopped scrolling, handle the selected value
                            required_height_of_partner+=String.valueOf(picker.getValue())+"''"+" to ";
                        }
                    }
                });

                feetPickerMax.setOnScrollListener(new NumberPicker.OnScrollListener() {
                    @Override
                    public void onScrollStateChange(NumberPicker picker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            // The user has stopped scrolling, handle the selected value
                            required_height_of_partner+=String.valueOf(picker.getValue())+"'";
                        }
                    }
                });
                inchPickerMax.setOnScrollListener(new NumberPicker.OnScrollListener() {
                    @Override
                    public void onScrollStateChange(NumberPicker picker, int scrollState) {
                        if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                            // The user has stopped scrolling, handle the selected value
                            required_height_of_partner+=String.valueOf(picker.getValue())+"''";
                        }
                    }
                });



                RangeSlider et_required_weight=view.findViewById(R.id.et_required_weight);
                et_required_weight.addOnChangeListener(new RangeSlider.OnChangeListener() {
                    @Override
                    public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                        required_weight_of_partner=slider.getValues().get(0)+" to "+slider.getValues().get(1);
                    }
                });

            }

            if(position==6)
            {
                et_email_address=view.findViewById(R.id.et_email_address);
                et_mobile_number=view.findViewById(R.id.et_mobile_number);
                et_landline_number=view.findViewById(R.id.et_landline_number);
                et_residence_number=view.findViewById(R.id.et_residence_number);
                et_address_line1=view.findViewById(R.id.et_address_line1);
                et_address_line2=view.findViewById(R.id.et_address_line2);
                email_address=et_email_address.getText().toString();
                mobile_number=et_mobile_number.getText().toString();
                landline_number=et_landline_number.getText().toString();
                residence_number=et_residence_number.getText().toString();
                address_line_1=et_address_line1.getText().toString();
                address_line_2=et_address_line2.getText().toString();



            }

            if(position==7)
            {
                btn_aadhar_pan =(Button) view.findViewById(R.id.btn_aadhar_pan);
                aadhar_pan=(ImageView) view.findViewById(R.id.aadhar_pan);

                btn_aadhar_pan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage(2);
                    }
                });
                aadhar_pan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage(2);
                    }
                });


            }

            if(position==8)
            {
                btn_edu_quali =(Button) view.findViewById(R.id.btn_edu_quali);
                edu_quali=(ImageView) view.findViewById(R.id.edu_quali);

                btn_aadhar_pan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage(3);
                    }
                });
                edu_quali.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage(3);
                    }
                });



            }

            if(position==9)
            {
                btn_income_proof =(Button) view.findViewById(R.id.btn_income_proof);
                income_proof=(ImageView) view.findViewById(R.id.income_proof);

                btn_income_proof.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage(4);
                    }
                });
                income_proof.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage(4);
                    }
                });



            }

            if(position==10)
            {
                btn_register=(Button) view.findViewById(R.id.btn_register);
                btn_proof_2000 =(Button) view.findViewById(R.id.btn_proof_2000);
                proof_2000=(ImageView) view.findViewById(R.id.proof_2000);
                btn_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        registerUser();
                    }
                });
                btn_proof_2000.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage(5);
                    }
                });
                proof_2000.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage(5);
                    }
                });


            }

            return view;
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

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }
    }


    private String convertToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        bitmap_aadhar.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        bitmap_edu_quali.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        bitmap_income_proof.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        bitmap_proof_2000.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }
    private void selectImage(int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, requestCode);
    }

    private void registerUser() {








//        name=edname.getText().toString();
//        no=mobno.getText().toString();
//        passwd=edpasswd.getText().toString();
        String regurl="https://pbmabad.000webhostapp.com/InsertIntoPendingTable.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, regurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        try{
                            if(response.equals("Data inserted successfully!"))
                            {
                                Toast.makeText(getContext(),"We will get back to you to confirm your registration",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getContext(),"Internal Server Error",Toast.LENGTH_LONG).show();

                            }
                        }catch(Exception e)
                        {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("first_name", et_first_name.getText().toString());
                params.put("middle_name",et_middle_name.getText().toString());
                params.put("last_name", et_last_name.getText().toString());
                String cgender=(et_gender.getSelectedItem()!=null)?et_gender.getSelectedItem().toString():"";
                params.put("gender", cgender);
                params.put("dob", displayDate.getText().toString());
                params.put("place_of_birth", et_place_of_birth.getText().toString());
                params.put("profile_image", "https://pbmabad.000webhostapp.com/imgs/boys.png");
                params.put("height", height);
                params.put("weight", str_weight);
                params.put("complexion", complexion);
                params.put("spectacles", str_spectacles);
                params.put("physial_handicap", physial_handicap);
                params.put("education", et_edu.getText().toString());
                params.put("occupation", et_occ.getText().toString());
                params.put("income", et_income.getText().toString());
                params.put("origin_of_family", et_origin_of_family.getText().toString());
                params.put("no_of_brothers", no_of_brothers);
                params.put("no_of_sisters", no_of_sisters);
                params.put("no_of_brothers_married", no_of_brothers_married);
                params.put("no_of_sisters_married", no_of_sisters_married);
                params.put("no_of_brothers_unmarried", no_of_brothers_unmarried);
                params.put("no_of_sisters_unmarried", no_of_sisters_unmarried);
                params.put("hobbies", et_hobbies.getText().toString());
                params.put("manglik", manglik);
                params.put("required_education_of_partner", et_required_education.getText().toString());
                params.put("age_group_preference_for_partner", age_group_preference_for_partner);
                params.put("required_height_of_partner", required_height_of_partner);
                params.put("required_weight_of_partner", required_weight_of_partner);
                params.put("email_address", et_email_address.getText().toString());
                params.put("mobile_number", et_mobile_number.getText().toString());
                params.put("landline_number", et_landline_number.getText().toString());
                params.put("residence_number", et_residence_number.getText().toString());
                params.put("address_line_1", et_address_line1.getText().toString());
                params.put("address_line_2", et_address_line2.getText().toString());
                params.put("aadhar_card_or_pan_card", "https://pbmabad.000webhostapp.com/imgs/boys.png");
                params.put("latest_education_qualification", "https://pbmabad.000webhostapp.com/imgs/boys.png");
                params.put("income_proof", "https://pbmabad.000webhostapp.com/imgs/boys.png");
                params.put("fee_submitted_2000_proof", "https://pbmabad.000webhostapp.com/imgs/boys.png");
                params.put("profile_status", profile_status);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //  if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
        if(requestCode== 1 && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),path);

                book_img.setImageBitmap(bitmap);

            } catch (IOException e) {
                System.out.println("err=="+e.getMessage());
            }
        }
        if(requestCode== 2 && resultCode==RESULT_OK && data!=null){
            Uri path = data.getData();
            try {
                bitmap_aadhar = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),path);

                aadhar_pan.setImageBitmap(bitmap_aadhar);
        } catch (IOException e) {
        System.out.println("err=="+e.getMessage());
    }
        }
        if(requestCode==3 && resultCode==RESULT_OK && data!=null){
            Uri path = data.getData();

            try {
                bitmap_edu_quali = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),path);
                edu_quali.setImageBitmap(bitmap_edu_quali);
    } catch (IOException e) {
        System.out.println("err=="+e.getMessage());
    }

        }
        if(requestCode==4 && resultCode==RESULT_OK && data!=null){
            Uri path = data.getData();

            try {

                bitmap_income_proof = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),path);

                income_proof.setImageBitmap(bitmap_income_proof);
                } catch (IOException e) {
                System.out.println("err=="+e.getMessage());
                }

        }
        if(requestCode==5 && resultCode==RESULT_OK && data!=null){
            Uri path = data.getData();

            try {

                bitmap_proof_2000 = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),path);
                proof_2000.setImageBitmap(bitmap_proof_2000);
                } catch (IOException e) {
                System.out.println("err=="+e.getMessage());
                }

        }
        filePath = data.getData();



//        viewPager.setOnPageChangeListener(mPageChangeListener);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {

//                instantiatePersonal();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}