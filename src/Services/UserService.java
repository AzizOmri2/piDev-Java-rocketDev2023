/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author wiem
 */
public class UserService {

    public static int idUser;
    public static String email;
    public static String password;
    public static String username;
    public static Integer num_tel;
    public static Date date_n;
    public static String roles;
    public static String image;
    public Connection conx;
    public Statement stm;

    public UserService() {
        conx = MyDB.getInstance().getConx();

    }

    public List<User> afficherListe() {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * from user";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next() && (rs.getInt("id") != idUser)) {
                list.add(new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getInt("num_tel"), rs.getDate("date_n"), rs.getString("roles"),
                        rs.getString("image"), rs.getBoolean("is_blocked"), rs.getBoolean("is_approved")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

}
