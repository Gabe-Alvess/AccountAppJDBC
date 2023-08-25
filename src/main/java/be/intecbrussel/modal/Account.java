package be.intecbrussel.modal;

public class Account {
    private String email;
    private String passw;

    public Account(String email, String passw) {
        this.email = email;
        this.passw = passw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }
}
