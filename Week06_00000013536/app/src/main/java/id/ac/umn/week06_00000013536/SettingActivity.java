package id.ac.umn.week06_00000013536;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILENAME = "settings";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static final String KEY_NAMA = "NAMA";
    private static final String KEY_PASS = "PASS";
    private static final String KEY_SWITCH = "SWITCH";

    private static final int REQUEST_CODE = 200;

    EditText edtNama;
    EditText edtPass;
    Switch swMenu;

    boolean isMenu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        edtNama = findViewById(R.id.setting_edittext_nama);
        edtPass = findViewById(R.id.setting_edittext_password);

        swMenu = findViewById(R.id.setting_switch_menushow);
        swMenu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isMenu = isChecked;
            }
        });

        Button btnSimpan = findViewById(R.id.setting_button_simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILENAME, PREFERENCES_MODE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String nama = edtNama.getText().toString();
                String pass = edtPass.getText().toString();

                editor.putString(KEY_NAMA, nama);
                editor.putString(KEY_PASS, pass);
                editor.putBoolean(KEY_SWITCH, isMenu);

                // editor.commit();
                editor.apply();
                setResult(RESULT_OK);
                finish();
            }
        });
    }


}
