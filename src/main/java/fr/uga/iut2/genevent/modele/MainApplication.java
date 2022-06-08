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


        evenements.add(new Evenement(0, LocalDate.of(2022, 6, 7), LocalDate.of(2022, 6, 10), "CONCERT DE RAP FRANCAIS OUIIIIIIII"));
        evenements.add(new Evenement(1, LocalDate.of(2022, 8, 25), LocalDate.of(2022, 8, 28), "SPECTACLE DE MAGIE"));
        evenements.add(new Evenement(2, LocalDate.of(2022, 8, 21), LocalDate.of(2022, 8, 21), "CONCERT ANNEE 90"));
        evenements.add(new Evenement(3, LocalDate.of(2022, 10, 17), LocalDate.of(2022, 10, 17), "CONCERT DE EDSHEERAN"));

        Personnel personnel1 = new Personnel("Quentin", "LACOMBE", TypePersonnel.VIGILE);

        evenements.get(0).addPersonnel(personnel1);
        evenements.get(0).confirme();
        System.out.println("Disponibilité du personnel : " + personnel1.estDisponible(LocalDate.of(2022, 6, 7)));

    }

    public ObservableList<Evenement> getEvenements() {
        return evenements;
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
     * Récupère tout les lieux disponibles pour un jour donné
     */
    public ObservableList<Lieu> getLieuxDisponible(LocalDate date) {
        ArrayList<Lieu> indisponible = new ArrayList<>();

        for(Reservable reservable : lieux) {

        }
        return null;
    }
}
