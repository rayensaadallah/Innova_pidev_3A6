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
import Entities.Activite;
import Utilis.Datasource;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


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
    
    
    public List<String> recupAdrM()
    {
        List<String> mail= new ArrayList();
        
        String req="Select email from Client";
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();
            
            
            while(rs.next()){
                String s = new String();
                s=rs.getNString(1);
                mail.add(s);
                
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mail;
    }
    
    public void envoyerMail(String from,String pass,List<String> to,String object,String subject) throws UnsupportedEncodingException
    {
    
         String password ="getaway123";
        String fromEmail ="getawayvoy.services@gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail,"Getaway Service"));
            
            InternetAddress[] toAddress = new InternetAddress[to.size()];
            for( int i = 0; i < to.size(); i++ ) {
                toAddress[i] = new InternetAddress(to.get(i));
            }
            for( int i = 0; i < toAddress.length; i++) {
                msg.addRecipient(Message .RecipientType.TO, toAddress[i]);
            }
            msg.setSubject(subject);

            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(object);

            emailContent.addBodyPart(textBodyPart);
            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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
//        try {
//            this.envoyerMail("Hey", "Aymenultras123",this.recupAdrM() ,"Détails:"+a.toString()+"" ,"Nouvelle activite: "+a.getNom()+"");
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
            
            //System.out.println("Liste des activites:");
            
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
    
    public List<Activite> rechercherActiviteRef(int input) { //Rechercher le contenu du input
        List<Activite> activites = new ArrayList<>();
        String req = "SELECT * FROM `Activite` Where RefAct="+input+"";
        
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();
            
//            ste = conn.createStatement();
//            ResultSet rs = ste.executeQuery(req);
            
            System.out.println("Resultat du recherche:");
            
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
    
    public List<Activite> rechercherActiviteNom(String nom, String type) { //Rechercher le contenu du input
        List<Activite> activites = new ArrayList<>();
        String req = "SELECT * FROM `Activite` Where Nom='"+nom+"' OR Type='"+type+"'";
        
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();
            
//            ste = conn.createStatement();
//            ResultSet rs = ste.executeQuery(req);
            
            System.out.println("Resultat du recherche:");
            
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
    
     public ObservableList<Activite> rechercherActivite(String input) {//Rechercher le contenu du input
        
        ObservableList<Activite> OActivite = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM `Activite` Where Nom LIKE '%"+input+"%' OR Type LIKE '%"+input+"%' OR NbrPlace LIKE '%"+input+"%' OR Date LIKE '%"+input+"%' OR Descrip LIKE '%"+input+"%' OR Duree LIKE '%"+input+"%' OR Date LIKE '%"+input+"%' OR Location LIKE '%"+input+"%' OR Prix LIKE '%"+input+"%'";
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();
            
            System.out.println("Resultat du recherche:");
            
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
                
                OActivite.add(a);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return OActivite;
}
     
     
     public ObservableList<Activite> trierActiviteNbrplace() {
       ObservableList<Activite> OActivite = FXCollections.observableArrayList();
        String req = "SELECT * FROM `Activite` ORDER BY NbrPlace";
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();
            
            

            while (rs.next()) {
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
                
                OActivite.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return OActivite;

    }
    }
    
     

