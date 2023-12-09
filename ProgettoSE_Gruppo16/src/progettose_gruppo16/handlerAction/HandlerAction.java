package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.Action;
import javafx.scene.layout.AnchorPane;
import progettose_gruppo16.Handler;

/**
 * Base interface for using the Chain of Responsibility pattern.
 * @author raffa
 */
public interface HandlerAction extends Handler{
    
    public void setNext(HandlerAction h);
    public Action handleBehaviour(AnchorPane ap);
    
}
