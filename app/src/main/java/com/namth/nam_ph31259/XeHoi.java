package com.namth.nam_ph31259;

import java.io.Serializable;

public class XeHoi implements Serializable {
    private String ten;
    private String hang;
    private int namsx;
    private int gia;

    public XeHoi(String hang, String ten, int namsx, int gia) {
        this.ten = ten;
        this.hang = hang;
        this.namsx = namsx;
        this.gia = gia;
    }

    public String getHang() {
        return hang;
    }

    public void setMa(String hang) {
        this.hang = hang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNamsx() {
        return namsx;
    }

    public void setLuong(int namsx) {
        this.namsx = namsx;
    }

    public int getGia() {
        return gia;
    }

    public void setTuoi(int gia) {
        this.gia = gia;
    }
}
