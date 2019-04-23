package id.ac.umn.week10_00000013536.POJO;

public class Identity {
    private int id;
    private String first_name;
    private String last_name;
    private String ip_address;
    private String mac_address;
    private String url_website;

    public Identity() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public void setUrl_website(String url_website) {
        this.url_website = url_website;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getIp_address() {
        return ip_address;
    }

    public String getMac_address() {
        return mac_address;
    }

    public String getUrl_website() {
        return url_website;
    }
}
