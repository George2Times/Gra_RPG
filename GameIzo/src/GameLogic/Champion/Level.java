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
        this.statistics.setMaxManaPoints(this.statistics.getMaxManaPoints() + 10);
        this.statistics.setMaxHitPoints(this.statistics.getMaxHitPoints() + 10);
        this.statistics.setMinDamage(this.statistics.getMinDamage() + 2);
        this.statistics.setMaxDamage(this.statistics.getMaxDamage() + 2);
        this.statistics.setArmor(this.statistics.getArmor() + 2);
        this.statistics.setAbilityPower(this.statistics.getAbilityPower() + 3);
    }
}
