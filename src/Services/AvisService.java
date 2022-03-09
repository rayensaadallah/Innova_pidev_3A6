/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activite;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.Avis;
import Utilis.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public String NumTel(Avis av) {
        String NumTel = "";

        String req = "Select DISTINCT NumTel from Client c JOIN Avis a ON c.id=a.Id WHERE a.Id=" + av.getId() + "";
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();

//            ste = conn.createStatement();
//            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {

                NumTel = rs.getNString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return NumTel;
    }

    @Override
    public void ajouter(Avis av) {

        String req = "INSERT INTO `Avis` (`Message`,`Date`,`Id`,`RefActivite`,`Rating`) VALUE ('" + av.getMessage() + "',NOW(),'"+av.getId()+"','"+ av.getRefActivite() + "','" + av.getRating() + "')";
        //String req = "INSERT INTO `Avis` (`Message`,`Date`,RefActivite,Rating) VALUE ('" + av.getMessage() + "',NOW(),'"+ av.getRefActivite() + "','" + av.getRating() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Avis crée");
            String NumTel = this.NumTel(av);
            //sendSms(NumTel);
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifier(Avis av) {
        System.out.println(av.getId());
       String req = "UPDATE Avis SET Date= NOW() ,Message='" + av.getMessage() + "',Rating='" + av.getRating()+ "'WHERE RefAvis='" + av.getRefAvis() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Avis Modifié");
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(Avis av) {

        String req = "DELETE FROM Avis WHERE RefAvis='" + av.getRefAvis() + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Avis Supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Avis> afficher() {
        List<Avis> avis = new ArrayList<>();
        String req = "select DISTINCT aa.RefAvis ,aa.Message , aa.Date, c.nom , a.Nom from activite a join avis aa on aa.RefActivite=a.RefAct JOIN client c on c.id=aa.Id;";

        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();

//            ste = conn.createStatement();
//            ResultSet rs = ste.executeQuery(req);
            //System.out.println("Liste des avis:");
            while (rs.next()) {
                Avis av = new Avis();
                av.setRefAvis(rs.getInt(1));
                av.setMessage(rs.getString(2));
                av.setDate(rs.getString(3));
                av.setNomClient(rs.getString(4));
                av.setNomAct(rs.getString(5));

                avis.add(av);

            }

        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return avis;
    }

    public ObservableList<Avis> avisListStat(int idActivite, String message) {
        ObservableList<Avis> avisList = FXCollections.observableArrayList();
        String req = null;

        if (message == null) 
            req = "SELECT * FROM `avis` where `RefActivite`=" + idActivite;
            else
                    req = "SELECT * FROM `avis` where `RefActivite`="+idActivite+" AND Message= '"+message+"' ";

            try {
                pste = conn.prepareStatement(req);
                ResultSet rs = pste.executeQuery();

                while (rs.next()) {
                    Avis avis = new Avis();
                    avis.setRefAvis(rs.getInt(1));
                    avis.setMessage(rs.getString(2));
                    avis.setDate(rs.getString(3));
                    avis.setNomClient(rs.getString(4));
                    avis.setNomAct(rs.getString(5));
                    avis.setRating(rs.getFloat(6));


                    avisList.add(avis);
                }

            } catch (SQLException ex) {
                Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
            }

            return avisList;

        }

    
}
