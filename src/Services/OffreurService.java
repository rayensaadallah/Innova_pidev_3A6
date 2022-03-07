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
import Entities.Offreur;
import Utilis.*;
/**
 *
 * @author asus
 */
public class OffreurService implements IService<Offreur>{
private Connection conn;
    private Statement ste;
 private PreparedStatement pste;

    public OffreurService() {
        conn = Datasource.getInstance().getCnx();
    }
 
 
 
   
    public void ajouter(Offreur o) {
               String req = "INSERT INTO offreur (`nom`,`prenom`,`email`,`password`,`numtl`) VALUE ('" + o.getNom() + "','" + o.getPrenom() + "','"+o.getEmail()+ "','"+o.getPwd()+ "','"+o.getNumtl()+"')";
   
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("offreur créée");
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
    public void modifier(Offreur a) {
             String sql ="UPDATE `offreur` SET nom = '"+a.getNom()+"',prenom = '"
                    +a.getPrenom()+"',email = '"+a.getEmail()+"',password = '"
                    +a.getPwd()+"',numtl = '"+a.getNumtl()+"' WHERE id ="+ a.getId()+";";
          try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("offreur modifié");
        } catch (SQLException ex) {
           System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

   
    public void supprimer(Offreur a) {
                    String req = "DELETE FROM `offreur` where id =? ";
    try {
          pste = conn.prepareStatement(req);
            pste.setInt(1, a.getId());
            int rowsDeleted = pste.executeUpdate();
            if (rowsDeleted > 0) 
              {
                   System.out.println("suppression avec succes");
              }
        } catch (SQLException ex) {
        Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
       public void supprimer(int id) {
       String req = "DELETE FROM `offreur` WHERE id = "+id;
     
          try {
          ste = conn.createStatement();
          ste.executeUpdate(req);
          }catch(SQLException E){
              System.out.println(E.getMessage());
               System.out.println("suppression avec succes !");
          }  
    }

    public List<Offreur> afficher() {
                     List<Offreur> offreurs = new ArrayList<>();
        String req = "SELECT * FROM `offreur`";
        
        try {
           
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Offreur a = new Offreur();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setPwd(rs.getString(5));
                a.setNumtl(rs.getInt("numtl"));
                offreurs.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return offreurs; 
    }
    
    public List<Offreur> rechercheroffreur(String nom) {
        List<Offreur> offreurs=new ArrayList <Offreur>();
        String sql ="select * from `offreur` where nom='"+nom+"';";
        try 
        {
            ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);          
            System.out.println("recherche avec succes");
            while(rs.next())
            {
                 Offreur a = new Offreur();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setNumtl(rs.getInt(6));
                a.setPwd(rs.getString(5));
                offreurs.add(a);
                System.out.println(a.toString());  
               
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return offreurs ;
    }
    
    
     public int   selectidO(String nom , String password ) {
         
        String req = "SELECT  id  FROM `offreur` WHERE  nom='"+nom+"' and password='"+password+"' " ;
     int i=0;
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
             i=rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return i;
    }

    
    public  Offreur  selectmodifier( int idc ) {
       String sql= " Select * from `offreur` WHERE id= '"+idc+"' " ;
       Offreur a = new Offreur();
             try 
        {
            ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);          
           
            while(rs.next())
            {
                 
                a.setId(idc);
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setNumtl(rs.getInt(6));
                a.setPwd(rs.getString(5));
               
              
                //System.out.println(a.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
         

       return a;
      
        
    }
     
    
    
    
    
}
