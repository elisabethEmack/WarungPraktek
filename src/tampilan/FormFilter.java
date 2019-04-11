/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Windows 8
 */
public class FormFilter extends JDialog {

    private JButton btnApply;
    private JButton btnClear;
    private JButton btnClose;
    private JCheckBox chkAngkatan;
    private JCheckBox chkNama;
    private JCheckBox chkPeriode;
    private JComboBox cmbBulan;
    private JSpinner spnTahun;
    private JFormattedTextField txtAngkatan;
    private JTextField txtNama;
    private final Demo parent;
    private static final String isiBulan[] = {"Jan", "Feb", "Mar",
        "Apr", "Mei", "Jun", "Jul", "Agust", "Sept",
        "Okt", "Nov", "Des"};

    private abstract static class TableRowFilter extends RowFilter<TableModel, Integer> {
        //digunakan untuk mempersingkat penulisan TableRowFilter
    }

    public FormFilter(Demo parent) {
        //parent digunakan untuk menghubungkan 2 kelas java
        this.parent = parent;
        initComponents();
    }

    private void initComponents() {
        setLocation(100, 100);
        JPanel jPanel1 = new JPanel();
        chkAngkatan = new JCheckBox();
        txtAngkatan = new JFormattedTextField();
        JPanel jPanel2 = new JPanel();
        chkNama = new JCheckBox();
        txtNama = new JTextField();
        JPanel jPanel3 = new JPanel();
        chkPeriode = new JCheckBox();
        JPanel jPanel6 = new JPanel();
        cmbBulan = new JComboBox();
        spnTahun = new JSpinner();

        JPanel jPanel5 = new JPanel();
        btnApply = new JButton();
        btnClear = new JButton();
        btnClose = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        getContentPane().setLayout(new GridLayout(5, 1));

        jPanel1.setLayout(new GridLayout(1, 2));

        chkAngkatan.setText("Angkatan");
        chkAngkatan.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAngkatan.setEnabled(chkAngkatan.isSelected());
            }
        });

        jPanel1.add(chkAngkatan);

        txtAngkatan.setColumns(2);
        txtAngkatan.setFormatterFactory(new DefaultFormatterFactory(new 
        NumberFormatter(new DecimalFormat("#"))));
        txtAngkatan.setEnabled(false);
        jPanel1.add(txtAngkatan);

        getContentPane().add(jPanel1);

        jPanel2.setLayout(new GridLayout(1, 2));

        chkNama.setText("Nama");
        chkNama.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNama.setEnabled(chkNama.isSelected());
            }
        });
        jPanel2.add(chkNama);

        txtNama.setEnabled(false);
        jPanel2.add(txtNama);

        getContentPane().add(jPanel2);

        jPanel3.setLayout(new GridLayout(1, 3));

        chkPeriode.setText("Periode Yudisium");
        chkPeriode.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boolean checked = chkPeriode.isSelected();
                cmbBulan.setEnabled(checked);
                spnTahun.setEnabled(checked);
            }
        });
        jPanel3.add(chkPeriode);

        cmbBulan.setModel(new DefaultComboBoxModel(isiBulan));
        cmbBulan.setEnabled(false);
        cmbBulan.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        jPanel6.add(cmbBulan);

        spnTahun.setModel(new SpinnerNumberModel(Integer.valueOf(2015), null, null, Integer.valueOf(1)));
        spnTahun.setEditor(new JSpinner.NumberEditor(spnTahun, "#"));
        spnTahun.setEnabled(false);
        spnTahun.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                //System.out.println(spnTahun.getValue());
            }
        });
        jPanel6.add(spnTahun);

        jPanel3.add(jPanel6);

        getContentPane().add(jPanel3);

        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCheckBoxes();
                List<TableRowFilter> filterList = new ArrayList<>();
                TableRowFilter rf = null;
                if (chkAngkatan.isSelected()) {
                    rf = new TableRowFilter() {
                        @Override
                        //digunakan utk memfilter
                        public boolean include(RowFilter.Entry<? extends TableModel, ? extends Integer> entry) {
                            TableModel model = entry.getModel();
                            String nim = model.getValueAt(entry.getIdentifier(), 0).toString();
                            return nim.matches("^" + txtAngkatan.getText() + "\\d{7}");
                        }
                    };
                    filterList.add(rf);
                }
                if (chkNama.isSelected()) {
                    rf = new TableRowFilter() {
                        @Override
                        public boolean include(RowFilter.Entry<? extends TableModel, ? extends Integer> entry) {
                            TableModel model = entry.getModel();
                            String nama = model.getValueAt(entry.getIdentifier(), 1).toString();
                            return nama.contains(txtNama.getText());
                        }
                    };
                    filterList.add(rf);
                }
                if (chkPeriode.isSelected()) {
                    rf = new TableRowFilter() {
                        @Override
                        public boolean include(RowFilter.Entry<? extends TableModel, ? extends Integer> entry) {
                            TableModel model = entry.getModel();
                            String[] periode = model.getValueAt(entry.getIdentifier(), 4).toString().split(" ");
                            return periode[0].equals(cmbBulan.getSelectedItem()) && periode[1].equals(spnTahun.getValue().toString());
                        }
                    };
                    filterList.add(rf);
                }
                ((TableRowSorter) parent.tabel.getRowSorter()).setRowFilter(RowFilter.andFilter(filterList));
            }
        });

        jPanel5.add(btnApply);

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel5.add(btnClear);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
        jPanel5.add(btnClose);
        getContentPane().add(jPanel5);
        pack();
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        txtAngkatan.setText("");
        txtNama.setText("");
        txtAngkatan.setEnabled(false);
        txtNama.setEnabled(false);
        cmbBulan.setEnabled(false);
        spnTahun.setEnabled(false);
        chkAngkatan.setSelected(false);
        chkNama.setSelected(false);
        chkPeriode.setSelected(false);
        ((TableRowSorter) parent.tabel.getRowSorter()).setRowFilter(null);
    }

    private void checkCheckBoxes() {
        if (!chkAngkatan.isSelected() && !chkNama.isSelected()
                && !chkPeriode.isSelected()) {
            btnClearActionPerformed(null);
        }
    }
}
