/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.*;
import Services.*;
import Utilis.Datasource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class PaiementService implements IService<Paiement>{
private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public PaiementService() {
          conn = Datasource.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Paiement p) {
         String req = "INSERT INTO `paiement` (`modalite_paiement`,`date`,`montant`,`id_reservation`) VALUE (?,?,?,?)";
        try {
             pste = conn.prepareStatement(req);
            pste.setString(1,p.getModalite());
             pste.setDate(2,p.getDate());
             pste.setFloat(3,p.getMontant());
             pste.setInt(4,p.getId_reservation());
            pste.executeUpdate();
            System.out.println(" Paiment est    créée");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Paiement p ) {
          String req = " update paiement set modalite_paiement=? , date=? , montant=? , id_reservation=? where id=? ";
        try {
             pste = conn.prepareStatement(req);
           pste.setString(1,p.getModalite());
             pste.setDate(2,p.getDate());
             pste.setFloat(3,p.getMontant());
             pste.setInt(4,p.getId_reservation());
            pste.setInt(5,p.getId());
            pste.executeUpdate();
            System.out.println(" Reservation voyage   créée");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     
    public void modifierMontant(int id , float nb  ) {
          String req = " update paiement set  montant=?  where id_reservation= ?";
        try {
             pste = conn.prepareStatement(req);
           pste.setInt(2, id);
            pste.setFloat(1,nb);
            pste.executeUpdate();
            System.out.println(" Reservation voyage   créée");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(Paiement p) {
            String req = "DELETE FROM `paiement` WHERE id_reservation=?";
        try {
             pste = conn.prepareStatement(req);
            pste.setInt(1,p.getId_reservation());
            pste.executeUpdate();
            System.out.println("paiment suprime");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Paiement> afficher() {
           List<Paiement> Paiements = new ArrayList<>();
        String req = "SELECT * FROM `paiement`";
        
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Paiement p = new Paiement();
               p.setId(rs.getInt("id"));
               p.setDate(rs.getDate("date"));
                p.setMontant(rs.getFloat("montant"));
                p.setModalite(rs.getNString("modalite_paiement"));
                p.setId_reservation(rs.getInt("id_reservation"));
                Paiements.add(p);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Paiements;
    }
 
    
        public List<Paiement> afficher2(int id ) {
           List<Paiement> Paiements = new ArrayList<>();
        String req = "SELECT * FROM `paiement` where id_reservation in ( select id from reservation where id_client='"+id+"' )";
        
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Paiement p = new Paiement();
               p.setId(rs.getInt("id"));
               p.setDate(rs.getDate("date"));
                p.setMontant(rs.getFloat("montant"));
                p.setModalite(rs.getNString("modalite_paiement"));
                p.setId_reservation(rs.getInt("id_reservation"));
                Paiements.add(p);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Paiements;
    }
   public void MontantTotal(List<Integer> l )
         
           
   { List<Paiement> lp= this.afficher();
   
      double montantT= lp.stream().filter(p -> l.contains(p.getId_reservation())).mapToDouble(p->p.getMontant()).sum();
    
       
              
    
   
      
        }
   
   
       
       
   
}
 
