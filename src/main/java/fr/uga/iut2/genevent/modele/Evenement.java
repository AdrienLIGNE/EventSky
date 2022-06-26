package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.Main;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

public class Evenement {

    private ObjectProperty<LocalDate> dateDebut;
    private ObjectProperty<LocalDate> dateFin;
    private IntegerProperty duree;
    private StringProperty nomEvenement;
    private StringProperty nomArtiste;
    private BooleanProperty estConfirme;

    private IntegerProperty nbPersonnes;

    private ObjectProperty<Lieu> lieu;

    private ObservableList<Personnel> personnel;
    private ObservableList<Materiel> materiel;

    private ObjectProperty<TypeEvenement> type;

    /**
     * Créé un événement
     * @param dateDebut date de début de l'événement
     * @param dateFin date de fin de l'événement
     * @param nomEvenement Nom de l'événement
     */
    public Evenement(LocalDate dateDebut, LocalDate dateFin, int duree, String nomEvenement, TypeEvenement typeEvenement) {
        personnel = FXCollections.observableArrayList();
        materiel = FXCollections.observableArrayList();

        this.nomEvenement = new SimpleStringProperty();
        this.nbPersonnes = new SimpleIntegerProperty();
        this.type = new SimpleObjectProperty<>();
        this.nomArtiste = new SimpleStringProperty();
        this.duree = new SimpleIntegerProperty();
        this.dateDebut = new SimpleObjectProperty<>();
        this.dateFin = new SimpleObjectProperty<>();
        this.estConfirme = new SimpleBooleanProperty();
        this.lieu = new SimpleObjectProperty<>();


        setNomEvenement(nomEvenement);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setType(typeEvenement);
        setDuree(duree);

    }

    public StringProperty getNomArtiste() {
        return nomArtiste;
    }

    public void setNomArtiste(String nomArtiste) {
        this.nomArtiste.set(nomArtiste);
    }

    public void setLieu(Lieu l) {
        l.affecteEvenement(this);
        this.lieu.set(l);
    }

    public void addPersonnel(Personnel p) {
        p.affecteEvenement(this);
        personnel.add(p);
    }

    public void addMateriel(Materiel m, int quantite) {
        m.affecteEvenement(this, quantite);
        materiel.add(m);
    }

    public ObjectProperty<Lieu> getLieu() {
        return lieu;
    }

    public ObservableList<Personnel> getPersonnel() {
        return personnel;
    }

    public ObservableList<Materiel> getMateriel() {
        return materiel;
    }

    public ArrayList<Reservable> getReservables() {
        ArrayList<Reservable> reservables = new ArrayList<>();
        reservables.addAll(personnel);
        reservables.addAll(materiel);
        reservables.add(lieu.getValue());

        return reservables;
    }

    public void setType(TypeEvenement type) {
        this.type.set(type);
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes.set(nbPersonnes);
    }

    public IntegerProperty getNbPersonnes() {
        return nbPersonnes;
    }

    public ObjectProperty<TypeEvenement> getType() {
        return type;
    }

    /**
     * Modifie la date du début
     * @param dateDebut date de début de l'événement
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut.set(dateDebut);
    }

    /**
     * Modifie la date de fin
     * @param dateFin date de fin de l'événement
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin.set(dateFin);
    }

    public void setDuree(int duree) {
        this.duree.set(duree);
    }

    public IntegerProperty getDuree() {
        return duree;
    }

    /**
     * Modifie le nom de l'événement
     * @param nomEvenement nom de l'événement
     */
    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement.set(nomEvenement);
    }



    public ObjectProperty<LocalDate> getDateDebut() {
        return dateDebut;
    }

    public ObjectProperty<LocalDate> getDateFin() {
        return dateFin;
    }

    public StringProperty getNomEvenement() {
        return nomEvenement;
    }

    public boolean isConfirmed() {
        return estConfirme.get();
    }
    public BooleanProperty getConfirmed() {
        return estConfirme;
    }

    public void confirme() {
        this.estConfirme.set(true);
    }

    /**
     * Supression des réservables (pour modification)
     */
    public void deleteAllReservable() {

        for(Reservable r : getReservables()) {
            r.supprimeEvenement(this);
        }

        materiel.clear();
        personnel.clear();
    }

    /**
     * Calcul le prix d'un évènement à partir du personnel et de sa durée
     * @return le prix de l'événement
     */
    public double getPrix() {
        double prix = 0;
        for(Personnel p : personnel) {
            prix += p.getTypeEmploi().get().getSalaire();
        }

        prix = prix * duree.get();
        return prix;
    }

    /**
     * Vérifie si un événement se passe un jour donné
     * @param date date à tester
     * @return vrai si l'événement se déroule le jour donné
     */
    public boolean sePasseCeJour(LocalDate date) {
        return (date.compareTo(dateDebut.getValue()) >= 0 & date.compareTo(dateFin.getValue()) <= 0);
    }
}
