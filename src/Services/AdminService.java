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
import javax.mail.Authenticator;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Utilis.*;
/**
 *
 * @author asus
 */
public class AdminService implements IService<Admin> {

   
private Connection conn;
    private Statement ste;
 private PreparedStatement pste;

    public AdminService() {
           conn = Datasource.getInstance().getCnx();
    }
      @Override
    public void ajouter(Admin a) {  
         String req = "INSERT INTO `admin` (`nom`,`prenom`,`email`,`adresse`,`password`) VALUE ('" + a.getNom() + "','" + a.getPrenom() + "','"+a.getEmail()+ "','"+a.getAdresse()+ "','"+a.getPwd()+"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("admin créée");
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Override
    public List<Admin> afficher() {
            List<Admin> admins = new ArrayList<>();
        String req = "SELECT * FROM `admin`";
        
        try {
           
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Admin a = new Admin();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setAdresse(rs.getString(5));
                a.setPwd(rs.getString(6));
                
                admins.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return admins; 
    }
    
    
    
    
    

   @Override
    public void modifier(Admin a) {
        String sql ="UPDATE admin SET nom = '"+a.getNom()+"',prenom = '"
                    +a.getPrenom()+"',email = '"+a.getEmail()+"',adresse = '"
                    +a.getAdresse()+"',password = '"+a.getPwd()+"' WHERE id ="+ a.getId()+";";
        
        
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Admin modifié");
        } catch (SQLException ex) {
           System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void supprimer(int id) {
       String req = "DELETE FROM `admin` WHERE id="+id;
     
          try {
          ste = conn.createStatement();
          ste.executeUpdate(req);
          }catch(SQLException E){
              System.out.println(E.getMessage());
               System.out.println("suppression avec succes !");
          }  
    }
    
    @Override
    public void supprimer(Admin a){
        
        String req = "DELETE FROM `admin` where id =? ";
    try {
          pste = conn.prepareStatement(req);
            pste.setInt(1, a.getId());
            int rowsDeleted = pste.executeUpdate();
            if (rowsDeleted > 0) 
              {
                   System.out.println("An admin was deleted successfully!");
              }
        } catch (SQLException ex) {
        Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
    
    
        public List<Admin> rechercher(String nom) {
        List<Admin> admins=new ArrayList <Admin>();
        String sql ="select * from `admin` where nom='"+nom+"';";
        try 
        {
            ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);          
            System.out.println("recherche avec succes");
            while(rs.next())
            {
                 Admin a = new Admin();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setAdresse(rs.getString(5));
                a.setPwd(rs.getString(6));
                admins.add(a);
                System.out.println(a.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return admins ;
    }
        
        
         public List<Admin> rechercherAdmin(String input) {
        
          List<Admin> admins=new ArrayList <Admin>();
        
        String req = "SELECT * FROM `admin` Where nom LIKE '%"+input+"%' OR prenom LIKE '%"+input+"%' OR email LIKE '%"+input+"%' OR adresse LIKE '%"+input+"%'";
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();
            
            System.out.println("recherche effectue avec succes");
            
            while(rs.next()){
                 Admin a = new Admin();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setAdresse(rs.getString(5));
                a.setPwd(rs.getString(6));
                admins.add(a);
                System.out.println(a.toString());  
                
            }
            
        } catch (SQLException ex) {
         System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
   return admins ;
}
        
        
        
        
        
    
        public void activerCompteParAdmin(int id) 
    {
        String sql = "UPDATE client SET etat = '"+1+"' WHERE id ="+id+";";
        try
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("client de nouveau active");
        } catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }    
    }
        
            public void traiter(int id) 
    {
        String sql = "UPDATE reclamation SET etat = '"+1+"' WHERE idR ="+id+";";
        try
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("reclamation traitee");
        } catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }    
    }
        
        
    public void BanirCompteParAdmin(int id)
    {
        String sql = "UPDATE client SET etat = '"+0+"' WHERE id ="+id+";";
        try
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("client desactivee");
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }    
    }    
    
 public static void sendMail(String recepteur) throws Exception
    {
    Properties p =new Properties();
    p.put("mail.smtp.auth","true");
   p.put("mail.smtp.starttls.enable","true");
   p.put("mail.smtp.host","smtp.gmail.com");
   p.put("mail.smtp.port","587");
      String myaccount="omayma.djebali@esprit.tn";
   String pwd = "191JFT1449";
        System.out.println("HEHI 1");

  Session session = Session.getInstance(p, new Authenticator() {
        protected PasswordAuthentication getpPasswordAuthentication()
        {
                    System.out.println("HEHI 2");

            System.out.println("my acc: "+myaccount +" PWD : "+pwd);
            return new PasswordAuthentication(myaccount,pwd);
        }
    });
Message m = prepareMessage(session,myaccount,recepteur);
Transport.send(m);
        System.out.println("msg envoye avec succes");
}

 private static Message prepareMessage(Session session, String myaccount, String recepteur) {

    try {
        Message msg= new MimeMessage(session);
        msg.setFrom(new InternetAddress(myaccount));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepteur));
        msg.setSubject("my first email from getaway");
        msg.setText("bienvenue dans notre agence de voyage getaway ");
        return msg;
    } catch (Exception ex) {
        Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
 }
  
           
           
  }

   
   
  