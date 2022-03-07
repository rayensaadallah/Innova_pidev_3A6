/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.*;
import Services.*;
import Utilis.Datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author TheBoss'07
 */
public class GestionActivitesController implements Initializable {
    
    

    private TextField txtnom;
    private TextField txtduree;
    private TextField txtnbrp;
    private TableView<Activite> tvactivite;
    private TextArea textdescrip;
    private TextField txttype;
    private DatePicker txtdate;
    private TextField txtprix;
    private TextField txtloc;
    private TableColumn<Activite, String> colnom;
    private TableColumn<Activite, String> colduree;
    private TableColumn<Activite, Integer> colnbrp;
    private TableColumn<Activite, String> coldate;
    private TableColumn<Activite, String> coltype;
    private TableColumn<Activite, String> colloc;
    private TableColumn<Activite, Float> colprix;
    private TableColumn<Activite, String> coldesc;
    private TextField txtinput;
    @FXML
    private TableView<Activite> tvavisa;
    @FXML
    private TableView<Avis> tvavis;
    @FXML
    private TableColumn<Activite, String> colnomac;
    @FXML
    private TableColumn<Activite, String> coldescripac;
    @FXML
     TextField txtnoma;
    @FXML
    private ComboBox<String> txtcomment;
    @FXML
    private TableColumn<Avis, String> colmessage;
    @FXML
    private TableColumn<Avis, String> coldateav;
    @FXML
    private TableColumn<Activite, String> colnomact;
    @FXML
    private TableColumn<?, ?> colnomcl;
    @FXML
    private Button btnmodifierav;
    @FXML
    private Button btnsupprimerav;
    @FXML
    private Button btnviderav;
    @FXML
    private Button btnajouterav;    

