package com.example.android.product_inventory;

import android.provider.BaseColumns;

/**
 * Created by Hendercine on 7/25/16.
 */
public class ProductContract {
    // Database Name
    public static final String DATABASE_NAME = "ProductInventory";
    // Database Version
    public static final int DATABASE_VERSION = 1;

    public class ProductEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "inventoryInfo";
        // Table Columns names
        public static final String COLUMN_PRODUCT_ID = "productId";
        public static final String COLUMN_PRODUCT_NAME = "productName";
        public static final String COLUMN_PRODUCT_IMAGE = "productPic";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_STOCK = "availableStock";
        public static final String COLUMN_PRODUCT_SALES = "sales";
        public static final String COLUMN_SUPPLIER_CONTACT = "supplierContactInfo";
    }
}
