/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class User {

    private int id;
    private String nom;
    private String prenom;
    private String pwd;
    private String adresse;
    private String securityQ;
    private String answer;
    private String numtel;
    private String email;
    private String nomAgence;
    private String role;
    private int etat = 1;
    private float solde;
    

    public User() {
    }

    public User(String nom, String prenom, String pwd, String adresse, String email, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.pwd = pwd;
        this.adresse = adresse;
        this.email = email;
        this.role = role;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public User(String nom, String prenom, String pwd, String securityQ, String answer, String numtel, String email, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.pwd = pwd;
        this.securityQ = securityQ;
        this.answer = answer;
        this.numtel = numtel;
        this.email = email;
        this.role = role;
    }
    
    
    

    public User(int id, String nom, String prenom, String pwd, String adresse, String securityQ, String answer, String numtel, String email, String nomAgence, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.pwd = pwd;
        this.adresse = adresse;
        this.securityQ = securityQ;
        this.answer = answer;
        this.numtel = numtel;
        this.email = email;
        this.nomAgence = nomAgence;
        this.role = role;
    }

    public User(String securityQ, String answer,String numtel, String nom, String prenom, String pwd,  String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.pwd = pwd;
        this.securityQ = securityQ;
        this.answer = answer;
        this.numtel = numtel;
        this.email = email;
    }

   

    public User(String nom, String prenom, String pwd, String adresse, String securityQ, String answer, String numtel, String email, String nomAgence, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.pwd = pwd;
        this.adresse = adresse;
        this.securityQ = securityQ;
        this.answer = answer;
        this.numtel = numtel;
        this.email = email;
        this.nomAgence = nomAgence;
        this.role = role;
    }

    public User(String nom, String prenom, String pwd, String adresse, String securityQ, String answer, String numtel, String email, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.pwd = pwd;
        this.adresse = adresse;
        this.securityQ = securityQ;
        this.answer = answer;
        this.numtel = numtel;
        this.email = email;
        this.role = role;
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

    public String getAdresse() {
        return adresse;
    }

    public String getSecurityQ() {
        return securityQ;
    }

    public String getAnswer() {
        return answer;
    }

    public String getNumtel() {
        return numtel;
    }

    public String getEmail() {
        return email;
    }

    public String getNomAgence() {
        return nomAgence;
    }

    public String getRole() {
        return role;
    }

    public int getEtat() {
        return etat;
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", pwd=" + pwd + ", adresse=" + adresse + ", securityQ=" + securityQ + ", answer=" + answer + ", numtel=" + numtel + ", email=" + email + ", nomAgence=" + nomAgence + ", role=" + role + ", etat=" + etat + '}';
    }
    
    

}

