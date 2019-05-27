package id.ac.umn.projectuas_00000013536.POJOs;

import java.util.List;

public class SeasonalAnime {

    private int mal_id;
    private String url;
    private String title;
    private String image_url;
    private String synopsis;
    private String type;
    private String airing_start;
    private int episodes;
    private int members;
    private List<Genre> genres;
    private String source;
    private List<Producer> producers;
    private double score;
    private List<String> licensors;
    private boolean r18;
    private boolean kids;
    private boolean continuing;

    public SeasonalAnime() {}

    public SeasonalAnime(int mal_id, String url, String title, String image_url, String synopsis, String type, String airing_start, int episodes, int members, List<Genre> genres, String source, List<Producer> producers, double score, List<String> licensors, boolean r18, boolean kids, boolean continuing) {
        this.mal_id = mal_id;
        this.url = url;
        this.title = title;
        this.image_url = image_url;
        this.synopsis = synopsis;
        this.type = type;
        this.airing_start = airing_start;
        this.episodes = episodes;
        this.members = members;
        this.genres = genres;
        this.source = source;
        this.producers = producers;
        this.score = score;
        this.licensors = licensors;
        this.r18 = r18;
        this.kids = kids;
        this.continuing = continuing;
    }

    public int getMal_id() {
        return mal_id;
    }

    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAiring_start() {
        return airing_start;
    }

    public void setAiring_start(String airing_start) {
        this.airing_start = airing_start;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<String> getLicensors() {
        return licensors;
    }

    public void setLicensors(List<String> licensors) {
        this.licensors = licensors;
    }

    public boolean isR18() {
        return r18;
    }

    public void setR18(boolean r18) {
        this.r18 = r18;
    }

    public boolean isKids() {
        return kids;
    }

    public void setKids(boolean kids) {
        this.kids = kids;
    }

    public boolean isContinuing() {
        return continuing;
    }

    public void setContinuing(boolean continuing) {
        this.continuing = continuing;
    }
}
