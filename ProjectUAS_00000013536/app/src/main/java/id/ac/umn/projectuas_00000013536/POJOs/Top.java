package id.ac.umn.projectuas_00000013536.POJOs;

import java.util.List;

public class Top {

    private String request_hash;
    private boolean request_cached;
    private int request_cache_expiry;
    private List<TopAnime> top;

    public Top(String request_hash, boolean request_cached, int request_cache_expiry, List<TopAnime> top) {
        this.request_hash = request_hash;
        this.request_cached = request_cached;
        this.request_cache_expiry = request_cache_expiry;
        this.top = top;
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

    public List<TopAnime> getTop() {
        return top;
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

    public void setTop(List<TopAnime> top) {
        this.top = top;
    }
}
