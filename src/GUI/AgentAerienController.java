/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import getaway.entities.Avion;
import Entities.Vol;
import Services.AvionService;
import Services.VolService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AgentAerienController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;


    @FXML
    private Button btnSignout;
    
       private int ida; 
       
       //tabavion
    @FXML
    private TextField nom_avion;
    @FXML
    private TextField nbr_place;
    @FXML
    private TextField nomagent;
    @FXML
    private ComboBox<String> id_agence;
    @FXML
    private TableView<Avion> tb_a;
     @FXML
     private TableColumn<Vol, String> tb_nomavion;
      @FXML
    private TableColumn<Vol, Integer> tb_nbrplace;
    @FXML
    private Button id_button1;
    @FXML
    private Button modifier1;
    @FXML
    private Button supp1;
    @FXML
    private Button r_id1;
    @FXML
    private TextField id_rech1;
    @FXML
    private TextField date_depart;
    @FXML
    private TextField date_arrivee;
    @FXML
    private TextField ville_depart;
    @FXML
    private TextField ville_arrivee;
    @FXML
    private TextField prix;
    @FXML
    private TextField nbr_placedispo;
    @FXML
    private ComboBox<String> id_avion;
    @FXML
    private TableView<Vol> tb_v;
    @FXML
     private TableColumn<Vol, Timestamp> tb_datedepart;
    @FXML
    private TableColumn<Vol, Timestamp> tb_datearrivee;
    @FXML
    private TableColumn<Vol, Float> tb_prix;
    @FXML
    private TableColumn<Vol, Integer> tb_place;
    @FXML
    private TableColumn<Vol, String> tb_villedepart;
    @FXML
    private TableColumn<Vol, String> tb_villearrivee;
    @FXML
    private Button id_button;
    @FXML
    private Button modifier;
     
    @FXML
    private Button supp;
    @FXML
    private Button r_id;
    @FXML
    private Button meteo;
    @FXML
    private Button pdf;
    @FXML
    private Button stat;
    @FXML
    private TextField id_rech;
    @FXML
    private TextField id_arrivee;
    @FXML
    private TextField id_depart;
   

    
    ObservableList<Vol> listM;
    ObservableList<Vol> dataList;
    int index= -1;
  ObservableList<Vol> oblist = FXCollections.observableArrayList();
    ObservableList<Avion> oblist1 = FXCollections.observableArrayList();
    
    AvionService as = new AvionService();
    VolService vs=new VolService();

    
    public void setIdagent(int ida)
    {
        System.out.println(ida);
        this.ida=ida;
        nomagent.setText(as.NomA(ida));
        afficher(ida);
        afficher1(ida);
        getida(ida);
        getidagence(ida);
        
        
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        
//         afficher1(ida);
//     afficher(ida);
     getidagence(ida);
     getida(ida);
     Vol v = tb_v.getSelectionModel().getSelectedItem();
        // TODO
    }    

    @FXML
    private void gestionvol(ActionEvent event) throws IOException {
        

      
    }

    
    
    
    

    public void getida(int id)
    {
        
        String req ="SELECT id_avion from avion where id_agence='"+id+"'";
         try {
            Connection conn = getConnection();
             Statement ste;
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
        
            id_avion.getItems().addAll(rs.getString("id_avion"));
            }}
         catch (SQLException ex) {
            Logger.getLogger(AgentAerienController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    
    //tabavion

    @FXML
    private void selectAVION(MouseEvent event) {
         index = tb_a.getSelectionModel().getSelectedIndex();
         
    nom_avion.setText(tb_nomavion.getCellData(index));
     nbr_place.setText(""+tb_nbrplace.getCellData(index));
    }
    
    private void viderAvion(javafx.scene.input.MouseEvent event) {
            
   nom_avion.clear();
    nbr_place.clear();
   id_agence.getItems().clear();
    afficher(ida);
    id_agence.getItems().clear();
    
  
    }

    @FXML
    private void ajouter1(ActionEvent event) {
         AvionService as = new AvionService();
        Avion p =new Avion();
        if(isEmpty1())
       {return;
        } else {
           if(!Pattern.matches("[a-zA-Z]+", nom_avion.getText()))
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le nom doit contenir des alphabets seulement!");
            alert.show();
        }
       else{
     
        p.setNom_avion(nom_avion.getText());
        p.setId_agence(Integer.parseInt(id_agence.getValue()));
        p.setNbr_place(Integer.parseInt(nbr_place.getText()));
       as.ajouter(p);
         
       tb_a.getItems().clear();
       afficher1(ida);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
    alert.setTitle("information");
    alert.setHeaderText(null);
    alert.setContentText("ajout avec succes!");
    alert.showAndWait();
    }  }
    }
    
    
     private boolean isEmpty1() {
        
        if (nom_avion.getText() == null || nom_avion.getText().trim().isEmpty()) {
            warning("Veuillez saisir le nom de l'avion!");
            return true;
        }
        else if (id_agence.getValue() == null ||id_agence.getValue().trim().isEmpty()) {
            warning("Veuillez saisir l'id de l'agence!");
        }
        else if (nbr_place.getText() == null || nbr_place.getText().trim().isEmpty()) {
            warning("Veuillez saisir le nombre de place!");
            return true;
        
        }
 
        else return false;
        return false;
        
    }

    @FXML
    private void modifier1(ActionEvent event) {
         Avion p=  tb_a.getSelectionModel().getSelectedItem();
      AvionService vs = new AvionService();
        p.setNom_avion(nom_avion.getText());
        p.setNbr_place(Integer.parseInt(nbr_place.getText()));
        p.toString();
       
      vs.modifier(p);
      
    tb_a.getItems().clear();
    afficher1(ida);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
alert.setTitle("intformation");
alert.setHeaderText(null);
alert.setContentText("modification avec succes!");
alert.showAndWait();
    }

     public void afficher1(int id)
    {
         AvionService as = new AvionService();
        List<Avion> ls = as.afficher2(ida);
         ls.forEach(e->oblist1.add(e));
          System.out.print(oblist1);
        tb_nomavion.setCellValueFactory(new PropertyValueFactory<>("nom_avion"));
        tb_nbrplace.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));
        tb_a.setItems(oblist1);
      
       
    }   
    @FXML
    private void supprimer1(ActionEvent event) {
        
       Avion a = new Avion();
       
      Avion r=  tb_a.getSelectionModel().getSelectedItem();
      AvionService as = new AvionService();
     
      as.supprimer(r);
      tb_a.getItems().clear();
      tb_v.getItems().clear();
       afficher1(ida);
    }

    @FXML
    private void recherche1(ActionEvent event) {
          AvionService ps = new AvionService();
        ResultSet resultset=ps.getall();
        
        ObservableList<Avion> listavion = FXCollections.observableArrayList(ps.findAvionParnom(id_rech1.getText(),ida));
//        tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       
        tb_nomavion.setCellValueFactory(new PropertyValueFactory<>("nom_avion"));
        tb_nbrplace.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));
        afficher1(ida);
        tb_a.setItems(listavion);
    }

    @FXML
    private void trier1(ActionEvent event) {
         AvionService ps = new AvionService();
        List<Avion> pl =ps.tri_avion(ida);
        //System.out.println(p1);
            ObservableList<Avion> listavion = FXCollections.observableArrayList(pl);
        tb_nomavion.setCellValueFactory(new PropertyValueFactory<>("nom_avion"));
        tb_nbrplace.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));
        
        tb_a.setItems(listavion);
        
    }
    
     public void getidagence(int id)
    {
        
        String req ="SELECT id FROM `agent-aerien` where id='"+id+"'";
         try {
            Connection conn = getConnection();
             Statement ste;
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
        
            id_agence.getItems().addAll(rs.getString("id"));
            }}
         catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }
