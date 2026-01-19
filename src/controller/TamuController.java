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
import model.Tamu;
import view.TamuView;

/**
 *
 * @author NATA
 */
public class TamuController {

    private final TamuView tamuView;
    private Tamu t;
    private Koneksi koneksi;

    public TamuController(TamuView tamuView) {
        this.tamuView = tamuView;
        koneksi = new Koneksi();
        t = new Tamu(koneksi.getConnection());
    }

    public Tamu getTamu() {
        return t;
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
        try {
            Tamu temp = new Tamu(koneksi.getConnection());
            Tamu result = temp.getById(kId);
            return result.getIdTamu() != null && !result.getIdTamu().isEmpty();
        } catch (SQLException ex) {
            Logger.getLogger(TamuController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
        }
    }
}