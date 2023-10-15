package com.example.bookbub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookbub.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewActivity extends AppCompatActivity
//
//    //for inserting new book
//    public static final String UPLOAD_URL = "https://myimon.000webhostapp.com/insert_new_book.php";
//    public static final String UPLOAD_KEY = "image";
//        TextView hindi,tamil,gujarati,english;
//    CardView cardtamil,cardhindi,cardenglish,cardgujarati;
//    CardView cardscifi,cardhorror,cardcomedy,cardstudy;
//
//
//    Uri filePath;
//    private int PICK_IMAGE_REQUEST = 1;
//    private LinearLayout btnchoose;
//    private Button buttonChoose;
//    private Button buttonUpload;
//    private Button buttonView;
//    private TextView tvchoose;
//    private ImageView imageView;
//String booknm;
//    private Bitmap bitmap;
//    Button bt;
//    String displayName = null;
//    private ViewPager viewPager;
//    private MyViewPagerAdapter myViewPagerAdapter;
//    private LinearLayout dotsLayout;
//    private OnPageChangeListener mPageChangeListener;
//    TextView bookname,desc,authorname;
//
//    RelativeLayout rv;
//    private TextView[] dots;
//    String strname=null,strdesc=null;
//ImageView book_img;
//Button btn_login;
//    String strnm;
//
//    private int[] layouts;
//    EditText tvname = null,tvdesc = null;
//
//    private Button btnSkip, btnNext;
//    private String encodedpdf;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
//        setContentView(R.layout.activity_new);
//        getSupportActionBar().hide();
//        viewPager = (ViewPager) findViewById(R.id.view_pager);
//        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
//        btnSkip = (Button) findViewById(R.id.btn_skip);
//        btnNext = (Button) findViewById(R.id.btn_next);
//        layouts = new int[]{
//                R.layout.publish_book_pg1,
//                R.layout.publish_book_pg2,
//                R.layout.publish_book_pg4,
//                R.layout.publish_book_pg5,
//                R.layout.publish_book_pg3,};
//        addBottomDots(0);
//        changeStatusBarColor();
//        myViewPagerAdapter = new MyViewPagerAdapter();
//        viewPager.setAdapter(myViewPagerAdapter);
//        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
//
//        btnSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // launchHomeScreen();
//                if (v == buttonChoose) {
//                    showFileChooser();
//                }
//
//                if(v == buttonUpload){
//                }
//
//            }
//        });
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int current = getItem(+1);
//                if (current < layouts.length) {
//                    // move to next screen
//                    viewPager.setCurrentItem(current);
//                }
////                else {
////                    launchHomeScreen();
////                }
//            }
//        });
//        /*setContentView(R.layout.activity_new);
//        if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().getDecorView()
//                    .setSystemUiVisibility(
//                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
//        setContentView(R.layout.activity_new);
//        getSupportActionBar().hide();
//        viewPager = findViewById(R.id.pagerIntroSlider);
//        TabLayout tabLayout = findViewById(R.id.tabs);
//        button = findViewById(R.id.button);
//        // init slider pager adapter
//        adapter = new SliderPagerAdapter(getSupportFragmentManager(),
//                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        // set adapter
//        viewPager.setAdapter(adapter);
//        // set dot indicators
//        tabLayout.setupWithViewPager(viewPager);
//        // make status bar transparent
//        changeStatusBarColor();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View view) {
//                if (viewPager.getCurrentItem() < adapter.getCount()) {
//                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
//                }
//            }
//        });*/
//
////        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
////            @Override
////            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////            }
////            @Override public void onPageSelected(int position) {
////                if (position == adapter.getCount() - 1) {
////                    button.setText(R.string.get_started);
////                } else {
////                    button.setText(R.string.next);
////                }
////            }
////            @Override public void onPageScrollStateChanged(int state) {
////            }
////        });
//    }
//    private void showFileChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//    }
//    private String convertToString()
//    {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//        byte[] imgByte = byteArrayOutputStream.toByteArray();
//        return Base64.encodeToString(imgByte,Base64.DEFAULT);
//    }
//    private void selectImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, 100);
//    }
//    private void addBottomDots(int currentPage)
//    {
//        dots = new TextView[layouts.length];
//        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
//        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
//        dotsLayout.removeAllViews();
//        for (int i = 0; i < dots.length; i++) {
//            dots[i] = new TextView(this);
//            dots[i].setText(Html.fromHtml("&#8226;"));
//            dots[i].setTextSize(35);
//            dots[i].setTextColor(colorsInactive[currentPage]);
//            dotsLayout.addView(dots[i]);
//        }
//        if (dots.length > 0)
//            dots[currentPage].setTextColor(colorsActive[currentPage]);
//    }
//    private int getItem(int i) {
//        return viewPager.getCurrentItem() + i;
//    }
////    private void launchHomeScreen() {
////       // prefManager.setFirstTimeLaunch(false);
////        startActivity(new Intent(NewActivity.this, NewActivity.class));
////        finish();
////    }
//    OnPageChangeListener viewPagerPageChangeListener = new OnPageChangeListener() {
//        @Override
//        public void onPageSelected(int position) {
//            addBottomDots(position);
//            // changing the next button text 'NEXT' / 'GOT IT'
//            if (position == layouts.length - 1) {
//                // last page. make button text to GOT IT
//                btnNext.setText(getString(R.string.start));
//                btnNext.setVisibility(View.GONE);
//                btnSkip.setVisibility(View.GONE);
//            } else
//                {
//                // still pages are left
//                btnNext.setText(getString(R.string.next));
//                btnSkip.setVisibility(View.VISIBLE);
//            }
//            if (position == 2) {
//                tvname = findViewById(R.id.name);
////                tvdesc = findViewById(R.id.desc);
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
//            }
//            if(position == 0)
//            {
//
//            }
//            if (position == 3) {
//
//                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//               // View v=inflater.inflate(R.layout.make_cover,null,false);
//                //Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
//                //Canvas c = new Canvas(b);
//               // if(strname!=null)Toast.makeText(NewActivity.this, "val=="+strname, Toast.LENGTH_SHORT).show();
//               /// v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
//                // bn.setText(tvname.getText().toString());
//                //d.setText(tvdesc.getText().toString());
//                //v.draw(c);
//
//                //book_img.setImageBitmap(b);
//
//
//
//            }
//        }
//        @Override
//        public void onPageScrolled(int arg0, float arg1, int arg2) {
//        }
//
//
//
//
//
//
//
//
//
//        @Override
//        public void onPageScrollStateChanged(int arg0) {
//
//
//        }
//    };
//    private void changeStatusBarColor() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
//    }
//    public class MyViewPagerAdapter extends PagerAdapter {
//        private LayoutInflater layoutInflater;
//        public MyViewPagerAdapter() {
//        }
//        @Override
//        public void startUpdate(@NonNull ViewGroup container) {
//            super.startUpdate(container);
//        }
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view = layoutInflater.inflate(layouts[position], container, false);
//            container.addView(view);
//            if(position == 0)
//            {
//             //   Toast.makeText(NewActivity.this, "opopopopopooooo1", Toast.LENGTH_SHORT).show();
////                hindi=view.findViewById(R.id.hindi);
////                english=view.findViewById(R.id.english);
////                gujarati=view.findViewById(R.id.gujarati);
////                tamil=view.findViewById(R.id.tamil);
////                cardhindi=view.findViewById(R.id.cardhindi);
////                cardenglish=view.findViewById(R.id.cardenglish);
////                cardgujarati=view.findViewById(R.id.cardgujarati);
////                cardtamil=view.findViewById(R.id.cardtamil);
//
//
////                cardhindi.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////
////                        hindi.setTextColor(getResources().getColor(R.color.white));
////                        cardhindi.setBackgroundColor(getResources().getColor(R.color.startblue));
////                        english.setTextColor(getResources().getColor(R.color.startblue));
////                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
////                        gujarati.setTextColor(getResources().getColor(R.color.startblue));
////                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.white));
////                        tamil.setTextColor(getResources().getColor(R.color.white));
////                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
////                    }
////                });
////                cardenglish.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        english.setTextColor(getResources().getColor(R.color.white));
////                        cardenglish.setBackgroundColor(getResources().getColor(R.color.startblue));
////                        gujarati.setTextColor(getResources().getColor(R.color.startblue));
////                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.white));
////                        hindi.setTextColor(getResources().getColor(R.color.startblue));
////                        cardhindi.setBackgroundColor(getResources().getColor(R.color.white));
////                        tamil.setTextColor(getResources().getColor(R.color.white));
////                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
////                        english.setTextColor(getResources().getColor(R.color.startblue));
////                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
////                        //selected="english";
////                    }
////                });
////                cardgujarati.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        gujarati.setTextColor(getResources().getColor(R.color.white));
////                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.startblue));
////                        //selected="english";
////                        hindi.setTextColor(getResources().getColor(R.color.startblue));
////                        cardhindi.setBackgroundColor(getResources().getColor(R.color.white));
////                        tamil.setTextColor(getResources().getColor(R.color.white));
////                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
////                        english.setTextColor(getResources().getColor(R.color.startblue));
////                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
////                    }
////                });
////                cardtamil.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        tamil.setTextColor(getResources().getColor(R.color.white));
////                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
////                        english.setTextColor(getResources().getColor(R.color.startblue));
////                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
////                        gujarati.setTextColor(getResources().getColor(R.color.startblue));
////                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.white));
////                        hindi.setTextColor(getResources().getColor(R.color.startblue));
////                        cardhindi.setBackgroundColor(getResources().getColor(R.color.white));
////                        //selected="english";
////                    }
////                });
////
////
////
////
////
////                TextView txt_choose=view.findViewById(R.id.txt_choose);
////                txt_choose.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                    }
////                });
//            }
//            if(position == 1)
//            {
//                TextView txt_choose=view.findViewById(R.id.txt_choose);
////                TextView scifi,horror,comedy,study;
////                scifi=view.findViewById(R.id.scifi);
////                horror=view.findViewById(R.id.horror);
////                comedy=view.findViewById(R.id.comedy);
////                study=view.findViewById(R.id.study);
////                cardscifi=view.findViewById(R.id.cardscifi);
////                cardhorror=view.findViewById(R.id.cardcomedy);
//////                cardstudy=view.findViewById(R.id.cardstudy);ew.findViewById(R.id.cardhorror);
////                cardcomedy=vi
//                txt_choose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                    }
//                });
//
////
////                cardhorror.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////
////                        horror.setTextColor(getResources().getColor(R.color.white));
////                        cardhorror.setBackgroundColor(getResources().getColor(R.color.startblue));
////
////                    }
////                });
////                cardcomedy.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        comedy.setTextColor(getResources().getColor(R.color.white));
////                        cardcomedy.setBackgroundColor(getResources().getColor(R.color.startblue));
////
////                        //selected="english";
////                    }
////                });
////                cardstudy.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        study.setTextColor(getResources().getColor(R.color.white));
////                        cardstudy.setBackgroundColor(getResources().getColor(R.color.startblue));
////                        //selected="english";
////                    }
////                });
////                cardscifi.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        scifi.setTextColor(getResources().getColor(R.color.white));
////                        cardscifi.setBackgroundColor(getResources().getColor(R.color.startblue));
////
////                        //selected="english";
////                    }
////                });
////
//
//
//
//            }
//            if(position == 2)
//            {
//                tvname=findViewById(R.id.name);
////                tvdesc=findViewById(R.id.desc);
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
//
//            }
//
//            if(position==3){
//        View v2=layoutInflater.inflate(R.layout.make_cover,null,false);
//        bookname=v2.findViewById(R.id.bookname);
//                desc=v2.findViewById(R.id.desc);
//                authorname=v2.findViewById(R.id.authorname);
//                bookname.setText(strname);
//                desc.setText(strdesc);
//                btn_login= view.findViewById(R.id.btn_login);
//                btn_login.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                       // uploadImage();
//                      //  uploadretrofit();
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
////                View v=layoutInflater.inflate(R.layout.make_cover,null,false);
////                TextView bn=v.findViewById(R.id.bookname);
////                TextView d=v.findViewById(R.id.desc);
////                if(strname!=null)Toast.makeText(NewActivity.this, "val=="+strname, Toast.LENGTH_SHORT).show();
////                v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
////               // bn.setText(tvname.getText().toString());
////                //d.setText(tvdesc.getText().toString());
////                Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
////                Canvas c = new Canvas(b);
////                v.draw(c);
//            }
//            if(position==4)
//            {
////            bt=view.findViewById(R.id.bt);
////                System.out.println("here="+booknm);
////                tvchoose=view.findViewById(R.id.tvchoose);
////                btnchoose=view.findViewById(R.id.btnchoose);
////            bt.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////                    uploadretrofit(strnm);
////                    Toast.makeText(NewActivity.this, "Book Uploaded", Toast.LENGTH_SHORT).show();
////                    Intent i=new Intent(getApplicationContext(),AllBooks.class);
////                    startActivity(i);
////
////
////                }
////            });
////                btnchoose.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////
////
////                        Intent choosefile = new Intent(Intent.ACTION_GET_CONTENT);
////                        choosefile.setType("application/pdf");
////                        choosefile = Intent.createChooser(choosefile, "Choose a file");
////                        startActivityForResult(choosefile, 21);
////                        bt.setVisibility(View.VISIBLE);
////                    }
////                });
//
//            }
//            return view;
//        }
//        private void uploadPdfToServer()
//        {
//            retrofit2.Call<ResponsePOJO> call = RetrofitClient.getInstance().getAPI().uploadDocument(encodedpdf);
//            call.enqueue(new retrofit2.Callback<ResponsePOJO>() {
//                @Override
//                public void onResponse( retrofit2.Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
//
//                    Toast.makeText(NewActivity.this, response.body().getRemarks(), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure( retrofit2.Call<ResponsePOJO> call, Throwable t) {
//                    Toast.makeText(NewActivity.this, "Network Failed", Toast.LENGTH_SHORT).show();
//
//                }
//            });
////            Toast.makeText(NewActivity.this, "upppppp", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public int getCount() {
//            return layouts.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object obj) {
//            return view == obj;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            View view = (View) object;
//            container.removeView(view);
//        }
//    }
//
//    private void uploadretrofit(String strnm) {
//
//        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//
//        String image = convertToString();
//
//// Retrieving the value using its keys the file name
//// must be same in both saving and retrieving the data
//        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
//
//// The value will be default as empty string because for
//// the very first time when the app is opened, there is nothing to show
//        String nm = sh.getString("name", "");
//
//
//
//        retrofit2.Call<Img_Pojo> call = apiInterface.uploadImage(strnm,image,strnm,"peehu"+strnm,"90","Hindi",
//
//               "study" ,"900",encodedpdf);
//        call.enqueue(new Callback<Img_Pojo>() {
//            @Override
//            public void onResponse(Call<Img_Pojo> call, Response<Img_Pojo> response) {
//
//                Img_Pojo img_pojo = response.body();
//                Toast.makeText(NewActivity.this, img_pojo.getResponse(), Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<Img_Pojo> call, Throwable t) {
//                Log.d("Server Response",""+t.toString());
//
//
//            }
//        });
//
//
//    }
////
////    private String getBookName() {
////
////    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//      //  if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            if(requestCode== 100 && resultCode==RESULT_OK && data!=null)
//            {
//                Uri path = data.getData();
//
//                try {
//
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
//
//                    book_img.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    System.out.println("err=="+e.getMessage());
//                }
//            }
//            filePath = data.getData();
//
//        if(requestCode==21 && resultCode==RESULT_OK && data!=null)
//        {
//
//
//
//            Uri path=data.getData();
//            String uriString = path.toString();
//            File myFile = new File(uriString);
//            displayName = myFile.getName();
//            tvchoose.setText(displayName);
//            byte[] pdfinbytes= new byte[0];
//            try {
//                InputStream is=NewActivity.this.getContentResolver().openInputStream(path);
//                pdfinbytes = new byte[is.available()];
//                is.read(pdfinbytes);
//                encodedpdf=Base64.encodeToString(pdfinbytes,Base64.DEFAULT);
//            }
//             catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        viewPager.setOnPageChangeListener(mPageChangeListener);
//
//        viewPager.addOnPageChangeListener(new OnPageChangeListener()
//        {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//            @Override
//            public void onPageSelected(int position) {
//                if(position==2)
//                {
//
//                    strname=tvname.getText().toString();
//                    strdesc=tvdesc.getText().toString();
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });
//    }
//
//
//}






