/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_FrontRestaurantController implements Initializable {

    @FXML
    private Button Reserver;
    
    
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
      Reserver.setOnAction((ActionEvent event) -> {
            GoToReservation();
    }); 
    //  FXML_FrontRestaurantController controller = new FXML_FrontRestaurantController();
    //  controller.afficherMenuSousFormeDImages();

    }    
     private void GoToReservation(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_AjouterReservation.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)Reserver.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
     
 /*    
public void afficherMenuSousFormeDImages() {
    // Créer une JFrame pour contenir le panel
    JFrame frame = new JFrame("Menu");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Créer un JPanel pour contenir les images du menu
    JPanel menuPanel = new JPanel(new GridLayout(1, 0, 10, 0));
    menuPanel.setPreferredSize(new Dimension(800, 400));

    try {
        // Exécuter une requête SQL pour récupérer le nom de chaque menu
        String sql = "SELECT categories FROM menu";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        // Parcourir chaque nom de menu dans le résultat de la requête
        while (rs.next()) {
            // Récupérer le nom de menu à partir du résultat de la requête
            String menuNom = rs.getString("categories");

            // Charger l'image correspondante à partir du nom de fichier
            ImageIcon icon = new ImageIcon(getClass().getResource(menuNom.toLowerCase() + ".jpg"));
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);

            // Créer un JLabel pour afficher l'image
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setVerticalAlignment(JLabel.TOP);

            // Ajouter le nom du menu sous l'image
            JLabel nameLabel = new JLabel(menuNom);
            nameLabel.setHorizontalAlignment(JLabel.CENTER);

            // Créer un JPanel pour contenir l'image et le nom de menu
            JPanel menuImagePanel = new JPanel(new GridLayout(2, 0, 0, 10));
            menuImagePanel.add(imageLabel);
            menuImagePanel.add(nameLabel);

            // Ajouter le JPanel à la vue contenant toutes les images
            menuPanel.add(menuImagePanel);
        }
    } catch (SQLException ex) {
        System.out.println("Failed to execute SQL query: " + ex.getMessage());
    }

    // Ajouter la vue contenant toutes les images à la JFrame
    frame.getContentPane().add(menuPanel);
    frame.pack();
    frame.setVisible(true);
}
*/
     
     
}
