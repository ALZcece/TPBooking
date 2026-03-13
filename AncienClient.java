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
    public Client login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Connexion ---");
        System.out.print("Email: ");
        this.email = sc.nextLine();
        System.out.print("Mot de passe: ");
        this.motDePasse = sc.nextLine();
        // aucune vérification pour simplifier
        System.out.println("Connexion réussie! Bienvenue " + prenom + "!");
        return this;
    }
    @Override
    public double getReduction() {
        if (nbReservations >= 10) {
            System.out.println("Merci pour votre confiance " + prenom + " ! \n"+
            "Pour vous remercier de votre fidélité, 15% sur votre prochaine réservation.");
            return 0.15;
        } else {
            return 0.0;
        }
    }

    public void afficherHistorique() {
        System.out.println("Historique des réservations pour " + prenom + ":");
        for (Reservation r : reservations) {
            System.out.println("- Réservation " + r.id + " pour " + r.hebergement.nom + " du " + r.dates.debut + " au " + r.dates.fin + " (status=" + r.status + ")");
        }
    }
}