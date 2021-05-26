package Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteStatement;
import android.net.Uri;

import java.util.List;

import Model.Product;

public class ProductManager  {
    private static ProductManager instance;

    private static final String INSERT_STMT =
            "INSERT INTO " + DbSchema.ProductTable.NAME + "(id, name, thumbnail, unit_price, quantity) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_QUANTITY = "UPDATE " + DbSchema.ProductTable.NAME +
                                                    " SET " + DbSchema.ProductTable.Cols.QUANTITY +
                                                    " = " + "( " + DbSchema.ProductTable.Cols.QUANTITY + " +1" + " )" +
                                                    "WHERE " + DbSchema.ProductTable.Cols.ID + " = " + " ? ";



    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public static ProductManager getInstance(Context context){
        if(instance == null){
            instance = new ProductManager(context);
        }
        return instance;
    }

    public ProductManager(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean addProduct(Product product){
        SQLiteStatement stm = db.compileStatement(INSERT_STMT);

        stm.bindLong(1, product.getId());

        stm.bindString(2, product.getName());
        stm.bindString(3, product.getThumbnail());
        stm.bindDouble(4, product.getUnitPrice());
        stm.bindString(5, product.getQuantity() +"");

        long id = stm.executeInsert();
        if(id > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean updateQuantity(Product product){
        SQLiteStatement stmq = db.compileStatement(UPDATE_QUANTITY);

            return true;

    }


    public Product findProductById(long productId){
        String sql = "SELECT * FROM " + DbSchema.ProductTable.NAME + " WHERE "+  DbSchema.ProductTable.Cols.ID+ " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{productId+""});

        ProductCursorWrapper cursorWrapper = new ProductCursorWrapper(cursor);

        return cursorWrapper.getProductByID();
    }

    public List<Product> getAllData(){
        String sql = "SELECT * FROM " + DbSchema.ProductTable.NAME;
        Cursor cursor = db.rawQuery(sql, null);
        ProductCursorWrapper cursorWrapper = new ProductCursorWrapper(cursor);
        return cursorWrapper.getProducts();
    }



}
