/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.BitMatrix;
import com.itextpdf.text.pdf.qrcode.WriterException;
import entites.Menu;
import entites.Plat;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pidev3a52.Pidev3a52;
import services.MenuServices;
import services.PlatServices;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.TextField;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.itextpdf.text.Element;


/**
 * FXML Controller class
 *
 * @author lenovo
 */

public class FXML_FrontPlatController implements Initializable {

    @FXML
    private Button Reserver;
    @FXML
    private Button Retour;
    @FXML
    private Label menuN;
    @FXML
    private Label menuP;
    @FXML
    private Label calories;
    @FXML
    private Label img;
    @FXML
    private ImageView imgg;
    @FXML
    private Label description;   
    @FXML
    private Label nbp; 
    
    public Connection conx;
    public Statement stm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                PlatServices ser = new PlatServices();    
List<Plat> listeee;
        try {
            listeee = ser.afficherListe1(Pidev3a52.id);
     
ObservableList<String> list = FXCollections.observableArrayList();
ObservableList<Double> listP = FXCollections.observableArrayList();
ObservableList<String> listcalories = FXCollections.observableArrayList();
ObservableList<String> listdescription  = FXCollections.observableArrayList();
ObservableList<Integer> listNbp  = FXCollections.observableArrayList();
//ObservableList<String> listImg = FXCollections.observableArrayList();

for (Plat menu : listeee) {
    list.addAll(menu.getNom());
}
for (Plat menu : listeee) {
    listP.addAll(menu.getPrix());
}
for (Plat menu : listeee) {
    listcalories.addAll(String.valueOf(menu.getCalories()));
}
for (Plat menu : listeee) {
    listdescription.addAll(menu.getDescription());
}
for (Plat menu : listeee) {
    listNbp.addAll(menu.getNbp());
}
//for (Plat menu : listeee) {
  //  listImg.addAll(menu.getImage());
//}

menuN.setText("Nom:"+ list.get(Pidev3a52.j));
menuP.setText("Prix:"+Double.toString(listP.get(Pidev3a52.j)) + "Dt");
calories.setText("Calories:"+ listcalories.get(Pidev3a52.j) + "kcal" );
description.setText("Description:"+ listdescription.get(Pidev3a52.j));
nbp.setText("Nombre de plats:"+ listNbp.get(Pidev3a52.j));
//img.setText(listImg.get(Pidev3a52.j));
//imgg.setImage(new Image(listImg.get(Pidev3a52.j)));

   } catch (SQLException ex) {
            Logger.getLogger(FXML_FrontPlatController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }   
      Reserver.setOnAction((ActionEvent event) -> {
            GoToReservation();
    }); 
      Retour.setOnAction((ActionEvent event) -> {
            GoBackToMenu();
    }); 
    
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
     private void GoBackToMenu(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_FrontRestaurant.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)Retour.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void next(ActionEvent event) throws SQLException {
        Pidev3a52.j ++;
        PlatServices ser = new PlatServices();    
        List<Plat> listeee;
        listeee = ser.afficherListe1(Pidev3a52.id);
     
            if( Pidev3a52.j == listeee.size())
                Pidev3a52.j = 0;
            ObservableList<String> list = FXCollections.observableArrayList();
            ObservableList<Double> listP = FXCollections.observableArrayList();
            ObservableList<String> listcalories = FXCollections.observableArrayList();
            ObservableList<String> listdescription  = FXCollections.observableArrayList();
            ObservableList<Integer> listNbp  = FXCollections.observableArrayList();
          //  ObservableList<String> listImg = FXCollections.observableArrayList();
            
   for (Plat Plat : listeee) {
    list.addAll(Plat.getNom());
}
   for (Plat menu : listeee) {
    listP.addAll(menu.getPrix());
}
for (Plat menu : listeee) {
    listcalories.addAll(String.valueOf(menu.getCalories()));
}
for (Plat menu : listeee) {
    listdescription.addAll(menu.getDescription());
}
for (Plat menu : listeee) {
    listNbp.addAll(menu.getNbp());
}
//for (Plat menu : listeee) {
//    listImg.addAll(menu.getImage());
//}
menuN.setText("Nom:"+ list.get(Pidev3a52.j));
menuP.setText("Prix:"+Double.toString(listP.get(Pidev3a52.j)) + "Dt");
calories.setText("Calories:"+ listcalories.get(Pidev3a52.j) + "kcal" );
description.setText("Description:"+ listdescription.get(Pidev3a52.j));
nbp.setText("Nombre de plats:"+ listNbp.get(Pidev3a52.j));
//img.setText(listImg.get(Pidev3a52.j));
//imgg.setImage(new Image(listImg.get(Pidev3a52.j)));

    }

    @FXML
    private void pred(ActionEvent event) throws SQLException {
             Pidev3a52.j --;
        PlatServices ser = new PlatServices();    
        List<Plat> listeee;
        listeee = ser.afficherListe1(Pidev3a52.id);
     
           if( Pidev3a52.j == -1)
                Pidev3a52.j = listeee.size()-1;
            ObservableList<String> list = FXCollections.observableArrayList();
            ObservableList<Double> listP = FXCollections.observableArrayList();
            ObservableList<String> listcalories = FXCollections.observableArrayList();
            ObservableList<String> listdescription  = FXCollections.observableArrayList();
            ObservableList<Integer> listNbp  = FXCollections.observableArrayList();
         //  ObservableList<String> listImg = FXCollections.observableArrayList();

   for (Plat Plat : listeee) {
    list.addAll(Plat.getNom());
}
   for (Plat menu : listeee) {
    listP.addAll(menu.getPrix());
}
for (Plat menu : listeee) {
    listcalories.addAll(String.valueOf(menu.getCalories()));
}

for (Plat menu : listeee) {
    listdescription.addAll(menu.getDescription());
}
for (Plat menu : listeee) {
    listNbp.addAll(menu.getNbp());
}

//for (Plat menu : listeee) {
//    listImg.addAll(menu.getImage());
//}

menuN.setText("Nom:"+ list.get(Pidev3a52.j));
menuP.setText("Prix:"+Double.toString(listP.get(Pidev3a52.j)) + "Dt");
calories.setText("Calories:"+ listcalories.get(Pidev3a52.j) + "kcal" );
description.setText("Description:"+ listdescription.get(Pidev3a52.j));
nbp.setText("Nombre de plats:"+ listNbp.get(Pidev3a52.j));
//img.setText(listImg.get(Pidev3a52.j));
//imgg.setImage(new Image(listImg.get(Pidev3a52.j)));

    }
  
    /****************************************************************************************************** */ 
  /*  private void genererQRCode(String nomPlat) throws WriterException, IOException, DocumentException, com.google.zxing.WriterException {
    // création du contenu du QR code
    String contenu = "Nom du plat : " + nomPlat;

    // création de l'image du QR code
    int taille = 250; // taille de l'image en pixels
        com.google.zxing.common.BitMatrix matrice = new MultiFormatWriter().encode(contenu, BarcodeFormat.QR_CODE, taille, taille);
    BufferedImage image = MatrixToImageWriter.toBufferedImage(matrice);

    // ajout de l'image au document PDF
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("C:\\chemin\\vers\\le\\fichier\\pdf"));
    document.open();
    Image qrCode = Image.getInstance(image, null);
    qrCode.setAlignment(Element.ALIGN_CENTER);
    document.add(qrCode);
    document.close();
}
*/
    /********************************************************************************************************* */


}
