package com.example.android.product_inventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> productInventory = new ArrayList<>();
    private ListView listView;
    private TextView textView;
    private ProductAdapter adapter;
    private ProductDbHandler db = new ProductDbHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addItemBtn = (Button) findViewById(R.id.add_item_btn);

        listView = (ListView) findViewById(R.id.product_list);
        adapter = new ProductAdapter(MainActivity.this, productInventory);
        listView.setAdapter(adapter);

        if (db.getProductsCount() == 0) {
            textView = (TextView) findViewById(R.id.no_product);
            textView.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);

            productInventory.addAll(db.getAllProducts());
            adapter.notifyDataSetChanged();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ProductDetails.class);
                intent.putExtra("class", productInventory.get(position));
                startActivityForResult(intent, 0);
            }
        });



        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddProduct.class);

                startActivityForResult(i, 0);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(listView.getVisibility() == View.GONE)
        {
            listView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
        adapter.clear();
        productInventory.clear();
        productInventory.addAll(db.getAllProducts());
        adapter.notifyDataSetChanged();

        if(productInventory != null)
        {
            if(productInventory.size() == 0)
            {
                listView.setVisibility(View.GONE);
            }
            else
            {
                listView.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            listView.setVisibility(View.GONE);
        }
    }

//    @Override
//    protected void onDestroy() {
//        //Delete entire Database
//        Log.d("Deleting: ", "Deleting entire Database");
//        db.close();
//        db.deleteDatabase();
//        super.onDestroy();
//    }

}

