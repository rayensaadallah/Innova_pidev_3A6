/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.voyageOrganise;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Amal Chibani
 */
public interface serv<T> {
    
   void ajouter(T entity);
   /* void ajouter(VoyageOrganise vo);*/
   List<T> afficher();
   void delete(int idVoyageOrganise);
   void update(T entity);
//   String FindVoyById(T entity);
//  void FindByDestination(String dest);
//    Boolean FindIdVoyById(int idvoy);
}

