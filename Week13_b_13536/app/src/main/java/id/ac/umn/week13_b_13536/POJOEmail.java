package id.ac.umn.week13_b_13536;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class POJOEmail {
    private String address;

    private boolean isVerified;

    public POJOEmail() {}

    public POJOEmail(String address, boolean isVerified) {
        this.setAddress(address);
        this.setVerified(isVerified);

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
