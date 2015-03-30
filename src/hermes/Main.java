/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hermes;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import hermes.gui.HermesClient;
import java.util.logging.Level;
import java.util.logging.Logger;
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
