/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class SideNavBController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnPlannings;
    @FXML
    private Button btnAbonnements;
    @FXML
    private Button btnCompetitions;
    @FXML
    private Button btnMateriaux;
    @FXML
    private Button btnSponsors;
    @FXML
    private Button btnRestaurants;
    @FXML
    private Button btnReclamation;

    @FXML
    private Button closW;
    @FXML
    private MenuItem profile;
    @FXML
    private MenuButton menu;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }


    @FXML
    private void listPlannings(MouseEvent event) {
    }

    @FXML
    private void ListAbonnements(MouseEvent event) {
    }

    @FXML
    private void listCompetitions(MouseEvent event) {
    }

    @FXML
    private void listMateriaux(MouseEvent event) {
    }

    @FXML
    private void listRestau(MouseEvent event) {
    }

    @FXML
    private void listReclamation(MouseEvent event) {
    }

    @FXML
    private void GoSponsor(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("listSponsor.fxml"));

        bp.setCenter(view);
    }

    @FXML
    private void GoUsers(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("ListUsers.fxml"));

        bp.setCenter(view);
    }
    
    public void GoProfile(MouseEvent event) {
        Stage stage = (Stage) closW.getScene().getWindow();
        //System.out.println("hi");
        stage.close();
    }
    
    

    @FXML
    public void closeW(MouseEvent event) {
        Stage stage = (Stage) closW.getScene().getWindow();
        //System.out.println("hi");
        stage.close();
    }

    @FXML
    private void Pro(ActionEvent event) {
    }

}
