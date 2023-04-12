/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class ListReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane listReclamationPane;
    
    @FXML
    void open_addReclamation(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("ajoutReclamation.fxml"));
        listReclamationPane.getChildren().removeAll();
        listReclamationPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherReclamation();
    }    
    
    
    /*TableView Réclamation*/
    @FXML
    private TableColumn<Reclamation, String> NomUserRecCell;
    @FXML
    private TableColumn<Reclamation, String> EmailUserRecCell;
    @FXML
    private TableColumn<Reclamation, String> ObjetRecCell;
    @FXML
    private TableColumn<Reclamation, Integer> CategRecCell;
    @FXML
    private TableColumn<Reclamation, Date> DateRecCell;
    @FXML
    private TableColumn<Reclamation, String> TexteRecCell;
    @FXML
    private TableView<Reclamation> tableReclamation;
    
    
    @FXML
    private Button btnDeleteRec;
    @FXML
    private TextField txtSearchRec;
    
    
    ObservableList<Reclamation> dataReclamation = FXCollections.observableArrayList();
    
    
    public void AfficherReclamation()
    {
        ReclamationService rs = new ReclamationService();
        rs.Show().stream().forEach((c) -> {
            dataReclamation.add(c);
        });
        
        NomUserRecCell.setCellValueFactory(new PropertyValueFactory<>("nom_user_reclamation"));
        NomUserRecCell.setCellFactory(TextFieldTableCell.forTableColumn());
        NomUserRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, String> event) {
                Reclamation r = event.getRowValue();
                r.setNom_user_reclamation(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        EmailUserRecCell.setCellValueFactory(new PropertyValueFactory<>("email_user_reclamation"));
        EmailUserRecCell.setCellFactory(TextFieldTableCell.forTableColumn());
        EmailUserRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, String> event) {
                Reclamation r = event.getRowValue();
                r.setEmail_user_reclamation(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        ObjetRecCell.setCellValueFactory(new PropertyValueFactory<>("objet_reclamation"));
        ObjetRecCell.setCellFactory(TextFieldTableCell.forTableColumn());
        ObjetRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, String> event) {
                Reclamation r = event.getRowValue();
                r.setObjet_reclamation(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        CategRecCell.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        CategRecCell.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        CategRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, Integer> event) {
                Reclamation r = event.getRowValue();
                r.setCategory_id(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        DateRecCell.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        DateRecCell.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {
            private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            @Override
            public String toString(Date object) {
                return dateFormat.format(object);
            }

            @Override
            public Date fromString(String string) {
                try {
                    // Parse the string into a Date object using the defined format
                    return dateFormat.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // If the string can't be parsed, return null
                    return null;
                }
            }
        }));
        DateRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, Date> event) {
                Reclamation r = event.getRowValue();
                r.setDate_reclamation(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        TexteRecCell.setCellValueFactory(new PropertyValueFactory<>("texte_reclamation"));
        TexteRecCell.setCellFactory(TextFieldTableCell.forTableColumn());
        TexteRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, String> event) {
                Reclamation r = event.getRowValue();
                r.setTexte_reclamation(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        tableReclamation.setItems(dataReclamation);
    }
    
    
    @FXML
    private void supprimerReclamation(ActionEvent event) throws SQLException {
        ReclamationService rs = new ReclamationService();
        
        if (tableReclamation.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner la réclamation à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette réclamation ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID du planning sélectionnée dans la vue de la table
            int id = tableReclamation.getSelectionModel().getSelectedItem().getId();

            // Supprimer la réclamation de la base de données
            rs.supprimer(id);
            // Rafraîchir la liste de données
            dataReclamation.clear();
            AfficherReclamation();
            // Rafraîchir la vue de la table
            tableReclamation.refresh();
        }
    }
    
}
