/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Menu;
import entites.Plat;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import services.MenuServices;
import services.PlatServices;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_ModifierPlatController implements Initializable {

    @FXML
    private TextArea fxdesc;
    @FXML
    private Button Modifier;
    @FXML
    private Button Retour;
    @FXML
    private TextArea fxnom;
    @FXML
    private Button Annuler;
    @FXML
    private TextArea fxprix;
    @FXML
    private TextArea fxnbp;
    @FXML
    private TextArea fximage;
    @FXML
    private TextArea fxetat;
    @FXML
    private TextArea fxcalories;
    @FXML
    private TextArea fxcategId;
    
    private Plat plat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Annuler.setOnAction((ActionEvent event) -> {
            fxnom.clear();
            fxdesc.clear();
            fxprix.clear();
            fxcalories.clear();
            fxetat.clear();
            fximage.clear();
            fxnbp.clear();
            fxcategId.clear();
        });
        
        Retour.setOnAction((ActionEvent event) -> {
            redirectToList();
        });
    }    
  private void redirectToList() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML_Plat.fxml"));
            Scene c = new Scene(root);
            Stage stage = (Stage) Retour.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterPlatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
   public void setPlat(Plat m) {
        this.plat = m;
        // affichage des détails du menu dans les éléments de l'interface utilisateur  
       fxnom.setText(m.getNom());
       fxdesc.setText(m.getDescription());
       fxprix.setText(String.valueOf(m.getPrix()));
       fxcalories.setText(m.getCalories());
       fxetat.setText(m.getEtat());
       fximage.setText(m.getImage());
      fxnbp.setText(String.valueOf(m.getNbp()));
      fxcategId.setText(String.valueOf(m.getCategories_id()));
    }
   
   @FXML
    public void modifierPlat(ActionEvent event) {
        // récupérer les nouvelles valeurs entrées par l'utilisateur
       
       String nom = fxnom.getText();
       String description = fxdesc.getText();
       String prix = fxprix.getText();
       String calories = fxcalories.getText();
       String etat = fxetat.getText();
       String image = fximage.getText();
       String nbp = fxnbp.getText();
       String categories_id = fxcategId.getText();
// vérifier que toutes les informations requises sont fournies
if (nom.isEmpty() || description.isEmpty() || prix.isEmpty() || calories.isEmpty() || etat.isEmpty() || image.isEmpty() || nbp.isEmpty() || categories_id.isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Tous les champs doivent être remplis.");
    alert.showAndWait();
} else {
    // mettre à jour les informations du plat
    plat.setNom(nom);
    plat.setDescription(description);
    plat.setPrix(Double.parseDouble(prix));
    plat.setCalories(calories);
    plat.setEtat(etat);
    plat.setImage(image);
    plat.setNbp(Integer.parseInt(nbp));
    plat.setCategories_id(Integer.parseInt(categories_id));

    // enregistrer les modifications dans la base de données
    try {
        PlatServices u = new PlatServices();
        u.Modifier(plat);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Le plat a été modifié avec succès.");
        alert.showAndWait();
    } catch (SQLException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite lors de la modification du plat.");
        alert.setContentText(ex.getMessage());
        alert.showAndWait();
    }
    }
    }
}
  
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
  

