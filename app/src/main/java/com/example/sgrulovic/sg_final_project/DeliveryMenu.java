package com.example.sgrulovic.sg_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import static android.icu.lang.UCharacter.toUpperCase;

public class DeliveryMenu extends AppCompatActivity {

    //Variable Declaration
    Intent intent;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_menu);

        //Grabbing the index from the intent
        index = getIntent().getExtras().getInt("index");

        //Variable Declaration
        final TextView result = (TextView)findViewById(R.id.menu_text);
        Button menu_back = (Button)findViewById(R.id.menu_back);
        Integer[] del_menu_arrays = {R.array.d1_menu, R.array.d2_menu, R.array.d3_menu, R.array.d4_menu}; //array of delivery places menues

        //Grabbing the correct menu of the selected delivery place and storing it to a
        //separate array that will be used in an adapter for the listview
        String[] del_place_menu = getResources().getStringArray(del_menu_arrays[index]);
        String[] del_place = getResources().getStringArray(R.array.del_places_string_array);
        //Setting the menu text
        result.setText( toUpperCase( del_place[index] ) + " MENU: ");

        //Make the Adapter for the listview
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, del_place_menu);
        //Declare and initialize the ListView
        ListView the_list_menu = (ListView)findViewById(R.id.menu_list_view);
        the_list_menu.setAdapter(adapter1);//setting the adapter for the listview

        //If the user click on the item from the menu it takes them to the order activity to order the selected item
        the_list_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                intent = new Intent(DeliveryMenu.this, DeliveryOrder.class);
                intent.putExtra("menu_item",index);
                startActivity(intent);
            }
        });

        //button for going back
        menu_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DeliveryMenu.this, DeliveryInfo.class);
                intent.putExtra("index",index);
                startActivity(intent);
            }
        });
    }
}
