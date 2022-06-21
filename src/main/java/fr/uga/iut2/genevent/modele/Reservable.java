package fr.uga.iut2.genevent.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe permettant de rendre un élément réservable
 */
public abstract class Reservable  {

    private IntegerProperty quantiteDisponible;
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
        quantiteDisponible = new SimpleIntegerProperty();
        setQuantiteDisponible(quantite);
    }

    public void setQuantiteDisponible(int quantite) {
        this.quantiteDisponible.set(quantite);
    }

    /**
     * Retourne la quantité totale disponible en tant que propriété
     * @return quantité
     */
    public IntegerProperty getQuantiteDisponibleProperty() {
        return quantiteDisponible;
    }

    /**
     * Retourne la quantité disponible en général (le maximum)
     * @return
     */
    public int getQuantiteDisponible() {
        return quantiteDisponible.get();
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

        return ((nbReserve + quantite) <= getQuantiteDisponible());
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
        // On ajoute 1 à la date de fin car sinon elle n'est pas comprise dans l'interval
        List<LocalDate> dates = dateDebut.datesUntil(dateFin.plusDays(1)).collect(Collectors.toList());

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
        return getQuantiteDisponible() - calculeQuantiteReserve(date);
    }

    /**
     * Retourne la quantité disponible pour un interval donné
     * @param dateDebut date de début
     * @param dateFin date de fin
     * @return quantité disponible
     */
    public int getQuantiteDisponible(LocalDate dateDebut, LocalDate dateFin) {
        // On ajoute 1 à la date de fin car sinon elle n'est pas comprise dans l'interval
        List<LocalDate> dates = dateDebut.datesUntil(dateFin.plusDays(1)).collect(Collectors.toList());

        int minimum = getQuantiteDisponible();

        // On regarde pour chaque jour la quantité disponible et on garde le minimum
        for(LocalDate d : dates) {
            int dispo = getQuantiteDisponible(d);

            if(dispo < minimum){
                minimum = dispo;
            }
        }

        return minimum;
    }

    /**
     * Retourne la quantité disponible maximum pour un ensemble de dates possibles
     * @param dates dates possibles
     * @return quantité disponible
     */
    public int getQuantiteDisponible(ObservableList<DatePossible> dates) {
        int maximum = 0;

        for(DatePossible date : dates) {
            int qDispo = getQuantiteDisponible(date.getDateDebut(), date.getDateFin());

            if(qDispo > maximum)
                maximum = qDispo;
        }

        return maximum;
    }

    /**
     * Récupère la quantité affecté à un événement
     * @param e Evenement pour lequel récupérer la quantité
     */
    public int getQuantiteAffecte(Evenement e) {

        int i = 0;

        // On cherche l'évenement en question
        while(i < affectation_evenements.size() && !affectation_evenements.get(i).getEvenement().equals(e)) {
            i++;
        }
        if(i < affectation_evenements.size()) {
            return affectation_evenements.get(i).getQuantite();
        }

        return 0;
    }


}
