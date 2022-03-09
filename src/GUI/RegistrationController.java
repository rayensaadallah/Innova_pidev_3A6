/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.*;
import Entities.Client;
import Entities.Offreur;
import Entities.Reclamation;
import Entities.encryption;
import Entities.*;
import static Entities.encryption.ALGORITHM;
import static Entities.encryption.decrypt;
import static Entities.encryption.keyValue;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import Services.*;
import Services.ClientService;
import Services.ReclamationService;
import Utilis.Datasource;
import Services.OffreurService;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import Utilis.*;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class RegistrationController implements Initializable {
private Connection conn;
    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtprenom;

    @FXML
    private TextField txtadr;

    @FXML
    private TextField txtemail;

    @FXML
    private TableView<Admin> tvadmin;


    @FXML
    private TableColumn<Admin, String> colnom;

    @FXML
    private TableColumn<Admin, String> colprenom;

    @FXML
    private TableColumn<Admin, String> coladr;

    @FXML
    private TableColumn<Admin, String> colemail;


    @FXML
    private Button btnajout;

    @FXML
    private Button btnmodif;

    @FXML
    private Button btnsupp;

    @FXML
    private PasswordField txtmdp;
    
    @FXML
    private TextField txtrech;

    @FXML
    private Button btnrech;
    
     @FXML
    private TextField txtnomC;


    @FXML
    private TextField txtprenomC;

    @FXML
    private TextField txtemailC;
    
    @FXML
    private ComboBox combosecurity;

    @FXML
    private TextField txtrep;

    @FXML
    private TableView<Client> tvClient;

    @FXML
    private TableColumn<Client, String> colnomC;

    @FXML
    private TableColumn<Client, String> colprenomC;

    @FXML
    private TableColumn<Client, String> colemailC;

    @FXML
    private TableColumn<Client, Integer> coletatC;

    @FXML
    private TextField txtrechClient;

    @FXML
    private Button btnrechClient;

    @FXML
    private Button btnajouterC;


    @FXML
    private Button btnsuppC;
    @FXML
    private PasswordField txtmdpC;
    
    @FXML
    private Button btnBloquer;

    @FXML
    private Button btnActiver;
        @FXML
    private TableColumn<Offreur, String> colnomOF;

    @FXML
    private TableColumn<Offreur, String> colprenomOF;

    @FXML
    private TableColumn<Offreur, String> colemailOF;

    @FXML
    private TableColumn<Offreur, Integer> colTelOF;

    @FXML
    private TextField txtrechOF;

    @FXML
    private Button btnrechOF;

    @FXML
    private Button btnajouterOF;

    @FXML
    private Button btnmodifierOF;

    @FXML
    private Button btnsuppOF;

    @FXML
    private PasswordField txtmdpOF;

    @FXML
    private TextField txtTelOF;
        @FXML
    private TextField txtnomOF;

    @FXML
    private TextField txtprenomOF;

    @FXML
    private TextField txtemailOF;
    
    @FXML
    private TableView<Offreur> tvOffreur;
    
    //agent aerien

 @FXML
    private TextField txtnomAG;

    @FXML
    private TextField txtprenomAG;

    @FXML
    private TextField txtemailAG;
    
    @FXML
    private PasswordField txtmdpAG;

    @FXML
    private TextField txtNomAgence; 
    @FXML
    private TextField txtrechAG;

    @FXML
    private TableView<AgentAerien> tvAgent;

    @FXML
    private TableColumn<AgentAerien, String> colnomAG;

    @FXML
    private TableColumn<AgentAerien, String> colprenomAG;

    @FXML
    private TableColumn<AgentAerien, String> colemailAG;

    @FXML
    private TableColumn<AgentAerien, String> colNomAgence;

    @FXML
    private Button btnrechAG;

    @FXML
    private Button btnajouterAG;

    @FXML
    private Button btnmodifierAG;

    @FXML
    private Button btnsuppAG;

    
    @FXML
    private Button btnActualiser;
    
    @FXML
    private TextField txtnbtot;
    
        @FXML
    private TableView<Reclamation> tvRC;

    @FXML
    private TableColumn<Reclamation, String> colObj;

    @FXML
    private TableColumn<Reclamation, String> colDesc;

    @FXML
    private TableColumn<Reclamation, Integer> colEtat;

    @FXML
    private Button btnTraiter;
    
     @FXML
    private Button btngestU;

    @FXML
    private Button btngestVo;


    @FXML
    private Button btnStat;

    @FXML
    private Button btnlogout;
    private int idadmin;
    @FXML
    private Button btnCapturer;
    @FXML
    private ImageView display;
    @FXML
    private TextField txtnumtel;
    @FXML
    private Button butAct;
    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
        
    }
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
      afficherAdmin();
      afficherClient();
      afficherOffreur();
       afficherAgent();
       nbtotalAdmin();
       afficherReclamation() ;
       
        ObservableList<String> list1 = FXCollections.observableArrayList("votre premiere voiture","pays de ton reve","ton idole");
        combosecurity.setItems(list1);
       
    }   

    public RegistrationController() {
        
         conn = Datasource.getInstance().getCnx();
    }
    
    ObservableList<Reclamation> oblist5 = FXCollections.observableArrayList();
    ReclamationService rs =new ReclamationService();
    
         private void afficherReclamation() {
      List <Reclamation> ls =rs.afficher();
      ls.forEach(e->oblist5.add(e));
      colObj.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("objet"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
         colEtat.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("etat"));
          tvRC.setItems(oblist5);    
    }
  
        @FXML
    void traiter(ActionEvent event) {
    Reclamation r =  tvRC.getSelectionModel().getSelectedItem();
        int idr=r.getIdR();
as.traiter(idr);
tvRC.getItems().clear();
afficherReclamation();
    }
        

    
    
    
    //********************************admin*******************************
   
    ObservableList<Admin> oblist1 = FXCollections.observableArrayList();
     AdminService as= new AdminService();
     
        @FXML
    void Actualiser(ActionEvent event) {
nbtotalAdmin();
    }
    
    
    public void nbtotalAdmin(){
    try {
        PreparedStatement pst=conn.prepareStatement("select count(id) from admin");
      ResultSet rs=pst.executeQuery();
      while(rs.next())
      {
      int calcul = rs.getInt("count(id)");
      txtnbtot.setText(String.valueOf(calcul));
      }
        
    } catch (SQLException ex) {
        Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    }
     
     
     
     
    
     private void afficherAdmin() {
      List <Admin> ls =as.afficher();
      ls.forEach(e->oblist1.add(e));
      colnom.setCellValueFactory(new PropertyValueFactory<Admin,String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Admin, String>("prenom"));
         colemail.setCellValueFactory(new PropertyValueFactory<Admin, String>("email"));
        coladr.setCellValueFactory(new PropertyValueFactory<Admin, String>("adresse"));
        tvadmin.setItems(oblist1);    
    }
     
     
    @FXML
    void ajouter(ActionEvent event) throws Exception {
        if ((txtnom.getText().length()==0)|| (txtprenom.getText().length()==0)||(txtadr.getText().length()==0) ||(txtemail.getText().length()==0)||(txtmdp.getText().length()==0))
           JOptionPane.showMessageDialog(null, "verifier vos champs "); 

        else if(!(txtnom.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre nom");
             }
          else if(!(txtprenom.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre prenom");
             }
             else if(!(txtadr.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre adresse");
             }
           else if(!(txtemail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$")))  {

            JOptionPane.showMessageDialog(null, "verifier votre email");
             }
              
           else if  (txtmdp.getText().length()<4) {

            JOptionPane.showMessageDialog(null, "votre mdp doit contenir au moins 4 characteres");
             }
             else{
                    String mdpcry = encryption.encrypt(txtmdp.getText(),new SecretKeySpec(keyValue, ALGORITHM));
      Admin a= new Admin(txtnom.getText(), txtprenom.getText(), txtemail.getText(), txtadr.getText(), mdpcry);
decrypt(mdpcry,new SecretKeySpec(keyValue, ALGORITHM));
as.ajouter(a);
tvadmin.getItems().clear();
afficherAdmin();
txtnom.setText("");
txtprenom.setText("");
txtadr.setText("");
   txtemail.setText("");
   txtmdp.setText("");         
           
           
           }
    }
    
    

    @FXML
    void modifier(ActionEvent event) throws Exception {

       Admin a =  tvadmin.getSelectionModel().getSelectedItem();
    
            if(!(txtnom.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre nom");
             }
             else if(!(txtprenom.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre prenom");
             }
             else if(!(txtadr.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre adresse");
             }
       else if  (txtmdp.getText().length()<4||(txtmdp.getText().length()==0)) {

            JOptionPane.showMessageDialog(null, "votre mdp doit contenir au moins 4 characteres ");
             }
            else if(!(txtemail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$")))  {

            JOptionPane.showMessageDialog(null, "verifier votre email");
             }
        
         
         else{
        a.setEmail(txtemail.getText());
        a.setNom( txtnom.getText());
        a.setPrenom(txtprenom.getText());
        a.setAdresse(txtadr.getText());
           String mdpcry = encryption.encrypt(txtmdp.getText(),new SecretKeySpec(keyValue, ALGORITHM));
        a.setPwd(mdpcry);
        decrypt(mdpcry,new SecretKeySpec(keyValue, ALGORITHM));
        as.modifier(a);
        tvadmin.getItems().clear();
        afficherAdmin();
        txtnom.setText("");
txtprenom.setText("");
txtadr.setText("");
   txtemail.setText("");
   txtmdp.setText("");
          }
    }
    
    

    @FXML
    void supprimer(ActionEvent event) {

Admin a =  tvadmin.getSelectionModel().getSelectedItem();
String pwd=a.getPwd();
as.supprimer(a.getId());
tvadmin.getItems().clear();
afficherAdmin();
txtnom.setText("");
txtprenom.setText("");
txtadr.setText("");
   txtemail.setText("");
   txtmdp.setText("");
   
    }
    
    
      @FXML
    void rechercher(ActionEvent event) {

List <Admin> ls =as.rechercher(txtrech.getText());
tvadmin.getItems().clear();
  tvadmin.getItems().addAll(ls);
    }

       
    
    @FXML
   private void selectAdmin(javafx.scene.input.MouseEvent event) {
     int index= -1;
     index = tvadmin.getSelectionModel().getSelectedIndex();
     txtnom.setText(colnom.getCellData(index));
     txtprenom.setText(""+  colprenom.getCellData(index));
     txtemail.setText(""+colemail.getCellData(index));
    txtadr.setText(""+coladr.getCellData(index));
      String pwd=tvadmin.getSelectionModel().getSelectedItem().getPwd();
       txtmdp.setText(pwd);
      
    }
    
    
   
    //***********************client**************************** 
   
   
   ObservableList<Client> oblist = FXCollections.observableArrayList();
     ClientService cs= new ClientService();
        @FXML
    void Activer(ActionEvent event) {
        Client c =  tvClient.getSelectionModel().getSelectedItem();
        int idCB=c.getId();
as.activerCompteParAdmin(idCB);
tvClient.getItems().clear();
afficherClient();
    }

    @FXML
    void Bloquer(ActionEvent event) {
           Client c =  tvClient.getSelectionModel().getSelectedItem();
        int idCB=c.getId();
as.BanirCompteParAdmin(idCB);
tvClient.getItems().clear();
afficherClient();
    } 
     
   @FXML
    void ajouterC(ActionEvent event) throws Exception { 
        if((txtnomC.getText().length()==0)||(txtprenomC.getText().length()==0)||(txtemailC.getText().length()==0)||(txtrep.getText().length()==0)||(txtmdpC.getText().length()==0))
             JOptionPane.showMessageDialog(null, "verifier vos champs");
       else if(!(txtnomC.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre nom");
             }
          else  if(!(txtprenomC.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre prenom");
             }
              
          else if(!(txtemailC.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
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
          else if(!(txtrep.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre reponse");
             }
              
            else if  (txtmdpC.getText().length()<4) {

            JOptionPane.showMessageDialog(null, "votre mdp doit contenir au moins 4 characteres");
             }
             
             else{
                 
 
           String mdpcry = encryption.encrypt(txtmdpC.getText(),new SecretKeySpec(keyValue, ALGORITHM));   
           Client c = new Client(combosecurity.getSelectionModel().getSelectedItem().toString(), txtrep.getText(),txtnumtel.getText(),  txtnomC.getText(), txtprenomC.getText(),mdpcry, txtemailC.getText());
       
//         Client c= new Client(combosecurity.getSelectionModel().getSelectedItem().toString(), txtrep.getText(), txtnomC.getText(), txtprenomC.getText(),mdpcry, txtemailC.getText(),txtnumtel.getText());
         decrypt(mdpcry,new SecretKeySpec(keyValue, ALGORITHM));
cs.ajouter(c);
tvClient.getItems().clear();
afficherClient();
txtnomC.setText("");
txtprenomC.setText("");

   txtemailC.setText("");
   txtmdpC.setText("");
   txtrep.setText("");
   txtnumtel.setText("");
             }
}

    
//    @FXML
//    void modifierC(ActionEvent event) {
//        Client c =  tvClient.getSelectionModel().getSelectedItem();
//           if  (txtmdpC.getText().length()<4||(txtmdpC.getText().length()==0)) {
//
//            JOptionPane.showMessageDialog(null, "votre mdp doit contenir au moins 4 characteres ");
//             }
//             if(!(txtrep.getText().matches("^[a-zA-Z@.]+$"))||(txtrep.getText().length()==0)) {
//
//            JOptionPane.showMessageDialog(null, "verifier votre reponse");
//             }
//else{
//        c.setEmail(txtemailC.getText());
//        c.setNom( txtnomC.getText());
//        c.setPrenom(txtprenomC.getText());  
//        c.setSecurityQ(combosecurity.getSelectionModel().getSelectedItem().toString());
//        c.setAnswer(txtrep.getText());
//cs.modifier(c);
//tvClient.getItems().clear();
//afficherClient();
//           }
//    }
    
    

    @FXML
    void supprimerC(ActionEvent event) {
Client c =  tvClient.getSelectionModel().getSelectedItem();
cs.supprimer(c.getId());
tvClient.getItems().clear();
afficherClient();
txtnomC.setText("");
txtprenomC.setText("");

   txtemailC.setText("");
   txtmdpC.setText("");
   txtrep.setText("");
   txtnumtel.setText("");
}
    
    

    @FXML
    void rechercherClient(ActionEvent event) {
List <Client> ls =cs.rechercherclient(txtrechClient.getText());
tvClient.getItems().clear();
  tvClient.getItems().addAll(ls);
  }

 
     
   private void afficherClient() {
      List <Client> ls =cs.afficher();
      ls.forEach(e->oblist.add(e));
      colnomC.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        colprenomC.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
         colemailC.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        coletatC.setCellValueFactory(new PropertyValueFactory<Client, Integer>("etat"));
        tvClient.setItems(oblist);    
    }
       
   
     @FXML
   private void selectClient(javafx.scene.input.MouseEvent event) {
     int index= -1;
     index = tvClient.getSelectionModel().getSelectedIndex();
     txtnomC.setText(colnomC.getCellData(index));
     txtprenomC.setText(""+  colprenomC.getCellData(index));
     txtemailC.setText(""+colemailC.getCellData(index));
      String pwd=tvClient.getSelectionModel().getSelectedItem().getPwd();
      String rep =tvClient.getSelectionModel().getSelectedItem().getAnswer();
      String tel =tvClient.getSelectionModel().getSelectedItem().getNumtel();
       txtmdpC.setText(pwd);
     txtrep.setText(rep);
     txtnumtel.setText(tel);
     ObservableList<String> list1 = FXCollections.observableArrayList("veuillez choisir une question","votre premiere voiture","pays de ton reve","ton idole");
        combosecurity.setItems(list1);
       
             
              
              
              
      
    }
      //***************Offreur*****************************
   
ObservableList<Offreur> oblist2 = FXCollections.observableArrayList();
     OffreurService os= new OffreurService();
   
    @FXML
   private void selectOffreur(javafx.scene.input.MouseEvent event) {
     int index= -1;
     index = tvOffreur.getSelectionModel().getSelectedIndex();
     txtnomOF.setText(colnomOF.getCellData(index));
     txtprenomOF.setText(""+  colprenomOF.getCellData(index));
     txtemailOF.setText(""+colemailOF.getCellData(index));
     txtTelOF.setText(""+colTelOF.getCellData(index));
     
           String pwd=tvOffreur.getSelectionModel().getSelectedItem().getPwd();
       txtmdpOF.setText(pwd);
      
    }
    
       private void afficherOffreur() {
      List <Offreur> ls =os.afficher();
      ls.forEach(e->oblist2.add(e));
      colnomOF.setCellValueFactory(new PropertyValueFactory<Offreur,String>("nom"));
        colprenomOF.setCellValueFactory(new PropertyValueFactory<Offreur, String>("prenom"));
         colemailOF.setCellValueFactory(new PropertyValueFactory<Offreur, String>("email"));
        colTelOF.setCellValueFactory(new PropertyValueFactory<Offreur, Integer>("numtl"));
        tvOffreur.setItems(oblist2);    
    }
    
   
       @FXML
    void ajouterO(ActionEvent event) throws Exception {
        if((txtnomOF.getText().length()==0)||(txtprenomOF.getText().length()==0)||(txtemailOF.getText().length()==0)||(txtmdpOF.getText().length()==0)||(txtTelOF.getText().length()==0))
            JOptionPane.showMessageDialog(null, "verifier vos champs");
           else if(!(txtnomOF.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre nom");
             }
           else if(!(txtprenomOF.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre prenom");
             }
              
             else if(!(txtemailOF.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$")))  {

            JOptionPane.showMessageDialog(null, "verifier votre email");
             }
              
            else if  (txtmdpOF.getText().length()<4) {

            JOptionPane.showMessageDialog(null, "votre mdp doit contenir au moins 4 characteres");
             }
            else  if  (!(txtTelOF.getText().matches("^[0-9]+$"))||txtTelOF.getText().length()<8) {

            JOptionPane.showMessageDialog(null, "verifier votre numero de telephone");
             }
             else{
 
 String mdpcry = encryption.encrypt(txtmdpOF.getText(),new SecretKeySpec(keyValue, ALGORITHM));
         int num = Integer.parseInt(txtTelOF.getText());
         Offreur o = new Offreur(txtnomOF.getText(), txtprenomOF.getText(), mdpcry, txtemailOF.getText(),num);
         decrypt(mdpcry,new SecretKeySpec(keyValue, ALGORITHM));
os.ajouter(o);
tvOffreur.getItems().clear();
afficherOffreur();
txtnomOF.setText("");
txtprenomOF.setText("");

   txtemailOF.setText("");
   txtmdpOF.setText("");
   txtTelOF.setText("");
             }   
    }
    
    @FXML
    void modifierO(ActionEvent event) throws Exception {
        Offreur o =  tvOffreur.getSelectionModel().getSelectedItem();
         if(!(txtnomOF.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre nom");
             }
           else if(!(txtprenomOF.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre prenom");
             }
              
             else if(!(txtemailOF.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$")))  {

            JOptionPane.showMessageDialog(null, "verifier votre email");
             }
              
            else if  (txtmdpOF.getText().length()<4) {

            JOptionPane.showMessageDialog(null, "votre mdp doit contenir au moins 4 characteres");
             }
            else  if  (!(txtTelOF.getText().matches("^[0-9]+$"))||txtTelOF.getText().length()<8) {

            JOptionPane.showMessageDialog(null, "verifier votre numero de telephone");
             }
         else{
        o.setEmail(txtemailOF.getText());
        o.setNom( txtnomOF.getText());
        o.setPrenom(txtprenomOF.getText());    
        o.setNumtl(Integer.parseInt(txtTelOF.getText()));
        String mdpcry = encryption.encrypt(txtmdpOF.getText(),new SecretKeySpec(keyValue, ALGORITHM));
        o.setPwd(mdpcry);
        decrypt(mdpcry,new SecretKeySpec(keyValue, ALGORITHM));

        
os.modifier(o);
tvOffreur.getItems().clear();
afficherOffreur();
txtnomOF.setText("");
txtprenomOF.setText("");

   txtemailOF.setText("");
   txtmdpOF.setText("");
   txtTelOF.setText("");
         }
        
    }
       @FXML
    void supprimerO(ActionEvent event) {
Offreur o =  tvOffreur.getSelectionModel().getSelectedItem();
os.supprimer(o.getId());
tvOffreur.getItems().clear();
afficherOffreur();
txtnomOF.setText("");
txtprenomOF.setText("");

   txtemailOF.setText("");
   txtmdpOF.setText("");
   txtTelOF.setText("");
    }
        @FXML
    void rechercherO(ActionEvent event) {
List <Offreur> ls =os.rechercheroffreur(txtrechOF.getText());
tvOffreur.getItems().clear();
  tvOffreur.getItems().addAll(ls);
    }
    
    
    
    //*******************************agent aerien*************************************************
    
   ObservableList<AgentAerien> oblist3 = FXCollections.observableArrayList();
     AgentAerienService ags= new AgentAerienService(); 
     
         @FXML
    void ajouterAG(ActionEvent event) throws Exception {
        if((txtnomAG.getText().length()==0)||(txtprenomAG.getText().length()==0)||(txtemailAG.getText().length()==0)||(txtmdpAG.getText().length()==0)||(txtNomAgence.getText().length()==0))
            JOptionPane.showMessageDialog(null, "verifier vos champs"); 
        else if(!(txtnomAG.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre nom");
             }
           else if(!(txtprenomAG.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre prenom");
             }
              
            else if(!(txtemailAG.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$")))  {

            JOptionPane.showMessageDialog(null, "verifier votre email");
             }
              
            else if  (txtmdpAG.getText().length()<4) {

            JOptionPane.showMessageDialog(null, "votre mdp doit contenir au moins 4 characteres");
             }
              else if(!(txtNomAgence.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre nom d'agence ");
             }
             else{
       
 String mdpcry = encryption.encrypt(txtmdpAG.getText(),new SecretKeySpec(keyValue, ALGORITHM));
AgentAerien ag = new AgentAerien(txtnomAG.getText(), txtprenomAG.getText(), mdpcry, txtemailAG.getText(), txtNomAgence.getText());
decrypt(mdpcry,new SecretKeySpec(keyValue, ALGORITHM));
ags.ajouter(ag);
tvAgent.getItems().clear();
afficherAgent();
txtnomAG.setText("");
txtprenomAG.setText("");

   txtemailAG.setText("");
   txtmdpAG.setText("");
  txtNomAgence.setText("");
             }   
    }
    
    @FXML
    void modifierAG(ActionEvent event) throws Exception {
      AgentAerien ag =  tvAgent.getSelectionModel().getSelectedItem();
        if(!(txtnomAG.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre nom");
             }
           else if(!(txtprenomAG.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre prenom");
             }
              
            else if(!(txtemailAG.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$")))  {

            JOptionPane.showMessageDialog(null, "verifier votre email");
             }
              
            else if  (txtmdpAG.getText().length()<4) {

            JOptionPane.showMessageDialog(null, "votre mdp doit contenir au moins 4 characteres");
             }
              else if(!(txtNomAgence.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre nom d'agence ");
             }
         else{
        ag.setEmail(txtemailAG.getText());
        ag.setNom( txtnomAG.getText());
        ag.setPrenom(txtprenomAG.getText()); 
        String mdpcry = encryption.encrypt(txtmdpAG.getText(),new SecretKeySpec(keyValue, ALGORITHM));
        ag.setPwd(mdpcry);
        decrypt(mdpcry,new SecretKeySpec(keyValue, ALGORITHM));
        ag.setNomAgence(txtNomAgence.getText());
        
ags.modifier(ag);
tvAgent.getItems().clear();
afficherAgent();
txtnomAG.setText("");
txtprenomAG.setText("");

   txtemailAG.setText("");
   txtmdpAG.setText("");
  txtNomAgence.setText("");
         }
    }
    
        @FXML
    void rechercherAG(ActionEvent event) {
List <AgentAerien> ls =ags.rechercheragent(txtrechAG.getText());
tvAgent.getItems().clear();
  tvAgent.getItems().addAll(ls);
    }
    
    
        @FXML
    void selectAgent(MouseEvent event) {
     int index= -1;
     index = tvAgent.getSelectionModel().getSelectedIndex();
     txtnomAG.setText(colnomAG.getCellData(index));
     txtprenomAG.setText(""+  colprenomAG.getCellData(index));
     txtemailAG.setText(""+colemailAG.getCellData(index));
     txtNomAgence.setText(""+colNomAgence.getCellData(index));
    
       String pwd=tvAgent.getSelectionModel().getSelectedItem().getPwd();
       txtmdpAG.setText(pwd);
     
    }
    
        private void afficherAgent() {
      List <AgentAerien> ls =ags.afficher();
      ls.forEach(e->oblist3.add(e));
      colnomAG.setCellValueFactory(new PropertyValueFactory<AgentAerien,String>("nom"));
        colprenomAG.setCellValueFactory(new PropertyValueFactory<AgentAerien, String>("prenom"));
         colemailAG.setCellValueFactory(new PropertyValueFactory<AgentAerien, String>("email"));
       colNomAgence.setCellValueFactory(new PropertyValueFactory<AgentAerien, String>("nomAgence"));
        tvAgent.setItems(oblist3);    
    }
    
    @FXML
    void supprimerAG(ActionEvent event) {
AgentAerien ag =  tvAgent.getSelectionModel().getSelectedItem();
ags.supprimer(ag.getId());
tvAgent.getItems().clear();
afficherAgent();
txtnomAG.setText("");
txtprenomAG.setText("");

   txtemailAG.setText("");
   txtmdpAG.setText("");
  txtNomAgence.setText("");
    }
    
    
    //bar
      @FXML
    void GestionU(ActionEvent event) throws IOException {

    }
    
     void gestAct(ActionEvent event) {
       

    }

    @FXML
    void gestVo(ActionEvent event) {
       try{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("MainFXML.fxml"));
		Parent root = loader.load();
		VoyOrgFXMLController  e = loader.getController();
               
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}  

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
    
    @FXML
    void statistique(ActionEvent event) {
        
        try{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("StatR.fxml"));
		Parent root = loader.load();
		StatRController  e = loader.getController();
               e.setIdA(idadmin);
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}

    }

    @FXML
    private void capturer(ActionEvent event) {
           try{
            Robot robot =new Robot();
            Rectangle rectangle = new Rectangle(100,100,1200,600);
            BufferedImage image = robot.createScreenCapture(rectangle);
        Image myImage = SwingFXUtils.toFXImage(image, null);
        display.setImage(myImage);
        ImageIO.write(image, "jpg", new File("capture/out.jpg"));
      JOptionPane.showMessageDialog(null, "capture avec succes");
        
        
        }catch(Exception ex){
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        
    }

    @FXML
    private void gestiondesA(ActionEvent event) {
        
         try{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Gestionactadmin.fxml"));
		Parent root = loader.load();
		GestionactadminController  e = loader.getController();
               
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		} 
    }

    
    
    
     
   
    
}
