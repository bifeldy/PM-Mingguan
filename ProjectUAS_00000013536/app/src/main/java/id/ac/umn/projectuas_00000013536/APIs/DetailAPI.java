package id.ac.umn.projectuas_00000013536.APIs;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import id.ac.umn.projectuas_00000013536.POJOs.Aired;
import id.ac.umn.projectuas_00000013536.POJOs.DetailAnime;
import id.ac.umn.projectuas_00000013536.POJOs.Genre;
import id.ac.umn.projectuas_00000013536.POJOs.Producer;

public class DetailAPI {

    private String urlApi;
    private JsonObjectRequest request;

    private DetailAnime detailAnime;

    private Context context;

    private RequestQueue requestQueue;

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

    public DetailAPI(
            Context context,
            int mal_id,
            ImageView detail_anime_image,
            TextView detail_anime_title,
            TextView detail_anime_title_romanji,
            TextView detail_anime_title_japanese,
            TextView detail_anime_score,
            TextView detail_anime_scored_by,
            TextView detail_anime_type,
            TextView detail_anime_status,
            TextView detail_anime_episodes,
            TextView detail_anime_aired_from,
            TextView detail_anime_aired_to,
            TextView detail_anime_premiered_broadcast,
            TextView detail_anime_synopsis,
            TextView detail_anime_source,
            TextView detail_anime_duration,
            TextView detail_anime_rating,
            TextView detail_anime_rank,
            TextView detail_anime_popularity,
            TextView detail_anime_members,
            TextView detail_anime_favorites
    ) {
        this.context = context;
        this.urlApi = "https://api.jikan.moe/v3/anime/" + mal_id;
        this.requestQueue = Volley.newRequestQueue(context);
        this.detail_anime_image = detail_anime_image;
        this.detail_anime_title = detail_anime_title;
        this.detail_anime_title_romanji = detail_anime_title_romanji;
        this.detail_anime_title_japanese = detail_anime_title_japanese;
        this.detail_anime_score = detail_anime_score;
        this.detail_anime_scored_by = detail_anime_scored_by;
        this.detail_anime_type = detail_anime_type;
        this.detail_anime_status = detail_anime_status;
        this.detail_anime_episodes = detail_anime_episodes;
        this.detail_anime_aired_from = detail_anime_aired_from;
        this.detail_anime_aired_to = detail_anime_aired_to;
        this.detail_anime_premiered_broadcast = detail_anime_premiered_broadcast;
        this.detail_anime_synopsis = detail_anime_synopsis;
        this.detail_anime_source = detail_anime_source;
        this.detail_anime_duration = detail_anime_duration;
        this.detail_anime_rating = detail_anime_rating;
        this.detail_anime_rank = detail_anime_rank;
        this.detail_anime_popularity = detail_anime_popularity;
        this.detail_anime_members = detail_anime_members;
        this.detail_anime_favorites = detail_anime_favorites;
    }

    public String getUrlApi() {
        return urlApi;
    }

    public void setUrlApi(String urlApi) {
        this.urlApi = urlApi;
    }

    public JsonObjectRequest getRequest() {
        return request;
    }

    public void setRequest(JsonObjectRequest request) {
        this.request = request;
    }

    public DetailAnime getDetailAnime() {
        return detailAnime;
    }

    public void setDetailAnime(DetailAnime detailAnime) {
        this.detailAnime = detailAnime;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }


