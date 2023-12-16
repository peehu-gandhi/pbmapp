package com.example.bookbub;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
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

public class AllBooks extends AppCompatActivity implements BookCallback{
    private RecyclerView rvBooks;
    private BookAdapter bookAdapter;
    private List<Book> mdata ;
    SearchView sv;
    int ppid;
    String ggender;
    private ProgressBar progressBar;
    SessionManager session;
    StaggeredGridLayoutManager m;
    private static   String BASE_URL = "https://pbmabad.000webhostapp.com/Php_AllGrooms.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_books);
                progressBar = findViewById(R.id.progressbar);
        sv=findViewById(R.id.sv);
        session=new SessionManager(getApplicationContext());
        Intent receivedIntent = getIntent();
//        String gender = receivedIntent.getStringExtra("gender");
//        int pid = receivedIntent.getIntExtra("pid",0);
        System.out.println("hh=="+session.getGender());
        if (session != null && session.getGender() != null &&
                ("Male".equals(session.getGender()) || "M".equals(session.getGender())))         {
            BASE_URL = "https://pbmabad.000webhostapp.com/Php_AllBrides.php";
        }
        m=new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        getSupportActionBar().hide();
        initViews();
        initmdataBooks(ppid);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
//                bookAdapter.getFilter().filter(newText);

                return false;
            }
        });


    }

    private void filter(String text) {

        // creating a new array list to filter our data.
//        ArrayList<Book> filteredlist = new ArrayList<>();
//        if(mdata !=null) {
//            // running a for loop to compare elements.
//            for (Book item : mdata) {
//                // checking if the entered string matched with any item of our recycler view.
//                if (item.getFirst_name().toLowerCase().contains(text.toLowerCase())) {
//                    // if the item is matched we are
//                    // adding it to our filtered list.
//                    filteredlist.add(item);
//                }
//            }
//        }
//        if (filteredlist.isEmpty()) {
//        } else {
//            bookAdapter.filterList(filteredlist);
//        }

        ArrayList<Book> filteredList = new ArrayList<>();
        List<Book> orig = mdata;

        for (int i = 0,j=0; i < mdata.size(); i++) {
            Book item = mdata.get(i);
            if (item.getFirst_name().toLowerCase().contains(text.toLowerCase())) {
                mdata.get(j).setOriginalPosition(i);
                filteredList.add(item);
                j++;
            }

        }

        if (!filteredList.isEmpty()) {
//            mdata=filteredList;
            bookAdapter.filterList(filteredList);

        }
        else{
//            bookAdapter.filterList(orig);

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
    private void initmdataBooks(int pid) {
        // for testing purpos I'm creating a random set of books
        // you may get your data from web service or firebase database.
        mdata = new ArrayList<>();
        allCompanions(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                JSONObject obj = null;
                try {
                    System.out.println("allCompanionsString=22=>" );

                    obj = new JSONObject(response);
                    System.out.println("allCompanionsString=22=>response"+response );

                    String all_recievers = obj.getString("all_recievers");
                    System.out.println("allCompanionsString=22=>2"+all_recievers);

//                    System.out.println("reci===>>>" + all_recievers.get("all_recievers"));
//                    JSONArray allRecieversArray = all_recievers.getJSONArray("all_recievers");
                    String allCompanionsString="";
//                    System.out.println("allCompanionsString=22=>2 rec==" +allRecieversArray.length()+" s"+allRecieversArray);
//
//                    for (int i = 0; i < allRecieversArray.length(); i++) {
//                     allCompanionsString = (String) all_recievers.get("all_recievers");
                    System.out.println("allCompanionsString=22=>====>" + allCompanionsString);
                    loadBooks(all_recievers);
//                    if (all_recievers.get("all_recievers")!=null) {
//                        String allCompanionsString = (String) all_recievers.get("all_recievers");
//                        System.out.println("allCompanionsString==>" + allCompanionsString);
//                        loadBooks(allCompanionsString);
//                    }

                } catch (JSONException e) {
                   System.out.println("exception e==>"+e.getMessage());

                }
            }

            @Override
            public void onError(Exception error) {

            }

//            @Override
//            public void onSuccess(JSONObject response) {
//                try {
//                    System.out.println("allCompanionsString=22=>" );
//
////                    JSONObject obj = new JSONObject(response);
//                    JSONObject all_recievers = response.getJSONObject("all_recievers");
//                    System.out.println("allCompanionsString=22=>2" );
//
////                    System.out.println("reci===>>>" + all_recievers.get("all_recievers"));
////                    JSONArray allRecieversArray = all_recievers.getJSONArray("all_recievers");
//                    String allCompanionsString="";
////                    System.out.println("allCompanionsString=22=>2 rec==" +allRecieversArray.length()+" s"+allRecieversArray);
////
////                    for (int i = 0; i < allRecieversArray.length(); i++) {
//
//                    allCompanionsString = (String) all_recievers.get("all_recievers");
//                    System.out.println("allCompanionsString=22=>====>" + allCompanionsString);
//
//                    loadBooks(allCompanionsString);
//
//
//
//
////                    if (all_recievers.get("all_recievers")!=null) {
////                        String allCompanionsString = (String) all_recievers.get("all_recievers");
////                        System.out.println("allCompanionsString==>" + allCompanionsString);
////                        loadBooks(allCompanionsString);
////                    }
//
//                } catch (JSONException e) {
//                    System.out.println("exception e==>"+e.getMessage());
//
//                }
//            }
        },pid);
    }
    private void allCompanions(final VolleyCallback callback,int pid) {
        String urllogin="https://pbmabad.000webhostapp.com/Php_IsCompanion.php";
        final String[] all_companions = new String[1];

        System.out.println("obj===>");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urllogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            System.out.println("obj===>"+response);
                            JSONObject all_recievers = obj.getJSONObject("all_recievers");
                            String rr=all_recievers.toString();
                            System.out.println("rr===>"+rr);

                            callback.onSuccess(rr);

                        } catch (JSONException e) {
                            System.out.println("error=="+e.getMessage());
                            callback.onError(e);

                            e.printStackTrace();
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AllBooks.this.getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                params.put("sender_pid",session.getPid());
                return params;
            }

        };
        System.out.println("all_companions===>"+all_companions[0]);

        VolleySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void loadBooks(String companions) {
        progressBar.setVisibility(View.VISIBLE);
        String[] allCompanions = companions.split("\\|");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);
                        try {

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
                                Book b = new Book(profile_id,first_name,middle_name,last_name,gender,mobile_number,profile_status,manglik,occupation,income,physicalpath,origin_family,is_cmp,companions,i);
                                mdata.add(b);
                            }
                        }catch (Exception e){
                            System.out.println("here exception==>"+e.getMessage());
                        }
                        setupBookAdapter();
//                        mAdapter = new RecyclerAdapter(HomeActivity.this,products);
//                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(AllBooks.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(AllBooks.this).add(stringRequest);

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
        int originalPosition = mdata.get(pos).getOriginalPosition();

        System.out.println("poso=="+originalPosition);
        Intent intent = new Intent(this,BookDetailsActivity.class);
        intent.putExtra("physical_path",mdata.get(originalPosition).getPhysicalpath());
        intent.putExtra("pid",mdata.get(originalPosition).getPid());

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    String gender = data.getStringExtra("gender");
                    int pid = data.getIntExtra("pid", 0); // Default value is 0 if "pid" is not found
                    System.out.println("pid==" + pid);
                    ppid=pid;
                    ggender=gender;
                    System.out.println("gen==" + gender);
                }
            }
        }
    }

}