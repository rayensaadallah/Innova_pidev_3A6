/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Paiement;
import Entities.Reservation;
import Entities.Vol;
import Entities.voyageOrganise;
import Services.PaiementService;
import Services.ReservationService;
import Services.voyOrgServ;
import static java.lang.String.valueOf;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ReserverVoyageController implements Initializable {

    @FXML
    private TextField villeDvoy;
    @FXML
    private TextField VilleDestVoy;
    @FXML
    private TextField dateD;
    @FXML
    private TextField idc;
    @FXML
    private Button Reservervoyage;
    @FXML
    private TextField prixVoy;
    @FXML
    private TextField nbp;
    @FXML
    private TextField dateF;
 ReservationService rs = new ReservationService();
    @FXML
    private TableView<voyageOrganise> tableviewVO;

    @FXML
    private TableColumn<voyageOrganise, String> VilleDep;

    @FXML
    private TableColumn<voyageOrganise, String> villeDest;

    @FXML
    private TableColumn<voyageOrganise, String> DateDeb;

    @FXML
    private TableColumn<voyageOrganise, String> DateFin;

    @FXML
    private TableColumn<voyageOrganise, Integer> nbrPlace;

    @FXML
    private TableColumn<voyageOrganise, Integer> categ;

    @FXML
    private TableColumn<voyageOrganise, Float> prix;

    @FXML
    private TableColumn<voyageOrganise, String> Desc;
    
    voyOrgServ vo = new voyOrgServ();
    @FXML
    private TextField prixTTV;
    @FXML
    private ComboBox<String> modaliteV;
     PaiementService ps = new PaiementService();
     private int idclient;
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
    private Button btnSignout;

    public void setIdclient(int idclient) {
        this.idclient = idclient;
        
        idc.setText(rs.NomP(idclient));
    }
     
     
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          BooleanBinding booleanBinding =(nbp.textProperty().isEqualTo("0")).or(
        prixVoy.textProperty().isEqualTo("")).or(
         villeDvoy.textProperty().isEqualTo("")).or(
      VilleDestVoy.textProperty().isEqualTo("").or(nbp.textProperty().isEqualTo("")).or(dateD.textProperty().isEqualTo("")).or(dateF.textProperty().isEqualTo("")).or(prixTTV.textProperty().isEqualTo("")));
            modaliteV.getItems().addAll("Cache" ,"Cheque","Carte bancaire");
            Reservervoyage.disableProperty().bind(booleanBinding);
        loadTable();
    }   
    
    
    
 
  

    @FXML
    void reserverVoyage(ActionEvent event) {

    
    
   
ReservationService rs = new ReservationService();
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       
       
       if(nbp.getText().matches("^[0-9]+$") && ! (  modaliteV.getValue()==null) )
       {
       try
       { voyageOrganise v = tableviewVO.getSelectionModel().getSelectedItem();
             java.util.Date parsedd = format.parse(dateD.getText());
              java.util.Date parseda = format.parse(dateF.getText());
              java.sql.Date datR = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Date Datedv = new java.sql.Date(parsedd.getTime());
        java.sql.Date Dateav = new java.sql.Date(parseda.getTime());
           System.out.print(Integer.parseInt(idc.getText()));
        Reservation r= new Reservation(datR,Integer.parseInt(nbp.getText()), Datedv, Dateav,v.getIdVoy(),0,0,0,"Approuve",Integer.parseInt(idc.getText()),"Voyage");
        if(rs. verifierNbplaceVoyage(v.getIdVoy(), Integer.parseInt(nbp.getText())))
        {rs.ajouter(r);
           
         rs.modifiernbplacevoyage(v.getIdVoy(),Integer.parseInt(nbp.getText()));
          Paiement p = new Paiement(modaliteV.getValue(),Float.valueOf(prixTTV.getText()),rs.afficher().get(rs.afficher().size()-1).getId(),datR);
         ps.ajouter(p);
          rs.modifiernbplacevol(v.getIdVoy(),Integer.parseInt(nbp.getText()));
          Notifications.create().title("Reservation voyage organise ").text(" Reservation est Créé ").show();
        
        }
        
        else
        {
            String s=" Nombre de place non valide il vous reste"+v.getNbrPlace();
            
            Notifications.create().title("Reservation voyage organise ").text(s).show();
            
        }
       
            
       
       }
       
       catch(ParseException e)
       {
           System.out.println(e);
       }
       }
       else
           
       {
          
            
            Notifications.create().title("Reservation voyage organise ").text(" verifier vos champs  " ) .show();
           
           
       }
    }


        
         private void loadTable() {
     
            // TODO
       
     ObservableList<voyageOrganise> oblist = FXCollections.observableArrayList();
          List <voyageOrganise> ls =vo.afficher();
          ls.forEach(e->oblist.add(e));
          System.out.print(oblist);
          VilleDep.setCellValueFactory(new PropertyValueFactory<>("villeDepart"));
          villeDest.setCellValueFactory(new PropertyValueFactory<>("villeDest"));
           DateDeb.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
            DateFin.setCellValueFactory(new PropertyValueFactory<>("dateArrive"));
            nbrPlace.setCellValueFactory(new PropertyValueFactory<>("nbrPlace"));
            categ.setCellValueFactory(new PropertyValueFactory<>("idCat"));
            prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            Desc.setCellValueFactory(new PropertyValueFactory<>("description"));
      
    tableviewVO.setItems(oblist);
   
    }
          @FXML
    void selectvol(MouseEvent event) {
      int  index =   tableviewVO.getSelectionModel().getSelectedIndex();
    villeDvoy.setText( VilleDep.getCellData(index));
     VilleDestVoy.setText(villeDest.getCellData(index));
    dateD.setText(DateDeb.getCellData(index));
    dateF.setText(DateDeb.getCellData(index));
   dateF.setText(DateFin.getCellData(index));
     prixVoy.setText(""+prix.getCellData(index));
    }   
       

   
        
    

    

    @FXML
    private void CalculerPrixTotal(KeyEvent event) {
        
            
       
           
        try{
            
          
            
            if(!nbp.getText().equals("0") && !nbp.getText().equals("") )
        { 
            Float prixTT=Integer.parseInt(nbp.getText())* Float.parseFloat(prixVoy.getText());
            
            prixTTV.setText(prixTT.toString());
           
            
        
    }
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @FXML
    private void vider(MouseEvent event) {
        
         villeDvoy.clear();
     VilleDestVoy.clear();
    dateD.clear();
    dateF.clear();
   dateF.clear();
     prixVoy.clear();
      prixTTV.clear();
     modaliteV.getItems().clear();
      nbp.setText("0");
        modaliteV.getItems().addAll("Cache" ,"Cheque","Carte bancaire");
     tableviewVO.getItems().clear();
      loadTable();
     
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
    private void reserverVO(ActionEvent event) {
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
                e.setIdC(idclient);
                
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
}

  