    private void printData() {

        Glide.with(context)
            .load(detailAnime.getImage_url())
            .transition(DrawableTransitionOptions.withCrossFade(250)) // Transition Effect Load Image
            .into(detail_anime_image);

        if(detailAnime.getTitle() != null) detail_anime_title.setText(detailAnime.getTitle());
        if(detailAnime.getTitle() != null) detail_anime_title_romanji.setText(detailAnime.getTitle());
        if(detailAnime.getTitle_japanese() != null) detail_anime_title_japanese.setText(detailAnime.getTitle_japanese());

        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        detail_anime_score.setText(decimalFormat.format(detailAnime.getScore()));
        detail_anime_scored_by.setText(decimalFormat.format(detailAnime.getScored_by()));

        detail_anime_type.setText(detailAnime.getType());
        detail_anime_status.setText(detailAnime.getStatus());

        String episode;
        if(detailAnime.getEpisodes() == 0) {
            episode = "? Episodes";
        }
        else {
            episode = Integer.toString(detailAnime.getEpisodes()) + " Episodes";
        }
        detail_anime_episodes.setText(episode);

        String[] airDate = detailAnime.getAired().getString().split(" to ");
        detail_anime_aired_from.setText(airDate[0]);
        detail_anime_aired_to.setText(airDate[1]);

        String premieredBroadcast = detailAnime.getPremiered() + ", " + detailAnime.getBroadcast();
        detail_anime_premiered_broadcast.setText(premieredBroadcast);

        detail_anime_synopsis.setText(detailAnime.getSynopsis());
        detail_anime_source.setText(detailAnime.getSource());
        detail_anime_duration.setText(detailAnime.getDuration());
        detail_anime_rating.setText(detailAnime.getRating());

        String rank = "#" + decimalFormat.format(detailAnime.getRank());
        detail_anime_rank.setText(rank);

        String popularity = "~" + decimalFormat.format(detailAnime.getPopularity());
        detail_anime_popularity.setText(popularity);

        detail_anime_members.setText(decimalFormat.format(detailAnime.getMembers()));
        detail_anime_favorites.setText(decimalFormat.format(detailAnime.getFavorites()));
    }


    private Aired generateDetailAired(JSONObject response) {

        Aired aired = new Aired();

        try {

            JSONObject anime_aired = response.getJSONObject("aired");

            if(anime_aired.getString("from") != null) aired.setFrom(anime_aired.getString("from"));
            if(anime_aired.getString("to") != null) aired.setTo(anime_aired.getString("to"));

            // TODO: 'prop' Tidak Ada
            // aired.setTo(anime_aired.getString("prop"));

            if(anime_aired.getString("string") != null) aired.setString(anime_aired.getString("string"));
        }
        catch (JSONException e) {

            e.printStackTrace();
        }

        return aired;
    }


    private List<Producer> generateDetailAnimeProducer(JSONObject response) {

        List<Producer> anime_producers = new ArrayList<>();

        try {

            for (int j = 0; j < response.getJSONArray("producers").length(); j++) {
                JSONObject anime_producer = response.getJSONArray("producers").getJSONObject(j);

                Producer producer = new Producer();

                if(!anime_producer.isNull("mal_id")) producer.setMal_id(anime_producer.getInt("mal_id"));

                if(anime_producer.getString("type") != null) producer.setType(anime_producer.getString("type"));
                if(anime_producer.getString("name") != null) producer.setName(anime_producer.getString("name"));
                if(anime_producer.getString("url") != null) producer.setUrl(anime_producer.getString("url"));

                anime_producers.add(producer);
            }
        }
        catch (JSONException e) {

            e.printStackTrace();
        }

        return anime_producers;
    }

    private List<Genre> generateDetailAnimeGenre(JSONObject response) {

        List<Genre> anime_genres = new ArrayList<>();

        try {

            for (int j = 0; j < response.getJSONArray("genres").length(); j++) {
                JSONObject anime_genre = response.getJSONArray("genres").getJSONObject(j);

                Genre genre = new Genre();

                if(!anime_genre.isNull("mal_id")) genre.setMal_id(anime_genre.getInt("mal_id"));

                if(anime_genre.getString("type") != null) genre.setType(anime_genre.getString("type"));
                if(anime_genre.getString("name") != null) genre.setName(anime_genre.getString("name"));
                if(anime_genre.getString("url") != null) genre.setUrl(anime_genre.getString("url"));

                anime_genres.add(genre);
            }
        }
        catch (JSONException e) {

            e.printStackTrace();
        }

        return anime_genres;
    }

