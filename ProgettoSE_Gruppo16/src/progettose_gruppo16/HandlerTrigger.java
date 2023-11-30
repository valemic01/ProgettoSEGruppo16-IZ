/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package progettose_gruppo16;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author raffa
 */
public interface HandlerTrigger extends Handler{
    
    public void setNext(HandlerTrigger h);
    public Trigger handleBehaviour(AnchorPane ap);
    
}
