package dk.eaa.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import dk.eaa.Ware;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Malik
 * Date: 05-10-12
 * Time: 09:42
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseHelper extends SQLiteOpenHelper  {

    private static final String dbName = "shoppingDB";

    private static final String idCounterTable = "idTable";
    private static final String idCounterId = "idCounterId";
    private static final String idCounterValue = "idCounterValue";
    private static final int idID = 1;

    private static final String waresTable = "waresTable";
    private static final String waresId = "waresId";
    private static final String waresName = "waresName";
    private static final String waresPrice = "waresPrice";
    private static final String waresUnit = "waresUnit";
    private static final String waresAmount = "waresAmount";

    private static final String shoppingListTable = "shoppingListTable";
    private static final String shoppingListItemId = "shoppingListItemId";
    private static final String shoppingListWareId = "shoppingListWareId";
    private static final String shoppingListQuantity = "shoppingListQuantity";


    public DatabaseHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE " + idCounterTable + " ("
//                + idCounterId + " INTEGER PRIMARY KEY, "
//                + idCounterValue + " INTEGER)"
//        );
//
//        ContentValues cv = new ContentValues();
//        cv.put(idCounterId, idID);
//        cv.put(idCounterValue, 0);
//        db.insert(idCounterTable, idCounterId, cv);

        db.execSQL("CREATE TABLE " + waresTable + " ("
                + waresId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + waresName + " TEXT, "
                + waresPrice + " DECIMAL, "
                + waresUnit + " TEXT, "
                + waresAmount + " DECIMAL"
                + ")"
        );
        db.execSQL("CREATE TABLE " + shoppingListTable + "("
                + shoppingListItemId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + shoppingListWareId + " INTEGER, "
                + shoppingListQuantity + " INTEGER"
                + ")"
        );

        createDefaultContent(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + waresTable);
        onCreate(db);
    }

    public void createDefaultContent(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(waresName, "Vare01");
        cv.put(waresAmount, 500);
        cv.put(waresPrice, 25);
        cv.put(waresUnit, "kg");
        db.insert(waresTable, waresId, cv);
    }

    public Cursor getShoppingList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + shoppingListItemId + " as _id, "
                + shoppingListWareId + ", " + shoppingListQuantity
                + " FROM " + shoppingListTable, new String[]{}
            );
        return cur;
    }

    public Cursor getAllWares() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + waresId + " as _id, "
                + waresName + ", " + waresPrice + ", " + waresUnit + ", " + waresAmount + " "
                + "FROM " + waresTable, new String[]{}
            );
        return cur;
    }

    public Ware getWare(int wareId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + waresTable + " WHERE " + wareId + " = " + wareId, new String[]{});
        Ware ware = new Ware(cursor.getString(cursor.getColumnIndex(waresName)));
        ware.setPrice(cursor.getDouble(cursor.getColumnIndex(waresPrice)));
        ware.setAmount(cursor.getDouble(cursor.getColumnIndex(waresAmount)));
        ware.setUnit(cursor.getString(cursor.getColumnIndex(waresUnit)));
        ware.setId(cursor.getInt(cursor.getColumnIndex(waresId)));
        return ware;
    }

    /**
     * Looks up an ID counter in the database and increments.
     * @return
     */
    private int createId() {
        // get current id
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + idCounterTable
                + " WHERE " + idCounterId + " = " + idID,
                new String[]{});
        int id = cursor.getInt(cursor.getColumnIndex(idCounterValue));

        // increment id
        ContentValues cv = new ContentValues();
        cv.put(idCounterId, idID);
        cv.put(idCounterValue, id++);
        db.insert(idCounterTable, idCounterId, cv);

        return id;
    }

    public void saveWare(Ware ware) {
//        ware.setId(createId());

        ContentValues cv = new ContentValues();
//        cv.put(waresId, ware.getId());
        cv.put(waresName, ware.getName());
        cv.put(waresPrice, ware.getPrice());
        cv.put(waresUnit, ware.getUnit());
        cv.put(waresAmount, ware.getAmount());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(waresTable, waresId, cv);
    }

    public void updateWares(List<Ware> waresToUpdate) {

    }
}
