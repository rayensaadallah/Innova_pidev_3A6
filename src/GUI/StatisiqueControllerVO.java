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
public class StatisiqueControllerVO extends VoyOrgFXMLController implements Initializable {

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
    
       
       
        
    String req= "SELECT c.nomcat , count(v.idVoy) from voyageorganise v join categorievoy c WHERE c.idcat=v.idCat GROUP by v.idCat";



        XYChart.Series<String,Integer> series = new XYChart.Series<String,Integer>();


        
             PreparedStatement ste;
        try {
            ste = (PreparedStatement) con.prepareStatement(req);
           
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
                

            }
            Stat.getData().add(series);
            
                    
        
        } catch (SQLException ex) {
            Logger.getLogger(StatisiqueController.class.getName()).log(Level.SEVERE, null, ex);
}
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
    
    FXMLLoader loader=new FXMLLoader(getClass().getResource("MainFXML.fxml"));
                       Parent root ;
        
            root=loader.load();
             btnretour.getScene().setRoot(root);
       }
    }
   

    
       
    
  

    
    

      
    



