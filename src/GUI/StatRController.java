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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class StatRController implements Initializable {
    @FXML
   private PieChart pieChart;
   ReservationService rs= new ReservationService();
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
    
}
