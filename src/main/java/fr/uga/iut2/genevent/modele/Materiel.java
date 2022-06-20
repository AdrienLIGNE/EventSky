package fr.uga.iut2.genevent.modele;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Materiel extends Reservable  {

    private StringProperty label;
    private ObjectProperty<TypeMateriel> type;

    /**
     * Constructeur de matériel avec seulement le label, quantité initiale à 0
     * @param label
     */
    public Materiel(String label, TypeMateriel type){
        this(label, type, 1);
    }

    public Materiel(String label, TypeMateriel type, int quantite){
        this.label = new SimpleStringProperty();
        setLabel(label);
        setType(type);
        setQuantiteDisponible(quantite);
    }

    public void setType(TypeMateriel type) {
        this.type.set(type);
    }

    public ObjectProperty<TypeMateriel> getType() {
        return type;
    }

    public StringProperty getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label.set(label);
    }

}
