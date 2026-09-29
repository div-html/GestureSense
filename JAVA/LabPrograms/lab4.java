class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// User class
class User {
    String name;
    int age;
    boolean premium;

    User(String name, int age, boolean premium) {
        this.name = name;
        this.age = age;
        this.premium = premium;
    }
}

// Matchmaking System
class MatchmakingSystem {

    private int availableMatches = 3;

    synchronized void findMatch(User user) {

        System.out.println(user.name + " is searching for a match...");

        if (availableMatches > 0) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }

            availableMatches--;

            System.out.println("Match found for " + user.name);
            System.out.println("Remaining matches: " + availableMatches);

        } else {
            System.out.println("No matches available for " + user.name);
        }
    }
}

// Thread class
class MatchThread extends Thread {

    User user;
    MatchmakingSystem system;

    MatchThread(User user, MatchmakingSystem system) {
        this.user = user;
        this.system = system;
    }

    public void run() {
        system.findMatch(user);
    }
}

// Main class
public class Lab4 {

    public static void main(String[] args) {

        // User-defined exception
        try {
            User u1 = createUser("Rahul", 17, false);
        } catch (InvalidAgeException e) {
            System.out.println(
                "User Defined Exception: " + e.getMessage()
            );
        }

        // Built-in exception
        try {
            int a = 10;
            int b = 0;

            int result = a / b;

            System.out.println(result);

        } catch (ArithmeticException e) {
            System.out.println(
                "Built-in Exception: Cannot divide by zero."
            );
        }

        // Matchmaking system
        MatchmakingSystem system = new MatchmakingSystem();

        User user1 = new User("Arjun", 17, true);
        User user2 = new User("Riya", 22, false);
        User user3 = new User("Karan", 24, true);
        User user4 = new User("Sneha", 23, false);

        // Create threads
        MatchThread t1 = new MatchThread(user1, system);
        MatchThread t2 = new MatchThread(user2, system);
        MatchThread t3 = new MatchThread(user3, system);
        MatchThread t4 = new MatchThread(user4, system);

        // Thread priority
        t1.setPriority(Thread.MAX_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);

        t2.setPriority(Thread.NORM_PRIORITY);
        t4.setPriority(Thread.NORM_PRIORITY);

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    // User-defined exception method
    static User createUser(String name, int age, boolean premium)
            throws InvalidAgeException {

        if (age < 18) {
            throw new InvalidAgeException(
                "User must be 18 or above to register."
            );
        }

        return new User(name, age, premium);
    }
}