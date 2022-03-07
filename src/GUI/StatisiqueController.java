/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javax.activation.DataSource;

/**
 * FXML Controller class
 *
 * @author TheBoss'07
 */
public class StatisiqueController extends GestionActivitesController implements Initializable {

    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private BarChart<String,Integer> Stat;

    
    /**
     * Initializes the controller class.
     */
    
    private Connection con = Datasource.getInstance().getCnx();
    @FXML
    private Button btnretour;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
       
       
        
String req= "Select Message ,count(Message) from Avis where `RefActivite`=31 AND Message= 'Très Satisfait'";
String req1= "Select Message ,count(Message) from Avis where `RefActivite`=31 AND Message= 'Satisfait'";
String req2= "Select Message ,count(Message) from Avis where `RefActivite`=31 AND Message= 'Neutre'";
String req3= "Select Message ,count(Message) from Avis where `RefActivite`=31 AND Message= 'Très déçu'";



        XYChart.Series<String,Integer> series = new XYChart.Series<String,Integer>();
        XYChart.Series<String,Integer> series1 = new XYChart.Series<String,Integer>();
        XYChart.Series<String,Integer> series2 = new XYChart.Series<String,Integer>();
        XYChart.Series<String,Integer> series3 = new XYChart.Series<String,Integer>();


        
             PreparedStatement ste,ste1,ste2,ste3;
        try {
            ste = (PreparedStatement) con.prepareStatement(req);
            ste1 = (PreparedStatement) con.prepareStatement(req1);
            ste2 = (PreparedStatement) con.prepareStatement(req2);
            ste3 = (PreparedStatement) con.prepareStatement(req3);


            
            ResultSet rs = ste.executeQuery();
            ResultSet rs1 = ste1.executeQuery();
            ResultSet rs2 = ste2.executeQuery();
            ResultSet rs3 = ste3.executeQuery();

            
            
            series.setName("Très Satisfait");
            series1.setName("Satisfait");
            series2.setName("Neutre");
            series3.setName("Très déçu");
            
            while (rs.next() && rs1.next() && rs2.next() && rs3.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
                series1.getData().add(new XYChart.Data<>(rs1.getString(1),rs1.getInt(2)));
                series2.getData().add(new XYChart.Data<>(rs2.getString(1),rs2.getInt(2)));
                series3.getData().add(new XYChart.Data<>(rs3.getString(1),rs3.getInt(2)));

            }
            Stat.getData().add(series);
            Stat.getData().add(series1);
            Stat.getData().add(series2);
            Stat.getData().add(series3);

                    
        
        } catch (SQLException ex) {
            Logger.getLogger(StatisiqueController.class.getName()).log(Level.SEVERE, null, ex);
}
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
    
    FXMLLoader loader=new FXMLLoader(getClass().getResource("GestionActivites.fxml"));
                       Parent root ;
        
            root=loader.load();
             btnretour.getScene().setRoot(root);
       }
    }
   

    
       
    
  

    
    