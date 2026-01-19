package controller;

import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import main.App;
import model.Kamar;
import view.KamarView;

public class KamarController {

    private final KamarView kamarView;

    public KamarController(KamarView kamarView) {
        this.kamarView = kamarView;
    }

    public void clearForm() {
        kamarView.getTextNo().setText(null);
        kamarView.getTextNama().setText(null);
        kamarView.getComboKategori().setSelectedIndex(0); // Assuming the first index is a placeholder
        kamarView.getTextHarga().setText(null);
        kamarView.getComboStatus().setSelectedIndex(0); // Assuming the first index is a placeholder
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
        ComboBoxModel cbm = new DefaultComboBoxModel(App.masterService.getAllName());
        kamarView.getComboKategori().setModel(cbm);
    }

    private boolean isNoKamarExists(String kId) {
        return App.masterService.isKamarExists(kId);
    }

    public boolean validasiInput() {
        if (kamarView.getTextNo().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(kamarView, "NO Kamar harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
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
        if (kamarView.getComboKategori().getSelectedIndex() == 0) { // Assuming the first index is a placeholder
            JOptionPane.showMessageDialog(kamarView, "Silakan pilih kategori!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (kamarView.getComboStatus().getSelectedIndex() == 0) { // Assuming the first index is a placeholder
            JOptionPane.showMessageDialog(kamarView, "Silakan pilih status!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean validasiInputBaru() {
        if (!validasiInput()) {
            return false; // If any validation fails, return false
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
            kamarView.getComboKategori().setSelectedItem(kamar.getKategori().getNama());
            kamarView.getTextHarga().setText(String.valueOf(kamar.getHarga()));
            kamarView.getComboStatus().setSelectedItem(kamar.getStatus());
            kamarView.getTextNo().setEnabled(false);
            enableForm(false);
            kamarView.getTextNama().setEnabled(true);
            kamarView.getComboKategori().setEnabled(true);
            kamarView.getTextHarga().setEnabled(true);
            kamarView.getComboStatus().setEnabled(true);
        }
    }
}
