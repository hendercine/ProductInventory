package com.example.android.product_inventory;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hendercine on 7/25/16.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Activity context, ArrayList<Product> products) {
        super(context, 0, products);
    }

    ProductDbHandler db = new ProductDbHandler(getContext());

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final Product currentProduct = getItem(position);

        Button btnBuy = (Button) listItemView.findViewById(R.id.list_order_prod);

        TextView tvProductName = (TextView) listItemView.findViewById(R.id.list_product_name);
        tvProductName.setText(currentProduct.getName());

        final TextView tvProductQty = (TextView) listItemView.findViewById(R.id.list_product_stock);
        tvProductQty.setText(Integer.toString(currentProduct.getStock()));

        TextView tvProductPrice = (TextView) listItemView.findViewById(R.id.list_product_price);
        tvProductPrice.setText("$" + Float.toString(currentProduct.getPrice()));

        final TextView tvProductSold = (TextView) listItemView.findViewById(R.id.list_product_sold);
        tvProductSold.setText(Integer.toString(currentProduct.getSales()));

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(tvProductQty.getText().toString());
                int sold = Integer.parseInt(tvProductSold.getText().toString());

                if (qty > 0) {
                    qty--;
                    sold++;
                    currentProduct.setSales(qty);
                    currentProduct.setStock(sold);
                    tvProductQty.setText(Integer.toString(qty));
                    tvProductSold.setText(Integer.toString(sold));
                    db.updateProduct(currentProduct);
                }

            }
        });

        return listItemView;
    }
}
