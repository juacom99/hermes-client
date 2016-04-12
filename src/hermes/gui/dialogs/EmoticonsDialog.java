/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hermes.gui.dialogs;

import com.hermes.common.EmoticonManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

/**
 *
 * @author jomartinez
 */
public class EmoticonsDialog extends javax.swing.JDialog
{

    /**
     * Creates new form EmoticonsDialog
     */
    private String selected = null;

    public EmoticonsDialog(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        setSize(152, 114);
        JButton emoticon;
        Iterator<String> i = EmoticonManager.getInstance().getAll();
        String key, value;

        ActionListener al = new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                selected = ((JButton) e.getSource()).getName().replaceAll("\\\\|\\?|-", ""); //.replaceAll("\\?","").replaceAll("\\\\\\)",")").replaceAll("\\\\\\(","(");
                dispose();
            }
        };

        while (i.hasNext())
        {
            key = i.next();
            value = EmoticonManager.getInstance().get(key);
            emoticon = new JButton(new ImageIcon("./emoticons/" + value));
            emoticon.setBorderPainted(false);
            emoticon.setContentAreaFilled(false);
            emoticon.setName(key);
            emoticon.addActionListener(al);
            add(emoticon);
        }

        ActionListener actionListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                dispose();
               // setVisible(false);
            }
        };

        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        this.getRootPane().registerKeyboardAction(actionListener, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

    }

    public String getSelected()
    {
        return selected;
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

        jLabel7 = new javax.swing.JLabel();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(0, 8));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
