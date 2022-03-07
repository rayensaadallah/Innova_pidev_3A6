/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utilis.*;

/**
 *
 * @author asus
 */
public class ReclamationService implements IService<Reclamation>{
    private Connection conn;
    private Statement ste;
 private PreparedStatement pste;


    public ReclamationService() {
        conn = Datasource.getInstance().getCnx();
    }
 
   
    public void ajouter(Reclamation r) {
            String req = "INSERT INTO `reclamation` (`idClient`,`objet`,`description`,`etat`) VALUE ('" + r.getIdC() + "','" + r.getObjet() + "','"+r.getDescription()+ "','"+r.getEtat()+ "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("reclamation ajoutee");
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    public void modifier(Reclamation r) {
              String sql ="UPDATE reclamation SET idClient = '"+r.getIdC()+"',objet = '"
                    +r.getObjet()+"',description = '"+r.getDescription()+"',etat = '"+r.getEtat()+"' WHERE idR ="+ r.getIdR()+";";
        
        
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("reclamation modifié");
        } catch (SQLException ex) {
           System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        
          }

  
    public void supprimer(Reclamation r) {
        
              String req = "DELETE FROM `reclamation` where idR =? ";
    try {
          pste = conn.prepareStatement(req);
            pste.setInt(1, r.getIdR());
            int rowsDeleted = pste.executeUpdate();
            if (rowsDeleted > 0) 
              {
                   System.out.println("reclamation supprime !");
              }
        } catch (SQLException ex) {
        Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
    
      public void supprimer(int id) {
       String req = "DELETE FROM `reclamation` WHERE idR="+id;
     
          try {
          ste = conn.createStatement();
          ste.executeUpdate(req);
          }catch(SQLException E){
              System.out.println(E.getMessage());
               System.out.println("suppression avec succes !");
          }  
    }
    
    
    

   
    public List<Reclamation> afficher() {
        
              List<Reclamation> reclamations = new ArrayList<>();
        String req = "SELECT * FROM `reclamation`";
        
        try {
           
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Reclamation r = new Reclamation();
                 r.setIdR(rs.getInt("idR"));
                r.setIdC(rs.getInt("idClient"));
               r.setObjet(rs.getString(3));
             r.setDescription(rs.getString(4)); 
              r.setEtat(rs.getInt("etat")); 

                reclamations.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reclamations; 
    }
    
  public  Reclamation selectmodifier( int idc ) {
       String sql= " Select * from reclamation WHERE idClient= '"+idc+"' " ;
       Reclamation r = new Reclamation();
             try 
        {
            ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);          
           
            while(rs.next())
            {
                
                r.setIdC(idc);
                r.setIdR(rs.getInt("idR"));
                 r.setObjet(rs.getString(3));
                r.setDescription(rs.getString(4));
                r.setEtat(rs.getInt("etat"));
                
              
                System.out.println(r.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
         

       return r;
      
        
    }
    
  
  
   public List<Reclamation> afficherParIdC(int idc)
   {
              List<Reclamation> reclamations = new ArrayList<>();
        String req =  " Select * from `reclamation` WHERE idClient= '"+idc+"' ";
        
        try {
           
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Reclamation r = new Reclamation();
                 r.setIdR(rs.getInt("idR"));
                r.setIdC(rs.getInt("idClient"));
               r.setObjet(rs.getString(3));
             r.setDescription(rs.getString(4)); 

                reclamations.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
 return reclamations;
   
   }
    
    
    
    
}
