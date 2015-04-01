/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hermes.gui;

import com.hermes.client.HCUser;
import com.hermes.client.HClient;
import com.hermes.client.events.HClientAckEvent;
import com.hermes.client.events.HClientAvatarEvent;
import com.hermes.client.events.HClientEmoteEvent;
import com.hermes.client.events.HClientEvent;
import com.hermes.client.events.HClientJoinEvent;
import com.hermes.client.events.HClientMessageEvent;
import com.hermes.client.events.HClientNoSuchEvent;
import com.hermes.client.events.HClientPartEvent;
import com.hermes.client.events.HClientPersonalMessageEvent;
import com.hermes.client.events.HClientTopicEvent;
import com.hermes.client.events.HClientUrlEvent;
import com.hermes.client.events.HClientUserListevent;
import com.hermes.client.events.HClientUserUpdateEvent;
import com.hermes.client.events.HIClientEvents;
import com.hermes.common.AresFormater;
import com.hermes.common.HChannel;
import com.hermes.common.HUser;
import hermes.events.ChannelPanEvents;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.text.BadLocationException;

/**
 *
 * @author jomartinez
 */
public class ChannelPane extends javax.swing.JPanel implements HIClientEvents
{

    /**
     * Creates new form Channel
     */
    private HClient client;
    private MainChatPane main;
    private String url;
    private HashMap<String, ChatPane> privates;
    private ChannelPanEvents event;

    public ChannelPane(HCUser user, HChannel channel,ChannelPanEvents event) throws IOException, Exception
    {
        initComponents();
        
        this.event=event;
        
        jToolBar1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        this.main = new MainChatPane();
        TPTabs.add("Main Chat", main);

        privates = new HashMap<String, ChatPane>();

        client = new HClient(user);
        client.addClientEventListener(this);
        main.write(AresFormater.FOREGROUND_CHARACTER + "02Connecting to host, please wait...");

        if (channel.getTopic() != null)
        {
            LTopic.setText(AresFormater.getInstance().toHTML(channel.getTopic()));
        }

        TFInput.requestFocus();

        client.connect(channel.getPublicIP(), channel.getPort());

        main.addMouseClickEvent(new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent evt)
            {
                JList<HUser> list = (JList) evt.getSource();
                if (evt.getClickCount() == 2)
                {
                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());

                    HUser u = list.getModel().getElementAt(index);

                    try
                    {
                        addPrivate(u.getUsername());
                    }
                    catch (Exception ex)
                    {
                        Logger.getLogger(ChannelPane.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        });

    }

    private JPanel getTitlePanel(final JTabbedPane tabbedPane, final JPanel panel, String title)
    {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
        titlePanel.setOpaque(false);

        JLabel titleLbl = new JLabel(title);
        titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        titlePanel.add(titleLbl);

        JButton closeButton = new JButton(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/close.png")));
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setRolloverEnabled(true);
        closeButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/close-over.png")));
        Dimension d = new Dimension(16, 16);
        closeButton.setSize(d);
        closeButton.setPreferredSize(d);
        closeButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                tabbedPane.remove(panel);
            }
        });
        titlePanel.add(closeButton);

