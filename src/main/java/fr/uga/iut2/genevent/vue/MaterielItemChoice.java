package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.ManageRessourcesController;
import fr.uga.iut2.genevent.controleur.MaterielItemController;
import fr.uga.iut2.genevent.modele.DatePossible;
import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.Personnel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Classe permettant d'afficher les items de matériels avec la sélection de quantité
 */
public class MaterielItemChoice extends ListCell<ChoixMaterielQuantite> {

    private MaterielItemController controller;
    private Node itemNode;

    // Les dates servent à calculer la dispo max pour la période afin de configurer le spinner

    private ObservableList<DatePossible> datesPossibles;

    public MaterielItemChoice(ObservableList<DatePossible> datePossibles) {
        super();

        this.datesPossibles = datePossibles;

        init();
    }


    private void init() {

        controller = new MaterielItemController();

        // On charge le fxml de l'item
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("materiel-item.fxml"));
        fxmlLoader.setController(controller);

        try {
            itemNode = fxmlLoader.load();
        }
        catch (IOException e) {
            // TODO: Logger erreur
        }
    }

    @Override
    protected void updateItem(ChoixMaterielQuantite materiel, boolean b) {
        super.updateItem(materiel, b);

        if(materiel != null) {

            controller.setNom(materiel.getMateriel().getLabel());

            updateQuantite(materiel);

            // Si la quantité de matériel change en cours de route, on actualise le spinner et on recalcul les dispos
            materiel.getMateriel().getQuantiteDisponibleProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    updateQuantite(materiel);
                }
            });

            // On active le mode de choix de quantité
            controller.setChooseQuantiteMode();


            setGraphic(itemNode);

            ContextMenu contextMenu = new ContextMenu();
            MenuItem modifier = new MenuItem("Modifier");

            modifier.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    ManageRessourcesController.editRessource(materiel.getMateriel());
                }
            });

            contextMenu.getItems().add(modifier);

            setContextMenu(contextMenu);
        }
        else setGraphic(null);
    }


    private void updateQuantite(ChoixMaterielQuantite materiel) {
        int quantiteDispo;

        if(datesPossibles == null) {
            quantiteDispo = materiel.getMateriel().getQuantiteDisponible();
        }
        else {
            quantiteDispo = materiel.getMateriel().getQuantiteDisponible(datesPossibles);
        }

        controller.setDispo(new SimpleIntegerProperty(quantiteDispo));
        materiel.setSpinner(controller.getQuantiteSpinner(), quantiteDispo);
    }

}
