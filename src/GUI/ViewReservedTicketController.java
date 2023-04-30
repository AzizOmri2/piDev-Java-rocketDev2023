/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.ViewFrontController;
import Entity.Competition;
import Entity.Ticket;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Salima
 */
public class ViewReservedTicketController implements Initializable {

    @FXML
    private Button btnRetour;
    @FXML
    private ImageView btRetour;
    @FXML
    private Button btnTelecharger;
    @FXML
    private TextField nomT;
    @FXML
    private TextArea descriptionT;
    @FXML
    private Button closW;

    private Connection conx;
    private Ticket ticket;
    
    /**
     * Initializes the controller class.
     */
        public void setConnection(Connection connection) {
        this.conx = connection;
    }

    public void setTicketId(int id) {
        if (this.ticket == null) {
            this.ticket = new Ticket();
        }
        this.ticket.setId(id);
    }
    
    @Override
public void initialize(URL url, ResourceBundle rb) {
    int idd = ViewFrontController.GlobalVars.idd;
    try {
        viewReservedTicket(idd);
    } catch (SQLException ex) {
        Logger.getLogger(ViewReservedTicketController.class.getName()).log(Level.SEVERE, null, ex);
    }
}



 public void viewReservedTicket(int idd) throws SQLException {
    String req = "SELECT t.id, t.description_ticket, c.nom_competition FROM ticket t JOIN competition c ON t.competition_id=c.id WHERE t.id=?";
    try {
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setInt(1, idd);
        ResultSet rs = ps.executeQuery();

        // Si le résultat est vide, on réinitialise les champs d'affichage et on affiche un message d'erreur
        if (!rs.next()) {
            nomT.setText("");
            descriptionT.setText("");
            System.out.println("Aucun résultat trouvé pour l'identifiant de ticket " + idd);
            return;
        }

        // Si le résultat n'est pas vide, on affiche les données du ticket
        Ticket t = new Ticket();
        t.setId(rs.getInt("id"));
        t.setDescription(rs.getString("description_ticket"));
        Competition competition = new Competition();
        competition.setNomCompetition(rs.getString("nom_competition"));
        t.setCompetition(competition);
        nomT.setText(t.getCompetition().getNomCompetition());
        descriptionT.setText(t.getDescription());
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}




    @FXML
    private void voirCompetition(MouseEvent event) {
    }

    @FXML
    private void viewFront(ActionEvent event) {
    }

    @FXML
    private void Telecharger(ActionEvent event) {
    }

    @FXML
    private void closeW(MouseEvent event) {
    }

    @FXML
    private void closeW(ActionEvent event) {
    }
}
