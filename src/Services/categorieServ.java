/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.categorie;
import Entities.voyageOrganise;
import Utilis.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Amal Chibani
 */
public class categorieServ implements serv<categorie>{
     private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    
    public categorieServ (){
    conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void ajouter(categorie cat) {
 String req = "INSERT INTO `categorievoy` (`idcat`,`nomcat`) VALUE ('" + cat.getIdcat()+ "','" + cat.getNomcat() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println(" categorie cree");
        } catch (SQLException ex) {
            Logger.getLogger(voyOrgServ.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<categorie> afficher() {
List<categorie> categ = new ArrayList<>();

        String req = "SELECT * FROM `categorievoy`";
        
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
              categorie cat =new categorie();
                cat.setIdcat(rs.getInt("idcat"));
                cat.setNomcat(rs.getString(2));
                categ.add(cat);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(voyOrgServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categ;    }

   
    @Override
    public void delete(int idcat) {
 try
        {   String req = "DELETE FROM `categorievoy` WHERE `idcat` = "+idcat;
                PreparedStatement ste = conn.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("voyage supprimé");
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }    
    }

    @Override
    public void update(categorie cat) {
 String sql = "UPDATE categorievoy SET `nomcat`=? WHERE idcat=" + cat.getIdcat();
        PreparedStatement ste;
        try {
            ste = conn.prepareStatement(sql);

        ste.setString(1, cat.getNomcat());
              
            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification du categorie a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(voyageOrganise.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
//    @Override
//    public String FindVoyById(categorie entity) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Boolean FindIdVoyById(int idvoy) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
    

