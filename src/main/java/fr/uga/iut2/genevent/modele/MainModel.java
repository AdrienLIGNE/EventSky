package fr.uga.iut2.genevent.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * Classe de base pour accéder au modèle de données
 */
public class MainModel {

    private ObservableList<Evenement> evenements;

    private ObservableList<Lieu> lieux;
    private ObservableList<Personnel> personnels;
    private ObservableList<Materiel> materiels;

    public MainModel() {
        this.evenements = FXCollections.observableArrayList();
        this.lieux = FXCollections.observableArrayList();
        this.personnels = FXCollections.observableArrayList();
        this.materiels = FXCollections.observableArrayList();
    }

    public ObservableList<Evenement> getEvenements() {
        return evenements;
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
    public ObservableList getReservablesDisponibles(ObservableList reservables, LocalDate date) {
        ObservableList<Reservable> indisponible = FXCollections.observableArrayList();

        //On regarde pour chaque item réservable, si il est disponible le jour donné
        for(int i = 0; i < reservables.size(); i++) {
            Reservable r = (Reservable) reservables.get(i);
            if(!r.estDisponible(date)) {
                indisponible.add(r);
            }
        }

        ObservableList<Reservable> disponible = FXCollections.observableArrayList(reservables);
        disponible.removeAll(indisponible);

        return disponible;
    }

    /**
     * Retourne la liste des lieux disponible un jour donné
     * @param date
     * @return
     */
    public ObservableList<Lieu> getLieuxDisponibles(LocalDate date) {
        return getReservablesDisponibles(lieux, date);
    }

    public ObservableList<Personnel> getPersonnelDisponibles(LocalDate date) {
        return getReservablesDisponibles(personnels, date);
    }

    public ObservableList<Materiel> getMaterielDisponibles(LocalDate date) {
        return getReservablesDisponibles(materiels, date);
    }
}
