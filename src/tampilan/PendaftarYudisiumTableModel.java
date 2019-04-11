/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


/**
 *
 * @author ric
 */
public class PendaftarYudisiumTableModel extends AbstractTableModel {

    private static final String[] FIELD = {"NIM", "Nama", "No HP", "Tanggal Daftar"
            , "Periode Yudisium", "Daftar Nilai", "Naskah TA","Bebas Pinjam dari Lab", 
            "Bebas Pinjam dari Dosen", "Bukti Penyerahan Laporan KP", "Surat Keterangan KKL/KI"}; //Membuat Nama Field

    private ArrayList<PendaftarYudisium> data;

    public void tambahPendaftarYudisium(PendaftarYudisium mhs) {
        data.add(mhs);
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex > 4 && columnIndex < 11) {
            return Boolean.class;
        }
        return String.class;
    }

    public PendaftarYudisiumTableModel() {
        data = new ArrayList<>();
    }

    public PendaftarYudisiumTableModel(ArrayList<PendaftarYudisium> data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int column) {
        return FIELD[column];
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PendaftarYudisium x = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return x.nim;
            case 1:
                return x.nama;
            case 2:
                return x.noHP;
            case 3:
                return x.tanggalDaftar;
            case 4:
                return x.periodeYudisium;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return x.syarat[columnIndex - 5].equals("ada");
        }
        return null;
    }
}
