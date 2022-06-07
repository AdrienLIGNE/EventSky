package fr.uga.iut2.genevent.modele;

import java.time.LocalDate;

public class Evenement {

    private int idEvenement;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String nomEvenement;
    private boolean estConfirme;

    public Evenement() {

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
        this.nomEvenement = nomEvenement;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public boolean isEstConfirme() {
        return estConfirme;
    }
}
