package id.ac.umn.week03_00000013536_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtNama;
    Button btnHalaman1, btnHalaman2;
    private static int REQUEST_CODE = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LIFECYCLE", "onCreate");

        edtNama = findViewById(R.id.main_edittext_nama);
        btnHalaman1 = findViewById(R.id.main_button_change_1);
        btnHalaman2 = findViewById(R.id.main_button_change_2);

        btnHalaman1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent( MainActivity.this, Main2Activity.class);

                // Kalo Mau Passing Data

                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btnHalaman2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent( MainActivity.this, Main3Activity.class);

                // Kalo Mau Passing Data

                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFECYCLE", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LIFECYCLE", "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLE", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LIFECYCLE", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LIFECYCLE", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LIFECYCLE", "onDestroy");
    }

    /*
     * Soal No. 8
     * 2019-02-14 21:40:29.832 3240-3240/id.ac.umn.week03_00000013536 D/LIFECYCLE: onCreate
     * 2019-02-14 21:40:29.886 3240-3240/id.ac.umn.week03_00000013536 D/LIFECYCLE: onStart
     * 2019-02-14 21:40:29.895 3240-3240/id.ac.umn.week03_00000013536 D/LIFECYCLE: onResume
     * */

    /*
     * Soal No. 9
     * 2019-02-14 21:41:47.879 3240-3240/id.ac.umn.week03_00000013536 D/LIFECYCLE: onPause
     * 2019-02-14 21:41:47.987 3240-3240/id.ac.umn.week03_00000013536 D/LIFECYCLE: onStop
     * */

    /*
     * Soal No. 10
     * 2019-02-14 21:42:46.169 3240-3240/id.ac.umn.week03_00000013536 D/LIFECYCLE: onRestart
     * 2019-02-14 21:42:46.249 3240-3240/id.ac.umn.week03_00000013536 D/LIFECYCLE: onStart
     * 2019-02-14 21:42:46.328 3240-3240/id.ac.umn.week03_00000013536 D/LIFECYCLE: onResume
     * */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("LIFECYCLE", "onSaveInstanceState");

        outState.putCharSequence("TEXT", edtNama.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("LIFECYCLE", "onRestoreInstanceState");

        CharSequence TEXT = savedInstanceState.getCharSequence("TEXT");
        edtNama.setText(TEXT);
    }

    /*
     * Soal No. 9
     * Teks Masih Tersimpan Karena InstanceStatenya Di Simpan Ke Dalam TEXT
     * */

}
