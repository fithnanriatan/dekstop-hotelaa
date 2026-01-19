package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Transaksi;

public class TransaksiDao {

    private KamarDao kamarDao;
    private TamuDao tamuDao;

    private Connection connection;
    private PreparedStatement simpanStatement;
    private PreparedStatement ubahStatement;
    private PreparedStatement hapusStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;

    private final String queryInsert = "INSERT INTO transaksi (idTrans, noKamar, idTamu, tglCheckin, tglCheckout, bayar) VALUES (?,?,?,?,?,?)";
    private final String queryDelete = "DELETE FROM transaksi WHERE idTrans=?";
    private final String querySelectAll = "SELECT * FROM transaksi";
    private final String querySelectById = "SELECT * FROM transaksi WHERE idTrans=?";

    public void setConnection(Connection connection, KamarDao kamarDao, TamuDao tamuDao) throws SQLException {
        this.connection = connection;
        this.kamarDao = kamarDao;
        this.tamuDao = tamuDao;

        simpanStatement = connection.prepareStatement(queryInsert);
        hapusStatement = connection.prepareStatement(queryDelete);
        getAllStatement = connection.prepareStatement(querySelectAll);
        getByIdStatement = connection.prepareStatement(querySelectById);
    }

    public Transaksi simpan(Transaksi tr) throws SQLException {
        simpanStatement.setString(1, tr.getIdTrans());
        simpanStatement.setString(2, tr.getNoKamar());
        simpanStatement.setString(3, tr.getIdTamu());
        simpanStatement.setDate(4, java.sql.Date.valueOf(tr.getTglCheckin()));
        simpanStatement.setDate(5, java.sql.Date.valueOf(tr.getTglCheckout()));
        simpanStatement.setDouble(6, tr.getTotBayar());
        simpanStatement.executeUpdate();
        return tr;
    }

    public Transaksi hapus(Transaksi tr) throws SQLException {
        hapusStatement.setString(1, tr.getIdTrans());
        hapusStatement.executeUpdate();
        return tr;
    }

    public List<Transaksi> getAllTransaksi() throws SQLException {
        List<Transaksi> listTrans = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();

        while (rs.next()) {
            Transaksi tr = new Transaksi();
            tr.setIdTrans(rs.getString("idTrans"));
            tr.setKamar(kamarDao.getById(rs.getString("noKamar")));
            tr.setTamu(tamuDao.getById(rs.getString("idTamu")));
            tr.setTglCheckin(rs.getString("tglCheckin"));
            tr.setTglCheckout(rs.getString("tglCheckout"));
            tr.setBayar(rs.getDouble("bayar"));

            listTrans.add(tr);
        }

        return listTrans;
    }

    public Transaksi getById(String id) throws SQLException {
        Transaksi t = new Transaksi();

        getByIdStatement.setString(1, id);
        ResultSet rs = getByIdStatement.executeQuery();

        if (rs.next()) {
            t.setIdTrans(rs.getString("idTrans"));
            t.setKamar(kamarDao.getById(rs.getString("noKamar")));
            t.setTamu(tamuDao.getById(rs.getString("idTamu")));
            t.setTglCheckin(rs.getString("tglCheckin"));
            t.setTglCheckout(rs.getString("tglCheckout"));
            t.setBayar(rs.getDouble("bayar"));
        }

        return t;
    }
}