implements BookCallback{
private RecyclerView rvBooks;
private BookAdapter bookAdapter;
private List<Book> mdata ;
        SearchView sv;

private ProgressBar progressBar;
        StaggeredGridLayoutManager m;
private static  final String BASE_URL = "https://pbmabad.000webhostapp.com/Php_AllCompanionInfoMale.php";
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_books);
        progressBar = findViewById(R.id.progressbar);
        sv=findViewById(R.id.sv);

        m=new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        getSupportActionBar().hide();
        initViews();
        initmdataBooks();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
@Override
public boolean onQueryTextSubmit(String query) {
        return false;
        }

@Override
public boolean onQueryTextChange(String newText) {
        // inside on query text change method we are
        // calling a method to filter our recycler view.
        filter(newText);
        return false;
        }
        });


        }

private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Book> filteredlist = new ArrayList<>();
        if(mdata !=null) {
        // running a for loop to compare elements.
        for (Book item : mdata) {
        // checking if the entered string matched with any item of our recycler view.
        if (item.getFirst_name().toLowerCase().contains(text.toLowerCase())) {
        // if the item is matched we are
        // adding it to our filtered list.
        filteredlist.add(item);
        }
        }
        }
        if (filteredlist.isEmpty()) {
        } else {
        // at last we are passing that filtered
        // list to our adapter class.
        bookAdapter.filterList(filteredlist);
        }
        }

