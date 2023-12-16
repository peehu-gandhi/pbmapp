package com.example.bookbub;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {
    ArrayList images;
    Context context;

    // Constructor for initialization
    public AboutAdapter(Context context, ArrayList images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public AboutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_about, parent, false);
        AboutAdapter.ViewHolder viewHolder = new AboutAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        int res = (int) images.get(position);
        String img= (String) images.get(position);
        System.out.println("imh====="+img);
        Glide.with(context)
                .load(img)
                .placeholder(R.drawable.punjab_logo)
                .into(holder.images);
//        if(b.get(position).isPinned()){
//            holder.ivpin.setImageResource(R.drawable.pin);
//        }
//        else{
//            holder.ivpin.setImageResource(0);
//        }
//        holder.images.setImageResource(res);
        holder.images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageDialog(holder.images.getDrawable());

            }
        });
    }

    private void showImageDialog(Drawable drawable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_fullscreen, null);

        ImageView imageViewFullScreen = dialogView.findViewById(R.id.imageViewFullScreen);
        imageViewFullScreen.setImageDrawable(drawable);

        builder.setView(dialogView);

        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    @Override
    public int getItemCount() {
        // Returns number of items currently available in AboutAdapter
        return images.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;

        public ViewHolder(View view) {
            super(view);
            images = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}