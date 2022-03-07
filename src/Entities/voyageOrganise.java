/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Asus
 */
public class voyageOrganise {
    
    private int idVoy;
    private String villeDepart;
    private String villeDest;
    private String dateDepart;
    private String dateArrive;
    private int nbrPlace;
    private int idCat;
    private float prix;
    private String description;

    public voyageOrganise() {
    }

    public voyageOrganise(String villeDepart, String villeDest, String dateDepart, String dateArrive, int nbrPlace, int idCat, float prix, String description) {
       // this.idVoy = idVoy;
        this.villeDepart = villeDepart;
        this.villeDest = villeDest;
        this.dateDepart = dateDepart;
        this.dateArrive = dateArrive;
        this.nbrPlace = nbrPlace;
        this.idCat = idCat;
        this.prix = prix;
        this.description = description;
    }

    public voyageOrganise(int idVoy, String villeDepart, String villeDest, String dateDepart, String dateArrive, int nbrPlace, int idCat, float prix, String description) {
        this.idVoy = idVoy;
        this.villeDepart = villeDepart;
        this.villeDest = villeDest;
        this.dateDepart = dateDepart;
        this.dateArrive = dateArrive;
        this.nbrPlace = nbrPlace;
        this.idCat = idCat;
        this.prix = prix;
        this.description = description;
    }

    public int getIdVoy() {
        return idVoy;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public String getVilleDest() {
        return villeDest;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public int getIdCat() {
        return idCat;
    }

    public float getPrix() {
        return prix;
    }
    

    public String getDescription() {
        return description;
    }

    public void setIdVoy(int idVoy) {
        this.idVoy = idVoy;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public void setVilleDest(String villeDest) {
        this.villeDest = villeDest;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "voyageOrganise{" + "idVoy=" + idVoy + ", villeDepart=" + villeDepart + ", villeDest=" + villeDest + ", dateDepart=" + dateDepart + ", dateArrive=" + dateArrive + ", nbrPlace=" + nbrPlace + ", idCat=" + idCat + ", prix=" + prix + ", description=" + description + '}';
    }

    
}
