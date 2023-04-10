/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CategoryReclamation;
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
import services.CategoryReclamationService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutCategoryReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane addCategRecPane;
    
    
    @FXML
    void return_ListCategoryReclamation()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listCategoryReclamation.fxml"));
        addCategRecPane.getChildren().removeAll();
        addCategRecPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    /*Formulaire AjoutCategoryReclamation*/
    @FXML
    private TextField textNomCategRec;
    @FXML
    private TextField textPrioriteCategRec;
    @FXML
    private TextArea textDescriptionCategRec;
    
    
    @FXML
    private Button btnClearCategRec;
    @FXML
    private Button btnAddCategRec;
    
    
    @FXML
    private void AjoutCategoryReclamation(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddCategRec){
            if (textNomCategRec.getText().isEmpty() || textPrioriteCategRec.getText().isEmpty() || textDescriptionCategRec.getText().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Missing Information");
                alert.setHeaderText(null);
                alert.setContentText("You have to complete all details about your category reclamation.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterCategoryReclamation();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Added Successfully");
                alert.setHeaderText(null);
                alert.setContentText("Your Category Reclamation was added to DataBase.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsCategoryReclamation();
            }
        }
        if(event.getSource() == btnClearCategRec){
            clearFieldsCategoryReclamation();
        }
    }
    
    
    @FXML
    private void clearFieldsCategoryReclamation() {
        textNomCategRec.clear();
        textPrioriteCategRec.clear();
        textDescriptionCategRec.clear();
    }
    
    
    private void ajouterCategoryReclamation() {
        
         // From Formulaire
        String nomCategRec = textNomCategRec.getText();
        String prioriteCategRec = textPrioriteCategRec.getText();
        String descriptionRec = textDescriptionCategRec.getText();
        
        
        
        CategoryReclamation cr = new CategoryReclamation(
                nomCategRec, descriptionRec, prioriteCategRec);
        CategoryReclamationService crs = new CategoryReclamationService();
        crs.ajouter(cr);
    }
    
}
