package id.ac.umn.project_uts_00000013536_v2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BookHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "books.db";
    public static final String DB_LOCATION = "/data/data/id.ac.umn.project_uts_00000013536_v2/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public BookHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DB_NAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase != null) {
            mDatabase.close();
        }
    }

    public void setFavorite(String asin, int favorite) {
        openDatabase();
        mDatabase.execSQL("UPDATE books SET FAVORITE = " + favorite + " WHERE ASIN = '" + asin + "'");
        closeDatabase();
    }

    public List<Book> getBooks(String selection_, String[] selectionArgs_, String groupBy_, String having_, String orderBy_, String limit_) {
        List<Book> books = new ArrayList<>();
        openDatabase();

        String table = "books";

        // SELECT COLUMN1, COLUMN2, COLUMN3, ...
        String[] columns = { "`ASIN`", "`GROUP`", "`FORMAT`", "`TITLE`", "`AUTHOR`", "`PUBLISHER`", "`FAVORITE`" };

        // WHERE fname="Lucida" AND lname="Console"
        String selection = selection_; // MISAL: "fname=? AND lname=?"
        String[] selectionArgs = selectionArgs_; // MISAL: {"Lucida", "Console"}
        String groupBy = groupBy_;
        String having = having_;
        String orderBy = orderBy_;
        String limit = limit_;

        Cursor cursor = mDatabase.query(
                table,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy,
                limit
        );

        while(cursor.moveToNext()) {
            books.add(new Book(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6)
            ));
        }
        cursor.close();

        closeDatabase();
        return books;
    }
}
