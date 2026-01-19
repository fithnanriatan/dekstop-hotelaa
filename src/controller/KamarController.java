package controller;

import config.Koneksi;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Kamar;
import model.Kategori;
import view.KamarView;

/**
 * @author NATA
 */
public class KamarController {

    private final KamarView kamarView;
    private Kamar km;
    private Kategori kat;
    private Koneksi koneksi;

    public KamarController(KamarView kamarView) {
        this.kamarView = kamarView;
        koneksi = new Koneksi();
        km = new Kamar(koneksi.getConnection());
        kat = new Kategori(koneksi.getConnection());
    }

    public Kamar getKamar() {
        return km;
    }

    public void clearForm() {
        kamarView.getTextNo().setText(null);
        kamarView.getTextNama().setText(null);
        kamarView.getComboKategori().setSelectedIndex(0);
        kamarView.getTextHarga().setText(null);
        kamarView.getComboStatus().setSelectedIndex(0);
        kamarView.getTabelKamar().clearSelection();
    }

    public void enableForm(boolean kondisi) {
        kamarView.getTextNo().setEnabled(kondisi);
        kamarView.getTextNama().setEnabled(kondisi);
        kamarView.getComboKategori().setEnabled(kondisi);
        kamarView.getTextHarga().setEnabled(kondisi);
        kamarView.getComboStatus().setEnabled(kondisi);
        kamarView.getTombolSimpan().setEnabled(kondisi);
        kamarView.getTombolUbah().setEnabled(!kondisi);
        kamarView.getTombolHapus().setEnabled(!kondisi);
    }

    public void loadKategori() {
        try {
            Object[] kategoriArray = kat.getAllNama();

            // Clear existing items
            kamarView.getComboKategori().removeAllItems();

            // Add placeholder
            kamarView.getComboKategori().addItem("-- Pilih Kategori --");

            // Add all kategori
            for (Object kategori : kategoriArray) {
                kamarView.getComboKategori().addItem(kategori.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(KamarController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(kamarView, "Gagal memuat kategori: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isNoKamarExists(String kId) {
        try {
            Kamar temp = new Kamar(koneksi.getConnection());
            Kamar result = temp.getById(kId);
            return result.getNoKamar() != null && !result.getNoKamar().isEmpty();
        } catch (SQLException ex) {
            Logger.getLogger(KamarController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean validasiInput() {
        if (kamarView.getTextNo().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(kamarView, "No Kamar harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (kamarView.getTextNama().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(kamarView, "Nama harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (kamarView.getTextHarga().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(kamarView, "Harga harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // FIX: Validate kategori selection
        if (kamarView.getComboKategori().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(kamarView, "Silakan pilih kategori!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // FIX: Validate status selection
        if (kamarView.getComboStatus().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(kamarView, "Silakan pilih status!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate harga is a valid number
        try {
            double harga = Double.parseDouble(kamarView.getTextHarga().getText().trim());
            if (harga <= 0) {
                JOptionPane.showMessageDialog(kamarView, "Harga harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(kamarView, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean validasiInputBaru() {
        if (!validasiInput()) {
            return false;
        }
        String noK = kamarView.getTextNo().getText().trim();
        if (isNoKamarExists(noK)) {
            JOptionPane.showMessageDialog(kamarView, "No Kamar sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void loadData(Kamar kamar, List<Kamar> list) {
        if (kamarView.getTabelKamar().getSelectedRow() >= 0) {
            int row = kamarView.getTabelKamar().getSelectedRow();
            kamar = list.get(row);
            kamarView.getTextNo().setText(kamar.getNoKamar());
            kamarView.getTextNama().setText(kamar.getNama());

            // FIX: Handle kategori selection with null check
            if (kamar.getKategori() != null) {
                kamarView.getComboKategori().setSelectedItem(kamar.getKategori().getNama());
            }
            else {
                kamarView.getComboKategori().setSelectedIndex(0);
            }

            kamarView.getTextHarga().setText(String.valueOf(kamar.getHarga()));
            kamarView.getComboStatus().setSelectedItem(kamar.getStatus());

            // Disable only noKamar field, enable others for editing
            kamarView.getTextNo().setEnabled(false);
            enableForm(false);
            kamarView.getTextNama().setEnabled(true);
            kamarView.getComboKategori().setEnabled(true);
            kamarView.getTextHarga().setEnabled(true);
            kamarView.getComboStatus().setEnabled(true);
        }
    }
}
