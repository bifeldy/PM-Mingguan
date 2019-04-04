package id.ac.umn.week07_00000013536_w8;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class StudentProvider extends ContentProvider {

    private StudentDbHelper dbHelper;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static final int STUDENTS_ALL = 1;
    static final int STUDENTS_SPECIFIC = 2;

    static {
        uriMatcher.addURI(DBContract.CONTENT_AUTHORITY, DBContract.StudentEntry.TABLE_NAME, STUDENTS_ALL);
        uriMatcher.addURI(DBContract.CONTENT_AUTHORITY, DBContract.StudentEntry.TABLE_NAME + "/#", STUDENTS_SPECIFIC);
    }

    @Override
    public boolean onCreate() {
        dbHelper = StudentDbHelper.getInstance(getContext());
        return dbHelper != null;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            // Apabila melakukan query untuk seluruh students
            case STUDENTS_ALL:
                String[] queryProjection = {
                        DBContract.StudentEntry.COLUMN_NAME_NIM,
                        DBContract.StudentEntry.COLUMN_NAME_FIRST_NAME,
                        DBContract.StudentEntry.COLUMN_NAME_LAST_NAME
                };

                String querySelect = ""; // Misal: "fname=? AND lname=?"
                String[] querySelectArgs = {};

                cursor =
                        db.query(DBContract.StudentEntry.TABLE_NAME,
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                null
                        );
                return cursor;

            case STUDENTS_SPECIFIC:
                // Disini kita tidak melakukan query spesifik sehingga kita skip
                return null;
            default:
                return null;
        }
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch(uriMatcher.match(uri)) {
            case STUDENTS_ALL:
                long rowId = db.insert(
                        DBContract.StudentEntry.TABLE_NAME,
                        null,
                        values
                );

                if (rowId > 0) {
                    Uri returnUri = ContentUris.withAppendedId(DBContract.StudentEntry.CONTENT_URI, rowId);
                    getContext().getContentResolver().notifyChange(returnUri, null);
                    return returnUri;
                }

            case STUDENTS_SPECIFIC:
                return null;
            default:
                return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT MAX(" + DBContract.StudentEntry._ID + ") " +
                        "FROM " + DBContract.StudentEntry.TABLE_NAME, null
        );

        int maxID;
        if(cursor.moveToNext()) {
            maxID = cursor.getInt( 0) ;
        }
        else {
            return -1;
        }

        cursor.close();

        switch (uriMatcher.match(uri)) {
            case STUDENTS_ALL:
                db = dbHelper.getWritableDatabase();
                int deleteQuery = db.delete(
                        DBContract.StudentEntry.TABLE_NAME,
                        "_id = ?",
                    new String[] { String.valueOf(maxID) }
                ) ;
                return deleteQuery;
            case STUDENTS_SPECIFIC:
                return -1;
            default:
                return -1;
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
