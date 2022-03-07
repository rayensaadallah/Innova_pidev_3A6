/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author asus
 */
public class Offreur extends User{

   private int numtl;

    public Offreur() {
    }

    public Offreur(int id, String nom, String prenom, String pwd, String email, int numtl) {
       super(id, nom, prenom, pwd, email);
        this.numtl = numtl;
    }

    public Offreur(String nom, String prenom, String pwd, String email, int numtl) {
        super(nom, prenom, pwd, email);
        this.numtl = numtl;
    }


    public int getNumtl() {
        return numtl;
    }


    public void setNumtl(int numtl) {
        this.numtl = numtl;
    }

    @Override
    public String toString() {
        return "Offreur{" +super.toString()+ ", numtl=" + numtl + '}';
    }
   
}
