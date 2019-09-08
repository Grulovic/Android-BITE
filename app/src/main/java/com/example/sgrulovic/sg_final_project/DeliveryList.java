package com.example.sgrulovic.sg_final_project;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DeliveryList extends Activity {

    ListView the_list;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_list);

        //grabbin the delivery places names and thei descriptions
        final String[] Delivery_Places = getResources().getStringArray(R.array.del_places_string_array);//which are grabbed from the xml file
        final String[] Delivery_Places_desc = getResources().getStringArray(R.array.del_places_description);//which are grabbed from the xml file
        Integer[] images = {R.drawable.logo1, R.drawable.logo2, R.drawable.logo3, R.drawable.logo4};//array of logos to check wether the correct images will be used in a real application

        //declaring the custom adapter with the correct values and data for the layout form
        CustomListAdapter adapter=new CustomListAdapter(this, Delivery_Places, images, Delivery_Places_desc);
        the_list=(ListView)findViewById(R.id.the_list);//declaring the listview
        the_list.setAdapter(adapter);//combining the listview and the adapter

        //on clicl of the list item/delivery place it takes them to the info activity with the correct information using the index
        the_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                intent = new Intent(DeliveryList.this, DeliveryInfo.class);//To go from one activiry to another
                intent.putExtra("DelName", Delivery_Places[index]);//sending the name of the delivery place
                intent.putExtra("index",index);//sending the index of the delivery place
                startActivity(intent);//starting the intent
            }
        });
    }


    //custom adapter for the listview
    class CustomListAdapter extends ArrayAdapter<String> {
        //variable declaration
        private final Activity context;
        private final String[] delname;
        private final Integer[] dellogo;
        private final String[] deldesc;

        //Constructor for the adapter
        public CustomListAdapter(Activity context, String[] delname, Integer[] dellogo, String[] deldesc) {
            super(context, R.layout.delivery_list_layout, delname);

            this.context=context;
            this.delname=delname;
            this.dellogo=dellogo;
            this.deldesc=deldesc;
        }
        //method for setting the correct values and data to the layout form
        public View getView(int index,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.delivery_list_layout, null,true);

            TextView del_place_name = (TextView) rowView.findViewById(R.id.del_place_name);
            ImageView del_logo = (ImageView) rowView.findViewById(R.id.del_place_logo);
            TextView del_place_desc = (TextView) rowView.findViewById(R.id.del_place_desc);

            del_place_name.setText(delname[index]);
            del_logo.setImageResource(dellogo[index]);
            del_place_desc.setText(deldesc[index]);
            return rowView;

        };

    }
}
