package fr.uga.iut2.genevent.modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

public class Evenement {

    private int idEvenement;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StringProperty nomEvenement;
    private boolean estConfirme;

    private Lieu lieu;
    private ArrayList<Personnel> personnel;
    private ArrayList<Materiel> materiel;

    /**
     * Créé un événement
     * @param id identifiant de l'événement
     * @param dateDebut date de début de l'événement
     * @param dateFin date de fin de l'événement
     * @param nomEvenement Nom de l'événement
     */
    public Evenement(int id, LocalDate dateDebut, LocalDate dateFin, String nomEvenement) {
        personnel = new ArrayList<>();
        this.nomEvenement = new SimpleStringProperty();

        setNomEvenement(nomEvenement);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setIdEvenement(id);

        estConfirme = false;
    }

    public void setLieu(Lieu l) {
        this.lieu = lieu;
    }

    public void addPersonnel(Personnel p) {
        personnel.add(p);
    }

    public void addMateriel(Materiel m) {
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

    /**
     * Modifie l'identifiant de l'événement (plus grand ou égale à 0)
     * @param idEvenement
     */
    public void setIdEvenement(int idEvenement) {
        if(idEvenement >= 0) {
            this.idEvenement = idEvenement;
        }
        else {
            this.idEvenement = 0;
        }
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

    /**
     * Modifie le nom de l'événement
     * @param nomEvenement nom de l'événement
     */
    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement.set(nomEvenement);
    }

    /**
     * Récupère l'identifiant de l'évènement
     * @return identifiant
     */
    public int getIdEvenement() {
        return idEvenement;
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

    public boolean isEstConfirme() {
        return estConfirme;
    }
}
