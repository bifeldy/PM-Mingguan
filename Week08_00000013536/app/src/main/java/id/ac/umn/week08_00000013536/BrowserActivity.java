package id.ac.umn.week08_00000013536;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Seharusnya network dan segala macam datanya tidak boleh digunakan secara langsung
        // pada Main Thread / Activity  hanya saja karena pembelajaran Thread / ASyncTask belum
        // diajarkan kita menambahkan kode ini.
        // Kode ini hanya untuk pembelajaran saja.

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_browser);
        Intent intent = getIntent();

        TextView txthmlCode = findViewById(R.id.browser_text_htmlcode);
        txthmlCode.setText("");

        // Untuk mendapatkan aksi yang digunakan oleh intent
        String action = intent.getAction();
        if (!action.equals(Intent.ACTION_VIEW)) {
            throw new RuntimeException("Sehagngnxa tidak boleh masuk ke sini");
        }

        // Untuk merender html ke dalam TextView ..
        Uri data = intent.getData();
        URL url;
        try {
            url = new URL(data.getScheme(), data.getHost(), data.getPath());
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );

            String htmlCode = "";
            while((htmlCode = bufferedReader.readLine()) != null) {
                txthmlCode.append(htmlCode);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
