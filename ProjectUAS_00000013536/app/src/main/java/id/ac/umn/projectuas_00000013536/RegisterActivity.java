package id.ac.umn.projectuas_00000013536;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    // UI Object
    private ImageView imgRegisterScreen;
    private TextView txtTitleRegisterScreen, txtInfoRegisterScreen;
    private EditText txtUserRegisterScreen, txtPassRegisterScreen, txtRePassRegisterScreen;
    private Button btnLoginRegisterScreen, btnRegisterRegisterScreen;
    private LinearLayout rootViewRegisterScreen;

    // Database Connection
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Change Activity Page UI Toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Pendaftaran");
        }

        // Find UI
        imgRegisterScreen = findViewById(R.id.imgRegisterScreen);
        txtTitleRegisterScreen = findViewById(R.id.txtTitleRegisterScreen);
        txtInfoRegisterScreen = findViewById(R.id.txtInfoRegisterScreen);
        txtUserRegisterScreen = findViewById(R.id.txtUserRegisterScreen);
        txtPassRegisterScreen = findViewById(R.id.txtPassRegisterScreen);
        txtRePassRegisterScreen = findViewById(R.id.txtRePassRegisterScreen);
        btnLoginRegisterScreen = findViewById(R.id.btnLoginRegisterScreen);
        btnRegisterRegisterScreen = findViewById(R.id.btnRegisterRegisterScreen);
        rootViewRegisterScreen = findViewById(R.id.rootViewRegisterScreen);

        // Click Listener
        btnLoginRegisterScreen.setOnClickListener(this);
        btnRegisterRegisterScreen.setOnClickListener(this);

        // Keyboard Listener
        Utility.keyboardListener(rootViewRegisterScreen, imgRegisterScreen, txtTitleRegisterScreen);

        // Initialize Database
        mDBHelper = new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_default_menu, menu);
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

            case R.id.default_activity_menu_about:
                // Go To About Activity
                intent = new Intent(RegisterActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
        }

        // Back To Parent Activity
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // Button Login
            case R.id.btnLoginRegisterScreen:

                // Go Back To Login Activity
                finish();
                break;

            // Button Register
            case R.id.btnRegisterRegisterScreen:
                RegisterNewAccount(
                        txtUserRegisterScreen.getText().toString().trim().toLowerCase(),
                        txtPassRegisterScreen.getText().toString().trim().toLowerCase(),
                        txtRePassRegisterScreen.getText().toString().trim().toLowerCase()
                );
                break;
        }
    }

    private void RegisterNewAccount(String username, String password, String rePassword) {

        // Get Login Info
        String information = "";

        // Form Validation
        if(username.equals("") || password.equals("") || rePassword.equals("")) {
            information = "Harap Mengisi Dengan Benar!";
        }
        else {

            // Check User If Exist Try Login
            if(mDBHelper.userExist(username)) {
                information = "Username Telah Terpakai.";
            }
            else if (!password.equals(rePassword)) {
                information = "Password Yang Anda Masukkan Tidak Sesuai.";
            }
            else {
                mDBHelper.addUser(username, password);
                information = "Berhasil Mendaftarkan Akun " + username + " ^_^.~";
                Toast.makeText(RegisterActivity.this, information, Toast.LENGTH_LONG).show();
                finish();
            }
        }
        txtInfoRegisterScreen.setText(information);
    }
}
