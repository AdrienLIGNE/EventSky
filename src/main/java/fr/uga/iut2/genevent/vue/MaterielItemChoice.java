package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.ManageRessourcesController;
import fr.uga.iut2.genevent.controleur.MaterielItemController;
import fr.uga.iut2.genevent.modele.Materiel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public MaterielItemChoice(LocalDate dateDebut, LocalDate dateFin) {
        super();

        // Création du controller
        controller = new MaterielItemController();
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

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

            int quantiteDispo = materiel.getMateriel().getQuantiteDisponible(dateDebut, dateFin);

            controller.setNom(materiel.getMateriel().getLabel());

            // On récupère le spinner pour pouvoir y accéder depuis le controleur du formulaire
            materiel.setSpinner(controller.getQuantiteSpinner(), quantiteDispo);

            controller.setDispo(new SimpleIntegerProperty(quantiteDispo));

            // Si la quantité de matériel change en cours de route, on actualise le spinner et on recalcul les dispos
            materiel.getMateriel().getQuantiteDisponibleProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    int quantiteDispo = materiel.getMateriel().getQuantiteDisponible(dateDebut, dateFin);

                    controller.setDispo(new SimpleIntegerProperty(quantiteDispo));
                    materiel.setSpinner(controller.getQuantiteSpinner(), quantiteDispo);

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

}
