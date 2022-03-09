/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.categorie;
import Entities.voyageOrganise;
import Services.*;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
private Timeline locationUpdateTimeline;
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
    private Button btnsearchCat;
    @FXML
    private TextField txtSearchCat;
    @FXML
    private TableView<categorie> tableviewCat;
    @FXML
    private TableColumn<categorie, Integer> idCat;
    @FXML
    private TableColumn<categorie, String> nomCat;
    
      final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
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
        if((txtvilleDep.getText().equals(""))||(txtvilleDest.getText().equals(""))||(txtdesc.getText().equals("")))
            JOptionPane.showMessageDialog(null, "verifier vos champs vides ");
                
        else if(!(txtvilleDep.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier la ville depart");}
        else if((from.compareTo(to) >= 0)) {

            JOptionPane.showMessageDialog(null, "verifier  date ");}
        
       else if(!(txtvilleDest.getText().matches("^[a-zA-Z]+$"))){

            JOptionPane.showMessageDialog(null, "verifier la ville arrive");
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
            Logger.getLogger(GUI.StatisiqueControllerVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
  
    
       @FXML
    void map(MouseEvent event) {
Stage stage = new Stage ();
      
        webEngine.load(getClass().getResource("/gui/map.html").toString());
       /////////////////////////
       
        // create map type buttons
        final ToggleGroup mapTypeGroup = new ToggleGroup();
        final ToggleButton road = new ToggleButton("Road");
        road.setSelected(true);
        road.setToggleGroup(mapTypeGroup);
        final ToggleButton satellite = new ToggleButton("Satellite");
        satellite.setToggleGroup(mapTypeGroup);
        final ToggleButton hybrid = new ToggleButton("Hybrid");
        hybrid.setToggleGroup(mapTypeGroup);
        final ToggleButton terrain = new ToggleButton("Terrain");
        terrain.setToggleGroup(mapTypeGroup);
        mapTypeGroup.selectedToggleProperty().addListener( new ChangeListener<Toggle>() {
            public void changed(
                    ObservableValue<? extends Toggle> observableValue,
                    Toggle toggle, Toggle toggle1) {
                if (road.isSelected()) {
                    webEngine.executeScript("document.setMapTypeRoad()");
                } else if (satellite.isSelected()) {
                    webEngine.executeScript("document.setMapTypeSatellite()");
                } else if (hybrid.isSelected()) {
                    webEngine.executeScript("document.setMapTypeHybrid()");
                } else if (terrain.isSelected()) {
                    webEngine.executeScript("document.setMapTypeTerrain()");
                }
            }
        });
        // add map source toggles
        ToggleGroup mapSourceGroup = new ToggleGroup();
        final ToggleButton google = new ToggleButton("Google");
        google.setSelected(true);
        google.setToggleGroup(mapSourceGroup);
        // listen to selected source
        mapSourceGroup.selectedToggleProperty().addListener(
                            new ChangeListener<Toggle>() {
            public void changed(
                    ObservableValue<? extends Toggle> observableValue,
                    Toggle toggle, Toggle toggle1) {
                terrain.setDisable(true);
                if (google.isSelected()) {
                    terrain.setDisable(false);
                    webEngine.load(
                          getClass().getResource("googlemap.html").toString());
                }
                mapTypeGroup.selectToggle(road);
            }
        });
        // add search
        final TextField searchBox = new TextField("95054");
       // searchBox.setC
        searchBox.setPromptText("Search");

  

        Button zoomIn = new Button("Zoom In");
        zoomIn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                webEngine.executeScript("document.zoomIn()");
            }
        });
        Button zoomOut = new Button("Zoom Out");
        zoomOut.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                webEngine.executeScript("document.zoomOut()");
            }
        });
        // create toolbar
        ToolBar toolBar = new ToolBar();
        toolBar.getStyleClass().add("map-toolbar");
        toolBar.getItems().addAll(
                road, satellite, hybrid, terrain,
                createSpacer(),
                google,
                createSpacer(),
                new Label("Location:"), searchBox, zoomIn, zoomOut);
          
        
        
        /////////////////////
                // create root
        BorderPane root = new BorderPane();
        root.getStyleClass().add("map");
        root.setCenter(webView);
        root.setTop(toolBar);
        stage.setTitle("localisation");
        Scene scene = new Scene(root,1000,700, Color.web("#666970"));
          scene.getStylesheets().add("/webmap/WebMap.css");
        stage.setScene(scene);
        // show stage
        stage.show();
        //
      
        // show stag
    }
              public void changed(
                    ObservableValue<? extends String> observableValue,
                    String s, String s1) {
                // delay location updates to we don't go too fast file typing
                if (locationUpdateTimeline!=null) locationUpdateTimeline.stop();
                locationUpdateTimeline = new Timeline();
//               locationUpdateTimeline.getKeyFrames().add(new KeyFrame(new Duration(400),
//                       new EventHandler<ActionEvent>() {
//                        public void handle(ActionEvent actionEvent) {
//                         //   webEngine.executeScript("document.goToLocation(\""+searchBox.getRawText()+"\")");
//                        }}));



//                locationUpdateTimeline.getKeyFrames().add(new KeyFrame(new Duration(400),
//                            new eh<ActionEvent>() {
//                        public void handle(ActionEvent actionEvent) {
//                            webEngine.executeScript("document.goToLocation(\""+
//                                    searchBox.getRawText()+"\")");
//                        }
//                    })
//                );
                locationUpdateTimeline.play();
            }
     private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
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
    private void deleteCat(ActionEvent event) throws SQLException {
categorie cat =  tableviewCat.getSelectionModel().getSelectedItem();

Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Notification de Confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Vous confirmer la suppression ?");
		Optional<ButtonType> action = alert.showAndWait();
                
                if(cats.verifIdCat(cat)==true)
        {
            JOptionPane.showMessageDialog(null, "impossible de suppression");
        }
        else
        {
cats.delete(cat.getIdcat());                
                tableviewCat.getItems().clear();
                

 loadTableCat();
    }
    }
    
    @FXML
    private void updateCat(ActionEvent event) {
categorie cat =  tableviewCat.getSelectionModel().getSelectedItem();

      categorie c= new categorie();
      
      c.setIdcat(Integer.parseInt(txtidCat.getText()));
      c.setNomcat(txtnomCat.getText());
             
        cats.update(c);
        tableviewCat.getItems().clear();
        loadTableCat();
    }
@FXML
    void searchCat(ActionEvent event) {
ObservableList<categorie> oblist = FXCollections.observableArrayList();
     List <categorie> ls =cats.FindNameCat(txtSearchCat.getText());
    tableviewCat.getItems().clear();
    tableviewCat.getItems().addAll(ls);
    
    }
    
    @FXML
    private void selectCat(MouseEvent event) {
      
        int  index1 = tableviewCat.getSelectionModel().getSelectedIndex();
      txtnomCat.setText(nomCat.getCellData(index1));
      txtidCat.setText(""+idCat.getCellData(index1));
      
     
    }
     
}
