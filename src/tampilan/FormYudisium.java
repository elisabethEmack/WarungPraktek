/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emackemax
 */
public class FormYudisium extends JFrame {

    JPanel panel[] = new JPanel[7];
    JTextField textField[] = new JTextField[3];
    JLabel label[] = new JLabel[5];
    JComboBox tahun, bulan, tanggal;
    JCheckBox syarat[] = new JCheckBox[6];
    Container content = getContentPane();
    JButton simpanBut;
    Calendar cal = Calendar.getInstance();
    String nama, noHp;
    String nim;
    int lengthNim;
    String isiLabel[] = {"NIM", "Nama", "No HP", "Periode Yudisium", "Tanggal Daftar"};
    String isiTahun[] = {"2014", "2015", "2016",
        "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024"};
    String isiBulan[] = {"Jan", "Feb", "Mar",
        "Apr", "Mei", "Jun", "Jul", "Agust", "Sept",
        "Okt", "Nov", "Des"};
    String isiSyarat[] = {"Daftar Nilai", "Naskah TA",
        "Bebas Pinjam dari Lab", "Bebas Pinjam dari Dosen",
        "Bukti Penyerahan Laporan KP", "Surat Keterangan KKL/KI"};
    SimpleDateFormat tanggalDaftar = new SimpleDateFormat("d-MMM-yyyy");
    String tanggalDaftarString = tanggalDaftar.format(cal.getTime());
    JLabel label5 = new JLabel();

    ArrayList<PendaftarYudisium> pendaftar = new ArrayList<>();
    File input = new File("PendaftarYudisium.data");

    public FormYudisium() {

        setSize(550, 390);
        setTitle("Form Yudisium");
        setLocation(100, 100);
        setResizable(true);
        content.setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        for (int i = 0; i < panel.length; i++) {
            panel[i] = new JPanel();
        }
        panel[0].setLayout(new GridLayout(5, 2));
        panel[1].setLayout(new GridLayout(1, 2));// panel combobox
        panel[2].setLayout(new GridLayout(3, 2));//panel utk checkbox
        panel[3].setLayout(new FlowLayout());//panel utk kotak checkbox
        panel[4].setLayout(new FlowLayout());// panel 5= 4
        panel[5].setLayout(new GridLayout(3, 1));//menampung semua panel
        panel[6].setLayout(new FlowLayout());//menampung tanggal daftar

        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel(isiLabel[i]);
        }

        for (int i = 0; i < textField.length; i++) {
            textField[i] = new JTextField();
            textField[i].setColumns(20);
        }

        bulan = new JComboBox(isiBulan);
        tahun = new JComboBox(isiTahun);
        panel[1].add(bulan);
        panel[1].add(tahun);

        //mengeset tanggal daftar
        label5.setText(tanggalDaftarString);
        panel[6].add(label5);

        for (int i = 0; i < label.length; i++) {
            panel[0].add(label[i]);
            if (i < textField.length) {
                panel[0].add(textField[i]);
            }
            if (i == label.length - 2) {
                panel[0].add(panel[1]);
            }
            if (i == label.length - 1) {
                panel[0].add(panel[6]);
            }
        }

        panel[0].setBorder(BorderFactory.createTitledBorder("Data Diri"));
        panel[5].add(panel[0]);

        CheckBoxHandler handler = new CheckBoxHandler();
        for (int i = 0; i < syarat.length; i++) {
            syarat[i] = new JCheckBox(isiSyarat[i]);
            panel[2].add(syarat[i]);
            syarat[i].addActionListener(handler);
        }
        panel[2].setBorder(BorderFactory.createTitledBorder("Syarat"));
        panel[5].add(panel[2]);

        simpanBut = new JButton("Simpan");
        simpanBut.setHorizontalAlignment(SwingConstants.CENTER);
        simpanBut.setVerticalAlignment(SwingConstants.BOTTOM);
        panel[4].add(simpanBut);
        panel[5].add(panel[4]);

        content.add(panel[5]);

        simpanBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File outfile = new File("PendaftarYudisium.data");
                    File jsonOut=new File("PendaftarYudisium.java");
                    try (ObjectInputStream input = new ObjectInputStream(new
         FileInputStream(outfile))) {
                        pendaftar = (ArrayList<PendaftarYudisium>) input.readObject();
                    } catch (Exception ex) {
                        pendaftar = new ArrayList<>();
                    }
                    setNim(textField[0].getText());
                    lengthNim = textField[0].getText().length();
                    setNama(textField[1].getText());
                    setNoHp(textField[2].getText());

                    ObjectOutputStream objectOutputStream = new ObjectOutputStream
        (new FileOutputStream(outfile));

                    PendaftarYudisium data = new PendaftarYudisium(nim, noHp, nama, tanggalDaftarString);
                    String bulanYudisium = (bulan.getSelectedItem().toString());
                    String tahunYudisium = (tahun.getSelectedItem().toString());
                    data.setPeriodeYudisium(bulanYudisium, tahunYudisium);
                    data.setSyarat(dataSyarat);

                    pendaftar.add(data);

                    objectOutputStream.writeObject(pendaftar);
                    objectOutputStream.close();

                    JOptionPane.showMessageDialog(null, "Data Tersimpan");

                    for (int i = 0; i < textField.length; i++) {
                        textField[i].setText("");
                    }
                    for (int i = 0; i < syarat.length; i++) {
                        syarat[i].setSelected(false);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "\n Data tidak tersimpan");
                } catch (FileNotFoundException eX) {
                    try {
                        throw new Exception("File tidak ketemu");
                    } catch (Exception ex) {
                        Logger.getLogger(FormYudisium.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
    }
    String dataSyarat[] = new String[6];

    public class CheckBoxHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < isiSyarat.length; i++) {
                dataSyarat[i] = "-";
                if (syarat[i].isSelected()) {
                    dataSyarat[i] = "ada";
                }
            }
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) throws Exception {
        String regex = "[a-zA-Z ]+";
        if (!nama.matches(regex)) {
            throw new Exception("Nama harus berupa alfabet");
        }
        this.nama = nama;
    }

    public String getNim() throws NumberFormatException {
        return nim;
    }

    public void setNim(String nim) {
        if (nim.matches("\\d{9}")) {
            this.nim = nim;
        } else {
            throw new NumberFormatException("NIM Salah, harus 9 digit");
        }
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) throws Exception {
        String regex = "0\\d{10,12}";
        if (!noHp.matches(regex)) {
            throw new Exception("No HP salah");
        } else {
            this.noHp = noHp;
        }
    }

    public static void main(String[] args) {
        FormYudisium form = new FormYudisium();
        form.setVisible(true);
    }
}