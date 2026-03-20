package TPBooking;

import java.util.List;

public abstract class Hebergement implements IAffichable, Comparable<Hebergement> {
    public int id;
    public String nom;
    public String adresse;
    public String type;
    public int capacite;
    public double prixParNuit;
    public List<Equipement> equipement = new java.util.ArrayList<>();
    public List<Notes> notes = new java.util.ArrayList<>();
    public List<Reservation> reservations = new java.util.ArrayList<>();

    public Hebergement(String nom, String adresse, String type, int capacite, double prixParNuit, List<Equipement> equipement) {
        this.id = Math.abs(java.util.UUID.randomUUID().hashCode());
        this.nom = nom;
        this.adresse = adresse;
        this.type = type;
        this.capacite = capacite;
        this.prixParNuit = prixParNuit;
        this.equipement = equipement;
    }

    @Override
    public void afficher() {
        System.out.print("www.tpbooking.yeah/" + id + "_view\n");
        System.out.println("\n--- " + nom + " ---");
        System.out.println(type);
        System.out.println("Adresse: " + adresse);
        System.out.println("Capacité: " + capacite);
        System.out.println("Prix/nuit: " + prixParNuit);
        System.out.println("Équipements:");
        for (Equipement e : equipement) {
            System.out.println("- " + e.nom + (e.inclu ? " (inclus)" : " (non inclus)"));
        }
        System.out.println("---");
    }

    @Override
    public String toString() {
        return type + "{id=" + id + ", nom='" + nom + "', adresse='" + adresse + "', capacite=" + capacite + ", prixParNuit=" + prixParNuit + "}";
    }

    @Override
    public int compareTo(Hebergement other) {
        if (other == null) return 1;
        return this.nom.compareToIgnoreCase(other.nom);
    }

    public static class PrixComparator implements java.util.Comparator<Hebergement> {
        @Override
        public int compare(Hebergement h1, Hebergement h2) {
            return Double.compare(h1.prixParNuit, h2.prixParNuit);
        }
    }

    public static final java.util.Comparator<Hebergement> PRIX_COMPARATOR = new PrixComparator();

    public boolean estDisponible(PeriodesDispo periode) {
        for (Reservation r : reservations) {
            if (r == null || r.dates == null || r.status == 2) continue;
            if (r.dates.chevauche(periode)) return false;
        }
        return true;
    }
}
