/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class PrimaryController  {

    @FXML
    private Button retourm;
     @FXML
    private TextField cityInput;

    @FXML
    private Text weatherText;

    private final String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final String weatherAPI = "https://www.metaweather.com/api/location/";

    @FXML
    void getWeatherData(ActionEvent event) throws MalformedURLException {
        JSONObject todaysWeather = GetTodaysWeatherInformation(getWoeid());

        System.out.println(todaysWeather);

        weatherText.setText(
            "            Min temperature: " + todaysWeather.get("min_temp") +
            "\n           Current temperature: " + todaysWeather.get("the_temp") +
            "\n           Max temperature: " + todaysWeather.get("max_temp")
        );
    }

    public String getWoeid() throws MalformedURLException {
        APIConnector apiConnectorCity = new APIConnector(cityAPI);

        JSONObject jsonData = (JSONObject) (apiConnectorCity.getJSONArray(cityInput.getText())).get(0);

        return jsonData.get("woeid").toString();
    }

    public JSONObject GetTodaysWeatherInformation(String woeid) throws MalformedURLException {
        APIConnector apiConnectorWeather = new APIConnector(weatherAPI);

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(woeid + "/");

        JSONArray weatherArray = (JSONArray) weatherJSONObject.get("consolidated_weather");

        return  (JSONObject) weatherArray.get(0);
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