    private Connection con = Datasource.getInstance().getCnx();
    @FXML
    private Button btnstat;
    @FXML
    private Button btnOverview;
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
    private Button bReclamation;
    @FXML
    private Button btnOrders1;
    @FXML
    private Button btnSignout;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      affichage();
      
      
      Activite a = tvactivite.getSelectionModel().getSelectedItem();
      Avis av = tvavis.getSelectionModel().getSelectedItem();
      Activite ava = tvavisa.getSelectionModel().getSelectedItem();
      
      
      ObservableList<String> list = FXCollections.observableArrayList("Très satisfait", "Satisfait", "Neutre", "Très déçu");
      txtcomment.setItems(list);
      
      
             
 
    }
    
        ActiviteService as = new ActiviteService();
         AvisService avs = new AvisService();
         
        ObservableList<Activite> oblist = FXCollections.observableArrayList();
        
        
     
    void ajouter(ActionEvent event) throws Exception {
        
         if ((txtnom.getText().length()==0)|| (txtduree.getText().length()==0)||(txtnbrp.getText().length()==0) ||(textdescrip.getText().length()==0)||(txttype.getText().length()==0)|| (txtdate.getValue()==null) || (txtprix.getText().length()==0) || (txtloc.getText().length()==0) )
           JOptionPane.showMessageDialog(null, "Un champs est vide, verifier avant !"); 

        else if(!(txtnom.getText().matches("[A-Za-z]+$"))) {

            JOptionPane.showMessageDialog(null, "Verfier le nom d'activite");
             }
          else if(!(txtduree.getText().matches("^[0-9]+$+[H-h]+[0-9]"))) {

            JOptionPane.showMessageDialog(null, "La duree doit être comme cet exemple: 1h30 ou 1H30");
             }
             else if(!(txtnbrp.getText().matches("[0-9]"))) {

            JOptionPane.showMessageDialog(null, "Il n'est pas un nombre");
             }
             else if(!(Integer.parseInt(txtnbrp.getText())>0)){

            JOptionPane.showMessageDialog(null, "Le nombre est négatif");
             }
              
           else if  (!(Float.parseFloat(txtprix.getText())>0)) {

            JOptionPane.showMessageDialog(null, "Prix négatif");
             }
             else{
        
        Activite a =new Activite();
         String date = txtdate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
         
        a.setNom(txtnom.getText());
        a.setDuree(txtduree.getText());
        a.setNbrPlace(Integer.parseInt(txtnbrp.getText()));
        a.setDate(date);
        a.setType(txttype.getText());
        a.setLocation(txtloc.getText());
        a.setPrix(Float.parseFloat(txtprix.getText()));
        a.setDescrip(textdescrip.getText());
        
        as.ajouter(a);
        
        
        saveAlert(a);
        tvactivite.getItems().clear();
        affichage();

           }
       }
    
    
    private void vider(ActionEvent event) {
        clearFields();
    }
    
     
        
        

    private void modifier(ActionEvent event) {
        
         Activite a=  tvactivite.getSelectionModel().getSelectedItem();
      
       String date = txtdate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
         
        a.setNom(txtnom.getText());
        a.setDuree(txtduree.getText());
        a.setNbrPlace(Integer.parseInt(txtnbrp.getText()));
        a.setDate(date);
        a.setType(txttype.getText());
       a.setLocation(txtloc.getText());
        a.setPrix(Float.parseFloat(txtprix.getText()));
      a.setDescrip(textdescrip.getText());
      
     as.modifier(a);
     
    tvactivite.getItems().clear();
    affichage();
  
   
    }

   
    private void supprimer(ActionEvent event) {
        
        Activite a =  tvactivite.getSelectionModel().getSelectedItem();  
        Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Notification de Confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Vous confirmer la suppression ?");
		Optional<ButtonType> action = alert.showAndWait();
        as.supprimer(a);
        tvactivite.getItems().clear();
        affichage();
     

    }
    
    
    public void affichage() {
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

//    
//       
//    }
    
     private boolean validate(String field, String value, String pattern){
		if(!value.isEmpty()){
			Pattern p = Pattern.compile(pattern);
	        Matcher m = p.matcher(value);
	        if(m.find() && m.group().equals(value)){
	            return true;
	        }else{
	        	validationAlert(field, false);            
	            return false;            
	        }
		}else{
			validationAlert(field, true);            
            return false;
		}        
    }
    
    private boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
        	validationAlert(field, true);            
            return false;            
        }
    }	
	
	private void validationAlert(String field, boolean empty){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erreur de validation");
        alert.setHeaderText(null);
        if(field.equals("Role")) alert.setContentText("Please Select "+ field);
        else{
        	if(empty) alert.setContentText("Please Enter "+ field);
        	else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
	}
        
        private void saveAlert(Activite a){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notification d'ajout.");
		alert.setHeaderText(null);
		alert.setContentText("L'activite "+a.getNom()+" est ajouter avec succées");
		alert.showAndWait();
	}
	
	private void updateAlert(Activite a){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notification de modification.");
		alert.setHeaderText(null);
		alert.setContentText("L'activite"+a.getNom()+" est modifier avec succees");
		alert.showAndWait();
	}

  private void clearFields() {
		txtduree.clear();
		txtnom.clear();
                txtnbrp.clear();
                textdescrip.clear();
                txttype.clear();
                txtdate.getEditor().clear();
                txtprix.clear();
                txtloc.clear();
		
	}

    private void trier(ActionEvent event) {
        ActiviteService as = new ActiviteService();
        

       ObservableList <Activite> activites= as.trierActiviteNbrplace();

        colnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("Duree"));
        colnbrp.setCellValueFactory(new PropertyValueFactory<>("NbrPlace"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colloc.setCellValueFactory(new PropertyValueFactory<>("Location"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("Descrip"));
        
        tvactivite.setItems(activites);
    }

   
    private void selectAll(javafx.scene.input.MouseEvent event) {
    int index= -1;
    index = tvactivite.getSelectionModel().getSelectedIndex();
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");     
    LocalDate localDate = LocalDate.parse(coldate.getCellData(index), formatter);
         
 
     txtduree.setText(colduree.getCellData(index));
     txtnom.setText(""+  colnom.getCellData(index));
     txtnbrp.setText(""+colnbrp.getCellData(index));
     textdescrip.setText(""+coldesc.getCellData(index));
     txttype.setText(""+coltype.getCellData(index));
     txtdate.setValue(localDate);
     txtprix.setText(""+colprix.getCellData(index));
     txtloc.setText(""+colloc.getCellData(index));
    }
   /************************************************Avis**********************************************
    */
    
 
 
}

    
    
        
//    private List<Activite> recupNomAct() 
//    {
//        
//        int ref = recupRefact();
//                List<Activite> nom = new ArrayList<>();
//
//    String req="SELECT Nom from activite INNER JOIN avis WHERE activite.RefAct=avis.RefActivite";
//    PreparedStatement ste;
//    
//        try {
//            ste = (PreparedStatement) con.prepareStatement(req);
//            ResultSet rs = ste.executeQuery();
//         while(rs.next()){
//                Activite a = new Activite();
//            rs.getString(1);
//             nom.add(rs);
//            } 
//        } catch (SQLException ex) {
//            Logger.getLogger(GestionActivitesController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         System.out.println(nom);
//return nom; 
//    }

    


 




