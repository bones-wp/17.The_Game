import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Person hero = null;
    private Battle battle = new Battle();


    public void start(){
        System.out.println("Введите имя Вашего героя:");
        try {
            enter(br.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void enter(String string) throws IOException {
        if (hero == null) {
            hero = new Hero(string);
            System.out.println(String.format("Вы создали героя %s со следующими характеристиками : \n Здоровье : %d " +
                    "\n Ловкость: %d \n Сила: %d \n Уровень: %d \n", hero.getName(), hero.getHealth(), hero.getDexterity(),
                    hero.getStrength(), hero.getLevel()));

            choice();
        }

        switch (string){
            case "1":
                System.out.println("Торговец ещё не приехал");
                enter(br.readLine());
            case "2":
                System.out.println("Ваш путь лежит в опасный тёмный лес!");
                realFight();
                break;
            case "3":
                System.exit(1);
                break;
            case "да": enter("2");
            break;
            case "нет":
                choice();
                enter(br.readLine());
        }
        enter(br.readLine());
    }

    private void choice(){
        System.out.println("Куда Вы хотите идти?" + "\n" + "1. К торговцу" + "\n" + "2. В тёмный лес" + "\n" +
                "3. На выход" +"\n");
    }

    private Person createMonster() {
        int random = (int) Math.ceil(Math.random() * 10);

        if (random % 2 == 0) return new Goblin();
        else return new Skeleton();
    }

    private void realFight(){
        battle.fight(hero, createMonster(), new FightCallback(){
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! У Вас %d опыта, %d золота, %d единиц HP", hero.getName(),
                        hero.getExperience(), hero.getGold(), hero.getHealth()));
                System.out.println("Продолжить исследовать тёмный лес? (да/нет)");
                try {
                    enter(br.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLose() {
                System.out.println("Стоит попробовать ещё раз..");
                hero = null;
                System.exit(1);
            }
        });
    }

}
