package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.util.TextUtilitaire;

import java.util.ArrayList;

/**
 * Classe représentant la notion de personnel
 * Personnel peut être affecté à un ou plusieurs événements
 */
public class Personnel {
    private TypePersonnel type;
    private ArrayList<Evenement> affectations;
    private String nom;
    private String prenom;

    public Personnel(TypePersonnel type, String nom, String prenom){
        this.type = type;
        this.nom = TextUtilitaire.capitalize(nom);
        this.prenom = TextUtilitaire.capitalize(prenom);
        this.affectations = new ArrayList<>();
    }

    public void ajoutAffectation(Evenement evenement){
        this.affectations.add(evenement);
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


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public ArrayList<Evenement> getAffectations() {
        return affectations;
    }

    public TypePersonnel getType() {
        return type;
    }

    public String getNomComplet(){
        return prenom + " " + nom;
    }
}
