package id.ac.umn.week02_00000013536;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText edtNama;
    EditText edtPass;
    Button btnMasuk;

    private static int REQUEST_CODE = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNama = findViewById(R.id.edtPertama);
        edtPass = findViewById(R.id.edtKedua);
        btnMasuk = findViewById(R.id.btnPertama);

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent( MainActivity.this, Main2Activity.class);

                intent.putExtra("nama", edtNama.getText().toString());
                intent.putExtra("pass", edtPass.getText().toString());

                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            String theMessage = data.getStringExtra("balik");
            edtNama.setText(theMessage);
        }
    }
}
