/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.sql.*;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Vol {
       private int id_vol ;
     public Timestamp date_depart;	
     public Timestamp date_arrivee;	
     private String ville_depart;
     private String ville_arrivee;
     private int nbr_placedispo;
     private int id_avion ;
     private float prix;

    public Vol() {
    }

    public Vol(int id_vol, Timestamp date_depart, Timestamp date_arrivee,String ville_depart, String ville_arrivee, int nbr_placedispo, int id_avion, float prix) {
        this.id_vol = id_vol;
        this.date_depart = date_depart;
        this.date_arrivee = date_arrivee;
        this.ville_depart=ville_depart;
        this.ville_arrivee = ville_arrivee;
        this.nbr_placedispo = nbr_placedispo;
        this.id_avion = id_avion;
        this.prix = prix;
    }

    public Vol(Timestamp date_depart, Timestamp date_arrivee,String ville_depart, String ville_arrivee, int nbr_placedispo, int id_avion, float prix) {
        this.date_depart = date_depart;
        this.date_arrivee = date_arrivee;
        this.ville_depart=ville_depart;
        this.ville_arrivee = ville_arrivee;
        this.nbr_placedispo = nbr_placedispo;
        this.id_avion = id_avion;
        this.prix = prix;
    }
    
   
    public int getId_vol() {
        return id_vol;
    }

    public Timestamp getDate_depart() {
        return date_depart;
    }

    public Timestamp getDate_arrivee() {
        return date_arrivee;
    }

    public String getVille_arrivee() {
        return ville_arrivee;
    }

    public int getNbr_placedispo() {
        return nbr_placedispo;
    }

    public int getId_avion() {
        return id_avion;
    }

    public float getPrix() {
        return prix;
    }

    public String getVille_depart() {
        return ville_depart;
    }

    public void setVille_depart(String ville_depart) {
        this.ville_depart = ville_depart;
    }
    
    

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public void setDate_depart(Timestamp date_depart) {
        this.date_depart = date_depart;
    }

    public void setDate_arrivee(Timestamp date_arrivee) {
        this.date_arrivee = date_arrivee;
    }


    public void setVille_arrivee(String ville_arrivee) {
        this.ville_arrivee = ville_arrivee;
    }

    public void setNbr_placedispo(int nbr_placedispo) {
        this.nbr_placedispo = nbr_placedispo;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Vol{" + "id_vol=" + id_vol + ", date_depart=" + date_depart + ", date_arrivee=" + date_arrivee + ", ville_depart=" + ville_depart + ", ville_arrivee=" + ville_arrivee + ", nbr_placedispo=" + nbr_placedispo + ", id_avion=" + id_avion + ", prix=" + prix + '}';
    }

   

   
    

    

    
     
    

    
}
