package com.example.bookbub;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public interface BookCallback {

    void onBookItemClick(int pos,
                         ImageView imgBook,
                         TextView title,
                         TextView ratingBar,TextView item_book_author,TextView item_book_pagesrev,TextView occ);


}


//public interface BookCallback {
//
//    void onBookItemClick(int pos,
//                         ImageView imgContainer,
//                         ImageView imgBook,
//                         TextView title,
//                         TextView authorName,
//                         TextView nbpages,
//                         TextView score,
//                         RatingBar ratingBar);
//
//
//}
