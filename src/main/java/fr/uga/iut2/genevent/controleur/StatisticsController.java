package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.TypeEvenement;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticsController extends Controller implements Initializable {
    @FXML Label lbl_nb_evenements;
    @FXML PieChart piechart;



    public static StatisticsController getController() {
        return new StatisticsController();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialisation du pieChart
        createChart();

        //affichage du nombre d'événements créés
        int nbEvenements = getModel().getEvenementsNonConfirme().size() + getModel().getEvenementsConfirme().size();
        lbl_nb_evenements.setText(String.valueOf(nbEvenements));
    }

    private void createChart(){
        ObjectProperty<ObservableList<PieChart.Data>> donnees = new SimpleObjectProperty<>(FXCollections.observableArrayList(
                new PieChart.Data("Théâtre",getNbEvenements(getModel().getEvenementsConfirme(), TypeEvenement.THEATRE)),
                new PieChart.Data("Concert",getNbEvenements(getModel().getEvenementsConfirme(), TypeEvenement.CONCERT)),
                new PieChart.Data("Danse",getNbEvenements(getModel().getEvenementsConfirme(), TypeEvenement.DANSE)),
                new PieChart.Data("Cirque",getNbEvenements(getModel().getEvenementsConfirme(), TypeEvenement.CIRQUE)),
                new PieChart.Data("Magie",getNbEvenements(getModel().getEvenementsConfirme(), TypeEvenement.MAGIE)),
                new PieChart.Data("Marionnette",getNbEvenements(getModel().getEvenementsConfirme(), TypeEvenement.MARIONNETTE)),
                new PieChart.Data("Cinéma",getNbEvenements(getModel().getEvenementsConfirme(), TypeEvenement.CINEMA))
        ));
        piechart.dataProperty().bind(donnees);
    }

    private int getNbEvenements(List<Evenement> evenements, TypeEvenement typeEvenement){
        int nbEvenements = 0;
        for (Evenement evenement : evenements){
            if (evenement.getType().get().getNom().equals(typeEvenement.getNom())){
                nbEvenements ++;
            }
        }
        return nbEvenements;
    }
}
