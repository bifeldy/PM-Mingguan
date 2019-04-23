package id.ac.umn.week11_00000013536;

import android.os.AsyncTask;
import android.util.Log;

public class CustomServiceTask extends AsyncTask<Integer, Integer, String> {

    @Override
    protected String doInBackground(Integer... integers) {
        int startId = integers[0];
        int i = 0;

        while(i<=3){
            publishProgress(integers[0]);
            try{
                Thread.sleep(5000);
                i++;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return("Service selesai nomor " + startId);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s){
        Log.i("CUSTOMSERVICETASK", s);
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        Log.i("CUSTOMSERVICETASK", "Service berjalan nomor " + values[0]);
    }
}
