/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hermes;

import com.hermes.client.HCChannel;
import com.hermes.common.HChannel;
import com.hermes.common.HHash;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import hermes.gui.HermesClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jomartinez
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException
    {
        
        
        try
        {
            HChannel c=new HCChannel("Uruguay y el Mundo",InetAddress.getByName("127.0.0.1"),14884,InetAddress.getByName("127.0.0.1"),"");
            System.out.println(HHash.getInstance().encode(c));
        } catch (UnknownHostException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
           
                  //Aluminium
            //Fast
            //hifi
             UIManager.setLookAndFeel(new AluminiumLookAndFeel());
       
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    new HermesClient().setVisible(true);
                } catch (Exception ex)
                {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
