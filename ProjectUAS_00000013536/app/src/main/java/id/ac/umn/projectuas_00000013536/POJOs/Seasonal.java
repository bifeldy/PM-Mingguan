package id.ac.umn.projectuas_00000013536.POJOs;

import java.util.List;

public class Seasonal {

    private String request_hash;
    private boolean request_cached;
    private int request_cache_expiry;
    private String season_name;
    private int season_year;
    private List<SeasonalAnime> anime;

    public Seasonal() {}

    public Seasonal(String request_hash, boolean request_cached, int request_cache_expiry, String season_name, int season_year, List<SeasonalAnime> anime) {
        this.request_hash = request_hash;
        this.request_cached = request_cached;
        this.request_cache_expiry = request_cache_expiry;
        this.season_name = season_name;
        this.season_year = season_year;
        this.anime = anime;
    }

    public String getRequest_hash() {
        return request_hash;
    }

    public boolean isRequest_cached() {
        return request_cached;
    }

    public int getRequest_cache_expiry() {
        return request_cache_expiry;
    }

    public String getSeason_name() {
        return season_name;
    }

    public int getSeason_year() {
        return season_year;
    }

    public List<SeasonalAnime> getAnime() {
        return anime;
    }

    public void setRequest_hash(String request_hash) {
        this.request_hash = request_hash;
    }

    public void setRequest_cached(boolean request_cached) {
        this.request_cached = request_cached;
    }

    public void setRequest_cache_expiry(int request_cache_expiry) {
        this.request_cache_expiry = request_cache_expiry;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

    public void setSeason_year(int season_year) {
        this.season_year = season_year;
    }

    public void setAnime(List<SeasonalAnime> anime) {
        this.anime = anime;
    }
}
