public class Lab2 {
    private String username;
    private int matchmakingRating;
    private String preferredAgent;
    private String serverRegion;

    Lab2(String u) {
        this.username = u;
        this.matchmakingRating = 0;
        this.preferredAgent = "Unassigned";
        this.serverRegion = "Auto";
    }

    Lab2(String u, int mmr) {
        this.username = u;
        this.matchmakingRating = mmr;
        this.preferredAgent = "Unassigned";
        this.serverRegion = "Auto";
    }

    Lab2(String u, int mmr, String pa, String sr) {
        this.username = u;
        this.matchmakingRating = mmr;
        this.preferredAgent = pa;
        this.serverRegion = sr;
    }

    public void updateStats(int matchResult) {
        if (matchResult == 1) {
            this.matchmakingRating = this.matchmakingRating + 20;
        } else if (matchResult == 0) {
            this.matchmakingRating = this.matchmakingRating - 20;
            if (this.matchmakingRating < 0) this.matchmakingRating = 0;
        }
    }

    public void updateStats(int kills, int deaths, int assists) {
        int performanceBonus = (kills * 2) + assists - (deaths * 2);
        this.matchmakingRating = this.matchmakingRating + performanceBonus;
        if (this.matchmakingRating < 0) this.matchmakingRating = 0;
    }

    public void updateStats(String newAgent) {
        this.preferredAgent = newAgent;
    }

    public void displayProfile() {
        System.out.println("Player: " + username + " | MMR: " + matchmakingRating + 
                           " | Agent: " + preferredAgent + " | Server: " + serverRegion);
    }

    public static void main(String[] args) {
        Lab2[] matchmakingQueue = new Lab2[3];

        matchmakingQueue[0] = new Lab2("GuestPlayer123");
        matchmakingQueue[1] = new Lab2("TacticalGenius", 1200);
        matchmakingQueue[2] = new Lab2("OmenMain", 2100, "Omen", "Mumbai");

        System.out.println("--- Initial Matchmaking Queue ---");
        for (int i = 0; i < matchmakingQueue.length; i++) {
            matchmakingQueue[i].displayProfile();
        }

        matchmakingQueue[0].updateStats(1); 
        matchmakingQueue[1].updateStats(18, 12, 6); 
        matchmakingQueue[2].updateStats("Reyna"); 

        System.out.println("\n--- Queue After Stat Updates ---");
        for (int i = 0; i < matchmakingQueue.length; i++) {
            matchmakingQueue[i].displayProfile();
        }
    }
}