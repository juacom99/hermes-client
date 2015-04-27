/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hermes.gui;

import hermes.gui.dialogs.ConfigDialog;
import com.hermes.client.HCUser;
import hermes.events.ChannelListClickedEvent;
import com.hermes.common.HChannel;
import com.hermes.common.HHash;
import hermes.events.ChannelPaneEvents;
import hermes.gui.dialogs.HashDialog;
import hermes.util.ConfigReader;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author joaquin
 */
public class HermesClient extends javax.swing.JFrame
{

    /**
     * Creates new form NewJFrame
     */
    private HCUser user;
    private JButton bNewTab;

    public HermesClient() throws UnknownHostException, IOException, NoSuchAlgorithmException, DataFormatException
    {
        initComponents();

        ListPane lp = new ListPane(new ChannelListClickedEvent()
        {

            @Override
            public void channelListClick(HChannel channel)
            {
                try
                {
                    addChannel(channel);
                } catch (Exception ex)
                {
                    Logger.getLogger(HermesClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        user = ConfigReader.getInstance().getUser();
        
        TPChat.add(lp);
        int index = TPChat.indexOfComponent(lp);
        TPChat.setTabComponentAt(index, getTitlePanel(lp, "Channel List", new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/channel-list.png")), null, 3));

        JPanel newTab = new JPanel();
        bNewTab = new JButton(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/newConnection.png")));
        bNewTab.setBorderPainted(false);
        bNewTab.setFocusPainted(false);
        bNewTab.setContentAreaFilled(false);
        bNewTab.setRolloverEnabled(true);
        bNewTab.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/newConnection-over.png")));
        bNewTab.setSize(24, 16);
               
        Action action = new AbstractAction("OpenHashDialog")
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                BNewTabActionPerformed();
            }

        };
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control N"));
        bNewTab.getActionMap().put("OpenHashDialog", action);
        bNewTab.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) action.getValue(Action.ACCELERATOR_KEY), "OpenHashDialog");
        bNewTab.addActionListener(action);

        TPChat.add(newTab);
        index = TPChat.indexOfComponent(newTab);
        TPChat.setTabComponentAt(index, getTitlePanel(newTab, null, null, bNewTab, 0));

        TPChat.setEnabledAt(index, false);

        Container glassPane = (Container) this.getGlassPane();
        glassPane.setBackground(Color.red);

        glassPane.setVisible(true);

        FlowLayout fl = new FlowLayout(FlowLayout.RIGHT);
        fl.setVgap(2);
        glassPane.setLayout(fl);

        glassPane.add(BConfig);
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

        BConfig = new javax.swing.JButton();
        TPChat = new javax.swing.JTabbedPane();

        BConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/config.png"))); // NOI18N
        BConfig.setBorderPainted(false);
        BConfig.setContentAreaFilled(false);
        BConfig.setMaximumSize(new java.awt.Dimension(20, 20));
        BConfig.setMinimumSize(new java.awt.Dimension(20, 20));
        BConfig.setPreferredSize(new java.awt.Dimension(20, 20));
        BConfig.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BConfigActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hermes-Client");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/icon.png")).getImage());

        TPChat.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                TPChatStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(TPChat, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TPChat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BNewTabActionPerformed()
    {
        HashDialog hd=new HashDialog(this,true,"arlnk://CHATROOM:127.0.0.1:14884|UYM");
        hd.setLocation((int)bNewTab.getLocationOnScreen().getX()-7,(int)bNewTab.getLocationOnScreen().getY()+14);
        hd.setVisible(true);
        if(hd.getCloseOption()==HashDialog.ACEPT_OPTION)
        {
            if(hd.getChannel()!=null)
            {
                try
                {
                    addChannel(hd.getChannel());
                }
                catch (Exception ex)
                {
                    Logger.getLogger(HermesClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void TPChatStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_TPChatStateChanged
    {//GEN-HEADEREND:event_TPChatStateChanged

        if ((TPChat.getSelectedIndex() != 0) && TPChat.getSelectedIndex() != (TPChat.getTabCount() - 1))
        {
            JPanel p = ((JPanel) TPChat.getTabComponentAt(TPChat.getSelectedIndex()));

            if (p != null)
            {
                ((JLabel) p.getComponent(0)).setIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/chat.png")));
            }
        }
    }//GEN-LAST:event_TPChatStateChanged

    private void BConfigActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BConfigActionPerformed
    {//GEN-HEADEREND:event_BConfigActionPerformed
        ConfigDialog cf=new ConfigDialog(user,this, true);
        
        cf.setLocation(BConfig.getLocationOnScreen().x-cf.getWidth()-5,BConfig.getLocationOnScreen().y+10);
        cf.setVisible(true);
        if(cf.getOption()==JOptionPane.YES_OPTION)
        {
            this.user=cf.getUser();
            
            for(int i=1;i<TPChat.getTabCount()-1;i++)
            {
                ((Panel)TPChat.getComponentAt(i)).update(user);
            }
        }
        
        
    }//GEN-LAST:event_BConfigActionPerformed

    private JPanel getTitlePanel(final JPanel panel, String title, ImageIcon icon, JButton button, int margin)
    {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, margin, 0));
        titlePanel.setOpaque(false);
        Dimension d;
        if (icon != null)
        {
            JLabel LIcon = new JLabel(icon);
            d = new Dimension(9, 8);
             LIcon.setSize(d);
             LIcon.setPreferredSize(d);
            titlePanel.add(LIcon);
        }

        if (title != null)
        {
            JLabel titleLbl = new JLabel(title);
            titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            titlePanel.add(titleLbl);
        }

        if (button != null)
        {
            d = new Dimension(8, 8);
            button.setSize(d);
            button.setPreferredSize(d);
            titlePanel.add(button);
        }

        return titlePanel;
    }

    private void addChannel(HChannel channel) throws Exception
    {
        ChannelPaneEvents events = new ChannelPaneEvents()
        {

            @Override
            public void onTextRecived(Panel source)
            {
                int index = TPChat.indexOfComponent(source);
                if (index != -1 && TPChat.getSelectedIndex() != index)
                {
                    JPanel p = ((JPanel) TPChat.getTabComponentAt(index));

                    ((JLabel) p.getComponent(0)).setIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/chat-new.png")));                    
                }
            }

            @Override
            public void onNameChange(Panel source, String newName)
            {
                int index = TPChat.indexOfComponent(source);
                if (index != -1)
                {
                    JPanel p = ((JPanel) TPChat.getTabComponentAt(index));

                    ((JLabel) p.getComponent(1)).setText(newName);
                    p.repaint();
                    TPChat.repaint();
                }
            }
        };

        final Panel cp = new Panel(user, channel, events);
        JButton closeButton = new JButton(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/close.png")));
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setRolloverEnabled(true);
        closeButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/close-over.png")));
        Dimension d = new Dimension(8, 8);
        closeButton.setSize(d);
        closeButton.setPreferredSize(d);
        closeButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int index = TPChat.getSelectedIndex() - 1;
                TPChat.remove(cp);
                ((Panel) cp).close();
                TPChat.setSelectedIndex(index);

            }
        });

        Action action = new AbstractAction("closeTab")
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                int index = TPChat.getSelectedIndex() - 1;
                TPChat.remove(cp);
                ((Panel) cp).close();
                TPChat.setSelectedIndex(index);
            }

        };
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control W"));
        closeButton.getActionMap().put("closeTab", action);
        closeButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) action.getValue(Action.ACCELERATOR_KEY), "closeTab");
        closeButton.addActionListener(action);

        TPChat.add(cp, TPChat.getTabCount() - 1);
        int index = TPChat.indexOfComponent(cp);
        TPChat.setTabComponentAt(index, getTitlePanel(cp, channel.getName(), new javax.swing.ImageIcon(getClass().getResource("/hermes/resources/images/chat.png")), closeButton, 6));
        TPChat.setSelectedIndex(TPChat.getTabCount() - 2);

        cp.connect();

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BConfig;
    private javax.swing.JTabbedPane TPChat;
    // End of variables declaration//GEN-END:variables
}
