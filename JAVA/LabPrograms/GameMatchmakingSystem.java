import java.util.*;

class Player {
    private String username;
    private String game;
    private int age;
    private int skillRating;

    public Player(String username, String game, int age, int skillRating) {
        this.username = username;
        this.game = game;
        this.age = age;
        this.skillRating = skillRating;
    }

    public String getUsername() {
        return username;
    }

    public String getGame() {
        return game;
    }

    public int getAge() {
        return age;
    }

    public int getSkillRating() {
        return skillRating;
    }

    @Override
    public String toString() {
        return "Username: " + username +
                " | Game: " + game +
                " | Age: " + age +
                " | Skill Rating: " + skillRating;
    }
}

public class GameMatchmakingSystem {

    static Scanner sc = new Scanner(System.in);
    static List<Player> players = new ArrayList<>();

    static void addPlayer() {

        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();

        while (username.isEmpty()) {
            System.out.print("Username cannot be empty. Enter again: ");
            username = sc.nextLine().trim();
        }

        System.out.print("Enter game: ");
        String game = sc.nextLine().trim();

        while (game.isEmpty()) {
            System.out.print("Game cannot be empty. Enter again: ");
            game = sc.nextLine().trim();
        }

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        while (age < 13) {
            System.out.print("Age must be at least 13. Enter again: ");
            age = sc.nextInt();
        }

        System.out.print("Enter skill rating (0-5000): ");
        int rating = sc.nextInt();

        while (rating < 0 || rating > 5000) {
            System.out.print("Rating must be between 0 and 5000: ");
            rating = sc.nextInt();
        }

        sc.nextLine();

        players.add(new Player(username, game, age, rating));

        System.out.println("Player added successfully!");
    }

    static void displayPlayers() {

        if (players.isEmpty()) {
            System.out.println("No players available.");
            return;
        }

        System.out.println("\n----- ALL PLAYERS -----");

        players.forEach(player -> System.out.println(player));
    }

    static void findMatches() {

        if (players.isEmpty()) {
            System.out.println("No players available.");
            return;
        }

        System.out.print("Enter game to find matches: ");
        String game = sc.nextLine();

        System.out.print("Enter minimum skill rating: ");
        int rating = sc.nextInt();
        sc.nextLine();

        System.out.println("\nMATCHES ");

        players.stream()
                .filter(player ->
                        player.getGame().equalsIgnoreCase(game)
                        && player.getSkillRating() >= rating)
                .forEach(player -> System.out.println(player));
    }

    static void sortPlayers() {

        if (players.isEmpty()) {
            System.out.println("No players available.");
            return;
        }

        System.out.println("\nPLAYERS SORTED BY SKILL ");

        players.stream()
                .sorted(
                        Comparator.comparingInt(
                                Player::getSkillRating
                        ).reversed()
                )
                .forEach(player -> System.out.println(player));
    }

    static void uniqueGames() {

        if (players.isEmpty()) {
            System.out.println("No players available.");
            return;
        }

        System.out.println("\nUNIQUE GAMES ");

        players.stream()
                .map(Player::getGame)
                .distinct()
                .forEach(game -> System.out.println(game));
    }

    // Display top players
    static void topPlayers() {

        if (players.isEmpty()) {
            System.out.println("No players available.");
            return;
        }

        System.out.print("Enter number of top players: ");
        int n = sc.nextInt();
        sc.nextLine();

        while (n <= 0) {
            System.out.print("Enter a positive number: ");
            n = sc.nextInt();
            sc.nextLine();
        }

        System.out.println("\nTOP " + n + " PLAYERS ");

        players.stream()
                .sorted(
                        Comparator.comparingInt(
                                Player::getSkillRating
                        ).reversed()
                )
                .limit(n)
                .forEach(player -> System.out.println(player));
    }

    static void countHighRatedPlayers() {

        long count = players.stream()
                .filter(player ->
                        player.getSkillRating() >= 3000)
                .count();

        System.out.println(
                "Number of players with rating >= 3000: "
                        + count
        );
    }

    static void averageRating() {

        if (players.isEmpty()) {
            System.out.println("No players available.");
            return;
        }

        double average = players.stream()
                .mapToInt(Player::getSkillRating)
                .average()
                .orElse(0);

        System.out.println(
                "Average Skill Rating: " + average
        );
    }

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\nGAME MATCHMAKING SYSTEM ");
            System.out.println("1. Add Player");
            System.out.println("2. Display All Players");
            System.out.println("3. Find Matches");
            System.out.println("4. Sort Players by Skill");
            System.out.println("5. Display Unique Games");
            System.out.println("6. Display Top Players");
            System.out.println("7. Count High-Rated Players");
            System.out.println("8. Average Skill Rating");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addPlayer();
                    break;

                case 2:
                    displayPlayers();
                    break;

                case 3:
                    findMatches();
                    break;

                case 4:
                    sortPlayers();
                    break;

                case 5:
                    uniqueGames();
                    break;

                case 6:
                    topPlayers();
                    break;

                case 7:
                    countHighRatedPlayers();
                    break;

                case 8:
                    averageRating();
                    break;

                case 9:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please enter 1-9."
                    );
            }

        } while (choice != 9);

        sc.close();
    }
}