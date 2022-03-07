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
public class Hebergement {
     private int referance;
    private int offreur;
    private String paye;
    private String adress;
    private float prix;
    private String description;
    private String photo;
    private Date date_start;
    private Date date_end;
    private String contact;
    private int nbr_detoile;
    private int nbr_suite;
    private int nbr_parking;
    private String model_caravane;
    private int id_confeg;

    
    public Hebergement(int offreur, String paye, String adress, float prix, String description, String photo, Date date_start, Date date_end, String contact, int nbr_detoile, int nbr_suite, int nbr_parking, String model_caravane, int id_confeg) {
       
        this.offreur = offreur;
        this.paye = paye;
        this.adress = adress;
        this.prix = prix;
        this.description = description;
        this.photo = photo;
        this.date_start = date_start;
        this.date_end = date_end;
        this.contact = contact;
        this.nbr_detoile = nbr_detoile;
        this.nbr_suite = nbr_suite;
        this.nbr_parking = nbr_parking;
        this.model_caravane = model_caravane;
        this.id_confeg = id_confeg;
    }

    public Hebergement() {
        
    }

    @Override
    public String toString() {
        return "Hebergement{" + "referance=" + referance + ", offreur=" + offreur + ", paye=" + paye + ", adress=" + adress + ", prix=" + prix + ", description=" + description + ", photo=" + photo + ", date_start=" + date_start + ", date_end=" + date_end + ", contact=" + contact + ", nbr_detoile=" + nbr_detoile + ", nbr_suite=" + nbr_suite + ", nbr_parking=" + nbr_parking + ", model_caravane=" + model_caravane + ", id_confeg=" + id_confeg + '}';
    }

    public int getReferance() {
        return referance;
    }

    public void setReferance(int referance) {
        this.referance = referance;
    }

    public int getOffreur() {
        return offreur;
    }

    public void setOffreur(int offreur) {
        this.offreur = offreur;
    }

    public String getPaye() {
        return paye;
    }

    public void setPaye(String paye) {
        this.paye = paye;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getNbr_detoile() {
        return nbr_detoile;
    }

    public void setNbr_detoile(int nbr_detoile) {
        this.nbr_detoile = nbr_detoile;
    }

    public int getNbr_suite() {
        return nbr_suite;
    }

    public void setNbr_suite(int nbr_suite) {
        this.nbr_suite = nbr_suite;
    }

    public int getNbr_parking() {
        return nbr_parking;
    }

    public void setNbr_parking(int nbr_parking) {
        this.nbr_parking = nbr_parking;
    }

    public String getModel_caravane() {
        return model_caravane;
    }

    public void setModel_caravane(String model_caravane) {
        this.model_caravane = model_caravane;
    }

    public int getId_confeg() {
        return id_confeg;
    }

    public void setId_confeg(int id_confeg) {
        this.id_confeg = id_confeg;
    }

}
