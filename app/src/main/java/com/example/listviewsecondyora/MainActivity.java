package com.example.listviewsecondyora;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static float SELECTED_ITEM_TRANSLATION_X = 100;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.activity_main_listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        CustomerAdapter adapter = new CustomerAdapter();
        for(int i=0; i<30; i++){
            adapter.add(new Customer("Customer_" + Integer.toString(i)));
        }
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                float translationX;
                if(listView.isItemChecked(i)){
                    translationX = SELECTED_ITEM_TRANSLATION_X;
                }else {
                    translationX = 0;
                }
                view.animate().translationX(translationX).setDuration(250).start();
            }
        });
    }

    private class CustomerAdapter extends ArrayAdapter<Customer> {

        public CustomerAdapter() {
            super(MainActivity.this, R.layout.list_item_customer);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //注意这些 View 是循环的，所以在做动画的时候，要注意
            ViewHolder viewHolder;

            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.list_item_customer,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.NameTextView = (TextView)convertView.findViewById(R.id.list_item_customer_name);
                convertView.setTag(viewHolder);

            }else {
                viewHolder = (ViewHolder) convertView.getTag();
                convertView.clearAnimation();
                if(listView.isItemChecked(position)){
                    convertView.setTranslationX(SELECTED_ITEM_TRANSLATION_X);
                }else {
                    convertView.setTranslationX(0);
                }

            }
            Customer customer = getItem(position);
            viewHolder.NameTextView.setText(customer.getName());
            return  convertView;
        }

        private class ViewHolder {
            public TextView NameTextView;
        }
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
