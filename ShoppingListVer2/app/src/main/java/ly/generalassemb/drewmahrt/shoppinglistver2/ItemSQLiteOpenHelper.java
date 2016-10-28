package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 10/25/16.
 */
public class ItemSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String LIST_TABLE_NAME = "SHOPPING_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_ITEM_NAME = "ITEM_NAME";

    public static final String CREATE_LIST_TABLE =
            "CREATE TABLE " + LIST_TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ITEM_NAME + " TEXT)";

    private static ItemSQLiteOpenHelper mInstance;

    public static ItemSQLiteOpenHelper getInstance(Context context){
        if (mInstance == null){
            mInstance = new ItemSQLiteOpenHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private ItemSQLiteOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LIST_TABLE_NAME);
        this.onCreate(db);
    }

    // Query the database
    public List<ItemObject> getList(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(LIST_TABLE_NAME,
                new String[]{COL_ITEM_NAME},
                null, null, null, null, null);

        List<ItemObject> itemList = new ArrayList<>();

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                String name = cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME));

                ItemObject object = new ItemObject(name);
                itemList.add(object);

                cursor.moveToNext();
            }
        }
        cursor.close();
        return itemList;
    }

    // Search
    public List<ItemObject> searchForItem(String query){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(LIST_TABLE_NAME, // Table
                null, // Column Names
                COL_ITEM_NAME + " LIKE ?", // Selections
                new String[]{"'%"+query+"%'"}, // Selection Args
                null, // Group By
                null, // Having
                null, // Order By
                null); // Limit

        List<ItemObject> eachItem = new ArrayList<>();

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                String name = cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME));

                ItemObject object = new ItemObject(name);
                eachItem.add(object);

                cursor.moveToNext();
            }
        }
        cursor.close();
        return eachItem;
    }
}
