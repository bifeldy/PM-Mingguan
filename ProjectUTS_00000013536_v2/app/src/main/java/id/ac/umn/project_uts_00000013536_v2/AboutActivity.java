package id.ac.umn.project_uts_00000013536_v2;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.felipecsl.gifimageview.library.GifImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AboutActivity extends AppCompatActivity {

    GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        gifImageView = findViewById(R.id.gifImage);
        new RetrieveByteArray().execute("https://jrznog.dm.files.1drv.com/y4mebt7-M1sS28d2MWGIFeO3rcdWbDua4NIFA3O5H6OqyBoxWcAR_P-Dxd2fxucS9TeH2btYKmn_VA91qemaumk0H7gvitAoBd9bOKkPpCb5-_LEDYaJQBk67JKPi1o059dSfK2zUu2HZDyP9_T8O7LtbaPO90vdGVhsKtY-QTqV5P9rAuiJgJRYBQD282arPNv8T4zIeKKcx8zj6qHgHbX8g");
        gifImageView.startAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.about_activity_menu_linkedin:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/bifeldy/"));
                startActivity(intent);
                break;

            case R.id.about_activity_menu_twitter:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Bifeldy"));
                startActivity(intent);
                break;

            case R.id.about_activity_menu_facebook:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Bifeldy"));
                startActivity(intent);
                break;

            case R.id.about_activity_menu_github:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/bifeldy"));
                startActivity(intent);
                break;

            case R.id.about_activity_menu_line:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://line.me/ti/p/~Bifeldy"));
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class RetrieveByteArray extends AsyncTask<String, Void, byte[]> {

        @Override
        protected byte[] doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode() == 200) {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    int nRead;
                    byte[] data = new byte[10240];
                    while((nRead = in.read(data, 0, data.length)) != -1) {
                        buffer.write(data, 0, nRead);
                    }
                    buffer.flush();
                    return buffer.toByteArray();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);
            gifImageView.setBytes(bytes);
        }
    }
}
