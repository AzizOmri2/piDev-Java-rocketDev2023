/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import blog.Entities.Post;
import blog.GUI.BackOfficeHomeController;
import blog.Services.PostService;
import javafx.application.Application;
import java.io.IOException;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ahmed Ben Abid
 */
public class Blog extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        //
      launch(args);
//Post post1 = new Post("dhafer","hello ","image",4);
//ChargeService chs= new ChargeService();
      //PostService ps=new PostService();
//        CommentService cs = new CommentService();
//ps.create(post1);
//System.out.println(ms.getAll());
//ps.delete(2);
//System.out.println(ps.getAll());
//ms.create(m1);
//System.out.println(ms.getOneById(7));
//ms.deleteAll();
//System.out.println(ms.getOneById(7));
//fs.create(f1);
//ms.create(m1);
//chs.createe(ch1,ms,22,8);
//System.out.println(chs.getOneById(27));
//chs.update(ch2,27);
//System.out.println(chs.getOneById(27));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
      //  FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/backOfficeHome.fxml"));
      FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/ShowPosts.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("GUI/navsidebar.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setHeight(950);
        primaryStage.setFullScreen(false);
         
        primaryStage.show();
//       String fileNamee = "../Sound/hmf.mp3";
// BackOfficeHomeController boc = new BackOfficeHomeController();
//boc.playSound(fileNamee);
    }

}