private void setupBookAdapter() {

        bookAdapter = new BookAdapter(mdata,this,getApplicationContext());
        rvBooks.setLayoutManager(m);
        rvBooks.setAdapter(bookAdapter);
        m.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        rvBooks.setLayoutManager(m);
        rvBooks.setItemAnimator(new DefaultItemAnimator());

        }
private void initmdataBooks() {
        // for testing purpos I'm creating a random set of books
        // you may get your data from web service or firebase database.
        mdata = new ArrayList<>();
        allCompanions(new VolleyCallback() {
@Override
public void onSuccess(String response) {
        JSONObject obj = null;
        try {
        obj = new JSONObject(response);
        JSONObject all_recievers = obj.getJSONObject("all_recievers");
        String allCompanionsString=     (String) all_recievers.get("all_recievers");
        System.out.println("allCompanionsString==>"+allCompanionsString);
//                    if(allCompanionsString!=null)
//                    {
//                        String[] allCompanions = allCompanionsString.split("\\|");
////                        System.out.println("comps==>"+b.getLast_name());
////                        for (String companion : allCompanions) {
////                            if (b.getPid().equals(companion)) {
////                                holder.imgFav.setImageResource(R.drawable.btn_custom);
////                            }
////                        }
////                    if (companion.equals(keyToFind)) {
////                        containsKey = true;
////                        break; // Exit the loop as soon as the key is found
////                    }
//                    }
        loadBooks(allCompanionsString);

        } catch (JSONException e) {
        e.printStackTrace();
        }

        //creating a new user object
//
//                                        System.out.println("all_recievers=="+all_recievers.get("all_recievers"));




        }

@Override
public void onError(Exception error) {

        }
        });



//
//
//
//
//        mdata.add(new Book(R.drawable.bookcover));
//        mdata.add(new Book(R.drawable.bookcover));
//        mdata.add(new Book(R.drawable.bookcover));
//        mdata.add(new Book(R.drawable.bookcover));
//        mdata.add(new Book(R.drawable.bookcover));
//        mdata.add(new Book(R.drawable.bookcover));
        }
