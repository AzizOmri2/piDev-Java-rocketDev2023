
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a52;

import entites.Menu;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.MenuServices;
import utils.MyDB;

/**
 *
 * @author lenovo
 */
public class Pidev3a52 extends Application{

  
     @Override
    public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/gui/FXML_BACKRestaurant.fxml"));
   // Parent root = FXMLLoader.load(getClass().getResource("/gui/FXML_FrontRestaurant.fxml"));
         // Jouer un son
           Media sound = new Media(new File("welcome.mp3").toURI().toString());
           MediaPlayer mediaPlayer = new MediaPlayer(sound);
           mediaPlayer.play();
         //fin jouer son 
    
      // Afficher un message de bienvenue
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Bienvenue");
    alert.setHeaderText("EnergyBox Restaurant");
  //  alert.setContentText("Nous sommes ravis de vous accueillir !");
    alert.setResizable(true); // permet le redimensionnement de la fenêtre
    alert.getDialogPane().setPrefSize(1200, 800); // définir la taille préférée de la fenêtre
   
    //Fin afficher msg 
      // Ajouter un style CSS personnalisé pour la fenêtre d'alerte
      DialogPane dialogPane = alert.getDialogPane();
     dialogPane.getStylesheets().add(getClass().getResource("/gui/alert.css").toExternalForm());
  
    alert.getDialogPane().getStyleClass().add("custom-alert");
// Créez un objet TextFlow avec le texte souhaité
TextFlow textFlow = new TextFlow();
textFlow.getChildren().addAll(
    new Text("Bienvenue "),
    new Text("chez "),
    new Text("EnergyBox !")
);
 
// Changez la couleur de l'écriture en rouge
for (Node child : textFlow.getChildren()) {
    ((Text) child).setStyle("-fx-fill: #720000;");
}
textFlow.setStyle("-fx-font-weight: bold;-fx-font-size: 50px;");// définit la taille de police à 30px
// Ajoutez une animation pour animer le texte
TranslateTransition tt = new TranslateTransition(Duration.seconds(2), textFlow);
tt.setByX(300);
tt.setCycleCount(Animation.INDEFINITE);
tt.setAutoReverse(true);
tt.play();

// Ajoutez le TextFlow à la fenêtre d'alert
alert.getDialogPane().setContent(textFlow);

     alert.showAndWait();
    
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  /*      
 //        MyDB db = new MyDB();
//        MyDB db1 = new MyDB();
     
        MyDB db2 = MyDB.getInstance();
        MyDB db3 = MyDB.getInstance();
        
        Menu p = new Menu("test","java");
        Menu p1 = new Menu("testtt","java");
        MenuServices ps = new MenuServices();
        try {
       //    ps.ajouter(p);
       //    ps.ajouterr(p1);
            System.out.println(ps.afficherListe());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        */

     launch(args);

    }
   
   
}







