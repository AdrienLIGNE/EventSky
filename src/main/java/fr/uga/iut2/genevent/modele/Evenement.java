package fr.uga.iut2.genevent.modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;

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
        l.affecteEvenement(this);
        this.lieu = lieu;
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
