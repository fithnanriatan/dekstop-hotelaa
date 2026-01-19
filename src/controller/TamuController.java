package controller;

import java.util.List;
import javax.swing.JOptionPane;
import main.App;
import model.Tamu;
import view.TamuView;

public class TamuController {

    private final TamuView tamuView;

    public TamuController(TamuView tamuView) {
        this.tamuView = tamuView;
    }

    public void clearForm() {
        tamuView.getTextNo().setText(null);
        tamuView.getTextNama().setText(null);
        tamuView.getTextAlamat().setText(null);
        tamuView.getTextTelp().setText(null);
        tamuView.getTabelTamu().clearSelection();
    }

    public void enableForm(boolean kondisi) {
        tamuView.getTextNo().setEnabled(kondisi);
        tamuView.getTextNama().setEnabled(kondisi);
        tamuView.getTextAlamat().setEnabled(kondisi);
        tamuView.getTextTelp().setEnabled(kondisi);
        tamuView.getTombolSimpan().setEnabled(kondisi);
        tamuView.getTombolUbah().setEnabled(!kondisi);
        tamuView.getTombolHapus().setEnabled(!kondisi);
    }

    private boolean isTamuIdExists(String kId) {
        return App.masterService.isTamuExists(kId);
    }

    public boolean validasiInputBaru() {
        if (tamuView.getTextNo().getText().equals("")) {
            JOptionPane.showMessageDialog(tamuView, "ID harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tamuView.getTextNama().getText().equals("")) {
            JOptionPane.showMessageDialog(tamuView, "Nama Pemesan harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tamuView.getTextAlamat().getText().equals("")) {
            JOptionPane.showMessageDialog(tamuView, "Alamat harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tamuView.getTextTelp().getText().equals("")) {
            JOptionPane.showMessageDialog(tamuView, "No Telepon harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String tId = tamuView.getTextNo().getText().trim();
        if (isTamuIdExists(tId)) {
            JOptionPane.showMessageDialog(tamuView, "ID Tamu sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;

    }

    public boolean validasiInput() {
        if (tamuView.getTextNo().getText().equals("")) {
            JOptionPane.showMessageDialog(tamuView, "ID harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tamuView.getTextNama().getText().equals("")) {
            JOptionPane.showMessageDialog(tamuView, "Nama Pemesan harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tamuView.getTextAlamat().getText().equals("")) {
            JOptionPane.showMessageDialog(tamuView, "Alamat harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tamuView.getTextTelp().getText().equals("")) {
            JOptionPane.showMessageDialog(tamuView, "No Telepon harus di isi !", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;

    }

    public void loadData(Tamu tamu, List<Tamu> list) {
        if (tamuView.getTabelTamu().getSelectedRow() >= 0) {
            int row = tamuView.getTabelTamu().getSelectedRow();
            tamu = list.get(row);
            tamuView.getTextNo().setText(tamu.getIdTamu());
            tamuView.getTextNama().setText(tamu.getNama());
            tamuView.getTextAlamat().setText(tamu.getAlamat());
            tamuView.getTextTelp().setText(tamu.getNoTelepon());
            enableForm(false);
            tamuView.getTextNama().setEnabled(true);
            tamuView.getTextAlamat().setEnabled(true);
            tamuView.getTextTelp().setEnabled(true);
        } else {

        }
    }

}
