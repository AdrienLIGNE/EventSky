package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

/**
 * Classe représentant la notion de personnel
 * Personnel peut être affecté à un ou plusieurs événements
 */
public class Personnel {
    private TypePersonnel type;
    private ArrayList<Evenement> affectations;

    public Personnel(TypePersonnel type){
        this.type = type;
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

}
