/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Taopik Ridwan
 */
public class Demo {

    JTable tabel;
    ArrayList<PendaftarYudisium> dataYudisium;
    String select[] = {"Angkatan", "Nama", "Periode Yudisium", "Tanggal Daftar"};

    public Demo() {
        try {
            File outfile = new File("PendaftarYudisium.data");
            
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream(outfile));
            dataYudisium = (ArrayList<PendaftarYudisium>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException ex) {
            new Exception(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void runDemo() {
        TableModel model = new PendaftarYudisiumTableModel(dataYudisium);
        tabel = new JTable(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        sorter.setComparator(4, new Comparator<String>() {
            List<String> bulan = Arrays.asList("Jan", "Feb", "Mar",
                    "Apr", "Mei", "Jun", "Jul", "Agust", "Sept",
                    "Okt", "Nov", "Des");

            @Override
            public int compare(String o1, String o2) {
                String[] periode1 = o1.split(" "), periode2 = o2.split(" ");
                int compareTahun = periode1[1].compareTo(periode2[1]);
                if (compareTahun != 0) {
                    return compareTahun;
                }
                int bulan1 = bulan.indexOf(periode1[0]), bulan2 = bulan.indexOf(periode2[0]);
                return bulan1 - bulan2;
            }
        });
        tabel.setRowSorter(sorter);
        JScrollPane pane = new JScrollPane(tabel, ScrollPaneConstants.
                VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AutoResizeTable.sesuaikanKolom(tabel);
            }
        });
        JFrame app = new JFrame("DATA PENDAFTAR YUDISIUM");
        app.dispose();
        app.getContentPane().add(pane);
        app.setSize(1300, 500);
        app.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        app.setJMenuBar(menuBar);

        JMenuItem filterMenu = new JMenuItem("Filter Data");
        menuBar.add(filterMenu);
        filterMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FormFilter(Demo.this).setVisible(true);
            }
        });
        app.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.
                    getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Demo().runDemo();
            }
        });
    }
}