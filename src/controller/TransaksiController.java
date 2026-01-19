package controller;

import config.Koneksi;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.App;
import model.Kamar;
import model.Tamu;
import model.Transaksi;
import view.KamarDialog;
import view.KamarView;
import view.TamuDialog;
import view.TamuView;
import view.TransaksiView;

/**
 * @author NATA
 */
public class TransaksiController {

    private final TransaksiView transaksiView;
    private List<Kamar> listKamar;
    private Kamar kamar;
    private List<Tamu> listTamu;
    private Tamu tamu;
    private Koneksi koneksi;

    public TransaksiController(TransaksiView transaksiView) {
        this.transaksiView = transaksiView;
        koneksi = new Koneksi();
    }

    public void cariKamar(String name) {
        try {
            KamarDialog kamarDialog = new KamarDialog(App.getMenuView(), true);
            
            Kamar km = new Kamar(koneksi.getConnection());
            listKamar = km.cariKamar(name);

            if (listKamar == null || listKamar.isEmpty()) {
                JOptionPane.showMessageDialog(kamarDialog, "Kamar tidak ditemukan.");
                return;
            }

            KamarView kv = new KamarView();
            KamarView.KamarTableModel kamarTabelModel = kv.new KamarTableModel(listKamar);

            kamarDialog.getTabelCariKamar().setModel(kamarTabelModel);

            kamarDialog.getTabelCariKamar().getSelectionModel().addListSelectionListener((lse) -> {
                if (kamarDialog.getTabelCariKamar().getSelectedRow() >= 0) {
                    int row = kamarDialog.getTabelCariKamar().getSelectedRow();
                    kamar = listKamar.get(row);
                    transaksiView.getTextKamar().setText(kamar.getNama());
                    transaksiView.getTextBayar().setText(String.valueOf(kamar.getHarga()));
                    transaksiView.getTextNoKamar().setText(kamar.getNoKamar());
                    kamarDialog.dispose(); // Close dialog after selection
                }
            });

            kamarDialog.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(transaksiView, "Gagal mencari kamar: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cariTamu(String namaTamu) {
        try {
            TamuDialog tamuDialog = new TamuDialog(App.getMenuView(), true);
            
            Tamu tm = new Tamu(koneksi.getConnection());
            listTamu = tm.cariTamu(namaTamu);
            
            if (listTamu == null || listTamu.isEmpty()) {
                JOptionPane.showMessageDialog(tamuDialog, "Tamu tidak ditemukan.");
                return;
            }

            TamuView kv = new TamuView();
            TamuView.TamuTableModel tamuTabelModel = kv.new TamuTableModel(listTamu);

            tamuDialog.getTabelCariTamu().setModel(tamuTabelModel);

            tamuDialog.getTabelCariTamu().getSelectionModel().addListSelectionListener((lse) -> {
                if (tamuDialog.getTabelCariTamu().getSelectedRow() >= 0) {
                    int row = tamuDialog.getTabelCariTamu().getSelectedRow();
                    tamu = listTamu.get(row);
                    transaksiView.getTextTamu().setText(tamu.getNama());
                    transaksiView.getTextIdTamu().setText(tamu.getIdTamu());
                    tamuDialog.dispose(); // Close dialog after selection
                }
            });

            tamuDialog.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(transaksiView, "Gagal mencari tamu: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean validasiInput() {
        if (transaksiView.getTextTransaksi().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(transaksiView, "ID harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean validasiInputBaru() {
        if (transaksiView.getTextTransaksi().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(transaksiView, "ID harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String transactionId = transaksiView.getTextTransaksi().getText().trim();
        if (isTransactionIdExists(transactionId)) {
            JOptionPane.showMessageDialog(transaksiView, "ID transaksi sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (transaksiView.getTextNoKamar().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(transaksiView, "Nomor Kamar harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (transaksiView.getTextIdTamu().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(transaksiView, "ID Tamu harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (transaksiView.getTglMasuk().getDate() == null) {
            JOptionPane.showMessageDialog(transaksiView, "Tanggal Check-In harus dipilih!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (transaksiView.getTglKeluar().getDate() == null) {
            JOptionPane.showMessageDialog(transaksiView, "Tanggal Check-Out harus dipilih!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (transaksiView.getTextTotBayar().getText().trim().isEmpty() || 
            transaksiView.getTextTotBayar().getText().equals("0")) {
            JOptionPane.showMessageDialog(transaksiView, "Total bayar harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }

    private boolean isTransactionIdExists(String trId) {
        try {
            Transaksi temp = new Transaksi(koneksi.getConnection());
            Transaksi result = temp.getById(trId);
            return result.getIdTrans() != null && !result.getIdTrans().isEmpty();
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void enableForm(boolean kondisi) {
        transaksiView.getTextTransaksi().setEnabled(kondisi);
        transaksiView.getTextKamar().setEnabled(kondisi);
        transaksiView.getTextTamu().setEnabled(kondisi);
        transaksiView.getTglMasuk().setEnabled(kondisi);
        transaksiView.getTglKeluar().setEnabled(kondisi);
        transaksiView.getTombolCariKamar().setEnabled(kondisi);
        transaksiView.getTombolCariTamu().setEnabled(kondisi);
        transaksiView.getTombolSimpan().setEnabled(kondisi);
        transaksiView.getTombolHapus().setEnabled(!kondisi);
    }

    public void loadData(Transaksi transaksi, List<Transaksi> list) throws ParseException {
        if (transaksiView.getTabelTrans().getSelectedRow() >= 0) {
            int row = transaksiView.getTabelTrans().getSelectedRow();
            transaksi = list.get(row);
            transaksiView.getTextTransaksi().setText(transaksi.getIdTrans());

            // Hide form fields when viewing existing transaction
            transaksiView.getNoKamar().setVisible(false);
            transaksiView.getKamarPanel().setVisible(false);
            transaksiView.getjLabel3().setVisible(false);
            transaksiView.getjPanel5().setVisible(false);
            transaksiView.getIDTamu().setVisible(false);
            transaksiView.getTamuPanel().setVisible(false);
            transaksiView.getTanggalMasuk().setVisible(false);
            transaksiView.getTglMasuk().setVisible(false);
            transaksiView.getTanggalKeluar().setVisible(false);
            transaksiView.getTglKeluar().setVisible(false);
            transaksiView.getLabelTot().setVisible(false);
            transaksiView.getjPanel6().setVisible(false);
            enableForm(false);
        }
    }

    public void clearForm() {
        transaksiView.getTextTransaksi().setText(null);
        transaksiView.getTextKamar().setText(null);
        transaksiView.getTextTamu().setText(null);
        transaksiView.getTextBayar().setText(null);
        transaksiView.getTextTotBayar().setText(null);
        transaksiView.getTextNoKamar().setText(null);  // FIX: Clear hidden field
        transaksiView.getTextIdTamu().setText(null);   // FIX: Clear hidden field
        transaksiView.getTglMasuk().setDate(null);
        transaksiView.getTglKeluar().setDate(null);
        transaksiView.getTabelTrans().clearSelection();
    }

    public void setKamar(Kamar kamar) {
        this.kamar = kamar;
        transaksiView.getTextKamar().setText(kamar.getNama());
    }

    public void setTamu(Tamu tamu) {
        this.tamu = tamu;
        transaksiView.getTextTamu().setText(tamu.getNama());
    }
}