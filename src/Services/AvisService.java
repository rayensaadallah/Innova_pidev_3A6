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
import Entities.Avis;
import Utilis.Datasource;

/**
 *
 * @author TheBoss'07
 */


public class AvisService extends SendSms implements IService<Avis> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public AvisService() {
        conn = Datasource.getInstance().getCnx();
    }

    public String NumTel(Avis av)
    {
        String NumTel ="";
        
       String req =  "Select DISTINCT NumTel from Client c JOIN Avis a ON c.id=a.Id WHERE a.Id="+ av.getId() +"";
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();
            
//            ste = conn.createStatement();
//            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                
               NumTel= rs.getNString(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return NumTel;
    }
    
    @Override
    public void ajouter(Avis av) {
//        String req = "INSERT INTO `Avis` (`Message`,`Date`,`Id`) VALUE ('" + av.getMessage() + "',NOW(), '" + av.getId() +"')";
        String req = "INSERT INTO `Avis` (`Message`,`Date`,RefActivite) VALUE ('" + av.getMessage() + "',NOW(),'" + av.getRefActivite() +"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Avis crée");
            String NumTel = this.NumTel(av);
            sendSms(NumTel);
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void modifier(Avis av) {
       String req = "UPDATE Avis SET Date= NOW(),Message='" + av.getMessage() +"' WHERE RefAvis='" + av.getRefAvis()+"'";
    try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        System.out.println("Avis Modifié");
    }   catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void supprimer(Avis av) {
        
      String req = "DELETE FROM Avis WHERE RefAvis='" + av.getRefAvis()+"'";
    try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        System.out.println("Avis Supprimer");
    }   catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Avis> afficher() {
        List<Avis> avis = new ArrayList<>();
        String req = "SELECT * FROM `Avis`";
        
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();
            
//            ste = conn.createStatement();
//            ResultSet rs = ste.executeQuery(req);
            //System.out.println("Liste des avis:");
            while(rs.next()){
                Avis av = new Avis();
                av.setRefAvis(rs.getInt(1));
                av.setMessage(rs.getString(2));
                av.setDate(rs.getString(3));
                av.setId(rs.getInt(4));
                av.setRefActivite(rs.getInt(5));
                
               
                avis.add(av);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return avis;
    }   

    

    
}
