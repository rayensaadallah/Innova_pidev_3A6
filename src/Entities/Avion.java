/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getaway.entities;

import java.util.Objects;

/**
 *
 * @author Malek
 */
public class Avion {
    private int id_avion;
    private int nbr_place;
    private int id_agence;
    private String nom_avion;

    public Avion() {
    }

    public Avion(int id_avion,String nom_avion) {
        this.nom_avion = nom_avion;
        this.id_avion=id_avion;
    }
    
    

    public Avion(int id_avion, int nbr_place, int id_agence, String nom_agence) {
        this.id_avion = id_avion;
        this.nbr_place = nbr_place;
        this.id_agence = id_agence;
        this.nom_avion = nom_agence;
    }

    public Avion(int nbr_place, int id_agence, String nom_agence) {
        this.nbr_place = nbr_place;
        this.id_agence = id_agence;
        this.nom_avion = nom_agence;
    }

   

    public int getId_avion() {
        return id_avion;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public int getId_agence() {
        return id_agence;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public String getNom_avion() {
        return nom_avion;
    }

    public void setNom_avion(String nom_avion) {
        this.nom_avion = nom_avion;
    }

    @Override
    public String toString() {
        return "Avion{" + "id_avion=" + id_avion + ", nbr_place=" + nbr_place + ", id_agence=" + id_agence + ", nom_avion=" + nom_avion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nom_avion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Avion other = (Avion) obj;
        if (!Objects.equals(this.nom_avion, other.nom_avion)) {
            return false;
        }
        return true;
    }

   
    
    
    
    
}