//tabvol
    
    public void afficher(int id)
    {
         VolService vs = new VolService();
         
        List<Vol> ls = vs.afficher2(id);
          
          ls.forEach(e->oblist.add(e));
          System.out.print(oblist);
        tb_datedepart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        tb_datearrivee.setCellValueFactory(new PropertyValueFactory<>("date_arrivee"));
        tb_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tb_villedepart.setCellValueFactory(new PropertyValueFactory<>("ville_depart"));
        tb_villearrivee.setCellValueFactory(new PropertyValueFactory<>("ville_arrivee"));
        tb_place.setCellValueFactory(new PropertyValueFactory<>("nbr_placedispo"));
        tb_v.setItems(oblist);
        
      
       
    }   
   

  
   public boolean verifierDate ()
    {
          java.sql.Timestamp Date_debut= java.sql.Timestamp.valueOf(date_depart.getText());
          java.sql.Timestamp Date_fin= java.sql.Timestamp.valueOf(date_arrivee.getText());
        
        if (Date_fin.before(Date_debut))
        
    {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Valider Les Champs");
    alert.setHeaderText(null);
    alert.setContentText("Verifier La Date ");
    alert.showAndWait();
    return false;
    }
   
   return true;
    }
    
    @FXML
    private void selectVOL(MouseEvent event) {
        index = tb_v.getSelectionModel().getSelectedIndex();
         
         
          
    ville_depart.setText(tb_villedepart.getCellData(index));
    ville_arrivee.setText(tb_villearrivee.getCellData(index));
    date_depart.setText(""+ tb_datedepart.getCellData(index));
     date_arrivee.setText(""+ tb_datearrivee.getCellData(index));
     prix.setText(""+ tb_prix.getCellData(index));
     nbr_placedispo.setText(""+tb_place.getCellData(index));
    }

    @FXML
    private void ajouter(ActionEvent event) throws Exception {
         VolService ps= new VolService();
        Vol p =new Vol();
         Vol v = tb_v.getSelectionModel().getSelectedItem();
          if(isEmpty())
       {return;
        } else {
         if (Float.parseFloat(prix.getText())<=0)
         {
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit etre positive!");
            alert.show();
         
         }
         else if(!Pattern.matches("[a-zA-Z]+", ville_depart.getText()))
        
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La ville doit contenir des alphabets seulement!");
            alert.show();
        }
          else if(!Pattern.matches("[a-zA-Z]+", ville_arrivee.getText()) )
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La ville doit contenir des alphabets seulement!");
            alert.show();
        }
          
         else if (verifierDate()==false)
         {System.out.println("error");}
         else{ 
      
       // p.setId(Integer.parseInt(id.getText()) );
          p.setDate_depart(Timestamp.valueOf(date_depart.getText()));
        p.setDate_arrivee(Timestamp.valueOf(date_arrivee.getText()));
        p.setPrix(Float.parseFloat(prix.getText()));
        p.setVille_depart(ville_depart.getText());
        p.setVille_arrivee(ville_arrivee.getText());
        p.setId_avion(Integer.parseInt(id_avion.getValue()));
        p.setNbr_placedispo(Integer.parseInt(nbr_placedispo.getText()));
         List<Timestamp> listDateDepart = ps.getListDateDepartByIdAvion( p.getId_avion(), p.getDate_depart());
         List<Timestamp> listDateArrive= ps.getListDateArriveByIdAvion( p.getId_avion(), p.getDate_arrivee());
        boolean volIsPresent= ps.checkVolIsBetweenDateDepartAndArriveIsPossible(listDateArrive, listDateDepart,p.getDate_arrivee(), p.getDate_depart()) ;
         if(volIsPresent==true) {
             System.out.println("vol existe déjà");
             Alert alert = new Alert(Alert.AlertType.WARNING);
        
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("vol existe déjà!");
        alert.showAndWait();
//             }
         System.out.println("vol existe déjà");
         }
         else {
       
      
       ps.ajouter(p);
       tb_v.getItems().clear();
       afficher(ida);
       //System.out.println("ajout avec succés");
       //Notifications.create().title("Vol").text(" Vol est Créé ").show();
         
       //tb_v.getItems().clear();
       
       
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
    alert.setTitle("information");
    alert.setHeaderText(null);
    alert.setContentText("ajout avec succes!");
    alert.showAndWait();
    } }
       }
    }
      @FXML
     private void supprimer(ActionEvent event) {
        
       
      Vol r=  tb_v.getSelectionModel().getSelectedItem();
      VolService vs = new VolService();
      vs.supprimer(r);
      tb_v.getItems().clear();
       afficher(ida);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
alert.setTitle("intformation");
alert.setHeaderText(null);
alert.setContentText("suppression avec succes!");
alert.showAndWait();
        
        
        
    }
    @FXML
    private void modifier(ActionEvent event) {
         Vol p=  tb_v.getSelectionModel().getSelectedItem();
      VolService vs = new VolService();
       p.setDate_depart(Timestamp.valueOf(date_depart.getText()));
        p.setDate_arrivee(Timestamp.valueOf(date_arrivee.getText()));
        p.setPrix(Float.parseFloat(prix.getText()));
        p.setVille_depart(ville_depart.getText());
        p.setVille_arrivee(ville_arrivee.getText());
        //p.setId_avion(Integer.parseInt(id_avion.getValue()));
        p.setNbr_placedispo(Integer.parseInt(nbr_placedispo.getText()));
        p.toString();
       
      vs.modifier(p);
      
    tb_v.getItems().clear();
    afficher(ida);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
alert.setTitle("intformation");
alert.setHeaderText(null);
alert.setContentText("modification avec succes!");
alert.showAndWait();
    }
 
    


    @FXML
    private void trier(ActionEvent event) {
        VolService ps = new VolService();
        List<Vol> p1=ps.tri_vol(ida);
        
        ObservableList<Vol> listvol = FXCollections.observableArrayList(p1);
        tb_datedepart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        tb_datearrivee.setCellValueFactory(new PropertyValueFactory<>("date_arrivee"));
        tb_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tb_villedepart.setCellValueFactory(new PropertyValueFactory<>("ville_depart"));
        tb_villearrivee.setCellValueFactory(new PropertyValueFactory<>("ville_arrivee"));
        tb_place.setCellValueFactory(new PropertyValueFactory<>("nbr_placedispo"));
      
        tb_v.setItems(listvol);
       
    }
    
     

    @FXML
    private void recherche(ActionEvent event) {
        
           VolService as = new VolService();
         Vol a=new Vol ();
        
        ObservableList<Vol> Vol = as.rechercherVol(id_rech.getText(),ida);
        ObservableList<Vol> listvol = FXCollections.observableArrayList(as.findVolParDest(id_arrivee.getText(),ida));
        ObservableList<Vol> listvol1 = FXCollections.observableArrayList(as.findVolPardepart(id_depart.getText(),ida));
       
        tb_datedepart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        tb_datearrivee.setCellValueFactory(new PropertyValueFactory<>("date_arrivee"));
        tb_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tb_villedepart.setCellValueFactory(new PropertyValueFactory<>("ville_depart"));
        tb_villearrivee.setCellValueFactory(new PropertyValueFactory<>("ville_arrivee"));
        tb_place.setCellValueFactory(new PropertyValueFactory<>("nbr_placedispo"));
        tb_v.getItems().clear();
        tb_v.setItems(Vol);
        
    }

    @FXML
    private void meteo(ActionEvent event) {
        
         
          try{
		  FXMLLoader loader=new FXMLLoader(getClass().getResource("primary.fxml"));
		Parent root = loader.load();
		PrimaryController  e = loader.getController();
                e.setIdagent(ida);
                
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
       
    }

    @FXML
    private void telechargerVol(ActionEvent event) throws IOException,FileNotFoundException, SQLException{
         try {
            String file_name="C:\\Users\\Malek\\Desktop\\Gestion_Vol.pdf";
            Document document = new Document();
                
           
            PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream(file_name));
           
            document.open();
            document.add(new Chunk(""));
            Connection conn = getConnection();
            
            String query = "SELECT * from vol";
            Statement st = conn.createStatement();
            ResultSet rs=st.executeQuery(query);

            PdfPTable t = new PdfPTable(6);
            PdfPCell c1 = new PdfPCell(new Phrase("date_depart"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("date_arrivee"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("ville_depart"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("ville_arrivee"));
            t.addCell(c4);
              PdfPCell c5 = new PdfPCell(new Phrase("prix"));
            t.addCell(c5);
              PdfPCell c6 = new PdfPCell(new Phrase("nbr_placedispo"));
            t.addCell(c6);
           
             
            t.setHeaderRows(6);
            while(rs.next()){
                t.addCell(rs.getString(1));
                t.addCell(rs.getString(2));
                t.addCell(rs.getString(3));
                t.addCell(rs.getString(4));
                t.addCell(rs.getString(5));
                t.addCell(rs.getString(6));
                 
                document.add(t);
            }
            document.close();
            System.out.println("finished");
        } catch (DocumentException ex) {
            System.out.println(ex);
        }
        JOptionPane.showMessageDialog(null,"PDF téléchargé ");
    }

    @FXML
    private void statistique(ActionEvent event) {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("Stat.fxml"));
                       Parent root ;
        try {
            root=loader.load();
             stat.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GUI.StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public Connection getConnection(){
    Connection conn;
    try{
        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/getaway","root","");
        return conn;
    }
    catch(SQLException ex){
        System.out.println("Erreur : " + ex.getMessage());
        return null;
    }
    }
     
      private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch(SQLException ex){
        }
        
       
    }
       private void warning(String txt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(null);
        alert.setContentText(txt);
        Optional<ButtonType> action = alert.showAndWait();
    }

     private boolean isEmpty() {
        
        if (date_depart.getText() == null || date_depart.getText().trim().isEmpty()) {
            warning("Veuillez saisir la date de départ!");
            return true;
        }
        else if (date_arrivee.getText() == null || date_arrivee.getText().trim().isEmpty()) {
            warning("Veuillez saisir la description de la date d'arrivee!");
        }
        else if (prix.getText() == null || prix.getText().trim().isEmpty()) {
            warning("Veuillez saisir le prix du vol!");
            return true;
        }
        else if (ville_depart.getText() == null || ville_depart.getText().trim().isEmpty()) {
            warning("Veuillez saisir la ville de depart!");
            return true;
        }
        
        else if (ville_arrivee.getText() == null || ville_arrivee.getText().trim().isEmpty()) {
            warning("Veuillez saisir la ville d'arrivee!");
            return true;
        }else if (id_avion.getValue() == null ||id_avion.getValue().trim().isEmpty()) {
            warning("Veuillez saisir l'id de l'avion!");
            return true;
        }
        else if (nbr_placedispo.getText() == null || nbr_placedispo.getText().trim().isEmpty()) {
            warning("Veuillez saisir le nombre de place !");
            return true;
        }
 
        else return false;
        return false;
        
    }

    @FXML
    private void consulterprofil(ActionEvent event) {
        
         try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCompteAgent.fxml"));
		Parent root = loader.load();
		ModifierCompteAgentController e = loader.getController();
                e.setIdc(ida);
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }

    @FXML
    private void logout(ActionEvent event) {
        
        
    
                      try{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
		Parent root = loader.load();
		LoginController  e = loader.getController();
               
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}
    }
    
    
}