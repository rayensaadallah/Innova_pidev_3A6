/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Paiement;
import Entities.Reservation;
import GetAway.entities.Activite;
import Services.ActiviteService;
import Services.PaiementService;
import Services.ReservationService;
import com.jfoenix.controls.JFXButton;
import static java.lang.String.valueOf;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ReserverActiviteController implements Initializable {

    @FXML
    private TextField nomA;
    @FXML
    private TextField prixA;
    @FXML
    private TextField nbpA;
    @FXML
    private TextField DateA;
    @FXML
    private TextField prixtotal;
    @FXML
    private Button ReserverA;
    @FXML
    private TextField idc;
     @FXML
    private TableView<Activite> tvactivite;
    @FXML
    private TableColumn<Activite, String> colnom;
    @FXML
    private TableColumn<Activite, String> colduree;
    @FXML
    private TableColumn<Activite, Integer> colnbrp;
    @FXML
    private TableColumn<Activite, String> coldate;
    @FXML
    private TableColumn<Activite, String> coltype;
    @FXML
    private TableColumn<Activite, String> colloc;
    @FXML
    private TableColumn<Activite, Float> colprix;
    @FXML
    private TableColumn<Activite, String> coldesc;
      ActiviteService as = new ActiviteService();
        ObservableList<Activite> oblist = FXCollections.observableArrayList();
        int index;
        int count=0;
        
               ReservationService rs = new ReservationService();
       PaiementService ps = new PaiementService();
        private int idclient;
    @FXML
    private JFXButton F31;
    @FXML
    private JFXButton F311;
    @FXML
    private Button consulterCompte;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSettings1;
    @FXML
    private Button btnSettings11;
    @FXML
    private Button btnSignout;

    public void setIdclient(int idclient) {
        this.idclient = idclient;
          idc.setText(rs.NomP(idclient));
        
    }
        
        
    @FXML
    private ComboBox<String> modalite;
    @FXML
    private JFXButton E1;
    @FXML
    private JFXButton E2;
    @FXML
    private JFXButton E3;
    @FXML
    private JFXButton E4;
    @FXML
    private JFXButton E5;
    @FXML
    private JFXButton E6;
    @FXML
    private JFXButton E7;
    @FXML
    private JFXButton E8;
    @FXML
    private JFXButton E9;
    @FXML
    private JFXButton E10;
    @FXML
    private JFXButton F1;
    @FXML
    private JFXButton F2;
    @FXML
    private JFXButton F3;
    @FXML
    private JFXButton F4;
    @FXML
    private JFXButton F5;
    @FXML
    private JFXButton F6;
    @FXML
    private JFXButton F7;
    @FXML
    private JFXButton F8;
    @FXML
    private JFXButton F9;
    @FXML
    private JFXButton F10;
    
      private JFXButton[] seats=new JFXButton[20];
    public static boolean[] bookings=new boolean[20];
    public static boolean[] booked;
  private int numberofseats=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         BooleanBinding booleanBinding =(nbpA.textProperty().isEqualTo("0")).or(
        prixA.textProperty().isEqualTo("")).or(
         DateA.textProperty().isEqualTo("")).or(
       nomA.textProperty().isEqualTo("").or(nbpA.textProperty().isEqualTo("")));
             modalite.getItems().addAll("Cache" ,"Cheque","Carte bancaire");
          nbpA.setText("0");
           affichage();
            
           
           ReserverA.disableProperty().bind(booleanBinding);
           
            booked=new boolean[20];
         initialiseArray();
           
           
    }    

    @FXML
    private void addact(ActionEvent event) {
        
        
     
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       
          if(nbpA.getText().matches("^[0-9]+$")&& ! ( modalite.getValue()==null)  )  
          {
              
          
       try
       {    Activite A =  tvactivite.getSelectionModel().getSelectedItem();
             java.util.Date parsedd = format.parse(DateA.getText());
              java.util.Date parseda = format.parse(DateA.getText());
              java.sql.Date datR = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Date Datedv = new java.sql.Date(parsedd.getTime());
        java.sql.Date Dateav = new java.sql.Date(parseda.getTime());
         if(rs.verifierNbplaceAct(A.getRefAct(),Integer.parseInt(nbpA.getText())))
        {
        Reservation r= new Reservation(datR,Integer.parseInt(nbpA.getText()), Datedv, Dateav,0,A.getRefAct(),0,0,"Approuve",idclient,"vol");
      
         rs.ajouterAct(r);
         float prixtotalt= Float.valueOf(prixtotal.getText());
         if(Integer.parseInt(nbpA.getText())==10)
             
         {
             prixtotalt= prixtotalt*01;
                
          }
         
         
          Paiement p = new Paiement(modalite.getValue(), prixtotalt,rs.afficher().get(rs.afficher().size()-1).getId(),datR);
         ps.ajouter(p);
         rs.modifiernbplaceA(A.getRefAct(),Integer.parseInt(nbpA.getText()));
         
         
          Notifications.create().title("Reservation Activite ").text(" Reservation est Créé ").show();
        }
          else
          {     Notifications.create().title("Reservation Activite ").text(" Nombre de place non valide  ").show();
          }
    
            
       
       }
       
       catch(ParseException e)
       {
           System.out.println(e);
       }
    }
    else
    {
        
        Notifications.create().title("Reservation Activite ").text("  Verifier vos champs  ").show();
        
    }}
    
    
    
     private void affichage() {
     
          List<Activite> activites = as.afficher();
        activites.forEach(e->oblist.add(e));
        
        colnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("Duree"));
        colnbrp.setCellValueFactory(new PropertyValueFactory<>("NbrPlace"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colloc.setCellValueFactory(new PropertyValueFactory<>("Location"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("Descrip"));
        

        tvactivite.setItems(oblist);
        
    
}

    @FXML
    private void selecteAct(MouseEvent event) {
             
          int  index = tvactivite.getSelectionModel().getSelectedIndex();
          nomA.setText(colnom.getCellData(index));
         prixA.setText(colprix.getCellData(index).toString());
         DateA.setText(coldate.getCellData(index));
         
            int placesdipo=colnbrp.getCellData(index);
            for (int i=0; i<20;i++)
            {
                 booked[i]=false;
            }
           System.out.println(placesdipo);
            setUpSeats(placesdipo);
        System.out.println("aaaaaaaaaaaaaa");
         if(count==0)
         {
                NotificationPane notificationPane;
            
               notificationPane = new NotificationPane();
       
        String imagePath = ReserverVolController.class.getResource("img/offre.png").toExternalForm();
        ImageView image = new ImageView(imagePath);
        notificationPane.setGraphic(image);
               Notifications  notificationBuilder  =   Notifications.create().title("OFFRE SPECIALE").graphic(image).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER);
               
                 notificationBuilder .show();
         index =  tvactivite.getSelectionModel().getSelectedIndex();   
         count+=1;
             
         }
      
        
        
    }

    @FXML
    private void vider(MouseEvent event) {
        
        
                 
    nomA.clear();

     prixtotal.setText("0");

  prixA.clear();

    DateA.clear();
     nbpA.setText("0");
    tvactivite.getItems().clear();
    affichage();
    
    for (int i=0; i < seats.length ;i++)
    {
     seats[i].setStyle("-fx-background-color:  #8EA6B4");
    }
    }
    @FXML
    private void calculerPT(KeyEvent event) {
        
           
        try{
            
          
            
            if(!nbpA.getText().equals("0") && !nbpA.getText().equals("") )
        { 
            Float prixTT=Integer.parseInt(nbpA.getText())* Float.parseFloat(prixA.getText());
            
            prixtotal.setText(prixTT.toString());
           
            
        
    }
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    
    
    private void initialiseArray(){
        seats[0]=E1;
        seats[1]=E2;
        seats[2]=E3;
        seats[3]=E4;
        seats[4]=E5;

        seats[5]=E6;
        seats[6]=E7;
        seats[7]=E8;
        seats[8]=E9;
        seats[9]=E10;

        seats[10]=F1;
        seats[11]=F2;
        seats[12]=F3;
        seats[13]=F4;
        seats[14]=F5;

        seats[15]=F6;
        seats[16]=F7;
        seats[17]=F8;
        seats[18]=F9;
        seats[19]=F10;
    }

  

    
      private void setUpSeats(int nb ){
         
          for(int i=0 ; i <nb ;i++)
          {
               booked[i]=true;
          }
          
        for(int i=0; i<seats.length; i++)
{
    
         if(booked[i]==false){
                seats[i].setStyle("-fx-background-color:  #C40018");
                  
         }
         else{
             
             seats[i].setStyle("-fx-background-color:  #4A772F");
         }
         
         
        
    


}
    }

    @FXML
    private void consulterCompteC(ActionEvent event) {
    }

    @FXML
    private void reserverVol(ActionEvent event) {
        
        try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverVol.fxml"));
		Parent root = loader.load();
		ReserverVolController  e = loader.getController();
                e.setIdclient(idclient);
                
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    private void reserverH(ActionEvent event) {
        
  try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverHebergement.fxml"));
		Parent root = loader.load();
		ReserverHebergementController  e = loader.getController();
                e.setIdC(idclient);
                
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    private void reserverVO(ActionEvent event) {
        
         try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverVoyage.fxml"));
		Parent root = loader.load();
		ReserverVoyageController  e = loader.getController();
           
                e.setIdclient(idclient);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    private void ReserverA(ActionEvent event) {
        
          try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverActivite.fxml"));
		Parent root = loader.load();
		ReserverActiviteController  e = loader.getController();
                
           
                e.setIdclient(idclient);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    private void consulterR(ActionEvent event) {
         try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReservation.fxml"));
		Parent root = loader.load();
		AfficherReservationController  e = loader.getController();
                e.setIdc(idclient);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}

        
        
    }

    @FXML
    private void reclamations(ActionEvent event) {
    }

    @FXML
    private void Avis(ActionEvent event) {
    }

    @FXML
    private void signout(ActionEvent event) {
    }
}


  
               
  


