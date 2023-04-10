/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Activite;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ActiviteService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutActiviteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane addActivitePane;
    
    
    @FXML
    void return_ListActivite()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listActivite.fxml"));
        addActivitePane.getChildren().removeAll();
        addActivitePane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options = FXCollections.observableArrayList(
        "Facile","Moyen","Difficile");
        textDiffAct.setItems(options);
    }    
    
    
    /*Formulaire AjoutActivité*/
    @FXML
    private TextArea textDescriptionAct;
    /*@FXML
    private TextField textDiffAct;*/
    
    String difficulte[] = { "Facile", "Moyen", "Difficile" };
    /*@FXML
    private ComboBox textDiffAct=*/
    @FXML
    private ComboBox<String> textDiffAct;
            
    @FXML
    private TextField textDureeAct;
    @FXML
    private TextField textNomAct;
    @FXML
    private TextField textTenueAct;
    @FXML
    private TextField textImageAct;
    
    
    @FXML
    private Button btnImportAct;
    @FXML
    private Button btnAddAct;
    @FXML
    private Button btnClearAct;
    
    
    @FXML
    private void AjoutActivite(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddAct){
            if (textDescriptionAct.getText().isEmpty() || textDiffAct.getItems().isEmpty() || textDureeAct.getText().isEmpty() || textNomAct.getText().isEmpty() || 
                textTenueAct.getText().isEmpty() || textImageAct.getText().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Missing Information");
                alert.setHeaderText(null);
                alert.setContentText("You have to complete all details about your activity.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {    
                ajouterActivite();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Added Successfully");
                alert.setHeaderText(null);
                alert.setContentText("Your activity was added to DataBase.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsActivite();
            }
        }
        if(event.getSource() == btnClearAct){
            clearFieldsActivite();
        }
    }
    
    
    @FXML
    private void clearFieldsActivite() {
        textDescriptionAct.clear();
        textDiffAct.getItems().clear();
        textDureeAct.clear();
        textNomAct.clear();
        textTenueAct.clear();
        textImageAct.clear();
    }
    
    
    private void ajouterActivite() {
        
         // From Formulaire
        String nomAct = textNomAct.getText();
        String dureeAct = textDureeAct.getText();
        String tenueAct = textTenueAct.getText();
        String diffAct = textDiffAct.getValue();
        String imgAct = textImageAct.getText();
        String descpAct = textDescriptionAct.getText();
       
        
        MyDB db = MyDB.getInstance();
        Activite act = new Activite(
                nomAct, dureeAct, tenueAct, diffAct, imgAct, descpAct);
        ActiviteService as = new ActiviteService();
        as.ajouter(act);
    }
    
}
