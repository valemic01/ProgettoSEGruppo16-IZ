/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.trigger.Trigger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progettose_gruppo16.Handler;

/**
 *
 * @author raffa
 */
public interface HandlerTrigger extends Handler{
    
    public void setNext(HandlerTrigger h);
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox vbox);
    
}
