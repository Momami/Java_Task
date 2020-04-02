package Classes;

import java.lang.String;
import java.math.BigInteger;
import java.sql.PreparedStatement;

public class User {
    private long id = -1;
    private String login;
    private String password;
    private String address;
    private String phone;

    public User(String login, String password, String address, String phone) {
        this.login = login;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public User(long id, String login, String password, String address, String phone) {
        this.login = login;
        this.password = password;
        this.address = address;
        this.phone = phone;
        setId(id);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
