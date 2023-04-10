/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.GUI;

import blog.Entities.Post;
import blog.Services.PostService;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class AjoutPostController implements Initializable {
 @FXML
    private Button exit;

    @FXML
    private TextField titleAjoutP;

    @FXML
    private TextField descAjoutP;

    @FXML
    private Button ajouterP;

    @FXML
    private Button annulerP;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleQuitter(ActionEvent event) {
        
        // Obtenez la fenêtre principale
        Stage stage = (Stage) exit.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }
    
     @FXML
    private void retourBlog(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ShowPosts.fxml"));
        Parent root = loader.load();
        annulerP.getScene().setRoot(root);

    }
    
     private boolean verifs() {
if (titleAjoutP.getText().isEmpty() ) {
        JOptionPane.showMessageDialog(null, "Le champ titre est obligatoire !");
        return false;
    }
    if (descAjoutP.getText().isEmpty() ) {
        JOptionPane.showMessageDialog(null, "Le champ description est obligatoire !");
        return false;
    }
 
      
  
     return true;
}
         @FXML
    private void ajouterF(ActionEvent event) throws SQLException, IOException {
        PostService utilisateurService = new PostService();
         
       if (verifs()) {
        
        Post p = new Post(filterWords(titleAjoutP.getText()),filterWords(descAjoutP.getText()));
       
       utilisateurService.create(p);
     JOptionPane.showMessageDialog(null, "Post ajouté avec succe");
     FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/ShowPosts.fxml"));
                   Parent root= loader.load();
                 ajouterP.getScene().setRoot(root);
       }
    }
    
    
    public static String filterWords(String text) {
    String[] filterWords = {"ahla", "word2", "word3"};
    String[] data = text.split("\\s+");
    String str = "";
    for (String s : data) {
        boolean g = false;
        for (String lib : filterWords) {
            if (s.equals(lib)) {
                String t = "";
                for (int i = 0; i < s.length(); i++) t += "*";
                str += t + " ";
                g = true;
                break;
            }
        }
        if (!g) str += s + " ";
    }
    return str.trim();
}
    
    
    
    
}
