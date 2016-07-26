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
        public static final String TABLE_NAME = "Inventory Info";
        // Table Columns names
        public static final String COLUMN_PRODUCT_ID = "Product Id";
        public static final String COLUMN_PRODUCT_NAME = "Product Name";
        public static final String COLUMN_PRODUCT_IMAGE = "Product Pic";
        public static final String COLUMN_PRODUCT_PRICE = "Price";
        public static final String COLUMN_PRODUCT_STOCK = "Available Stock";
        public static final String COLUMN_PRODUCT_SALES = "Sales";
        public static final String COLUMN_SUPPLIER_CONTACT = "Supplier Contact Info";
    }
}
