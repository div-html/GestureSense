import java.util.Scanner;
import matchmaking.MatchEligible;

// Base Class
class Player {

    protected String username;
    protected int level;

    Player(String username, int level) {
        this.username = username;
        this.level = level;
    }

    public void displayProfile() {
        System.out.println("Username : " + username);
        System.out.println("Level    : " + level);
    }
}

// Derived Class
class RankedPlayer extends Player {

    protected int matchmakingRating;

    RankedPlayer(String username, int level, int matchmakingRating) {
        super(username, level);
        this.matchmakingRating = matchmakingRating;
    }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("MMR  : " + matchmakingRating);
    }
}

// Final Derived Class
class PremiumPlayer extends RankedPlayer implements MatchEligible {

    private String preferredAgent;
    private String serverRegion;

    PremiumPlayer(String username,
                  int level,
                  int matchmakingRating,
                  String preferredAgent,
                  String serverRegion) {

        super(username, level, matchmakingRating);

        this.preferredAgent = preferredAgent;
        this.serverRegion = serverRegion;
    }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("Preferred Agent : " + preferredAgent);
        System.out.println("Server Region   : " + serverRegion);
    }

    @Override
    public void findMatch() {

        if (matchmakingRating >= 2000) {
            System.out.println("Rank : Immortal/Radiant");
        } else if (matchmakingRating >= 1000) {
            System.out.println("Rank : Platinum/Diamond");
        } else {
            System.out.println("Rank : Bronze/Silver");
        }

        System.out.println("Searching for a match in " + serverRegion + " server...");
    }
}

// Main Class
public class Lab3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Username : ");
        String username = sc.nextLine();

        System.out.print("Enter Level : ");
        int level = sc.nextInt();

        System.out.print("Enter Matchmaking Rating (MMR) : ");
        int mmr = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Preferred Agent : ");
        String agent = sc.nextLine();

        System.out.print("Enter Server Region : ");
        String region = sc.nextLine();

        PremiumPlayer player = new PremiumPlayer(
                username,
                level,
                mmr,
                agent,
                region);

        System.out.println("\nPLAYER PROFILE ");
        player.displayProfile();

        System.out.println("\nMATCHMAKING ");
        player.findMatch();

        sc.close();
    }
}