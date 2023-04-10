/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.sql.Date;
import entites.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_AjouterReservationController implements Initializable {

    @FXML
    private Button Reserver;
    @FXML
    private Button Retour;
    @FXML
    private Button Annuler;
    @FXML
    private TextArea fxid;
    @FXML
    private TextArea fxidplat;
    @FXML
    private TextArea fxuserid;
    @FXML
    private DatePicker fxdate;

    public Connection conx;
    public Statement stm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          Reservation m =new Reservation () ;
          
         try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }
          Reserver.setOnAction((ActionEvent event) -> {
              LocalDate date = fxdate.getValue();
              if (date != null && date.isAfter(LocalDate.now().minusDays(1))) {
                 String dateString = date.toString();
                 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                 java.util.Date utilDate = null;
                 try {
                      utilDate = formatter.parse(dateString);
                    } catch (ParseException ex) {
                         Logger.getLogger(FXML_AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
                      }
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            m.setDate(sqlDate);

            ajouterReservation(m);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("DATE INVALIDE!");
            alert.setContentText("La date doit être supérieure ou égale à la date d'aujourd'hui.");
            alert.showAndWait();
        }
    });
         Retour.setOnAction((ActionEvent event) -> {
            redirectToList();
    });
         Annuler.setOnAction((ActionEvent event) -> {
          fxdate.setValue(null);
    });


    }

private void redirectToList(){
    Parent root;
    try {
        root = FXMLLoader.load(getClass().getResource("FXML_FrontRestaurant.fxml"));
        Scene c = new Scene(root);
        Stage stage = (Stage)Retour.getScene().getWindow();
        stage.setScene(c);
    } catch (IOException ex) {
        Logger.getLogger(FXML_AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public void ajouterReservation(Reservation p) {

        try {
            String req = "INSERT INTO `reservation`(`date`) "
                    + "VALUES (?)";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setDate(1, p.getDate());
            ps.executeUpdate();
            System.out.println("Réservation ajoutée avec succèes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
 