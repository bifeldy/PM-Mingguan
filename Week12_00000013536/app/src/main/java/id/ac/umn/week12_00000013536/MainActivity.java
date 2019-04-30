package id.ac.umn.week12_00000013536;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver;

    public void createAlarm() {
        // Perhatikan bahwa class tujuannya bukan ke Activity, tapi menuju BroadcastReceiver
        Intent intentWithData = new Intent(getApplicationContext(), CustomBroadcastReceiver.class);

        // Data yang akan dipassing oleh intent
        intentWithData.putExtra("Data1", "Ini Data 1");
        intentWithData.putExtra("Data2", "Ini Data 2");

        // Membuat pending intent, perhatikan ada requestCode 0x1, optional, bisa digunakan apabila ada banyak PendingIntent yang digunakan pada Aplikasi
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0x1, intentWithData, 0);

        // AlarmManager merupakan class bawaan dari Android yang dapat digunakan bersamaan dengan PendingIntent
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // 5000 merupakan waktu dalam milisecond (5 detik)
        if(alarmManager != null) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);
        }
    }

    private void configureReceiver() {
        IntentFilter filter = new IntentFilter();

        // Hanya untuk filter package yang digunakan saja
        filter.addAction("id.ac.umn.week12_00000013536");

        // Downcasting BroadcastReceiver ke CustomBroadcastReceiver
        receiver = new CustomBroadcastReceiver();

        // Meregister receiver yang sudah dibuat
        registerReceiver(receiver, filter);
    }

    public void broadcastView(View view) {
        Intent intent = new Intent();

        // Samakan nama action dengan nama package yang kalian miliki
        intent.setAction("id.ac.umn.week12_00000013536");

        // Karena broadcast dapat dilakukan sekalipun aplikasi sedang dimatikan
        // Kita mengset flag ini agar dapat diterima sekalipun aplikasi mati
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        sendBroadcast(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureReceiver();
        createAlarm();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }
}
