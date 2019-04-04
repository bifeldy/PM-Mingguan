package id.ac.umn.week07_00000013536_w8;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvDatabase;
    Button btnAdd;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDatabase = findViewById(R.id.main_text_databasedata);
        btnAdd = findViewById(R.id.main_button_add);
        btnDelete = findViewById(R.id.main_button_delete);

        viewData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // StudentDbHelper dbHelper = StudentDbHelper.getInstance(MainActivity.this);
                // dbHelper.addDummyData(
                //         "12002",
                //         "Monaco",
                //         "Monaco",
                //         "Monaco Monaco",
                //         "monaco@gmail.com"
                // );

                ContentValues contentValues = new ContentValues();
                contentValues.put(DBContract.StudentEntry.COLUMN_NAME_NIM, "12002");
                contentValues.put(DBContract.StudentEntry.COLUMN_NAME_FIRST_NAME, "Mo naco");
                contentValues.put(DBContract.StudentEntry.COLUMN_NAME_LAST_NAME, "Monaco");
                contentValues.put(DBContract.StudentEntry.COLUMN_NAME_ADDRESS, "Monaco Monaco");
                contentValues.put(DBContract.StudentEntry.COLUMN_NAME_EMAIL, "monaco@gmail.com");

                getContentResolver().insert(DBContract.StudentEntry.CONTENT_URI, contentValues);

                viewData();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDbHelper dbHelper = StudentDbHelper.getInstance(MainActivity.this);
                dbHelper.deleteLastDummyData();
                viewData();
            }
        });
    }

    void viewData() {
        StudentDbHelper dbHelper = StudentDbHelper.getInstance(this);

        // Cursor queryStudent = dbHelper.getData();
        Cursor queryStudent = getContentResolver().query(DBContract.StudentEntry.CONTENT_URI, null, null, null, null);

        StringBuilder dataStudent = new StringBuilder();
        while(queryStudent.moveToNext()) {
            dataStudent
                    .append("NIM = ")
                    .append(queryStudent.getString(queryStudent.getColumnIndex(
                            DBContract.StudentEntry.COLUMN_NAME_NIM
                    )))
                    .append("\n")
                    .append("FIRST NAME = ")
                    .append(queryStudent.getString(queryStudent.getColumnIndex(
                            DBContract.StudentEntry.COLUMN_NAME_FIRST_NAME
                    )))
                    .append("\n")
                    .append("LAST NAME = ")
                    .append(queryStudent.getString(queryStudent.getColumnIndex(
                            DBContract.StudentEntry.COLUMN_NAME_LAST_NAME
                    )))
                    .append("\n----------------------------------------------\n");
        }

        tvDatabase.setText(dataStudent);
    }
}
