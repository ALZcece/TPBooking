package TPBooking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TP3Test {

    public List<Hebergement> initialisation() {
        Hebergement h1 = new Hotel("Hotel de Paris", "Paris", 5, 500, List.of(new Equipement("WiFi", true), new Equipement("Petit déjeuner", true), new Equipement("Piscine", false)));
        Hebergement h2 = new Hotel("Le roi lion", "Lyon", 4, 80, List.of(new Equipement("WiFi", true), new Equipement("Petit déjeuner", false), new Equipement("Parking", true)));
        Hebergement h3 = new ChambreDHote("Auberge de jeunesse", "Marseille", 1, 20, List.of(new Equipement("WiFi", false), new Equipement("Petit déjeuner", false), new Equipement("Cuisine commune", true), new Equipement("Douche", false)));
        Hebergement h4 = new Hotel("Hotel vue mer", "Nice", 2, 40, List.of(new Equipement("WiFi", true), new Equipement("Petit déjeuner", true), new Equipement("Vue mer", false)));
        Hebergement h5 = new Appartement("Loft vins", "Bordeaux", 2, 75, List.of(new Equipement("WiFi", false), new Equipement("Petit déjeuner", true), new Equipement("Oenologie", true)));
        Hebergement h6 = new Appartement("Maison d'hôtes", "Toulouse", 4, 70, List.of(new Equipement("WiFi", true), new Equipement("Petit déjeuner", true)));
        return List.of(h1, h2, h3, h4, h5, h6);
    }

    public static List<Hebergement> trierParNom(List<Hebergement> hebergements) {
        List<Hebergement> copie = new ArrayList<>(hebergements);
        Collections.sort(copie);
        return copie;
    }

    public static List<Hebergement> trierParPrix(List<Hebergement> hebergements) {
        List<Hebergement> copie = new ArrayList<>(hebergements);
        copie.sort(Hebergement.PRIX_COMPARATOR);
        return copie;
    }

    public static List<Hebergement> filtrerParPrixMax(List<Hebergement> hebergements, double prixMax) {
        return hebergements.stream().filter(h -> h.prixParNuit <= prixMax).collect(Collectors.toList());
    }

    public static void afficherListe(List<Hebergement> liste) {
        int index = 1;
        for (Hebergement h : liste) {
            System.out.println("Option " + index + ":");
            h.afficher();
            index++;
        }
    }

    public static void main(String[] args) {
        TP3Test test = new TP3Test();
        List<Hebergement> listeHebergements = test.initialisation();

        Scanner sc = new Scanner(System.in);
        System.out.println("\nBienvenue sur TPBOOKING.com.yeah!");
        System.out.println("1. S'inscrire");
        System.out.println("2. Se connecter");
        int select = sc.nextInt();
        sc.nextLine();

        Client clientx;
        if (select == 1) {
            clientx = new NouveauClient().login(sc);
        } else {
            clientx = new AncienClient().login(sc);
        }

        System.out.println("\nSouhaitez-vous trier/filtrer les hébergements ?");
        System.out.println("1. Trier par nom (Comparable)");
        System.out.println("2. Trier par prix (Comparator)");
        System.out.println("3. Filtrer par prix maximum");
        System.out.println("4. Aucun");
        int optionTri = sc.nextInt();
        sc.nextLine();

        if (optionTri == 1) {
            listeHebergements = trierParNom(listeHebergements);
            System.out.println("\nHébergements triés par nom (Comparable) :");
        } else if (optionTri == 2) {
            listeHebergements = trierParPrix(listeHebergements);
            System.out.println("\nHébergements triés par prix (Comparator) :");
        } else if (optionTri == 3) {
            System.out.println("Entrez le prix maximum :");
            double prixMax = sc.nextDouble();
            sc.nextLine();
            listeHebergements = filtrerParPrixMax(listeHebergements, prixMax);
            System.out.println("\nHébergements filtrés par prix <= " + prixMax + " :");
        } else {
            System.out.println("\nAucun tri/filtrage appliqué.");
        }

        if (listeHebergements.isEmpty()) {
            System.out.println("Aucun hébergement disponible après filtrage.");
            sc.close();
            return;
        }

        afficherListe(listeHebergements);

        System.out.println("\nEntrer le numéro de l'hébergement que vous voulez réserver :");
        int choix = sc.nextInt();
        while (choix < 1 || choix > listeHebergements.size()) {
            System.out.println("Numéro d'hébergement invalide. Veuillez entrer un numéro entre 1 et " + listeHebergements.size() + " :");
            choix = sc.nextInt();
        }
        sc.nextLine();
        Hebergement hebergementChoisi = listeHebergements.get(choix - 1);
        System.out.println("\nVous avez choisi : ");
        hebergementChoisi.afficher();

        System.out.println("Entrer la date d'arrivée (jj.mm.aaaa) :");
        String dateArriveeStr = sc.nextLine();
        System.out.println("Entrer la date de départ (jj.mm.aaaa) :");
        String dateDepartStr = sc.nextLine();

        String[] partsA = dateArriveeStr.split("\\.");
        String[] partsD = dateDepartStr.split("\\.");
        Calendar cA = Calendar.getInstance();
        cA.set(Integer.parseInt(partsA[2]), Integer.parseInt(partsA[1]) - 1, Integer.parseInt(partsA[0]));
        Calendar cD = Calendar.getInstance();
        cD.set(Integer.parseInt(partsD[2]), Integer.parseInt(partsD[1]) - 1, Integer.parseInt(partsD[0]));
        Date dA = cA.getTime();
        Date dD = cD.getTime();

        PeriodesDispo periode = new PeriodesDispo(dA, dD);

        if (hebergementChoisi.estDisponible(periode)) {
            System.out.println("Logement disponible.");
            Reservation reservation = new Reservation(clientx, hebergementChoisi, periode);
            System.out.println("Prix total : " + reservation.prixFinal + " euros.");
            System.out.println("Confirmer ? (1=oui, 0=non)");
            int c = sc.nextInt();
            reservation.status = (c == 1 ? 1 : 2);
            System.out.println(reservation.status == 1 ? "Réservation confirmée." : "Réservation annulée.");
        } else {
            System.out.println("Hébergement indisponible pour ces dates.");
        }

        sc.close();
    }
}