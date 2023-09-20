package com.example.volunteers;

public class EventPost {
    private String Ngoname;
    private String email;

    private String Eventname;
    private String phone;
    private String Doc;
    private String volreq ;
    private String address;

    public EventPost (String Ngoname, String Eventname, String Doc, String phone, String volreq , String address) {
        // Default constructor required for Firebase Realtime Database
        this.Ngoname = Ngoname;
        this.Doc = Doc;
        this.Eventname = Eventname;
        this.phone = phone;
        this.volreq  = volreq ;
        this.address = address;
    }

    public EventPost (String Ngoname, String Eventname, String Doc, String phone, String volreq , String address, String email) {
        this.Ngoname =Ngoname;
        this.email = email;
        this.Doc = Doc;
        this.Eventname = Eventname;
        this.phone = phone;
        this.volreq = volreq ;
        this.address = address;
    }

    // Getters and setters
    public String getNgoname() {
        return Ngoname;
    }

    public void setNgoname(String Ngoname) {
        this.Ngoname =Ngoname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getEventname() {
        return Eventname;
    }

    public void setEventname(String Eventname) {
        this.Eventname = Eventname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getvolreq () {
        return volreq ;
    }

    public void setvolreq (String volreq ) {
        this.volreq  = volreq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getDoc() {
        return Doc;
    }

    public void setDoc(String Doc) {
        this.Doc = Doc;
    }



}
