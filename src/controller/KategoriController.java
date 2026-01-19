/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.Koneksi;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Kategori;
import view.KategoriView;

/**
 *
 * @author NATA
 */
public class KategoriController {

    private KategoriView kategoriView;
    private Kategori k;
    private Koneksi koneksi;

    public KategoriController(KategoriView kategoriView) {
        this.kategoriView = kategoriView;
        koneksi = new Koneksi();
        k = new Kategori(koneksi.getConnection());
    }

    public Kategori getKategori() {
        return k;
    }

    public void clearForm() {
        kategoriView.getTextId().setText(null);
        kategoriView.getTextNama().setText(null);
        kategoriView.getTabelKategori().clearSelection();
    }

    public void enableForm(boolean kondisi) {
        kategoriView.getTextId().setEnabled(kondisi);
        kategoriView.getTextNama().setEnabled(kondisi);
        kategoriView.getTombolSimpan().setEnabled(kondisi);
        kategoriView.getTombolUbah().setEnabled(!kondisi);
        kategoriView.getTombolHapus().setEnabled(!kondisi);
    }

    private boolean isKategoriIdExists(String kId) {
        try {
            Kategori temp = new Kategori(koneksi.getConnection());
            Kategori result = temp.getById(kId);
            return result.getId() != null && !result.getId().isEmpty();
        } catch (SQLException ex) {
            Logger.getLogger(KategoriController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean validasiInputBaru() {
        if (kategoriView.getTextId().getText().equals("")) {
            JOptionPane.showMessageDialog(kategoriView, "ID harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (kategoriView.getTextNama().getText().equals("")) {
            JOptionPane.showMessageDialog(kategoriView, "Nama harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String kId = kategoriView.getTextId().getText().trim();
        if (isKategoriIdExists(kId)) {
            JOptionPane.showMessageDialog(kategoriView, "ID Kategori sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean validasiInput() {
        if (kategoriView.getTextId().getText().equals("")) {
            JOptionPane.showMessageDialog(kategoriView, "ID harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (kategoriView.getTextNama().getText().equals("")) {
            JOptionPane.showMessageDialog(kategoriView, "Nama harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void loadData(Kategori kategori, List<Kategori> list) {
        if (kategoriView.getTabelKategori().getSelectedRow() >= 0) {
            int row = kategoriView.getTabelKategori().getSelectedRow();
            kategori = list.get(row);
            kategoriView.getTextId().setText(kategori.getId());
            kategoriView.getTextNama().setText(kategori.getNama());
            enableForm(false);
            kategoriView.getTextNama().setEnabled(true);
        }
    }
}