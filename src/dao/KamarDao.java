/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Kamar;

public class KamarDao {

    private KategoriDao kategoriDao;

    private Connection connection;
    private PreparedStatement simpanStatement;
    private PreparedStatement ubahStatement;
    private PreparedStatement hapusStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement findByNameStatement;

    private final String queryInsert = "INSERT INTO kamar (noKamar, nama, idKategori, harga,status) VALUES (?,?,?,?,?)";
    private final String queryUpdate = "UPDATE kamar SET nama=?,idKategori=?,harga=?,status=? WHERE noKamar=?";
    private final String queryDelete = "DELETE FROM kamar WHERE noKamar=?";
    private final String querySelectAll = "SELECT * FROM kamar";
    private final String querySelectById = "SELECT * from kamar WHERE noKamar=?";
    private final String queryLikeByName = "SELECT * from kamar WHERE nama LIKE ? && status = 'Tersedia'";

    public void setConnection(Connection connection, KategoriDao kategoriDao) throws SQLException {
        this.connection = connection;
        this.kategoriDao = kategoriDao;

        simpanStatement = connection.prepareStatement(queryInsert);
        ubahStatement = connection.prepareStatement(queryUpdate);
        hapusStatement = connection.prepareStatement(queryDelete);
        getAllStatement = connection.prepareStatement(querySelectAll);
        getByIdStatement = connection.prepareStatement(querySelectById);
        findByNameStatement = connection.prepareStatement(queryLikeByName);
    }

    public Kamar simpan(Kamar km) throws SQLException {
        simpanStatement.setString(1, km.getNoKamar());
        simpanStatement.setString(2, km.getNama());
        simpanStatement.setString(3, km.getKategori().getId());
        simpanStatement.setDouble(4, km.getHarga());
        simpanStatement.setString(5, km.getStatus());
        simpanStatement.executeUpdate();
        return km;
    }

    public Kamar ubah(Kamar km) throws SQLException {
        ubahStatement.setString(1, km.getNama());
        ubahStatement.setString(2, km.getKategori().getId());
        ubahStatement.setDouble(3, km.getHarga());
        ubahStatement.setString(4, km.getStatus());
        ubahStatement.setString(5, km.getNoKamar());
        ubahStatement.executeUpdate();
        return km;
    }

    public Kamar hapus(Kamar km) throws SQLException {
        hapusStatement.setString(1, km.getNoKamar());
        hapusStatement.executeUpdate();
        return km;
    }

    public List<Kamar> getAll() throws SQLException {
        List<Kamar> list = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();

        while (rs.next()) {
            Kamar km = new Kamar();
            km.setNoKamar(rs.getString("noKamar"));
            km.setNama(rs.getString("nama"));
            km.setKategori(kategoriDao.getById(rs.getString("idKategori")));
            km.setHarga(rs.getDouble("harga"));
            km.setStatus(rs.getString("status"));

            list.add(km);
        }

        return list;
    }

    public Kamar getById(String id) throws SQLException {
        Kamar km = new Kamar();

        getByIdStatement.setString(1, id);
        ResultSet rs = getByIdStatement.executeQuery();

        while (rs.next()) {
            km.setNoKamar(rs.getString("noKamar"));
            km.setNama(rs.getString("nama"));
            km.setKategori(kategoriDao.getById(rs.getString("idKategori")));
            km.setHarga(rs.getDouble("harga"));
            km.setStatus(rs.getString("status"));
        }

        return km;
    }

    public List<Kamar> findKamarByName(String name) throws SQLException {
        List<Kamar> list = new ArrayList<>();
        String sql = "SELECT * FROM kamar WHERE nama LIKE ? and status = 'Tersedia'";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + name + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Kamar k = new Kamar();
            k.setNoKamar(rs.getString("noKamar"));
            k.setNama(rs.getString("nama"));
            k.setKategori(kategoriDao.getById(rs.getString("idKategori")));
            k.setHarga(rs.getDouble("harga"));
            k.setStatus(rs.getString("status"));
            list.add(k);
        }
        return list;
    }

}
