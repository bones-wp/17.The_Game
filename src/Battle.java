public class Battle {
    public void fight (Person hero, Person monster, FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;

            boolean fightEnd = false;

            while (!fightEnd){
                System.out.println("----Ход: " + turn + "----");
                if (turn++ %2 != 0) {
                    fightEnd = damage (monster, hero, fightCallback);
                }
                else {
                    fightEnd = damage (hero, monster, fightCallback);
                }
                try {
                    Thread.sleep(1500);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean damage (Person defender, Person attacker, FightCallback fightCallback) {
        int random = (int) Math.ceil(Math.random() * 2);
        int dmg = attacker.attack();

        int defenderHP;
        // смотрим был ли удар критическим
        if (random == 1) {
            defenderHP = defender.getHealth() - dmg;
        } else {
            defenderHP = defender.getHealth() - (dmg * 2);
        }

        // пишем вывод в консоль при ударах
        if (dmg != 0 && random == 1) {
            System.out.println(String.format("%s наносит %d урона", attacker.getName(), dmg));
            System.out.println(String.format("У %s осталось %d единиц здоровья", defender.getName(), defenderHP));
        } else if (dmg != 0 && random == 2) {
            System.out.println(String.format("%s наносит критический удар %d урона", attacker.getName(), dmg));
            System.out.println(String.format("У %s осталось %d единиц здоровья", defender.getName(), defenderHP));

        } else {
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }

        if (defenderHP <= 0 && defender instanceof Hero){
            System.out.println("Ваш герой пал в бою..");
            fightCallback.fightLose();
            return true;
        } else if (defenderHP <= 0) {
            System.out.println(String.format("%s уничтожен! Вы получаете %d опыта и %d золота", defender.getName(),
                    defender.getExperience(), defender.getGold()));
            attacker.setExperience(attacker.getExperience() + defender.getExperience());
            attacker.setGold(attacker.getGold() + defender.getGold());
            fightCallback.fightWin();
            return true;
            //если защищающийся не повержен, то мы устанавливаем ему новый уровень здоровья
        }else {
            defender.setHealth(defenderHP);
            return false;
        }
    }
}
