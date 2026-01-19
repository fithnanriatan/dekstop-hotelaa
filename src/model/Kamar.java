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
public class Kamar {

    private String noKamar;
    private String nama;
    private Kategori kategori;
    private double harga;
    private String status;
    
    private Connection c;

    public Kamar(Connection c) {
        this.c = c;
    }

    // Getters and Setters
    public String getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(String noKamar) {
        this.noKamar = noKamar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method CRUD
    public void simpan() throws SQLException {
        String sqlInsert = "INSERT INTO kamar (noKamar, nama, idKategori, harga, status) VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sqlInsert);
            ps.setString(1, noKamar);
            ps.setString(2, nama);
            ps.setString(3, kategori.getId());
            ps.setDouble(4, harga);
            ps.setString(5, status);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    public void ubah() throws SQLException {
        String sqlUpdate = "UPDATE kamar SET nama=?, idKategori=?, harga=?, status=? WHERE noKamar=?";
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sqlUpdate);
            ps.setString(1, nama);
            ps.setString(2, kategori.getId());
            ps.setDouble(3, harga);
            ps.setString(4, status);
            ps.setString(5, noKamar);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    public void hapus() throws SQLException {
        String sqlDelete = "DELETE FROM kamar WHERE noKamar=?";
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sqlDelete);
            ps.setString(1, noKamar);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    public List<Kamar> getAll() throws SQLException {
        List<Kamar> list = new ArrayList<>();
        String sqlSelect = "SELECT * FROM kamar";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = c.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            while (rs.next()) {
                Kamar km = new Kamar(c);
                km.setNoKamar(rs.getString("noKamar"));
                km.setNama(rs.getString("nama"));
                
                // Get Kategori data with null check
                String idKategori = rs.getString("idKategori");
                if (idKategori != null && !idKategori.isEmpty()) {
                    Kategori kat = new Kategori(c);
                    km.setKategori(kat.getById(idKategori));
                }
                
                km.setHarga(rs.getDouble("harga"));
                km.setStatus(rs.getString("status"));
                list.add(km);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return list;
    }

    public Kamar getById(String id) throws SQLException {
        Kamar km = new Kamar(c);
        String sqlSelect = "SELECT * FROM kamar WHERE noKamar=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = c.prepareStatement(sqlSelect);
            ps.setString(1, id);
            rs = ps.executeQuery();

            // FIX: Use 'if' instead of 'while' for single record lookup
            if (rs.next()) {
                km.setNoKamar(rs.getString("noKamar"));
                km.setNama(rs.getString("nama"));
                
                // Get Kategori data with null check
                String idKategori = rs.getString("idKategori");
                if (idKategori != null && !idKategori.isEmpty()) {
                    Kategori kat = new Kategori(c);
                    km.setKategori(kat.getById(idKategori));
                }
                
                km.setHarga(rs.getDouble("harga"));
                km.setStatus(rs.getString("status"));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return km;
    }

    public List<Kamar> cariKamar(String nama) throws SQLException {
        List<Kamar> list = new ArrayList<>();
        String sqlSelect = "SELECT * FROM kamar WHERE nama LIKE ? AND status = 'Tersedia'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = c.prepareStatement(sqlSelect);
            ps.setString(1, "%" + nama + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Kamar km = new Kamar(c);
                km.setNoKamar(rs.getString("noKamar"));
                km.setNama(rs.getString("nama"));
                
                // Get Kategori data with null check
                String idKategori = rs.getString("idKategori");
                if (idKategori != null && !idKategori.isEmpty()) {
                    Kategori kat = new Kategori(c);
                    km.setKategori(kat.getById(idKategori));
                }
                
                km.setHarga(rs.getDouble("harga"));
                km.setStatus(rs.getString("status"));
                list.add(km);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return list;
    }
}