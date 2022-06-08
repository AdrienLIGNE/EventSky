package fr.uga.iut2.genevent.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe de base pour accéder au modèle de données
 */
public class MainApplication {

    private ObservableList<Evenement> evenements;

    private ObservableList<Lieu> lieux;
    private ObservableList<Personnel> personnels;
    private ObservableList<Materiel> materiels;

    public MainApplication () {
        this.evenements = FXCollections.observableArrayList();
        this.lieux = FXCollections.observableArrayList();
        this.personnels = FXCollections.observableArrayList();
        this.materiels = FXCollections.observableArrayList();


        evenements.add(new Evenement(0, LocalDate.of(2022, 6, 7), LocalDate.of(2022, 6, 10), "CONCERT DE RAP FRANCAIS OUIIIIIIII"));
        evenements.add(new Evenement(1, LocalDate.of(2022, 8, 25), LocalDate.of(2022, 8, 28), "SPECTACLE DE MAGIE"));
        evenements.add(new Evenement(2, LocalDate.of(2022, 8, 21), LocalDate.of(2022, 8, 21), "CONCERT ANNEE 90"));
        evenements.add(new Evenement(3, LocalDate.of(2022, 10, 17), LocalDate.of(2022, 10, 17), "CONCERT DE EDSHEERAN"));

        Personnel personnel1 = new Personnel("Quentin", "LACOMBE", TypePersonnel.VIGILE);
        Personnel personnel2 = new Personnel("Timéo", "COGNE", TypePersonnel.VIGILE);

        personnels.add(personnel1);
        personnels.add(personnel2);

        evenements.get(0).addPersonnel(personnel1);
        evenements.get(1).addPersonnel(personnel2);
        evenements.get(0).confirme();
        evenements.get(1).confirme();

        ObservableList<Personnel> personnels_dispo = getPersonnelDisponibles(LocalDate.of(2022, 8, 26));

        System.out.println("Disponibilité du personnel : " + personnel1.estDisponible(LocalDate.of(2022, 8, 26)));

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
}
