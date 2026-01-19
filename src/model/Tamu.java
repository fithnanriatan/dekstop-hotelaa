/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NATA
 */
public class Tamu {

    private String idTamu;
    private String nama;
    private String alamat;
    private String noTelepon;
    
    private Connection c;

    public Tamu(Connection c) {
        this.c = c;
    }

    public String getIdTamu() {
        return idTamu;
    }

    public void setIdTamu(String idTamu) {
        this.idTamu = idTamu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    // Method CRUD
    public void simpan() throws SQLException {
        String sqlInsert = "INSERT INTO tamu (idTamu, nama, alamat, noTelepon) VALUES (?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(sqlInsert);
        ps.setString(1, idTamu);
        ps.setString(2, nama);
        ps.setString(3, alamat);
        ps.setString(4, noTelepon);
        ps.executeUpdate();
    }

    public void ubah() throws SQLException {
        String sqlUpdate = "UPDATE tamu SET nama=?, alamat=?, noTelepon=? WHERE idTamu=?";
        PreparedStatement ps = c.prepareStatement(sqlUpdate);
        ps.setString(1, nama);
        ps.setString(2, alamat);
        ps.setString(3, noTelepon);
        ps.setString(4, idTamu);
        ps.executeUpdate();
    }

    public void hapus() throws SQLException {
        String sqlDelete = "DELETE FROM tamu WHERE idTamu=?";
        PreparedStatement ps = c.prepareStatement(sqlDelete);
        ps.setString(1, idTamu);
        ps.executeUpdate();
    }

    public List<Tamu> getAll() throws SQLException {
        List<Tamu> list = new ArrayList<>();
        String sqlSelect = "SELECT * FROM tamu";
        PreparedStatement ps = c.prepareStatement(sqlSelect);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Tamu t = new Tamu(c);
            t.setIdTamu(rs.getString("idTamu"));
            t.setNama(rs.getString("nama"));
            t.setAlamat(rs.getString("alamat"));
            t.setNoTelepon(rs.getString("noTelepon"));
            list.add(t);
        }

        return list;
    }

    public Tamu getById(String id) throws SQLException {
        Tamu t = new Tamu(c);
        String sqlSelect = "SELECT * FROM tamu WHERE idTamu=?";
        PreparedStatement ps = c.prepareStatement(sqlSelect);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            t.setIdTamu(rs.getString("idTamu"));
            t.setNama(rs.getString("nama"));
            t.setAlamat(rs.getString("alamat"));
            t.setNoTelepon(rs.getString("noTelepon"));
        }

        return t;
    }

    public List<Tamu> cariTamu(String nama) throws SQLException {
        List<Tamu> list = new ArrayList<>();
        String sqlSelect = "SELECT * FROM tamu WHERE nama LIKE ?";
        PreparedStatement ps = c.prepareStatement(sqlSelect);
        ps.setString(1, "%" + nama + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Tamu t = new Tamu(c);
            t.setIdTamu(rs.getString("idTamu"));
            t.setNama(rs.getString("nama"));
            t.setAlamat(rs.getString("alamat"));
            t.setNoTelepon(rs.getString("noTelepon"));
            list.add(t);
        }

        return list;
    }

    public Object[] getAllNama() throws SQLException {
        ArrayList<Object> newObj = new ArrayList<>();
        String sqlSelect = "SELECT nama FROM tamu";
        PreparedStatement ps = c.prepareStatement(sqlSelect);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            newObj.add(rs.getString("nama"));
        }
        return newObj.toArray();
    }

    public Tamu getByNama(String nama) throws SQLException {
        Tamu t = new Tamu(c);
        String sqlSelect = "SELECT * FROM tamu WHERE nama=?";
        PreparedStatement ps = c.prepareStatement(sqlSelect);
        ps.setString(1, nama);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            t.setIdTamu(rs.getString("idTamu"));
            t.setNama(rs.getString("nama"));
            t.setAlamat(rs.getString("alamat"));
            t.setNoTelepon(rs.getString("noTelepon"));
        }
        return t;
    }
}