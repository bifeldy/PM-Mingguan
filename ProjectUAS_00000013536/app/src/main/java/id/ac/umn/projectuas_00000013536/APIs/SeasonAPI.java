package id.ac.umn.projectuas_00000013536.APIs;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.ac.umn.projectuas_00000013536.POJOs.Genre;
import id.ac.umn.projectuas_00000013536.POJOs.Producer;
import id.ac.umn.projectuas_00000013536.POJOs.Seasonal;
import id.ac.umn.projectuas_00000013536.POJOs.SeasonalAnime;
import id.ac.umn.projectuas_00000013536.SeasonAdapter;

public class SeasonAPI {

    private String urlApi;
    private JsonObjectRequest request;

    private Seasonal seasonal;

    private SeasonAdapter seasonAdapter;
    private RecyclerView recyclerView;
    private Context context;
    private int rowLayout;

    private RequestQueue requestQueue;

    public SeasonAPI(Context context, int year, String season, RecyclerView recyclerView, int rowLayout) {
        this.context = context;
        this.urlApi = "https://api.jikan.moe/v3/season/" + year + "/" + season;
        this.requestQueue = Volley.newRequestQueue(context);
        this.recyclerView = recyclerView;
        this.rowLayout = rowLayout;
    }

    public Seasonal getSeasonal() {
        return seasonal;
    }

    public Context getContext() {
        return context;
    }

    public SeasonAdapter getSeasonAdapter() {
        return seasonAdapter;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void setRecyclerViewAdapter() {

        seasonAdapter = new SeasonAdapter(context, rowLayout, getSeasonal().getAnime());
        recyclerView.setAdapter(seasonAdapter);
    }

    private List<String> generateAnimeLicensor(JSONObject anime) {

        List<String> anime_licensors = new ArrayList<>();

        try {

            for (int j = 0; j < anime.getJSONArray("licensors").length(); j++) {
                anime_licensors.add(
                        anime.getJSONArray("licensors").getString(j)
                );
            }
        }
        catch (JSONException e) {

            e.printStackTrace();
        }

        return anime_licensors;
    }


    private List<Producer> generateSeasonalAnimeProducer(JSONObject anime) {

        List<Producer> anime_producers = new ArrayList<>();

        try {

            for (int j = 0; j < anime.getJSONArray("producers").length(); j++) {
                JSONObject anime_producer = anime.getJSONArray("producers").getJSONObject(j);

                Producer producer = new Producer();

                producer.setMal_id(anime_producer.getInt("mal_id"));
                producer.setType(anime_producer.getString("type"));
                producer.setName(anime_producer.getString("name"));
                producer.setUrl(anime_producer.getString("url"));

                anime_producers.add(producer);
            }
        }
        catch (JSONException e) {

            e.printStackTrace();
        }

        return anime_producers;
    }

    private List<Genre> generateSeasonalAnimeGenre(JSONObject anime) {

        List<Genre> anime_genres = new ArrayList<>();

        try {

            for (int j = 0; j < anime.getJSONArray("genres").length(); j++) {
                JSONObject anime_genre = anime.getJSONArray("genres").getJSONObject(j);

                Genre genre = new Genre();

                genre.setMal_id(anime_genre.getInt("mal_id"));
                genre.setType(anime_genre.getString("type"));
                genre.setName(anime_genre.getString("name"));
                genre.setUrl(anime_genre.getString("url"));

                anime_genres.add(genre);
            }
        }
        catch (JSONException e) {

            e.printStackTrace();
        }

        return anime_genres;
    }

    private List<SeasonalAnime> generateSeasonalAnimeList(JSONObject response) {

        List<SeasonalAnime> animes = new ArrayList<>();

        try {

            for (int i = 0; i < response.getJSONArray("anime").length(); i++) {
                JSONObject anime = response.getJSONArray("anime").getJSONObject(i);

                SeasonalAnime seasonalAnime = new SeasonalAnime();

                seasonalAnime.setMal_id(anime.getInt("mal_id"));
                seasonalAnime.setUrl(anime.getString("url"));
                seasonalAnime.setTitle(anime.getString("title"));
                seasonalAnime.setImage_url(anime.getString("image_url"));
                seasonalAnime.setSynopsis(anime.getString("synopsis"));
                seasonalAnime.setType(anime.getString("type"));
                seasonalAnime.setAiring_start(anime.getString("airing_start"));

                if (anime.isNull("episodes")) {
                    seasonalAnime.setEpisodes(0);
                } else {
                    seasonalAnime.setEpisodes(anime.getInt("episodes"));
                }

                seasonalAnime.setMembers(anime.getInt("members"));

                // Get Genre From JikanMAL
                seasonalAnime.setGenres(generateSeasonalAnimeGenre(anime));

                seasonalAnime.setSource(anime.getString("source"));

                // Get Producer From JikanMAL
                seasonalAnime.setProducers(generateSeasonalAnimeProducer(anime));

                if (anime.isNull("score")) {
                    seasonalAnime.setScore(0);
                } else {
                    seasonalAnime.setScore(anime.getDouble("score"));
                }

                // Get Licensor From JikanMAL
                seasonalAnime.setLicensors(generateAnimeLicensor(anime));

                seasonalAnime.setR18(anime.getBoolean("r18"));
                seasonalAnime.setKids(anime.getBoolean("kids"));
                seasonalAnime.setContinuing(anime.getBoolean("continuing"));

                animes.add(seasonalAnime);
            }
        }
        catch (JSONException e) {

            e.printStackTrace();
        }

        return animes;
    }

    private void createSeasonalData(JSONObject response){

        seasonal = new Seasonal();

        try {

            seasonal.setRequest_hash(response.getString("request_hash"));
            seasonal.setRequest_cached(response.getBoolean("request_cached"));
            seasonal.setRequest_cache_expiry(response.getInt("request_cache_expiry"));
            seasonal.setSeason_name(response.getString("season_name"));
            seasonal.setSeason_year(response.getInt("season_year"));

            // Get Anime From JikanMAL
            seasonal.setAnime(generateSeasonalAnimeList(response));
        }
        catch (JSONException e) {

            e.printStackTrace();
        }
    }

    public void fetchJikanMAL(final TextView textView) {

        JsonObjectRequest request = new JsonObjectRequest(
            Request.Method.GET, urlApi, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    createSeasonalData(response);
                    setRecyclerViewAdapter();
                    textView.setVisibility(View.GONE);
                }
            },
            new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    String errorInfo = error.networkResponse.statusCode + "Terjadi Kesalahan -- ";
                    textView.setText(errorInfo);
                    textView.setTextColor(Color.RED);
                }
            }
        );

        requestQueue.add(request);
    }
}
