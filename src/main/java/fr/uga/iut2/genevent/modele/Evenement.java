package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.Main;
import javafx.beans.property.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

public class Evenement {

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private IntegerProperty duree;
    private StringProperty nomEvenement;
    private StringProperty nomArtiste;
    private boolean estConfirme;

    private IntegerProperty nbPersonnes;

    private Lieu lieu;

    private ArrayList<Personnel> personnel;
    private ArrayList<Materiel> materiel;

    private ObjectProperty<TypeEvenement> type;

    /**
     * Créé un événement
     * @param dateDebut date de début de l'événement
     * @param dateFin date de fin de l'événement
     * @param nomEvenement Nom de l'événement
     */
    public Evenement(LocalDate dateDebut, LocalDate dateFin, int duree, String nomEvenement, TypeEvenement typeEvenement) {
        personnel = new ArrayList<>();
        materiel = new ArrayList<>();

        this.nomEvenement = new SimpleStringProperty();
        this.nbPersonnes = new SimpleIntegerProperty();
        this.type = new SimpleObjectProperty<>();
        this.nomArtiste = new SimpleStringProperty();
        this.duree = new SimpleIntegerProperty();

        setNomEvenement(nomEvenement);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setType(typeEvenement);

        estConfirme = false;
    }

    public StringProperty getNomArtiste() {
        return nomArtiste;
    }

    public void setNomArtiste(String nomArtiste) {
        this.nomArtiste.set(nomArtiste);
    }

    public void setLieu(Lieu l) {
        l.affecteEvenement(this);
        this.lieu = l;
    }

    public void addPersonnel(Personnel p) {
        p.affecteEvenement(this);
        personnel.add(p);
    }

    public void addMateriel(Materiel m, int quantite) {
        m.affecteEvenement(this, quantite);
        materiel.add(m);
    }

    public Lieu getLieu() {
        return lieu;
    }

    public ArrayList<Personnel> getPersonnel() {
        return personnel;
    }

    public ArrayList<Materiel> getMateriel() {
        return materiel;
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
        this.dateDebut = dateDebut;
    }

    /**
     * Modifie la date de fin
     * @param dateFin date de fin de l'événement
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
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



    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public StringProperty getNomEvenement() {
        return nomEvenement;
    }

    public boolean isConfirmed() {
        return estConfirme;
    }

    public void confirme() {
        this.estConfirme = true;
    }

    /**
     * Vérifie si un événement se passe un jour donné
     * @param date date à tester
     * @return vrai si l'événement se déroule le jour donné
     */
    public boolean sePasseCeJour(LocalDate date) {
        return (date.compareTo(dateDebut) >= 0 & date.compareTo(dateFin) <= 0);
    }
}
