package com.emlakcepte.model;

public class Banner {
    private String ilanNo;
    private int adet;
    private String telno1;
    private String telno2;
    //Adress bilgisi eklenmesi


    public Banner(String ilanNo, int adet, String telno1, String telno2) {
        this.ilanNo = ilanNo;
        this.adet = adet;
        this.telno1 = telno1;
        this.telno2 = telno2;
    }

    public String getIlanNo() {
        return ilanNo;
    }

    public void setIlanNo(String ilanNo) {
        this.ilanNo = ilanNo;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public String getTelno1() {
        return telno1;
    }

    public void setTelno1(String telno1) {
        this.telno1 = telno1;
    }

    public String getTelno2() {
        return telno2;
    }

    public void setTelno2(String telno2) {
        this.telno2 = telno2;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "ilanNo='" + ilanNo + '\'' +
                ", adet=" + adet +
                ", telno1='" + telno1 + '\'' +
                ", telno2='" + telno2 + '\'' +
                '}';
    }
}
