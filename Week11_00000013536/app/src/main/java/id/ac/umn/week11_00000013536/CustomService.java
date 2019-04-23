package id.ac.umn.week11_00000013536;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class CustomService extends Service {
    public CustomService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");

        Log.i("CUSTOMSERVICE", "onBind: Service Bind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("CUSTOMSERVICE", "onCreate: CustomService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("CUSTOMSERVICE", "onStartCommand: " + startId);

        // int i = 0;
        // while (i<=3){
        //     try{
        //         Thread.sleep(5000);
        //         i++;
        //     }
        //     catch (InterruptedException e){
        //         Log.e("CUSTOMSERVICE", "onStartCommand: " + e.getMessage());
        //     }
        //     Log.i("CUSTOMSERVICE", "onStartCommand: " + "Service Running");
        // }

        AsyncTask customServiceTask = new CustomServiceTask().executeOnExecutor(
                AsyncTask.THREAD_POOL_EXECUTOR, startId
        );

        /* Nomor 13
        * -> Tidak
        * */

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i("CUSTOMSERVICE", "onDestroy: Service Destroyed!");
    }
}
