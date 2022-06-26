package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.TypeEvenement;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe controleur permettant de manipuler les items représentant chaque évènements
 */
public class EvenementItemController extends Controller{

    private static EvenementItemController controller;

    static {
        controller = new EvenementItemController();
    }

    public static EvenementItemController getController() {
        return controller;
    }

    public EvenementItemController() {
        super();
        System.out.println();
    }

    @FXML private Label label_titre;
    @FXML private Label label_date;
    @FXML private Label label_type;
    @FXML private ImageView iv;

    public void setTitre(StringProperty titre) {
        this.label_titre.textProperty().bind(titre);
    }

    public void setDates(ObjectProperty<LocalDate> dateDebut, ObjectProperty<LocalDate> dateFin) {
        label_date.setText(dateDebut.getValue().format(DateTimeFormatter.ofPattern("dd MMM YYYY")) + " - " + dateFin.getValue().format(DateTimeFormatter.ofPattern("dd MMM YYYY")));

        // Lorsque les dates changent, on actualise
        ChangeListener callback =  new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate date, LocalDate t1) {
                label_date.setText(dateDebut.getValue().format(DateTimeFormatter.ofPattern("dd MMM YYYY")) + "\t-\t" + dateFin.getValue().format(DateTimeFormatter.ofPattern("dd MMM YYYY")));
            }
        };

        dateDebut.addListener(callback);
        dateDebut.addListener(callback);
    }

    public void setType(ObjectProperty<TypeEvenement> type) {
        label_type.setText(type.getValue().getNom());

        type.addListener(new ChangeListener<TypeEvenement>() {
            @Override
            public void changed(ObservableValue<? extends TypeEvenement> observableValue, TypeEvenement typeEvenement, TypeEvenement t1) {
                label_type.setText(type.getValue().getNom());
            }
        });

    }

    public void setImage(StringProperty lien){
        if (lien.get() !=null){
            iv.setImage(new Image(lien.get()));
            lien.addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    iv.setImage(new Image(t1));
                }
            });
        }
    }

}
