package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Tamu;

public class TamuDao {

    private Connection connection;
    private PreparedStatement simpanStatement;
    private PreparedStatement ubahStatement;
    private PreparedStatement hapusStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getAllNamaStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getByNameStatement;

    private final String queryInsert = "INSERT INTO tamu (idTamu, nama, alamat, noTelepon) VALUES (?,?,?,?)";
    private final String queryUpdate = "UPDATE tamu SET nama=?, alamat=?, noTelepon=? WHERE idTamu=?";
    private final String queryDelete = "DELETE FROM tamu WHERE idTamu=?";
    private final String querySelectAll = "SELECT * FROM tamu";
    private final String querySelectById = "SELECT * FROM tamu WHERE idTamu=?";
    private final String querySelectAllName = "SELECT nama from tamu";
    private final String querySelectByName = "SELECT * from tamu WHERE nama=?";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        simpanStatement = connection.prepareStatement(queryInsert);
        ubahStatement = connection.prepareStatement(queryUpdate);
        hapusStatement = connection.prepareStatement(queryDelete);
        getAllStatement = connection.prepareStatement(querySelectAll);
        getByIdStatement = connection.prepareStatement(querySelectById);
        getAllNamaStatement = connection.prepareStatement(querySelectAllName);
        getByNameStatement = connection.prepareStatement(querySelectByName);
    }

    public Tamu simpan(Tamu t) throws SQLException {
        simpanStatement.setString(1, t.getIdTamu());
        simpanStatement.setString(2, t.getNama());
        simpanStatement.setString(3, t.getAlamat());
        simpanStatement.setString(4, t.getNoTelepon());
        simpanStatement.executeUpdate();
        return t;
    }

    public Tamu ubah(Tamu t) throws SQLException {
        ubahStatement.setString(1, t.getNama());
        ubahStatement.setString(2, t.getAlamat());
        ubahStatement.setString(3, t.getNoTelepon());
        ubahStatement.setString(4, t.getIdTamu());
        ubahStatement.executeUpdate();
        return t;
    }

    public Tamu hapus(Tamu t) throws SQLException {
        hapusStatement.setString(1, t.getIdTamu());
        hapusStatement.executeUpdate();
        return t;
    }

    public List<Tamu> getAll() throws SQLException {
        List<Tamu> list = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();

        while (rs.next()) {
            Tamu t = new Tamu();
            t.setIdTamu(rs.getString("idTamu"));
            t.setNama(rs.getString("nama"));
            t.setAlamat(rs.getString("alamat"));
            t.setNoTelepon(rs.getString("noTelepon"));
            list.add(t);
        }

        return list;
    }

    public Tamu getById(String id) throws SQLException {
        Tamu t = new Tamu();
        getByIdStatement.setString(1, id);
        ResultSet rs = getByIdStatement.executeQuery();

        if (rs.next()) {
            t.setIdTamu(rs.getString("idTamu"));
            t.setNama(rs.getString("nama"));
            t.setAlamat(rs.getString("alamat"));
            t.setNoTelepon(rs.getString("noTelepon"));
        }
        return t;
    }

    public Tamu getByNameTamu(String name) throws SQLException {
        Tamu t = new Tamu();

        getByNameStatement.setString(1, name);
        ResultSet rs = getByNameStatement.executeQuery();
        while (rs.next()) {
            t.setIdTamu(rs.getString("idTamu"));
            t.setNama(rs.getString("nama"));
            t.setAlamat(rs.getString("alamat"));
            t.setNoTelepon(rs.getString("noTelepon"));
        }
        return t;
    }

    public Object[] getAllNama() throws SQLException {
        Object[] name = new Object[]{};
        ArrayList<Object> newObj = new ArrayList<>();
        ResultSet rs = getAllNamaStatement.executeQuery();
        while (rs.next()) {
            newObj.add(rs.getString("nama"));
        }
        return newObj.toArray();
    }

    public Tamu getByName(String name) throws SQLException {
        Tamu t = new Tamu();

        getByNameStatement.setString(1, name);
        ResultSet rs = getByNameStatement.executeQuery();
        while (rs.next()) {
            t.setIdTamu(rs.getString("idTamu"));
            t.setNama(rs.getString("nama"));
            t.setAlamat(rs.getString("alamat"));
            t.setNoTelepon(rs.getString("noTelepon"));
        }
        return t;
    }

    public List<Tamu> findTamuByName(String namaTamu) throws SQLException {
        List<Tamu> list = new ArrayList<>();
        String sql = "SELECT * FROM tamu WHERE nama LIKE ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + namaTamu + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Tamu t = new Tamu();
            t.setIdTamu(rs.getString("idTamu"));
            t.setNama(rs.getString("nama"));
            t.setAlamat(rs.getString("alamat"));
            t.setNoTelepon(rs.getString("noTelepon"));

            list.add(t);
        }
        return list;
    }

}
