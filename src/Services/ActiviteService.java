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

import Entities.*;
import GetAway.entities.Activite;
import Utilis.Datasource;


/**
 *
 * @author TheBoss'07
 */
public class ActiviteService implements IService<Activite> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public ActiviteService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void ajouter(Activite a) {
        String req = "INSERT INTO `Activite` (`Nom`,`Descrip`,`Duree`,`NbrPlace`,`Date`,`Type`,`Location`,`Prix`) VALUE ('" + a.getNom() + "','" + a.getDescrip() +"', '" + a.getDuree() +"','" + a.getNbrPlace() +"','" + a.getDate() +"','" + a.getType() +"','" + a.getLocation() +"','" + a.getPrix() +"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Activite créée");
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void modifier(Activite a) {
       String req = "UPDATE Activite SET RefAct='" + a.getRefAct() + "',Nom='" + a.getNom() + "',Descrip='" + a.getDescrip() + "',Duree='" + a.getDuree() + "',NbrPlace='" + a.getNbrPlace() + "',Date='" + a.getDate() + "',Type='" + a.getType() + "',Location='" + a.getLocation() + "',Prix='" + a.getPrix() + "' WHERE RefAct='" + a.getRefAct()+"'";
    try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        System.out.println("Activite Modifié");
    }   catch (SQLException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void supprimer(Activite a) {
        
      String req = "DELETE FROM Activite WHERE RefAct='" + a.getRefAct()+"'";
    try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        System.out.println("Activite Supprimer");
    }   catch (SQLException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Activite> afficher() {
        List<Activite> activites = new ArrayList<>();
        String req = "SELECT * FROM `Activite`";
        
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();
            
//            ste = conn.createStatement();
//            ResultSet rs = ste.executeQuery(req);
            
            System.out.println("Liste des activites:");
            
            while(rs.next()){
                Activite a = new Activite();
                a.setRefAct(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setDescrip(rs.getString(3));
                a.setDuree(rs.getString(4));
                a.setNbrPlace(rs.getInt(5));
                a.setDate(rs.getString(6));
                a.setType(rs.getString(7));
                a.setLocation(rs.getString(8));
                a.setPrix(rs.getFloat(9));
                
                activites.add(a);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return activites;
    }   
}
