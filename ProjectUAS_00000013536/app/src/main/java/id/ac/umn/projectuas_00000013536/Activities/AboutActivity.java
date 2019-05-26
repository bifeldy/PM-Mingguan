package id.ac.umn.projectuas_00000013536.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import id.ac.umn.projectuas_00000013536.R;

public class AboutActivity extends AppCompatActivity {

    // UI Object
    private TextView aboutLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Change Activity Page UI Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find UI
        aboutLibrary = findViewById(R.id.about_library);

        // Change UI text
        aboutLibrary.setText(
            "com.android.support:design:28.0.0\n" +
            "com.android.support:recyclerview-v7:28.0.0\n" +
            "com.android.support:cardview-v7:28.0.0\n" +
            "\n" +
            "com.github.bumptech.glide:glide:4.9.0\n" +
            "com.android.volley:volley:1.1.1" +
            "\n"
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // For Open Intent
        Intent intent;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

        // Back To Parent Activity
        finish();
        return super.onOptionsItemSelected(item);
    }
}
