package com.example.sgrulovic.sg_final_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class DeliveryRating extends AppCompatActivity {

    //Variable declaration
    Intent intent;
    int index;
    int rating;
    String rating_comment;
    Integer[] images = {R.drawable.logo1, R.drawable.logo2, R.drawable.logo3, R.drawable.logo4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_rating);

        //Object declaration
        Button rating_back = (Button)findViewById(R.id.rating_back);
        Button rating_give = (Button)findViewById(R.id.rating_give);
        Button rating_other = (Button)findViewById(R.id.rating_other);
        final RadioButton star1 = (RadioButton)findViewById(R.id.star1);
        final RadioButton star2 = (RadioButton)findViewById(R.id.star2);
        final RadioButton star3 = (RadioButton)findViewById(R.id.star3);
        final RadioButton star4 = (RadioButton)findViewById(R.id.star4);
        final RadioButton star5 = (RadioButton)findViewById(R.id.star5);
        ImageView rating_logo = (ImageView)findViewById(R.id.rating_logo);
        final EditText rating_comment_edit = (EditText)findViewById(R.id.rating_comment);


        //grabbing the index from the intent
        index = getIntent().getExtras().getInt("index");



        switch (index) {
            case 0:
                rating_logo.setImageResource(images[0]);
                break;
            case 1:
                rating_logo.setImageResource(images[1]);
                break;
            case 2:
                rating_logo.setImageResource(images[2]);
                break;
            case 3:
                rating_logo.setImageResource(images[3]);
                break;
        }

        //declarin sharedprefrences and manager for storing the user rating and comment from before as well as using this example how the info can be extracted and sent
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int rating_stored = sharedPreferences.getInt("rating"+index, 10);
        String comment_stored = sharedPreferences.getString("comment"+index, "   Comment");

        //setting the previous comment if the user has done it before
        rating_comment_edit.setText(comment_stored, TextView.BufferType.EDITABLE);

        //setting the previous number rating if the user has done it before
        if(rating_stored == 1){
            star1.setChecked(true);
        }
        else if(rating_stored == 2){
            star2.setChecked(true);
        }
        else if(rating_stored == 3){
            star3.setChecked(true);
        }
        else if(rating_stored == 4){
            star4.setChecked(true);
        }
        else if(rating_stored == 5){
            star5.setChecked(true);
        }
        else{
            star1.setChecked(false);
            star2.setChecked(false);
            star3.setChecked(false);
            star4.setChecked(false);
            star5.setChecked(false);
        }


        //button for going back
        rating_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DeliveryRating.this, DeliveryInfo.class);
                intent.putExtra("index",index);
                startActivity(intent);
            }
        });

        //button for storing the correct rating and comment after the user has done
        rating_give.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();

                if(star1.isChecked()){
                    rating = 1;
                }
                else if(star2.isChecked()){
                    rating = 2;
                }
                else if(star3.isChecked()){
                    rating = 3;
                }
                else if(star4.isChecked()){
                    rating = 4;
                }
                else if(star5.isChecked()){
                    rating = 5;
                }
                else{
                    rating = 10;
                }

                rating_comment = rating_comment_edit.getText().toString();

                //storing the comment on the phone
                editor.putString("comment_stored" + index, rating_comment);
                editor.putString("rating_stored" + index, "RATING: " + rating);
                editor.commit();

                //toast message for displaying a message
                Toast.makeText(DeliveryRating.this, "Thank you for leaving a rating! ", Toast.LENGTH_LONG).show();
            }
        });

        //button to go to view other peoples ratings
        rating_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DeliveryRating.this, DeliveryRatingOther.class);
                intent.putExtra("index",index);
                startActivity(intent);
            }
        });






    }
}
