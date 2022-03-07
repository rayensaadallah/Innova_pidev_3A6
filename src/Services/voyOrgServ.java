/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.voyageOrganise;
import Utilis.Datasource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
;
/**
 *
 * @author Asus
 */
public class voyOrgServ implements IService<voyageOrganise> {
       private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    
    public voyOrgServ (){
    conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void modifier(voyageOrganise entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(voyageOrganise entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
     @Override
    public void ajouter(voyageOrganise vo) {
        String req = "INSERT INTO `voyageorganise` (`idVoy`,`villeDepart`,`villeDest`,`dateDepart`,`dateArrive`,`nbrPlace`,`idCat`,`prix`,`description`) VALUE ('" + vo.getIdVoy()+ "','" + vo.getVilleDepart() + "','" + vo.getVilleDest() + "','" + vo.getDateDepart() + "','" + vo.getDateArrive() + "','" + vo.getNbrPlace() + "','" + vo.getIdCat() + "','" +vo.getPrix() + "','" + vo.getDescription() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println(" voyage org cree");
        } catch (SQLException ex) {
            Logger.getLogger(voyOrgServ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
     @Override
    public List<voyageOrganise> afficher() {
        List<voyageOrganise> voyageorganise = new ArrayList<>();
        String req = "SELECT * FROM `voyageorganise`";
        
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
               voyageOrganise vo =new voyageOrganise();
                vo.setIdVoy(rs.getInt("IdVoy"));
                vo.setVilleDepart(rs.getString(2));
                vo.setVilleDest(rs.getString(3));
                vo.setDateDepart(rs.getString(4));
                vo.setDateArrive(rs.getString(5));
                vo.setNbrPlace(rs.getInt("nbrPlace"));
                vo.setIdCat(rs.getInt("IdCat"));
                vo.setPrix(rs.getFloat("prix"));
                vo.setDescription(rs.getString(9));
                voyageorganise.add(vo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(voyOrgServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return voyageorganise;
    }
    
    

   
    public void delete(int idvoy) {
       try
        {   String req = "DELETE FROM `voyageorganise` WHERE `idVoy` = "+idvoy;
                PreparedStatement ste = conn.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("voyage supprimé");
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }    
    }

    public void update(voyageOrganise vo) {
       String sql = "UPDATE voyageorganise SET `villeDepart`=?,`villeDest`=?,`dateDepart`=?,`dateArrive`=?,`nbrPlace`=?,`idCat`=?,`prix`=?,`description`=? WHERE idVoy=" + vo.getIdVoy();
        PreparedStatement ste;
        try {
            ste = conn.prepareStatement(sql);

        ste.setString(1, vo.getVilleDepart());

            ste.setString(2, vo.getVilleDest());
            
            ste.setString(3, vo.getDateDepart());
            ste.setString(4, vo.getDateArrive());
            ste.setInt(5, vo.getNbrPlace());
            ste.setInt(6, vo.getIdCat());
            ste.setFloat(7, vo.getPrix());
            ste.setString(8, vo.getDescription());
                

            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification du voy : " + vo.getIdVoy()+ " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(voyageOrganise.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


   
    public List<voyageOrganise> FindIdVoyById(int idvoy) {
        
List<voyageOrganise> voyageorganise = new ArrayList<>();
        String req = "SELECT * FROM `voyageorganise` where idVoy='" + idvoy + "'";
        
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
               voyageOrganise vo =new voyageOrganise();
                vo.setIdVoy(rs.getInt("IdVoy"));
                vo.setVilleDepart(rs.getString(2));
                vo.setVilleDest(rs.getString(3));
                vo.setDateDepart(rs.getString(4));
                vo.setDateArrive(rs.getString(5));
                vo.setNbrPlace(rs.getInt("nbrPlace"));
                vo.setIdCat(rs.getInt("IdCat"));
                vo.setPrix(rs.getFloat("prix"));
                vo.setDescription(rs.getString(9));
                voyageorganise.add(vo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(voyOrgServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return voyageorganise;
        
    }
    
    
    public List<voyageOrganise> FindVille(String dest) {
        
List<voyageOrganise> voyageorganise = new ArrayList<>();
        String req = "SELECT * FROM `voyageorganise` where villeDest='" + dest + "'";
        
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
               voyageOrganise vo =new voyageOrganise();
                vo.setIdVoy(rs.getInt("IdVoy"));
                vo.setVilleDepart(rs.getString(2));
                vo.setVilleDest(rs.getString(3));
                vo.setDateDepart(rs.getString(4));
                vo.setDateArrive(rs.getString(5));
                vo.setNbrPlace(rs.getInt("nbrPlace"));
                vo.setIdCat(rs.getInt("IdCat"));
                vo.setPrix(rs.getFloat("prix"));
                vo.setDescription(rs.getString(9));
                voyageorganise.add(vo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(voyOrgServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return voyageorganise;

    }
    
    public List<voyageOrganise> TrierParPrix()  {
        List<voyageOrganise> list = new ArrayList<>();
        try{
        ste = conn.createStatement();
        
        ResultSet res = ste.executeQuery("select distinct * FROM `voyageorganise` ORDER BY prix ASC");
        
        voyageOrganise com = null;
        while (res.next()) {
            com = new voyageOrganise(res.getInt(1),res.getString(2), res.getString(3),res.getString(4),res.getString(5),res.getInt(6),res.getInt(7),res.getFloat(8),res.getString(9));
            list.add(com);
            //System.out.println(list);
        }
        }catch (SQLException ex) {
            Logger.getLogger(voyOrgServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public int stat() {
        int nb = 0;
try{
        String req1 = "SELECT c.nomcat , count(v.idVoy) from `voyageorganise` v join `categorievoy` c WHERE c.idcat=v.idCat GROUP by v.idCat;";
        ResultSet result = ste.executeQuery(req1);
        if (result.first()) {
            nb = result.getInt(1);
        }

        System.out.println(nb);
}catch (SQLException ex) {
            Logger.getLogger(voyOrgServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nb;

    }
}
