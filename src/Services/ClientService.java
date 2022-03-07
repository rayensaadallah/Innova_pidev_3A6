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
import entities.Client;
import javafx.event.ActionEvent;
import Utilis.Datasource;
/**
 *
 * @author asus
 */
//test git 
public class ClientService implements IService<Client> {
private Connection conn;
    private Statement ste;
 private PreparedStatement pste;

    public ClientService() {
        conn = Datasource.getInstance().getCnx();
    }
 
 
 
 
    
    public void ajouter(Client c) {
          String req = "INSERT INTO client (`nom`,`prenom`,`email`,`password`,`etat`,`securityQ`,`answer`) VALUE ('" + c.getNom() + "','" + c.getPrenom() + "','"+c.getEmail()+ "','"+c.getPwd()+ "','"+c.getEtat()+"','"+c.getSecurityQ()+"','"+c.getAnswer()+"')";
   
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("client créée");
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    public void modifier(Client c) {
             String sql ="UPDATE `client` SET nom = '"+c.getNom()+"',prenom = '"
                    +c.getPrenom()+"',email = '"+c.getEmail()+"',password = '"
                    +c.getPwd()+"',etat = '"+c.getEtat()+"',securityQ = '"+c.getSecurityQ()+"',answer = '"+c.getAnswer()+"' WHERE id ="+ c.getId()+";";
          try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("client modifié");
        } catch (SQLException ex) {
           System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } 
    }
    
    

    public void supprimer(Client c) {
                   String req = "DELETE FROM `client` where id =? ";
    try {
          pste = conn.prepareStatement(req);
            pste.setInt(1, c.getId());
            int rowsDeleted = pste.executeUpdate();
            if (rowsDeleted > 0) 
              {
                   System.out.println("suppression avec succes");
              }
        } catch (SQLException ex) {
        Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
         public void supprimer(int id) {
       String req = "DELETE FROM `client` WHERE id="+id;
     
          try {
          ste = conn.createStatement();
          ste.executeUpdate(req);
          }catch(SQLException E){
              System.out.println(E.getMessage());
               System.out.println("suppression avec succes !");
          }  
    }
    

    public List<Client> afficher() {
              List<Client> clients = new ArrayList<>();
        String req = "SELECT * FROM `client`";
        
        try {
           
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Client a = new Client();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setPwd(rs.getString(5));
                a.setEtat(rs.getInt("etat"));
                clients.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return clients;  
    }
    
    public List<Client> rechercherclient(String nom) {
        List<Client> clients=new ArrayList <Client>();
        String sql ="select * from `client` where nom='"+nom+"';";
        try 
        {
            ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);          
            System.out.println("recherche avec succes");
            while(rs.next())
            {
                 Client a = new Client();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setEtat(rs.getInt(6));
                a.setPwd(rs.getString(5));
                clients.add(a);
                System.out.println(a.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return clients ;
    }
    

     public int   selectidC(String nom , String password ) {
         
        String req = "SELECT  id  FROM `client` WHERE  nom='"+nom+"' and password='"+password+"' " ;
     int i=0;
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
             i=rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return i;
    }

    
    public  Client  selectmodifier( int idc ) {
       String sql= " Select * from client   WHERE id= '"+idc+"' " ;
       Client a = new Client();
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
                a.setPwd(rs.getString(5));
              
                System.out.println(a.toString());                
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
