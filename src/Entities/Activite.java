/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GetAway.entities;

/**
 *
 * @author TheBoss'07
 */
public class Activite {
   private int RefAct;
   private String Nom;
   private String Descrip;
   private String Duree;
   private int NbrPlace;
   private String Date;
   private String Type;
   private String Location;
   private float Prix;
   
   public Activite() {
    }
    
   public Activite(int RefAct) {
       this.RefAct = RefAct;
    }
    public Activite(int RefAct, String Nom, String Descrip, String Duree, int NbrPlace, String Date, String Type, String Location, float Prix) {
        this.RefAct = RefAct;
        this.Nom = Nom;
        this.Descrip = Descrip;
        this.Duree = Duree;
        this.NbrPlace = NbrPlace;
        this.Date = Date;
        this.Type = Type;
        this.Location = Location;
        this.Prix = Prix;
    }

    public Activite(String Nom, String Descrip, String Duree, int NbrPlace, String Date, String Type, String Location, float Prix) {
        this.Nom = Nom;
        this.Descrip = Descrip;
        this.Duree = Duree;
        this.NbrPlace = NbrPlace;
        this.Date = Date;
        this.Type = Type;
        this.Location = Location;
        this.Prix = Prix;
    }

    public int getRefAct() {
        return RefAct;
    }

    public void setRefAct(int RefAct) {
        this.RefAct = RefAct;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getDescrip() {
        return Descrip;
    }
    
    public void setDescrip(String Descrip) {
        this.Descrip = Descrip;
    }

    public String getDuree() {
        return Duree;
    }
    
    public void setDuree(String Duree) {
        this.Duree = Duree;
    }
    
    public int getNbrPlace() {
        return NbrPlace;
    }

    public void setNbrPlace(int NbrPlace) {
        this.NbrPlace = NbrPlace;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    @Override
    public String toString() {
        return "RefAct= " + RefAct + ", Nom= " + Nom + ", Descrip= " + Descrip + ", Duree= " + Duree + ", NbrPlace= " + NbrPlace + ", Date= " + Date + ", Type= " + Type + ", Location= " + Location + ", Prix= " + Prix + "}\n";
    }

    
   
   
}

