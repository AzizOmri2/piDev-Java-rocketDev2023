/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonnement;
import Services.AbonnementService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
public class AjoutAbonnementClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TextField typePack;

    @FXML
    private TextField codePromo;
     @FXML
    private TextField idUser;

    @FXML
    private Button btnValider;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void ajouterF(ActionEvent event) throws SQLException, IOException {
        AbonnementService utilisateurService = new AbonnementService();
         
        Date dateAchat = Date.valueOf("2022-02-20");
        Date dateFin = Date.valueOf("2022-03-20");
        
        Abonnement p = new Abonnement(dateAchat,dateFin,"a",codePromo.getText(),100.f,Integer.parseInt( typePack.getText()),Integer.parseInt(idUser.getText()));
       
     utilisateurService.create(p);
     JOptionPane.showMessageDialog(null, "Abonnement ajouté avec succe");
     FXMLLoader loader= new FXMLLoader(getClass().getResource("IndexClientAbonnement.fxml"));
                   Parent root= loader.load();
                 btnValider.getScene().setRoot(root);
       
    
}
}