        return titlePanel;
    }

    private void addPrivate(String userName) throws Exception
    {
        ChatPane cp;

        if (!privates.containsKey(userName))
        {
            cp = new ChatPane();
            TPTabs.add(cp, userName);
            privates.put(userName, cp);
            int index = TPTabs.indexOfComponent(cp);
            TPTabs.setTabComponentAt(index, getTitlePanel(TPTabs, cp, userName));
        }
        else
        {
            cp = privates.get(userName);

            if (TPTabs.indexOfComponent(cp) == -1)
            {
                TPTabs.add(cp, userName);
                int index = TPTabs.indexOfComponent(cp);
                TPTabs.setTabComponentAt(index, getTitlePanel(TPTabs, cp, userName));
            }
        }

        TPTabs.setSelectedIndex(TPTabs.getTabCount() - 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        LTopic = new javax.swing.JLabel();
        TFInput = new javax.swing.JTextField();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jToolBar1 = new javax.swing.JToolBar();
        BBold = new javax.swing.JButton();
        BItalic = new javax.swing.JButton();
        BUnderline = new javax.swing.JButton();
        BForeground = new javax.swing.JButton();
        BBackground = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 20), new java.awt.Dimension(30, 20), new java.awt.Dimension(30, 32767));
        BEmoticons = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        LURL = new javax.swing.JLabel();
        TPTabs = new javax.swing.JTabbedPane();

        jList1.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setToolTipText("");

        LTopic.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        LTopic.setText("TOPIC");

        TFInput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TFInput.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                TFInputKeyPressed(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setAlignmentY(0.5F);
        jToolBar1.setMinimumSize(new java.awt.Dimension(10, 31));
        jToolBar1.setPreferredSize(new java.awt.Dimension(148, 22));

        BBold.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BBold.setText("<html><b>B</b></html>");
        BBold.setToolTipText("");
        BBold.setActionCommand("");
        BBold.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BBold.setMaximumSize(new java.awt.Dimension(20, 20));
        BBold.setMinimumSize(new java.awt.Dimension(20, 20));
        BBold.setPreferredSize(new java.awt.Dimension(20, 20));
        BBold.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BBoldActionPerformed(evt);
            }
        });
        jToolBar1.add(BBold);

        BItalic.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BItalic.setText("<html><i>I</i></html>");
        BItalic.setToolTipText("");
        BItalic.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BItalic.setMaximumSize(new java.awt.Dimension(20, 20));
        BItalic.setMinimumSize(new java.awt.Dimension(20, 20));
        BItalic.setPreferredSize(new java.awt.Dimension(20, 20));
        BItalic.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BItalicActionPerformed(evt);
            }
        });
        jToolBar1.add(BItalic);

        BUnderline.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BUnderline.setText("<html><u>U</U></html>");
        BUnderline.setToolTipText("");
        BUnderline.setMaximumSize(new java.awt.Dimension(20, 20));
        BUnderline.setMinimumSize(new java.awt.Dimension(20, 20));
        BUnderline.setPreferredSize(new java.awt.Dimension(20, 20));
        BUnderline.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BUnderlineActionPerformed(evt);
            }
        });
        jToolBar1.add(BUnderline);

        BForeground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/fg.png"))); // NOI18N
        BForeground.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BForeground.setMaximumSize(new java.awt.Dimension(20, 20));
        BForeground.setMinimumSize(new java.awt.Dimension(20, 20));
        BForeground.setPreferredSize(new java.awt.Dimension(20, 20));
        BForeground.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BForegroundActionPerformed(evt);
            }
        });
        jToolBar1.add(BForeground);

        BBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/bg.png"))); // NOI18N
        BBackground.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BBackground.setMaximumSize(new java.awt.Dimension(20, 20));
        BBackground.setMinimumSize(new java.awt.Dimension(20, 20));
        BBackground.setPreferredSize(new java.awt.Dimension(20, 20));
        BBackground.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BBackgroundActionPerformed(evt);
            }
        });
        jToolBar1.add(BBackground);
        jToolBar1.add(filler1);

        BEmoticons.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/emoti.png"))); // NOI18N
        BEmoticons.setToolTipText("");
        BEmoticons.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BEmoticons.setMaximumSize(new java.awt.Dimension(19, 19));
        BEmoticons.setMinimumSize(new java.awt.Dimension(20, 20));
        BEmoticons.setPreferredSize(new java.awt.Dimension(20, 20));
        jToolBar1.add(BEmoticons);
        jToolBar1.add(filler4);

        LURL.setMaximumSize(null);
        LURL.setMinimumSize(new java.awt.Dimension(60, 19));
        LURL.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                LURLMouseClicked(evt);
            }
        });
        jToolBar1.add(LURL);

        TPTabs.setBackground(new java.awt.Color(255, 255, 255));
        TPTabs.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(406, 406, 406)
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TFInput)
                        .addGap(12, 12, 12)))
                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LTopic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(TPTabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(TPTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TFInputKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_TFInputKeyPressed
    {//GEN-HEADEREND:event_TFInputKeyPressed
        if (evt.getKeyCode() == 10)
        {
            String text = TFInput.getText().replace(((char) 2) + "6", "" + AresFormater.BOLD_CHARACTER);
            text = text.replaceAll(((char) 2) + "7", "" + AresFormater.UNDERLINE_CHARACTER);
            text = text.replaceAll(((char) 2) + "9", "" + AresFormater.ITALIC_CHARACTER);
            if (TPTabs.getSelectedIndex() == 0)
            {
                try
                {
                    if (text.startsWith("/me"))
                    {
                        client.sendEmote(text.substring(3));
                    }
                    else if (text.startsWith("/") || text.startsWith("#"))
                    {
                        client.sendCommand(text.substring(1));
                    }
                    else
                    {
                        client.sendMessage(text);
                    }

                }
                catch (IOException ex)
                {
                    Logger.getLogger(ChannelPane.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                String to = TPTabs.getTitleAt(TPTabs.getSelectedIndex());
                client.sendPM(to, text);

                ChatPane cp = ((ChatPane) TPTabs.getComponentAt(TPTabs.getSelectedIndex()));
                cp.write(AresFormater.getInstance().toHTML(AresFormater.BOLD_CHARACTER + "Me:"));
                cp.write(AresFormater.getInstance().toHTML("        " + text));

            }

            TFInput.setText("");
        }
    }//GEN-LAST:event_TFInputKeyPressed

    private void BBoldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BBoldActionPerformed
    {//GEN-HEADEREND:event_BBoldActionPerformed
        try
        {
            TFInput.getDocument().insertString(TFInput.getCaretPosition(), "" + ((char) 2) + ((int) AresFormater.BOLD_CHARACTER), null);
            TFInput.requestFocus();
        }
        catch (BadLocationException ex)
        {
            Logger.getLogger(ChannelPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BBoldActionPerformed

    private void BItalicActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BItalicActionPerformed
    {//GEN-HEADEREND:event_BItalicActionPerformed
        try
        {
            TFInput.getDocument().insertString(TFInput.getCaretPosition(), "" + ((char) 2) + ((int) AresFormater.ITALIC_CHARACTER), null);
            TFInput.requestFocus();
        }
        catch (BadLocationException ex)
        {
            Logger.getLogger(ChannelPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BItalicActionPerformed

    private void BUnderlineActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BUnderlineActionPerformed
    {//GEN-HEADEREND:event_BUnderlineActionPerformed
        try
        {
            TFInput.getDocument().insertString(TFInput.getCaretPosition(), "" + ((char) 2) + ((int) AresFormater.UNDERLINE_CHARACTER), null);
            TFInput.requestFocus();
        }
        catch (BadLocationException ex)
        {
            Logger.getLogger(ChannelPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BUnderlineActionPerformed

    private void LURLMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_LURLMouseClicked
    {//GEN-HEADEREND:event_LURLMouseClicked
        try
        {

            DesktopApi.browse(new URI(this.url));
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(ChannelPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_LURLMouseClicked

    private void BBackgroundActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BBackgroundActionPerformed
    {//GEN-HEADEREND:event_BBackgroundActionPerformed
        ColorDialog c = new ColorDialog(null, false);
        c.setModal(true);
        c.setLocation((int) BForeground.getLocationOnScreen().getX() - 50, (int) BForeground.getLocationOnScreen().getY() - 125);
        JRootPane rootPane = ((JDialog) c).getRootPane();
        rootPane.setWindowDecorationStyle(JRootPane.NONE);
        c.setVisible(true);
        if (c.getColorCode() != null)
        {
            try
            {
                TFInput.getDocument().insertString(TFInput.getCaretPosition(), AresFormater.BACKGROUND_CHARACTER + c.getColorCode(), null);
            }
            catch (BadLocationException ex)
            {
                Logger.getLogger(ChannelPane.class.getName()).log(Level.SEVERE, null, ex);
            }
            TFInput.requestFocus();
        }
    }//GEN-LAST:event_BBackgroundActionPerformed

    private void BForegroundActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BForegroundActionPerformed
    {//GEN-HEADEREND:event_BForegroundActionPerformed
        ColorDialog c = new ColorDialog(null, false);
        c.setModal(true);
        c.setLocation((int) BForeground.getLocationOnScreen().getX() - 50, (int) BForeground.getLocationOnScreen().getY() - 125);
        JRootPane rootPane = ((JDialog) c).getRootPane();
        rootPane.setWindowDecorationStyle(JRootPane.NONE);
        c.setVisible(true);
        if (c.getColorCode() != null)
        {
            try
            {
                TFInput.getDocument().insertString(TFInput.getCaretPosition(), AresFormater.FOREGROUND_CHARACTER + c.getColorCode(), null);
            }
            catch (BadLocationException ex)
            {
                Logger.getLogger(ChannelPane.class.getName()).log(Level.SEVERE, null, ex);
            }
            TFInput.requestFocus();
        }
    }//GEN-LAST:event_BForegroundActionPerformed

    public void close()
    {
        try
        {
            client.disconnect();
        }
        catch (IOException ex)
        {
            Logger.getLogger(ChannelPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBackground;
    private javax.swing.JButton BBold;
    private javax.swing.JButton BEmoticons;
    private javax.swing.JButton BForeground;
    private javax.swing.JButton BItalic;
    private javax.swing.JButton BUnderline;
    private javax.swing.JLabel LTopic;
    private javax.swing.JLabel LURL;
    private javax.swing.JTextField TFInput;
    private javax.swing.JTabbedPane TPTabs;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onPublicMessage(HClientMessageEvent evt)
    {
        main.write(AresFormater.FOREGROUND_CHARACTER + "01" + evt.getSender() + "> " + AresFormater.FOREGROUND_CHARACTER + "12" + evt.getText());
        event.onTextRecived(this);
    }

    @Override
    public void onPrivateMessage(HClientMessageEvent evt)
    {
        try
        {
            addPrivate(evt.getSender());
            ChatPane cp = privates.get(evt.getSender());
            cp.write(AresFormater.getInstance().toHTML(AresFormater.BOLD_CHARACTER + evt.getSender() + ":"));
            cp.write(AresFormater.getInstance().toHTML("        " + evt.getText()));
            event.onTextRecived(this);
        }
        catch (Exception ex)
        {
            Logger.getLogger(ChannelPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onNoSuch(HClientNoSuchEvent evt)
    {
        main.write(AresFormater.FOREGROUND_CHARACTER + "04" + evt.getNoSuch());
        event.onTextRecived(this);
    }

    @Override
    public void onJoin(HClientJoinEvent evt)
    {
        main.addUser(evt.getUser());
        main.write(AresFormater.FOREGROUND_CHARACTER + "03" + evt.getUser() + " has join the channel");
    }

    @Override
    public void onPart(HClientPartEvent evt)
    {

        main.write(AresFormater.FOREGROUND_CHARACTER + "07" + evt.getUser() + " has part the channel");
        main.removeUser(evt.getUser());
    }

    @Override
    public void onPersonalMessage(HClientPersonalMessageEvent evt)
    {
        main.updateusers();
    }

    @Override
    public void onAvatar(HClientAvatarEvent evt)
    {

    }

    @Override
    public void onEmote(HClientEmoteEvent evt)
    {
        main.write(AresFormater.FOREGROUND_CHARACTER + "06* " + evt.getUsername() + " " + evt.getEmote());
    }

    @Override
    public void onURL(HClientUrlEvent evt)
    {
        LURL.setText("<html><a href=\"" + evt.getUrl() + "\">" + evt.getUrlCaption() + "<a/>");
        LURL.setToolTipText(evt.getUrl());
        url = evt.getUrl();
    }

    @Override
    public void onTopic(HClientTopicEvent evt)
    {
        LTopic.setText("<html>" + AresFormater.getInstance().toHTML(evt.getTopic()) + "<html>");
    }

    @Override
    public void onConnect(HClientEvent evt)
    {
        main.write(((char) 3) + "02Connected, starting handshake");        
    }

    @Override
    public void onDisconnect(HClientEvent evt)
    {

        main.write(((char) 3) + "04Disconnected");
    }

    @Override
    public void onUserList(HClientUserListevent evt)
    {
        main.addUser(evt.getUser());
    }

    @Override
    public void onUserUpdate(HClientUserUpdateEvent evt)
    {
        main.updateusers();
    }

    @Override
    public void onServerAck(HClientAckEvent evt)
    {
        main.write(AresFormater.FOREGROUND_CHARACTER + "02Logged in, retrieving user's list...");
        event.onNameChange(this,evt.getChannelName());
    }
}
