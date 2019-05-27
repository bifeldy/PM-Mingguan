package id.ac.umn.projectuas_00000013536.POJOs;

public class Aired {

    private String from;
    private String to;
    private String string;

    public Aired() {
    }

    public Aired(String from, String to, String string) {
        this.from = from;
        this.to = to;
        this.string = string;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
