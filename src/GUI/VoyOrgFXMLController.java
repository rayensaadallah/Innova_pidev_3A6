/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.*;
import Services.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Amal Chibani
 */
public class VoyOrgFXMLController implements Initializable {

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

     @FXML
    private TextField txtvilleDep;

    @FXML
    private TextField txtvilleDest;

    @FXML
    private DatePicker txtdateDeb;

    @FXML
    private DatePicker txtdateFin;

    @FXML
    private TextField txtnbrPlace;

    @FXML
    private TextField txtCat;

    @FXML
    private TextField txtprix;

    @FXML
    private TextArea txtdesc;
    
 @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnsearch;

    @FXML
    private TextField txtsearch;
    
     @FXML
    private Button btnsort;

    @FXML
    private WebView WebView;
    
    voyOrgServ vos = new voyOrgServ();
    categorieServ cats= new categorieServ();
    @FXML
    private Button btnstat;
    @FXML
    private TextField txtidCat;
    @FXML
    private TextField txtnomCat;
   
    @FXML
    private Button btnaddC;
    @FXML
    private Button btndeleteC;
    @FXML
    private Button btnupdateC;
    @FXML
    private TableView<categorie> tableviewCat;
    @FXML
    private TableColumn<categorie, Integer> idCat;
    @FXML
    private TableColumn<categorie, String> nomCat;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnOrders1;
    @FXML
    private Button btnPackages;
    /**
     * Initializes the controller class.
     */
    
    private void loadTable() {
     
            // TODO
       
     ObservableList<voyageOrganise> oblist = FXCollections.observableArrayList();
          List <voyageOrganise> ls =vos.afficher();
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTable();
        loadTableCat();
    }  
   

