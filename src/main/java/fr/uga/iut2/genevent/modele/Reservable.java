package fr.uga.iut2.genevent.modele;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe permettant de rendre un élément réservable
 */
public abstract class Reservable {

    private int quantiteDisponible;
    private ArrayList<TupleQuantiteEvenement> affectation_evenements;

    /**
     * Classe permettant de lier un évènement avec une quantité
     * Par exemple si la quantite est de 4 pour un évènement, cela veut dire qu'il y a 4 éléments réservé pour cet événement
     */
    private static class TupleQuantiteEvenement {
        private int quantite;
        private Evenement evenement;

        public TupleQuantiteEvenement(Evenement evenement, int quantite) {
            this.evenement = evenement;
            this.quantite = quantite;
        }

        public TupleQuantiteEvenement(Evenement evenement) {
            this(evenement, 1);
        }

        public Evenement getEvenement() {
            return evenement;
        }

        public int getQuantite() {
            return quantite;
        }
    }


    public Reservable() {
        this(1);
    }
    public Reservable(int quantite) {
        affectation_evenements = new ArrayList<>();
        setQuantiteDisponible(quantite);
    }

    public void setQuantiteDisponible(int quantite) {
        this.quantiteDisponible = quantite;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    /**
     * Réserve le Reservable pour un évenement avec une quantite spécifié
     * @param e Evenement sur lequel réserver l'élément réservable
     * @param quantite nombre à réserver pour l'événement
     */
    public void affecteEvenement(Evenement e, int quantite) {
        affectation_evenements.add(new TupleQuantiteEvenement(e, quantite));
    }

    /**
     * Réserve le réservable pour un événement
     * @param e Evenement sur lequel réserver l'élément réservable
     */
    public void affecteEvenement(Evenement e) {
        affecteEvenement(e, 1);
    }


    /**
     * Vérifie si c'est disponible dans la quantité demandé le jour spécifié
     * @param date date à laquelle vérifier la disponibilité
     * @param quantite quantité souhaitée
     * @return Vrai si c'est disponible dans la quantité demandé
     */
    public boolean estDisponible(LocalDate date, int quantite) {
        int nbReserve = 0;

        // On regarde pour chaque événement, s'il se produit le jour donné
        for(TupleQuantiteEvenement affect : affectation_evenements) {
            if(affect.getEvenement().isConfirmed() & affect.getEvenement().sePasseCeJour(date)) {
                nbReserve += affect.getQuantite();
            }
        }
        return (nbReserve < quantiteDisponible);
    }

    public boolean estDisponible(LocalDate date) {
        return estDisponible(date, 1);
    }


}
