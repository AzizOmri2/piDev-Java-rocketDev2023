/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CrudPlanning;
import entities.Planning;
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
public class PlanningService implements CrudPlanning<Planning>{

    public Connection conx;
    public Statement stm;

    
    public PlanningService() {
        conx = MyDB.getInstance().getConx();

    }
    
    @Override
    public void ajouter(Planning p) {
        String req = 
                "INSERT INTO planning"
                + "(cours_id,date_planning,jour_planning,heure_planning)"
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, p.getCours_id());
            ps.setDate(2, new java.sql.Date(p.getDate_planning().getTime()));
            ps.setString(3, p.getJour_planning());
            ps.setInt(4, p.getHeure_planning());
            ps.executeUpdate();
            System.out.println("Planning Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Planning p) {
        String req = "UPDATE planning SET cours_id=?, date_planning=?, jour_planning=?, heure_planning=? WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(5, p.getId());
            pst.setInt(1, p.getCours_id());
            pst.setDate(2, new java.sql.Date(p.getDate_planning().getTime()));
            pst.setString(3, p.getJour_planning());
            pst.setInt(4, p.getHeure_planning());
            pst.executeUpdate();
            System.out.println("Planning le" + p.getDate_planning() + " est modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM planning WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Planning suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<Planning> Show() {
        List<Planning> list = new ArrayList<>();

        try {
            String req = "SELECT * from planning";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Planning(rs.getInt("id"), rs.getInt("cours_id"), 
                        rs.getDate("date_planning"), rs.getString("jour_planning"), 
                        rs.getInt("heure_planning")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
}
