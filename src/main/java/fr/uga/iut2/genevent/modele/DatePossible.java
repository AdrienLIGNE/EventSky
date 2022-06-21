package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.vue.ChoixMaterielQuantite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe permettant de représenter une date possible
 */
public class DatePossible {

    private LocalDate dateDebut;
    private LocalDate dateFin;

    public DatePossible(LocalDate d, LocalDate f) {
        this.dateDebut = d;
        this.dateFin = f;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    @Override
    public String toString() {
        return getDateDebut().format(DateTimeFormatter.ofPattern("dd MMMM YYYY")) + "\t - \t" + getDateFin().format(DateTimeFormatter.ofPattern("dd MMMM YYYY"));
    }

    /**
     * Calcul toutes les dates possible d'une durée déterminée sur un interval donné
     * @return
     */
    public static ObservableList<DatePossible> getDatePossible(LocalDate d, LocalDate f, int duree) {
        ObservableList<DatePossible> datePossibles = FXCollections.observableArrayList();

        // On récupère toutes les dates possibles entre le début et la fin.
        List<LocalDate> dates = d.datesUntil(f.minusDays(duree - 2)).collect(Collectors.toList());

        for(LocalDate date : dates) {
            datePossibles.add(new DatePossible(date, date.plusDays(duree - 1)));
        }

        return datePossibles;
    }

    /**
     * Retourne toutes les dates possible en fonction des contraintes matériels et humaines
     * @param d date de début
     * @param f date de fin
     * @param duree durée de l'évènement
     * @param lieu lieu où se passe l'événement
     * @param materiels materiels nécessaire
     * @param personnels personnels nécessaire
     * @return liste de date disponible
     */
    public static ObservableList<DatePossible> getDatePossible(LocalDate d, LocalDate f, int duree, Lieu lieu, ObservableList<ChoixMaterielQuantite> materiels, ObservableList<Personnel> personnels) {
        ObservableList<DatePossible> datePossibles = getDatePossible(d, f, duree);

        // Pour chaque date on regarde si c'est possible
        for(Iterator<DatePossible> it = datePossibles.iterator(); it.hasNext();) {

            DatePossible date = it.next();

            // Si le lieu est pas dispo alors la date n'est pas possible
            if(lieu != null && !lieu.estDisponible(date.getDateDebut(), date.getDateFin())) {
                it.remove();
            }
            else if(materiels != null){

                // Si un du matériel n'est pas dispo alors la date n'est pas possible
                int i = 0;
                while (i < materiels.size() && materiels.get(i).getMateriel().estDisponible(date.getDateDebut(), date.getDateFin(), materiels.get(i).getQuantite())) {
                    i++;
                }

                if (i < materiels.size()) {
                    it.remove();
                }
                else if(personnels != null){

                    // Si un du personnel n'est pas disponible, alors la date n'est pas possible
                    i = 0;
                    while (i < personnels.size() && personnels.get(i).estDisponible(date.getDateDebut(), date.getDateFin())) {
                        i++;
                    }

                    if (i < personnels.size()) {
                        it.remove();
                    }

                }
            }

        }

        return datePossibles;
    }
}
