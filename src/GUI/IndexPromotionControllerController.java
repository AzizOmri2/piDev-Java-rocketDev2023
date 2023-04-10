/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Promotion;

import Services.PromotionService;
import java.net.URL;
import java.util.Date;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class IndexPromotionControllerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Promotion> promotionTable;
     @FXML
    private TableColumn<Promotion,Integer> id;

      @FXML
    private TableColumn<Promotion,String> code_promotion;

    @FXML
    private TableColumn<Promotion,Double> reduction_promotion;

    @FXML
    private TableColumn<Promotion,Date> date_expiration;
     @FXML
    private TableColumn<Promotion, Void> action;
     
     ObservableList<Promotion> obslistsp = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Load();
        } catch (SQLException ex) {
            Logger.getLogger(IndexPromotionControllerController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    
      @FXML
    public void Load() throws SQLException {
        PromotionService Sps = new PromotionService();
            Sps.getAll().stream().forEach((p) -> {
            obslistsp.add(p);
        });
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        code_promotion.setCellValueFactory(new PropertyValueFactory<>("codePromotion"));
        code_promotion.setCellFactory(TextFieldTableCell.forTableColumn());
        code_promotion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Promotion, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Promotion, String> event) {
                Promotion a = event.getRowValue();
                a.setCodePromotion(event.getNewValue());
                PromotionService as = new PromotionService();
                as.modifier(a);
            }
        });
        reduction_promotion.setCellValueFactory(new PropertyValueFactory<>("reductionPromotion"));
  reduction_promotion.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        reduction_promotion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Promotion, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Promotion, Double> event) {
                Promotion a = event.getRowValue();
                a.setReductionPromotion(event.getNewValue());
                PromotionService as = new PromotionService();
                as.modifier(a);
            }
        });
        date_expiration.setCellValueFactory(new PropertyValueFactory<>("dateExpiration"));
         date_expiration.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {
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
        date_expiration.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Promotion, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Promotion, Date> event) {
                Promotion a = event.getRowValue();
                a.setDateExpiration(event.getNewValue());
                PromotionService as = new PromotionService();
                as.modifier(a);
            }
        });
        promotionTable.setItems(obslistsp);
        action.setCellFactory(column -> {
            return new TableCell<Promotion, Void>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        Promotion promotion = getTableView().getItems().get(getIndex());
                        obslistsp.remove(promotion);
                        Sps.Supprimer(promotion);
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

    }
    
    
}
