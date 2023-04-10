/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reponse;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ReponseService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutReponseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane addReponsePane;
    
    
    @FXML
    void return_ListReponse()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listReponse.fxml"));
        addReponsePane.getChildren().removeAll();
        addReponsePane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    /*Formulaire AjoutReponse*/
    @FXML
    private TextField textReclamationReponse;
    @FXML
    private TextField textObjetReponse;
    @FXML
    private DatePicker textDateReponse;
    @FXML
    private TextField textPieceJointeReponse;
    @FXML
    private TextArea textContenuReponse;
    
    
    @FXML
    private Button btnAddReponse;
    @FXML
    private Button btnClearReponse;
    
    
    @FXML
    private void AjoutReponse(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddReponse){
            if (textReclamationReponse.getText().isEmpty() || textObjetReponse.getText().isEmpty() 
                    || textPieceJointeReponse.getText().isEmpty() || textContenuReponse.getText().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Missing Information");
                alert.setHeaderText(null);
                alert.setContentText("You have to complete all details about your reply.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterReponse();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Added Successfully");
                alert.setHeaderText(null);
                alert.setContentText("Your Reply was added to DataBase.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsReponse();
            }
        }
        if(event.getSource() == btnClearReponse){
            clearFieldsReponse();
        }
    }
    
    
    @FXML
    private void clearFieldsReponse() {
        textReclamationReponse.clear();
        textObjetReponse.clear();
        textPieceJointeReponse.clear();
        textContenuReponse.clear();
    }
    
    
    private void ajouterReponse() {
        
         // From Formulaire
        int recRep = Integer.parseInt(textReclamationReponse.getText());
        String objetRep = textObjetReponse.getText();
        Date dateRep = null;
        try {
            LocalDate localDate = textDateReponse.getValue();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                dateRep = Date.from(instant);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        String pieceJointe = textPieceJointeReponse.getText();
        String contenuRep = textContenuReponse.getText();
        
        
        Reponse re = new Reponse(
                recRep, objetRep, dateRep, pieceJointe, contenuRep);
        ReponseService rep = new ReponseService();
        rep.ajouter(re);
    }
    
}
