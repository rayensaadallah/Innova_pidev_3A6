/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Entities.*;
import Entities.encryption;
import static Entities.encryption.ALGORITHM;
import static Entities.encryption.decrypt;
import static Entities.encryption.keyValue;
import Services.UserService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javax.crypto.spec.SecretKeySpec;
import Utilis.*;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierCompteClientController implements Initializable {

    @FXML
    private TextField txtnomCMod;
    @FXML
    private TextField txtemailCMOD;
    @FXML
    private TextField txtprenomCMOD;
    @FXML
    private PasswordField txtmdpCMOD;
       @FXML
    private TextField txtQS;

    @FXML
    private TextField txtRepCMod;
    @FXML
    private Button btnmodif;
    
    private Connection conn;
    private int idc;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button bReclamation;
    @FXML
    private Button btnOrders1;
    @FXML
    private Button btnSignout;
        @FXML
    private TextField txtnumtel;

    public void setIdc(int idc) {
        
        UserService cs =new UserService();
        System.out.println(idc);
    User c= cs.selectmodifier(idc);
     txtnomCMod.setText(c.getNom());
     txtprenomCMOD.setText(c.getPrenom());
     txtemailCMOD.setText(c.getEmail());
     txtmdpCMOD.setText(c.getPwd());
     txtQS.setText(c.getSecurityQ());
      txtRepCMod.setText(c.getAnswer());
      txtnumtel.setText(c.getNumtel());
        this.idc = idc;
    }
    

    /**
     * Initializes the controller class.
     */
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        UserService cs =new UserService();
    
    User c= cs.selectmodifier(idc);
     txtnomCMod.setText(c.getNom());
     txtprenomCMOD.setText(c.getPrenom());
        txtQS.setText(c.getSecurityQ());
      txtRepCMod.setText(c.getAnswer());
     
    }  
      @FXML
    void modifier(ActionEvent event) throws Exception {
          if(!(txtnomCMod.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre nom");
             }
          else  if(!(txtprenomCMOD.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre prenom");
             }
              
          else if(!(txtemailCMOD.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))) {
//              - commence par une lettre majuscule ou minuscule
//- suivi éventuellement d'une combinaison de lettres, chiffres, points, underscores ou tirets
//- suivi d'au moins un caractère ou chiffre
//- suivi d'une arobase
//- suivi d'au moins d'une lettre
//- suivi éventuellement d'une combinaison de lettres, chiffres, points, underscores ou tirets
//- suivi d'au moins d'une lettre
//- suivi d'un point
//- se termine par entre 2 et 4 lettres

            JOptionPane.showMessageDialog(null, "verifier votre email");
             }
          else if(!(txtRepCMod.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre reponse");
             }
              
            else if  (txtmdpCMOD.getText().length()<4) {

            JOptionPane.showMessageDialog(null, "votre mdp doit contenir au moins 4 characteres");
             }
             else if(!(txtnumtel.getText().matches("^[0-9]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre numero de telephone");
             }
            else{
            
                UserService cs =new UserService();
    User c= cs.selectmodifier(idc);
     c.setEmail(txtemailCMOD.getText());
        c.setNom( txtnomCMod.getText());
        c.setPrenom(txtprenomCMOD.getText());
        c.setNumtel(txtnumtel.getText());
              String mdpcry = encryption.encrypt(txtmdpCMOD.getText(),new SecretKeySpec(keyValue, ALGORITHM));      
       c.setPwd(mdpcry);
         decrypt(mdpcry,new SecretKeySpec(keyValue, ALGORITHM));
       
c.setAnswer(txtRepCMod.getText());
cs.modifier(c);
          JOptionPane.showMessageDialog(null, "votre compte est modifie avec succes");
            }

    
         
    }
    
    
    
        @FXML
    void reclamation(ActionEvent event) throws IOException {
      try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
		Parent root = loader.load();
		ReclamationController  e = loader.getController();
                e.setIdc(idc);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    void reserverActivite(ActionEvent event) {
         try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverActivite.fxml"));
		Parent root = loader.load();
		ReserverActiviteController  e = loader.getController();
                
           
                e.setIdclient(idc);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}

    }

    @FXML
    void reserverHebergement(ActionEvent event) {
           try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverHebergement.fxml"));
		Parent root = loader.load();
		ReserverHebergementController  e = loader.getController();
                e.setIdC(idc);
                
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}

    }

    @FXML
    void reserverVol(ActionEvent event) {
          try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverVol.fxml"));
		Parent root = loader.load();
		ReserverVolController  e = loader.getController();
                e.setIdclient(idc);
                
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}

    }

    @FXML
    void reserverVoyageO(ActionEvent event) {
         try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverVoyage.fxml"));
		Parent root = loader.load();
		ReserverVoyageController  e = loader.getController();
           
                e.setIdclient(idc);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}

    }
    
        @FXML
    void avis(ActionEvent event) {
        
           try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Avis.fxml"));
		Parent root = loader.load();
		GUI.AvisController  e = loader.getController();
                e.setIdclient(idc);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}

    }

    @FXML
    void consulterProfil(ActionEvent event) throws IOException {
        
        

    }

    @FXML
    void consulterReservation(ActionEvent event) {
         try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReservation.fxml"));
		Parent root = loader.load();
		AfficherReservationController  e = loader.getController();
                e.setIdc(idc);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }
    
       @FXML
    void logout(ActionEvent event) throws IOException {
//  Stage stage = new Stage();
 
//        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        Scene scene =new Scene(root);
//        stage.setScene(scene);
//        stage.show();

    
                      try{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
		Parent root = loader.load();
		LoginController  e = loader.getController();
               
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}


    }

    @FXML
    private void refresh(MouseEvent event) {
         UserService cs =new UserService();
    
    User c= cs.selectmodifier(idc);
     txtnomCMod.setText(c.getNom());
     txtprenomCMOD.setText(c.getPrenom());
     txtemailCMOD.setText(c.getEmail());
     txtmdpCMOD.setText(c.getPwd());
     txtQS.setText(c.getSecurityQ());
      txtRepCMod.setText(c.getAnswer());
       
        
    }
    
    
    
    
    
    
    
    
    
}
