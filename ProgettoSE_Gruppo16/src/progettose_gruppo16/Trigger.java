/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package progettose_gruppo16;

import java.io.Serializable;

/**
 *
 * @author raffa
 */
public interface Trigger extends Serializable{
    
    public abstract boolean checkCondition();
    
    @Override
    public abstract String toString();
    
}
