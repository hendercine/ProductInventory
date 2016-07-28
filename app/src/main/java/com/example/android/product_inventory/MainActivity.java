package com.example.android.product_inventory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> productInventory = new ArrayList<Product>();
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
            @Override
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

    public void buttonClicked(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.add_product, null);
        final EditText setItemName = (EditText) alertLayout.findViewById(R.id.edit_item_name);
        final EditText setItemPrice = (EditText) alertLayout.findViewById(R.id.edit_item_price);
        final EditText setItemStock = (EditText) alertLayout.findViewById(R.id.edit_stock);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Add New Product");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "No items added", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String itemName = setItemName.getText().toString();
                String itemPrice = setItemPrice.getText().toString();
                String itemQty = setItemStock.getText().toString();
                Toast.makeText(getBaseContext(), itemQty + " " + itemName + ", costing $" + itemPrice + ", added", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
