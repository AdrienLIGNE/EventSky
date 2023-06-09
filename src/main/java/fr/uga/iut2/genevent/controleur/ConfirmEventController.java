package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.DatePossible;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.Personnel;
import fr.uga.iut2.genevent.vue.ChoixMaterielQuantite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;

public class ConfirmEventController extends FormulaireController {

    private static ConfirmEventController controller;

    static {
        controller = new ConfirmEventController();
    }

    public static ConfirmEventController getController() {
        return controller;
    }

    private Evenement evenement;
    @FXML private ListView<DatePossible> dates_list;

    public void setEvenement(Evenement e) {
        this.evenement = e;

        ObservableList<ChoixMaterielQuantite> materiels = FXCollections.observableArrayList();

        for(Materiel m : evenement.getMateriel()) {
            materiels.add(new ChoixMaterielQuantite(m, m.getQuantiteAffecte(evenement)));
        }

        ObservableList<Personnel> personnels = FXCollections.observableArrayList(e.getPersonnel());

        // Calcul de la date possible en fonction des contraintes
        ObservableList<DatePossible> datePossibles = DatePossible.getDatePossible(e.getDateDebut().get(), e.getDateFin().get(), e.getDuree().getValue(), e.getLieu().get(), materiels, personnels);
        dates_list.setItems(datePossibles);

    }


    @Override
    public void confirmButtonClick(ActionEvent e) {
        if(verifieSaisies()) {
            // On récupère la date choisies

            DatePossible dateChoisi = dates_list.getSelectionModel().getSelectedItem();
            getModel().confirmeEvenement(evenement, dateChoisi);
            exitStage(getStageFromTarget(e.getTarget()));
        }
    }

    @Override
    public boolean verifieSaisies() {
        //TODO : Vérifier saisies
        return true;
    }

}
