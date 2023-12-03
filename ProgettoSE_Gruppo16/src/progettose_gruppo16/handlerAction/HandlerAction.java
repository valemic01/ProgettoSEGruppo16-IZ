/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.Action;
import javafx.scene.layout.AnchorPane;
import progettose_gruppo16.Handler;

/**
 *
 * @author raffa
 */
public interface HandlerAction extends Handler{
    
    public void setNext(HandlerAction h);
    public Action handleBehaviour(AnchorPane ap);
    
}
