/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Rayen
 */
public class Category {
    private int id_categ;
    private  String nom_categ;

    public Category() {
    }

    public Category(String nom_categ) {
        this.nom_categ = nom_categ;
    }
    

    @Override
    public String toString() {
        return "Category{" + "id_categ=" + id_categ + ", nom_categ=" + nom_categ + '}';
    }

    public int getId_categ() {
        return id_categ;
    }

    public void setId_categ(int id_categ) {
        this.id_categ = id_categ;
    }

    public String getNom_categ() {
        return nom_categ;
    }

    public void setNom_categ(String nom_categ) {
        this.nom_categ = nom_categ;
    }

    public Category(int id_categ, String nom_categ) {
        this.id_categ = id_categ;
        this.nom_categ = nom_categ;
    }
    
    
}
