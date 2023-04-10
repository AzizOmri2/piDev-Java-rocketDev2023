/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Menu;
import entites.Plat;
import entites.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.PlatServices;
import services.ReservationServices;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_ReservationController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPlats;
    @FXML
    private Button btnReservations;
    @FXML
    private Pane panePlats;
    @FXML
    private VBox Items;
    @FXML
    private Pane paneMenus;
    @FXML
    private Pane paneReservations;
    @FXML
    private Pane paneHome;
    
    @FXML
    private Button refresh;
    @FXML
    private Button Ajout;
    
@FXML
private TableColumn<Reservation, Integer> idreservation;

@FXML
private TableColumn<Reservation, Integer> idplat;

@FXML
private TableColumn<Reservation, Date> date;

@FXML
private TableColumn<Reservation, String> userid;

    @FXML
    private  TableView tvReservation;
      
    public Connection conx;
    public Statement stm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     // TODO
         try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }   
      btnHome.setOnAction((ActionEvent event) -> {
            GoToRestau();
    }); 
       btnPlats.setOnAction((ActionEvent event) -> {
            GoToPlat();
    }); 
      btnMenus.setOnAction((ActionEvent event) -> {
            GoToMenu();
    }); 
      refresh.setOnAction((ActionEvent event) -> {
        showRec();
    });
     /*  Ajout.setOnAction((ActionEvent event) -> {
            GoToAjout();
    });*/
    }    
   private void GoToRestau(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Restaurant.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)btnHome.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private void GoToPlat(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Plat.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)btnPlats.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private void GoToMenu(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Menu.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)btnMenus.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
 @FXML
  public  ObservableList<Reservation> getReservationList() {
         conx = MyDB.getInstance().getConx();
        ObservableList<Reservation> ReservationList = FXCollections.observableArrayList();
        try {
                String query2="SELECT * from reservation ";
                PreparedStatement smt = conx.prepareStatement(query2);
                Reservation R;
                ResultSet rs= smt.executeQuery();
            while(rs.next()){
         R = new Reservation(rs.getInt("id"), rs.getInt("idplat_id"), rs.getDate("date"), rs.getString("userid"));
                ReservationList.add(R);
            }
                System.out.println(ReservationList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ReservationList;
   
    }
 
     @FXML
         public void ShowListe(){
         ObservableList<Reservation> list = getReservationList();
         idreservation.setCellValueFactory(new PropertyValueFactory<>("id"));
         idplat.setCellValueFactory(new PropertyValueFactory<>("idplat_id"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
         userid.setCellValueFactory(new PropertyValueFactory<>("userid"));
        tvReservation.setItems(list);
     }   
 
 @FXML
  public void showRec() {
    ObservableList<Reservation> list = getReservationList();
    idreservation.setCellValueFactory(new PropertyValueFactory<>("id"));
    idplat.setCellValueFactory(new PropertyValueFactory<>("idplat_id"));
    date.setCellValueFactory(new PropertyValueFactory<>("date"));
    userid.setCellValueFactory(new PropertyValueFactory<>("userid"));
    
    TableColumn<Reservation, Void> colBtn = new TableColumn("Actions");

    Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>> cellFactory = new Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>>() {
        @Override
        public TableCell<Reservation, Void> call(final TableColumn<Reservation, Void> param) {
            final TableCell<Reservation, Void> cell = new TableCell<Reservation, Void>() {

                private final Button btn = new Button("Supprimer");

                {
                  btn.setOnAction((ActionEvent event) -> {
                  Reservation data = getTableView().getItems().get(getIndex());
                   SupprimerReservation(data);
                     });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        }
    };

    colBtn.setCellFactory(cellFactory);

    tvReservation.setItems(list);
    tvReservation.getColumns().addAll(colBtn);
}
     
  private void SupprimerReservation(Reservation r) {
    ReservationServices u=new ReservationServices();
        try {
            u.Supprimer(r);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXML_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ShowListe();
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("EnergyBox :: Error Message");
    alert.setHeaderText(null);
    alert.setContentText("Reservation supprimé");
    alert.showAndWait();
}    
  /*  private void GoToAjout(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_AjouterReservation.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)Ajout.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
    */     
}
