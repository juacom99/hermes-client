/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.events;

import com.hermes.common.HChannel;
import com.hermes.gui.Panel;

/**
 *
 * @author joaquin
 */
public interface ChannelPaneEvents 
{
    public void onTextRecived(Panel source);
    
    public void onNameChange(Panel source,String newName);

    public void onUrlClicked(Panel source, HChannel ch);
            
    
}