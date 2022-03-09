/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.*;

import Utilis.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Amal Chibani
 */
public class categorieServ implements serv<categorie>{
     private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    
    public categorieServ (){
    conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void ajouter(categorie cat) {
 String req = "INSERT INTO `categorievoy` (`idcat`,`nomcat`) VALUE ('" + cat.getIdcat()+ "','" + cat.getNomcat() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println(" categorie cree");
        } catch (SQLException ex) {
            Logger.getLogger(voyOrgServ.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<categorie> afficher() {
List<categorie> categ = new ArrayList<>();

        String req = "SELECT * FROM `categorievoy`";
        
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
              categorie cat =new categorie();
                cat.setIdcat(rs.getInt("idcat"));
                cat.setNomcat(rs.getString(2));
                categ.add(cat);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(voyOrgServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categ;    }

   
    @Override
    public void delete(int idcat) {
 try
        {   String req = "DELETE FROM `categorievoy` WHERE `idcat` = "+idcat;
                PreparedStatement ste = conn.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("voyage supprimé");
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }    
    }

    @Override
    public void update(categorie cat) {
 String sql = "UPDATE categorievoy SET `nomcat`=? WHERE idcat=" + cat.getIdcat();
        PreparedStatement ste;
        try {
            ste = conn.prepareStatement(sql);

        ste.setString(1, cat.getNomcat());
              
            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification du categorie a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(voyageOrganise.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public List<categorie> FindNameCat(String name) {
        
List<categorie> cat = new ArrayList<>();
        String req = "SELECT * FROM `categorievoy` where nomcat='" + name + "'";
        
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
               categorie categ = new categorie();
                categ.setIdcat(rs.getInt("idcat"));
                categ.setNomcat(rs.getString("nomcat"));
               
                cat.add(categ);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(categorieServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cat;
}

public boolean verifIdCat(categorie cat) throws SQLException
    {
       boolean found;
       
      
       
      String req= "Select * FROM voyageorganise Join categorievoy WHERE voyageorganise.idCat=categorievoy.idcat && categorievoy.idcat='" + cat.getIdcat() + "'";
       
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();

            if (rs.next()==false) {
                found=false;
            }
            else {
                found=true;
            }
      return found;
     
    
}

}
    

