/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Pack;

import Services.PackService;
import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class AjoutPackController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TextField typePack;

    @FXML
    private TextField montantPack;

    @FXML
    private TextField dureePack;

    @FXML
    private TextField descriptionPack;

    @FXML
    private TextField placesPack;

    @FXML
    private TextField disponibilitePack;

    @FXML
    private Button btnValider;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      @FXML
    private void ajouterPack(ActionEvent event) throws SQLException, IOException {
        PackService utilisateurService = new PackService();
         
        
       Pack p = new Pack(typePack.getText(),Float.parseFloat( montantPack.getText()),Integer.parseInt(dureePack.getText()),descriptionPack.getText(),Integer.parseInt(placesPack.getText()), Integer.parseInt(disponibilitePack.getText()) );
       
     utilisateurService.create(p);
     JOptionPane.showMessageDialog(null, "Pack ajouté avec succe");
     FXMLLoader loader= new FXMLLoader(getClass().getResource("AjoutPack.fxml"));
                   Parent root= loader.load();
                 btnValider.getScene().setRoot(root);
       
    
}
}
