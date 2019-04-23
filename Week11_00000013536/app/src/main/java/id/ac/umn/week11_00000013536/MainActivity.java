package id.ac.umn.week11_00000013536;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CustomBoundService customBoundService;
    // Untuk mengecek apakah BoundService sudah terikat atau belum
    boolean isBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // Mengambil Service yang sudah dibuat sebelumnya
            CustomBoundService.CustomLocalBinder binder = (CustomBoundService.CustomLocalBinder) service;

            customBoundService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, CustomIntentService.class);
        startService(intent);

        /* Nomor 9
        * -> YA
        * */

        Button btnStartService = findViewById(R.id.main_button_startservice);
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomService.class);
                startService(intent);
            }
        });

        /* Nomor 9
        * -> YA
        */

        // Membuat BoundService dan mengikatnya pada Activity
        Intent intent2 = new Intent(this, CustomBoundService.class);
        bindService(intent2, serviceConnection, Context.BIND_AUTO_CREATE);

        Button btnShowTime = findViewById(R.id.main_button_startservice);
        btnShowTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String currentTime = customBoundService.getCurrentTime();

                Toast.makeText(getApplicationContext(), currentTime, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
