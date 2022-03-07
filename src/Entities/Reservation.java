/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Reservation {
    private int id;
    private Date date_reservation;
    private int nbr_place;
    private Date date_debut;
    private Date date_fin;
    private int id_client;
     private int id_voyage=0;
     private int id_active=0;
      private int id_vol=0;
      private int id_hebergement=0;
     private String etat;
     private String type;
     public Reservation()
     {
         
     }

    public Reservation(Date date_reservation, int nbr_place, Date date_debut, Date date_fin, int id_voyage, int id_active, int id_vol, int id_hebergement, String etat,int id_client, String type ) {
        this.date_reservation = date_reservation;
        this.nbr_place = nbr_place;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_client = id_client;
        this.id_voyage = id_voyage;
        this.id_active = id_active;
        this.id_vol = id_vol;
        this.id_hebergement = id_hebergement;
        this.etat = etat;
        this.type=type;
    }
  public Reservation(Date dr , int nbr_p,int id_voyage, int id_client, String etat , String type)
  {
      
      this.date_reservation=dr;
      this.nbr_place=nbr_p;
      this.id_voyage=id_voyage;
      this.etat=etat;
      this.type=type;
      this.id_client=id_client;
      
      
  }
  
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   

    

 
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_voyage() {
        return id_voyage;
    }

    public void setId_voyage(int id_voyage) {
        this.id_voyage = id_voyage;
    }

    public int getId_active() {
        return id_active;
    }

    public void setId_active(int id_active) {
        this.id_active = id_active;
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public int getId_hebergement() {
        return id_hebergement;
    }

    public void setId_hebergement(int id_hebergement) {
        this.id_hebergement = id_hebergement;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date_reservation=" + date_reservation + ", nbr_place=" + nbr_place + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", id_client=" + id_client + ", id_voyage=" + id_voyage + ", id_active=" + id_active + ", id_vol=" + id_vol + ", id_hebergement=" + id_hebergement + ", etat=" + etat + '}';
    }
       
      
}
