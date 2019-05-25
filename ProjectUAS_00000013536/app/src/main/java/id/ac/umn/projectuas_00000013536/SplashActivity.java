package id.ac.umn.projectuas_00000013536;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    // Shared Preferences
    private static final String PREFERENCES_FILENAME = "USER_INFORMATION";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static final String KEY_USERNAME = "USERNAME";
    private static final String KEY_PASSWORD = "PASSWORD";

    private SharedPreferences userInfo;
    private String userNameInfo, userPassInfo;

    // Animation Resource
    private Animation animation;

    // UI Object
    private ImageView imgSplashScreen;
    private TextView txtSplashScreen, txtInfoSplashScreen;
    private EditText txtUserSplashScreen, txtPassSplashScreen;
    private Button btnLoginSplashScreen, btnRegisterSplashScreen, btnAboutSplashScreen;
    private LinearLayout rootViewSplashScreen;

    // Database Helper
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Find UI
        imgSplashScreen = findViewById(R.id.imgSplashScreen);
        txtSplashScreen = findViewById(R.id.txtTitleSplashScreen);
        txtInfoSplashScreen = findViewById(R.id.txtInfoSplashScreen);
        txtUserSplashScreen = findViewById(R.id.txtUserSplashScreen);
        txtPassSplashScreen = findViewById(R.id.txtPassSplashScreen);
        btnLoginSplashScreen = findViewById(R.id.btnLoginSplashScreen);
        btnRegisterSplashScreen = findViewById(R.id.btnRegisterSplashScreen);
        btnAboutSplashScreen = findViewById(R.id.btnAboutSplashScreen);
        rootViewSplashScreen = findViewById(R.id.rootViewSplashScreen);

        // Click Listener
        btnLoginSplashScreen.setOnClickListener(this);
        btnRegisterSplashScreen.setOnClickListener(this);

        // Default
        txtInfoSplashScreen.setVisibility(View.GONE);
        txtUserSplashScreen.setVisibility(View.GONE);
        txtPassSplashScreen.setVisibility(View.GONE);
        btnLoginSplashScreen.setVisibility(View.GONE);
        btnRegisterSplashScreen.setVisibility(View.GONE);
        btnAboutSplashScreen.setOnClickListener(this);

        // Import Database
        mDBHelper = new DatabaseHelper(this);
        Utility.importDatabase(this, mDBHelper);

        // Keyboard Listener
        Utility.keyboardListener(rootViewSplashScreen, imgSplashScreen, txtSplashScreen);

        // Load Animasi Fade Out HandMade Hehe
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Run Animate
        imgSplashScreen.startAnimation(animation);
        txtSplashScreen.startAnimation(animation);

        // Get Data Shared Preferences For Login
        userInfo = getSharedPreferences(PREFERENCES_FILENAME, PREFERENCES_MODE);
        userNameInfo = userInfo.getString(KEY_USERNAME, "");
        userPassInfo = userInfo.getString(KEY_PASSWORD, "");

        // Put It Into Form
        txtUserSplashScreen.setText(userNameInfo);
        txtPassSplashScreen.setText(userPassInfo);

        // Thread For Loading Splash Animation Delay
        new Thread(new Runnable() {
            public void run() {
                try {

                    // Delay 2.5 Second Showing Logo & Login
                    Thread.sleep(2500);

                    // Check Login Status
                    if(!isLogin()) {

                        // Show Info Text
                        txtInfoSplashScreen.post(new Runnable() {
                            @Override
                            public void run() {

                                // Show TextView
                                txtInfoSplashScreen.startAnimation(animation);
                                txtInfoSplashScreen.setVisibility(View.VISIBLE);
                            }
                        });

                        // Show TextBox Username
                        txtUserSplashScreen.post(new Runnable() {
                            @Override
                            public void run() {

                                // Show TextBox
                                txtUserSplashScreen.startAnimation(animation);
                                txtUserSplashScreen.setVisibility(View.VISIBLE);
                            }
                        });

                        // Show TextBox Password
                        txtPassSplashScreen.post(new Runnable() {
                            @Override
                            public void run() {

                                // Show TextBox
                                txtPassSplashScreen.startAnimation(animation);
                                txtPassSplashScreen.setVisibility(View.VISIBLE);
                            }
                        });


                        // Show Button Login
                        btnLoginSplashScreen.post(new Runnable() {
                            @Override
                            public void run() {

                                // Show Button
                                btnLoginSplashScreen.startAnimation(animation);
                                btnLoginSplashScreen.setVisibility(View.VISIBLE);
                            }
                        });

                        // Show Button Register
                        btnRegisterSplashScreen.post(new Runnable() {
                            @Override
                            public void run() {

                                // Show Button
                                btnRegisterSplashScreen.startAnimation(animation);
                                btnRegisterSplashScreen.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                    else {
                        loginSuccess();
                    }

                }
                catch( InterruptedException e ) {

                    // Print Error
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {

        // For Open New Activity
        Intent intent;
        switch (v.getId()) {

            // Button About
            case R.id.btnAboutSplashScreen:

                // Go To About Activity
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;

            // Button Login
            case R.id.btnLoginSplashScreen:
                loginAccount(
                        txtUserSplashScreen.getText().toString().trim().toLowerCase(),
                        txtPassSplashScreen.getText().toString().trim().toLowerCase()
                );
                break;

            // Button Register
            case R.id.btnRegisterSplashScreen:

                // Go To Register Activity
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean isLogin() {

        if(!userNameInfo.equals("") && !userPassInfo.equals("")) {
            return true;
        }
        return false;
    }

    private void loginAccount(String username, String password) {

        // Get Login Info
        String information = "";

        // Form Validation
        if(username.equals("") || password.equals("")) {
            information = "Harap Mengisi Dengan Benar!";
        }
        else {

            // Check User If Exist Try Login
            if (mDBHelper.userExist(username)) {
                if(mDBHelper.login(username, password)) {
                    information = "Selamat Datang, " + username + "! ^_^.~";
                    Toast.makeText(SplashActivity.this, information, Toast.LENGTH_LONG).show();
                    loginSuccess();
                }
                else {
                    information = "Username Atau Password Salah.";
                }
            }
            else {
                information = "Username Tidak Dapat Ditemukan~\nSilahkan Daftar Terlebih Dahulu!";
            }
        }
        txtInfoSplashScreen.setText(information);
    }

    private void loginSuccess() {

        // Go To Main Activity
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//        finish();
    }
}
