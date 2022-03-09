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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author TheBoss'07
 */
public class StatActController implements Initializable {
 private Connection con = Datasource.getInstance().getCnx();
    @FXML
    private Button btnretour2;
    @FXML
    private BarChart<String,Integer> statact;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String req= "Select Type, COUNT(Type) from Activite GROUP BY Type";

        XYChart.Series<String,Integer> series = new XYChart.Series<String,Integer>();
       
        
             PreparedStatement ste;
        try {
            ste = (PreparedStatement) con.prepareStatement(req);

            ResultSet rs = ste.executeQuery();
        
            
            while (rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
                
            }
            statact.getData().add(series);
           

                    
        
        } catch (SQLException ex) {
            Logger.getLogger(StatisiqueController.class.getName()).log(Level.SEVERE, null, ex);
}
    }

       

    @FXML
    private void retour2(ActionEvent event) throws IOException {
FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionactadmin.fxml"));
                       Parent root ;
        
            root=loader.load();
             btnretour2.getScene().setRoot(root);
    }
    
}
