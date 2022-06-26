package fr.uga.iut2.genevent.modele;

import javafx.beans.property.*;

import java.io.Serializable;
import java.util.TreeSet;

public class Lieu extends Reservable  {

    // Déclaration des attributs
    private StringProperty nom;

    private StringProperty adresse;
    private StringProperty complement_adresse;
    private StringProperty code_postal;
    private StringProperty ville;

    private IntegerProperty capacite;
    private ObjectProperty<TypeLieu> type;

    public Lieu(String nom, int capacite, TypeLieu type) {

        this.nom = new SimpleStringProperty();
        this.capacite = new SimpleIntegerProperty();

        this.adresse = new SimpleStringProperty();
        this.complement_adresse = new SimpleStringProperty();
        this.code_postal = new SimpleStringProperty();
        this.ville = new SimpleStringProperty();

        this.type = new SimpleObjectProperty<>();

        setNom(nom);
        setCapacite(capacite);
        setType(type);
    }



    public StringProperty getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public void setComplementAdresse(String complement_adresse) {
        this.complement_adresse.set(complement_adresse);
    }

    public void setVille(String ville) {
        this.ville.set(ville);
    }

    public void setCodePostal(String code_postal) {
        this.code_postal.set(code_postal);
    }

    public void setType(TypeLieu type) {
        this.type.set(type);
    }

    public ObjectProperty<TypeLieu> getType() {
        return type;
    }

    public StringProperty getAdresse() {
        return adresse;
    }

    public StringProperty getComplementAdresse() {
        return complement_adresse;
    }

    public StringProperty getCodePostal() {
        return code_postal;
    }

    public StringProperty getVille() {
        return ville;
    }

    public IntegerProperty getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite.set(capacite);
    }

}
