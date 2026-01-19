package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NATA
 */
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
    
    private Connection c;

    public Transaksi(Connection c) {
        this.c = c;
    }

    // Getters and Setters
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

    // Method CRUD
    public void simpan() throws SQLException {
        String sqlInsert = "INSERT INTO transaksi (idTrans, noKamar, idTamu, tglCheckin, tglCheckout, bayar) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sqlInsert);
            ps.setString(1, idTrans);
            ps.setString(2, noKamar);
            ps.setString(3, idTamu);
            ps.setDate(4, java.sql.Date.valueOf(tglCheckin));
            ps.setDate(5, java.sql.Date.valueOf(tglCheckout));
            ps.setDouble(6, totBayar);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    public void hapus() throws SQLException {
        String sqlDelete = "DELETE FROM transaksi WHERE idTrans=?";
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sqlDelete);
            ps.setString(1, idTrans);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    public List<Transaksi> getAll() throws SQLException {
        List<Transaksi> list = new ArrayList<>();
        String sqlSelect = "SELECT * FROM transaksi";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = c.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            while (rs.next()) {
                Transaksi tr = new Transaksi(c);
                tr.setIdTrans(rs.getString("idTrans"));
                
                // Get Kamar data
                Kamar km = new Kamar(c);
                tr.setKamar(km.getById(rs.getString("noKamar")));
                
                // Get Tamu data
                Tamu tm = new Tamu(c);
                tr.setTamu(tm.getById(rs.getString("idTamu")));
                
                tr.setTglCheckin(rs.getString("tglCheckin"));
                tr.setTglCheckout(rs.getString("tglCheckout"));
                tr.setBayar(rs.getDouble("bayar"));
                
                list.add(tr);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return list;
    }

    public Transaksi getById(String id) throws SQLException {
        Transaksi tr = new Transaksi(c);
        String sqlSelect = "SELECT * FROM transaksi WHERE idTrans=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = c.prepareStatement(sqlSelect);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                tr.setIdTrans(rs.getString("idTrans"));
                
                // Get Kamar data
                Kamar km = new Kamar(c);
                tr.setKamar(km.getById(rs.getString("noKamar")));
                
                // Get Tamu data
                Tamu tm = new Tamu(c);
                tr.setTamu(tm.getById(rs.getString("idTamu")));
                
                tr.setTglCheckin(rs.getString("tglCheckin"));
                tr.setTglCheckout(rs.getString("tglCheckout"));
                tr.setBayar(rs.getDouble("bayar"));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return tr;
    }
}