/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entites.Menu;
import entites.Plat;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.MenuServices;
import services.PlatServices;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_PlatController implements Initializable {

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
    private Button retour;
     @FXML
    private Button Ajout;
 
     @FXML
    private  TableColumn nom;
     @FXML
    private  TableColumn prix;
      @FXML
    private  TableColumn description;
      @FXML
    private  TableColumn calories;
     @FXML
    private  TableColumn etat;
     @FXML
    private  TableColumn nbp;
       @FXML
    private  TableColumn image; 
      @FXML
    private  TableColumn categories;
     @FXML
    private  TableView tvPlat;
     @FXML
    private Button test;
     @FXML
    private Button Refresh;
      @FXML
    private Button Afficher;
      
    public Connection conx;
    public Statement stm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }
         btnHome.setOnAction((ActionEvent event) -> {
            GoToRestau();
    }); 
        btnMenus.setOnAction((ActionEvent event) -> {
            GoToMenu();
    }); 
         btnReservations.setOnAction((ActionEvent event) -> {
            GoToReservation();
    }); 
        Afficher.setOnAction((ActionEvent event) -> {
            showActions();
        });

        Refresh.setOnAction((ActionEvent event) -> {
            ShowListe();
        });
     Ajout.setOnAction((ActionEvent event) -> {
           GoToAjout();
    });  
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
        private void GoToReservation(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Reservation.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)btnReservations.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    private void GoToAjout(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_AjouterPlat.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)Ajout.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
  @FXML
  public  ObservableList<Plat> getPlatList() {
         conx = MyDB.getInstance().getConx();
        ObservableList<Plat> PlatList = FXCollections.observableArrayList();
        try {
                String query2="SELECT * from plat ";
                PreparedStatement smt = conx.prepareStatement(query2);
                Plat p;
                ResultSet rs= smt.executeQuery();
            while(rs.next()){
         p = new Plat(rs.getInt("id"),rs.getInt("categories_id"), rs.getString("nom"),rs.getDouble("prix"), rs.getString("description"), rs.getString("calories"), rs.getString("etat"), rs.getString("image"),rs.getInt("nbp") );
                PlatList.add(p);
            }
                System.out.println(PlatList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return PlatList;
   
    }
  
     @FXML
         public void ShowListe(){
         ObservableList<Plat> list = getPlatList();
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         description.setCellValueFactory(new PropertyValueFactory<>("description"));
         calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
         etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         nbp.setCellValueFactory(new PropertyValueFactory<>("nbp"));
         image.setCellValueFactory(new PropertyValueFactory<>("image"));
         categories.setCellValueFactory(new PropertyValueFactory<>("categories_id"));
       
        tvPlat.setItems(list);
     }   
     
   @FXML
public void showActions() {
   ObservableList<Plat> list = getPlatList();
       nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
       description.setCellValueFactory(new PropertyValueFactory<>("description"));
       calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       nbp.setCellValueFactory(new PropertyValueFactory<>("nbp"));
       image.setCellValueFactory(new PropertyValueFactory<>("image"));
       categories.setCellValueFactory(new PropertyValueFactory<>("categories_id"));

    TableColumn<Plat, Void> colBtn = new TableColumn("Actions");

    Callback<TableColumn<Plat, Void>, TableCell<Plat, Void>> cellFactory = new Callback<TableColumn<Plat, Void>, TableCell<Plat, Void>>() {
        @Override
        public TableCell<Plat, Void> call(final TableColumn<Plat, Void> param) {
            final TableCell<Plat, Void> cell = new TableCell<Plat, Void>() {

                private final Button btn = new Button("Supprimer");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Plat data = getTableView().getItems().get(getIndex());
                        SupprimerPlat(data);
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

    TableColumn<Plat, Void> colBtn2 = new TableColumn("Actions");

    Callback<TableColumn<Plat, Void>, TableCell<Plat, Void>> cellFactory2 = new Callback<TableColumn<Plat, Void>, TableCell<Plat, Void>>() {
        @Override
        public TableCell<Plat, Void> call(final TableColumn<Plat, Void> param) {
            final TableCell<Plat, Void> cell = new TableCell<Plat, Void>() {

                private final Button btn = new Button("Modifier");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Plat data = getTableView().getItems().get(getIndex());
                            ModifierPlat(data);
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

    colBtn2.setCellFactory(cellFactory2);

    tvPlat.setItems(list);

    tvPlat.getColumns().addAll(colBtn, colBtn2);
}
     
  private void SupprimerPlat(Plat p) {
    PlatServices u=new PlatServices();
        try {
            u.Supprimer(p);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXML_PlatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ShowListe();
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("EnergyBox :: Error Message");
    alert.setHeaderText(null);
    alert.setContentText("Plat supprimé");
    alert.showAndWait();
}
  
 private void ModifierPlat(Plat m) {
    // chargement de la vue FXML pour la modification du menu
    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ModifierPlat.fxml"));
    Parent root;
    try {
        root = loader.load();
    } catch (IOException ex) {
        Logger.getLogger(FXML_PlatController.class.getName()).log(Level.SEVERE, null, ex);
        return;
    }
    
    // récupération du contrôleur de la vue FXML pour la modification du menu
    FXML_ModifierPlatController controller = loader.getController();
    
    // passage des détails du menu sélectionné au contrôleur
    controller.setPlat(m);
    
    // création d'une nouvelle fenêtre pour la modification du menu
    Stage stage = new Stage();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}   
  
 
 
 /*  
      public void afficherPlat(){
         try {
            String req = "SELECT * FROM `plat`";
            stm = conx.createStatement();
             ResultSet rs = stm.executeQuery(req);
             List<Plat> menus = new ArrayList<Plat>();
            while(rs.next()){
                Plat p = new Plat(rs.getInt("id"), //or rst.getInt(1)
                     //    rs.getInt("categories_id"),
                         rs.getString("nom"),
                         rs.getInt("prix"),
                         rs.getString("description"),
                         rs.getString("calories"),
                         rs.getString("etat"),
                         rs.getString("image"),
                        rs.getInt("nbp"));
               menus.add(p);
            }
          Items.getChildren().clear();
            for (Plat m : menus) {
                 Label label = new Label(m.getNom() + ": " + m.getPrix() + ", " + m.getDescription() + ", " + m.getCalories() + ", " + m.getEtat() + ", " + m.getImage() + ", " + m.getNbp());
                Items.getChildren().add(label);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    */
}
