package fr.uga.iut2.genevent.test;

import fr.uga.iut2.genevent.modele.*;
import fr.uga.iut2.genevent.vue.EvenementItem;
import fr.uga.iut2.genevent.vue.MaterielItem;
import fr.uga.iut2.genevent.vue.PersonnelItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML private ListView<Personnel> list_personnel;
    @FXML private ListView<Materiel> list_materiel;

    private MainApplication app;

    public TestController() {
        this.app = new MainApplication();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Personnel> list = FXCollections.observableArrayList();
        ObservableList<Materiel> list2 = FXCollections.observableArrayList();

        list_personnel.setItems(list);
        list_materiel.setItems(list2);

        list.add(new Personnel("DURAND", "Nadia", TypePersonnel.BARMAN));
        list.add(new Personnel("ALIA", "Alice", TypePersonnel.BARMAN));

        list2.add(new Materiel("Camion", 2));

        list_personnel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        list_personnel.setCellFactory(new Callback<ListView<Personnel>, ListCell<Personnel>>() {
            @Override
            public ListCell<Personnel> call(ListView<Personnel> personnelListView) {
                return new PersonnelItem();
            }
        });

        list_materiel.setCellFactory(new Callback<ListView<Materiel>, ListCell<Materiel>>() {
            @Override
            public ListCell<Materiel> call(ListView<Materiel> materielListView) {
                return new MaterielItem();
            }
        });


        

    }
}
