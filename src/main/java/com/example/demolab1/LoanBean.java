package com.example.demolab1;
import java.io.Serializable;
import java.text.DecimalFormat;

public class LoanBean implements Serializable {
    private double kwota;
    private double procent;
    private int liczbaRat;


    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    public double getProcent() {
        return procent;
    }

    public void setProcent(double procent) {
        this.procent = procent;
    }

    public int getLiczbaRat() {
        return liczbaRat;
    }

    public void setLiczbaRat(int liczbaRat) {
        this.liczbaRat = liczbaRat;
    }

    public String getRata(){
        double p = procent / 1200;
        Double rata = (kwota * p) / (1 - 1 / Math.pow(1 + p, liczbaRat));
        if(rata<0){
            throw new IllegalArgumentException("Niepoprawne dane! Rata wyszła ujemna!");
        }
        DecimalFormat format = new DecimalFormat("#.00");
        return rata > 0 ? "Rata: "+format.format(rata) : "";
    }
}
