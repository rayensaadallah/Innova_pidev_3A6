/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.Category;
import Utilis.Datasource;

/**
 *
 * @author Rayen
 */
public class CategoryService implements IServices<Category> { 
    
    
   private Connection conn;
   private Statement ste;
   private PreparedStatement pste;

   public CategoryService() {
     conn = Datasource.getInstance().getCnx();   
    }

    @Override
    public void ajouter(Category C) {
 String req = "INSERT INTO `category`(`id_categ`, `nom_categ`) VALUES (?,?)";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, C.getId_categ());
            pste.setString(2, C.getNom_categ());           
            pste.execute();
            System.out.println("category créée");
        } catch (SQLException ex) {
                        Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Category C) {
String req = "UPDATE `category` SET `nom_categ`=?  WHERE  `id_categ` = "+ C.getId_categ() + "";  
    try {
            pste = conn.prepareStatement(req);
            pste.setString(1, C.getNom_categ());
            pste.executeUpdate();
            int rowsUpdated = pste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de category a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
//
// String req = "UPDATE `category` SET nom_categ = " + C.getNom_categ()+ "  WHERE  `id_categ` = "+ C.getId_categ() +""; 
//          try {
//            Statement stl = conn.createStatement();
//            stl.executeUpdate(req);
//            System.out.println("category modifié");
//        } catch (SQLException ex) {
//           System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        } 
    
    }

    @Override
    public void supprimer(Category C) {
try {
            String req = "DELETE FROM `category` WHERE `category`.`id_categ` = "+ C.getId_categ() + "";
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("Category supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Category> afficher() {
List<Category> cat = new ArrayList<>();
        String req = "SELECT * FROM `category`"; 
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                Category c = new Category();
                c.setId_categ(rs.getInt("id_categ"));
                c.setNom_categ(rs.getString("nom_categ"));
                cat.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat ;
    }
    
    
    public List<Category> getByReferanc (int id_categ) {
        List<Category> categorys = new ArrayList<>();
        String req = "select * from category where id_categ='"+id_categ+"';";
        
        try {
           ste = conn.createStatement();
            ResultSet resultSet = ste.executeQuery(req);
            
          
            if (resultSet.next()){
                Category c= new Category();
                c.setId_categ(resultSet.getInt(1)); 
                c.setNom_categ(resultSet.getString(2));
                categorys.add(c);
                System.out.println(c.toString());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categorys;
    }
}
