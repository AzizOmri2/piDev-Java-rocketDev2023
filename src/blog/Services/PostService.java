/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.Services;

import blog.DbConfig.MyDB;
import blog.Entities.Comment;
import blog.Entities.Post;
import blog.Interfaces.BlogCRUD;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed Ben Abid
 */
public class PostService implements BlogCRUD<Post>{
    
      public Connection conx= MyDB.getInstance().getConx();
    public Statement stm;

 public PostService() {
    conx = MyDB.getInstance().getConx();

   }

    @Override
    public void create(Post post) throws SQLException {
String sql = "INSERT INTO post (title, details,image,date_post,rate) VALUES (?,?,?,?,?)";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getDetails());
            pstmt.setString(3, post.getImage());
             pstmt.setString(4, post.getDate_post());
             pstmt.setInt(5, post.getRate());
            pstmt.executeUpdate();
            
                    System.out.println("Post ajouté");
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }
     public List<Comment> showComments(int id) throws SQLException {
String sql = "Select * From comment Where post_id = ?"; 
try (
             PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();


        List<Comment> personnes = new ArrayList<Comment>();
        while (rs.next()) {
            Comment p = new Comment(rs.getInt("id"),//or rst.getInt(1)
                    rs.getString("description"),
                    rs.getString("date_com"),
            rs.getInt("post_id"));
            personnes.add(p);
        }

        return personnes;
    } catch (SQLException e) {
System.out.println(e.getMessage());
        }
return null;
    }

    @Override
    public Post getOneById(int id) throws SQLException {
String sql = "SELECT id, title, details,image,date_post,rate FROM post WHERE id = ?";
        try (
             PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Post person = new Post();
                person.setId(rs.getInt("id"));
                person.setTitle(rs.getString("title"));
                person.setDetails(rs.getString("details"));
                person.setImage(rs.getString("image"));
                person.setDate_post(rs.getString("date_post"));
                person.setRate(rs.getInt("rate"));
                return person;
            }
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Post post, int id) throws SQLException {

        String sql = "UPDATE post SET title = ?, details = ?,image = ?,rate = ?, date_post = ? WHERE id = ?";
        try ( 
               
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
             
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getDetails());
             pstmt.setString(3, post.getImage());
              pstmt.setInt(4, post.getRate());
               pstmt.setString(5, post.getDate_post());
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            System.out.println("Post updated");
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws SQLException {
 String sql = "DELETE FROM post WHERE id = ?";
 //String sql = "DELETE FROM post WHERE id = ?";
        try {
             PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() throws SQLException {
  String sql = "DELETE FROM post";
        try {
             PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }
    
    public void deleteAllComments(int id) throws SQLException {
  String sql = "DELETE FROM comment WHERE post_id = ?";
        try {
             PreparedStatement pstmt = conx.prepareStatement(sql);
             pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Post> getAll() throws SQLException {
String sql = "Select * From Post";
PreparedStatement pstmt = conx.prepareStatement(sql);


ResultSet rs = pstmt.executeQuery();
        List<Post> personnes = new ArrayList<Post>();
        while (rs.next()) {
            Post p = new Post(rs.getInt("id"),//or rst.getInt(1)
                    rs.getString("title"),
                    rs.getString("details"),
                    rs.getString("image"),
                    rs.getString("date_post"),
                    rs.getInt("rate"));
            personnes.add(p);
        }

        return personnes;
    }

    
}
