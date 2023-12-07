/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerTrigger;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progettose_gruppo16.trigger.CompositeTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 *
 * @author raffa
 */
public class HandlerComposite extends BaseHandlerTrigger{

    
    @Override
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox) {
        if(ap.getId().substring(0, ap.getId().length()-1).equalsIgnoreCase("CompositePane")){
            Trigger t1, t2;
            AnchorPane ap1 = new AnchorPane();
            AnchorPane ap2 = new AnchorPane();
            AnchorPane a = new AnchorPane();
            int n1 = 0, n2 = 0;
            String pane = ap.getId().substring(13);
            String op;
            boolean not;
            switch (pane) {
                case "1":
                    ap1 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(1);
                    ap2 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(2);
                    a = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(7);
                    n1 = 2;
                    n2 = 3;
                    break;
                case "2":
                    ap1 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(3);
                    ap2 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(4);
                    a = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(8);
                    n1 = 4;
                    n2 = 5;
                    break;
                case "3":/*
                    String cbT2 = ((ComboBox<String>) ap.get().get(8)).getChildrenUnmodifiable(0);
                    if (!ap.getParent().getChildrenUnmodifiable().get(8).is){
                        System.out.println("ciao");
                        ap1 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(3);
                        ap2 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(4);
                        a = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(8);
                        n1 = 4;
                        n2 = 5;
                        break;
                    }*/
                    ap1 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(5);
                    ap2 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(6);
                    a = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(9);
                    n1 = 6;
                    n2 = 7;
                    break;
            }
            
            op = ((ComboBox<String>) a.getChildren().get(2)).getValue();
            not = ((CheckBox) notVBox.getChildren().get(x-1)).isSelected();
            t1 = ht.handleBehaviour(ap1, ht, n1, notVBox);
            t2 = ht.handleBehaviour(ap2, ht, n2,notVBox);
            
            if (t1==null && t2==null)
                return null;
            return new CompositeTrigger(t1, t2, not, op);
        }
        else{
            return super.handleBehaviour(ap, ht, x, notVBox);
        }
    }

    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb, Button btn) {
        String x;
        if(cb.getValue().equalsIgnoreCase("Composite")){
            ap.getChildren().clear();
            x = cb.getId().substring(6);
            switch (x) {
                case "1":
                    ap.setId("CompositePane1");
                    break;
                case "2":
                    ap.setId("CompositePane2");
                    break;
                case "3":
                    ap.setId("CompositePane3");
                    break;
            }
        }
        else{
            super.handleGUI(ap, cb, btn);
        }
    }
}
