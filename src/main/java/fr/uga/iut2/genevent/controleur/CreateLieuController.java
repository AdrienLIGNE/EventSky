package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Lieu;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateLieuController extends FormulaireController<Lieu> implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public boolean verifieSaisies() {
        return false;
    }
}
