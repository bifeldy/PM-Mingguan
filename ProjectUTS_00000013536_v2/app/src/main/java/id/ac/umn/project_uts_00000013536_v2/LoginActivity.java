package id.ac.umn.project_uts_00000013536_v2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    // Shared Preferences
    private static final String PREFERENCES_FILENAME = "USER_INFORMATION";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static final String KEY_USERNAME = "USERNAME";
    private static final String KEY_PASSWORD = "PASSWORD";
    private static final int REQUEST_CODE = 200;
    SharedPreferences userInfo;

    // User Interface
    private EditText txtUser;
    private EditText txtPass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ganti Nama TitleBar
        getSupportActionBar().setTitle("Login");

        // Ambil Objek Form Login
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);

        // Ambil Data Dari Shared Preferences Untuk Login
        userInfo = getSharedPreferences(PREFERENCES_FILENAME, PREFERENCES_MODE);
        String userNameInfo = userInfo.getString(KEY_USERNAME, "");
        String userPassInfo = userInfo.getString(KEY_PASSWORD, "");

        // Masukkan Data Login Ke Form Login Kalau Ada
        txtUser.setText(userNameInfo);
        txtPass.setText(userPassInfo);

        // Coba Untuk Login Dari Sesi Sebelumnya Kalau Ada
        boolean loginTest = CobaLogin();
        if(loginTest) {
            LoginBerhasil();
        }

        // Saat Tombol Login Di Pencet
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loginTest = CobaLogin();
                if(loginTest) {
                    LoginBerhasil();
                    Toast.makeText(LoginActivity.this, "Hai, " + txtUser.getText().toString() + "! ^_^", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LoginActivity.this,"Username / Password Salah.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.detail_activity_menu_about:
                intent = new Intent(LoginActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Login Test
    private boolean CobaLogin() {
        if(txtUser.getText().toString().equals(getResources().getString(R.string.userId)) && txtPass.getText().toString().equals(getResources().getString(R.string.userPass))) {
            return true;
        }
        else {
            return false;
        }
    }

    // Ganti Ke MainActivity
    private void LoginBerhasil() {
        Intent intent = new Intent(LoginActivity.this, DataActivity.class);
        startActivityForResult(intent, REQUEST_CODE);

        // Ambil Data Dari Shared Preferences
        SharedPreferences.Editor editor = userInfo.edit();

        String userNameInfo = txtUser.getText().toString();
        String userPassInfo = txtPass.getText().toString();

        editor.putString(KEY_USERNAME, userNameInfo);
        editor.putString(KEY_PASSWORD, userPassInfo);

        // editor.commit();
        editor.apply();
        setResult(RESULT_OK);
        finish();
    }
}

