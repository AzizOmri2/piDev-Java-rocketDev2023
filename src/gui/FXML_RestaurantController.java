/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_RestaurantController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPlats;
    @FXML
    private Button btnReservations;
    @FXML
    private Pane panePlats;

    public Connection conx;
    public Statement stm;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // TODO
         try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }
          btnMenus.setOnAction((ActionEvent event) -> {
        GoToMenu();
    });
            btnPlats.setOnAction((ActionEvent event) -> {
        GoToPlat();
    });
              btnReservations.setOnAction((ActionEvent event) -> {
        GoToReservation();
    });
    
    }    
    
     private void GoToMenu(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Menu.fxml"));
            Scene c=new Scene(root);
            Stage stage=(Stage)btnMenus.getScene().getWindow();
           stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      private void GoToPlat(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Plat.fxml"));
            Scene c=new Scene(root);
            Stage stage=(Stage)btnPlats.getScene().getWindow();
           stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         private void GoToReservation(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Reservation.fxml"));
            Scene c=new Scene(root);
            Stage stage=(Stage)btnReservations.getScene().getWindow();
           stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
