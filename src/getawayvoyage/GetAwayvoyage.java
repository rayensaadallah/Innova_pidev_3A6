/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getawayvoyage;


import Utilis.Datasource;
import Entities.*;
import java.text.SimpleDateFormat;
import Services.*;
import java.text.ParseException;
import java.time.*;

import java.sql.*;

/**
 *
 * @author Asus
 */
public class GetAwayvoyage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    String SDateR="2022-03-31";  
      
        String SDateD="2022-02-01";
         String SDateF="2022-02-16";
        
        
        
        
    Date DateR=Date.valueOf(SDateR);
        
    Date DateD=Date.valueOf(SDateD);
    Date DateF=Date.valueOf(SDateF);
  
//  Reservation r5= new Reservation(DateR, 5, DateD, DateF, 0, 0, 0,1,"enA", 1, "hebergment");
//           
//  Reservation r4= new Reservation(DateR,12, DateD, DateF, 0, 1,0 ,0,"enA", 1, "activite");  
  HebergementService hs = new HebergementService();
        System.out.println(hs.afficher());
             
   
    ReservationService Rs= new ReservationService();
        System.out.println("aaaaa"+Rs.NomP(1));
//        System.out.println(Rs.verifierNbplaceVol(3,11));
//             String hDateD="2022-02-11";
//      String hDateF="2022-02-12";
////        
//           // Rs.ajouterHeb(r5);
//       Rs.ajouterAct(r4);
//       VolService vs = new VolService();
//       Rs.modifiernbplacevol(1,2);
         
       
//    Date h1DateD=Date.valueOf(hDateD);
//    Date h1DateF=Date.valueOf(hDateF);
//        
//        System.out.println(Rs.testerdisponibliteH(h1DateD, h1DateF,1));
//        
   //Rs.ajouterHeb(r5);
//      
//        Paiement p = new Paiement("cheque",0,3,DateD);
    PaiementService ps= new  PaiementService();
//    
//    
////        ps.ajouter(p);
////        Paiement p2= new Paiement();
////  
//////        ps.supprimer(p2);
        System.out.println(ps.afficher());
////   p.setMontant(ps.calculermontantActivite(r4));
////        ps.modifier(p,3);
////

////Rs.modifiernbplacevol(1,2);
////Rs.modifiernbplacevoyage(1,10);
////
////     
////        
////        //Rs.ajouterVol(r7);
////        r7.setNbr_place(12);
////        Rs.modifiervol(r7,14);
////        r7.setId(15);
////        Rs.supprimer(r7);
//
////        System.out.println(Rs.listeR(1));
////        System.out.println(ps.MontantT(Rs.listeR(1)));
////        
////        Rs.modifiernbplacevol(1,2);
//
     
    }
     
}
