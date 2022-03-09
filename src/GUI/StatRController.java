/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reservation;
import Services.ReservationService;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class StatRController implements Initializable {
    @FXML
   private PieChart pieChart;
   ReservationService rs= new ReservationService();
    @FXML
    private Button btngestU;
    @FXML
    private Button btngestVo;
    @FXML
    private Button btngestActv;
    @FXML
    private Button btnStat;
    @FXML
    private Button btnlogout;
    private int idA;

    public void setIdA(int idA) {
        this.idA = idA;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
             Map<String,List<Reservation>> map = rs.sataR();
        ObservableList<PieChart.Data> piedata=FXCollections.observableArrayList(
        
        );
        
       map.forEach((e,v)->piedata.add(new PieChart.Data(e,(int)v.stream().mapToInt(r->r.getNbr_place()).sum())));
        
      
       pieChart.setData(piedata);
       pieChart.setTitle("test");
    }    

    @FXML
    private void GestionU(ActionEvent event) {
           try{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Registration.fxml"));
		Parent root = loader.load();
		RegistrationController  e = loader.getController();
                e.setIdadmin(idA);
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}  
    }

    @FXML
    private void gestVo(ActionEvent event) {
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
    private void gestAct(ActionEvent event) {
    }

    @FXML
    private void statistique(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
           try{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
		Parent root = loader.load();
		LoginController  e = loader.getController();
               
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }
    
}
