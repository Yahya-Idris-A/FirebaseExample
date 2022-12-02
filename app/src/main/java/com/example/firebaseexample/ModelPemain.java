package com.example.firebaseexample;

public class ModelPemain {
    private String nama;
    private String club;
    private String key;

    public ModelPemain() {

    }

    public ModelPemain(String nama, String club) {
        this.nama = nama;
        this.club = club;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
