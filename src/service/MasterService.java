package service;

import java.util.List;
import model.Kamar;
import model.Kategori;
import model.Tamu;
import model.Transaksi;

public interface MasterService {

//    kategori
    Kategori simpanKategori(Kategori k);

    boolean isKategoriExists(String kId);

    Kategori ubahKategori(Kategori k);

    Kategori hapusKategori(Kategori k);

    List<Kategori> getAllKategori();

    Kategori getByIdKategori(String id);

    Object[] getAllName();

    Kategori getByNameKategori(String name);

    //    kamar
    Kamar simpanKamar(Kamar km);

    boolean isKamarExists(String kmId);

    Kamar ubahKamar(Kamar km);

    Kamar hapusKamar(Kamar km);

    List<Kamar> getAllKamar();

    Kamar getByIdKamar(String id);

    List<Kamar> findKamarByName(String name);

// Tamu
    Tamu simpanTamu(Tamu t);

    boolean isTamuExists(String tId);

    Tamu ubahTamu(Tamu t);

    Tamu hapusTamu(Tamu t);

    List<Tamu> getAllTamu();

    Tamu getByIdTamu(String id);

    Object[] getAllNama();

    List<Tamu> getByNameTamu(String namaTamu);

//transaksi
    Transaksi simpanTransaksi(Transaksi tr);

    boolean isTransaksiExists(String trId);

    Transaksi hapusTransaksi(Transaksi tr);

    List<Transaksi> getAllTransaksi();

    Transaksi getByIdTrans(String id);

}
