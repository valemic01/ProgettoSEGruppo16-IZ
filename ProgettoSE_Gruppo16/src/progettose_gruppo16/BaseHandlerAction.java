/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author raffa
 */
public class BaseHandlerAction implements HandlerAction{
    
    private HandlerAction next;
    
    @Override
    public void setNext(HandlerAction h) {
        next = h;
    }

    @Override
    public void handleGUI(AnchorPane ap, String s) {
        if(next != null) next.handleGUI(ap, s);
    }

    @Override
    public Action handleBehaviour(AnchorPane ap) {
        if(next != null) next.handleBehaviour(ap);
        return null;
    }
    
}
