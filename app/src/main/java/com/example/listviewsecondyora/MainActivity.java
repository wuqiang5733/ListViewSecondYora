package com.example.listviewsecondyora;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ArrayAdapter<Customer> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice
//                android.R.layout.simple_list_item_single_choice
        );

        final ListView listView = (ListView) findViewById(R.id.activity_main_listView);
        listView.setAdapter(adapter);
         for (int i=0; i<20; i++) {
             adapter.add(new Customer("Customer_" + Integer.toString(i)));
         }
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Customer customer = adapter.getItem(i);

                StringBuilder builder = new StringBuilder();
                SparseBooleanArray checkPositions = listView.getCheckedItemPositions();
                for (int j=0;j<adapter.getCount();j++){
                    if(checkPositions.get(j)){
                        Customer selectedCustomer = adapter.getItem(j);
                        builder.append(selectedCustomer.getName() + " ");
                    }
                }

                Toast.makeText(MainActivity.this,"Selected " + builder.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
