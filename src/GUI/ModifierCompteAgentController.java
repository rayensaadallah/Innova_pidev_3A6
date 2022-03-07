/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.*;
import Entities.encryption;
import static Entities.encryption.ALGORITHM;
import static Entities.encryption.decrypt;
import static Entities.encryption.keyValue;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.crypto.spec.SecretKeySpec;
import Services.AgentAerienService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierCompteAgentController implements Initializable {
    
        
private int ida;
     public void setIdc(int ida) {
              this.ida= ida;
              AgentAerienService as =new AgentAerienService();
         System.out.println("houniiiaaaaaaaaaaaa");
    AgentAerien a= as.selectmodifier(ida);
     txtnom.setText(a.getNom());
     txtprenom.setText(a.getPrenom());
     txtemail.setText(a.getEmail());
     txtmdp.setText(a.getPwd());
     txtnomAgence.setText(a.getNomAgence());
//        System.out.println(ida);
        this.ida= ida;
    }
    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtprenom;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtnomAgence;
    @FXML
    private PasswordField txtmdp;
   

    @FXML
    private Button btnmodif;

    @FXML
    void modifier(ActionEvent event) throws Exception {
        AgentAerienService as =new AgentAerienService();
    AgentAerien a= as.selectmodifier(ida);
     a.setEmail(txtemail.getText());
        a.setNom( txtnom.getText());
        a.setPrenom(txtprenom.getText());
              String mdpcry = encryption.encrypt(txtmdp.getText(),new SecretKeySpec(keyValue, ALGORITHM));      
       a.setPwd(mdpcry);
         decrypt(mdpcry,new SecretKeySpec(keyValue, ALGORITHM));
        a.setNomAgence(txtnomAgence.getText());

as.modifier(a);
    }
    
    
    
     @FXML
    private Button btnConsPRO;

    @FXML
    private Button btnGestVol;

    @FXML
    private Button btnSignout;

    @FXML
    void GestVol(ActionEvent event) {

    }

    @FXML
    void consulterProfil(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        try{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
		Parent root = loader.load();
		LoginController  e = loader.getController();
               
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