    private void createDetailAnime(JSONObject response){

        detailAnime = new DetailAnime();

        try {

            if(response.getString("request_hash") != null) detailAnime.setRequest_hash(response.getString("request_hash"));

            if(!response.isNull("request_cached")) detailAnime.setRequest_cached(response.getBoolean("request_cached"));
            if(!response.isNull("request_cache_expiry")) detailAnime.setRequest_cache_expiry(response.getInt("request_cache_expiry"));

            if(!response.isNull("mal_id")) detailAnime.setMal_id(response.getInt("mal_id"));

            if(response.getString("url") != null) detailAnime.setUrl(response.getString("url"));
            if(response.getString("image_url") != null) detailAnime.setImage_url(response.getString("image_url"));
            if(response.getString("trailer_url") != null) detailAnime.setTrailer_url(response.getString("trailer_url"));
            if(response.getString("title") != null) detailAnime.setTitle(response.getString("title"));
            if(response.getString("title_english") != null) detailAnime.setTitle_english(response.getString("title_english"));
            if(response.getString("title_japanese") != null) detailAnime.setTitle_japanese(response.getString("title_japanese"));

            // if(response.getString("title_synonyms") != null) detailAnime.setTitle_synonyms(response.getString("title_synonyms"));

            if(response.getString("type") != null) detailAnime.setType(response.getString("type"));
            if(response.getString("source") != null) detailAnime.setSource(response.getString("source"));

            if(!response.isNull("episodes")) detailAnime.setEpisodes(response.getInt("episodes"));

            if(response.getString("status") != null) detailAnime.setStatus(response.getString("status"));

            if(!response.isNull("airing")) detailAnime.setAiring(response.getBoolean("airing"));

            // Get Aired From JikanMAL
            detailAnime.setAired(generateDetailAired(response));

            if(response.getString("duration") != null) detailAnime.setDuration(response.getString("duration"));
            if(response.getString("rating") != null) detailAnime.setRating(response.getString("rating"));

            if(!response.isNull("score")) detailAnime.setScore(response.getDouble("score"));
            if(!response.isNull("scored_by")) detailAnime.setScored_by(response.getInt("scored_by"));
            if(!response.isNull("rank")) detailAnime.setRank(response.getInt("rank"));
            if(!response.isNull("popularity")) detailAnime.setPopularity(response.getInt("popularity"));
            if(!response.isNull("members")) detailAnime.setMembers(response.getInt("members"));
            if(!response.isNull("favorites")) detailAnime.setFavorites(response.getInt("favorites"));

            if(response.getString("synopsis") != null) detailAnime.setSynopsis(response.getString("synopsis"));
            if(response.getString("background") != null) detailAnime.setBackground(response.getString("background"));
            if(response.getString("premiered") != null) detailAnime.setPremiered(response.getString("premiered"));
            if(response.getString("broadcast") != null) detailAnime.setBroadcast(response.getString("broadcast"));

            // Get Producer From JikanMAL
            detailAnime.setProducers(generateDetailAnimeProducer(response));

            // Get Licensor From JikanMAL TODO: Belum Ada
            // if(response != null) detailAnime.setProducers(generateDetailAnimeLicensor(response));

            // Get Studios From JikanMAL TODO: Belum Ada
            // if(response != null) detailAnime.setStudios(generateDetailAnimeStudios(response));

            // Get Genre From JikanMAL
            detailAnime.setGenres(generateDetailAnimeGenre(response));

            // Get Opening From JikanMAL TODO: Belum Ada
            // if(response != null) detailAnime.setOpening_Themes(generateDetailAnimeOpening(response));

            // Get Ending From JikanMAL TODO: Belum Ada
            // if(response != null) detailAnime.setEnding_Themes(generateDetailAnimeEnding(response));
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

                    createDetailAnime(response);
                    Log.d("API", response.toString());
                    printData();
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
