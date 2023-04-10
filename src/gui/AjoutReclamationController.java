/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
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
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane addReclamationPane;
    
    
    @FXML
    void return_ListReclamation()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listReclamation.fxml"));
        addReclamationPane.getChildren().removeAll();
        addReclamationPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    /*Formulaire AjoutReclamation*/
    @FXML
    private TextField textNomUserRec;
    @FXML
    private TextField textEmailUserRec;
    @FXML
    private TextField textObjetRec;
    @FXML
    private TextField textCategRec;
    @FXML
    private DatePicker txtDateRec;
    @FXML
    private TextArea textTexteRec;
    
    
    @FXML
    private Button btnClearRec;
    @FXML
    private Button btnAddRec;
    
    
    @FXML
    private void AjoutReclamation(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddRec){
            if (textNomUserRec.getText().isEmpty() || textEmailUserRec.getText().isEmpty() || textObjetRec.getText().isEmpty()
                    || textCategRec.getText().isEmpty() || textTexteRec.getText().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Missing Information");
                alert.setHeaderText(null);
                alert.setContentText("You have to complete all details about your reclamation.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterReclamation();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Added Successfully");
                alert.setHeaderText(null);
                alert.setContentText("Your Reclamation was added to DataBase.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsReclamation();
            }
        }
        if(event.getSource() == btnClearRec){
            clearFieldsReclamation();
        }
    }
    
    
    @FXML
    private void clearFieldsReclamation() {
        textNomUserRec.clear();
        textEmailUserRec.clear();
        textObjetRec.clear();
        textCategRec.clear();
        textTexteRec.clear();
    }
    
    
    private void ajouterReclamation() {
        
         // From Formulaire
        String nomUserRec = textNomUserRec.getText();
        String emailUserRec = textEmailUserRec.getText();
        String objetRec = textObjetRec.getText();
        int categRec = Integer.parseInt(textCategRec.getText());
        Date dateReclamation = null;
        try {
            LocalDate localDate = txtDateRec.getValue();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                dateReclamation = Date.from(instant);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        String texteRec = textTexteRec.getText();
        
        
        Reclamation r = new Reclamation(
                categRec, objetRec, texteRec, dateReclamation, nomUserRec, emailUserRec);
        ReclamationService rs = new ReclamationService();
        rs.ajouter(r);
    }
    
}
