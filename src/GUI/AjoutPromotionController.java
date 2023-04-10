/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Pack;
import Entities.Promotion;
import Services.PackService;
import Services.PromotionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import java.sql.Date;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class AjoutPromotionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField codePromotion;

    @FXML
    private TextField reductionPromotion;

    @FXML
    private DatePicker dateExpiration;

    @FXML
    private Button btnValider;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
       @FXML
    private void ajouterPromtion(ActionEvent event) throws SQLException, IOException {
        PromotionService utilisateurService = new PromotionService();
      Promotion p = new Promotion(codePromotion.getText(),Float.parseFloat( reductionPromotion.getText()),Date.valueOf(dateExpiration.getValue()));
       
    utilisateurService.create(p);
     JOptionPane.showMessageDialog(null, "Promotion ajouté avec succe");
     FXMLLoader loader= new FXMLLoader(getClass().getResource("AjoutPromotion.fxml"));
                   Parent root= loader.load();
                 btnValider.getScene().setRoot(root);
       
    
}
    
}
