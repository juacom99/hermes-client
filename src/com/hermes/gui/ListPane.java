/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.gui;

import com.hermes.gui.renderers.TopicRenderer;
import com.hermes.client.HCChannelDownloader;
import com.hermes.events.ChannelListClickedEvent;
import com.hermes.client.events.HChannelListEvents;

import com.hermes.client.events.HClientEvent;
import com.hermes.common.HChannel;
import com.hermes.common.HHash;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jomartinez
 */
public class ListPane extends javax.swing.JPanel
{

    /**
     * Creates new form List
     */
    private HCChannelDownloader downloader;
    private TableRowSorter<DefaultTableModel> sorter;
    private HChannel selectedChannel;

    public ListPane(final ChannelListClickedEvent evt) throws IOException
    {
        initComponents();
        sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) TChannels.getModel()));
        TChannels.setRowSorter(sorter);
        TChannels.putClientProperty("terminateEditOnFocusLost", true);
        TFFilter.getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent e)
            {
                RowFilter<DefaultTableModel, Object> rf = null;
                //declare a row filter for your table model
                try
                {
                    rf = RowFilter.regexFilter("(?i)" + TFFilter.getText(), 1, 5);

                }
                catch (java.util.regex.PatternSyntaxException ex)
                {
                    ex.printStackTrace();
                    return;
                }
                sorter.setRowFilter(rf);
                //TChannels.repaint();
            }

            public void insertUpdate(DocumentEvent e)
            {
                RowFilter<DefaultTableModel, Object> rf = null;
                try
                {
                    rf = RowFilter.regexFilter("(?i)" + TFFilter.getText(), 1, 5);
                }
                catch (java.util.regex.PatternSyntaxException ex)
                {
                    ex.printStackTrace();
                    return;
                }
                sorter.setRowFilter(rf);
               // TChannels.repaint();
            }

            public void removeUpdate(DocumentEvent e)
            {
                if (TFFilter.getText().length() == 0)
                {
                    sorter.setRowFilter(null);
                }
                else
                {
                    RowFilter<DefaultTableModel, Object> rf = null;
                    try
                    {
                        rf = RowFilter.regexFilter("(?i)" + TFFilter.getText(), 1, 5);
                    }
                    catch (java.util.regex.PatternSyntaxException ex)
                    {
                        ex.printStackTrace();
                        return;
                    }
                    sorter.setRowFilter(rf);
                   // TChannels.repaint();
                }
            }
        });
        TChannels.addMouseListener(new MouseAdapter()
        {
            
            
            @Override
            public void mousePressed(MouseEvent e)
            {
                int row = TChannels.rowAtPoint(e.getPoint());
                selectedChannel = downloader.get((int) TChannels.getValueAt(row, 0));
                if (e.getClickCount() % 2 == 0 && e.getButton() == MouseEvent.BUTTON1)
                {
                    evt.channelListClick(selectedChannel);
                }
                
                else if(e.getButton()==MouseEvent.BUTTON3)
                {
                    TChannels.setRowSelectionInterval(row,row);
                    PMMenu.show(TChannels,e.getX(),e.getY());
                }
            }
        });
        TFFilter.requestFocus();
        downloader = new HCChannelDownloader(new File("ChatroomIPs.dat"));
        downloader.addEventListener(new HChannelListEvents()
        {

            @Override
            public void onNewChannel(HChannel channel, int index,int count)
            {
                Object[] row =
                {
                    index, channel.getName(), channel.getTopic(), channel.getLanguage(), channel.getUserCount()
                };

                ((DefaultTableModel) TChannels.getModel()).addRow(row);
                
                evt.channelDownloaded(count);
            }

            @Override
            public void onDownloadStart(HClientEvent evt)
            {
                BRefresh.setEnabled(false);
            }

            @Override
            public void onDownloadFinish(HClientEvent evt)
            {
                BRefresh.setEnabled(true);
            }

        });
        downloader.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BUpdate = new javax.swing.JButton();
        PMMenu = new javax.swing.JPopupMenu();
        MIExport = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        TChannels = new javax.swing.JTable();
        TFFilter = new javax.swing.JTextField();
        LFilter = new javax.swing.JLabel();
        BRefresh = new javax.swing.JButton();

        BUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hermes/resources/images/update.png"))); // NOI18N
        BUpdate.setBorderPainted(false);
        BUpdate.setContentAreaFilled(false);
        BUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BUpdate.setMaximumSize(new java.awt.Dimension(24, 19));
        BUpdate.setMinimumSize(new java.awt.Dimension(24, 19));
        BUpdate.setPreferredSize(new java.awt.Dimension(24, 19));
        BUpdate.setRequestFocusEnabled(false);
        BUpdate.setRolloverEnabled(false);

        MIExport.setText("Export Hash");
        MIExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MIExportActionPerformed(evt);
            }
        });
        PMMenu.add(MIExport);

        TChannels.setAutoCreateRowSorter(true);
        TChannels.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        TChannels.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Topic", "Language", "Users"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TChannels.setRowHeight(25);
        //center Usercount and Language Colums
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );

        TChannels.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        TChannels.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        jScrollPane1.setViewportView(TChannels);
        if (TChannels.getColumnModel().getColumnCount() > 0) {
            TChannels.getColumnModel().getColumn(0).setMinWidth(0);
            TChannels.getColumnModel().getColumn(0).setPreferredWidth(0);
            TChannels.getColumnModel().getColumn(0).setMaxWidth(0);
            TChannels.getColumnModel().getColumn(1).setMinWidth(260);
            TChannels.getColumnModel().getColumn(1).setPreferredWidth(260);
            TChannels.getColumnModel().getColumn(1).setMaxWidth(260);
            TChannels.getColumnModel().getColumn(2).setCellRenderer(new TopicRenderer());
            TChannels.getColumnModel().getColumn(3).setMinWidth(90);
            TChannels.getColumnModel().getColumn(3).setPreferredWidth(90);
            TChannels.getColumnModel().getColumn(3).setMaxWidth(90);
            TChannels.getColumnModel().getColumn(4).setMinWidth(65);
            TChannels.getColumnModel().getColumn(4).setPreferredWidth(65);
            TChannels.getColumnModel().getColumn(4).setMaxWidth(65);
        }

        TFFilter.setMaximumSize(new java.awt.Dimension(340, 24));
        TFFilter.setMinimumSize(new java.awt.Dimension(340, 24));
        TFFilter.setPreferredSize(new java.awt.Dimension(340, 24));

        LFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hermes/resources/images/filter.png"))); // NOI18N

        BRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hermes/resources/images/update.png"))); // NOI18N
        BRefresh.setBorderPainted(false);
        BRefresh.setContentAreaFilled(false);
        BRefresh.setFocusPainted(false);
        BRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TFFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFFilter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BRefreshActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BRefreshActionPerformed
    {//GEN-HEADEREND:event_BRefreshActionPerformed
        downloader.restart();
        ((DefaultTableModel) TChannels.getModel()).setRowCount(0);
        downloader.start();
    }//GEN-LAST:event_BRefreshActionPerformed

    private void MIExportActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_MIExportActionPerformed
    {//GEN-HEADEREND:event_MIExportActionPerformed
        StringSelection stringSelection = null;
        try
        {
            stringSelection = new StringSelection(selectedChannel.getHash());
        }
        catch (UnknownHostException ex)
        {
            Logger.getLogger(ListPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(ListPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }//GEN-LAST:event_MIExportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BRefresh;
    private javax.swing.JButton BUpdate;
    private javax.swing.JLabel LFilter;
    private javax.swing.JMenuItem MIExport;
    private javax.swing.JPopupMenu PMMenu;
    private javax.swing.JTable TChannels;
    private javax.swing.JTextField TFFilter;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
