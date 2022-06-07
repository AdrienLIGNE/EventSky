package fr.uga.iut2.genevent.modele;

import java.util.TreeSet;

public class Lieu {

    // Déclaration des attributs
    private int idLieu;
    private String nom;
    private int capacite;
    private double surface;
    private TreeSet<TypeEvenement> TypesAcceptes;


    // Constructeur

    public Lieu(int idLieu, String nom) {
        this.idLieu = idLieu;
        this.nom = nom;
    }

    public Lieu(int idLieu, String nom, int capacite, double surface) {
        this.idLieu = idLieu;
        this.nom = nom;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
