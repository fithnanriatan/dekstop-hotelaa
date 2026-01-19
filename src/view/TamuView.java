/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TamuController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import main.App;
import model.Tamu;

public class TamuView extends javax.swing.JInternalFrame {

    private final Tamu tamu;
    private List<Tamu> listTamu;
    private final TamuController TamuController;

    public TamuView() {
        initComponents();
        tamu = new Tamu();
        TamuController = new TamuController(this);
        TamuController.enableForm(false);
        refreshTable();
        initListener();
    }

    public JTextField getTextNo() {
        return textNo;
    }

    public JTextField getTextNama() {
        return textNama;
    }

    public JTextField getTextAlamat() {
        return textAlamat;
    }

    public JTextField getTextTelp() {
        return textTelp;
    }

    public JButton getTombolBaru() {
        return tombolBaru;
    }

    public JButton getTombolHapus() {
        return tombolHapus;
    }

    public JButton getTombolSimpan() {
        return tombolSimpan;
    }

    public JButton getTombolUbah() {
        return tombolUbah;
    }

    public JTable getTabelTamu() {
        return tabelTamu;
    }

    private void refreshTable() {
        listTamu = App.masterService.getAllTamu();
        tabelTamu.setModel(new TamuTableModel(listTamu));
    }

    private void initListener() {
        tabelTamu.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            TamuController.loadData(tamu, listTamu);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pJudul = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        form = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelNo = new javax.swing.JLabel();
        textNo = new javax.swing.JTextField();
        labelNama = new javax.swing.JLabel();
        textNama = new javax.swing.JTextField();
        labelAlamat = new javax.swing.JLabel();
        textAlamat = new javax.swing.JTextField();
        labelTelp = new javax.swing.JLabel();
        textTelp = new javax.swing.JTextField();
        tombolBaru = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tombolSimpan = new javax.swing.JButton();
        tombolUbah = new javax.swing.JButton();
        tombolHapus = new javax.swing.JButton();
        tabel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTamu = new javax.swing.JTable();

        setClosable(true);
        setTitle("Data Tamu");
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

        judul.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        judul.setText("DATA TAMU");
        pJudul.add(judul);

        getContentPane().add(pJudul, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        form.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Form", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(30, 20, 30, 20)));
        jPanel2.setLayout(new java.awt.GridLayout(5, 2, 0, 10));

        labelNo.setText("Id Tamu");
        jPanel2.add(labelNo);
        jPanel2.add(textNo);

        labelNama.setText("Nama Pemesan");
        jPanel2.add(labelNama);
        jPanel2.add(textNama);

        labelAlamat.setText("Alamat");
        jPanel2.add(labelAlamat);
        jPanel2.add(textAlamat);

        labelTelp.setText("No Telepon");
        jPanel2.add(labelTelp);
        jPanel2.add(textTelp);

        tombolBaru.setText("Baru");
        tombolBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolBaruActionPerformed(evt);
            }
        });
        jPanel2.add(tombolBaru);

        tombolSimpan.setText("Simpan");
        tombolSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolSimpanActionPerformed(evt);
            }
        });
        jPanel3.add(tombolSimpan);

        tombolUbah.setText("Ubah");
        tombolUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolUbahActionPerformed(evt);
            }
        });
        jPanel3.add(tombolUbah);

        tombolHapus.setText("Hapus");
        tombolHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolHapusActionPerformed(evt);
            }
        });
        jPanel3.add(tombolHapus);

        jPanel2.add(jPanel3);

        form.add(jPanel2);

        jPanel1.add(form);

        tabelTamu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelTamu);

        tabel.add(jScrollPane1);

        jPanel1.add(tabel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tombolHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusActionPerformed
        // TODO add your handling code here:
        if (TamuController.validasiInput()) {
            tamu.setIdTamu(textNo.getText());
            int konfirmasi = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data ini?", "Konfirmasi", JOptionPane.WARNING_MESSAGE);
            if (konfirmasi == 0) {
                App.masterService.hapusTamu(tamu);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus !", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
                TamuController.clearForm();
            }

        }
    }//GEN-LAST:event_tombolHapusActionPerformed

    private void tombolSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolSimpanActionPerformed
        // TODO add your handling code here:
        if (TamuController.validasiInputBaru()) {
            tamu.setIdTamu(textNo.getText());
            tamu.setNama(textNama.getText());
            tamu.setAlamat(textAlamat.getText());
            tamu.setNoTelepon(textTelp.getText());

            App.masterService.simpanTamu(tamu);
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan !", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        }
    }//GEN-LAST:event_tombolSimpanActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        App.menuView.tamuView = null;
    }//GEN-LAST:event_formInternalFrameClosed

    private void tombolBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolBaruActionPerformed
        // TODO add your handling code here:
        TamuController.clearForm();
        TamuController.enableForm(true);
        textNo.requestFocusInWindow();

    }//GEN-LAST:event_tombolBaruActionPerformed

    private void tombolUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolUbahActionPerformed
        // TODO add your handling code here:
        if (TamuController.validasiInput()) {
            tamu.setIdTamu(textNo.getText());
            tamu.setNama(textNama.getText());
            tamu.setAlamat(textAlamat.getText());
            tamu.setNoTelepon(textTelp.getText());

            App.masterService.ubahTamu(tamu);
            JOptionPane.showMessageDialog(this, "Tamu berhasil diubah !", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        }
    }//GEN-LAST:event_tombolUbahActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel form;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel judul;
    private javax.swing.JLabel labelAlamat;
    private javax.swing.JLabel labelNama;
    private javax.swing.JLabel labelNo;
    private javax.swing.JLabel labelTelp;
    private javax.swing.JPanel pJudul;
    private javax.swing.JPanel tabel;
    private javax.swing.JTable tabelTamu;
    private javax.swing.JTextField textAlamat;
    private javax.swing.JTextField textNama;
    private javax.swing.JTextField textNo;
    private javax.swing.JTextField textTelp;
    private javax.swing.JButton tombolBaru;
    private javax.swing.JButton tombolHapus;
    private javax.swing.JButton tombolSimpan;
    private javax.swing.JButton tombolUbah;
    // End of variables declaration//GEN-END:variables

    public class TamuTableModel extends AbstractTableModel {

        private List<Tamu> listTamu = new ArrayList<>();

        private final String HEADER[] = {"idTamu", "nama", "alamat", "noTelepon"};

        public TamuTableModel(List<Tamu> listTamu) {
            this.listTamu = listTamu;
        }

        @Override
        public int getRowCount() { // jumlah baris
            return listTamu.size();
        }

        @Override
        public int getColumnCount() { // jumlah kolom
            return HEADER.length;
        }

        @Override
        public String getColumnName(int i) { // nama kolom
            return HEADER[i];
        }

        @Override
        public Object getValueAt(int i, int i1) { // mengisi data
            Tamu t = listTamu.get(i); // baris
            switch (i1) { // kolom
                case 0:
                    return t.getIdTamu();
                case 1:
                    return t.getNama();
                case 2:
                    return t.getAlamat();
                case 3:
                    return t.getNoTelepon();
                default:
                    return null;
            }
        }
    }
}
