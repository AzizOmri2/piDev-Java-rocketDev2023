/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;


/**
 * FXML Controller class
 *
 * @author wiem
 */
public class GestionAbonnementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnPack;

    @FXML
    private Button btnAbonnement;

    @FXML
    private Button btnPromotion;

    @FXML
    private Button btnAbonnements;
    @FXML
    private AnchorPane gestionAbonnement;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void back_GestionAbonnemet() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionAbonnement.fxml"));
        Parent root = loader.load();
        btnAbonnements.getScene().setRoot(root);
    }

    @FXML
    void open_listPacks() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("IndexPackAdmin.fxml"));
        Parent root = loader.load();
        btnPack.getScene().setRoot(root);
    }

    @FXML
    void open_listPromotions() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("IndexPromotionAdmin.fxml"));
        Parent root = loader.load();
        btnPromotion.getScene().setRoot(root);
    }

    @FXML
    void open_listAbonnnements() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("IndexAbonnementAdmin.fxml"));
        Parent root = loader.load();
        btnAbonnement.getScene().setRoot(root);
    }

    /*
    @FXML
    public void close(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EnergyBox | CrossFit Center");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous quitter ?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                System.exit(0);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    @FXML
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);   
    }
     */
}
