/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Cours;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.CoursService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutCoursController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane addCoursPane;
    
    
    @FXML
    void return_ListCours()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listCours.fxml"));
        addCoursPane.getChildren().removeAll();
        addCoursPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    /*Formulaire AjoutCours*/
    @FXML
    private TextField textNomCours;
    @FXML
    private TextField textPrixCours;
    @FXML
    private TextField textNomCoachCours;
    @FXML
    private TextField textAgeMinCours;
    @FXML
    private TextArea textDescriptionCours;
    
    
    @FXML
    private Button btnAddCours;
    @FXML
    private Button btnClearCours;
    
    
    @FXML
    private void AjoutCours(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddCours){
            if (textNomCours.getText().isEmpty() || textPrixCours.getText().isEmpty() || textNomCoachCours.getText().isEmpty() || textAgeMinCours.getText().isEmpty() || 
                textDescriptionCours.getText().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Missing Information");
                alert.setHeaderText(null);
                alert.setContentText("You have to complete all details about your course.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterCours();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Added Successfully");
                alert.setHeaderText(null);
                alert.setContentText("Your course was added to DataBase.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsCours();
            }
        }
        if(event.getSource() == btnClearCours){
            clearFieldsCours();
        }
    }
    
    @FXML
    private void clearFieldsCours() {
        textNomCours.clear();
        textPrixCours.clear();
        textNomCoachCours.clear();
        textAgeMinCours.clear();
        textDescriptionCours.clear();
    }
    
    
    private void ajouterCours() {
        
         // From Formulaire
        String nomCours = textNomCours.getText();
        float prixCours = Float.parseFloat(textPrixCours.getText());
        String nomCoach = textNomCoachCours.getText();
        int ageCours = Integer.parseInt(textAgeMinCours.getText());
        String descpCours = textDescriptionCours.getText();
       
        
        MyDB db = MyDB.getInstance();
        Cours c = new Cours(
                nomCours, prixCours, nomCoach, ageCours, descpCours);
        CoursService cs = new CoursService();
        cs.ajouter(c);
    }  
    
}
