package id.ac.umn.project_uts_00000013536_v2;

public class Book {
    private String Asin, Group, Format, Title, Author, Publisher;
    private int Favorite;

    public Book(String asin, String group, String format, String title, String author, String publisher, int favorite) {
        this.Asin = asin;
        this.Group = group;
        this.Format = format;
        this.Title = title;
        this.Author = author;
        this.Publisher = publisher;
        this.Favorite = favorite;
    }

    public String getAsin() {
        return Asin;
    }

    public String getGroup() {
        return Group;
    }

    public String getFormat() {
        return Format;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public int getFavorite() { return Favorite; }

    public void setAsin(String asin) {
        this.Asin = asin;
    }

    public void setGroup(String group) {
        this.Group = group;
    }

    public void setFormat(String format) {
        this.Format = format;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public void setPublisher(String publisher) {
        this.Publisher = publisher;
    }

    public void setFavorite(int favorite) { Favorite = favorite; }
}