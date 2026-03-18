package TPBooking;

import java.util.Scanner;

/**
 * Ancien client qui se connecte et peut consulter son historique.
 * Dispose d'une réduction en fonction du nombre de réservations.
 */
public class AncienClient extends Client {
    public AncienClient() {
        super("", "", ""); 
    }

    @Override
    public Client login(Scanner sc) {
        System.out.println("--- Connexion ---");
        System.out.print("Nom: ");
        this.nom = sc.nextLine();
        System.out.print("Prénom: ");
        this.prenom = sc.nextLine();
        System.out.print("Email: ");
        this.email = sc.nextLine();
        System.out.print("Mot de passe: ");
        this.motDePasse = sc.nextLine();
        System.out.println("Connexion réussie! Bienvenue " + prenom + "!");
        return this;
    }
    @Override
    public double getReduction() {
        if (nbReservations >= 10) {
            System.out.println("Merci pour votre fidélité " + prenom + " ! 15% de réduction.");
            return 0.15;
        }
        return 0.0;
    }

    public void afficherHistorique() {
        System.out.println("Historique des réservations pour " + prenom + " :");
        for (Reservation r : reservations) {
            System.out.println("- Réservation " + r.id + " pour " + r.hebergement.nom + " du " + r.dates.debut + " au " + r.dates.fin + " (status=" + r.status + ")");
        }
    }
}