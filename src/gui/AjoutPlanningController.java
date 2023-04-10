/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Planning;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.PlanningService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutPlanningController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane addPlanningPane;
    
    
    @FXML
    void return_ListPlanning()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listPlanning.fxml"));
        addPlanningPane.getChildren().removeAll();
        addPlanningPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    /*Formulaire AjoutPlanning*/
    @FXML
    private TextField textCoursPlanning;
    @FXML
    private DatePicker txtDatePlanning;
    @FXML
    private TextField textJourPlanning;
    @FXML
    private TextField textHeurePlanning;
    
    
    @FXML
    private Button btnClearPlanning;
    @FXML
    private Button btnAddPlanning;
    
    
    @FXML
    private void AjoutPlanning(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddPlanning){
            if (textCoursPlanning.getText().isEmpty() || textJourPlanning.getText().isEmpty() || textHeurePlanning.getText().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Missing Information");
                alert.setHeaderText(null);
                alert.setContentText("You have to complete all details about your planning.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterPlanning();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Added Successfully");
                alert.setHeaderText(null);
                alert.setContentText("Your Planning was added to DataBase.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsPlanning();
            }
        }
        if(event.getSource() == btnClearPlanning){
            clearFieldsPlanning();
        }
    }
    
    
    @FXML
    private void clearFieldsPlanning() {
        textCoursPlanning.clear();
        textJourPlanning.clear();
        textHeurePlanning.clear();
    }
    
    
    private void ajouterPlanning() {
        
         // From Formulaire
        int coursPlanning = Integer.parseInt(textCoursPlanning.getText());
        Date datePlanning = null;
        try {
            LocalDate localDate = txtDatePlanning.getValue();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                datePlanning = Date.from(instant);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        String jourPlanning = textJourPlanning.getText();
        int heurePlanning = Integer.parseInt(textHeurePlanning.getText());
       
        
        Planning p = new Planning(
                coursPlanning, datePlanning, jourPlanning, heurePlanning);
        PlanningService ps = new PlanningService();
        ps.ajouter(p);
    }
    
}
