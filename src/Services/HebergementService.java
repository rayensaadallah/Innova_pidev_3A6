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
import Entities.Hebergement;
import Utilis.Datasource;
/**
 *
 * @author Rayen
 */
public class HebergementService implements IService<Hebergement> {
 
     
private Connection conn;
private Statement ste;
private PreparedStatement pste;
    

    public HebergementService() {
     conn = Datasource.getInstance().getCnx();   
    }

@Override
    public void ajouter(Hebergement H) {
String req = "INSERT INTO `hebergement`(`referance`, `paye`, `adress`, `prix`, `description`, `photo`, `date_start`, `date_end`, `contact`, `nbr_detoile`, `nbr_suite`, `nbr_parking`, `model_caravane`, `id_categ`, `offreur_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, H.getReferance());
            pste.setString(2, H.getPaye());
            pste.setString(3, H.getAdress());
            pste.setFloat(4, H.getPrix());
            pste.setString(5, H.getDescription());
            pste.setString(6, H.getPhoto());
            pste.setDate(7, H.getDate_start());
            pste.setDate(8, H.getDate_end());
            pste.setString(9, H.getContact());
            pste.setInt(10, H.getNbr_detoile());
            pste.setInt(11, H.getNbr_suite());
            pste.setInt(12,H.getNbr_parking());
            pste.setString(13, H.getModel_caravane());
            pste.setInt(14, H.getId_confeg());
            pste.setInt(15, H.getOffreur());
                        pste.execute();
            System.out.println("Hebergement créée");
        } catch (SQLException ex) {
            Logger.getLogger(HebergementService.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    @Override
    public void modifier(Hebergement H) {
        String req = "update hebergement set  paye=?,adress=?,prix=? ,description=?,photo=?,date_start=?,date_end=?,contact=?,nbr_suite=?,nbr_parking=?,model_caravane=?,id_categ=?  where referance=?";
    try {
            pste = conn.prepareStatement(req);
            pste.setString(1, H.getPaye());
            pste.setString(2, H.getAdress());
            pste.setFloat(3, H.getPrix());
            pste.setString(4, H.getDescription());
            pste.setString(5, H.getPhoto());
            pste.setDate(6, H.getDate_start());
            pste.setDate(7, H.getDate_end());
            pste.setString(8, H.getContact());
            pste.setInt(9, H.getNbr_detoile());
            pste.setInt(10, H.getNbr_suite());
            pste.setString(11, H.getModel_caravane());
            pste.setInt(12, H.getId_confeg());
            pste.setInt(13, H.getReferance());
            
           pste.executeUpdate();
            int rowsUpdated = pste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification d'avion a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HebergementService.class.getName()).log(Level.SEVERE, null, ex);     
        }
    }

    @Override
    public void supprimer(Hebergement H) {
try {
            String req = "DELETE FROM `hebergement` WHERE  `referance` = "+ H.getReferance() + "";
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("hebergement supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(HebergementService.class.getName()).log(Level.SEVERE, null, ex);     
       }
    }

    @Override
    public List<Hebergement> afficher() {
    List<Hebergement> Hebergement = new ArrayList<>();
        String req = "SELECT * FROM `hebergement`";
        
        try {
            
            ste = conn.createStatement();
            ResultSet resultSet = ste.executeQuery(req);
            
            while(resultSet.next()){
                Hebergement h = new Hebergement();
                h.setReferance(resultSet.getInt(1));
                h.setOffreur(resultSet.getInt(2));
                h.setPaye(resultSet.getString(3));
                h.setAdress(resultSet.getString(4));
                h.setPrix(resultSet.getFloat(5));
                h.setDescription(resultSet.getString(6));
                h.setPhoto(resultSet.getString(6));
                h.setDate_start(resultSet.getDate("date_start"));
                h.setDate_end(resultSet.getDate("date_end"));
                h.setContact(resultSet.getString(9));
                h.setNbr_detoile(resultSet.getInt(10));
                h.setNbr_suite(resultSet.getInt(11));
                h.setNbr_parking(resultSet.getInt(12));
                h.setModel_caravane(resultSet.getString(13));
                h.setId_confeg(resultSet.getInt(14));
                Hebergement.add(h);
                System.out.println(h);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HebergementService.class.getName()).log(Level.SEVERE, null, ex);     
        }
        return Hebergement;
    }
    


    
}

