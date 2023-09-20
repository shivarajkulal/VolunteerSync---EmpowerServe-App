package com.example.volunteers;

public class nsignup {
    private String name;
    private String email;
    private String password;
    private String Ngoname;
    private String phone;
//    private String location;
    private String address;

    public nsignup(String nam, String email, String Ngoname, String phn, String add) {
        // Default constructor required for Firebase Realtime Database
        this.name = nam;
        this.email = email;

        this.Ngoname = Ngoname;
        this.phone = phn;
//        this.location = loc;
        this.address = add;
    }

    public nsignup(String name, String email, String password, String Ngoname, String phone,  String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.Ngoname =Ngoname;
        this.phone = phone;
//        this.location = location;
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

    public String getNgoname() {
        return Ngoname;
    }

    public void setNgoname(String username) {
        this.Ngoname = Ngoname;
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
