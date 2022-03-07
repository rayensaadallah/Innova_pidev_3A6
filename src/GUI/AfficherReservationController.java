/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Paiement;
import Entities.Reservation;
import Services.HebergementService;
import Services.PaiementService;
import Services.ReservationService;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficherReservationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
         @FXML
    private AnchorPane tableR;
  @FXML
    private TableView<Reservation> tableRe;
    @FXML
    private TableColumn<Reservation, Date> dateR;

    @FXML
    private TableColumn<Reservation, Integer> nbp;

    @FXML
    private TableColumn<Reservation, Date> dated;

    @FXML
    private TableColumn<Reservation, Date> datef;

   

    private TableColumn<Reservation, Integer> idh;

    @FXML
    private TableColumn<Reservation, String> etat;

    @FXML
    private TableColumn<Reservation, String > type;
    
    
     ObservableList<Reservation> oblist = FXCollections.observableArrayList();
     
     ObservableList<Paiement> oblistp = FXCollections.observableArrayList();
     ReservationService rs= new ReservationService();
     PaiementService ps = new  PaiementService();
    @FXML
    private Button supprimerR;

    @FXML
    private TableView<Paiement> paiment;
    @FXML
    private TableColumn<Paiement,Date> dateP;
    @FXML
    private TableColumn<Paiement, String> modalite;
    @FXML
    private TableColumn<Paiement, Float> montant;
    @FXML
    private ComboBox<String> comboMontant;
    @FXML
    private DatePicker datedeR;
    @FXML
    private ComboBox<String> Etat;
    @FXML
    private DatePicker DF;
    @FXML
    private DatePicker DD;
    @FXML
    private Button modifierR;
        

    @FXML
    private Button agenda;
    
    
  private int idc ;
  
  
  

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
    private Button btnSignout;
    @FXML
    private Button btnSettings1;
    @FXML
    private Button modifierP;
    @FXML
    private Button consulterCompte;
    @FXML
    private Button btnSettings11;

    public void setIdc(int idc) {
        this.idc = idc;
        
        loadTable(idc);
          loadTableP(idc);
        
    }
  
  
    
    
    

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          loadTable(idc);
          loadTableP(idc);
          supprimerR.disableProperty().bind(Bindings.isEmpty(tableRe.getSelectionModel().getSelectedItems()));
       comboMontant.getItems().addAll("Cache" ,"Cheque","Carte bancaire");
       Etat.getItems().addAll("Approuve" ,"Annule");
        
    } 
    
     @FXML
    void agenda(ActionEvent event)   {
  try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("agenda.fxml"));
		Parent root = loader.load();
		AgendaController  e = loader.getController();
                e.setId(idc);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
        
      
    }
    
      
   

    private void loadTable(int id ) {
     
       
         


            // TODO
       
     
          List <Reservation> ls =rs.afficher2(idc);
       
          ls.forEach(e->{oblist.add(e);
         
          dateR.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
          nbp.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));
           dated.setCellValueFactory(new PropertyValueFactory<>("Date_debut"));
            datef.setCellValueFactory(new PropertyValueFactory<>("Date_fin"));
                  etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
           type.setCellValueFactory(new PropertyValueFactory<>("type")); 
           
          });
          
