/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
       // Parent root = FXMLLoader.load(getClass().getResource("AfficherReservation.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("StatR.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("ReserverVol.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("ReserverActivite.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("ReserverVoyage.fxml"));
       Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("agenda.fxml"));
        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
