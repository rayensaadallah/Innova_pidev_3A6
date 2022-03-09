/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Vol;
import Services.VolService;
import Utilis.Datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javax.activation.DataSource;

/**
 * FXML Controller class
 *
 * @author Malek
 */


public class StatController implements Initializable {
    @FXML
    private BarChart<String,Integer> barChart;
    @FXML
    private Button retourm;

    /**
     * Initializes the controller class.
     */
    
    private Connection con = Datasource.getInstance().getCnx();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      String req =" select a.nom_avion , COUNT(v.ville_arrivee) from vol v join avion a on v.id_avion=a.id_avion group by a.id_avion; ";
        XYChart.Series<String,Integer> series = new XYChart.Series<String,Integer>();
        try {
             PreparedStatement ste = (PreparedStatement) con.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
            }
            barChart.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
@FXML
    private void retour(ActionEvent event) {
        
          try{
		 FXMLLoader loader=new FXMLLoader(getClass().getResource("Agentaerien.fxml"));
		Parent root = loader.load();
		AgentAerienController  e = loader.getController();
                e.setIdagent(ida);
                
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
       
    } 
     
     private int ida;
    public void setIdagent(int ida)
    {
        System.out.println(ida);
        this.ida=ida;
        
        
        
        
    }
    
  
}
