/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Services.AvisService;
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
public class StatisiqueController extends GestionactadminController implements Initializable {

    private BarChart<String,Integer> Stat;

    
    /**
     * Initializes the controller class.
     */
    
    private Connection con = Datasource.getInstance().getCnx();
    @FXML
    private Button btnretour;
    @FXML
    private PieChart statActivite;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
       
       
        

    }
    
        public void initializeFxml(int idStat) {


        statav(idStat);
        }
        
        
    @SuppressWarnings("empty-statement")
            public void statav(int idStat) {

        AvisService avisService = new AvisService();

        String message1 = "Très Satisfait";
        String message2 = "Satisfait";
        String message3 = "Neutre";
        String message4 = "Très déçu";

        int message1Size = avisService.avisListStat(idStat, message1).size();

        int message2Size = avisService.avisListStat(idStat, message2).size();

        int message3Size = avisService.avisListStat(idStat, message3).size();
        int message4Size = avisService.avisListStat(idStat, message4).size();

        int all = message1Size + message2Size + message3Size + message4Size;
                ObservableList<PieChart.Data> list_stat;

        
        
        
        if(message1Size==all)
        {
         list_stat = FXCollections.observableArrayList(
                new PieChart.Data(message1+" : " + (message1Size * 100) / all + "%", message1Size))      ;
        
        }
        else
        if(message4Size==all)
        {
         list_stat = FXCollections.observableArrayList(
                new PieChart.Data(message4 +" : "+ (message4Size * 100) / all + "%", message4Size));
        
        }
        
        else
         if(message2Size==all)
        {
        list_stat = FXCollections.observableArrayList(
                new PieChart.Data(message2 +" : "+ (message2Size * 100) / all + "%", message2Size));
        
        }
         
         
         else
          if(message3Size==all)
        {
          list_stat = FXCollections.observableArrayList(
                new PieChart.Data(message3 +" : "+ (message3Size * 100) / all + "%", message3Size));
        
        }
          else{
        

         list_stat = FXCollections.observableArrayList(
                new PieChart.Data(message1+" : " + (message1Size * 100) / all + "%", message1Size),
                new PieChart.Data(message2 +" : "+ (message2Size * 100) / all + "%", message2Size),
                new PieChart.Data(message3 +" : "+ (message3Size * 100) / all + "%", message3Size),
                new PieChart.Data(message4 +" : "+ (message4Size * 100) / all + "%", message4Size)
        );
          
          }
        statActivite.setData(list_stat);
        }

    

        
        


    @FXML
    private void retour(ActionEvent event) throws IOException {
        
    
    FXMLLoader loader=new FXMLLoader(getClass().getResource("Avis.fxml"));
                       Parent root ;
        
            root=loader.load();
             btnretour.getScene().setRoot(root);
       }
    }
   

    
       
    
  

    
    