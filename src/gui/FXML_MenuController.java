/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Menu;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import services.MenuServices;
import utils.MyDB;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
  
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_MenuController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPlats;
    @FXML
    private Button btnReservations;
    @FXML
    private Pane paneMenus;
    @FXML
    private VBox Items;
    @FXML
    private Pane paneHome;
    @FXML
    private Pane panePlats;
    @FXML
    private Pane paneReservations;
    @FXML
    private Button Refresh;
     @FXML
    private Button Afficher;
    @FXML
    private Button Ajout;
     @FXML
    private Button Modifier;
     @FXML
    private Button retour;
     @FXML
    private  TableColumn nom;
     @FXML
    private  TableColumn description;
      @FXML
    private  TableView tvMenu;
             
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
       btnReservations.setOnAction((ActionEvent event) -> {
            GoToReservation();
    }); 
                   
        Afficher.setOnAction((ActionEvent event) -> {
        showActions();
    });
        Ajout.setOnAction((ActionEvent event) -> {
            GoToAjout();
    });
       Refresh.setOnAction((ActionEvent event) -> {
            ShowListe();
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
            root = FXMLLoader.load(getClass().getResource("FXML_AjouterMenu.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)Ajout.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
      private void GoToModifier(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_ModifierMenu.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)Modifier.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_ModifierMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     */
  
      
 @FXML
  public  ObservableList<Menu> getMenuList() {
         conx = MyDB.getInstance().getConx();
        ObservableList<Menu> MenuList = FXCollections.observableArrayList();
        try {
                String query2="SELECT * from menu ";
                PreparedStatement smt = conx.prepareStatement(query2);
                Menu m;
                ResultSet rs= smt.executeQuery();
            while(rs.next()){
         m = new Menu(rs.getInt("id"), rs.getString("categories"), rs.getString("descriptionmenu"), rs.getString("user_id"), rs.getInt("nbplats") );
                MenuList.add(m);
            }
                System.out.println(MenuList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return MenuList;
   
    }
 
     @FXML
         public void ShowListe(){
         ObservableList<Menu> list = getMenuList();
         nom.setCellValueFactory(new PropertyValueFactory<>("categories"));
         description.setCellValueFactory(new PropertyValueFactory<>("descriptionmenu"));
        tvMenu.setItems(list);
     }
       
@FXML
public void showActions() {
    ObservableList<Menu> list = getMenuList();
    nom.setCellValueFactory(new PropertyValueFactory<>("categories"));
    description.setCellValueFactory(new PropertyValueFactory<>("descriptionmenu"));

    TableColumn<Menu, Void> colBtn = new TableColumn("Actions");

    Callback<TableColumn<Menu, Void>, TableCell<Menu, Void>> cellFactory = new Callback<TableColumn<Menu, Void>, TableCell<Menu, Void>>() {
        @Override
        public TableCell<Menu, Void> call(final TableColumn<Menu, Void> param) {
            final TableCell<Menu, Void> cell = new TableCell<Menu, Void>() {

                private final Button btn = new Button("Supprimer");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Menu data = getTableView().getItems().get(getIndex());
                        SupprimerMenu(data);
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

    TableColumn<Menu, Void> colBtn2 = new TableColumn("Actions");

    Callback<TableColumn<Menu, Void>, TableCell<Menu, Void>> cellFactory2 = new Callback<TableColumn<Menu, Void>, TableCell<Menu, Void>>() {
        @Override
        public TableCell<Menu, Void> call(final TableColumn<Menu, Void> param) {
            final TableCell<Menu, Void> cell = new TableCell<Menu, Void>() {

                private final Button btn = new Button("Modifier");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Menu data = getTableView().getItems().get(getIndex());
                            ModifierMenu(data);
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

    tvMenu.setItems(list);

    tvMenu.getColumns().addAll(colBtn, colBtn2);

}

  private void SupprimerMenu(Menu m) {
    MenuServices u=new MenuServices();
        try {
            u.Supprimer(m);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXML_MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ShowListe();
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("EnergyBox :: Error Message");
    alert.setHeaderText(null);
    alert.setContentText("Menu supprimé");
    alert.showAndWait();
}
   
//Modifier 
 /* @FXML
public void showM() {
    ObservableList<Menu> list = getMenuList();
    nom.setCellValueFactory(new PropertyValueFactory<>("categories"));
    description.setCellValueFactory(new PropertyValueFactory<>("descriptionmenu"));
    
    TableColumn<Menu, Void> colBtn = new TableColumn("Actions");

    Callback<TableColumn<Menu, Void>, TableCell<Menu, Void>> cellFactory = new Callback<TableColumn<Menu, Void>, TableCell<Menu, Void>>() {
        @Override
        public TableCell<Menu, Void> call(final TableColumn<Menu, Void> param) {
            final TableCell<Menu, Void> cell = new TableCell<Menu, Void>() {

                private final Button btn = new Button("Modifier");

                {
                  btn.setOnAction((ActionEvent event) -> {
                  Menu data = getTableView().getItems().get(getIndex());
                    ModifierMenu(data);
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

    tvMenu.setItems(list);
    tvMenu.getColumns().addAll(colBtn);
   
}
  */   
  private void ModifierMenu(Menu m) {
    // chargement de la vue FXML pour la modification du menu
    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ModifierMenu.fxml"));
    Parent root;
    try {
        root = loader.load();
    } catch (IOException ex) {
        Logger.getLogger(FXML_MenuController.class.getName()).log(Level.SEVERE, null, ex);
        return;
    }
    
    // récupération du contrôleur de la vue FXML pour la modification du menu
    FXML_ModifierMenuController controller = loader.getController();
    
    // passage des détails du menu sélectionné au contrôleur
    controller.setMenu(m);
    
    // création d'une nouvelle fenêtre pour la modification du menu
    Stage stage = new Stage();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
   
  
  
  
  

  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
   
 
     
    
     

     
     
     
     
     
/*  ************************************************************************************  
        //Afficher bl bouttons modifier kodm kol menu :
         public void afficherMenu(){
         try {
            String req = "SELECT * FROM `menu`";
            stm = conx.createStatement();
             ResultSet rs = stm.executeQuery(req);
             List<Menu> menus = new ArrayList<Menu>();
            while(rs.next()){
                Menu m = new Menu(rs.getInt("id"), //or rst.getInt(1)
                    rs.getString("categories"),
                    rs.getString(3));
               menus.add(m);
            }
          Items.getChildren().clear();
            for (Menu m : menus) {
               Label label = new Label(m.getCategories() + ": " + m.getDescriptionmenu());
            Button buttonModify = new Button("Modifier");
            buttonModify.setId(Integer.toString(m.getId())); // set the ID of the button to the ID of the associated Menu object
            System.out.println("Button ID: " + buttonModify.getId());
           
            System.out.println("Event registered for button " + m.getId()); // add this line
            HBox hbox = new HBox(label, buttonModify);
            Items.getChildren().add(hbox);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
      //End of afficher bl buttons modifier 


  /* 
    public void afficherMenu(){
         try {
            String req = "SELECT * FROM `menu`";
            stm = conx.createStatement();
             ResultSet rs = stm.executeQuery(req);
             List<Menu> menus = new ArrayList<Menu>();
            while(rs.next()){
                Menu m = new Menu(rs.getInt("id"), //or rst.getInt(1)
                    rs.getString("categories"),
                    rs.getString(3));
               menus.add(m);
            }
          Items.getChildren().clear();
            for (Menu m : menus) {
                Label label = new Label(m.getCategories() + ": " + m.getDescriptionmenu());
                Items.getChildren().add(label);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

        
      
************************************************************************************   */     

    
      
   

    
   
   