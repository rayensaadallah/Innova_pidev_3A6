/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Amal Chibani
 */
public class categorie {
    private int idcat;
    private String nomcat;

    public categorie() {
    }


    public categorie(int idcat, String nomcat) {
        this.idcat = idcat;
        this.nomcat = nomcat;
    }

    public categorie(String nomcat) {
        this.nomcat = nomcat;
    }
    

    public int getIdcat() {
        return idcat;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    @Override
    public String toString() {
        return "categorie{" + "idcat=" + idcat + ", nomcat=" + nomcat + '}';
    }
    
    
}
