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
public class Kategori {

    private String id;
    private String nama;
    
    private Connection c;

    public Kategori(Connection c) {
        this.c = c;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Method CRUD
    public void simpan() throws SQLException {
        String sqlInsert = "INSERT INTO kategorikamar (idKategori, nama) VALUES (?,?)";
        PreparedStatement ps = c.prepareStatement(sqlInsert);
        ps.setString(1, id);
        ps.setString(2, nama);
        ps.executeUpdate();
    }

    public void ubah() throws SQLException {
        String sqlUpdate = "UPDATE kategorikamar SET nama=? WHERE idKategori=?";
        PreparedStatement ps = c.prepareStatement(sqlUpdate);
        ps.setString(1, nama);
        ps.setString(2, id);
        ps.executeUpdate();
    }

    public void hapus() throws SQLException {
        String sqlDelete = "DELETE FROM kategorikamar WHERE idKategori=?";
        PreparedStatement ps = c.prepareStatement(sqlDelete);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public List<Kategori> getAll() throws SQLException {
        List<Kategori> list = new ArrayList<>();
        String sqlSelect = "SELECT * FROM kategorikamar";
        PreparedStatement ps = c.prepareStatement(sqlSelect);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Kategori k = new Kategori(c);
            k.setId(rs.getString("idKategori"));
            k.setNama(rs.getString("nama"));
            list.add(k);
        }

        return list;
    }

    public Kategori getById(String id) throws SQLException {
        Kategori k = new Kategori(c);
        String sqlSelect = "SELECT * FROM kategorikamar WHERE idKategori=?";
        PreparedStatement ps = c.prepareStatement(sqlSelect);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            k.setId(rs.getString("idKategori"));
            k.setNama(rs.getString("nama"));
        }

        return k;
    }

    public Object[] getAllNama() throws SQLException {
        ArrayList<Object> newObj = new ArrayList<>();
        String sqlSelect = "SELECT nama FROM kategorikamar";
        PreparedStatement ps = c.prepareStatement(sqlSelect);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            newObj.add(rs.getString("nama"));
        }
        return newObj.toArray();
    }

    public Kategori getByNama(String nama) throws SQLException {
        Kategori k = new Kategori(c);
        String sqlSelect = "SELECT * FROM kategorikamar WHERE nama=?";
        PreparedStatement ps = c.prepareStatement(sqlSelect);
        ps.setString(1, nama);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            k.setId(rs.getString("idKategori"));
            k.setNama(rs.getString("nama"));
        }
        return k;
    }
}