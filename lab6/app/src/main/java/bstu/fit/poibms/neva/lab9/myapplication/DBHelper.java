package bstu.fit.poibms.neva.lab9.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "lab6Db";
    public static final String TABLE_Languages = "language";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE;
        CREATE_CONTACTS_TABLE = "create table " + TABLE_Languages + "("
                + KEY_ID + " integer primary key autoincrement, " + KEY_NAME
                + " text not null);";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    void add(Values lang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, lang.getValue());
        db.insert(TABLE_Languages, null, values);
        db.close();
    }
    void select(){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Languages);
        onCreate(db);
    }

}