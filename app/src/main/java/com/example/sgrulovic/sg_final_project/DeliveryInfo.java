package com.example.sgrulovic.sg_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DeliveryInfo extends AppCompatActivity {

    //Variable declaration
    Intent intent;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_info);

        //Get the intents from the ListView
        index = getIntent().getExtras().getInt("index");

        //Objects declaration
        TextView del_desc = (TextView)findViewById(R.id.del_desc);
        ImageView logo = (ImageView)findViewById(R.id.del_logo);
        Button del_pics = (Button)findViewById(R.id.del_pics);
        Button del_menu = (Button)findViewById(R.id.del_menu);
        Button del_order = (Button)findViewById(R.id.del_order);
        Button del_back = (Button)findViewById(R.id.del_back);
        Button del_rating = (Button)findViewById(R.id.del_rating);

        //Grabbin the delivery place description
        String[] del_place_desc = getResources().getStringArray(R.array.del_places_description);
        //Declaring the logos of the delivery places
        Integer[] images = {R.drawable.logo1, R.drawable.logo2, R.drawable.logo3, R.drawable.logo4};

        //Setting the correct delivery place description
        del_desc.setText(del_place_desc[index]);

        //Seting the image based on the index from the selected delivery place
        logo.setImageResource(images[index]);

        //Creating the button listeners for all the options of the deliveryinfo to take them to the right activity and send the correct index
        del_pics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DeliveryInfo.this, DeliveryPics.class);
                intent.putExtra("index",index);
                startActivity(intent);
            }
        });

        del_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DeliveryInfo.this, DeliveryMenu.class);
                intent.putExtra("index",index);
                startActivity(intent);
            }
        });

        del_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DeliveryInfo.this, DeliveryOrder.class);
                intent.putExtra("index",index);
                startActivity(intent);
            }
        });

        del_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeliveryInfo.this, DeliveryList.class));
            }
        });

        del_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DeliveryInfo.this, DeliveryRating.class);
                intent.putExtra("index",index);
                startActivity(intent);
            }
        });

    }
}
