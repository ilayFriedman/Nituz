package model;

import java.util.Date;
import java.util.Objects;

public class User {
    private String username;
    private String pwd;
    private Date birthday;
    private String privateName;
    private String lastName;
    private String city;

    public static User readUserFromDB(String username, String pwd){
        //if user exists in DB, construct it.
        return null;
    }

    public static boolean deleteUserFromDB(String username, String pwd){
        //if user exists in DB, delete it and return true. Else return false.
        return true;
    }

    public static String createUsersTableSQL(){
        return ("CREATE TABLE IF NOT EXISTS tbl_users (\n"
                + " username text NOT NULL,\n"
                + " pwd text NOT NULL,\n"
                + " birthday text,\n"
                + "	privateName text,\n"
                + "	lastName text,\n"
                + "	city text\n"
                + ");");
    }

    public static String dropUsersTableSQL(){
        return "DROP TABLE IF EXISTS tbl_users;";
    }

    public String getUserFieldsSQL(){
        return "VALUES (" + username +
                ", " + pwd +
                ", " + birthday +
                ", " + privateName +
                ", " + lastName +
                ", " + city +
                ");";
    }

    public User(String username, String pwd, Date birthday, String privateName, String lastName, String city) {
        this.username = username;
        this.pwd = pwd;
        this.birthday = birthday;
        this.privateName = privateName;
        this.lastName = lastName;
        this.city = city;
    }

    public boolean updateUserOnDB(){
        User currentlyOnDB = readUserFromDB(username, pwd);
        if (!currentlyOnDB.equals(this))
            //write changes to db
            return true;
        else
            return false;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPrivateName() {
        return privateName;
    }

    public void setPrivateName(String privateName) {
        this.privateName = privateName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPwd(), user.getPwd()) &&
                Objects.equals(getBirthday(), user.getBirthday()) &&
                Objects.equals(getPrivateName(), user.getPrivateName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getCity(), user.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPwd(), getBirthday(), getPrivateName(), getLastName(), getCity());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", birthday=" + birthday +
                ", privateName='" + privateName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }


}
