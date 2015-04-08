/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hermes.events;

import hermes.gui.ChannelPane;

/**
 *
 * @author joaquin
 */
public interface ChannelPaneEvents 
{
    public void onTextRecived(ChannelPane source);
    
    public void onNameChange(ChannelPane source,String newName);    
    
}