/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.encryption;
import static Entities.encryption.ALGORITHM;
import static Entities.encryption.keyValue;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Services.*;
import javax.crypto.spec.SecretKeySpec;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class LoginController implements Initializable {

   String mdpn="";
    @FXML
    private TextField txtnom;

    @FXML
    private PasswordField txtmdp;

    @FXML
    private Button btncnx;
    
        @FXML
    private ComboBox combo;
           @FXML
    private Hyperlink linkmdpoublie;
            
                @FXML
    private Hyperlink linkcreerCompte;
     
             @FXML
    void mdpoublie(ActionEvent event) throws IOException {
 Parent root = FXMLLoader.load(getClass().getResource("mdpoub.fxml"));
  Stage stage = new Stage();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    

    @FXML
    void creernvCompte(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NvCompte.fxml"));
  Stage stage = new Stage();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
            
  
    
    

    @FXML
    void login(ActionEvent event) throws IOException, Exception  {
String nom = txtnom.getText();
String mdp = txtmdp.getText();
UserService us= new UserService();
if(nom.equals("") && mdp.equals("")||nom.equals("")||mdp.equals(""))
{
    JOptionPane.showMessageDialog(null, "veuillez remplir tous les champs vides");
}
else {
    try {
       
        mdpn=  BCrypt.hashpw(mdp.toString(),BCrypt.gensalt(13));
        System.out.println(mdp);
         String userpwd=us.UserByEmail(txtnom.getText()).getPwd().replace("$2y", "$2a");
               System.out.println(userpwd);
            System.out.println("input_password= "+ BCrypt.checkpw(txtmdp.getText(),userpwd));
        Class.forName("com.mysql.jdbc.Driver");
         Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/getaway", "root", "");
        if(combo.getSelectionModel().getSelectedItem().toString()=="Admin"){
      String sql="select * from user where email=?  and role=?";
       String role =combo.getSelectionModel().getSelectedItem().toString();
PreparedStatement pste=conn.prepareStatement(sql);
pste.setString(1,nom);
pste.setString(2,role);
 ResultSet rs = pste.executeQuery();
   if(BCrypt.checkpw(txtmdp.getText(),userpwd)&&rs.next()){
                JOptionPane.showMessageDialog(null, "admin and password matched");
                 txtnom.setText("");
                     txtmdp.setText("");
                 try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Registration.fxml"));
		Parent root = loader.load();
		RegistrationController ee = loader.getController();
               
                
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
  
        
                
               
            }else{
                    JOptionPane.showMessageDialog(null, "admin and password donot matched");
                    txtnom.setText("");
                     txtmdp.setText("");
           
                     
                    }
    }else if(combo.getSelectionModel().getSelectedItem().toString()=="Client"){
          String role =combo.getSelectionModel().getSelectedItem().toString();
         String sql="select * from user where email=?  and role=?";
PreparedStatement pste=conn.prepareStatement(sql);
pste.setString(1,nom);
pste.setString(2,role);
 ResultSet rs = pste.executeQuery();
            System.out.println("client"+BCrypt.checkpw(txtmdp.getText(),userpwd));
            
   if(rs.next()){
       if(rs.getInt("etat")==0){
          JOptionPane.showMessageDialog(null, "vous etes bloqué");
          txtnom.setText("");
          txtmdp.setText("");}
       else{
           if(BCrypt.checkpw(txtmdp.getText(),userpwd))
           {  JOptionPane.showMessageDialog(null, "client and password matched");
//                txtnom.setText("");
//                     txtmdp.setText("");
                     
                      try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientD.fxml"));
		Parent root = loader.load();
		ClientDController  ee = loader.getController();
                
                int i=us.selectid(txtnom.getText(),mdpn,"Client");
                          System.out.println("iddd"+i);
                ee.setIdc(i);
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
           }
           else
           {
               JOptionPane.showMessageDialog(null, "client and password donot matched");
                    txtnom.setText("");
                     txtmdp.setText("");
           }
//                    }    Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));
//        Scene scene =new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//        rs.getInt("id");
       }      
            }else{
                    JOptionPane.showMessageDialog(null, "client and password donot matched");
                    txtnom.setText("");
                     txtmdp.setText("");
                    }
    }else if(combo.getSelectionModel().getSelectedItem().toString()=="Offreur"){
               String role =combo.getSelectionModel().getSelectedItem().toString();
         String sql="select * from user where email=? and role=?";
PreparedStatement pste=conn.prepareStatement(sql);
pste.setString(1,nom);
pste.setString(2,role);
 ResultSet rs = pste.executeQuery();
   if(BCrypt.checkpw(txtmdp.getText(),userpwd)&&rs.next()){
                JOptionPane.showMessageDialog(null, "Offreur and password matched"); 
     try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCompteOffreur.fxml"));
		Parent root = loader.load();
		ModifierCompteOffreurController  ee = loader.getController();
                
                int i=us.selectid(txtnom.getText(),mdpn,"Offreur");
                ee.setIdc(i);
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
               
            }else{
                    JOptionPane.showMessageDialog(null, "Offreur and password donot matched");
                    txtnom.setText("");
                     txtmdp.setText("");
                    }
    }else if(combo.getSelectionModel().getSelectedItem().toString()=="Agent-Aerien"){ 
              String sql="select * from user where email=? and role=?";
               String role =combo.getSelectionModel().getSelectedItem().toString();
PreparedStatement pste=conn.prepareStatement(sql);
pste.setString(1,nom);
pste.setString(2,role);
 ResultSet rs = pste.executeQuery();
   if(BCrypt.checkpw(txtmdp.getText(),userpwd)&&rs.next()){
                JOptionPane.showMessageDialog(null, "agent-aerien and password matched");
//                 txtnom.setText("");
//                     txtmdp.setText("");

 try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCompteAgent.fxml"));
		Parent root = loader.load();
		ModifierCompteAgentController  ee = loader.getController();
               
                int i=us.selectid(txtnom.getText(),mdpn,"Agent-Aerien");
                ee.setIdc(i);
                
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
               
            }else{
                    JOptionPane.showMessageDialog(null, "agent-aerien and password donot matched");
                    txtnom.setText("");
                     txtmdp.setText("");
                    }
    }
         
         
         
         
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
    }
           



}
    }
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Admin","Client","Offreur","Agent-Aerien");
        combo.setItems(list);
    
    }
//
//public int getIdClientConn()
//{
//
//}


    
    
}
