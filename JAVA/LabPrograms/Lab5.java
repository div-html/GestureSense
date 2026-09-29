import java.util.ArrayList;

// User-defined exception
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

    @Override
    public String toString() {
        return "Name: " + name +
               ", Age: " + age +
               ", Premium: " + premium;
    }
}

// Matchmaking System
class MatchmakingSystem {

    private int availableMatches = 3;

    // Collection Framework
    private ArrayList<User> matchedUsers = new ArrayList<>();

    synchronized void findMatch(User user) {

        System.out.println(user.name + " is searching for a match...");

        if (availableMatches > 0) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }

            availableMatches--;

            // Adding user to ArrayList
            matchedUsers.add(user);

            System.out.println("Match found for " + user.name);
            System.out.println("Remaining matches: " + availableMatches);

        } else {
            System.out.println("No matches available for " + user.name);
        }
    }

    // Display collection
    void displayMatchedUsers() {

        System.out.println("\n--- Matched Users ---");

        for (User user : matchedUsers) {
            System.out.println(user);
        }

        System.out.println("Total matched users: " + matchedUsers.size());
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
    @Override
    public void run() {
        system.findMatch(user);
    }
}

// Main class
public class Lab5 {

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

        // Collection Framework
        ArrayList<User> users = new ArrayList<>();

        users.add(new User("Arjun", 21, true));
        users.add(new User("Riya", 22, false));
        users.add(new User("Karan", 24, true));
        users.add(new User("Sneha", 23, false));

        // Display all users using Collection
        System.out.println("\n--- Registered Users ---");

        for (User user : users) {
            System.out.println(user);
        }

        // Create threads using users from ArrayList
        MatchThread t1 = new MatchThread(users.get(0), system);
        MatchThread t2 = new MatchThread(users.get(1), system);
        MatchThread t3 = new MatchThread(users.get(2), system);
        MatchThread t4 = new MatchThread(users.get(3), system);

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
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        // Display matched users
        system.displayMatchedUsers();
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

