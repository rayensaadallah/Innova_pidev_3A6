/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.*;


import Services.ActiviteService;
import Services.AvisService;
import Utilis.Datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author TheBoss'07
 */
public class AvisController implements Initializable {

    @FXML
    private TableView<Activite> tvavisa;
    @FXML
    private TableColumn<Activite, String> colnomac;
    @FXML
    private TableColumn<Activite, String> coldescripac;
    @FXML
    private TextField txtnoma;
    @FXML
    private TableView<Avis> tvavis;
    @FXML
    private TableColumn<Avis, String> colmessage;
    @FXML
    private TableColumn<Avis, String> coldateav;
    @FXML
    private TableColumn<Activite, String> colnomcl;
    @FXML
    private TableColumn<?, ?> colnomact;
    @FXML
    private Button btnmodifierav;
    @FXML
    private Button btnsupprimerav;
    @FXML
    private Button btnviderav;
    @FXML
    private ComboBox<String> txtcomment;
    @FXML
    private Button btnajouterav;
    @FXML
    private Button btnstat;
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
    private int idclient;

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       affichageAv();
      AffActAv();
    }    
    
    private Connection con = Datasource.getInstance().getCnx();
AvisService avs = new AvisService();
ActiviteService as = new ActiviteService();

ObservableList<Avis> oblistav = FXCollections.observableArrayList();
ObservableList<Activite> oblist = FXCollections.observableArrayList();


    @FXML
    private void consulterProfil(ActionEvent event) {
    }

    @FXML
    private void reserverVol(ActionEvent event) {
    }

    @FXML
    private void reserverHebergement(ActionEvent event) {
    }

    @FXML
    private void reserverVoyageO(ActionEvent event) {
    }

    @FXML
    private void reserverActivite(ActionEvent event) {
    }

    @FXML
    private void consulterReservation(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void avis(ActionEvent event) {
    }
    
    
       
    public void affichageAv() {
        List<Avis> avis = avs.afficher();
        avis.forEach(e->oblistav.add(e));
        Activite a = new Activite ();

        colmessage.setCellValueFactory(new PropertyValueFactory<>("Message"));
        coldateav.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colnomcl.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colnomact.setCellValueFactory(new PropertyValueFactory<>("RefActivite"));
        
        
        tvavis.setItems(oblistav);
    
    
    }

    @FXML
    private void ajouterav(ActionEvent event) {
        Avis av =new Avis();
        
        int ref = recupRefact();
        av.setMessage(txtcomment.getSelectionModel().getSelectedItem());
        av.setRefActivite(ref);
        avs.ajouter(av);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Notification d'ajout.");
		alert.setHeaderText(null);
		alert.setContentText("Votre avis est ajouter avec succées");
		alert.showAndWait();
                
        tvavis.getItems().clear();
        affichageAv();
        
    }
    
    
    
    @FXML
    private void modifierAv(ActionEvent event) {
        
         Avis av=  tvavis.getSelectionModel().getSelectedItem();
      av.setMessage(txtcomment.getSelectionModel().getSelectedItem().toString());
      
     avs.modifier(av);
     
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Notification de modification.");
		alert.setHeaderText(null);
		alert.setContentText("L'avis est modifier avec succees");
		alert.showAndWait();
     
    tvavis.getItems().clear();
    affichageAv();

    
}
    @FXML
     private void supprimerav(ActionEvent event) {
        
        Avis av =  tvavis.getSelectionModel().getSelectedItem();  
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Notification de Confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Vous confirmer la suppression ?");
		Optional<ButtonType> action = alert.showAndWait();
        avs.supprimer(av);
        tvavis.getItems().clear();
        affichageAv();
     

    }
     
    @FXML
   private void selectAllav(javafx.scene.input.MouseEvent event) {
    int index= -1;
    index = tvavis.getSelectionModel().getSelectedIndex();
     txtcomment.setValue(""+colmessage.getCellData(index));
     
    }
    
    
    @FXML
    private void selectNom(javafx.scene.input.MouseEvent event) {
    int index= -1;
    index = tvavisa.getSelectionModel().getSelectedIndex();
    
     txtnoma.setText(""+colnomac.getCellData(index));
     recupRefact();
     
    }

    public void AffActAv() {
        List<Activite> activites = as.afficher();
      activites.forEach(e->oblist.add(e));
        
        colnomac.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        coldescripac.setCellValueFactory(new PropertyValueFactory<>("Descrip"));
        

        tvavisa.setItems(oblist);

    }

    
    private void clearFieldsAv() {
		txtcomment.getSelectionModel().clearSelection();
		txtnoma.clear();
	}
    
    @FXML
    private void viderAv(ActionEvent event) {
        clearFieldsAv();
        
    }



    private int recupRefact() 
    {
    int ref = 0;    
    
    String req="Select RefAct from Activite where Nom='"+txtnoma.getText()+"'";
    PreparedStatement ste;
    
        try {
            ste = (PreparedStatement) con.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
         while(rs.next()){
                
             ref = rs.getInt(1);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(AvisController.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println(ref);
 return ref;
 
    }

 @FXML
        private void stat(){
                       FXMLLoader loader=new FXMLLoader(getClass().getResource("Statisique.fxml"));
                       Parent root ;
        try {
            root=loader.load();
             btnstat.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(StatisiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
               }  
}
