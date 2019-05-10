package id.ac.umn.week13_b_13536;

public class POJOUser {
    private int userid;

    private String username;

    private POJOEmail email;

    public POJOUser() {}

    public POJOUser(int userid, String username, POJOEmail email) {
        this.setUserid(userid);
        this.setUsername(username);
        this.setEmail(email);

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;

    }

    public String getUsername() {
        return username;

    }

    public void setUsername(String username) {
        this.username = username;

    }

    public POJOEmail getEmail() {
        return email;

    }

    public void setEmail(POJOEmail email) {
        this.email = email;

    }
}
