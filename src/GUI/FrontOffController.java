/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class FrontOffController implements Initializable {

    private BorderPane bp;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuBack;
    @FXML
    private AnchorPane slider;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnPlanning;
    @FXML
    private Button btnActivity;
    @FXML
    private Button btnRestau;
    @FXML
    private Button btnBlog;
    @FXML
    private Button btnCompetition;
    @FXML
    private Button btnUsers3;
    @FXML
    private Button btnUsers6;
    @FXML
    private Button btnClose;
    @FXML
    private ImageView btnAbonnements;
    @FXML
    private Button btnAbonnements2;
    @FXML
    private AnchorPane viewPages;
    @FXML
    private Button btnPlanning2;
    @FXML
    private Button btnReclamation2;
    @FXML
    private Button btnProfilFrontAb;
    @FXML
    private Button btnCompetition1;
    @FXML
    private Button btnRss;
    @FXML
    private Button btnGpt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        slider.setTranslateX(-300);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slider.setTranslateX(-300);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        slider.setTranslateX(0);
        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-300);
            slide.play();
            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuBack.setVisible(false);
            });
        });
         
    }

    @FXML
    private void switchForm(ActionEvent event) throws IOException {

//         if (event.getSource() == btnHome) {
//            //Parent fxml = FXMLLoader.load(getClass().getResource("Weath.fxml"));
//            viewPages.getChildren().removeAll();
//            //viewPages.getChildren().setAll(fxml);
//        } 
        if (event.getSource() == btnAbonnements2) {
            Parent fxml = FXMLLoader.load(getClass().getResource("IndexClientAbonnement.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
//        } else if (event.getSource() == btnGestionPlanning) {
//            Parent fxml = FXMLLoader.load(getClass().getResource("gestionReclamation.fxml"));
//            viewPages.getChildren().removeAll();
//            viewPages.getChildren().setAll(fxml);
//        } else if (event.getSource() == btnGestionReclamation) {
//            Parent fxml = FXMLLoader.load(getClass().getResource("gestionReclamation.fxml"));
//            viewPages.getChildren().removeAll();
//            viewPages.getChildren().setAll(fxml);
//        } else if (event.getSource() == btnSponsors) {
//            Parent fxml = FXMLLoader.load(getClass().getResource("ListSponsor.fxml"));
//            viewPages.getChildren().removeAll();
//            viewPages.getChildren().setAll(fxml);
//        } else if (event.getSource() == profile) {
//            Parent fxml = FXMLLoader.load(getClass().getResource("ProfilAdmin.fxml"));
//            viewPages.getChildren().removeAll();
//            viewPages.getChildren().setAll(fxml);
//        } else if (event.getSource() == btnAbonnements) {
//            Parent fxml = FXMLLoader.load(getClass().getResource("gestionAbonnement.fxml"));
//            viewPages.getChildren().removeAll();
//            viewPages.getChildren().setAll(fxml);
      }
        
        if (event.getSource() == btnPlanning2) {
            Parent fxml = FXMLLoader.load(getClass().getResource("listPlanningFront.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        }
        if (event.getSource() == btnReclamation2) {
            Parent fxml = FXMLLoader.load(getClass().getResource("ajoutReclamationFront.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        } if (event.getSource() == btnProfilFrontAb) {
            Parent fxml = FXMLLoader.load(getClass().getResource("ProfileAbonne.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        }if (event.getSource() == btnCompetition1) {
            Parent fxml = FXMLLoader.load(getClass().getResource("ViewFront.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        }
//        if (event.getSource() == btnRss) {
//            Parent fxml = FXMLLoader.load(getClass().getResource("rss.fxml"));
//            viewPages.getChildren().removeAll();
//            viewPages.getChildren().setAll(fxml);
//        }
        if (event.getSource() == btnGpt) {
            Parent fxml = FXMLLoader.load(getClass().getResource("AskChat.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
       }if (event.getSource() == btnRestau) {
            Parent fxml = FXMLLoader.load(getClass().getResource("FXML_FrontRestaurant.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
       }if (event.getSource() == btnUsers6) {
            Parent fxml = FXMLLoader.load(getClass().getResource("ShowPosts.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
       }
// if (event.getSource() == btnHome) {
//            Parent fxml = FXMLLoader.load(getClass().getResource("FrontOff.fxml"));
//            viewPages.getChildren().removeAll();
//            viewPages.getChildren().setAll(fxml);
//        }

    }

    @FXML
    public void close() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EnergyBox | CrossFit Center");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous quitter ?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}

    
