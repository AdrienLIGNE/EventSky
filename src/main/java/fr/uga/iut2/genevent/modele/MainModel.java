package fr.uga.iut2.genevent.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * Classe de base pour accéder au modèle de données
 */
public class MainModel {

    private ObservableList<Evenement> evenementsNonConfirme;
    private ObservableList<Evenement> evenementsConfirme;

    private ObservableList<Lieu> lieux;
    private ObservableList<Personnel> personnels;
    private ObservableList<Materiel> materiels;

    public MainModel() {
        this.evenementsNonConfirme = FXCollections.observableArrayList();
        this.evenementsConfirme = FXCollections.observableArrayList();
        this.lieux = FXCollections.observableArrayList();
        this.personnels = FXCollections.observableArrayList();
        this.materiels = FXCollections.observableArrayList();
    }

    public ObservableList<Evenement> getEvenementsNonConfirme() {
        return evenementsNonConfirme;
    }

    public ObservableList<Evenement> getEvenementsConfirme() {
        return evenementsConfirme;
    }

    /**
     * Permet de confirmer un évenement
     * @param evenement évenement à confirmer
     * @param date Date à laquelle confirmer
     */
    public void confirmeEvenement(Evenement evenement, DatePossible date) {
        evenement.confirme();
        evenement.setDateDebut(date.getDateDebut());
        evenement.setDateFin(date.getDateFin());

        evenementsNonConfirme.remove(evenement);
        evenementsConfirme.add(evenement);
    }

    public void addLieu(Lieu lieu) {
        lieux.add(lieu);
    }

    public void addPersonnel(Personnel personnel) {
        personnels.add(personnel);
    }

    public void addMateriel(Materiel materiel) {
        materiels.add(materiel);
    }

    public void removeMateriel(Materiel materiel) {
        materiels.remove(materiel);
    }

    public ObservableList<Lieu> getLieux() {
        return lieux;
    }

    public ObservableList<Materiel> getMateriels() {
        return materiels;
    }

    public ObservableList<Personnel> getPersonnels() {
        return personnels;
    }

    /**
     * Récupère tout les reservables disponibles un jour donné
     */
    public ObservableList getReservablesDisponibles(ObservableList reservables, LocalDate dateDebut, LocalDate dateFin) {
        ObservableList<Reservable> indisponible = FXCollections.observableArrayList();

        //On regarde pour chaque item réservable, si il est disponible le jour donné
        for(int i = 0; i < reservables.size(); i++) {
            Reservable r = (Reservable) reservables.get(i);
            if(!r.estDisponible(dateDebut, dateFin)) {
                indisponible.add(r);
            }
        }

        ObservableList<Reservable> disponible = FXCollections.observableArrayList(reservables);
        disponible.removeAll(indisponible);

        return disponible;
    }

    /**
     * Retourne la liste des reservable disponibles sur au moins une des dates possibles
     * @param reservables liste de reservables
     * @param date_possibles liste des dates possibles
     * @return liste de reservable
     */
    public ObservableList getReservablesDisponibles(ObservableList reservables, ObservableList<DatePossible> date_possibles) {
        ObservableList<Reservable> dispo = FXCollections.observableArrayList();

        // On récupère tout les reservable qui sont disponibles sur au moins une date possible
        for(int j = 0; j < reservables.size(); j++) {
            Reservable r = (Reservable) reservables.get(j);

            int i = 0;
            while(i < date_possibles.size() && !r.estDisponible(date_possibles.get(i).getDateDebut(), date_possibles.get(i).getDateFin())) {
                i++;
            }
            if(i < date_possibles.size()) dispo.add(r);
        }

        return dispo;
    }

    /**
     * Retourne la liste des lieux disponible dans une intervalle de date
     * @param dateDebut date de début
     * @param dateFin date de fin
     * @return
     */
    public ObservableList<Lieu> getLieuxDisponibles(LocalDate dateDebut, LocalDate dateFin) {
        return getReservablesDisponibles(lieux, dateDebut, dateFin);
    }

    public ObservableList<Personnel> getPersonnelDisponibles(LocalDate dateDebut, LocalDate dateFin) {
        return getReservablesDisponibles(personnels, dateDebut, dateFin);
    }

    public ObservableList<Materiel> getMaterielDisponibles(LocalDate dateDebut, LocalDate dateFin) {
        return getReservablesDisponibles(materiels, dateDebut, dateFin);
    }

    public ObservableList<Lieu> getLieuDisponibles(ObservableList<DatePossible> date_possibles) {
        return getReservablesDisponibles(lieux, date_possibles);
    }

    public ObservableList<Materiel> getMaterielDisponibles(ObservableList<DatePossible> date_possibles) {
        return getReservablesDisponibles(materiels, date_possibles);
    }

    public ObservableList<Personnel> getPersonnelDisponibles(ObservableList<DatePossible> date_possible) {
        return getReservablesDisponibles(personnels, date_possible);
    }


    /**
     * Supprime un élément reservable parmis les lieux, matériel et événements
     * @param r reservable
     */
    public void removeReservable(Reservable r) {
        materiels.remove(r);
        lieux.remove(r);
        personnels.remove(r);
    }

    public void addEvenement(Evenement e) {
        evenementsNonConfirme.add(e);
    }

    /**
     * Ajoute une évènement confirmé
     * @param e
     */
    public void addEvenementConfirme(Evenement e) {
        evenementsConfirme.add(e);
    }


    /**
     * Supression d'un évènement
     * @param e évènement à supprimer
     */
    public void supprimeEvenement(Evenement e) {
        evenementsConfirme.remove(e);
        evenementsNonConfirme.remove(e);

        // On défait le lien avec les ressources pour éviter les effets de bord
        for(Reservable r : e.getReservables()) {
            r.supprimeEvenement(e);
        }
    }
}
