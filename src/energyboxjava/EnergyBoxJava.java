/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package energyboxjava;

import Entities.Abonnement;
import Entities.Pack;
import Entities.Promotion;
import Services.AbonnementService;
import Services.PackService;
import Services.PromotionService;
import java.sql.SQLException;
import utils.MyDB;
import java.sql.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
/**
 *
 * @author wiem
 */
public class EnergyBoxJava extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        launch(args);
        // TODO code application logic here
        MyDB db2 = MyDB.getInstance();
        MyDB db3 = MyDB.getInstance();
         Date dateExpiration = Date.valueOf("2022-02-20");
        Promotion pr1 = new Promotion("code",0.20,dateExpiration);
         Promotion pr2 = new Promotion("code2",0.20,dateExpiration);
        PromotionService prm = new PromotionService();
        //prm.create(pr1);
        //prm.update(pr2, 3);
        prm.delete(3);
       System.out.println(prm.getAll());
        Pack p1 = new Pack("new pack", 100f, 1, "new pack", 0, 10);
        Pack p2 = new Pack("new pack", 100f, 1, "new pack", 0, 10);
        PackService ps = new PackService();
         //ps.create(p1);
        // ps.update(p1, 4);
        //System.out.println(ps.getAll());
        // System.out.println(ps.getOneById(4));
        //ps.delete(4);
        Date dateAchat = Date.valueOf("2022-02-20");
        Date dateFin = Date.valueOf("2022-03-20");
        Abonnement a1 = new Abonnement(dateAchat, dateFin, "a", "2023", 100.f, 2, 19);
       Abonnement a3 = new Abonnement(dateAchat, dateFin, "a", "2023", 10.f, 2 , 19);
        AbonnementService ab = new AbonnementService();
       // ab.create(a1);
       // ab.update(a3, 28);
        //ab.delete(2);
      //  System.out.println(ab.getAllUserPackAbonnement());

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

      // FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/gestionAbonnement.fxml"));
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/IndexClientAbonnement.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       // scene.getStylesheets().add(getClass().getResource("../GUI/backAbonnement.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
