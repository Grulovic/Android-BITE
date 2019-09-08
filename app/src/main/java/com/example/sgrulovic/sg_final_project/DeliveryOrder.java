package com.example.sgrulovic.sg_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.toUpperCase;

public class DeliveryOrder extends AppCompatActivity {

    //Variable Declaration
    Intent intent;
    int index, menu_item, amount, phone;
    String request, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_order);

        //Edit texts to grab the user entered information
        final EditText order_amount = (EditText)findViewById(R.id.order_amount);
        final EditText order_request = (EditText)findViewById(R.id.order_request);
        final EditText order_address = (EditText)findViewById(R.id.order_address);
        final EditText order_phone = (EditText)findViewById(R.id.order_phone);

        //Variable Declaration
        Button order_button = (Button)findViewById(R.id.order_button);
        Button order_back = (Button)findViewById(R.id.order_back);
        final TextView order_title = (TextView)findViewById(R.id.order_title);

        //index to get the intent
        index = getIntent().getExtras().getInt("index");

        //array of the menus that will be added to the spinner
        Integer[] del_menu_arrays = {R.array.d1_menu, R.array.d2_menu, R.array.d3_menu, R.array.d4_menu};
        final String[] del_place_menu = getResources().getStringArray(del_menu_arrays[index]);//adding the correct menu to a separate array that will be added to the spinner

        //Adapter for the spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, del_place_menu);
        final Spinner del_menu_spinner = (Spinner)findViewById(R.id.order_spinner);//spinner declaration
        del_menu_spinner.setAdapter(adapter1);//combining the adapter and the spinner

        //Grabbing the menu item if the user has selected an item from the menu activity
        menu_item = getIntent().getExtras().getInt("menu_item");
        del_menu_spinner.setSelection(menu_item);//setting the spinner to that position

        String[] del_place = getResources().getStringArray(R.array.del_places_string_array);
        //Setting the menu text
        order_title.setText( toUpperCase( del_place[index] ) + " ORDER: ");

        //Button that will store the order on the phone that in real life application can be sent to the delivery place
        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Integer.parseInt(order_amount.getText().toString());
                request = order_request.getText().toString();
                phone = Integer.parseInt(order_amount.getText().toString());
                address = order_request.getText().toString();
                //Toast message to display what the user has ordered
                Toast.makeText(DeliveryOrder.this, "You have ordered " + amount + " times "
                        + del_place_menu[(int)del_menu_spinner.getSelectedItemId()] + ". And your custom request was: " + request
                        + " . On the address: " + address + " and phone: " + phone + ". ", Toast.LENGTH_LONG).show();
            }
        });

        //Button to go back
        order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DeliveryOrder.this, DeliveryInfo.class);
                intent.putExtra("index", index);
                startActivity(intent);

            }
        });


    }
}
