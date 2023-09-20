package com.example.volunteers;

public class VeventRegis {
    private String name;
    private String email;
    private String eventnam;
    private String username;
    private String phone;
//    private String City ;
    private String ngoname;
    private String address;

//    public VeventRegis (String name, String email, String username, String phone, String City , String address) {
//        // Default constructor required for Firebase Realtime Database
//        this.name = name;
//        this.email = email;
//
//        this.username = username;
//        this.phone = phone;
//        this.City  = City ;
//        this.address = address;
//    }

    public VeventRegis (String name, String eventnam,String email,  String username, String phone, String address,String ngoname) {
        this.name = name;
        this.email = email;
        this.eventnam = eventnam;
        this.username = username;
        this.phone = phone;
//        this.City = City ;
        this.address = address;
        this.ngoname=ngoname;
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

    public String geteventnam() {
        return eventnam;
    }

    public void seteventnam(String eventnam) {
        this.eventnam = eventnam;
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

//    public String getCity () {
//        return City ;
//    }
//
//    public void setCity (String City ) {
//        this.City  = City ;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getngoname() {
        return ngoname;
    }

    public void setngoname(String ngoname) {
        this.ngoname = ngoname;
    }

}