//          
         
    tableRe.setItems(oblist);
    
       
        
    }
    
    private void loadTableP(int id ) { 
          List <Paiement> lp =ps.afficher2(id);
          lp.forEach(e->{oblistp.add(e);
         System.out.print(oblistp);
           dateP.setCellValueFactory(new PropertyValueFactory<>("date"));
          modalite.setCellValueFactory(new PropertyValueFactory<>("modalite"));
           montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
          });
    paiment.setItems(oblistp);
    }
    
    @FXML
    private void Supprimer(ActionEvent event) {
        
       
      Reservation r=  tableRe.getSelectionModel().getSelectedItem();
      Paiement p = new Paiement();
      p.setId_reservation(r.getId());
        
      if(r.getEtat().equals("Vol") || r.getEtat().equals("Voyage") || r.getEtat().equals("Activite") && r.getEtat().equals("Annule"))
      {
       ps.supprimer(p);
      rs.supprimer(r);  
      }
      else if (r.getType().equals("Hebergement") && r.getEtat().equals("Annule") )
      {
            ps.supprimer(p);
           rs.supprimer(r);  
          
      }
      else 
      {
            Notifications.create().title(" Affichage  ").text("   suppression impoosible   ").show();
      }
      
           tableRe.getItems().clear();
     paiment.getItems().clear();
     loadTable(idc);
     loadTableP(idc);   
      
    }

    @FXML
    private void UpdateTable(MouseEvent event) {  
     tableRe.getItems().clear();
     paiment.getItems().clear();
     loadTable(idc);
     loadTableP(idc);
    }
    
    @FXML
    private void selectP(MouseEvent event) {
             
      Reservation r=  tableRe.getSelectionModel().getSelectedItem();
     try{
         
       tableRe.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->
     {
      
        Paiement p = ps.afficher().stream().filter(e->e.getId_reservation()==newSelection.getId()).findFirst().get();
        
       
   int a=  IntStream.range(0,paiment.getItems().size())
     .filter(i -> paiment.getItems().get(i).getId()==p.getId()).findFirst().getAsInt();
   
    paiment.getSelectionModel().select(a);
    
     datedeR.setValue(paiment.getSelectionModel().getSelectedItem().getDate().toLocalDate());
     comboMontant.setValue(paiment.getSelectionModel().getSelectedItem().getModalite());
     });
       
     }catch(Exception e)
     {
         
         System.out.println(e);
         
     }
     if(r.getType().equals("Vol") || r.getType().equals("Voyage") || r.getType().equals("Activite"))
     {
         DD.setDisable(true);
         DF.setDisable(true);
         
         
     }
     if(r.getType().equals("Hebergement")){
        
         
              List<LocalDate> listdd = rs.ListeDd(r.getId_hebergement());
       DD.setDayCellFactory((DatePicker param) -> new DateCell(){
           public void updateItem(LocalDate item, boolean empty) {
               super.updateItem(item, empty);
               
               if (!empty && item != null) {
                   if(listdd.contains(item)) {
                       
                       this.setStyle("-fx-background-color: pink");
                   }
               }
           }
       });
       
       
         DF.setDayCellFactory((DatePicker param) -> new DateCell(){
           public void updateItem(LocalDate item, boolean empty) {
               super.updateItem(item, empty);
               
               if (!empty && item != null) {
                   if(listdd.contains(item)) {
                       
                       this.setStyle("-fx-background-color:yellow");
                   }
               }
           }
       });
         DD.setDisable(false);
         DF.setDisable(false);
    }  
    }
    

    @FXML
    private void ModifierP(ActionEvent event) {
        
        Paiement p = paiment.getSelectionModel().getSelectedItem();
         java.sql.Date DateR = Date.valueOf(datedeR.getValue());
        p.setDate(DateR);
        p.setModalite( comboMontant.getValue());
        ps.modifier(p);
        paiment.getItems().clear();
     loadTableP(idc);
        
    }

    @FXML
    private void modifierR(ActionEvent event) {
         Reservation r=  tableRe.getSelectionModel().getSelectedItem();
         
         System.out.println("test1 ");
         if(r.getType().equals("Vol") || r.getType().equals("Voyage") || r.getType().equals("Voyage"))
         {   
               
             
              if(r.getEtat().equals("Annule")&&Etat.getValue().equals("Approuve"))
                 
             {
                  Notifications.create().title(" Affichage  ").text("  lmodification imposible  ").show();
                 
                 
             }

             
             else if(Etat.getValue().equals("Annule") && !r .getEtat().equals("Annule"))
                 
             {
                 switch(r.getType())
                  {
             case "Vol":
                 
                 
               r.setEtat(Etat.getValue());
               rs.modifiervol(r);
              rs.Annulernbplacevol(r.getId_vol(), r.getNbr_place());
              ps.modifierMontant(r.getId(),0);
               Notifications.create().title(" Affichage  ").text("  la reservation est annulee   ").show();
                 tableRe.getItems().clear();
                paiment.getItems().clear();
                  loadTableP(idc);
                 loadTable(idc);  
            break;
        case "Voyage":
             r.setEtat(Etat.getValue());
              rs.modifier(r);
              rs.Annulernbplacevoyage(r.getId_voyage(), r.getNbr_place());
              ps.modifierMontant(r.getId(),0);
               Notifications.create().title(" Affichage  ").text("  la reservation est annulee   ").show();
                 tableRe.getItems().clear();
                paiment.getItems().clear();
                
            loadTableP(idc);
                 loadTable(idc);  
            
           
        case "Activite":
            
             r.setEtat(Etat.getValue());
              rs.modifierAct(r);
             rs.AnnulernbplaceA(r.getId_active(),r.getNbr_place());
              ps.modifierMontant(r.getId(),0);
               Notifications.create().title(" Affichage  ").text("  la reservation est annulee   ").show();
                 tableRe.getItems().clear();
                paiment.getItems().clear();
                  loadTableP(idc);
                 loadTable(idc);  
         
            break;        
                 }
                  
             }
             
             else if(Etat.getValue().equals("Annule"))
                 
             {
                  Notifications.create().title(" Affichage  ").text("  la reseravation est deja annule  ").show();
                 
                 
             }
                         
             
             
         }
         else
         {
             if(r.getType().equals("Hebergement")&&Etat.getValue().equals("Approuve"))
             {
                  java.sql.Date Datedv =Date.valueOf(DD.getValue());
        java.sql.Date Dateav = Date.valueOf(DF.getValue());
                 if(rs.verifierDateHberg(r.getId_hebergement(), Datedv,  Dateav)&&rs.testerdisponibliteH( Datedv,  Dateav,r.getId_hebergement()))
                     
                 {
                     r.setDate_debut(Datedv);
                     r.setDate_fin(Dateav);
                     r.setEtat("Approuve");
                     rs.modifierHeb(r);
                      java.util.Date  utilDateD = new java.util.Date(Datedv.getTime());
         java.util.Date  utilDateF = new java.util.Date(Dateav.getTime());
          long diffInMillies = Math.abs( utilDateD.getTime() -  utilDateF.getTime());
             System.out.println(diffInMillies);
             long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
             System.out.println(diff);
             HebergementService hs = new HebergementService();
             
              double prixx= hs.afficher().stream().filter(e->e.getReferance()==r.getId_hebergement()).mapToDouble(e->e.getPrix()).findAny().getAsDouble();
              Float prixTT=diff*(float)prixx;
                     System.out.println("zzzzzzzzzzzzzzzzz"+prixTT);
               ps.modifierMontant(r.getId(),prixTT);
              tableRe.getItems().clear();
                paiment.getItems().clear();
                 loadTableP(idc);
                 loadTable(idc);
                      Notifications.create().title(" Affichage  ").text("  la reseravation est modifiee  ").show();
                     
                 }
                 else
                 {
                     
                     
                      Notifications.create().title(" Affichage  ").text("  la date n'est pas dispo   ").show();
                     
                 }
                 
                 
                 
                 
                 
             }
             if(r.getType().equals("Hebergement")&&Etat.getValue().equals("Annule"))
                 
                 
             {
               r.setEtat(Etat.getValue());
              rs.modifierHeb(r);
              ps.modifierMontant(r.getId(),0);
                 tableRe.getItems().clear();
                paiment.getItems().clear();
                 loadTableP(idc);
                 loadTable(idc);
                 
                 
             }
                 
                 
             
             
             
             
         } 
            
        
    }

    @FXML
    private void vider(MouseEvent event) {
           DD.setDisable(false);
         DF.setDisable(false);
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
                e.setIdclient(idc);
                
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
           
                e.setIdclient(idc);
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
                
           
                e.setIdclient(idc);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    private void reclamations(ActionEvent event) {
    }

    @FXML
    private void signout(ActionEvent event) {
    }

    @FXML
    private void reserverH(ActionEvent event) {
        
        try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverHebergement.fxml"));
		Parent root = loader.load();
		ReserverHebergementController  e = loader.getController();
                e.setIdC(idc);
                
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    

}
