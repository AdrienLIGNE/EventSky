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

    public MainApplication () {
        this.evenements = FXCollections.observableArrayList();

        evenements.add(new Evenement(0, LocalDate.of(2022, 06, 07), LocalDate.of(2022, 06, 10), "CONCERT DE RAP FRANCAIS OUIIIIIIII"));
        evenements.add(new Evenement(1, null, null, "CONCERT DE RAP FRANCAIS"));
        evenements.add(new Evenement(2, null, null, "CONCERT DE RAP FRANCAIS"));
        evenements.add(new Evenement(3, null, null, "CONCERT DE RAP FRANCAIS"));
    }


    public ObservableList<Evenement> getEvenements() {
        return evenements;
    }
}
