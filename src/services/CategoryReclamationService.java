/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CategoryReclamation;
import entities.CrudCategoryReclamation;
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
public class CategoryReclamationService implements CrudCategoryReclamation<CategoryReclamation>{

    public Connection conx;
    public Statement stm;

    
    public CategoryReclamationService() {
        conx = MyDB.getInstance().getConx();
    }
    
    
    @Override
    public void ajouter(CategoryReclamation ca) {
        String req = 
                "INSERT INTO category_reclamation"
                + "(nom_category,description_category,priorite_category)"
                + "VALUES(?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, ca.getNom_category());
            ps.setString(2, ca.getDescription_category());
            ps.setString(3, ca.getPriorite_category());
            ps.executeUpdate();
            System.out.println("Category Réclamation Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(CategoryReclamation ca) {
        String req = "UPDATE category_reclamation SET nom_category=?, description_category=?, priorite_category=? WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(4, ca.getId());
            pst.setString(1, ca.getNom_category());
            pst.setString(2, ca.getDescription_category());
            pst.setString(3, ca.getPriorite_category());
            pst.executeUpdate();
            System.out.println("Category Réclamation " + ca.getNom_category() + " est modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM category_reclamation WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Category Réclamation suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<CategoryReclamation> Show() {
        List<CategoryReclamation> list = new ArrayList<>();

        try {
            String req = "SELECT * from category_reclamation";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new CategoryReclamation(rs.getInt("id"), rs.getString("nom_category"), 
                        rs.getString("description_category"), rs.getString("priorite_category")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
}
