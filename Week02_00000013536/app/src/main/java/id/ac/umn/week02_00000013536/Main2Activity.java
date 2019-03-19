package id.ac.umn.week02_00000013536;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    private static int REQUEST_CODE = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView txtNama = findViewById(R.id.textNama);
        TextView txtPass = findViewById(R.id.textPass);
        Button btnBalik = findViewById(R.id.btnBalik);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String nama = bundle.getString("nama");
            String pass = bundle.getString("pass");

            txtNama.setText(nama);
            txtPass.setText(pass);
        }

        btnBalik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("balik", "00000013536");
                setResult(REQUEST_CODE, intent);
                finish();
            }
        });
    }
}
