package id.ac.umn.project_uts_00000013536_v2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    // Shared Preferences
    private static final String PREFERENCES_FILENAME = "USER_INFORMATION";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static final String KEY_ORDER_FAVORITES = "ORDER_FAVORITES";
    private static final int REQUEST_CODE = 200;
    SharedPreferences userInfo;
    SharedPreferences.Editor editor;

    // Tampung Data
    private BookHelper mDBHelper;
    private List<Book> books;
    private String sortQuery;

    // RecyclerView
    RecyclerView.LayoutManager layoutManager;
    BookRecyclerAdapter bookRecyclerAdapter;
    RecyclerView rvwMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Ambil Data Dari Shared Preferences Untuk Login
        userInfo = getSharedPreferences(PREFERENCES_FILENAME, PREFERENCES_MODE);
        sortQuery = userInfo.getString(KEY_ORDER_FAVORITES, "") ;

        // Ambil Data
        cetakData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_favorites_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.favorite_activity_menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setMinimumWidth(Integer.MIN_VALUE);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                bookRecyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.favorite_activity_menu_sort:
                final String[] orderBy = getResources().getStringArray(R.array.action_order_by);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Sort By");
                builder.setIcon(R.drawable.ic_sort);
                int checkedItemSort = -1;
                if(userInfo.getString(KEY_ORDER_FAVORITES, "").equals("")) {
                    checkedItemSort = -1;
                }
                else if(userInfo.getString(KEY_ORDER_FAVORITES, "").equals(orderBy[0])) {
                    checkedItemSort = 0;
                }
                else if(userInfo.getString(KEY_ORDER_FAVORITES, "").equals(orderBy[1])) {
                    checkedItemSort = 1;
                }
                else if(userInfo.getString(KEY_ORDER_FAVORITES, "").equals(orderBy[2])) {
                    checkedItemSort = 2;
                }
                builder.setSingleChoiceItems(R.array.action_order_by, checkedItemSort, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Ambil Data Dari Shared Preferences
                        editor = userInfo.edit();
                        editor.putString(KEY_ORDER_FAVORITES, orderBy[which]);
                        editor.apply();
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sortQuery = userInfo.getString(KEY_ORDER_FAVORITES, "");
                        setResult(RESULT_OK);
                        cetakData();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNeutralButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Ambil Data Dari Shared Preferences
                        editor = userInfo.edit();
                        editor.putString(KEY_ORDER_FAVORITES, null);
                        editor.apply();
                        sortQuery = null;
                        setResult(RESULT_OK);
                        cetakData();
                    }
                });
                builder.create().show();
                break;

            case R.id.favorite_activity_menu_about:
                intent = new Intent(FavoritesActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        // Reload RecyclerView Kalau Back / Buka Ulang Intent
        cetakData();
    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(BookHelper.DB_NAME);
            String outFileName = BookHelper.DB_LOCATION + BookHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void cetakData() {
        // Cek Apakah Database Sudah Ada
        mDBHelper = new BookHelper(this);
        File database = getApplicationContext().getDatabasePath(BookHelper.DB_NAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            // Copy DB
            if(!copyDatabase(this)) {
                Toast.makeText(this, "Gagal Membuka Database", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Isi Data Ke Dalam List
        books = new ArrayList<>();
        books = mDBHelper.getBooks("FAVORITE = ?", new String[]{ "1" },null,null,sortQuery,null);

        // Tampilkan Data Pada Recycler
        layoutManager = new LinearLayoutManager(FavoritesActivity.this);
        bookRecyclerAdapter = new BookRecyclerAdapter(this, R.layout.row_data, books);
        rvwMain = findViewById(R.id.row_favorite_activity);
        rvwMain.setLayoutManager(layoutManager);
        rvwMain.setAdapter(bookRecyclerAdapter);
        rvwMain.addItemDecoration(new DividerItemDecoration(rvwMain.getContext(), DividerItemDecoration.VERTICAL));
    }
}
