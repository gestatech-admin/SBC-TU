/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.sbc.ui;

import at.ac.tuwien.sbc.Connector;
import at.ac.tuwien.sbc.actor.SupplierActor;
import at.ac.tuwien.sbc.model.ClockPartType;
import java.util.concurrent.ExecutorService;
import javax.swing.JButton;

/**
 *
 * @author Christian
 */
public class MainFrame extends javax.swing.JFrame {

    private final Connector connector;
    private final ExecutorService threadPool;

    private final ClockPartCounter counter;
    private final ClockList clockList;

    private final SupplierTableModel supplierTableModel;
    private final AssembledClocksTableModel assembledTableModel;
    private final CheckedClocksTableModel checkedTableModel;
    private final DeliveredClocksTableModel deliveredTableModel;
    private final DisassembledClocksTableModel disassembledTableModel;
    private final AllClocksTableModel clockTableModel;

    /**
     * Creates new form App
     *
     * @param connector
     * @param threadPool
     */
    public MainFrame(Connector connector, final ExecutorService threadPool) {
        this.connector = connector;
        this.threadPool = threadPool;
        this.counter = new ClockPartCounter();
        this.clockList = new ClockList();
        this.supplierTableModel = new SupplierTableModel();
        this.clockTableModel = new AllClocksTableModel(clockList);    
        this.assembledTableModel = new AssembledClocksTableModel(clockList);
        this.checkedTableModel = new CheckedClocksTableModel(clockList);
        this.deliveredTableModel = new DeliveredClocksTableModel(clockList);
        this.disassembledTableModel = new DisassembledClocksTableModel(clockList);
        initComponents();

        // Add cell renderer for supplier table
        new ButtonColumn(supplierTable, new ButtonColumn.Listener() {

            @Override
            public void buttonClicked(final JButton editButton, final JButton renderButton, final int row) {
                supplierTableModel.setValueAt("Running...", row, 3);
                // We need a repaint because the cell renderer will change appearance
                supplierTable.repaint();

                threadPool.submit(new ActorRunner(supplierTableModel.get(row), new Runnable() {

                    @Override
                    public void run() {
                        supplierTableModel.setValueAt("Start", row, 3);
                        // We need a repaint because the cell renderer will change appearance
                        supplierTable.repaint();
                    }
                }));
            }
        }, 3);

        CountingClockPartListener clockPartListener = new CountingClockPartListener(counter, new Runnable() {

            @Override
            public void run() {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        gehaeuseValue.setText(counter.getChassisCounter()
                            .toString());
                        uhrwerkeValue.setText(counter.getClockWorkCounter()
                            .toString());
                        zeigerValue.setText(counter.getClockHandCounter()
                            .toString());
                        armbaenderValue.setText(counter.getWristbandCounter()
                            .toString());
                    }
                });
            }
        });
        // Subscribe before retrieving data or else we might miss notifications
        connector.subscribeForClockParts(clockPartListener);
        clockPartListener.setCurrentClockParts(connector.getClockParts());

        final CollectingClockListener clockListener = new CollectingClockListener(clockList, new Runnable() {

            @Override
            public void run() {
//                clockTableModel.fireTableDataChanged();
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        assembledValue.setText(String.valueOf(
                                clockList.getAssembledClocks().size()));
                        checkedValue.setText(String.valueOf(
                                clockList.getCheckedClocks().size()));
                        deliveredValue.setText(String.valueOf(
                                clockList.getDeliveredClocks().size()));
                        disassembledValue.setText(String.valueOf(
                                clockList.getDisassembledClocks().size()));
                        clockTableModel.fireTableDataChanged();
                        assembledTableModel.fireTableDataChanged();
                        checkedTableModel.fireTableDataChanged();
                        deliveredTableModel.fireTableDataChanged();
                        disassembledTableModel.fireTableDataChanged();
                    }
                });
            }
        });
        // Subscribe before retrieving data or else we might miss notifications
        connector.subscribeForClocks(clockListener);
        clockListener.onClocksUpdated(connector.getClocks());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        statusPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        gehaeuseValue = new javax.swing.JLabel();
        uhrwerkeValue = new javax.swing.JLabel();
        zeigerValue = new javax.swing.JLabel();
        armbaenderValue = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        clockTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        clockTable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        clockTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        clockTable3 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        clockTable4 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        assembledValue = new javax.swing.JLabel();
        checkedValue = new javax.swing.JLabel();
        disassembledValue = new javax.swing.JLabel();
        deliveredValue = new javax.swing.JLabel();
        supplierPanel1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        supplierTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        statusPanel1.setLayout(new javax.swing.BoxLayout(statusPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel3.setMaximumSize(new java.awt.Dimension(1000, 116));

        jLabel1.setText("Bestandteile");

        jLabel4.setText("Gehäuse");

        jLabel5.setText("Uhrwerke");

        jLabel6.setText("Zeiger");

        jLabel7.setText("Armbänder");

        gehaeuseValue.setText("0");

        uhrwerkeValue.setText("0");

        zeigerValue.setText("0");

        armbaenderValue.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(uhrwerkeValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(gehaeuseValue, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(zeigerValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(armbaenderValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(822, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(gehaeuseValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(uhrwerkeValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(zeigerValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(armbaenderValue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        clockTable1.setModel(clockTableModel);
        jScrollPane3.setViewportView(clockTable1);

        jTabbedPane2.addTab("Alle Uhren", jScrollPane3);

        clockTable.setModel(assembledTableModel);
        jScrollPane2.setViewportView(clockTable);

        jLabel8.setText("Zusammengebaute Uhren:");

        clockTable2.setModel(checkedTableModel);
        jScrollPane4.setViewportView(clockTable2);

        jLabel9.setText("Überprüfte Uhren:");

        clockTable3.setModel(deliveredTableModel);
        jScrollPane5.setViewportView(clockTable3);

        jLabel10.setText("Gelieferte Uhren:");

        clockTable4.setModel(disassembledTableModel);
        jScrollPane6.setViewportView(clockTable4);

        jLabel11.setText("Zerlegte Uhren:");

        assembledValue.setText("0");

        checkedValue.setText("0");

        disassembledValue.setText("0");

        deliveredValue.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(assembledValue)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(checkedValue))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(deliveredValue)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(disassembledValue))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(326, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(checkedValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(assembledValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(disassembledValue)
                    .addComponent(deliveredValue))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Uhren nach Status", jPanel5);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane2))
        );

        statusPanel1.add(jPanel4);

        jTabbedPane1.addTab("Status", statusPanel1);

        supplierPanel1.setLayout(new javax.swing.BoxLayout(supplierPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setMaximumSize(new java.awt.Dimension(1671, 120));
        jPanel1.setPreferredSize(new java.awt.Dimension(1671, 100));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gehäuse", "Uhrwerk", "Zeiger", "Armband" }));

        jLabel2.setText("Anzahl");

        jTextField1.setText("0");

        jButton1.setText("Anlegen");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });

        jLabel3.setText("Bestandteil");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
                    .addComponent(jButton1))
                .addContainerGap(1399, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        supplierPanel1.add(jPanel1);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        supplierTable.setModel(supplierTableModel);
        jScrollPane1.setViewportView(supplierTable);

        jPanel2.add(jScrollPane1);

        supplierPanel1.add(jPanel2);

        jTabbedPane1.addTab("Lieferanten", supplierPanel1);

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        ClockPartType type = null;
        switch (jComboBox1.getSelectedIndex()) {
            case 0:
                type = ClockPartType.GEHAEUSE;
                break;
            case 1:
                type = ClockPartType.UHRWERK;
                break;
            case 2:
                type = ClockPartType.ZEIGER;
                break;
            case 3:
                type = ClockPartType.ARMBAND;
                break;
        }
        int amount;

        try {
            amount = Integer.parseInt(jTextField1.getText());
        } catch (NumberFormatException e) {
            return;
        }

        if (amount > 0) {
            supplierTableModel.addSupplier(new SupplierActor(connector, type, amount));
        }
    }//GEN-LAST:event_jButton1MouseReleased

    public static void start(final Connector connector, final ExecutorService threadPool) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame(connector, threadPool).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel armbaenderValue;
    private javax.swing.JLabel assembledValue;
    private javax.swing.JLabel checkedValue;
    private javax.swing.JTable clockTable;
    private javax.swing.JTable clockTable1;
    private javax.swing.JTable clockTable2;
    private javax.swing.JTable clockTable3;
    private javax.swing.JTable clockTable4;
    private javax.swing.JLabel deliveredValue;
    private javax.swing.JLabel disassembledValue;
    private javax.swing.JLabel gehaeuseValue;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel statusPanel1;
    private javax.swing.JPanel supplierPanel1;
    private javax.swing.JTable supplierTable;
    private javax.swing.JLabel uhrwerkeValue;
    private javax.swing.JLabel zeigerValue;
    // End of variables declaration//GEN-END:variables
}
