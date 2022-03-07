/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Vol;
import Utilis.Datasource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Asus
 */
public class VolService implements IService<Vol> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public VolService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void ajouter(Vol V){
//     List<Timestamp> listDateDepart = getListDateDepartByIdAvion( V.getId_avion(), V.getDate_depart());
//     List<Timestamp> listDateArrive= getListDateArriveByIdAvion( V.getId_avion(), V.getDate_arrivee());
//    boolean volIsPresent= checkVolIsBetweenDateDepartAndArriveIsPossible(listDateArrive, listDateDepart,V.getDate_arrivee(), V.getDate_depart()) ;
//     if(volIsPresent==true ) throw new Exception("Vol Existe deja");
//        
//     {String req = "INSERT INTO `vol` (`date_depart`,`date_arrivee`,`ville_depart`,`ville_arrivee`,`nbr_placedispo`,`id_avion`,`prix`) VALUE (?,?,?,?,?,?,?) ";
//         
//         try {
//            pste = conn.prepareStatement(req);
//            pste.setTimestamp(1, V.getDate_depart());
//            pste.setTimestamp(2, V.getDate_arrivee());
//            pste.setString(3, V.getVille_depart());
//            pste.setString(4, V.getVille_arrivee());
//            pste.setInt(5, V.getNbr_placedispo());
//            pste.setInt(6, V.getId_avion());
//            pste.setFloat(7, V.getPrix());
//            
//            
//            pste.executeUpdate();
//            System.out.println("vol créée");
//         
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
//            
//            }}
 
        }
    
   
//    
//    @Override
//    public void modifier(Vol V) {
//       String req = "UPDATE `vol` SET `date_depart`=?,`date_arrivee`=?,`ville_depart`=?,`ville_arrivee`=?,`nbr_placedispo`=?,`id_avion`=? ,`prix`=? WHERE  `vol`.`id_vol` = "+ V.getId_vol() + "";
//    
//        try {
//            pste = conn.prepareStatement(req);
//
//            pste.setTimestamp(1, V.getDate_depart());
//            pste.setTimestamp(2, V.getDate_arrivee());
            //pste.setString(3, V.getVille_depart());
//            pste.setString(4, V.getVille_arrivee());
//            pste.setInt(5, V.getNbr_placedispo());
//            pste.setInt(6, V.getId_avion());
//            pste.setFloat(7, V.getPrix());
//
//            pste.executeUpdate();
//            int rowsUpdated = pste.executeUpdate();
//            if (rowsUpdated > 0) {
//                System.out.println("La modification du vol a été éffectuée avec succès ");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      
//      
//    }
    
