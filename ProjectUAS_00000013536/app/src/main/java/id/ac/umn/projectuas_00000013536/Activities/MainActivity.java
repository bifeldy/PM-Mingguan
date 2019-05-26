package id.ac.umn.projectuas_00000013536.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import id.ac.umn.projectuas_00000013536.DatabaseHelper;
import id.ac.umn.projectuas_00000013536.Fragments.SeasonalAnimeFragment;
import id.ac.umn.projectuas_00000013536.Fragments.TopAnimeFragment;
import id.ac.umn.projectuas_00000013536.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Shared Preferences
    private static final String PREFERENCES_FILENAME = "USER_INFORMATION";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static final String KEY_USERNAME = "USERNAME";
    private SharedPreferences userInfo;

    // Database Helper
    private DatabaseHelper mDBHelper;

    // Action Bar
    private Toolbar toolbar;

    // Left NavMenu
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    // Custom Setting For 2x Back To Close App
    private boolean doubleBackToExitPressedOnce = false;

    // UI Object
    private ImageView userImg;
    private TextView userName, userEmail;

    /*
    Snackbar snackBar;

    // SnackBar
    snackBar = Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_INDEFINITE);
    snackBar.setAction("Dismiss", new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Close SnackBar
            snackBar.dismiss();
        }
    });
    snackBar.show();

    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find UI
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Setting Up Navigation Menu
        toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Force To Open List Of Product When App Firstly Opened
        navigationView.setCheckedItem(R.id.nav_seasonal);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_seasonal));

        // Get Data Shared Preferences For Login
        userInfo = getSharedPreferences(PREFERENCES_FILENAME, PREFERENCES_MODE);

        // Initialize DB Helper
        mDBHelper = new DatabaseHelper(this);

        // Find UI
        userImg = navigationView.getHeaderView(0).findViewById(R.id.userImg);
        userName = navigationView.getHeaderView(0).findViewById(R.id.userName);
        userEmail = navigationView.getHeaderView(0).findViewById(R.id.userEmail);

        // Load User Login Info Into Nav Menu Profile
        Glide.with(this)
            .load("https://jrznog.dm.files.1drv.com/y4mebt7-M1sS28d2MWGIFeO3rcdWbDua4NIFA3O5H6OqyBoxWcAR_P-Dxd2fxucS9TeH2btYKmn_VA91qemaumk0H7gvitAoBd9bOKkPpCb5-_LEDYaJQBk67JKPi1o059dSfK2zUu2HZDyP9_T8O7LtbaPO90vdGVhsKtY-QTqV5P9rAuiJgJRYBQD282arPNv8T4zIeKKcx8zj6qHgHbX8g")
            .transition(DrawableTransitionOptions.withCrossFade(1234)) // Transition Effect Load Image
            .apply(
                new RequestOptions().circleCropTransform()
                .override(85, 85)
            ).into(userImg);
        userName.setText(userInfo.getString(KEY_USERNAME, getText(R.string.nav_header_title).toString()));
        userEmail.setText(getText(R.string.nav_header_subtitle).toString());
    }

    @Override
    public void onBackPressed() {

        // When NavMenu Opened
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            // Close It First
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (doubleBackToExitPressedOnce) {

            // 2x Back Button Pressed To Close App
            super.onBackPressed();
        }
        else {

            // In Other Menu Except Menu 1
            if (!navigationView.getMenu().getItem(0).isChecked()) {

                // Back To Menu 1 First
                navigationView.setCheckedItem(R.id.nav_seasonal);
                onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_seasonal));
            }
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Tombol BACK Lagi Untuk Keluar", Toast.LENGTH_SHORT).show();

        // Handler Back Button Checker Can Close App Within 1 Second On 2x Press
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // Reset Condition
                doubleBackToExitPressedOnce = false;
            }
        }, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        else if(id == R.id.action_main_logout) {

            // Logout & Go To Login Screen
            mDBHelper.logout();
            // Go To About Activity
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            Toast.makeText(MainActivity.this, "Selamat Tinggal >_<\"", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }
        else if (id == R.id.action_main_about) {

            // Go To About Activity
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_seasonal) {

            // Show Seasonal Anime Fragment
            getSupportActionBar().setTitle(R.string.menu_seasonal);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SeasonalAnimeFragment()).commit();
        }
        else if(id == R.id.nav_top) {

            // Show Top Anime Fragment
            getSupportActionBar().setTitle(R.string.menu_top);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new TopAnimeFragment()).commit();
        }
        else if(id == R.id.nav_about) {

            // Go To About Activity
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
