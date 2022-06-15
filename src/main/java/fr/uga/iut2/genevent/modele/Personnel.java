package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.util.TextUtilitaire;

import java.util.ArrayList;

/**
 * Classe représentant la notion de personnel
 * Personnel peut être affecté à un ou plusieurs événements
 */
public class Personnel extends Reservable {
    private TypePersonnel type;

    private String nom;
    private String prenom;
    private String mail;
    private String numero;

    public Personnel(String nom, String prenom, TypePersonnel type){
        setNom(nom);
        //setPrenom(prenom);
        this.prenom = "";
        this.type = type;
    }

    public void setNom(String nom) {
        this.nom = nom.toUpperCase();
    }

    public void setPrenom(String prenom) {
        this.prenom = TextUtilitaire.capitalize(prenom);
    }

    public String getNom() {
        return nom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setType(TypePersonnel type) {
        this.type = type;
    }

    public String getPrenom() {
        return prenom;
    }

    public TypePersonnel getTypeEmploi() {
        return type;
    }

    public double getSalaireHoraire(){
        return this.type.getSalaire();
    }

    /**
     * Méthode permettant de savoir combien va coûter le personnel pendant une durée donnée
     * @param nbHeure temps de sollicitation du personnel, en heure
     * @return salaire horaire multiplié par le nombre d'heure
     */
    public double getPrixDuree(double nbHeure){
        return this.type.getSalaire()*nbHeure;
    }

}
