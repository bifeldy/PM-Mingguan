package id.ac.umn.projectuas_00000013536.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import id.ac.umn.projectuas_00000013536.APIs.DetailAPI;
import id.ac.umn.projectuas_00000013536.APIs.SeasonAPI;
import id.ac.umn.projectuas_00000013536.R;
import id.ac.umn.projectuas_00000013536.Utility;

public class DetailActivity extends AppCompatActivity {

    // Save Passed Id From Main Fragment
    private int mal_id;

    // Anime
    DetailAPI detailAPI;

    // UI Object
    private ImageView detail_anime_image;
    private TextView detail_anime_title;
    private TextView detail_anime_title_romanji;
    private TextView detail_anime_title_japanese;
    private TextView detail_anime_score;
    private TextView detail_anime_scored_by;
    private TextView detail_anime_type;
    private TextView detail_anime_status;
    private TextView detail_anime_episodes;
    private TextView detail_anime_aired_from;
    private TextView detail_anime_aired_to;
    private TextView detail_anime_premiered_broadcast;
    private TextView detail_anime_synopsis;
    private TextView detail_anime_source;
    private TextView detail_anime_duration;
    private TextView detail_anime_rating;
    private TextView detail_anime_rank;
    private TextView detail_anime_popularity;
    private TextView detail_anime_members;
    private TextView detail_anime_favorites;

    private TextView detail_information;
    FloatingActionButton detail_floating_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Change Activity Page UI Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get Id From Main Fragment
        Bundle bundle = getIntent().getExtras();
        mal_id = bundle.getInt("mal_id");

        // Find UI
        detail_anime_image = findViewById(R.id.detail_anime_image);
        detail_anime_title = findViewById(R.id.detail_anime_title);
        detail_anime_title_romanji = findViewById(R.id.detail_anime_title_romanji);
        detail_anime_title_japanese = findViewById(R.id.detail_anime_title_japanese);
        detail_anime_score = findViewById(R.id.detail_anime_score);
        detail_anime_scored_by = findViewById(R.id.detail_anime_scored_by);
        detail_anime_type = findViewById(R.id.detail_anime_type);
        detail_anime_status = findViewById(R.id.detail_anime_status);
        detail_anime_episodes = findViewById(R.id.detail_anime_episodes);
        detail_anime_aired_from = findViewById(R.id.detail_anime_aired_from);
        detail_anime_aired_to = findViewById(R.id.detail_anime_aired_to);
        detail_anime_premiered_broadcast = findViewById(R.id.detail_anime_premiered_broadcast);
        detail_anime_synopsis = findViewById(R.id.detail_anime_synopsis);
        detail_anime_source = findViewById(R.id.detail_anime_source);
        detail_anime_duration = findViewById(R.id.detail_anime_duration);
        detail_anime_rating = findViewById(R.id.detail_anime_rating);
        detail_anime_rank = findViewById(R.id.detail_anime_rank);
        detail_anime_popularity = findViewById(R.id.detail_anime_popularity);
        detail_anime_members = findViewById(R.id.detail_anime_members);
        detail_anime_favorites = findViewById(R.id.detail_anime_favorites);

        detail_information = findViewById(R.id.detail_information);
        detail_floating_button = findViewById(R.id.detail_floating_button);
        detail_floating_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(DetailActivity.this, detailAPI.getDetailAnime().getTitle(), Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent notificationIntent = new Intent(DetailActivity.this, DetailActivity.class);
                        notificationIntent.putExtra("mal_id", detailAPI.getDetailAnime().getMal_id());

                        PendingIntent contentIntent = PendingIntent.getActivity(
                                DetailActivity.this,
                                0,
                                notificationIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                        Intent malIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(detailAPI.getDetailAnime().getUrl()));

                        PendingIntent malContent = PendingIntent.getActivity(
                                DetailActivity.this,
                                0,
                                malIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                        String text = "#" + detailAPI.getDetailAnime().getMal_id() + " ~ " + detailAPI.getDetailAnime().getType() + " ~ " + detailAPI.getDetailAnime().getEpisodes() + " Episodes";

                        Notification.Builder builder = new Notification.Builder(DetailActivity.this)
                                .setSmallIcon(R.drawable.maido2)
                                .setStyle(new Notification.BigTextStyle()
                                        .bigText(detailAPI.getDetailAnime().getSynopsis())
                                        .setBigContentTitle(detailAPI.getDetailAnime().getTitle())
                                        .setSummaryText(text)
                                )
                                .setContentTitle(detailAPI.getDetailAnime().getTitle())
                                .setContentText(text)
                                .setPriority(Notification.PRIORITY_HIGH)
                                .setColor(Color.BLUE)
                                .addAction(0, "Lihat Di MyAnimeList!", malContent);

                        NotificationManager manager = (NotificationManager) DetailActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
                        manager.notify(0, builder.build());
                    }
                }, 5000);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String loadingInfo = "Mengambil Data Dari Server ...";
        detail_information.setVisibility(View.VISIBLE);
        detail_information.setText(loadingInfo);
        detail_information.setTextColor(Color.GREEN);

        // Seasonal Anime List API
        detailAPI = new DetailAPI(
                this,
                mal_id,
                detail_anime_image,
                detail_anime_title,
                detail_anime_title_romanji,
                detail_anime_title_japanese,
                detail_anime_score,
                detail_anime_scored_by,
                detail_anime_type,
                detail_anime_status,
                detail_anime_episodes,
                detail_anime_aired_from,
                detail_anime_aired_to,
                detail_anime_premiered_broadcast,
                detail_anime_synopsis,
                detail_anime_source,
                detail_anime_duration,
                detail_anime_rating,
                detail_anime_rank,
                detail_anime_popularity,
                detail_anime_members,
                detail_anime_favorites
        );
        detailAPI.fetchJikanMAL(detail_information);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_detail_menu, menu);
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

            case R.id.detail_activity_menu_view_on_mal:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.MyAnimeList.net/anime/" + mal_id));
                startActivity(intent);
                break;
        }

        // Back To Parent Activity
        finish();
        return super.onOptionsItemSelected(item);
    }
}
