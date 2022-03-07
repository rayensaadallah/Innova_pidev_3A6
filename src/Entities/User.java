/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class User {
   protected int id;
   protected String nom;
   protected String prenom;
   protected String pwd;
   protected String email;

    public User() {
    }

    public User(int id, String nom, String prenom, String pwd, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.pwd = pwd;
        this.email = email;
    }

    public User(String nom, String prenom, String pwd, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.pwd = pwd;
        this.email = email;
    }

    public User(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPwd() {
        return pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", pwd=" + pwd + ", email=" + email;
    }
 
    
    
   
   
   
   
   
   
}
