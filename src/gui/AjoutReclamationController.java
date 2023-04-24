/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CategoryReclamation;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.CategoryReclamationService;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane addReclamationPane;
    
    
    @FXML
    void return_ListReclamation()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listReclamation.fxml"));
        addReclamationPane.getChildren().removeAll();
        addReclamationPane.getChildren().setAll(fxml);
    }
    
    
    
    CategoryReclamationService crs = new CategoryReclamationService();
    List<CategoryReclamation> category = crs.Show();
    private int categoryId;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for(CategoryReclamation cr : category){
            textCategRec.getItems().add(cr.getNom_category());
            valuesMap.put(cr.getNom_category(),cr.getId());
        }
        
        textCategRec.setOnAction(event ->{
            String SelectedOption = textCategRec.getValue();
            int SelectedValue = valuesMap.get(SelectedOption);
            categoryId = SelectedValue;
        });

    }    
    
    
    /*Formulaire AjoutReclamation*/
    @FXML
    private TextField textNomUserRec;
    @FXML
    private TextField textEmailUserRec;
    @FXML
    private TextField textObjetRec;
    @FXML
    private ComboBox<String> textCategRec;
    @FXML
    private DatePicker txtDateRec;
    @FXML
    private TextArea textTexteRec;
    
    
    @FXML
    private Button btnClearRec;
    @FXML
    private Button btnAddRec;
    
    
    @FXML
    private void AjoutReclamation(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddRec){
            if (textNomUserRec.getText().isEmpty() || textEmailUserRec.getText().isEmpty() || textObjetRec.getText().isEmpty()
                    || textCategRec.getValue().isEmpty() || textTexteRec.getText().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre planning.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterReclamation();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre réclamation a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsReclamation();
            }
        }
        if(event.getSource() == btnClearRec){
            clearFieldsReclamation();
        }
    }
    
    
    @FXML
    private void clearFieldsReclamation() {
        textNomUserRec.clear();
        textEmailUserRec.clear();
        textObjetRec.clear();
        textTexteRec.clear();
        txtDateRec.getEditor().clear();
    }
    
    
    private void ajouterReclamation() {
        
         // From Formulaire
        String nomUserRec = textNomUserRec.getText();
        String emailUserRec = textEmailUserRec.getText();
        String objetRec = textObjetRec.getText();
        int categRec = Integer.parseInt(textCategRec.getValue());
        Date dateReclamation = null;
        try {
            LocalDate localDate = txtDateRec.getValue();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                dateReclamation = Date.from(instant);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        String texteRec = textTexteRec.getText();
        
        
        Reclamation r = new Reclamation(
                categRec, objetRec, texteRec, dateReclamation, nomUserRec, emailUserRec);
        ReclamationService rs = new ReclamationService();
        rs.ajouter(r);
    }
    
    
    @FXML
    private void sendMail() {
        // Set the SMTP host and port for sending the email
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "arco.sc0156@gmail.com";
        String password = "hghseksuroiqviag";

        // Set the properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS encryption

        // Create a new email session using the specified properties
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            Message msg = new MimeMessage(session);

            // Set the "From" address for the email
            // msg.setFrom(new InternetAddress("ahmed.benabid2503@gmail.com"));
            // Add the "To" address for the email (including the recipient's name)
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(textEmailUserRec.getText()));

            // Set the subject and body text for the email
            msg.setSubject("Demande d'une charge.");
            msg.setText("Mr " + textNomUserRec.getText() + ", j'ai besoin d'une charge le plus tôt possible et merci!");
            // Create an alert to notify the user that the email was sent successfully

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation d'envoie");
            alert.setHeaderText("Voulez-vous envoyez ce mail à " + textNomUserRec.getText() + " ?");
            alert.setContentText("Cette action est requise.");
          
            // Show the confirmation dialog and wait for the user's response
            Optional<ButtonType> result = alert.showAndWait();

            // Send the email
            if (result.get() == ButtonType.OK) {

               

                Transport.send(msg);

               
            } else {
                // Close the dialog and do nothing
                alert.close();
               
            }

            // Print a message to the console to indicate that the email was sent successfully
        } catch (AddressException e) {
            // Create an alert to notify the user that there was an error with the email address
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
    
}
