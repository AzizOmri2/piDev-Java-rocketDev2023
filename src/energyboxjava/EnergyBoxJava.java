/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package energyboxjava;

import Entity.Competition;
import Entity.Ticket;
import Services.CompetitionServices;
import Services.TicketServices;
import Utils.MyDB;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Salima
 */
public class EnergyBoxJava {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) throws ParseException, SQLException {
    // TODO code application logic here
    MyDB db = MyDB.getInstance();

    // CECII EST POUR TESTER LES CRUDS
   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = dateFormat.parse("2023-05-22");
java.sql.Date sqlDate = new java.sql.Date(date.getTime());

    try {
        date = dateFormat.parse("2023-05-22");
    } catch (ParseException e) {
        e.printStackTrace();
    }
Competition c = new Competition(10, "testeuu", 50, date, 10, "disponible", 0);
Competition c1 = new Competition(46, "test222", 20, date, 10, "disponible", 0);

CompetitionServices ps = new CompetitionServices();
TicketServices ts = new TicketServices();
//Ajouter la competition dans la base de données
//ps.ajouterr(c);

// Afficher la liste des tickets
List<Ticket> tickets = ts.afficherListe();
for (Ticket ticket : tickets) {
    System.out.println(ticket.toString());
}
//modif competition
//ps.ajouterr(c);
ps.modifierCompetition(c1);

// Créer un nouveau ticket
//Ticket t = new Ticket("bonjour c'est un test",c);
//Ticket r=new Ticket(11,"Description test",c1);
Ticket rs=new Ticket();
// Ajouter le ticket dans la base de données
//ts.ajouterr(t);

ts.afficherListe();
ts.afficher(1);
//ts.modifierTicket(r);
/*
ts.afficher(1);
ps.afficherCompetition(5);

ts.supprimerTicket(10);
*/
}
        
        //ps.afficherListe();
       // ps.modifierCompetition(c);
    
}

       /* try{
            ps.modifierCompetition(c);
        }
        catch (SQLException exx){
            System.out.println(exx.getMessage());
        }*/
        
              //launch(args);
    
        
    

    
