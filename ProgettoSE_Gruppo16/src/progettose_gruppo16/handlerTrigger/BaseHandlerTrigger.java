/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.handlerTrigger.HandlerTrigger;
import progettose_gruppo16.trigger.Trigger;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progettose_gruppo16.trigger.Trigger;

/**
 *
 * @author raffa
 */
public class BaseHandlerTrigger implements HandlerTrigger{
    
    private HandlerTrigger next;

    @Override
    public void setNext(HandlerTrigger h) {
        next = h;
    }

    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {
        if(next != null) next.handleGUI(ap, cb);
    }

    @Override
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox vbox) {
        if(next != null) return next.handleBehaviour(ap, ht, x, vbox);
        return null;
    }
    
}
