package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Kategori;
import java.util.ArrayList;
import java.util.List;

public class KategoriDao {

    private Connection connection;
    private PreparedStatement simpanStatement;
    private PreparedStatement ubahStatement;
    private PreparedStatement hapusStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getAllNameStatement;
    private PreparedStatement getByNameStatement;

    private final String queryInsert = "INSERT INTO kategorikamar (idKategori, nama) VALUES (?,?)";
    private final String queryUpdate = "UPDATE kategorikamar SET nama=? WHERE idKategori=?";
    private final String queryDelete = "DELETE FROM kategorikamar WHERE idKategori=?";
    private final String querySelectAll = "SELECT * FROM kategorikamar";
    private final String querySelectById = "SELECT * from kategorikamar WHERE idKategori=?";
    private final String querySelectAllName = "SELECT nama from kategorikamar";
    private final String querySelectByName = "SELECT * from kategorikamar WHERE nama=?";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;

        simpanStatement = connection.prepareStatement(queryInsert);
        ubahStatement = connection.prepareStatement(queryUpdate);
        hapusStatement = connection.prepareStatement(queryDelete);
        getAllStatement = connection.prepareStatement(querySelectAll);
        getByIdStatement = connection.prepareStatement(querySelectById);
        getAllNameStatement = connection.prepareStatement(querySelectAllName);
        getByNameStatement = connection.prepareStatement(querySelectByName);
    }

    public Kategori simpan(Kategori k) throws SQLException {
        simpanStatement.setString(1, k.getId());
        simpanStatement.setString(2, k.getNama());
        simpanStatement.executeUpdate();
        return k;
    }

    public Kategori ubah(Kategori k) throws SQLException {
        ubahStatement.setString(1, k.getNama());
        ubahStatement.setString(2, k.getId());
        ubahStatement.executeUpdate();
        return k;
    }

    public Kategori hapus(Kategori k) throws SQLException {
        hapusStatement.setString(1, k.getId());
        hapusStatement.executeUpdate();
        return k;
    }

    public List<Kategori> getAll() throws SQLException {
        List<Kategori> list = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();

        while (rs.next()) {
            Kategori k = new Kategori();
            k.setId(rs.getString("idKategori"));
            k.setNama(rs.getString("nama"));
            list.add(k);
        }

        return list;
    }

    public Kategori getById(String id) throws SQLException {
        Kategori k = new Kategori();

        getByIdStatement.setString(1, id);
        ResultSet rs = getByIdStatement.executeQuery();

        while (rs.next()) {
            k.setId(rs.getString("idKategori"));
            k.setNama(rs.getString("nama"));
        }

        return k;
    }

    public Object[] getAllName() throws SQLException {
        Object[] name = new Object[]{};
        ArrayList<Object> newObj = new ArrayList<>();
        ResultSet rs = getAllNameStatement.executeQuery();
        while (rs.next()) {
            newObj.add(rs.getString("nama"));
        }
        return newObj.toArray();
    }

    public Kategori getByName(String name) throws SQLException {
        Kategori k = new Kategori();

        getByNameStatement.setString(1, name);
        ResultSet rs = getByNameStatement.executeQuery();
        while (rs.next()) {
            k.setId(rs.getString("idKategori"));
            k.setNama(rs.getString("nama"));
        }
        return k;
    }

}