    @FXML
    void Add(ActionEvent event) throws Exception{

        LocalDate from = txtdateDeb.getValue();
    LocalDate to = txtdateFin.getValue();
        voyOrgServ vos= new voyOrgServ();
        if(txtvilleDep.getText().equals("")||(txtvilleDest.getText().equals("")))
            JOptionPane.showMessageDialog(null, "verifier vos champs vides ");
                
        else if(!(txtvilleDep.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier votre choix");}
        else if((from.compareTo(to) >= 0)) {

            JOptionPane.showMessageDialog(null, "verifier  date ");}
        
       else if(!(txtvilleDest.getText().matches("^[a-zA-Z]+$"))){

            JOptionPane.showMessageDialog(null, "verifier votre choix");
             }

        else{
       voyageOrganise v;
            v = new voyageOrganise(txtvilleDep.getText(),txtvilleDest.getText(),txtdateDeb.getValue().toString(),txtdateFin.getValue().toString(),Integer.parseInt(txtnbrPlace.getText()),Integer.parseInt(txtCat.getText()),Float.parseFloat(txtprix.getText()),txtdesc.getText());
         vos.ajouter(v);

      tableviewVO.getItems().clear();
       System.out.println("ajout avec succés");
       loadTable();} 
    }
   

    
    @FXML
    void deletevo(ActionEvent event) {
        
voyageOrganise v =  tableviewVO.getSelectionModel().getSelectedItem();
vos.delete(v.getIdVoy());
tableviewVO.getItems().clear();
 loadTable();
    }

    @FXML
    void updatevo(ActionEvent event) throws Exception {
        voyageOrganise v =  tableviewVO.getSelectionModel().getSelectedItem();
        String dateD = txtdateDeb.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String dateF = txtdateFin.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        v.setVilleDepart(txtvilleDep.getText());
        v.setVilleDest(txtvilleDest.getText());
        v.setDateDepart(dateD);
        v.setDateArrive(dateF);
        v.setNbrPlace(Integer.parseInt(txtnbrPlace.getText()));
        v.setPrix(Float.parseFloat(txtprix.getText()));
        v.setIdCat(Integer.parseInt(txtCat.getText()));
        v.setDescription(txtdesc.getText());
        vos.update(v);
        tableviewVO.getItems().clear();
        loadTable();
    }

    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         
  
    @FXML
    private void select(javafx.scene.input.MouseEvent event) {
        int index= -1;
         index = tableviewVO.getSelectionModel().getSelectedIndex();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 

    String  date =DateDeb.getCellData(index).toString();
    String  date1 =DateFin.getCellData(index).toString();
    LocalDate localDate = LocalDate.parse(date, formatter);
    LocalDate localDate1 = LocalDate.parse(date1, formatter);
   // int  index = tableviewVO.getSelectionModel().getSelectedIndex();
     txtvilleDep.setText(VilleDep.getCellData(index));
     txtvilleDest.setText(villeDest.getCellData(index));
     txtdateDeb.setValue(localDate);
     txtdateFin.setValue(localDate1);
      txtnbrPlace.setText(""+nbrPlace.getCellData(index));
      txtprix.setText(""+prix.getCellData(index));
      txtCat.setText(""+categ.getCellData(index));
      txtdesc.setText(Desc.getCellData(index));
    }
    
    @FXML
    void searchvo(ActionEvent event) {
    ObservableList<voyageOrganise> oblist = FXCollections.observableArrayList();
     List <voyageOrganise> ls =vos.FindVille(txtsearch.getText());
    tableviewVO.getItems().clear();
    tableviewVO.getItems().addAll(ls);
        
    }
     @FXML
    void sortprix(ActionEvent event) {

         
        vos.TrierParPrix();

       ObservableList <voyageOrganise> oblist= vos.TrierParPrix();

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
    private void statistique(ActionEvent event) {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("StatisiqueVO.fxml"));
                       Parent root ;
        try {
            root=loader.load();
             btnstat.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GUI.StatisiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
//     public boolean verifierDate ()
//    {
//          java.sql.Timestamp DatT= java.sql.Timestamp.valueOf(DateDeb.getText());
//          java.sql.Timestamp Date_fin= java.sql.Timestamp.valueOf(DateFin.getText());
//        
//        if (DateFin.before(DateDeb))
//        
//    {
//        
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//    alert.setTitle("Valider Les Champs");
//    alert.setHeaderText(null);
//    alert.setContentText("Verifier La Date ");
//    alert.showAndWait();
//    return false;
//    }
//   
//   return true;
//    }
    
       @FXML
    void map(MouseEvent event) {
Stage stage = new Stage ();
         
        final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/gui/map.html").toString());
       
        
        stage.setTitle("localisation");
        Scene scene = new Scene(webView,1000,700, Color.web("#666970"));
        stage.setScene(scene);
        // show stage
        stage.show();
    }
    static { // use system proxy settings when standalone application
        System.setProperty("java.net.useSystemProxies", "true");
    }

    @FXML
    private void addCat(ActionEvent event) {
         categorie cat;
            cat = new categorie(Integer.parseInt(txtidCat.getText()),txtnomCat.getText());
         cats.ajouter(cat);

      tableviewCat.getItems().clear();
        
     
       System.out.println("ajout avec succés");
       loadTableCat();} 

    private void loadTableCat() {
        ObservableList<categorie> oblist1 = FXCollections.observableArrayList();
          List <categorie> ls =cats.afficher();
          ls.forEach(e->oblist1.add(e));
          System.out.print(oblist1);
        idCat.setCellValueFactory(new PropertyValueFactory<>("idcat"));
            nomCat.setCellValueFactory(new PropertyValueFactory<>("nomcat"));
      
    tableviewCat.setItems(oblist1);
        
    }

    @FXML
    private void deleteCat(ActionEvent event) {
categorie cat =  tableviewCat.getSelectionModel().getSelectedItem();
cats.delete(cat.getIdcat());
tableviewCat.getItems().clear();
 loadTableCat();
    }

    @FXML
    private void updateCat(ActionEvent event) {
categorie cat =  tableviewCat.getSelectionModel().getSelectedItem();

        cat.setNomcat(txtnomCat.getText());
        
        cat.setIdcat(Integer.parseInt(txtidCat.getText()));
       
        cats.update(cat);
        tableviewCat.getItems().clear();
        loadTableCat();
    }

    @FXML
    private void selectCat(MouseEvent event) {
      
        int  index1 = tableviewCat.getSelectionModel().getSelectedIndex();
      txtnomCat.setText(nomCat.getCellData(index1));
      txtidCat.setText(""+idCat.getCellData(index1));
      
     
    }
     
}
