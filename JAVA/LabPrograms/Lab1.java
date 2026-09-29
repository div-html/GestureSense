public class Lab1{
    private String playerName;
    private int level;
    private int score;
    private int coins;
    public Lab1(String name,int l, int s, int c){
        this.playerName=name;
        this.level=l;
        this.score=s;
        this.coins=c;

    }
    public void winGame(int pointsEarned){
        this.score+=pointsEarned;
        if (this.score>=100){
            this.level++;
            System.out.println("Congratulations on leveling up "+this.playerName);
            this.score-=100;
        }
    }
    public void collectDailyBonus(int daysLogged){
        System.out.println("Collecting bonus for number of days Logged in "+daysLogged);
        for(int i=1;i<=daysLogged;i++){
            this.coins+=10;
        }
    }
    public void displayStats(){
        System.out.println("Player Name:"+this.playerName);
        System.out.println("Level:"+this.level);
        System.out.println("Coins:"+this.coins);
        System.out.println("Score:"+this.score+"/100");
    }
    public static void main(String[] args) {
        Lab1 obj1=new Lab1("Akio",0,0,0);
        Lab1 obj2=new Lab1("Nikihashi",1,20,10);
        obj1.winGame(20);
        obj1.collectDailyBonus(3);
        obj1.displayStats();
        obj2.winGame(80);
        obj2.collectDailyBonus(8);
        obj2.displayStats();
    }
}