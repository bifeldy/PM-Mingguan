package id.ac.umn.week11_00000013536;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CustomIntentService extends IntentService {

    // Constructor
    public CustomIntentService() {
        // String Digunakan Untuk Debugging
        super("CustomIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Task dari Service nantinya akan ditaruh disini
        Log.i("INTENTSERVICE", "onHandledIntent: IntentSrvice dimulai!");

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        String jsonString = null;

        try{
            String urlString = "http://10.0.1.31:10000/requestdata?loggeduser=user";

            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();

            if(inputStream != null){
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line + "\n");
                }

                jsonString = stringBuffer.toString();

                Log.d("FETCHDATA", jsonString);
            }
        }
        catch(Exception e){
            Log.e("EXCEPTION", "ErrorMessage" + e.getMessage());
        }
    }
}
