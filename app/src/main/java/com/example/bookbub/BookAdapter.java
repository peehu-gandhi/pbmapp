package com.example.bookbub;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bookbub.model.Book;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookAdapter  extends RecyclerView.Adapter<BookAdapter.bookviewholder>
{
    List<Book> mdata;
    BookCallback callback;
    Context c;
    public BookAdapter(List<Book> mdata , BookCallback callback, Context c) {
        this.mdata = mdata;
        this.callback = callback ;
        this.c=c;
    }
    @NonNull
    @Override
    public bookviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book,parent,false);
        return new bookviewholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull bookviewholder holder, int position) {
        final Book b = mdata.get(position);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.bookcover2)
                .error(R.drawable.bookcover2);
        Glide.with(c).load(b.getPhysicalpath()).apply(options).into(holder.imgBook);
        holder.title.setText(b.getFirst_name() + " "+b.getMiddle_name()+"\n"+b.getLast_name());
        holder.item_book_author.setText("Salary: "+b.getIncome());
        holder.ratingBar.setText("Contact: "+b.getMobile_number());
        String m=b.getManglik().equals("M")?"Manglik":"Non-Manglik";
        m="Status: "+m;
        holder.item_book_pagesrev.setText(m);
        holder.item_book_score.setText("Occupation: "+b.getOccupation());
        String companions=b.getCompanions();
        holder.imgFav.setColorFilter(ContextCompat.getColor(c.getApplicationContext(), android.R.color.darker_gray)); // Change R.color.default_color to your default color resource ID

        String[] allCompanions = companions.split("\\|");
                            if (b.getCompanions().equals("y")) {
                                holder.imgFav.setColorFilter(ContextCompat.getColor(c.getApplicationContext(), android.R.color.holo_red_dark));
                                System.out.println("comp==>"+b.getCompanions()+" pid==>"+b.getPid());
                            }
                            holder.imgFav.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        System.out.println("is cmp==>"+b.getCompanions()+" pid==>"+b.getPid());

                                        if (b.getCompanions().equals("n")) {
                                            holder.imgFav.setColorFilter(ContextCompat.getColor(c.getApplicationContext(), android.R.color.holo_red_dark));
                                            make_companions(b.getPid());
                                            b.setCompanions("y");

                                        }
                                        else{
                                            holder.imgFav.setColorFilter(ContextCompat.getColor(c.getApplicationContext(), android.R.color.darker_gray));
                                            remove_companions(b.getPid(),b.getExixtingCompanions());
                                            b.setCompanions("n");

                                        }
                                    }
                                });

    }

    private void remove_companions(String pid,String all_companions)
    {
        String url="https://pbmabad.000webhostapp.com/Php_RemoveCompanion.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("rep=="+response);
                        }catch (Exception e){
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String regex = "(^|\\|)" + pid + "(\\||$)";

                String updatedString = all_companions.replaceAll(regex, "|");

                updatedString = updatedString.replaceAll("^\\|+|\\|+$", "");

                params.put("new_companion_list",updatedString);
                params.put("sender","101");
                return params;
            }

        };

        Volley.newRequestQueue(c).add(stringRequest);


    }

    private void make_companions(String recieverPid) {
        String url="https://pbmabad.000webhostapp.com/Php_AddCompanion.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("rep=="+response);
                        }catch (Exception e){
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("new_reciever",recieverPid);


                params.put("sender","1");
                return params;
            }

        };

        Volley.newRequestQueue(c).add(stringRequest);


    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public void filterList(List<Book> filteredlist) {
        mdata = filteredlist;
        notifyDataSetChanged();
    }

    public class bookviewholder extends RecyclerView.ViewHolder {
        ImageView imgBook,imgFav;
        ImageView imgContainer;
        TextView title,item_book_author,item_book_pagesrev;
        TextView ratingBar,item_book_score;
        public bookviewholder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.item_book_img);
            title = itemView.findViewById(R.id.item_book_title);
            ratingBar = itemView.findViewById(R.id.item_book_ratingbar);
            item_book_author=itemView.findViewById(R.id.item_book_author);
            item_book_pagesrev=itemView.findViewById(R.id.item_book_pagesrev);
            item_book_score=itemView.findViewById(R.id.item_book_score);
            imgFav=itemView.findViewById(R.id.imageView3);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onBookItemClick(getAdapterPosition(),
                            imgBook,
                            title,
                            ratingBar,item_book_author,item_book_pagesrev,item_book_score);

                }
            });


            imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }
}