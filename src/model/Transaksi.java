package model;

public class Transaksi {

    private String idTrans;
    private String noKamar;
    private String idTamu;
    private Kamar kamar;
    private Tamu tamu;
    private String tglCheckin;
    private String tglCheckout;
    private double bayar;
    private double totBayar;

    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public void setKamar(Kamar kamar) {
        this.kamar = kamar;
    }

    public Tamu getTamu() {
        return tamu;
    }

    public void setTamu(Tamu tamu) {
        this.tamu = tamu;
    }

    public String getTglCheckin() {
        return tglCheckin;
    }

    public void setTglCheckin(String tglCheckin) {
        this.tglCheckin = tglCheckin;
    }

    public String getTglCheckout() {
        return tglCheckout;
    }

    public void setTglCheckout(String tglCheckout) {
        this.tglCheckout = tglCheckout;
    }

    public double getBayar() {
        return bayar;
    }

    public void setBayar(double bayar) {
        this.bayar = bayar;
    }

    public String getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(String noKamar) {
        this.noKamar = noKamar;
    }

    public String getIdTamu() {
        return idTamu;
    }

    public void setIdTamu(String idTamu) {
        this.idTamu = idTamu;
    }

    public double getTotBayar() {
        return totBayar;
    }

    public void setTotBayar(double totBayar) {
        this.totBayar = totBayar;
    }

}
