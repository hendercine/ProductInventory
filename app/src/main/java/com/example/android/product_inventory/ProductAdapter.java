package com.example.android.product_inventory;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Hendercine on 7/25/16.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    Context context;
    ArrayList<Product> products = new ArrayList<>();

    public ProductAdapter(Activity context, ArrayList<Product> products) {
        super(context, 0, products);
        this.context = context;
        this.products = products;
    }

    ProductDbHandler db = new ProductDbHandler(getContext());

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        ImageHolder holder;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

            holder = new ImageHolder();
            holder.imgIcon = (ImageView)listItemView.findViewById(R.id.image_thumb);
            listItemView.setTag(holder);
        }
        else
        {
            holder = (ImageHolder)listItemView.getTag();
        }

        Product picture = products.get(position);
        //convert byte to bitmap take from contact class

        byte[] outImage=picture.getImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imgIcon.setImageBitmap(theImage);


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

static class ImageHolder
{
    ImageView imgIcon;
}
}
