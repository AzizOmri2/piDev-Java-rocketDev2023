/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Pack;
import Entities.Promotion;
import Services.PackService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class IndexPackAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TableView<Pack> packTable;
         @FXML
    private TableColumn<Pack,Integer> id;
      @FXML
    private TableColumn<Pack,String> type_pack;

    @FXML
    private TableColumn<Pack,Double> montantPack;

    @FXML
    private TableColumn<Pack,Integer> dureePack;

    @FXML
    private TableColumn<Pack,String> descriptionPack;

    @FXML
    private TableColumn<Pack,Integer> placesPack;

    @FXML
    private TableColumn<Pack,Integer> disponibilitePack;
     @FXML
    private TableColumn<Pack, Void> action;
    ObservableList<Pack> obslistsp = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Load();
    }    
    @FXML
    public void Load() {
        PackService Sps = new PackService();
            try {
                Sps.getAll().stream().forEach((p) -> {
                    obslistsp.add(p);
                }); } catch (SQLException ex) {
                Logger.getLogger(IndexPackAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type_pack.setCellValueFactory(new PropertyValueFactory<>("typePack"));
          type_pack.setCellFactory(TextFieldTableCell.forTableColumn());
        type_pack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, String> event) {
                Pack a = event.getRowValue();
                a.setTypePack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        });
        montantPack.setCellValueFactory(new PropertyValueFactory<>("montantPack"));
         montantPack.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        montantPack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, Double> event) {
                Pack a = event.getRowValue();
                a.setMontantPack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        }); 
        dureePack.setCellValueFactory(new PropertyValueFactory<>("dureePack"));
          dureePack.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        dureePack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, Integer> event) {
                Pack a = event.getRowValue();
                a.setDureePack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        });
        descriptionPack.setCellValueFactory(new PropertyValueFactory<>("descriptionPack"));
         descriptionPack.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionPack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, String> event) {
                Pack a = event.getRowValue();
                a.setDescriptionPack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        });
        placesPack.setCellValueFactory(new PropertyValueFactory<>("placesPack"));
         placesPack.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        placesPack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, Integer> event) {
                Pack a = event.getRowValue();
                a.setPlacesPack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        });
        disponibilitePack.setCellValueFactory(new PropertyValueFactory<>("disponibilitePack"));
          disponibilitePack.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        disponibilitePack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, Integer> event) {
                Pack a = event.getRowValue();
                a.setDisponibilitePack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        });
        packTable.setItems(obslistsp);

        action.setCellFactory(column -> {
            return new TableCell<Pack, Void>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        Pack pack = getTableView().getItems().get(getIndex());
                        obslistsp.remove(pack);
                        Sps.Supprimer(pack);
                        deleteButton.setStyle("-fx-background-color: #720000; -fx-text-fill: white;");
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                    }
                }
            };
        });

    }}
