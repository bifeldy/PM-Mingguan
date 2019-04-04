package id.ac.umn.week07_00000013536_w8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class StudentDbHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Student.db";
    private static StudentDbHelper sInstance;

    private static final String SQL_CREATE_QUERY = "CREATE TABLE " +
            DBContract.StudentEntry.TABLE_NAME + "(" +
            DBContract.StudentEntry._ID + " INTEGER PRIMARY KEY," +
            DBContract.StudentEntry.COLUMN_NAME_NIM + " TEXT, " +
            DBContract.StudentEntry.COLUMN_NAME_FIRST_NAME + " TEXT, " +
            DBContract.StudentEntry.COLUMN_NAME_LAST_NAME + " TEXT, " +
            DBContract.StudentEntry.COLUMN_NAME_ADDRESS + " TEXT, " +
            DBContract.StudentEntry.COLUMN_NAME_EMAIL + " TEXT " + ")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.StudentEntry.TABLE_NAME;

    private static final String SQL_DUMMY_ENTRIES = "INSERT INTO " +
            DBContract.StudentEntry.TABLE_NAME + " (" +
            DBContract.StudentEntry.COLUMN_NAME_NIM + "," +
            DBContract.StudentEntry.COLUMN_NAME_FIRST_NAME + "," +
            DBContract.StudentEntry.COLUMN_NAME_LAST_NAME + "," +
            DBContract.StudentEntry.COLUMN_NAME_ADDRESS + "," +
            DBContract.StudentEntry.COLUMN_NAME_EMAIL + ") VALUES " +
            "('1000', 'Lucida', 'Consolas', 'Lucida Console', 'lucidaconsole@gmail.com')," +
            "('1001', 'Helvetica', 'Helvetica', 'Helvetica Helvetica', 'helvetica@gmail.com')";

    private StudentDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_QUERY);
        db.execSQL(SQL_DUMMY_ENTRIES);
    }

    public static synchronized StudentDbHelper getInstance(Context context) {
        if(sInstance == null) {
            sInstance = new StudentDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public Cursor getData() {
        SQLiteDatabase db = sInstance.getReadableDatabase();

        // Data apa yang akan ditampilkan
        // SELECT COLUMN1, COLUMN2, COLUMN3, ...
        String[] projection = {
                DBContract.StudentEntry.COLUMN_NAME_NIM,
                DBContract.StudentEntry.COLUMN_NAME_FIRST_NAME,
                DBContract.StudentEntry.COLUMN_NAME_LAST_NAME
        };

        // Digunakan dalam pemilihan data
        // WHERE fname="Lucida" AND lname="Console"
        String selection = ""; // MISAL: "fname=? AND lname=?"
        String[] selectionArgs = {}; // MISAL: {"Lucida", "Console"}

        Cursor cursor = db.query(
                DBContract.StudentEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        return cursor;
    }

    public long addDummyData(String nim, String fname, String lname, String address, String email) {

        SQLiteDatabase db = sInstance.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.StudentEntry.COLUMN_NAME_NIM, nim);
        contentValues.put(DBContract.StudentEntry.COLUMN_NAME_FIRST_NAME, fname);
        contentValues.put(DBContract.StudentEntry.COLUMN_NAME_LAST_NAME, lname);
        contentValues.put(DBContract.StudentEntry.COLUMN_NAME_ADDRESS, address);
        contentValues.put(DBContract.StudentEntry.COLUMN_NAME_EMAIL, email);
        long result = db.insert(
            DBContract.StudentEntry.TABLE_NAME,
            null,
            contentValues
        );
        if(result == -1) {
            //
        }
        else {
            //
        }

        return result;
    }

    public int deleteLastDummyData() {
        SQLiteDatabase db = sInstance.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT MAX(" + DBContract.StudentEntry._ID + ") " +
                "FROM " + DBContract.StudentEntry.TABLE_NAME, null
        );

        int maxID;
        if(cursor.moveToNext()) {
            maxID = cursor.getInt(0);
        }
        else {
            return -1;
        }

        cursor.close();

        db = sInstance.getWritableDatabase();
        int result = db.delete(
                DBContract.StudentEntry.TABLE_NAME,
                "_id = ?",
        new String[] {String.valueOf(maxID)}
        );

        return result;
    }
}
