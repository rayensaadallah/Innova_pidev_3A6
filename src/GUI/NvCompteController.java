/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Client;
import entities.encryption;
import static entities.encryption.ALGORITHM;
import static entities.encryption.decrypt;
import static entities.encryption.keyValue;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import services.ClientService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class NvCompteController implements Initializable {
    @FXML
    private Button btnvalider;

    @FXML
    private TextField txtnomNVC;

    @FXML
    private TextField txtprenomNVC;

    @FXML
    private TextField txtemailNVC;

    @FXML
    private TextField txtrepNVC;

    @FXML
    private PasswordField txtmdpNVC;

    @FXML
    private ComboBox combosecNVC;
ClientService cs = new ClientService();
    @FXML
    void validerCompte(ActionEvent event) throws Exception {
        
        if((txtnomNVC.getText().length()==0)||(txtprenomNVC.getText().length()==0)||(txtemailNVC.getText().length()==0)||(txtrepNVC.getText().length()==0)||(txtmdpNVC.getText().length()==0))
             JOptionPane.showMessageDialog(null, "verifier vos champs");
       else if(!(txtnomNVC.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre nom");
             }
          else  if(!(txtprenomNVC.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre prenom");
             }
              
          else if(!(txtemailNVC.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))) {
 JOptionPane.showMessageDialog(null, "verifier votre email");
             }
          else if(!(txtrepNVC.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre reponse");
             }
              
            else if  (txtmdpNVC.getText().length()<4) {

            JOptionPane.showMessageDialog(null, "votre mdp doit contenir au moins 4 characteres");
             }
            else{
                
                   String mdpcry = encryption.encrypt(txtmdpNVC.getText(),new SecretKeySpec(keyValue, ALGORITHM));      
               Client c= new Client(combosecNVC.getSelectionModel().getSelectedItem().toString(), txtrepNVC.getText(), txtnomNVC.getText(), txtprenomNVC.getText(),mdpcry, txtemailNVC.getText());
         decrypt(mdpcry,new SecretKeySpec(keyValue, ALGORITHM));
          cs.ajouter(c);
        JOptionPane.showMessageDialog(null, "votre compte est cree avec succes");
          }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> list1 = FXCollections.observableArrayList("votre premiere voiture","pays de ton reve","ton idole");
        combosecNVC.setItems(list1);
    }    
    
}
