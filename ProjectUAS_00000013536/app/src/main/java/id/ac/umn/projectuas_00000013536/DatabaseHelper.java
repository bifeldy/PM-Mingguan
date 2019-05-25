package id.ac.umn.projectuas_00000013536;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Credential.db";
    public static final String DB_LOCATION = "/data/data/id.ac.umn.projectuas_00000013536/databases/";

    // Shared Preferences
    private static final String PREFERENCES_FILENAME = "USER_INFORMATION";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static final String KEY_USERNAME = "USERNAME";
    private static final String KEY_PASSWORD = "PASSWORD";
    private static final int REQUEST_CODE = 200;

    private SharedPreferences userInfo;
    private SharedPreferences.Editor editor;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Get Data From Shared Preferences
        userInfo = mContext.getSharedPreferences(PREFERENCES_FILENAME, PREFERENCES_MODE);
        editor = userInfo.edit();

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

    public long addUser(String username, String password) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("user_name", username);
        contentValues.put("user_password", password);

        openDatabase();

        long result = mDatabase.insert("users", "null", contentValues);

        closeDatabase();
        return result;
    }

    public boolean userExist(String username) {

        // Table Name
        String table = "users";

        // SELECT COLUMN1, COLUMN2, COLUMN3, ...
        String[] columns = { "user_id", "user_name" };

        // WHERE fname="Lucida" AND lname="Console"
        String selection = "user_name=?"; // MISAL: "fname=? AND lname=?"
        String[] selectionArgs = { username }; // MISAL: {"Lucida", "Console"}

        openDatabase();

        Cursor cursor = mDatabase.query(
                table,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int count = cursor.getCount();

        cursor.close();
        closeDatabase();

        if(count > 0) {
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {

        // Table Name
        String table = "users";

        // SELECT COLUMN1, COLUMN2, COLUMN3, ...
        String[] columns = { "user_id", "user_name", "user_password" };

        // WHERE fname="Lucida" AND lname="Console"
        String selection = "user_name=? AND user_password=?"; // MISAL: "fname=? AND lname=?"
        String[] selectionArgs = { username, password }; // MISAL: {"Lucida", "Console"}

        openDatabase();

        Cursor cursor = mDatabase.query(
                table,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int count = cursor.getCount();

        cursor.close();
        closeDatabase();

        if(count == 1) {

            editor.putString(KEY_USERNAME, username);
            editor.putString(KEY_PASSWORD, password);

            // editor.commit();
            editor.apply();

            return true;
        }
        return false;
    }

    public void logout() {

        editor.putString(KEY_USERNAME, "");
        editor.putString(KEY_PASSWORD, "");

        // editor.commit();
        editor.apply();
    }

}
