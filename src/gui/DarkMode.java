/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class DarkMode implements javafx.fxml.Initializable{
 @FXML
  private ToggleButton toggleBtn;
  private Scene scene;
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // La classe de style pour le bouton
        toggleBtn.getStyleClass().add("dark-toggle-btn");
        // Quand le bouton change d'état la fonction est appelée
        toggleBtn.selectedProperty().addListener(this::onSelectChanged);

       StackPane root = new StackPane(toggleBtn);

        scene = new Scene(root, 640, 480);
    }

    private void onSelectChanged(ObservableValue<? extends Boolean> obs, Boolean oldValue, Boolean newValue) {
        if (newValue) {
            /*
       * Suppression de toutes les feuilles de style et ajoute la feuille pour le mode
       * sombre
             */
            scene.getStylesheets().clear();
            scene.getStylesheets().add("DarkMode.css");
        } else {
            // Suppression des feuilles de style (retour au style "par défaut")
            scene.getStylesheets().clear();
        }
    }

    

}





    