private void allCompanions(final VolleyCallback callback) {
        String urllogin="https://pbmabad.000webhostapp.com/Php_IsCompanion.php";
final String[] all_companions = new String[1];


        StringRequest stringRequest = new StringRequest(Request.Method.POST, urllogin,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            System.out.println("obj===>" + obj);

                            JSONObject all_recievers = obj.getJSONObject("all_recievers");
                            //creating a new user object
//
//                                        System.out.println("all_recievers=="+all_recievers.get("all_recievers"));
                            callback.onSuccess(response);

//                            (String) all_recievers.get("all_recievers");

//                                        else {
//                                            Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                                        }
                        } catch (JSONException e) {
                            System.out.println("error==" + e.getMessage());
                            callback.onError(e);

                            e.printStackTrace();
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NewActivity.this.getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                params.put("reciever_pid", "1");


                params.put("sender_pid", "101");
                return params;
            }

        };

        VolleySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(stringRequest);
        }

private void loadBooks(String companions) {
        progressBar.setVisibility(View.VISIBLE);
        String[] allCompanions = companions.split("\\|");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL,
        new Response.Listener<String>() {
@Override
public void onResponse(String response) {

        progressBar.setVisibility(View.GONE);
        try {System.out.println("respppp=="+response);
        JSONArray array = new JSONArray(response);
        for (int i = 0; i<array.length(); i++){
        JSONObject object = array.getJSONObject(i);
        String first_name = object.getString("first_name");
        String middle_name = object.getString("middle_name");
        String last_name = object.getString("last_name");
        String gender = object.getString("gender");
        String mobile_number = object.getString("mobile_number");
        String profile_status = object.getString("profile_status");
        String manglik = object.getString("manglik");
        String occupation = object.getString("occupation");
        String income = object.getString("income");
        String physicalpath = object.getString("physicalpath");
        String origin_family = object.getString("origin_family");
        String profile_id=object.getString("profile_id");
        String is_cmp="n";
        for (String companion : allCompanions) {
        if (profile_id.equals(companion)) {
        is_cmp="y";
        break;
        }
        else{
        is_cmp="n";
        }
        }
        Book b = new Book(profile_id,first_name,middle_name,last_name,gender,mobile_number,profile_status,manglik,occupation,income,physicalpath,origin_family,is_cmp,companions);
        mdata.add(b);
        }
        }catch (Exception e){
        }
        setupBookAdapter();
//                        mAdapter = new RecyclerAdapter(HomeActivity.this,products);
//                        recyclerView.setAdapter(mAdapter);

        }
        }, new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError error) {

        progressBar.setVisibility(View.GONE);
        Toast.makeText(NewActivity.this, error.toString(),Toast.LENGTH_LONG).show();

        }
        }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("all_companions", companions);
                        return params;
                }

        };

        Volley.newRequestQueue(NewActivity.this).add(stringRequest);

        }


