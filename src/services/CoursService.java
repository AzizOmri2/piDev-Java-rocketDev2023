/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cours;
import entities.CrudCours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author azizo
 */
public class CoursService implements CrudCours<Cours>{

    public Connection conx;
    public Statement stm;

    
    public CoursService() {
        conx = MyDB.getInstance().getConx();

    }
    
    @Override
    public void ajouter(Cours c) {
        String req = 
                "INSERT INTO cours"
                + "(nom_cours,prix_cours,nom_coach,age_min_cours,description_cours)"
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, c.getNom_cours());
            ps.setFloat(2, c.getPrix_cours());
            ps.setString(3, c.getNom_coach());
            ps.setInt(4, c.getAge_min_cours());
            ps.setString(5, c.getDescription_cours());
            ps.executeUpdate();
            System.out.println("Cours Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Cours c) {
        String req = "UPDATE cours SET nom_cours=?, prix_cours=?, nom_coach=?, age_min_cours=?, description_cours=? WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(6, c.getId());
            pst.setString(1, c.getNom_cours());
            pst.setFloat(2, c.getPrix_cours());
            pst.setString(3, c.getNom_coach());
            pst.setInt(4, c.getAge_min_cours());
            pst.setString(5, c.getDescription_cours());
            pst.executeUpdate();
            System.out.println("Cours " + c.getNom_cours() + " Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM cours WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Cours suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<Cours> Show() {
        List<Cours> list = new ArrayList<>();

        try {
            String req = "SELECT * from cours";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Cours(rs.getInt("id"), rs.getString("nom_cours"), 
                        rs.getFloat("prix_cours"), rs.getString("nom_coach"), 
                        rs.getInt("age_min_cours"), rs.getString("description_cours")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    
}
