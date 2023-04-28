/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javax.swing.JTextField;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.*;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.scene.media.AudioClip;
import java.io.File;
import Entity.Competition;
import Services.CompetitionServices;
import Services.TicketServices;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Salima
 */
public class ViewFrontController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private MenuButton menu;
    @FXML
    private MenuItem profile;
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
    private TableView<Competition> tableCompetition;
    @FXML
    private TableColumn<Competition, String> cNom;
    @FXML
    private TableColumn<Competition, Integer> cFrais;
    @FXML
    private TableColumn<Competition, Date> cDate;
    @FXML
    private TableColumn<Competition, Integer> cNbMax;
    @FXML
    private TableColumn<Competition, String> cEtat;
    @FXML
    private TableColumn<Competition, Integer> cNbParticipants;
    @FXML
    private TableColumn<Competition, Integer> cId;
    @FXML
    private Button btReserver;
    @FXML
    private Button closW;
    private CompetitionServices ccrd =new CompetitionServices();
    ObservableList<Competition> data = FXCollections.observableArrayList();
    
      MyDB cnx = null;
    Statement st = null;
    TicketServices tckS=new TicketServices();
    CompetitionServices cS = new CompetitionServices();   
    @FXML
    private TextField backIndex;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO.
      tableCompetition.refresh();
      ViewFront();
    }    
    
    public void ViewFront(){
        try{
        String rq= "SELECT *FROM competition";
        Statement st=MyDB.getInstance().getConx().createStatement();
        ResultSet rs = st.executeQuery(rq);
            int nb=0;
            while (rs.next()) {
                nb++;
                Competition competition = new Competition(rs.getInt("id"),rs.getString("nom_competition"),
                                                         (int) rs.getDouble("frais_competition"),
                                                          rs.getDate("date_competition"),
                                                          rs.getInt("nbr_max_inscrit"),
                                                          rs.getString("etat_competition"),
                                                          rs.getInt("nbr_participant"));
                  data.add(competition);
        }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       
        cNom.setCellValueFactory(new PropertyValueFactory("nomCompetition"));
        cFrais.setCellValueFactory(new PropertyValueFactory("fraisCompetition"));
        cDate.setCellValueFactory(new PropertyValueFactory("dateCompetition"));
        cNbMax.setCellValueFactory(new PropertyValueFactory("nbrMaxInscrit"));
        cEtat.setCellValueFactory(new PropertyValueFactory("etatCompetition"));
        cNbParticipants.setCellValueFactory(new PropertyValueFactory("nbrParticipants"));
        cId.setCellValueFactory(new PropertyValueFactory("id"));
        tableCompetition.setItems(data);

    }

    @FXML
    private void Pro(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void GoUsers(ActionEvent event) {
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
    private void GoSponsor(ActionEvent event) {
    }

    @FXML
    private void listRestau(MouseEvent event) {
    }

    @FXML
    private void listReclamation(MouseEvent event) {
    }

    @FXML
private void reserver(ActionEvent event) throws IOException, SQLException {
    LocalDate currentDate = LocalDate.now();
    Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Competition comp = tableCompetition.getSelectionModel().getSelectedItem();
    if (comp == null) {
        // Aucune compétition sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de réservation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une compétition à réserver.");
        alert.showAndWait();
                      }
     else if (comp.getEtatCompetition().equals("non disponible")) {
    // Afficher un message indiquant que la réservation n'est pas possible
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de réservation");
    alert.setHeaderText(null);
    alert.setContentText("Erreur de réservation, cette compétition n'est plus disponible.");
    alert.showAndWait();
}
    else if ((comp.getNbrMaxInscrit()==0)) {
    // Afficher un message indiquant que la réservation n'est pas possible
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de réservation");
    alert.setHeaderText(null);
    alert.setContentText("Erreur de réservation, cette compétition n'est plus disponible.");
    alert.showAndWait();
}
    else if ((comp.getNbrMaxInscrit() > 0) ) {
        comp.setNbrMaxInscrit(comp.getNbrMaxInscrit() - 1);
        comp.setNbrParticipants(comp.getNbrParticipants() + 1);
        System.out.println (comp.toString());  
        cS.modifierCompetition(comp);
        tableCompetition.refresh();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Réservation validée");
alert.setHeaderText(null); 
alert.setContentText("Validation de la réservation, compétition réservée avec succès. Vous voulez obtenir le ticket de votre compétition ?");
Optional<ButtonType> result = alert.showAndWait();
if (result.isPresent() && result.get() == ButtonType.OK) {
   if (tckS.existeTicket(comp.getId())>=0) {
       System.out.println(tckS.existeTicket(comp.getId()));
       /////////////
       tckS.afficher(tckS.existeTicket(comp.getId()));
       sendMail();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("viewReservedTicket.fxml"));
        Parent root = loader.load();
        ViewReservedTicketController controller= loader.getController();
        controller.setTicketId(tckS.existeTicket(comp.getId()));
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
        // Cacher la fenêtre actuelle
    Node source = (Node) event.getSource();
    Stage currentStage = (Stage) source.getScene().getWindow();
    currentStage.hide();
       
        tckS.afficher(comp.getId());
    } else {
        Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.setTitle("Ticket non Disponible");
        alert2.setHeaderText(null);
        alert2.setContentText("Votre réservation a été effectuée. Veuillez attendre l'admin pour l'ajout du ticket !");
        alert2.showAndWait();
    }
}
    }
else if (comp.getDateCompetition().after(date)) {
        comp.setNbrMaxInscrit(comp.getNbrMaxInscrit() - 1);
        comp.setNbrParticipants(comp.getNbrParticipants() + 1);
        System.out.println (comp.toString());  
        cS.modifierCompetition(comp);
        tableCompetition.refresh();
       Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
alert1.setTitle("Réservation validée");
alert1.setHeaderText(null);
alert1.setContentText("Validation de la réservation, compétition réservée avec succès. Vous voulez obtenir le ticket de votre compétition ?");
 Optional<ButtonType> result1 = alert1.showAndWait();
if (result1.isPresent() && result1.get() == ButtonType.OK) {
   if (tckS.existeTicket(comp.getId())>=0) {
        tckS.afficher(tckS.existeTicket(comp.getId()));
    } else {
        Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.setTitle("Ticket non Disponible");
        alert2.setHeaderText(null);
        alert2.setContentText("Votre réservation a été effectuée. Veuillez attendre l'admin pour l'ajout du ticket !");
        alert2.showAndWait();
    }
}
//&& 
        // Ajouter à la session du user
    }
    tableCompetition.refresh();
    searchBox();
}
    @FXML
    private void closeW(MouseEvent event) {
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

    public void searchBox() throws SQLException {
   //     Competition competition= getTableView().getItems().
       FilteredList<Competition> filteredData = new FilteredList<>(FXCollections.observableArrayList(cS.afficherListe()), p -> true);
        backIndex.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(competition -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String fr = String.valueOf(competition.getFraisCompetition());
                String dt = String.valueOf(competition.getDateCompetition());
                String ins = String.valueOf(competition.getNbrMaxInscrit());
                String nbP = String.valueOf(competition.getNbrParticipants());
                


                String lowerCaseFilter = newValue.toLowerCase();
                if (competition.getEtatCompetition().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (competition.getNomCompetition().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (fr.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (dt.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (ins.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (nbP.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Competition> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCompetition.comparatorProperty());
        tableCompetition.setItems(sortedData);
    
    }
    public void sendMail() {
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
             msg.setFrom(new InternetAddress("salima.jenhani@esprit.tn"));
            // Add the "To" address for the email (including the recipient's name)
            
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mail de confirmation"));
            // Set the subject and body text for the email
            msg.setSubject("Demande d'une charge.");
            msg.setText("Mr j'ai besoin d'une charge le plus tôt possible et merci!");
            // Create an alert to notify the user that the email was sent successfully

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation d'envoie");
            alert.setHeaderText("Voulez-vous envoyez ce mail à  ?");
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
