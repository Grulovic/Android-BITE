package com.example.sgrulovic.sg_final_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class DeliveryPics extends AppCompatActivity {

    //Variable and object declaration
    ImageView img;
    int index;
    Intent intent;
    Integer[] images = {R.drawable.logo1, R.drawable.logo2, R.drawable.logo3, R.drawable.logo4};//array of logos to check wether the correct images will be used in a real application
    Integer[] del_imgs = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6};//sample images to be used in a gridview that would normally be filled with specific images of the delivery place

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_pics);

        //Object declaration
        Button pic_back = (Button)findViewById(R.id.pic_back);

        //Grabbing the inde from the info activity
        index = getIntent().getExtras().getInt("index");
        del_imgs[0] = images[index];//setting the first image of the gridview to be the logo of the selected delivery place -> mostly done as a check

        //Declaring the grid views
        GridView grid = (GridView)findViewById(R.id.grid);
        final ImageView img = (ImageView)findViewById(R.id.pics_img); //declaring the image object that will be used to present the selected image from the gridview
        grid.setAdapter(new ImageAdapter(this));//Setting the custom made adapter

        //grabs the image selected in gridview and puts in the image object
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                img.setImageResource(del_imgs[position]);//sets the correct image
            }
        });

        //button to go back
        pic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DeliveryPics.this, DeliveryInfo.class);
                intent.putExtra("index",index);
                startActivity(intent);
            }
        });
    }

    //Custom imageadapter for the gridview
    public class ImageAdapter extends BaseAdapter{
        private Context context;

        public ImageAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return del_imgs.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            img = new ImageView(context);
            img.setImageResource(del_imgs[position]);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setLayoutParams(new GridView.LayoutParams(350,300));
            return img;
        }
    }
}