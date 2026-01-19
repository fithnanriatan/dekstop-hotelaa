package controller;

import java.text.ParseException;
import java.util.List;
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

public class TransaksiController {

    private final TransaksiView transaksiView;
    private List<Kamar> listKamar;
    private Kamar kamar;
    private List<Tamu> listTamu;
    private Tamu tamu;

    public TransaksiController(TransaksiView transaksiView) {
        this.transaksiView = transaksiView;
    }

    public void cariKamar(String name) {
        KamarDialog kamarDialog = new KamarDialog(App.menuView, true);

        listKamar = App.masterService.findKamarByName(name);

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

            }
        });

        kamarDialog.setVisible(true);
    }

    public void cariTamu(String namaTamu) {
        TamuDialog tamuDialog = new TamuDialog(App.menuView, true);

        listTamu = App.masterService.getByNameTamu(namaTamu);
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

            }
        });

        tamuDialog.setVisible(true);
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
        return true;
    }

    private boolean isTransactionIdExists(String trId) {
        return App.masterService.isTransaksiExists(trId);
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
        transaksiView.getTglMasuk().setDate(null);
        transaksiView.getTglKeluar().setDate(null);
        transaksiView.getTabelTrans().clearSelection();
    }

    public void simpanTransaksi(Transaksi transaksi) {
        App.masterService.simpanTransaksi(transaksi);
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
