package id.ac.umn.week08_00000013536;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAction1 = findViewById(R.id.main_button_action1);
        Button btnAction2 = findViewById(R.id.main_button_action2);

        btnAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.umn.ac.id";
                Intent i = new Intent();
                i.setAction (Intent.ACTION_VIEW);
                i.setClass(getApplicationContext(), BrowserActivity.class);
                i.setData(Uri.parse(url));

                // Test PopUp
//                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    String[] contactPermission = new String[] {
//                            Manifest.permission.INTERNET
//                    };
//                    ActivityCompat.requestPermissions(MainActivity.this, contactPermission, 0);
//                }

                startActivity(i);
            }
        });

        btnAction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.umn.ac.id"));
                // Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:(+62)54220808"));
                // Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-6.2568921,106.6180266?z=19.17"));
                // Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Universitas%20Multimedia%20Nusantara"));
                // Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/2"));
                startActivity(i);
            }
        });
    }
}
