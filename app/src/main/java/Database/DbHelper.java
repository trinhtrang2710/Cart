package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "products.db";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DbSchema.ProductTable.NAME+ "(" +
                DbSchema.ProductTable.Cols.ID + "INTEGER PRIMARY KEY," +
                DbSchema.ProductTable.Cols.NAME + "TEXT, "+
                DbSchema.ProductTable.Cols.THUMNAIL +"TEXT," +
                DbSchema.ProductTable.Cols.UNIT_PRICE + "INTERGER" + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("Products", "Products: upgrading DB; dropping/recreating tables.");
        db.execSQL("DROP TABLE IF EXISTS " + DbSchema.ProductTable.NAME);

        onCreate(db);
    }
}
