/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.ShowMessageAction;
import progettose_gruppo16.action.Action;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *Classe che gestisce la GUI e le funzionalità necessarie per mostrare un messaggio. 
 * Estende la classe BaseHandlerAction.
 * @author valentina <your.name at your.org>
 */
public class HandlerShowMessageAction extends BaseHandlerAction{
    private TextField messTxtBox = new TextField();
          
    /**
     * Gestisce i componenti GUI in base all'azione specificata.
     *
     * @param ap  L'AnchorPane dove sono posizionati i componenti GUI.
     * @param s   L'identificatore dell'azione.
     * @param btn Il pulsante associato all'azione.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb, Button btn){ 
        if(cb.getValue().equals("Show message")){
            // Cancella i componenti GUI esistenti
            ap.getChildren().clear();
            ap.setId("ShowMessagePane");
           
            // Configura la casella di testo per il messaggio
            messTxtBox.setPromptText("Your text...");
            ap.getChildren().add(messTxtBox);
            messTxtBox.setLayoutX(106);
            messTxtBox.setLayoutY(45);
            messTxtBox.setPrefWidth(200);
            messTxtBox.setPrefHeight(31);
        }else{
            super.handleGUI(ap, cb, btn);
        }      
    }
    
    /**
     * Gestisce il comportamento in base allo stato dell'AnchorPane.
     *
     * @param ap L'AnchorPane che rappresenta lo stato corrente.
     * @return Un'istanza di Action basata sullo stato corrente.
     */
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
