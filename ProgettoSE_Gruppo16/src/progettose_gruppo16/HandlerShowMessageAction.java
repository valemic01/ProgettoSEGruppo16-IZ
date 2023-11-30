/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author valentina <your.name at your.org>
 */
public class HandlerShowMessageAction extends BaseHandlerAction{
    private TextField messTxtBox = new TextField();
            
    @Override
    public void handleGUI(AnchorPane ap, String s){ 
        if(s.equals("Show message")){
            ap.getChildren().clear();
            ap.setId("ShowMessagePane");
           
            messTxtBox.setPromptText("Your text...");
            ap.getChildren().add(messTxtBox);
            messTxtBox.setLayoutX(50);
            messTxtBox.setLayoutY(42);
        }else{
            super.handleGUI(ap, s);
        }      
    }
    
    @Override 
    public Action handleBehaviour(AnchorPane ap){
        if(ap.getId().equals("ShowMessagePane")){        
            return new ShowMessageAction(messTxtBox.getText());
        }
        else{
            return super.handleBehaviour(ap);
        }
    }
}
