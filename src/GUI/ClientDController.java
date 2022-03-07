/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ClientDController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOverview;

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
    private Button btnSignout;
    
       private int idc; 
    @FXML
    private Button bReclamation;
    @FXML
    private Button btnOrders1;
    public void setIdc(int idc) {
        System.out.println(idc);
        this.idc = idc;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reserverVol(ActionEvent event) throws IOException {
        
       
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
    private void reserverHebergement(ActionEvent event) {
        
        
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
    private void reserverVoyageO(ActionEvent event) {
        
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
    private void reserverActivite(ActionEvent event) {
        
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
    private void consulterProfil(ActionEvent event) {
        
          
        
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void consulterReservation(ActionEvent event) {
        
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
    private void avis(ActionEvent event) {
    }
    
}
