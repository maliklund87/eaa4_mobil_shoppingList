package dk.eaa.db;

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
        db.execSQL("CREATE TABLE " + waresTable + " ("
                + waresId + " INTEGER PRIMARY KEY, "
                + waresName + " TEXT, "
                + waresPrice + " DECIMAL, "
                + waresUnit + " TEXT, "
                + waresAmount + " DECIMAL"
                + ")"
            );
        db.execSQL("CREATE TABLE " + shoppingListTable + "("
                + shoppingListItemId + " INTEGER PRIMARY KEY, "
                + shoppingListWareId + " INTEGER FOREIGN KEY, "
                + shoppingListQuantity + " INTEGER"
                + ")"
            );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + waresTable);
        onCreate(db);
    }

    public void createDefaultContent() {

    }

    public Cursor getShoppingList() {
        return null;
    }

    public Cursor getAllWares() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + waresId + " as _id, "
                + waresName + ", " + waresUnit + ", " + waresAmount + " "
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
        return ware;
    }

    public void updateWares(List<Ware> waresToUpdate) {

    }
}
