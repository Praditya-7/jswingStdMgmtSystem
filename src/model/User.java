package model;

public class User {
    private String fullName;
    private String email;
    private String userName;
    private String password;

    //For Register
    public User(String fullName,String email,String userName,String password){
        this.fullName=fullName;
        this.email=email;
        this.userName=userName;
        this.password=password;
    }

    //For Login
    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

    public String getFullName(){
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
