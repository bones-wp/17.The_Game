import java.util.Random;
public abstract class Person implements Fighter{

    private String name;
    private int health = 100;
    private int gold = (int) Math.ceil(Math.random() * 100);
    private int dexterity = (int) Math.ceil(Math.random() * 30);
    private int strength = (int) Math.ceil(Math.random() * 30);
    private int experience = (int) Math.ceil(Math.random() * 100);
    private int level = 1;

    public Person(String name) {
        this.name = name;
    }

    public int attack () {
        Random randomCrit = new Random();

        int random = (int) Math.ceil(Math.random() * 100);

        if (dexterity * 3 > random && randomCrit.nextBoolean()) {
            System.out.println(name + " наносит критический удар! " + (strength * 2) + " урона");
            return (strength * 2);
        }
        else if (dexterity * 3 > random) {
            System.out.println(name + " наносит " + strength + " урона");
            return strength;
        }
        else {
            System.out.println(name + " промахнулся");
            return 0;
        }
    }

    public void healing (){
        if (health < 100){
            health += 10;
        }
        if (health > 100) {
            health = 100;
        }
    }

    //@Override
    //public String toString() {
        //return String.format("%s здоровье: %d", name, health);
    //}

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getStrength() {
        return strength;
    }

    public int getExperience() {
        return experience;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLevel() {
        return level;
    }
}
