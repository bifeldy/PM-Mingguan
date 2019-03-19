package id.ac.umn.project_uts_00000013536_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jgabrielfreitas.core.BlurImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    String asin;
    BlurImageView blurImageView;

    // Tampung Data
    private BookHelper mDBHelper;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Ambil ID Buku
        Bundle bundle = getIntent().getExtras();
        asin = bundle.getString("asin");

        // External Library Orang Ini
        blurImageView = findViewById(R.id.book_image);
        blurImageView.setBlur(2);

        // Ambil Data Dari Database
        getData(asin);

        // Tampilkan Data Buku
        EditText bookAsin = findViewById(R.id.book_asin);
        bookAsin.setText(books.get(0).getAsin());

        EditText bookGroup = findViewById(R.id.book_group);
        bookGroup.setText(books.get(0).getGroup());

        EditText bookFormat = findViewById(R.id.book_format);
        bookFormat.setText(books.get(0).getFormat());

        EditText bookTitle = findViewById(R.id.book_title);
        bookTitle.setText(books.get(0).getTitle());

        EditText bookAuthor = findViewById(R.id.book_author);
        bookAuthor.setText(books.get(0).getAuthor());

        EditText bookPublisher = findViewById(R.id.book_publisher);
        bookPublisher.setText(books.get(0).getPublisher());

        final FloatingActionButton fab = findViewById(R.id.fab);
        if(books.get(0).getFavorite() == 1) {
            fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite));
        }
        else {
            fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_border));
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(books.get(0).getFavorite() == 0){
                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite));
                    books.get(0).setFavorite(1);
                    mDBHelper.setFavorite(books.get(0).getAsin(), 1);
                    Snackbar.make(view, "Added to favorite~ :: " + books.get(0).getAsin(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else {
                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_border));
                    books.get(0).setFavorite(0);
                    mDBHelper.setFavorite(books.get(0).getAsin(), 0);
                    Snackbar.make(view, "Removed from favorite~ :: " + books.get(0).getAsin(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
                intent = new Intent(DetailsActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
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

    private void getData(String asin) {
        // Cek Apakah Database Sudah Ada
        mDBHelper = new BookHelper(this);
        File database = getApplicationContext().getDatabasePath(BookHelper.DB_NAME);
        if (false == database.exists()) {
            mDBHelper.getReadableDatabase();
            // Copy DB
            if (!copyDatabase(this)) {
                Toast.makeText(this, "Gagal Membuka Database", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Isi Data Ke Dalam List
        books = mDBHelper.getBooks("ASIN = ?", new String[]{ asin }, null, null, null, null);
    }
}
