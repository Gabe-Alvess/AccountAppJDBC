package be.intecbrussel.modal;

import be.intecbrussel.util.IdGenerator;

public class User {
    private long id;
    private String fname;
    private String lname;
    private Account account;

    public User(long id, String fname, String lname, Account account) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.account = account;
    }

    public User(String fname, String lname, Account account) {
        this(IdGenerator.generateId(), fname, lname, account);
    }

    public long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Account getAccount() {
        return account;
    }
}
