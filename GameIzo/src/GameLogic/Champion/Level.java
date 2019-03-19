package GameLogic.Champion;

public class Level {

    private int experience;
    private int level;
    private Statistics statistics;
    private int[] levelValues = {
      0,0,100,200,500,1000,2000
    };

    public Level(Statistics statistics) {
        this.statistics = statistics;
        this.experience = 0;
        this.level = 1;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public void addExperience(int experience) {
        this.experience += experience;
        if(this.experience >= this.levelValues[level+1]) {
            this.level++;
            this.experience = 0;
            this.levelUp();
        }
    }

    public int getNextLevelExperience() {
        return this.levelValues[level+1];
    }

    public void levelUp() {
        this.statistics.setPointsAvailable(5);
    }
}
