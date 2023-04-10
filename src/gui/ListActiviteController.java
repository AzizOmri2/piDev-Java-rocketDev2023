/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Activite;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import services.ActiviteService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class ListActiviteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane listActivitePane;
    
    @FXML
    void open_addActivite(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("ajoutActivite.fxml"));
        listActivitePane.getChildren().removeAll();
        listActivitePane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherActivites();
    }  
    
    
    /*TableView Activité*/
    @FXML
    private TableColumn<Activite, String> DescriptionActCell;
    @FXML
    private TableColumn<Activite, String> DiffActCell;
    @FXML
    private TableColumn<Activite, String> DureeActCell;
    @FXML
    private TableColumn<Activite, String> ImageActCell;
    @FXML
    private TableColumn<Activite, String> NomActCell;
    @FXML
    private TableColumn<Activite, String> TenueActCell;
    @FXML
    private TableView<Activite> tableActivite;
    
    
    @FXML
    private Button btnDeleteAct;
    @FXML
    private TextField txtSearchAct;
    
    
    ObservableList<Activite> data = FXCollections.observableArrayList();
    
    
    public void AfficherActivites()
    {
        ActiviteService as = new ActiviteService();
        as.Show().stream().forEach((p) -> {
            data.add(p);
        });
        NomActCell.setCellValueFactory(new PropertyValueFactory<>("nom_activite"));
        NomActCell.setCellFactory(TextFieldTableCell.forTableColumn());
        NomActCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activite, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Activite, String> event) {
                Activite a = event.getRowValue();
                a.setNom_activite(event.getNewValue());
                ActiviteService as = new ActiviteService();
                as.modifier(a);
            }
        });
        DureeActCell.setCellValueFactory(new PropertyValueFactory<>("duree_activite"));
        DureeActCell.setCellFactory(TextFieldTableCell.forTableColumn());
        DureeActCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activite, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Activite, String> event) {
                Activite a = event.getRowValue();
                a.setDuree_activite(event.getNewValue());
                ActiviteService as = new ActiviteService();
                as.modifier(a);
            }
        });
        TenueActCell.setCellValueFactory(new PropertyValueFactory<>("tenue_activite"));
        TenueActCell.setCellFactory(TextFieldTableCell.forTableColumn());
        TenueActCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activite, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Activite, String> event) {
                Activite a = event.getRowValue();
                a.setTenue_activite(event.getNewValue());
                ActiviteService as = new ActiviteService();
                as.modifier(a);
            }
        });
        DiffActCell.setCellValueFactory(new PropertyValueFactory<>("difficulte_activite"));
        DiffActCell.setCellFactory(TextFieldTableCell.forTableColumn());
        DiffActCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activite, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Activite, String> event) {
                Activite a = event.getRowValue();
                a.setDifficulte_activite(event.getNewValue());
                ActiviteService as = new ActiviteService();
                as.modifier(a);
            }
        });
        ImageActCell.setCellValueFactory(new PropertyValueFactory<>("image_activite"));
        ImageActCell.setCellFactory(TextFieldTableCell.forTableColumn());
        ImageActCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activite, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Activite, String> event) {
                Activite a = event.getRowValue();
                a.setImage_activite(event.getNewValue());
                ActiviteService as = new ActiviteService();
                as.modifier(a);
            }
        });
        DescriptionActCell.setCellValueFactory(new PropertyValueFactory<>("description_activite"));
        DescriptionActCell.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionActCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activite, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Activite, String> event) {
                Activite a = event.getRowValue();
                a.setDescription_activite(event.getNewValue());
                ActiviteService as = new ActiviteService();
                as.modifier(a);
            }
        });
        
        tableActivite.setItems(data);
    }
    
    @FXML
    private void supprimerActivite(ActionEvent event) throws SQLException {
        ActiviteService as = new ActiviteService();
        
        if (tableActivite.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner l'activité à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette activité ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de l'activité sélectionnée dans la vue de la table
            int id = tableActivite.getSelectionModel().getSelectedItem().getId();

            // Supprimer l'activité de la base de données
            as.supprimer(id);
            // Rafraîchir la liste de données
            data.clear();
            AfficherActivites();
            // Rafraîchir la vue de la table
            tableActivite.refresh();
        }
    }
    
}
