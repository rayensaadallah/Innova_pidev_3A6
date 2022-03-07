/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import Services.ReclamationService;
import Entities.Reclamation;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Services.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ReclamationController implements Initializable {

    

    @FXML
    private TextField txtobj;

    @FXML
    private TextArea txtdesc;
    
    @FXML
    private TableView<Reclamation> tvRec;

    @FXML
    private TableColumn<Reclamation, String> colObj;

    @FXML
    private TableColumn<Reclamation, String> colDesc;
    
    
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
    private Button btnajoutRec;
    @FXML
    private Button btnmodifRec;
    @FXML
    private Button btnsuppRec;

    public void setIdc(int idc) {
        this.idc = idc;
        System.out.println(idc);
        afficherRec();
    }
    ObservableList<Reclamation> oblist1 = FXCollections.observableArrayList();  
ReclamationService rs = new ReclamationService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
afficherRec();

    }
    
    @FXML
        void selectRec(MouseEvent event) {
        
          int index= -1;
     index = tvRec.getSelectionModel().getSelectedIndex();
     txtobj.setText(colObj.getCellData(index));
     txtdesc.setText(""+  colDesc.getCellData(index));
          //  System.out.println("aaaaaaa");
  
    }
    
    
    
          private void afficherRec() {
      List <Reclamation> ls =rs.afficherParIdC(idc);
      ls.forEach(e->oblist1.add(e));
      colObj.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("objet"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
         
        tvRec.setItems(oblist1);    
    }

    @FXML
    void AjouterRec(ActionEvent event) {

      Reclamation r= new Reclamation(idc,txtobj.getText(),txtdesc.getText());
rs.ajouter(r);
tvRec.getItems().clear();
afficherRec();
            

    }
    
    
    @FXML
        void modifierreclamation(ActionEvent event) {

       
       Reclamation r =  tvRec.getSelectionModel().getSelectedItem();
      
         
        r.setObjet(txtobj.getText());
        r.setDescription(txtdesc.getText());
       
        rs.modifier(r);
        tvRec.getItems().clear();
        afficherRec();
        
    }

    @FXML
    void supprimerRec(ActionEvent event) {

       Reclamation o =  tvRec.getSelectionModel().getSelectedItem();
rs.supprimer(o.getIdR());
tvRec.getItems().clear();
afficherRec(); 
        
    }

    @FXML
    private void consulterProfil(ActionEvent event) {
         try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCompteClient.fxml"));
		Parent root = loader.load();
		GUI.ModifierCompteClientController  e = loader.getController();
                e.setIdc(idc);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    private void reserverVol(ActionEvent event) {
         try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverVol.fxml"));
		Parent root = loader.load();
		GUI.ReserverVolController  e = loader.getController();
                e.setIdclient(idc);
                
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
      
         
    }

    @FXML
    private void reserverHebergement(ActionEvent event) {
        try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverHebergement.fxml"));
		Parent root = loader.load();
		GUI.ReserverHebergementController  e = loader.getController();
                e.setIdC(idc);
                
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    private void reserverVoyageO(ActionEvent event) {
         try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverVoyage.fxml"));
		Parent root = loader.load();
		GUI.ReserverVoyageController  e = loader.getController();
           
                e.setIdclient(idc);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    private void reserverActivite(ActionEvent event) {
           try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverActivite.fxml"));
		Parent root = loader.load();
		GUI.ReserverActiviteController  e = loader.getController();
                
           
                e.setIdclient(idc);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    private void consulterReservation(ActionEvent event) {
        try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReservation.fxml"));
		Parent root = loader.load();
		GUI.AfficherReservationController  e = loader.getController();
                e.setIdc(idc);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void avis(ActionEvent event) {
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
    private void logout(ActionEvent event) {
        
    
                      try{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
		Parent root = loader.load();
		GUI.LoginController  e = loader.getController();
               
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
        
    }
    

   
  
    
}