//    @Override
//    public void supprimer(Vol V) {
//        try {
//            String req = "DELETE FROM `vol` WHERE `vol`.`id_vol` =?";
//            pste = conn.prepareStatement(req);
//            pste.setInt(1,V.getId_vol());
//            pste.executeUpdate();
//            int rowsUpdated = pste.executeUpdate();
//            if (rowsUpdated > 0) {
//            System.out.println("Vol supprimé");
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    
     
     public boolean supprimer_v(Vol v) {
         boolean ok=false;
        try {
            
            PreparedStatement req = conn.prepareStatement("delete from vol where ville_depart = ? ");
            req.setString(1, v.getVille_depart());
            req.executeUpdate();
ok=true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    }

    
  
    public void modifier1(Vol V,int id_vol) {
      String req = "UPDATE `vol` SET `date_depart`=?,`date_arrivee`=?,`ville_depart`=?,`ville_arrivee`=?,`nbr_placedispo`=?,`id_avion`=? ,`prix`=? WHERE  `vol`.`id_vol` = "+ String.valueOf(id_vol) + "";
    
        try {
            pste = conn.prepareStatement(req);

            pste.setTimestamp(1, V.getDate_depart());
            pste.setTimestamp(2, V.getDate_arrivee());
            pste.setString(3, V.getVille_depart());
            pste.setString(4, V.getVille_arrivee());
            pste.setInt(5, V.getNbr_placedispo());
            pste.setInt(6, V.getId_avion());
            pste.setFloat(7, V.getPrix());

            pste.executeUpdate();
            int rowsUpdated = pste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification du vol :" + String.valueOf(id_vol) + " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      
    }

  public ResultSet getall() {
         
        try {
            PreparedStatement req = conn.prepareStatement("SELECT * FROM vol");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
      return null;
    }
  
   
    public void supprimer(int id_vol) {
        try {
            String req = "DELETE FROM `vol` WHERE `vol`.`id_vol` = " + String.valueOf(id_vol) + "";
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("Vol supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Vol> afficher() {
         List<Vol> Vols = new ArrayList<>();
        String req = "SELECT * FROM `vol` ";
        
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Vol v = new Vol();
                v.setId_vol(rs.getInt("id_vol"));
                v.setDate_depart(rs.getTimestamp("date_depart"));
                v.setDate_arrivee(rs.getTimestamp("date_arrivee"));
                v.setVille_depart(rs.getString("ville_depart"));
                v.setVille_arrivee(rs.getString("ville_arrivee"));
                v.setNbr_placedispo(rs.getInt("nbr_placedispo"));
                v.setId_avion(rs.getInt("id_avion"));
                v.setPrix(rs.getFloat("prix"));
                Vols.add(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Vols;
    }

    public List<Vol> findVolParDest(String ville_arrivee) {
         List<Vol> Vols = new ArrayList<>();
        String req = "SELECT * FROM `vol` where `ville_arrivee`= ? ";
        
        try {
            
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, ville_arrivee);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                Vol v = new Vol();
                v.setId_vol(rs.getInt("id_vol"));
                v.setDate_depart(rs.getTimestamp("date_depart"));
                v.setDate_arrivee(rs.getTimestamp("date_arrivee"));
                v.setVille_depart(rs.getString("ville_depart"));
                v.setVille_arrivee(rs.getString("ville_arrivee"));
                v.setNbr_placedispo(rs.getInt("nbr_placedispo"));
                v.setId_avion(rs.getInt("id_avion"));
                v.setPrix(rs.getFloat("prix"));
                Vols.add(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Vols;
    }
    
    public List<Vol> getALLVolsprix(float prix) throws SQLException {
        List<Vol> vols = new ArrayList<>();
        String req = "SELECT *   FROM vol  WHERE (prix=" + prix + ")";
        Statement stm = conn.createStatement();
    //  PreparedStatement pre = connexion.prepareStatement(req);

        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {

            Vol p = new Vol(rst.getInt("id_vol"), rst.getTimestamp("date_depart"), rst.getTimestamp("date_arrive"),rst.getString("ville_depart"), rst.getString("ville_arrivee"), rst.getInt("nbr_placedispo"),rst.getInt("id_avion"), rst.getFloat("prix"));
            vols.add(p);

        }
        return vols;
    }
    
    
    
    
    
    public Vol findVolParId(int id_vol) {

        String req = "SELECT * FROM `vol` WHERE `id_vol`= ? ";
        Vol v = new Vol();
        try {
            
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, id_vol);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                
                v.setId_vol(rs.getInt(1));
                v.setDate_depart(rs.getTimestamp(2));
                v.setDate_arrivee(rs.getTimestamp(3));
                v.setVille_depart(rs.getString(4));
                v.setVille_arrivee(rs.getString(5));
                v.setNbr_placedispo(rs.getInt(6));
                v.setId_avion(rs.getInt(7));
                v.setPrix(rs.getFloat(8));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return v;

    }
    
  
   
    
    public ResultSet tri_vol() {
         
       try {
            PreparedStatement req = conn.prepareStatement("SELECT * FROM vol ORDER BY prix");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    
}
    
   
    
    
    
   
     public int calculenbvol(int id_avion ) {
     String x = ""; 
        int a =0; 
  String req="select COUNT(id_vol) FROM vol where id_avion =? ";
           
           try {    
               PreparedStatement ps = conn.prepareStatement(req);
                ps.setInt(1, id_avion);
                 ResultSet rs = ps.executeQuery();
           rs.next(); 
           System.out.println("Le nombre de vol de l'avion d'id '" + String.valueOf(id_avion) + "': ");          
      
           a =  rs.getInt(1);
           } catch (SQLException ex) {
               Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return a; 
     }
     public int calculnbvolparjr(int id_avion,Timestamp date_depart ) {
     String x = ""; 
        int a =0; 
  String req="select COUNT(id_vol) FROM vol where id_avion =? and date_depart=? ";
           
           try {    
               PreparedStatement ps = conn.prepareStatement(req);
                ps.setInt(1, id_avion);
                ps.setTimestamp(2, date_depart);
                 ResultSet rs = ps.executeQuery();
           rs.next(); 
           System.out.println("Le nombre de vol de l'avion d'id '" + String.valueOf(id_avion) + "': en "+String.valueOf(date_depart)+ ":");          
      
           a =  rs.getInt(1);
           } catch (SQLException ex) {
               Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return a; 
     }
     
    

    public static int totaleJ = 0;
    public static int totaleF = 0;
    public static int totaleM = 0;
    public static int totaleA = 0;
    public static int totaleMai = 0;
    public static int totaleJuin = 0;
    public static int totaleJuillet = 0;
    public static int totaleAout = 0;
    public static int totaleSeptembre = 0;
    public static int totaleOctobre = 0;
    public static int totaleNovembre = 0;
    public static int totaleDécembre = 0;
     public void calculernbreVolparmois() {
 
        try {

            String req = "SELECT * FROM vol ";
           ste = conn.createStatement();
           ResultSet rs = ste.executeQuery(req);
            System.out.println(rs);
            while (rs.next()) {
                if (rs.getTimestamp("date_depart").getMonth() + 1 == 1) {
                    totaleJ += 1;
                    System.out.println("janvier " + totaleJ + " date " + rs.getTimestamp("date_depart"));

                } else if (rs.getTimestamp("date_depart").getMonth() + 1 == 2) {
                    totaleF += 1;
                    System.out.println("fevrier " + totaleF + " date " + rs.getTimestamp("date_depart"));

                } else if (rs.getTimestamp("date_depart").getMonth() + 1 == 3) {
                    totaleM += 1;
                    System.out.println("mars " + totaleM + " date " + rs.getTimestamp("date_depart"));

                } else if (rs.getTimestamp("date_depart").getMonth() + 1 == 4) {
                    totaleA += 1;
                    System.out.println("avril " + totaleA + " date " + rs.getTimestamp("date_depart"));

                } else if (rs.getTimestamp("date_depart").getMonth() + 1 == 5) {
                    totaleMai += 1;
                    System.out.println("mai " + totaleMai + " date " + rs.getTimestamp("date_depart"));

                } else if (rs.getTimestamp("date_depart").getMonth() + 1 == 6) {
                    totaleJuin += 1;
                    System.out.println("juin " + totaleJuin + " date " + rs.getTimestamp("date_depart"));

                } else if (rs.getTimestamp("date_depart").getMonth() + 1 == 7) {
                    totaleJuillet += 1;
                    System.out.println("juillet " + totaleJuillet + " date" + rs.getTimestamp("date_depart"));

                } else if (rs.getTimestamp("date_depart").getMonth() + 1 == 8) {
                    totaleAout += 1;
                    System.out.println("aout " + totaleAout + " date " + rs.getTimestamp("date_depart"));
                }
                
              else if (rs.getTimestamp("date_depart").getMonth() + 1 == 9) {
                    totaleSeptembre += 1;
                    System.out.println("septembre " + totaleSeptembre + " date " + rs.getTimestamp("date_depart"));
                }
                else if (rs.getTimestamp("date_depart").getMonth() + 1 == 10) {
                    totaleOctobre += 1;
                    System.out.println("octobre " + totaleOctobre + " date " + rs.getTimestamp("date_depart"));
                }
                else if (rs.getTimestamp("date_depart").getMonth() + 1 == 11) {
                    totaleNovembre += 1;
                    System.out.println("date_depart " + totaleNovembre + " date " + rs.getTimestamp("date_depart"));
                }
                else if (rs.getTimestamp("date_depart").getMonth() + 1 == 12) {
                    totaleDécembre += 1;
                    System.out.println("décembre " + totaleDécembre + " date " + rs.getTimestamp("date_depart"));
                }

            }

        } catch (SQLException ex) {
                 Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }

     }
     

            public boolean checkVolIsBetweenDateDepartAndArriveIsPossible( List<Timestamp> listDateArrive, List<Timestamp> listDateDepart,Timestamp dateArrive, Timestamp dateDepart){

       boolean checkIfStartTimeIsAfterDBStartTime= listDateDepart.stream().anyMatch(lds ->
               startTimeIsAfterDBStartTime(dateDepart,lds) );
   
 
              boolean checkIfStartTimeIsBeforeEndTIME= listDateArrive.stream().anyMatch(lds ->
          ( endTimeIsBeforeBDEndTime( dateArrive,lds)) );
              
       boolean checkIfStartTimeIsAfterDBEndTime= listDateArrive.stream().anyMatch(lds ->
               startTimeIsAfterDBStartTime(dateArrive,lds) );
       
                    boolean checkIfEndTimeIsBeforeDBEndTime= listDateDepart.stream().anyMatch(lds ->
          ( endTimeIsBeforeBDEndTime( dateDepart,lds)) );
                    
                    
                      boolean checkIfStartTimeIsBeforeDBtartTime= listDateDepart.stream().anyMatch(lds ->
          ( startTimeIsBeforeDBEndTime( dateDepart,lds)) );
                 
                         boolean checkIfStartTimeIsBeforeDBEndTime= listDateArrive.stream().anyMatch(lds ->
          ( startTimeIsBeforeDBEndTime( dateDepart,lds)) );
                         
                      boolean checkIfEndTimeIsAfterDBStartTime= listDateDepart.stream().anyMatch(lds ->
          ( endTimeIsAfterDBStartTime( dateArrive,lds)) );
                      
                            boolean checkIfEndTimeIsAfterDBEndTime= listDateDepart.stream().anyMatch(lds ->
          ( endTimeIsAfterDBEndTime( dateDepart,lds)) );
                            
                         boolean   checkIfStartTimeIsBeforeDBStartTime =listDateDepart.stream().anyMatch(lds ->
          ( startTimeIsBeforeDBStartTime( dateDepart,lds)) );
              
              if(checkIfStartTimeIsAfterDBEndTime && checkIfStartTimeIsBeforeEndTIME) return true;
              if(checkIfStartTimeIsAfterDBEndTime&&checkIfStartTimeIsBeforeDBtartTime && checkIfStartTimeIsAfterDBEndTime) return true;
               if  (checkIfStartTimeIsAfterDBStartTime&& checkIfStartTimeIsBeforeDBEndTime && checkIfEndTimeIsAfterDBEndTime) return true;
               if  (checkIfEndTimeIsBeforeDBEndTime&& checkIfEndTimeIsAfterDBStartTime && checkIfStartTimeIsBeforeDBStartTime) return true;
                 
                      
         return false;
     }
     
    public List<Timestamp> getListDateArriveByIdAvion(int idAvion, Timestamp dateArrive){
         
            List<Timestamp> Datel = new ArrayList<>();
             String req = "SELECT date_arrivee FROM vol where id_avion="+ idAvion +"";
            try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Datel.add(rs.getTimestamp(1));
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Datel;

    }
    
       public List<Timestamp> getListDateDepartByIdAvion(int idAvion, Timestamp dateDepart){
              List<Timestamp> Datel = new ArrayList<>();
              String req = "SELECT date_depart FROM vol where id_avion="+ idAvion +"";
         
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Datel.add(rs.getTimestamp(1));
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Datel;
    }
       
       public boolean startTimeIsAfterDBStartTime(Timestamp startDateTime, Timestamp startDateTimeBD) {

  return (startDateTime.equals(startDateTimeBD) || startDateTime.after(startDateTimeBD)) ;
}
              
       public boolean startTimeIsBeforeDBStartTime(Timestamp startDateTime, Timestamp startDateTimeBD) {

  return (startDateTime.equals(startDateTimeBD) || startDateTime.before(startDateTimeBD)) ;
}
                      public boolean startTimeIsBeforeDBEndTime(Timestamp startDateTime, Timestamp endDateTimeBD) {

  return (startDateTime.equals(endDateTimeBD) || startDateTime.before(endDateTimeBD)) ;
                      }
     public boolean endTimeIsBeforeDBStartTime(Timestamp endDateTime, Timestamp startDateTimeBD) {

  return (endDateTime.equals(startDateTimeBD) || endDateTime.before(startDateTimeBD)) ;
}
             public boolean endTimeIsBeforeBDEndTime(Timestamp endDateTime, Timestamp endDateTimeBD) {

  return( endDateTime.equals(endDateTimeBD) || endDateTime.before(endDateTimeBD));
}
             
                 
             public boolean endTimeIsAfterDBStartTime(Timestamp endDateTime, Timestamp startDateTimeBD) {

  return( endDateTime.equals(startDateTimeBD) || endDateTime.after(startDateTimeBD));
 
  
}
             
             public boolean endTimeIsAfterDBEndTime(Timestamp endDateTime, Timestamp endDateTimeBD) {

  return( endDateTime.equals(endDateTimeBD) || endDateTime.after(endDateTimeBD));
}

    @Override
    public void modifier(Vol entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Vol entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
