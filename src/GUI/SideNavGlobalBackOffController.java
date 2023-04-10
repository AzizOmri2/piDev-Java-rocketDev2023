/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author azizo
 */
public class SideNavGlobalBackOffController implements Initializable {
    
    @FXML
    private VBox Items = null;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnPlanning;
    
    @FXML
    private Button btnCours;
    
    @FXML
    private Button btnActivite;

    @FXML
    private Button btnReclamation;

    @FXML
    private Button btnReponse;
    
    @FXML
    private Pane paneHome;
    
    @FXML
    private Pane panePlanning;
    
    @FXML
    private Pane paneCours;
    
    @FXML
    private Pane paneActivite;

    @FXML
    private Pane paneReclamation;

    @FXML
    private Pane paneReponse;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Node[] nodes = new Node[8];
        for(int i=0 ; i<nodes.length ; i++){
            try{
                
                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("FXMLItem.fxml"));
                
                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #720000");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #fff");
                });
                Items.getChildren().add(nodes[i]);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnHome) {
            paneHome.setStyle("-fx-background-color : #1620A1");
            paneHome.toFront();
        }
        if(actionEvent.getSource() == btnPlanning){
            panePlanning.setStyle("-fx-background-color : #720000");
            panePlanning.toFront();
        }
        if(actionEvent.getSource() == btnCours){
            paneCours.setStyle("-fx-background-color : #724587");
            paneCours.toFront();
        }
        if(actionEvent.getSource() == btnActivite){
            paneActivite.toFront();
        }
        if(actionEvent.getSource() == btnReclamation){
            paneReclamation.setStyle("-fx-background-color : #000");
            paneReclamation.toFront();
        }
        if(actionEvent.getSource() == btnReponse){
            paneReponse.setStyle("-fx-background-color : #BF4527");
            paneReponse.toFront();
        }
        
    }
    
    
    
}
