package controller;

import java.util.List;
import javax.swing.JOptionPane;
import main.App;
import model.Kategori;
import view.KategoriView;

public class KategoriController {

    private KategoriView kategoriView;

    public KategoriController(KategoriView kategoriView) {
        this.kategoriView = kategoriView;
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
        return App.masterService.isKategoriExists(kId);
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
        } else {

        }
    }
}
