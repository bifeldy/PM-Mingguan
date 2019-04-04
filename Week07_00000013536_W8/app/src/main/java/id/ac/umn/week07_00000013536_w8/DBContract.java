package id.ac.umn.week07_00000013536_w8;

import android.net.Uri;
import android.provider.BaseColumns;

public class DBContract {

    // Konstruktor tidak boleh digunakan
    private DBContract() {}

    // Samakan dengan nama package Anda pada saat pembuatan aplikasi
    public static final String CONTENT_AUTHORITY = "id.ac.umn.week07_00000013536_w8";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "students";
        public static final String COLUMN_NAME_NIM = "nim";
        public static final String COLUMN_NAME_FIRST_NAME = "fname";
        public static final String COLUMN_NAME_LAST_NAME = "lname";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_EMAIL = "email";

        // Disini kita mendefinisikan untuk menggunakan data_type yang sama dengan nama tabel student
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        // Disini kita akan menambahkan id untuk Uri yang digunakan menjadi content://id.ac.umn.week07_00000013536_w8/students/[id]
        public static final Uri buildUri(long id) {
            return  CONTENT_URI.buildUpon().appendPath(Long.toString(id)).build();
        }
    }
}
