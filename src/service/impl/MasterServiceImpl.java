package service.impl;

import config.Koneksi;
import dao.KategoriDao;
import dao.KamarDao;
import dao.TamuDao;
import dao.TransaksiDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Kategori;
import model.Kamar;
import model.Tamu;
import model.Transaksi;
import service.MasterService;

public class MasterServiceImpl implements MasterService {

    private KategoriDao kategoriDao;
    private KamarDao kamarDao;
    private TamuDao tamuDao;
    private TransaksiDao transaksiDao;

    private Koneksi koneksi;
    private Connection connection;

    public MasterServiceImpl() {
        try {
            koneksi = new Koneksi();
            connection = koneksi.getConnection();

            kategoriDao = new KategoriDao();
            kategoriDao.setConnection(connection);

            kamarDao = new KamarDao();
            kamarDao.setConnection(connection, kategoriDao);

            tamuDao = new TamuDao();
            tamuDao.setConnection(connection);

            transaksiDao = new TransaksiDao();
            transaksiDao.setConnection(connection, kamarDao, tamuDao);
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Kategori
    @Override
    public Kategori simpanKategori(Kategori k) {
        try {
            connection.setAutoCommit(false);
            kategoriDao.simpan(k);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }

    @Override
    public boolean isKategoriExists(String kId) {
        String sql = "SELECT COUNT(*) FROM kategorikamar WHERE idKategori = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, kId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Kategori ubahKategori(Kategori k) {
        try {
            connection.setAutoCommit(false);
            kategoriDao.ubah(k);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }

    @Override
    public Kategori hapusKategori(Kategori k) {
        try {
            connection.setAutoCommit(false);
            kategoriDao.hapus(k);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }

    @Override
    public List<Kategori> getAllKategori() {
        try {
            return kategoriDao.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public Kategori getByIdKategori(String id) {
        try {
            return kategoriDao.getById(id);
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Kategori getByNameKategori(String name) {
        try {
            return kategoriDao.getByName(name);
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Kamar
    @Override
    public Kamar simpanKamar(Kamar km) {
        try {
            connection.setAutoCommit(false);
            kamarDao.simpan(km);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return km;
    }

    @Override
    public boolean isKamarExists(String kmId) {
        String sql = "SELECT COUNT(*) FROM kamar WHERE noKamar = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, kmId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Kamar ubahKamar(Kamar km) {
        try {
            connection.setAutoCommit(false);
            kamarDao.ubah(km);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return km;
    }

    @Override
    public Kamar hapusKamar(Kamar km) {
        try {
            connection.setAutoCommit(false);
            kamarDao.hapus(km);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return km;
    }

    @Override
    public List<Kamar> getAllKamar() {
        try {
            return kamarDao.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public Kamar getByIdKamar(String id) {
        try {
            return kamarDao.getById(id);
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object[] getAllName() {
        try {
            return kategoriDao.getAllName();
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Kamar> findKamarByName(String name) {
        try {
            return kamarDao.findKamarByName(name);
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    // Tamu
    @Override
    public Tamu simpanTamu(Tamu t) {
        try {
            connection.setAutoCommit(false);
            tamuDao.simpan(t);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public Tamu ubahTamu(Tamu t) {
        try {
            connection.setAutoCommit(false);
            tamuDao.ubah(t);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public boolean isTamuExists(String tId) {
        String sql = "SELECT COUNT(*) FROM tamu WHERE idTamu = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Tamu hapusTamu(Tamu t) {
        try {
            connection.setAutoCommit(false);
            tamuDao.hapus(t);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public List<Tamu> getAllTamu() {
        try {
            return tamuDao.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public Tamu getByIdTamu(String id) {
        try {
            return tamuDao.getById(id);
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Tamu> getByNameTamu(String namaTamu) {
        try {
            return tamuDao.findTamuByName(namaTamu);
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public Object[] getAllNama() {
        try {
            return tamuDao.getAllNama();
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    transaksi
    @Override
    public Transaksi simpanTransaksi(Transaksi tr) {
        try {
            connection.setAutoCommit(false);
            transaksiDao.simpan(tr);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tr;
    }

    @Override
    public boolean isTransaksiExists(String trId) {
        String sql = "SELECT COUNT(*) FROM transaksi WHERE idTrans = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, trId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Transaksi hapusTransaksi(Transaksi tr) {
        try {
            connection.setAutoCommit(false);
            transaksiDao.hapus(tr);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tr;
    }

    @Override
    public List<Transaksi> getAllTransaksi() {
        try {
            return transaksiDao.getAllTransaksi();
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public Transaksi getByIdTrans(String id) {
        try {
            return transaksiDao.getById(id);
        } catch (SQLException ex) {
            Logger.getLogger(MasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
