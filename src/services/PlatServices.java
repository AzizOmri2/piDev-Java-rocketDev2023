/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import services.Icrud;
import entites.Menu;
import entites.Plat;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import utils.MyDB;

/**
 *
 * @author lenovo
 */
public class PlatServices implements Icrud<Plat> {
    
    public Connection conx;
    public Statement stm;

    public PlatServices() {
        conx = MyDB.getInstance().getConx();

    }

    @Override
    public void ajouter(Plat m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterr(Plat m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      @Override
    public List<Plat> afficherListe() throws SQLException {
       String req = "SELECT * FROM `plat`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Plat> menus = new ArrayList<Plat>();
        while (rs.next()) {
            Plat m = new Plat(rs.getInt("id"), //or rst.getInt(1)
                    rs.getString("nom"),
                    rs.getDouble("prix"));
            menus.add(m);
        }

        return menus;
    }

  //  @Override
  //  public void modifier(Plat m) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    @Override
    public void Supprimer(Plat t) throws SQLException {
           try {
            String requete="DELETE FROM plat WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1,t.getId());
            pst.executeUpdate();
           
            System.out.println("Plat est supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @Override
    public void Modifier(Plat m) throws SQLException {
        String requete = "UPDATE plat SET nom=?, prix=? , description=? , calories=? , etat=? , image=? , nbp=? , categories_id =? WHERE id=?";
        PreparedStatement pst = conx.prepareStatement(requete);
        pst.setString(1, m.getNom());
        pst.setDouble(2, m.getPrix());
        pst.setString(3, m.getDescription());
        pst.setString(4, m.getCalories());
        pst.setString(5, m.getEtat());
        pst.setString(6, m.getImage());
        pst.setInt(7, m.getNbp());
        pst.setInt(8, m.getCategories_id());
        pst.setInt(9, m.getId());
        pst.executeUpdate();
        System.out.println("Plat modifié avec succès");
    }

    @Override
    public int idmenu(String nom) {
         String req = "SELECT id FROM plat WHERE nom = ?";
    try {
        PreparedStatement pstmt = conx.prepareStatement(req);
        pstmt.setString(1, nom);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            return 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }    }

    @Override
    public List<Plat> afficherListe1(int id) throws SQLException {
             String req = "SELECT * FROM `plat` where categories_id = ?";
            PreparedStatement pstmt = conx.prepareStatement(req);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        List<Plat> menus = new ArrayList<Plat>();
        while (rs.next()) {
                Plat m = new Plat(rs.getInt("id"), //or rst.getInt(1)
                    rs.getString("nom"),
                    rs.getDouble("prix"),
                    rs.getString("calories"),
                    rs.getString("description"),
                    rs.getInt("nbp"),
                    rs.getString("image"));
            menus.add(m);
        }

        return menus;
    }
    
 //PDF 
    
   
    
    
    
    
}
   
     