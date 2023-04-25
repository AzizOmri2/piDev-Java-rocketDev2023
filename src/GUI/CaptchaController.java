/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class CaptchaController implements Initializable {

    @FXML
    private Canvas captchaCanvas;
    @FXML
    private TextField captchaField;
private String captchaText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          captchaText = generateCaptchaText(5);
        drawCaptcha(captchaText);
    }    

     private String generateCaptchaText(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
         Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    private void drawCaptcha(String captchaText) {
        GraphicsContext gc = captchaCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setFill(Color.BLACK);
        gc.setFont(gc.getFont().font(20));
        gc.fillText(captchaText, 30, 35);
    }

    @FXML
    private void handleSubmit() {
        String userInput = captchaField.getText();
        if (userInput.equals(captchaText)) {
            // Captcha is correct, do something
            System.out.println("Captcha is correct!");
        } else {
            // Captcha is incorrect, display an error message
            Alert alert = new Alert(Alert.AlertType.ERROR, "Captcha is incorrect.");
            alert.showAndWait();
        }
    }
}
