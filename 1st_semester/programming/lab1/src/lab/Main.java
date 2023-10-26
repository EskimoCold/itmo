package lab;

import ru.ifmo.se.pokemon.*;
import lab.pokemons.*;

class Main{
    public static void main(String[] args){
        Battle battle = new Battle();

        Pokemon tornadus = new Tornadus("Tornadus", 1);
        Pokemon baltoy = new Baltoy("Baltoy", 1);
        Pokemon claydol = new Claydol("Claydol", 1);
        Pokemon sewaddle = new Sewaddle("Sewaddle", 1);
        Pokemon swadloon = new Swadloon("Swadloon", 1);
        Pokemon leavanny = new Leavanny("Leavanny", 1);


        battle.addAlly(tornadus);
        battle.addAlly(baltoy);
        battle.addAlly(claydol);

        battle.addFoe(sewaddle);
        battle.addFoe(swadloon);
        battle.addFoe(leavanny);

        battle.go();
    }
}
