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
import Entities.AgentAerien;
import Utilis.Datasource;
/**
 *
 * @author asus
 */
public class AgentAerienService implements IService<AgentAerien> {
private Connection conn;
    private Statement ste;
 private PreparedStatement pste;

    public AgentAerienService() {
          conn = Datasource.getInstance().getCnx();
    }
    @Override
    public void ajouter(AgentAerien a) {
         String req = "INSERT INTO `agent-aerien` (`nom`,`prenom`,`password`,`email`,`nomAgence`) VALUE ('" + a.getNom() + "','" + a.getPrenom() + "','"+a.getPwd()+ "','"+a.getEmail()+ "','"+a.getNomAgence()+"')";
   
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("agent aerien créée");
        } catch (SQLException ex) {
            Logger.getLogger(AgentAerienService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(AgentAerien a) {
          String sql ="UPDATE `agent-aerien` SET nom = '"+a.getNom()+"',prenom = '"
                    +a.getPrenom()+"',email = '"+a.getEmail()+"',nomAgence = '"
                    +a.getNomAgence()+"',password = '"+a.getPwd()+"' WHERE id ="+ a.getId()+";";
          try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("agent modifié");
        } catch (SQLException ex) {
           System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
   
    public void supprimer(int id) {
       String req = "DELETE FROM `agent-aerien` WHERE id = "+id;
     
          try {
          ste = conn.createStatement();
          ste.executeUpdate(req);
          }catch(SQLException E){
              System.out.println(E.getMessage());
               System.out.println("suppression avec succes !");
          }  
    }

    @Override
    public void supprimer(AgentAerien a) {
              String req = "DELETE FROM `agent-aerien` where id =? ";
    try {
          pste = conn.prepareStatement(req);
            pste.setInt(1, a.getId());
            int rowsDeleted = pste.executeUpdate();
            if (rowsDeleted > 0) 
              {
                   System.out.println("Agent supprime");
              }
        } catch (SQLException ex) {
        Logger.getLogger(AgentAerienService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public List<AgentAerien> afficher() {
           List<AgentAerien> agents = new ArrayList<>();
        String req = "SELECT * FROM `agent-aerien`";
        
        try {
           
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                AgentAerien a = new AgentAerien();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setNomAgence(rs.getString(5));
                a.setPwd(rs.getString(6));
                agents.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AgentAerienService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return agents;   
    }
    
     public List<AgentAerien> rechercheragent(String nom) {
        List<AgentAerien> agents=new ArrayList <AgentAerien>();
        String sql ="select * from `agent-aerien` where nom='"+nom+"';";
        try 
        {
            ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);          
            System.out.println("recherche avec succes");
            while(rs.next())
            {
                 AgentAerien a = new AgentAerien();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setNomAgence(rs.getString(5));
                a.setPwd(rs.getString(6));
                agents.add(a);
                System.out.println(a.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return agents ;
    }
    
     
     public int   selectidA(String nom , String password ) {
         
        String req = "SELECT  id  FROM `agent-aerien` WHERE  nom='"+nom+"' and password='"+password+"' " ;
     int i=0;
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
             i=rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AgentAerienService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return i;
    }

    
}
