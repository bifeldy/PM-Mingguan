package id.ac.umn.week12_00000013536;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class CustomBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");

        // Untuk mengambil data dari Intent (bila ada)
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            // Tampilkan data pada Intent bila ada (PendingIntent)
            Toast.makeText(context, bundle.getString("Data1"), Toast.LENGTH_SHORT).show();
        }
        else {
            // Tampilkan data bila mendapatkan broadcast intent
            String message = "Mendeteksi adanya Broadcast Intent";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
