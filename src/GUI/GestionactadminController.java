/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activite;
import Entities.Avis;
import Services.ActiviteService;
import Services.AvisService;
import Utilis.Datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author TheBoss'07
 */
public class GestionactadminController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtduree;
    @FXML
    private TextField txtnbrp;
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
    @FXML
    private TextArea textdescrip;
    @FXML
    private TextField txttype;
    @FXML
    private DatePicker txtdate;
    @FXML
    private TextField txtprix;
    @FXML
    private TextField txtloc;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnvider;
    @FXML
    private Button btnrecherche;
    @FXML
    private TextField txtinput;
    @FXML
    private Button btntri;
    @FXML
    private Button btnetat;
    @FXML
    private Button btnstat2;
    @FXML
    private TableView<Avis> tvavis;
    @FXML
    private TableColumn<Avis, String> colmessage;
    @FXML
    private TableColumn<Avis, String> coldateav;
    @FXML
    private TableColumn<Avis, String> colnomcl;
    @FXML
    private TableColumn<Avis, String> colnomact;
    private Connection con = Datasource.getInstance().getCnx();
    @FXML
    private Button btngestU;
    @FXML
    private Button btngestVo;
    @FXML
    private Button btngestActv;
    @FXML
    private Button btnStat;
    @FXML
    private Button btnlogout;
    private int ida;

    public void setIda(int ida) {
        this.ida = ida;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichage();
        affichageAv();
       

        Activite a = tvactivite.getSelectionModel().getSelectedItem();
        Avis av = tvavis.getSelectionModel().getSelectedItem();
        help();
    }

    ActiviteService as = new ActiviteService();
    AvisService avs = new AvisService();

    ObservableList<Activite> oblist = FXCollections.observableArrayList();
    ObservableList<Avis> oblistav = FXCollections.observableArrayList();

    public void affichageAv() {
        List<Avis> avis = avs.afficher();
        avis.forEach(e -> oblistav.add(e));
        Activite a = new Activite();

        colmessage.setCellValueFactory(new PropertyValueFactory<>("Message"));
        coldateav.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colnomcl.setCellValueFactory(new PropertyValueFactory<>("NomClient"));
        colnomact.setCellValueFactory(new PropertyValueFactory<>("NomAct"));

        tvavis.setItems(oblistav);

    }

    public void affichage() {
        List<Activite> activites = as.afficher();
        activites.forEach(e -> oblist.add(e));

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

    private void saveAlert(Activite a) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification d'ajout.");
        alert.setHeaderText(null);
        alert.setContentText("L'activite " + a.getNom() + " est ajouter avec succées");
        alert.showAndWait();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        {

            if ((txtnom.getText().length() == 0) || (txtduree.getText().length() == 0) || (txtnbrp.getText().length() == 0) || (txttype.getText().length() == 0) || (txtloc.getText().length() == 0) || (txtprix.getText().length() == 0) || (textdescrip.getText().length() == 0)) {
                JOptionPane.showMessageDialog(null, "Il y'a un champ vide, verifier! ");
            } else if ((txtdate.getValue() == null)) {
                JOptionPane.showMessageDialog(null, "Date Vide ");
            } else if (!(txtnom.getText().matches("^[a-zA-Z]+$"))) {

                JOptionPane.showMessageDialog(null, "Verifier le nom (Pas de chiffres)");
            } else if (!(txtduree.getText().matches("^[0-9][hH][0-9]+$"))) {

                JOptionPane.showMessageDialog(null, "Exemple de durée: 1h30 OU 1H30");
            } else if (!(txtnbrp.getText().matches("[0-9]+$"))) {

                JOptionPane.showMessageDialog(null, "Nombre de place doit etre un nombre");
            } else if (!(txttype.getText().matches("^[a-zA-Z]+$"))) {

                JOptionPane.showMessageDialog(null, "Verifier le type (Pas de chiffres)");
            } else if (!(txtloc.getText().matches("^[a-zA-Z]+$"))) {

                JOptionPane.showMessageDialog(null, "Verifier la localisation (Pas de chiffres)");
            } else if (Float.parseFloat(txtprix.getText()) <= 0) {

                JOptionPane.showMessageDialog(null, "Prix doit être positif");
            } else {
                Activite a = new Activite();
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

    @FXML
    private void selectAll(javafx.scene.input.MouseEvent event) {
        int index = -1;
        index = tvactivite.getSelectionModel().getSelectedIndex();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(coldate.getCellData(index), formatter);

        txtduree.setText(colduree.getCellData(index));
        txtnom.setText("" + colnom.getCellData(index));
        txtnbrp.setText("" + colnbrp.getCellData(index));
        textdescrip.setText("" + coldesc.getCellData(index));
        txttype.setText("" + coltype.getCellData(index));
        txtdate.setValue(localDate);
        txtprix.setText("" + colprix.getCellData(index));
        txtloc.setText("" + colloc.getCellData(index));
    }

    @FXML
    private void modifier(ActionEvent event) {
        if ((txtnom.getText().length() == 0) || (txtduree.getText().length() == 0) || (txtnbrp.getText().length() == 0) || (txttype.getText().length() == 0) || (txtloc.getText().length() == 0) || (txtprix.getText().length() == 0) || (textdescrip.getText().length() == 0)) {
            JOptionPane.showMessageDialog(null, "Il y'a un champ vide, verifier! ");
        } else if ((txtdate.getValue() == null)) {
            JOptionPane.showMessageDialog(null, "Date Vide ");
        } else if (!(txtnom.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "Verifier le nom (Pas de chiffres)");
        } else if (!(txtduree.getText().matches("^[0-9][hH][0-9]+$"))) {

            JOptionPane.showMessageDialog(null, "Exemple de durée: 1h30 OU 1H30");
        } else if (!(txtnbrp.getText().matches("[0-9]+$"))) {

            JOptionPane.showMessageDialog(null, "Nombre de place doit etre un nombre");
        } else if (!(txttype.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "Verifier le type (Pas de chiffres)");
        } else if (!(txtloc.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "Verifier la localisation (Pas de chiffres)");
        } else if (Float.parseFloat(txtprix.getText()) <= 0) {

            JOptionPane.showMessageDialog(null, "Prix doit être positif");
        } else {

            Activite a = tvactivite.getSelectionModel().getSelectedItem();

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
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
        Activite a = tvactivite.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Notification de Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Vous confirmer la suppression ?");
        Optional<ButtonType> action = alert.showAndWait();
        
        if(as.verif(a)==true)
        {
            JOptionPane.showMessageDialog(null, "L'activite à des avis, pas de suppression");
        }
        else
        {
        as.supprimer(a);
        tvactivite.getItems().clear();
        affichage();
    }
    }

    @FXML
    private void vider(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void recherchek(KeyEvent event) {
        ActiviteService as = new ActiviteService();
        Activite a = new Activite();

        ObservableList<Activite> activ = as.rechercherActivite(txtinput.getText());

        colnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("Duree"));
        colnbrp.setCellValueFactory(new PropertyValueFactory<>("NbrPlace"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colloc.setCellValueFactory(new PropertyValueFactory<>("Location"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("Descrip"));

        tvactivite.setItems(activ);
    }

    @FXML
    private void trier(ActionEvent event) {
        ActiviteService as = new ActiviteService();

        ObservableList<Activite> activites = as.trierActiviteNbrplace();

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

    @FXML
    private void help() {
        int nb = 0;
        String req = "Select COUNT(RefAct) from Activite";
        PreparedStatement ste;

        try {
            ste = (PreparedStatement) con.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                nb = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionactadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (nb <= 5) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte Activites");
            alert.setHeaderText(null);
            alert.setContentText("Vous Avez " + nb + " Activites, Veuillez ajouter d'autres.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte Activites");
            alert.setHeaderText(null);
            alert.setContentText("Vous Avez " + nb + " Activites, pas d'insuffisance pour le moment");
            alert.showAndWait();
        }
    }

    @FXML
    private void Statact(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatAct.fxml"));
        Parent root;
        try {
            root = loader.load();
            btnstat2.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GUI.StatActController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



@FXML
        private void GestionU(ActionEvent event) {
    }

    @FXML
        private void gestVo(ActionEvent event) {
       try{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("MainFXML.fxml"));
		Parent root = loader.load();
		VoyOrgFXMLController  e = loader.getController();
               
              
		((Button) event.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println(ex);
		}  

    
    }

    @FXML
        private void gestAct(ActionEvent event) {

    }

    @FXML
        private void statistique(ActionEvent event) {
            try{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("StatR.fxml"));
		Parent root = loader.load();
		StatRController  e = loader.getController();
               e.setIdA(ida);
              
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
