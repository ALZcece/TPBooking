package TPBooking;

import java.util.List;

public class Hebergement implements IAffichable {
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
        System.out.println("Adresse: " + adresse);
        System.out.println("Type: " + type);
        System.out.println("Capacité: " + capacite);
        System.out.println("Prix par nuit: " + prixParNuit + "e");
        System.out.println("Equipements:");
        for (Equipement e : equipement) {
            System.out.println("- " + e.nom + (e.inclu ? " (inclus)" : " (non inclus)"));
        }
        System.out.println("---------------------------------\n");
    }

    public boolean estDisponible(PeriodesDispo periode) {
        for (Reservation r : reservations) {
            if (r == null || r.dates == null) continue;
            if (r.status == 2) continue;
            if (r.dates.chevauche(periode)) return false;
        }
        return true;
    }
}
