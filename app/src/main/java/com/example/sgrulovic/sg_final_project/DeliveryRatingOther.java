package com.example.sgrulovic.sg_final_project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import static android.icu.lang.UCharacter.toUpperCase;

public class DeliveryRatingOther extends Activity {

    //Variable declaration
    ListView rating_other_listview;
    Intent intent;
    int index;
    ArrayList<String> del_place_comments = new ArrayList<String>();
    ArrayList<String> del_place_ratings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_rating_other);

        //grabing the index from the intent
        index = getIntent().getExtras().getInt("index");
        //declaring the shared prefrances for grabbing stored user comment and rating
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String comment_stored = sharedPref.getString("comment_stored" + index, "Comment");//grabbing the comment if there is one
        String rating_stored = sharedPref.getString("rating_stored" + index, "Comment");//grabbing the comment if there is one

        //text view to display the title/header
        final TextView rating_other_name = (TextView) findViewById(R.id.rating_other_name);
        //grabing the delivery place name for the title header
        String[] del_place = getResources().getStringArray(R.array.del_places_string_array);
        rating_other_name.setText( toUpperCase( del_place[index] ) + " RATINGS: ");//setting the text for the title/header

        //grabbing the already stored comments
        Integer[] del_comment_arrays = {R.array.d1_comment, R.array.d2_comment, R.array.d3_comment, R.array.d4_comment};
        String[] del_place_comment_before = getResources().getStringArray(del_comment_arrays[index]);//grabbing the correct comment array based on the index
        //grabbing the already stored ratings
        Integer[] del_rating_arrays = {R.array.d1_rating, R.array.d2_rating, R.array.d3_rating, R.array.d4_rating};
        String[] del_rating_before = getResources().getStringArray(del_rating_arrays[index]);//grabbing the correct comment array based on the index

        //placing the comments and ratings from before into array list
        for (int i = 0; i < del_place_comment_before.length; i++) {
            del_place_comments.add(del_place_comment_before[i]);
        }
        for (int j = 0; j < del_rating_before.length; j++) {
            del_place_ratings.add(del_rating_before[j]);
        }
        //placing the stored comment and rating from the user into array list
        del_place_comments.add(comment_stored);
        del_place_ratings.add(rating_stored);

        //custom adapter for the listview
        CustomListAdapter adapter=new CustomListAdapter(this, del_place_comments, del_place_ratings);
        rating_other_listview = (ListView)findViewById(R.id.rating_other_listview);//listview
        rating_other_listview.setAdapter(adapter);//combining adapter and listview


        //button for going back
        Button rating_other_back = (Button)findViewById(R.id.rating_other_back);
        rating_other_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DeliveryRatingOther.this, DeliveryRating.class);
                intent.putExtra("index",index);
                startActivity(intent);
            }
        });
    }


    //custom list adapter
    class CustomListAdapter extends ArrayAdapter<String> {

        //variable declaration
        private final Activity context;
        private final ArrayList<String> comment;
        private final ArrayList<String> rating;

        //constructor
        public CustomListAdapter(Activity context, ArrayList<String> rating_comment,ArrayList<String> rating_rating) {
            super(context, R.layout.delivery_rating_layout, rating_comment);

            this.context=context;
            this.comment=rating_comment;
            this.rating=rating_rating;
        }
        //method for setting the correct values to the layut form
        public View getView(int index,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.delivery_rating_layout, null,true);

            TextView rating_comment_text = (TextView) rowView.findViewById(R.id.rating_other_comment);
            TextView rating_rating_text = (TextView) rowView.findViewById(R.id.rating_other_rating);

            rating_comment_text.setText(comment.get(index));
            rating_rating_text.setText(rating.get(index));
            return rowView;

        };

    }
}
