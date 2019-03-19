package id.ac.umn.week05_00000013536;

public class Mahasiswa {
    private String NIM, FirstName, LastName, Email;

    public Mahasiswa(String NIM, String firstName, String lastName, String email) {
        this.NIM = NIM;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
    }

    public String getNIM() {
        return NIM;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
