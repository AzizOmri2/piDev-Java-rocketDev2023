/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Cours;
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
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import services.CoursService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class ListCoursController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane listCoursPane;
    
    @FXML
    void open_addCours(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("ajoutCours.fxml"));
        listCoursPane.getChildren().removeAll();
        listCoursPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherCours();
    }  
    
    
    /*TableView Cours*/
    @FXML
    private TableColumn<Cours, String> NomCoursCell;
    @FXML
    private TableColumn<Cours, Float> PrixCoursCell;
    @FXML
    private TableColumn<Cours, String> NomCoachCoursCell;
    @FXML
    private TableColumn<Cours, Integer> AgeMinCoursCell;
    @FXML
    private TableColumn<Cours, String> DescriptionCoursCell;
    @FXML
    private TableView<Cours> tableCours;
    
    
    @FXML
    private Button btnDeleteCours;
    @FXML
    private TextField txtSearchCours;
    
    
    ObservableList<Cours> dataCours = FXCollections.observableArrayList();  
    
    
    public void AfficherCours()
    {
        CoursService cs = new CoursService();
        cs.Show().stream().forEach((c) -> {
            dataCours.add(c);
        });
        
        NomCoursCell.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        NomCoursCell.setCellFactory(TextFieldTableCell.forTableColumn());
        NomCoursCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cours, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Cours, String> event) {
                Cours c = event.getRowValue();
                c.setNom_cours(event.getNewValue());
                CoursService cs = new CoursService();
                cs.modifier(c);
            }
        });
        PrixCoursCell.setCellValueFactory(new PropertyValueFactory<>("prix_cours"));
        PrixCoursCell.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Float>() {
            @Override
            public String toString(Float object) {
                return object.toString();
            }

            @Override
            public Float fromString(String string) {
                return Float.valueOf(string);
            }
        }));
        PrixCoursCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cours, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Cours, Float> event) {
                Cours c = event.getRowValue();
                c.setPrix_cours(event.getNewValue());
                CoursService cs = new CoursService();
                cs.modifier(c);
            }
        });
        NomCoachCoursCell.setCellValueFactory(new PropertyValueFactory<>("nom_coach"));
        NomCoachCoursCell.setCellFactory(TextFieldTableCell.forTableColumn());
        NomCoachCoursCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cours, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Cours, String> event) {
                Cours c = event.getRowValue();
                c.setNom_coach(event.getNewValue());
                CoursService cs = new CoursService();
                cs.modifier(c);
            }
        });
        AgeMinCoursCell.setCellValueFactory(new PropertyValueFactory<>("age_min_cours"));
        AgeMinCoursCell.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        AgeMinCoursCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cours, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Cours, Integer> event) {
                Cours c = event.getRowValue();
                c.setAge_min_cours(event.getNewValue());
                CoursService cs = new CoursService();
                cs.modifier(c);
            }
        });
        DescriptionCoursCell.setCellValueFactory(new PropertyValueFactory<>("description_cours"));
        DescriptionCoursCell.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionCoursCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cours, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Cours, String> event) {
                Cours c = event.getRowValue();
                c.setDescription_cours(event.getNewValue());
                CoursService cs = new CoursService();
                cs.modifier(c);
            }
        });
        tableCours.setItems(dataCours);
    }
    
    @FXML
    private void supprimerCours(ActionEvent event) throws SQLException {
        CoursService cs = new CoursService();
        
        if (tableCours.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner le cours à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce cours ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID du cours sélectionnée dans la vue de la table
            int id = tableCours.getSelectionModel().getSelectedItem().getId();

            // Supprimer le cours de la base de données
            cs.supprimer(id);
            // Rafraîchir la liste de données
            dataCours.clear();
            AfficherCours();
            // Rafraîchir la vue de la table
            tableCours.refresh();
        }
    }
    
}
