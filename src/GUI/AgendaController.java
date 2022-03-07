/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.HebergementService;
import Services.ReservationService;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AgendaController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     * 
     */
    private int id; 
    @FXML
    private Agenda agenda;
    @FXML
    private Button retourb;
    @FXML
    private AnchorPane agendaA;
 
  
  
  
  
  
  
  
  
  
  
  
  
  
      
    public void setId(int idc) {
        this.id = idc;
        System.out.println("to agenda"+id);
      int t=2;
       ReservationService rs = new ReservationService();
     List<LocalDate> ll=  rs.WeekRH(id);
        ll .forEach(e->
       agenda.appointments().add(
        new Agenda.AppointmentImpl().withDescription("Reservation Hebergement").withSummary("Reservation Hebergement")
        .withStartTime((GregorianCalendar.from(e.atStartOfDay(ZoneId.systemDefault()).plusHours(2))))));
        System.out.println(  rs.WeekRV(1));
         List<LocalDate> ll2=  rs.WeekRV(id);
          ll2.forEach(e->
              
       agenda.appointments().add(
        new Agenda.AppointmentImpl().withDescription("Reservation Vol ").withSummary("Reservation Vol ")
        .withStartTime((GregorianCalendar.from(e.atStartOfDay(ZoneId.systemDefault()).plusHours(2))))));
         
        rs.WeekVO(id).forEach((e)->{
       
       agenda.appointments().add(
        new Agenda.AppointmentImpl().withDescription("Reservation voyage  ").withSummary("Reservation voyage ").withLocation("tunis")
        .withStartTime((GregorianCalendar.from(e.atStartOfDay(ZoneId.systemDefault()).plusHours(5).withDayOfMonth(t)))));
         
        
        });
    

       
 
    }
    
   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        
}
    
     @FXML
    void afficher(MouseEvent event) {

     
    
        
        
    }

    @FXML
    private void retour(ActionEvent event) {
        
        try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReservation.fxml"));
		Parent root = loader.load();
		AfficherReservationController  e = loader.getController();
                System.out.println("back to consulter "+id);
                e.setIdc(id);
                
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			         System.out.println(ex);
		}
        
        
    }
    
    
    
    
   

    
    
    
    
    
    
    }    
    

