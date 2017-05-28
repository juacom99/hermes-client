/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes;

import com.hermes.client.HCChannel;
import com.hermes.common.HChannel;
import com.hermes.common.HHash;
import com.hermes.gui.HSplashScreen;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import java.awt.SplashScreen;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                   HSplashScreen ss=new HSplashScreen();                   
                } 
                catch (Exception ex)
                {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
