package be.intecbrussel.modal;

import be.intecbrussel.util.IdGenerator;
import jakarta.persistence.*;

//@Entity(name = "user_tb")
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fname;
    private String lname;
//    @OneToOne
    private Account account;

    // JDBC Constructors
    public User(long id, String fname, String lname, Account account) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.account = account;
    }

    public User(String fname, String lname, Account account) {
        this(IdGenerator.generateId(), fname, lname, account);
    }
//
//    public User(String fname, String lname, Account account) {
//        this.fname = fname;
//        this.lname = lname;
//        this.account = account;
//    }
//
//    protected User() {
//    }

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

    public long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + fname + '\'' +
                ", lastName='" + lname + '\'' +
                ", account=" + account +
                '}';
    }
}
