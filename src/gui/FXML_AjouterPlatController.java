/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Plat;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

public class FXML_AjouterPlatController implements Initializable {
    @FXML
    private Button fxajout;
    @FXML
    private Button retour;
    @FXML
    private Button fxannuler;
    @FXML
    private TextArea fxnom;
    @FXML
    private TextArea fxdesc;
    @FXML
    private TextArea fxcategId;
    @FXML
    private TextArea fxprix;
    @FXML
    private TextArea fxcalories;
    @FXML
    private TextArea fxetat;
    @FXML
    private TextArea fximage;
    @FXML
    private TextArea fxnbp;

    public Connection conx;
     public Statement stm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Plat p = new Plat();

        try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }

        fxajout.setOnAction((ActionEvent event) -> {
            if (validateInputs() && controleDeSaisie())
            {
                p.setNom(fxnom.getText());
                p.setDescription(fxdesc.getText());
                p.setEtat(fxetat.getText());
                p.setPrix(Double.parseDouble(fxprix.getText()));
                p.setCalories(fxcalories.getText());
                p.setImage(fximage.getText());
                p.setNbp(Integer.parseInt(fxnbp.getText()));
                p.setCategories_id(Integer.parseInt(fxcategId.getText()));
                 ajouterPlat(p);
            }
        });
   
        fxannuler.setOnAction((ActionEvent event) -> {
            fxnom.clear();
            fxdesc.clear();
            fxprix.clear();
            fxcalories.clear();
            fxetat.clear();
            fximage.clear();
            fxnbp.clear();
            fxcategId.clear();
        });
        
        retour.setOnAction((ActionEvent event) -> {
            redirectToList();
        });
    }

     public void ajouterPlat(Plat p) {

        try {
            String req = "INSERT INTO `plat`(`categories_id`, `nom` ,`prix`,`description`,`calories`,`etat` ,`image`,`nbp`) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, p.getCategories_id());
            ps.setString(2, p.getNom());
            ps.setDouble(3, p.getPrix());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getCalories());
            ps.setString(6, p.getEtat());
            ps.setString(7, p.getImage());
            ps.setInt(8, p.getNbp());
            ps.executeUpdate();
            System.out.println("Plat ajouté avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    private boolean validateInputs() {
           String nom = fxnom.getText();
           String desc = fxdesc.getText();
           String prix = fxprix.getText();
           String categId = fxcategId.getText();
           String calories = fxcalories.getText();
           String etat = fxetat.getText();
           String image = fximage.getText();
           String nbp = fxnbp.getText();
           
        if (nom.trim().isEmpty() || desc.trim().isEmpty() || prix.trim().isEmpty() || categId.trim().isEmpty()
            || calories.trim().isEmpty() || etat.trim().isEmpty() || image.trim().isEmpty()
            || nbp.trim().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Un ou plusieurs champs sont vides");
        alert.showAndWait();
        return false;
    }
    try {
        Double.parseDouble(prix);
        Integer.parseInt(categId);
        Integer.parseInt(nbp);
        return true;
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Le format de certains champs est incorrect");
        alert.showAndWait();
        return false;
    }
}

private void redirectToList(){
    Parent root;
    try {
        root = FXMLLoader.load(getClass().getResource("FXML_Plat.fxml"));
        Scene c = new Scene(root);
        Stage stage = (Stage)fxajout.getScene().getWindow();
        stage.setScene(c);
    } catch (IOException ex) {
        Logger.getLogger(FXML_AjouterPlatController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

private boolean controleDeSaisie() {
    // Récupérer les valeurs saisies par l'utilisateur
    String nom = fxnom.getText().trim();
    String desc = fxdesc.getText().trim();
    String categId = fxcategId.getText().trim();
    String prixStr = fxprix.getText().trim();
    String caloriesStr = fxcalories.getText().trim();
    String etatStr = fxetat.getText().trim();
    String image = fximage.getText().trim();
    String nbpStr = fxnbp.getText().trim();

    // Vérifier la validité des saisies
    boolean isValid = true;

    // Vérifier le nom
    if (nom.length() < 3 || !Character.isUpperCase(nom.charAt(0))) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Le nom doit commencer par une majuscule et contenir au moins 3 lettres.");
        alert.showAndWait();
        isValid = false;
    }

    // Vérifier la description
    if (desc.length() < 10 || !Character.isUpperCase(desc.charAt(0))) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText( "La description doit commencer par une majuscule et contenir au moins 10 lettres.");
        alert.showAndWait();
        isValid = false;
    }

    // Vérifier le prix
    double prix = 0;
    try {
        prix = Double.parseDouble(prixStr);
        if (prix <= 0) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Le prix doit être un nombre positif.");
        alert.showAndWait();
            isValid = false;
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Le prix doit être un nombre positif.");
        alert.showAndWait();
        isValid = false;
    }

    // Vérifier les calories
    if (caloriesStr.length() < 1 || caloriesStr.length() > 4 || !caloriesStr.matches("\\d+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Les calories doivent contenir entre 1 et 4 chiffres.");
        alert.showAndWait();
        isValid = false;
    }

    // Vérifier le nbp
    int nbp = 0;
    try {
        nbp = Integer.parseInt(nbpStr);
        if (nbp <= 0 || nbp > 50) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Erreur de saisie");
              alert.setHeaderText("Le nombre de personnes doit être un entier positif et ne doit pas dépasser 50.");
              alert.showAndWait();             
            isValid = false;
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Erreur de saisie");
              alert.setHeaderText("Le nombre de personnes doit être un entier positif et ne doit pas dépasser 50.");
              alert.showAndWait();
        isValid = false;
    }

    // Vérifier l'état
    if (!etatStr.equals("0") && !etatStr.equals("1")) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Erreur de saisie");
              alert.setHeaderText( "L'état doit être 0 ou 1.");
              alert.showAndWait();
        isValid = false;
    }

    // Si toutes les saisies sont valides, ajouter le plat
    if (isValid) {
        Plat p = new Plat();
        p.setNom(nom);
        p.setDescription(desc);
        p.setEtat(etatStr);
        p.setPrix(prix);
        p.setCalories(caloriesStr);
        p.setImage(image);
        p.setNbp(nbp);
        p.setCategories_id(Integer.parseInt(categId));
        ajouterPlat(p);
    }

    return isValid;
}

}
