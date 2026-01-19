/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TransaksiController;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;
import main.App;
import model.Kamar;
import model.Tamu;
import model.Transaksi;

/**
 *
 * @author LENOVO
 */
public class TransaksiView extends javax.swing.JInternalFrame {

    /**
     * Creates new form TransaksiView
     */
    private final Transaksi transaksi;
    private Kamar kamar;
    private Tamu tamu;
    private List<Transaksi> listTransaksi;
    private final TransaksiController transaksiController;

    public TransaksiView() {
        initComponents();

        transaksi = new Transaksi();
        kamar = new Kamar();
        tamu = new Tamu();

        transaksiController = new TransaksiController(this);
        transaksiController.enableForm(false);
        refreshTable();
        initListener();

    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public Tamu getTamu() {
        return tamu;
    }

    public List<Transaksi> getListTransaksi() {
        return listTransaksi;
    }

    public TransaksiController getTransaksiController() {
        return transaksiController;
    }

    public JLabel getIDTamu() {
        return IDTamu;
    }

    public JLabel getIDTransaksi() {
        return IDTransaksi;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public JPanel getjPanel3() {
        return jPanel3;
    }

    public JPanel getjPanel4() {
        return tabel;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JTable getTabelTrans() {
        return tabelTrans;
    }

    public JLabel getNoKamar() {
        return noKamar;
    }

    public JLabel getTanggalKeluar() {
        return tanggalKeluar;
    }

    public JLabel getTanggalMasuk() {
        return tanggalMasuk;
    }

    public JTextField getTextBayar() {
        return textBayar;
    }

    public JTextField getTextKamar() {
        return textKamar;
    }

    public JTextField getTextTamu() {
        return textTamu;
    }

    public JTextField getTextTransaksi() {
        return textTransaksi;
    }

    public JDateChooser getTglKeluar() {
        return tglKeluar;
    }

    public JDateChooser getTglMasuk() {
        return tglMasuk;
    }

    public JButton getTombolCariKamar() {
        return tombolCariKamar;
    }

    public JButton getTombolCariTamu() {
        return tombolCariTamu;
    }

    public JButton getTombolReset() {
        return tombolReset;
    }

    public JButton getTombolSimpan() {
        return tombolSimpan;
    }

    public JPanel getKamarPanel() {
        return kamarPanel;
    }

    public JPanel getTamuPanel() {
        return tamuPanel;
    }

    public JButton getTombolHapus() {
        return tombolHapus;
    }

    public JLabel getLabelTot() {
        return labelTot;
    }

    public JTextField getTextTotBayar() {
        return textTotBayar;
    }

    public JTextField getTextIdTamu() {
        return textIdTamu;
    }

    public JTextField getTextNoKamar() {
        return textNoKamar;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public JLabel getjLabel4() {
        return jLabel4;
    }

    public JPanel getjPanel5() {
        return jPanel5;
    }

    public JPanel getjPanel6() {
        return jPanel6;
    }

    private void refreshTable() {
        listTransaksi = App.masterService.getAllTransaksi();
        tabelTrans.setModel(new TransTableModel(listTransaksi));
    }

    private void initListener() {
        tabelTrans.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            try {
                transaksiController.loadData(transaksi, listTransaksi);
            } catch (ParseException ex) {
                Logger.getLogger(TransaksiView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textNoKamar = new javax.swing.JTextField();
        textIdTamu = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        form = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        IDTransaksi = new javax.swing.JLabel();
        textTransaksi = new javax.swing.JTextField();
        noKamar = new javax.swing.JLabel();
        kamarPanel = new javax.swing.JPanel();
        textKamar = new javax.swing.JTextField();
        tombolCariKamar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textBayar = new javax.swing.JTextField();
        IDTamu = new javax.swing.JLabel();
        tamuPanel = new javax.swing.JPanel();
        textTamu = new javax.swing.JTextField();
        tombolCariTamu = new javax.swing.JButton();
        tanggalMasuk = new javax.swing.JLabel();
        tglMasuk = new com.toedter.calendar.JDateChooser();
        tanggalKeluar = new javax.swing.JLabel();
        tglKeluar = new com.toedter.calendar.JDateChooser();
        labelTot = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textTotBayar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        tombolReset = new javax.swing.JButton();
        tombolHapus = new javax.swing.JButton();
        tombolSimpan = new javax.swing.JButton();
        tabel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTrans = new javax.swing.JTable();

        textNoKamar.setEditable(false);

        textIdTamu.setEditable(false);

        setClosable(true);
        setForeground(java.awt.Color.black);
        setMaximizable(true);
        setResizable(true);
        setTitle("Data Transaksi");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new java.awt.BorderLayout(0, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Data Transaksi");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 20));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Form", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel2.setLayout(new java.awt.GridLayout(8, 2, 30, 20));

        IDTransaksi.setText("ID Transaksi :");
        jPanel2.add(IDTransaksi);

        textTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textTransaksiKeyTyped(evt);
            }
        });
        jPanel2.add(textTransaksi);

        noKamar.setText("Nama Kamar :");
        jPanel2.add(noKamar);

        kamarPanel.setLayout(new javax.swing.BoxLayout(kamarPanel, javax.swing.BoxLayout.LINE_AXIS));
        kamarPanel.add(textKamar);

        tombolCariKamar.setText("cari");
        tombolCariKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolCariKamarActionPerformed(evt);
            }
        });
        kamarPanel.add(tombolCariKamar);

        jPanel2.add(kamarPanel);

        jLabel3.setText("Harga Kamar per Malam");
        jPanel2.add(jLabel3);

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setText("Rp.");
        jPanel5.add(jLabel2);

        textBayar.setEditable(false);
        jPanel5.add(textBayar);

        jPanel2.add(jPanel5);

        IDTamu.setText("Nama Tamu :");
        jPanel2.add(IDTamu);

        tamuPanel.setLayout(new javax.swing.BoxLayout(tamuPanel, javax.swing.BoxLayout.LINE_AXIS));
        tamuPanel.add(textTamu);

        tombolCariTamu.setText("cari");
        tombolCariTamu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolCariTamuActionPerformed(evt);
            }
        });
        tamuPanel.add(tombolCariTamu);

        jPanel2.add(tamuPanel);

        tanggalMasuk.setText("Tanggal Check In :");
        jPanel2.add(tanggalMasuk);
        jPanel2.add(tglMasuk);

        tanggalKeluar.setText("Tanggal Check Out");
        jPanel2.add(tanggalKeluar);

        tglKeluar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglKeluarPropertyChange(evt);
            }
        });
        jPanel2.add(tglKeluar);

        labelTot.setText("Total Bayar");
        jPanel2.add(labelTot);

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setText("Rp.");
        jPanel6.add(jLabel4);

        textTotBayar.setEditable(false);
        jPanel6.add(textTotBayar);

        jPanel2.add(jPanel6);

        tombolReset.setText("Baru");
        tombolReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolResetActionPerformed(evt);
            }
        });
        jPanel3.add(tombolReset);

        tombolHapus.setText("Hapus");
        tombolHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolHapusActionPerformed(evt);
            }
        });
        jPanel3.add(tombolHapus);

        jPanel2.add(jPanel3);

        tombolSimpan.setText("Simpan");
        tombolSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(tombolSimpan);

        form.add(jPanel2);

        jPanel8.add(form);

        tabel.setLayout(new java.awt.BorderLayout());

        tabelTrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelTrans);

        tabel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel8.add(tabel);

        getContentPane().add(jPanel8, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tombolResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolResetActionPerformed
        // TODO add your handling code here:
        transaksiController.clearForm();
        transaksiController.enableForm(true);
        getNoKamar().setVisible(true);
        getKamarPanel().setVisible(true);
        getjLabel3().setVisible(true);
        getjPanel5().setVisible(true);
        getIDTamu().setVisible(true);
        getTamuPanel().setVisible(true);
        getTanggalMasuk().setVisible(true);
        getTglMasuk().setVisible(true);
        getTanggalKeluar().setVisible(true);
        getTglKeluar().setVisible(true);
        getLabelTot().setVisible(true);
        getjPanel6().setVisible(true);
        textTransaksi.requestFocusInWindow();
    }//GEN-LAST:event_tombolResetActionPerformed

    private void tombolCariTamuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolCariTamuActionPerformed
        // TODO add your handling code here:
        String tamu = textTamu.getText();
        transaksiController.cariTamu(tamu);
    }//GEN-LAST:event_tombolCariTamuActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        App.menuView.transaksiView = null;
    }//GEN-LAST:event_formInternalFrameClosed

    private void tglKeluarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglKeluarPropertyChange
        // TODO add your handling code here:
        if (evt != null && "date".equals(evt.getPropertyName())) {
            if (tglKeluar.getDate() != null && tglMasuk.getDate() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {

                    LocalDate t1 = LocalDate.parse(sdf.format(tglMasuk.getDate()), DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalDate t2 = LocalDate.parse(sdf.format(tglKeluar.getDate()), DateTimeFormatter.ISO_LOCAL_DATE);

                    if (t2.isBefore(t1)) {
                        textTotBayar.setText("0");
                        JOptionPane.showMessageDialog(this, "Tanggal Check Out tidak boleh sebelum Tanggal Check In!", "Error", JOptionPane.ERROR_MESSAGE);
                        getTglKeluar().setDate(null);

                    } else if (t2.equals(t1)) {
                        textTotBayar.setText("0");
                        JOptionPane.showMessageDialog(this, "Tanggal Check Out tidak boleh Sama dengan Tanggal Check In!", "Error", JOptionPane.ERROR_MESSAGE);
                        getTglKeluar().setDate(null);

                    } else {

                        Duration diff = Duration.between(t1.atStartOfDay(), t2.atStartOfDay());
                        long diffDays = diff.toDays();

                        double totalBayar = diffDays * Double.parseDouble(textBayar.getText());;
                        textTotBayar.setText(String.valueOf(totalBayar));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghitung total harga: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_tglKeluarPropertyChange

    private void tombolHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusActionPerformed
        // TODO add your handling code here:
        if (transaksiController.validasiInput()) {
            transaksi.setIdTrans(textTransaksi.getText());
            int konfirmasi = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data ini?", "Konfirmasi", JOptionPane.WARNING_MESSAGE);
            if (konfirmasi == 0) {
                App.masterService.hapusTransaksi(transaksi);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus !", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            }

        }
    }//GEN-LAST:event_tombolHapusActionPerformed

    private void tombolSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolSimpanActionPerformed
        // TODO add your handling code here:
        if (transaksiController.validasiInputBaru()) {

            transaksi.setIdTrans(textTransaksi.getText());
            transaksi.setNoKamar(textNoKamar.getText());
            transaksi.setIdTamu(textIdTamu.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            transaksi.setTglCheckin(sdf.format(tglMasuk.getDate()));
            transaksi.setTglCheckout(sdf.format(tglKeluar.getDate()));
            transaksi.setTotBayar(Double.parseDouble(textTotBayar.getText()));

            App.masterService.simpanTransaksi(transaksi);
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
            initListener();
        }
    }//GEN-LAST:event_tombolSimpanActionPerformed

    private void textTransaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textTransaksiKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Hanya Boleh Angka!", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_textTransaksiKeyTyped

    private void tombolCariKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolCariKamarActionPerformed
        // TODO add your handling code here:
        String kamar = textKamar.getText();
        transaksiController.cariKamar(kamar);
    }//GEN-LAST:event_tombolCariKamarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IDTamu;
    private javax.swing.JLabel IDTransaksi;
    private javax.swing.JPanel form;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel kamarPanel;
    private javax.swing.JLabel labelTot;
    private javax.swing.JLabel noKamar;
    private javax.swing.JPanel tabel;
    private javax.swing.JTable tabelTrans;
    private javax.swing.JPanel tamuPanel;
    private javax.swing.JLabel tanggalKeluar;
    private javax.swing.JLabel tanggalMasuk;
    private javax.swing.JTextField textBayar;
    private javax.swing.JTextField textIdTamu;
    private javax.swing.JTextField textKamar;
    private javax.swing.JTextField textNoKamar;
    private javax.swing.JTextField textTamu;
    private javax.swing.JTextField textTotBayar;
    private javax.swing.JTextField textTransaksi;
    private com.toedter.calendar.JDateChooser tglKeluar;
    private com.toedter.calendar.JDateChooser tglMasuk;
    private javax.swing.JButton tombolCariKamar;
    private javax.swing.JButton tombolCariTamu;
    private javax.swing.JButton tombolHapus;
    private javax.swing.JButton tombolReset;
    private javax.swing.JButton tombolSimpan;
    // End of variables declaration//GEN-END:variables

    public class TransTableModel extends AbstractTableModel {

        private List<Transaksi> listTransaksi = new ArrayList<>();
        private final String HEADER[] = {"Id Transaksi", "Nama Kamar", "Nama Tamu", "Tanggal Check In", "Tanggal Check Out", "Total Bayar"};

        public TransTableModel(List<Transaksi> listTransaksi) {
            this.listTransaksi = listTransaksi;
        }

        @Override
        public int getRowCount() {
            return listTransaksi.size();
        }

        @Override
        public int getColumnCount() {
            return HEADER.length;
        }

        @Override
        public String getColumnName(int i) {
            return HEADER[i];
        }

        @Override
        public Object getValueAt(int i, int i1) {
            Transaksi tr = listTransaksi.get(i);
            switch (i1) {
                case 0:
                    return tr.getIdTrans();
                case 1:
                    return tr.getKamar().getNama();
                case 2:
                    return tr.getTamu().getNama();
                case 3:
                    return tr.getTglCheckin();
                case 4:
                    return tr.getTglCheckout();
                case 5:
                    return tr.getBayar();
                default:
                    return null;
            }
        }

    }

}
