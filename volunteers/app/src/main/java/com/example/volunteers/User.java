package com.example.volunteers;

public class User {
    private String name;
    private String email;
   private String password;
    private String username;
    private String phone;
   // private String location;
    private String address;

    public User(String nam, String email, String unam, String phn, String add) {
        // Default constructor required for Firebase Realtime Database
        this.name = name;
        this.email = email;

        this.username = username;
        this.phone = phone;
       // this.location = location;
        this.address = address;
    }

    public User(String name, String email, String password, String username, String phone, String address) {
        this.name = name;
        this.email = email;
       this.password = password;
        this.username = username;
        this.phone = phone;
       // this.location = location;
        this.address = address;
    }

// Getters and setters
public String getName() {
    return name;
}

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
