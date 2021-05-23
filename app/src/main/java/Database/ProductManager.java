package Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import Model.Product;

public class ProductManager {
    private static ProductManager instance;

    private static final String INSERT_STMT =
            "INSERT INTO " + DbSchema.ProductTable.NAME + "id, name, thumnail, unit_price) VALUES (?, ?, ?,?)";


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

    public List<Product> all(){
        String sql = "SELECT * FROM products";
        Cursor cursor = db.rawQuery(sql, null);
        ProductCursorWrapper cursorWrapper = new ProductCursorWrapper(cursor);

        return cursorWrapper.getProducts();

    }

}
