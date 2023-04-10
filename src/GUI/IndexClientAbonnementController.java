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
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class IndexClientAbonnementController implements Initializable {

@FXML
    private Button btnRserverUnAbonnement;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void handleClicks(ActionEvent actionEvent ,  ResourceBundle  rb){
     if (actionEvent.getSource() == btnRserverUnAbonnement) {
           //inserer ici
           this.initialize("AjoutAbonnementClient.fxml", rb);
        }
    }

    private void initialize(String ajoutAbonnementClientfxml, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @FXML
        private void puser(javafx.event.ActionEvent event) throws IOException 
       {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("AjoutAbonnementClient.fxml"));
                   Parent root= loader.load();
                 btnRserverUnAbonnement.getScene().setRoot(root);
                   
       }

   
}
