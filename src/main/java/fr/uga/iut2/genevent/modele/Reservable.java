package fr.uga.iut2.genevent.modele;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Retourne la quantité totale disponible
     * @return quantité
     */
    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    /**
     * Réserve le Reservable pour un évenement avec une quantite spécifié
     * @param e Evenement sur lequel réserver l'élément réservable
     * @param quantite nombre à réserver pour l'événement
     */
    public void affecteEvenement(Evenement e, int quantite) {

        // TODO: Vérifier que pour chaque jour de l'événement la quantité est dispo

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
     * Calcule la quantité réservée pour un jour donné.
     * @param date date à tester
     * @return le nombre d'élément réservé
     */
    private int calculeQuantiteReserve(LocalDate date) {
        int nbReserve = 0;

        // On regarde pour chaque événement, s'il se produit le jour donné
        for(TupleQuantiteEvenement affect : affectation_evenements) {
            if(affect.getEvenement().isConfirmed() & affect.getEvenement().sePasseCeJour(date)) {
                nbReserve += affect.getQuantite();
            }
        }

        return nbReserve;
    }

    /**
     * Vérifie si c'est disponible dans la quantité demandé le jour spécifié
     * @param date date à laquelle vérifier la disponibilité
     * @param quantite quantité souhaitée
     * @return Vrai si c'est disponible dans la quantité demandé
     */
    public boolean estDisponible(LocalDate date, int quantite) {
        int nbReserve = calculeQuantiteReserve(date);

        return ((nbReserve + quantite) <= quantiteDisponible);
    }


    /**
     * Vérifie si l'élément est disponible le jour donné
     * @param date jour auquel vérifié
     * @return vrai si c'est disponible
     */
    public boolean estDisponible(LocalDate date) {
        return estDisponible(date, 1);
    }


    /**
     * Vérifie si un élément est disponible dans une plage de dates donnés avec la quantité demandée
     * @param dateDebut date de début
     * @param dateFin date de fin
     * @param quantite quantité requise
     * @return vrai si c'est disponible sur l'intégralité de la plage
     */
    public boolean estDisponible(LocalDate dateDebut, LocalDate dateFin, int quantite) {
        List<LocalDate> dates = dateDebut.datesUntil(dateFin).collect(Collectors.toList());

        // On regarde pour chaque jour si c'est disponible
        int i = 0;
        while(i < dates.size() && estDisponible(dates.get(i), quantite)) {
            i++;
        }

        return i == dates.size();
    }

    /**
     * Vérifie si un élément est disponible dans une plage de dates donnés
     * @param dateDebut date de début
     * @param dateFin date de fin
     * @return vrai si c'est disponible sur l'intégralité de la plage
     */
    public boolean estDisponible(LocalDate dateDebut, LocalDate dateFin) {
        return estDisponible(dateDebut, dateFin, 1);
    }

    /**
     * Retourne la quantité disponible pour un jour donné
     * @param date date à tester
     * @return nombre disponible
     */
    public int getQuantiteDisponible(LocalDate date) {
        return quantiteDisponible - calculeQuantiteReserve(date);
    }


}
