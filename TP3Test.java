package TPBooking;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class TP3Test {

    public List<Hebergement> initialisation() {
        // initialiser hebergement de test depuis Hebergement.java
            Hebergement hebergement1 = new Hebergement("Hotel de Paris", "Paris","Hotel", 5, 500, List.of(new Equipement("WiFi", true), new Equipement("Petit déjeuner", true), new Equipement("Piscine", false)));
            Hebergement hebergement2 = new Hebergement("Le roi lion", "Lyon","Hotel", 4, 80, List.of(new Equipement("WiFi", true), new Equipement("Petit déjeuner", false), new Equipement("Parking", true)));
            Hebergement hebergement3 = new Hebergement("Auberge de jeunesse", "Marseille","Auberge de jeunesse", 1, 20, List.of(new Equipement("WiFi", false), new Equipement("Petit déjeuner", false), new Equipement("Cuisine commune", true), new Equipement("Douche", false)));
            Hebergement hebergement4 = new Hebergement("Hotel vue mer", "Nice","Hotel", 2, 40, List.of(new Equipement("WiFi", true), new Equipement("Petit déjeuner", true), new Equipement("Vue mer", false)));
            Hebergement hebergement5 = new Hebergement("Les vignerons", "Bordeaux","Hotel", 1, 20, List.of(new Equipement("WiFi", false), new Equipement("Petit déjeuner", true), new Equipement("Oenologie", true)));
            Hebergement hebergement6 = new Hebergement("Maison d'hôtes", "Toulouse","Maison d'hôtes", 4, 70, List.of(new Equipement("WiFi", true), new Equipement("Petit déjeuner", true)));
            return List.of(hebergement1, hebergement2, hebergement3, hebergement4, hebergement5, hebergement6);
    }
      
    public static void main(String[] args) {
        TP3Test test = new TP3Test();
        List<Hebergement> listeHebergements = test.initialisation();
        // choisir le type de client (nouveau ou ancien)
        Scanner sc = new Scanner(System.in);
        System.out.println("\nBienvenue sur TPBOOKING.com.yeah!");
        System.out.println("\n1. S'inscrire\n2. Se connecter");
        int select = sc.nextInt();
        sc.nextLine(); // consommer la fin de ligne
        Client clientx;
        if (select == 1) {
            clientx = new NouveauClient().login();
        } else {
            clientx = new AncienClient().login();
        }
        // afficher les hébergements disponibles
        System.out.println("\nVoici les hébergements disponibles :\n");
        int i = 1;
        for (Hebergement hebergement : listeHebergements) {
            System.out.println("Option " + i + ":");
            hebergement.afficher();
            i++;
        }
        // entrer le numéro de l'hébergement choisi
        System.out.println("\nEntrer le numéro de l'hébergement que vous voulez réserver :");
        int choix = sc.nextInt();
        while (choix < 1 || choix > listeHebergements.size()) {
            System.out.println("Numéro d'hébergement invalide. Veuillez entrer un numéro entre 1 et " + listeHebergements.size() + " :");
            choix = sc.nextInt();
        }
        Hebergement hebergementChoisi = listeHebergements.get(choix - 1);

        // afficher vous avez choisi l'hébergement
        System.out.println("\nVous avez choisi l'hébergement : " + choix);
        hebergementChoisi.afficher();
        // entrer les dates de réservation (bloquant)
        System.out.println("Entrer la date d'arrivée (format: jj.mm.aaaa) :");
        String dateArriveeStr;
        while (true) {
            try {
                dateArriveeStr = sc.nextLine();
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Entrée fermée, fin du programme.");
                sc.close();
                return;
            }
            if (dateArriveeStr != null && !dateArriveeStr.trim().isEmpty()) break;
            System.out.println("Veuillez entrer une date d'arrivée valide (jj.mm.aaaa) :");
        }
        System.out.println("Entrer la date de départ (format: jj.mm.aaaa) :");
        String dateDepartStr = sc.next();
        // convertir les dates en objets Date
        String[] dateArriveeParts = dateArriveeStr.split("\\.");
        String[] dateDepartParts = dateDepartStr.split("\\.");
        Calendar calArrivee = Calendar.getInstance();
        calArrivee.set(Integer.parseInt(dateArriveeParts[2]), Integer.parseInt(dateArriveeParts[1]) - 1, Integer.parseInt(dateArriveeParts[0]));
        Calendar calDepart = Calendar.getInstance();
        calDepart.set(Integer.parseInt(dateDepartParts[2]), Integer.parseInt(dateDepartParts[1]) - 1, Integer.parseInt(dateDepartParts[0]));
        Date dateArrivee = calArrivee.getTime();
        Date dateDepart = calDepart.getTime();
        PeriodesDispo periode = new PeriodesDispo(dateArrivee, dateDepart);
        // vérifier la disponibilité de l'hébergement pour les dates choisies
        if (hebergementChoisi.estDisponible(periode)) {
            System.out.println("\nLogement disponible pour les dates choisies.");
            // calculer le prix total de la réservation
            Reservation reservation = new Reservation(clientx, hebergementChoisi, periode);
            System.out.println("\nLe prix total de votre réservation est : " + reservation.prixFinal + " euros.");
            // confirmer la réservation
            System.out.println("\nVoulez-vous confirmer votre réservation ? (0/1)");
            int c = sc.nextInt();
            if (c == 1) {
                reservation.status = 1; // confirmer la réservation
                System.out.println("\nVotre réservation a été confirmée. Merci pour votre confiance !");
            } else {
                reservation.status = 2; // annuler la réservation
                System.out.println("\nVotre réservation a été annulée.");
            }
        } else {
            System.out.println("\nDésolé, l'hébergement n'est pas disponible pour les dates choisies.");
        }
        sc.close();
    }
}