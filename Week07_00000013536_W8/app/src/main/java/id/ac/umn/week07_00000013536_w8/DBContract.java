package id.ac.umn.week07_00000013536;

import android.provider.BaseColumns;

public class DBContract {

    // Konstruktor tidak boleh digunakan
    private DBContract() {}

    public static class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "students";
        public static final String COLUMN_NAME_NIM = "nim";
        public static final String COLUMN_NAME_FIRST_NAME = "fname";
        public static final String COLUMN_NAME_LAST_NAME = "lname";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_EMAIL = "email";
    }
}
