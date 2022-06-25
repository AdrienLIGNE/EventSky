package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.util.TextUtilitaire;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe représentant la notion de personnel
 * Personnel peut être affecté à un ou plusieurs événements
 */
public class Personnel extends Reservable  {

    private ObjectProperty<TypePersonnel> type;

    private StringProperty nom;
    private StringProperty mail;
    private StringProperty numero;

    public Personnel(String nom, TypePersonnel type){
        this.nom = new SimpleStringProperty();
        this.mail = new SimpleStringProperty();
        this.numero = new SimpleStringProperty();
        this.type = new SimpleObjectProperty<>();

        setNom(nom);
        setType(type);

    }

    public void setNom(String nom) {
        this.nom.set(TextUtilitaire.capitalize(nom));
    }


    public StringProperty getNom() {
        return nom;
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public StringProperty getMail() {
        return mail;
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    public StringProperty getNumero() {
        return numero;
    }

    public void setType(TypePersonnel type) {
        this.type.set(type);
    }

    public ObjectProperty<TypePersonnel> getTypeEmploi() {
        return type;
    }

    public double getSalaireHoraire(){
        return this.type.get().getSalaire();
    }

    /**
     * Méthode permettant de savoir combien va coûter le personnel pendant une durée donnée
     * @param nbHeure temps de sollicitation du personnel, en heure
     * @return salaire horaire multiplié par le nombre d'heure
     */
    public double getPrixDuree(double nbHeure){
        return this.type.get().getSalaire()*nbHeure;
    }

}
