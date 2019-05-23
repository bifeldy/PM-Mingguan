package id.ac.umn.projectuas_00000013536;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    // Shared Preferences
    private static final String PREFERENCES_FILENAME = "USER_INFORMATION";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static final String KEY_USERNAME = "USERNAME";
    private static final String KEY_PASSWORD = "PASSWORD";
    private static final int REQUEST_CODE = 200;
    SharedPreferences userInfo;

    // Animation Resource
    private Animation animation;

    // UI Object
    private ImageView imgSplashScreen;
    private TextView txtSplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Find UI
        imgSplashScreen = findViewById(R.id.imgSplashScreen);
        txtSplashScreen = findViewById(R.id.txtSplashScreen);

        // Load Animasi Fade Out HandMade Hehe
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Run Animate
        imgSplashScreen.startAnimation(animation);
        txtSplashScreen.startAnimation(animation);

        // Thread For Loading Splash Animation Delay
        new Thread(new Runnable() {
            public void run() {

                try {

                    // Delay 2 Second Showing Logo & Login
                    Thread.sleep(2000);
                    checkLoginInfo();
                } catch( InterruptedException e ) {

                    // Print Error
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // Running Under Thread
    public void checkLoginInfo() {


    }

    public void loginSuccess() {

        // Go To Main Apps
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
