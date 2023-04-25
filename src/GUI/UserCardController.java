/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class UserCardController implements Initializable {

    private int idCu;

    @FXML
    private Circle profilePicture;
    @FXML
    private Label usernameU;
    @FXML
    private Label emailU;
    @FXML
    private Button Bloquer;
    @FXML
    private Label num_telU;
    @FXML
    private Label date_nU;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LoadData();
    }

    public void LoadData() {

        UserService us = new UserService();
        try {
            User u = us.findById(this.idCu);
            if (u != null) {
                if (u.getUsername() != null) {
                    usernameU.setText(u.getUsername());
                }
                if (u.getEmail() != null) {
                    emailU.setText(u.getEmail());
                }
                if (u.getNumtel() != null) {
                    num_telU.setText(Integer.toString(u.getNumtel()));
                }
                if (u.getDate_n() != null) {
                    date_nU.setText(u.getDate_n().toString());
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int initData(int id) {
        this.idCu = id;
        this.LoadData();
        System.err.println("ena aaaaaa" + this.idCu);
        return this.idCu;

    }

}
