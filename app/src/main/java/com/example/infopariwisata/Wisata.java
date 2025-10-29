package com.example.infopariwisata;

public class Wisata {
    private String nama;
    private String lokasi;
    private String deskripsi;
    private int gambarResId;
    private boolean isFavorite = false;
    private String jamBuka;
    private String telepon;
    private String hargaTiket;

    public Wisata(String nama, String lokasi, String deskripsi, int gambarResId, String jamBuka, String telepon, String hargaTiket) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
        this.gambarResId = gambarResId;
        this.jamBuka = jamBuka;
        this.telepon = telepon;
        this.hargaTiket = hargaTiket;
    }

    public String getNama() { return nama; }
    public String getLokasi() { return lokasi; }
    public String getDeskripsi() { return deskripsi; }
    public int getGambarResId() { return gambarResId; }
    public String getJamBuka() { return jamBuka; }
    public String getTelepon() { return telepon; }
    public String getHargaTiket() { return hargaTiket; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { this.isFavorite = favorite; }
}