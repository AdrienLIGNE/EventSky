package fr.uga.iut2.genevent.modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.TreeSet;

public class Lieu extends Reservable {

    // Déclaration des attributs
    private int idLieu;
    private StringProperty nom;
    private int capacite;
    private double surface;
    private TreeSet<TypeEvenement> TypesAcceptes;


    // Constructeur

    public Lieu(int idLieu, String nom) {
        this(idLieu, nom, 0, 0);
    }

    public Lieu(int idLieu, String nom, int capacite, double surface) {
        this.idLieu = idLieu;
        this.nom = new SimpleStringProperty();
        setNom(nom);
        this.capacite = capacite;
        this.surface = surface;
    }


    // Méthodes
    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public StringProperty getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public TreeSet<TypeEvenement> getTypesAcceptes() {
        return TypesAcceptes;
    }
}
