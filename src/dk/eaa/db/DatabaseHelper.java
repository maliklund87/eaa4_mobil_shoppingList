package dk.eaa.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String waresUnit = "waresUnit";
    private static final String waresAmount = "waresAmount";


    public DatabaseHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + waresTable + " ("
                + waresId + " INTEGER PRIMARY KEY, "
                + waresName + " TEXT, "
                + waresUnit + " TEXT, "
                + waresAmount + " DECIMAL"
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
        return null;
    }
}
