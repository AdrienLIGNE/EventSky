package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ReservableTest {

    @org.junit.jupiter.api.Test
    void estDisponible() {

        LocalDate debut = LocalDate.of(2022, 10, 5);
        LocalDate fin = LocalDate.of(2022, 10, 6);

        // Création de l'événement
        Evenement evenement = new Evenement(0, debut, fin, "Concert de Rock");

        // Création de personnel
        Personnel personnel1 = new Personnel("LACOMBE", "Quentin", TypePersonnel.VIGILE);

        // Le personnel n'étant pas associé à un événement, il devrait être disponible tout les jours
        for(LocalDate d : LocalDate.of(2022, 1, 1).datesUntil(LocalDate.of(2022, 12, 31)).collect(Collectors.toList())) {
            assertTrue(personnel1.estDisponible(d), "Le personnel devrait être disponible le " + d.format(DateTimeFormatter.ISO_DATE));
        }

        // On affecte le personnel à l'événement
        evenement.addPersonnel(personnel1);

        // Le personnel devrait encore être disponible car l'événement n'est pas confirmé
        for(LocalDate d : LocalDate.of(2022, 1, 1).datesUntil(LocalDate.of(2022, 12, 31)).collect(Collectors.toList())) {
            assertTrue(personnel1.estDisponible(d), "Le personnel devrait être disponible le " + d.format(DateTimeFormatter.ISO_DATE));
        }

        // Confirmation de l'événement
        evenement.confirme();

        // Le personnel devrait ne pas être disponible pendant les événements
        for(LocalDate d : LocalDate.of(2022, 1, 1).datesUntil(LocalDate.of(2022, 12, 31)).collect(Collectors.toList())) {
            if (d.compareTo(debut) >= 0 & d.compareTo(fin) <= 0) {
                assertFalse(personnel1.estDisponible(d), "Le personnel ne devrait pas être disponible le " + d.format(DateTimeFormatter.ISO_DATE));
            } else {
                assertTrue(personnel1.estDisponible(d), "Le personnel devrait être disponible le " + d.format(DateTimeFormatter.ISO_DATE));
            }
        }
    }

    @Test
    void testDispoAvecQuantite() {
        Evenement evenement = new Evenement(0, LocalDate.of(2022, 7, 8), LocalDate.of(2022, 7, 11), "Concert de Rock");
        Evenement evenement2 = new Evenement(0, LocalDate.of(2022, 7, 10), LocalDate.of(2022, 7, 15), "Spectacle de magie");

        Materiel chaises = new Materiel("chaise", TypeMateriel.MOBILIER,10);

        evenement.addMateriel(chaises, 5);
        evenement2.addMateriel(chaises, 5);

        evenement.confirme();
        evenement2.confirme();

        assertTrue(chaises.estDisponible(LocalDate.of(2022, 7, 8)));
        assertTrue(chaises.estDisponible(LocalDate.of(2022, 7, 9)));
        assertFalse(chaises.estDisponible(LocalDate.of(2022, 7, 10)));
        assertFalse(chaises.estDisponible(LocalDate.of(2022, 7, 11)));
        assertTrue(chaises.estDisponible(LocalDate.of(2022, 7, 12)));
        assertTrue(chaises.estDisponible(LocalDate.of(2022, 7, 13)));
        assertTrue(chaises.estDisponible(LocalDate.of(2022, 7, 14)));
        assertTrue(chaises.estDisponible(LocalDate.of(2022, 7, 15)));
    }

    @Test
    void getQuantiteDisponible() {

        // Création de l'événement
        Evenement evenement = new Evenement(0, LocalDate.of(2022, 10, 5), LocalDate.of(2022, 10, 6), "Concert de Rock");

        Materiel materiel = new Materiel("Chaise", TypeMateriel.MATERIEL_MUSICAL,15);
        evenement.addMateriel(materiel, 10);

        assertEquals(15, materiel.getQuantiteDisponible(LocalDate.of(2022, 10, 5)), "Il n'y a pas le bon nombre de chaise");

        // Confirmation de l'événement
        evenement.confirme();
        assertEquals(5, materiel.getQuantiteDisponible(LocalDate.of(2022, 10, 5)), "Il n'y a pas le bon nombre de chaise");

        // Vérification du nombre de quantité en général
        assertEquals(15, materiel.getQuantiteDisponible(), "Il n'y a pas le bon nombre de chaise");
        assertEquals(15, materiel.getQuantiteDisponible(LocalDate.of(2022, 10, 8)), "Il n'y a pas le bon nombre de chaise");

    }
}