package Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.List;

import Model.Product;

public class ProductCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ProductCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Product getProduct(){
        int id =  getInt((getColumnIndex(DbSchema.ProductTable.Cols.ID)));
        String name =  getString((getColumnIndex(DbSchema.ProductTable.Cols.NAME)));
        String thumnail =  getString((getColumnIndex(DbSchema.ProductTable.Cols.THUMNAIL)));
        int unit_price =  getInt((getColumnIndex(DbSchema.ProductTable.Cols.UNIT_PRICE)));

        Product product = new Product(id, name, thumnail, unit_price);

        return product;
    }

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();

        moveToFirst();
        while (!isAfterLast()){
            Product product = getProduct();
            products.add(product);

            moveToNext();
        }
        products.clear();

        return  products;
    }


    public Product getProductByID(){
        Product product = null;
        moveToFirst();
        if(!isAfterLast()){
            product = getProduct();
        }

        return product;
    }
}
