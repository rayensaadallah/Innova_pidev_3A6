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
public class Paiement {
    private int id;
    private String modalite;
    private float montant;
    private int id_reservation;
    private Date date;

    public Paiement() {
    }

    public Paiement( String modalite, float montant, int id_reservation,Date date ) {
        
        this.modalite = modalite;
        this.montant = montant;
        this.id_reservation = id_reservation;
        this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModalite() {
        return modalite;
    }

    public void setModalite(String modalite) {
        this.modalite = modalite;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    @Override
    public String toString() {
        return "Paiement{" + "id=" + id + ", modalite=" + modalite + ", montant=" + montant + ", id_reservation=" + id_reservation + '}';
    }
    
}
