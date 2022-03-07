/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class Client extends User{
String securityQ;
String answer;
   private int etat=1;
    public Client(int id, String nom, String prenom, String pwd, String email, int etat) {
     super(id,nom,prenom,pwd,email);
     
        this.etat = etat;
    }

    public Client(String securityQ, String answer, String nom, String prenom, String pwd, String email) {
        super( nom, prenom, pwd, email);
        this.securityQ = securityQ;
        this.answer = answer;
    }

    public Client(String nom, String prenom, String email) {
        super(nom, prenom, email);
    }

    public Client(String nom, String prenom, String pwd, String email, int etat) {
    super(nom, prenom, pwd, email);
        this.etat = etat;
    }

    public Client(String nom, String prenom, String pwd, String email) {
        super(nom, prenom, pwd, email);
    }

    public Client() {
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getSecurityQ() {
        return securityQ;
    }

    public String getAnswer() {
        return answer;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    

    @Override
    public String toString() {
        return "Client{" +super.toString()+", etat=" + etat +", securityQ=" + securityQ +", answer=" + answer +'}';
    }
   
}
