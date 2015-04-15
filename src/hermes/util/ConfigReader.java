/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hermes.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author jomartinez
 */


public class ConfigReader
{
    private Properties config;
    
    private static ConfigReader instance;

    public ConfigReader(File configFile) throws IOException
    {
        config=new Properties();
        config.load(new FileReader(configFile));
    }

    public static ConfigReader getInstance() throws IOException
    {
        if(instance==null)
        {
            instance=new ConfigReader(new File(System.getProperty("user.home")+"hermes.conf"));
        }
        return instance;
    }
    
    
    
    
}
