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
public class BaseHandler implements Handler{
    
    private Handler next;

    @Override
    public void setNext(Handler h) {
        next = h;
    }

    @Override
    public void handle(AnchorPane ap, String s) {
        if(next != null) next.handle(ap, s);
    }
    
}
