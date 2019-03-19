package id.ac.umn.week06_00000013536;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILENAME = "settings";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static final String KEY_NAMA = "NAMA";
    private static final String KEY_PASS = "PASS";
    private static final String KEY_SWITCH = "SWITCH";

    private static final int REQUEST_CODE = 200;

    TextView textNama;
    TextView textPass;
    TextView textSwitch;
    TextView textNamaPrefs;
    TextView textPassPrefs;
    TextView textSwitchPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNama = findViewById(R.id.main_text_setting01);
        textPass = findViewById(R.id.main_text_setting02);
        textSwitch = findViewById(R.id.main_text_setting03);
        textNamaPrefs = findViewById(R.id.main_text_preference01);
        textPassPrefs = findViewById(R.id.main_text_preference02);
        textSwitchPrefs = findViewById(R.id.main_text_preference03);
        Button btnPage = findViewById(R.id.main_button_settingpage);

        cetakTulisan();

        // SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILENAME, PREFERENCES_MODE);

        // String nama = sharedPreferences.getString(KEY_NAMA, "UNDEFINED");
        // String pass = sharedPreferences.getString(KEY_PASS, "UNDEFINED");
        // boolean isMenu = sharedPreferences.getBoolean(KEY_SWITCH, false);

        // textNama.setText(nama);
        // textPass.setText(pass);
        // textSwitch.setText(String.valueOf(isMenu));

        btnPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    void cetakTulisan() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILENAME, PREFERENCES_MODE);

        String nama = sharedPreferences.getString(KEY_NAMA, "UNDEFINED");
        String pass = sharedPreferences.getString(KEY_PASS, "UNDEFINED");
        boolean isMenu = sharedPreferences.getBoolean(KEY_SWITCH, false);

        textNama.setText(nama);
        textPass.setText(pass);
        textSwitch.setText(String.valueOf(isMenu));

        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String namaPref = sharedPreferences1.getString(KEY_NAMA, "UNDEFINED");
        String passPref = sharedPreferences1.getString(KEY_PASS, "UNDEFINED");
        boolean isMenuPref = sharedPreferences1.getBoolean(KEY_SWITCH, false);

        textNamaPrefs.setText(namaPref);
        textPassPrefs.setText(passPref);
        textSwitchPrefs.setText(String.valueOf(isMenuPref));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            cetakTulisan();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_settings:
                Intent intent = new Intent(MainActivity.this, Setting2Activity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
