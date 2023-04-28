/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.beans.property.SimpleStringProperty;
import Entity.Competition;
import Entity.Ticket;
import Services.CompetitionServices;
import Services.TicketServices;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Salima
 */
public class ViewTicketsController implements Initializable {

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

     ObservableList<Ticket> data = FXCollections.observableArrayList();
     private TicketServices ccrd =new TicketServices();
    @FXML
    private TableColumn<Ticket, String> ticketNomComp;
    @FXML
    private TableColumn<Ticket, String> descTicket;
    @FXML
    private Button btSupp;
    @FXML
    private Button btAjout;
    @FXML
    private TableView<Ticket> tableTickets;
    @FXML
    private TableColumn<Ticket, Integer> id;
    @FXML
    private BorderPane bp;
    @FXML
    private MenuButton menu;
    @FXML
    private MenuItem profile;
    @FXML
    private Button closW;
     public Connection conx;
     
      MyDB cnx = null;
    Statement st = null;
    @FXML
    private Button btnLoadPlanning;
    @FXML
    private TextField txtSearchPlanning;
    
  
    /**
     * Initializes the controller class.
     */
 @Override
public void initialize(URL url, ResourceBundle rb) {
    // TODO
    data.clear();
    tableTickets.refresh();
    viewBackTicket();
}


public void viewBackTicket() {
    TicketServices ts=new TicketServices();
    ts.afficherListe().stream().forEach((t)->{
        data.add(t);
    });

    ticketNomComp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCompetition().getNomCompetition()));

    descTicket.setCellValueFactory(new PropertyValueFactory<>("description"));
    descTicket.setEditable(true);
    descTicket.setCellFactory(TextFieldTableCell.forTableColumn());
    descTicket.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ticket, String>>() {
        @Override
        public void handle(TableColumn.CellEditEvent<Ticket, String> event) {
            Ticket ticket = event.getRowValue();
            ticket.setDescription(event.getNewValue());
            TicketServices ts = new TicketServices();
            ts.modifierTicket(ticket);
        }
    });
    
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    tableTickets.setItems(data);
}


    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void AjouterTicket(ActionEvent event) throws IOException {
Parent loader = FXMLLoader.load(getClass().getResource("ajouTicket.fxml"));
    Scene scene = new Scene(loader);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
    Node source = (Node) event.getSource();
    Stage currentStage = (Stage) source.getScene().getWindow();
    currentStage.hide();
    tableTickets.refresh();
    }

    @FXML
    private void Pro(ActionEvent event) {
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

    @FXML
    private void deleteTicket(ActionEvent event) {
             if (tableTickets.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner le Ticket à supprimer");
            alert.showAndWait();
            return;
        }
          // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce Ticket ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la réclamation sélectionnée dans la vue de la table
            int id = tableTickets.getSelectionModel().getSelectedItem().getId();

            // Supprimer la réclamation de la base de données
            ccrd.supprimerTicket(id);
            // Rafraîchir la liste de données
            data.clear();
            tableTickets.refresh();
            Alert alert2=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression validée");
            alert.setHeaderText("Le Ticket séléctioné a été supprimé avec succés ");
            alert.showAndWait();
            viewBackTicket();
        }
    }

    @FXML
    private void refreshTab(ActionEvent event) {
    tableTickets.refresh();
    }
    


} 

