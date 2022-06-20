package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Evenement;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Date;

public class InfosEventController extends Controller{


    @FXML private Label title;
    @FXML private TextField nom_tf;
    @FXML private TextField lieu_tf;
    @FXML private DatePicker date_debut_dp;
    @FXML private DatePicker date_fin_dp;
    @FXML private TextField nb_personnes_tf;
    @FXML private TextField type_tf;
    @FXML private TextField nom_artiste_tf;
    @FXML private TextField cout_tf;


    public void setEvenement(Evenement e) {
        title.setText(e.getNomEvenement().getValue());
        nom_tf.setText(e.getNomEvenement().getValue());
        lieu_tf.setText(e.getLieu().getNom().getValue());
        date_debut_dp.setValue(e.getDateDebut());
        date_fin_dp.setValue(e.getDateFin());
        nb_personnes_tf.setText(e.getNbPersonnes().getValue().toString());
        type_tf.setText(e.getType().getValue().getNom());
        nom_artiste_tf.setText(e.getNomArtiste().getValue());

    }




}
