/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.*;
import Entities.User;
import Utilis.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author asus
 */
public class UserService implements IService<User>  {
    
    private Connection conn;
    private Statement ste;
 private PreparedStatement pste;

    public UserService() {
           conn = Datasource.getInstance().getCnx();
    }
 private String nom;
    private String prenom;
    private String pwd;
    private String adresse;
    private String securityQ;
    private String answer;
    private String numtel;
    private String email;
    private String nomAgence;
    private String role;
   
    public void ajouter1(User a) {
    String req = "INSERT INTO `user` (`nom`,`prenom`,`email`,`adresse`,`password`,`securityQ`,`answer`,`numtel`,`nomAgence`,`role`) VALUE ('" + a.getNom() + "','" + a.getPrenom() + "','"+a.getEmail()+ "','"+a.getAdresse()+ "','"+a.getPwd()+"','"+a.getSecurityQ()+"','"+a.getAnswer()+"','"+a.getNumtel()+"','"+a.getNomAgence()+"','"+a.getRole()+"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("admin créée");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
      @Override
     public void ajouter(User a) {
         
    String req = "INSERT INTO `user` (`nom`,`prenom`,`email`,`adresse`,`password`,`role`) VALUE ('" + a.getNom() + "','" + a.getPrenom() + "','"+a.getEmail()+ "','"+a.getAdresse()+ "','"+a.getPwd()+"','"+a.getRole()+"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("admin créée");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void modifier(User a) {
        
            String sql ="UPDATE user SET nom = '"+a.getNom()+"',prenom = '"
                    +a.getPrenom()+"',email = '"+a.getEmail()+"',adresse = '"
                    +a.getAdresse()+"',password = '"+BCrypt.hashpw(a.getPwd(), BCrypt.gensalt(13)).replace("$2a", "$2y")+"',securityQ = '"+a.getSecurityQ()+"'"
                    + ",answer = '"+a.getAnswer()+"',numtel = '"+a.getNumtel()+"',nomAgence = '"+a.getNomAgence()+"'"
                    + ",role = '"+a.getRole()+"'  WHERE id ="+ a.getId()+";";
  
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("User modifié");
        } catch (SQLException ex) {
           System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void supprimer(User a) {
     String req = "DELETE FROM `user` where id =? ";
    try {
          pste = conn.prepareStatement(req);
            pste.setInt(1, a.getId());
            int rowsDeleted = pste.executeUpdate();
            if (rowsDeleted > 0) 
              {
                   System.out.println("A user was deleted successfully!");
              }
        } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }      }
    
     public void supprimer(int id) {
       String req = "DELETE FROM `user` WHERE id="+id;
     
          try {
          ste = conn.createStatement();
          ste.executeUpdate(req);
          }catch(SQLException E){
              System.out.println(E.getMessage());
               System.out.println("suppression avec succes !");
          }  
    }

    
    public List<User> afficher(String r) {
        System.out.println(r);
        List<User> users = new ArrayList<>();
        String req = "SELECT * FROM `user` where role='"+r+"'";
        
        try {
           
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                User a = new User();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(7));
                a.setAdresse(rs.getString(8));
                a.setPwd(rs.getString(4));
                a.setSecurityQ(rs.getString(5));
                a.setAnswer(rs.getString(6));
                a.setNumtel(rs.getString(9));
                a.setNomAgence(rs.getString(10));
                a.setRole(rs.getString(11));
                users.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;

    }
    
    
    
    
           public User UserByEmail(String email) {
          User a = new User();
        try {
            String findbyemail = "SELECT * FROM `user` WHERE `email`=? ";
            pste = conn.prepareStatement(findbyemail);

            pste.setString(1, email);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
              
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(7));
                a.setAdresse(rs.getString(8));
                a.setPwd(rs.getString(4));
                a.setSecurityQ(rs.getString(5));
                a.setAnswer(rs.getString(6));
                a.setNumtel(rs.getString(9));
                a.setNomAgence(rs.getString(10));
                a.setRole(rs.getString(11));
               ;
                System.out.println(a.toString());   
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        return a;
    }  
    
    
    
    
    
    
    
     public List<User> rechercheragent(String nom) { 
        List<User> users=new ArrayList <User>();
        String sql ="select * from `User` where nom='"+nom+"'and role='Agent-Aerien';";
        try 
        {
            ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);          
            System.out.println("recherche avec succes");
            while(rs.next())
            {
                 User a = new User();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setAdresse(rs.getString(5));
                a.setPwd(rs.getString(6));
                a.setSecurityQ(rs.getString(7));
                a.setAnswer(rs.getString(8));
                a.setNumtel(rs.getString(9));
                a.setNomAgence(rs.getString(10));
                a.setRole(rs.getString(11));
                users.add(a);
                System.out.println(a.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return users ;
    }
     
       public List<User> rechercher(String nom) { 
        List<User> users=new ArrayList <User>();
        String sql ="select * from `User` where nom='"+nom+"'and role='Admin';";
        try 
        {
            ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);          
            System.out.println("recherche avec succes");
            while(rs.next())
            {
                 User a = new User();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setAdresse(rs.getString(5));
                a.setPwd(rs.getString(6));
                a.setSecurityQ(rs.getString(7));
                a.setAnswer(rs.getString(8));
                a.setNumtel(rs.getString(9));
                a.setNomAgence(rs.getString(10));
                a.setRole(rs.getString(11));
                users.add(a);
                System.out.println(a.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return users ;
    }
     
     
     
       public List<User> rechercherclient(String nom) { 
        List<User> users=new ArrayList <User>();
        String sql ="select * from `User` where nom='"+nom+"' and role='Client';";
        try 
        {
            ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);          
            System.out.println("recherche avec succes");
            while(rs.next())
            {
                 User a = new User();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setAdresse(rs.getString(5));
                a.setPwd(rs.getString(6));
                a.setSecurityQ(rs.getString(7));
                a.setAnswer(rs.getString(8));
                a.setNumtel(rs.getString(9));
                a.setNomAgence(rs.getString(10));
                a.setRole(rs.getString(11));
                users.add(a);
                System.out.println(a.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return users ;
    }
       
        public List<User> rechercheroffreur(String nom) { 
        List<User> users=new ArrayList <User>();
        String sql ="select * from `User` where nom='"+nom+"' and role='Offreur';";
        try 
        {
            ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);          
            System.out.println("recherche avec succes");
            while(rs.next())
            {
                 User a = new User();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setAdresse(rs.getString(5));
                a.setPwd(rs.getString(6));
                a.setSecurityQ(rs.getString(7));
                a.setAnswer(rs.getString(8));
                a.setNumtel(rs.getString(9));
                a.setNomAgence(rs.getString(10));
                a.setRole(rs.getString(11));
                users.add(a);
                System.out.println(a.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return users ;
    }
     
     
     
     
     
        public void BanirCompteParAdmin(int id)
    {
        String sql = "UPDATE user SET etat = '"+0+"' WHERE id ="+id+";";
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
        
        public void activerCompteParAdmin(int id) 
    {
        String sql = "UPDATE user SET etat = '"+1+"' WHERE id ="+id+";";
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
     public int   selectid(String email , String password ,String role ) {
         
        String req = "SELECT  id  FROM `user` WHERE  email='"+email+"' and role='"+role+"' " ;
     int i=0;
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
             i=rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return i;
    }
     
     
     public  User  selectmodifier( int idc ) {
       String sql= " Select * from `user` WHERE id= '"+idc+"' " ;
       User a = new User();
             try 
        {
            ste = conn.createStatement();
            ResultSet rs=ste.executeQuery(sql);          
           
            while(rs.next())
            {
                 
                a.setId(idc);
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setPwd(rs.getString(4));
                  a.setSecurityQ(rs.getString(5));
                a.setAnswer(rs.getString(6));
                a.setEmail(rs.getString(7));
                a.setAdresse(rs.getString(8));
                 a.setNumtel(rs.getString(9));
                a.setNomAgence(rs.getString(10));
                 a.setRole(rs.getString(11));
                 a.setSolde(rs.getFloat(13));
               
               
               
               
               
              
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

    @Override
    public List<User> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    
}
