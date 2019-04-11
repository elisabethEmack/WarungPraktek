/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author emackemax
 */
public class TampilAwal extends JFrame {

    JButton butDaftar;
    JLabel l1;
    JMenuBar menuBar = new JMenuBar();
    JMenu menuPasien;
    JMenuItem tampilkanRM;

    public TampilAwal() {
        setTitle("SISTEM PRAKTEK DOKTER");
        setLocation(50, 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("background.png"));
        add(background);
        background.setLayout(new FlowLayout());
//        l1 = new JLabel("MAU DAFTAR???");
        butDaftar = new JButton("DAFTAR PASIEN BARU");
        butDaftar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("DAFTAR PASIEN BARU")) {
                    FormYudisium form = new FormYudisium();
                    form.setVisible(true);
                }
            }
        });

//        background.add(l1);
        background.add(butDaftar);

//        setJMenuBar(menuBar);
//        menu = new JMenu("Pilihan");
//        menuBar.add(menu);
//        semuaDataPendaftar = new JMenuItem("Data Pendaftar");
//        menu.add(semuaDataPendaftar);
//
//        semuaDataPendaftar.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getActionCommand().equals("Data Pendaftar")) {
//                    new Demo().runDemo();
//                }
//            }
//        });
//        pack();
//        setVisible(true);
        
        setJMenuBar(menuBar);
        menuPasien = new JMenu("Obat");
        menuBar.add(menuPasien);
        tampilkanRM = new JMenuItem("Perbarui Data Obat");
        menuPasien.add(tampilkanRM);

        tampilkanRM.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Perbarui Data Obat")) {
                    new Demo().runDemo();
                }
            }
        });
        pack();
        setVisible(true);
        
        //menubar pasien
        menuPasien = new JMenu("Pasien");
        menuBar.add(menuPasien);
        
        //update data pasien
        tampilkanRM = new JMenuItem("Perbarui Data Pasien");
        menuPasien.add(tampilkanRM);

        tampilkanRM.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Perbarui Data Pasien")) {
                    new Demo().runDemo();
                }
            }
        });
        pack();
        setVisible(true);
        
        //rekam medis pasien
        tampilkanRM = new JMenuItem("Rekam Medis Pasien");
        menuPasien.add(tampilkanRM);

        tampilkanRM.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Rekam Medis Pasien")) {
                    new Demo().runDemo();
                }
            }
        });
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new TampilAwal();
    }
}