/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
      //  Parent root =FXMLLoader.load(getClass().getResource("MenuDynamicDevelopers.fxml"));
       // Parent root =FXMLLoader.load(getClass().getResource("Back.fxml"));
               Parent root =FXMLLoader.load(getClass().getResource("FXML_Menu.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("sportifly");
            primaryStage.setTitle("sportifly application");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
}
