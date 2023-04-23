/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import  utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import entites.Plat;
import entites.Menu;
import entites.Reservation;

/**
 *
 * @author lenovo
 */
public class Mailing {
    
    private Connection con;
    private Statement ste;

    public Mailing() {
        con = MyDB.getInstance().getConx();

    }

public static void sendEmail(String recipientEmail) throws Exception {
    // Vérification de l'adresse email du destinataire
    if (!recipientEmail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
        throw new IllegalArgumentException("Adresse email invalide");
    }
    
        // Remplacer les informations ci-dessous avec les informations de votre compte email
        String host = "smtp.gmail.com";
        String senderEmail = "chaima.benhmida@esprit.tn";
        String senderPassword = "iamaghost";

        // configuration de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // création d'une nouvelle session avec l'authentification
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // création d'un nouveau message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("EnergyBox:Confirmation de réservation");
        message.setText("Bienvenue chez EnergyBox restaurant ! Votre réservation sera prête dans une heure . Veuillez la confirmer par mail s'il vous plaît ! ");

        // envoi du message
        Transport.send(message);

        System.out.println("Le mail a été envoyé avec succès à " + recipientEmail);
    }

    public static void main(String[] args) {
        try {
            sendEmail("destinataire@example.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
