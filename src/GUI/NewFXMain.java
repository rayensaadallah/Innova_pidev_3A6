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
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
       primaryStage.getIcons().add(new Image(NewFXMain.class.getResourceAsStream("img/logo.png")));
        primaryStage.setTitle("GateAway");
       Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("agenda.fxml"));
       
        Scene scene = new Scene(root);
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