private void initViews() {

        rvBooks = findViewById(R.id.rv_book);
        rvBooks.setLayoutManager(m);

//        rvBooks.setHasFixedSize(true);
        // we need first to setupe the custom item animator that we just create
//        rvBooks.setItemAnimator(new CustomItemAnimator());
        // to test the animation we need to simulate the add book operation


        }
@Override
public void onBookItemClick(int pos,
        ImageView imgBook,
        TextView title,
        TextView ratingBar,TextView item_book_author,TextView item_book_pagesrev,TextView occ) {
        // create intent and send book object to Details activity
        Intent intent = new Intent(this,BookDetailsActivity.class);
        intent.putExtra("physical_path",mdata.get(pos).getPhysicalpath());
        // Pair<View,String> p1 = Pair.create((View)imgContainer,"containerTN"); // second arg is the tansition string Name
        Pair<View,String> p2 = Pair.create((View)imgBook,"bookTN"); // second arg is the tansition string Name
        Pair<View,String> p3 = Pair.create((View)title,"booktitleTN"); // second arg is the tansition string Name
//        Pair<View,String> p4 = Pair.create((View)authorName,"authorTN"); // second arg is the tansition string Name
//        Pair<View,String> p5 = Pair.create((View)nbpages,"bookpagesTN"); // second arg is the tansition string Name
//        Pair<View,String> p6 = Pair.create((View)score,"scoreTN"); // second arg is the tansition string Name
        Pair<View,String> p7 = Pair.create((View)ratingBar,"rateTN"); // second arg is the tansition string Name
//        ActivityOptionsCompat optionsCompat =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(this,p1,p2 ,p3,p4,p5,p6,p7);
        // start the activity with scene transition
        ActivityOptionsCompat optionsCompat =
        ActivityOptionsCompat.makeSceneTransitionAnimation(this,p2 ,p3,p7);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivity(intent,optionsCompat.toBundle());
        }
        else
        startActivity(intent);


        }
        }