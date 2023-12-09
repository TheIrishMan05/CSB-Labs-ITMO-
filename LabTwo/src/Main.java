import Pokemons.*;
import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;


public class Main {
    public static void main(String[] args)
    {

        Battle battle = new Battle();
        battle.addAlly(new Oddish("Oddish", 10));
        battle.addAlly(new Servine("Servine", 10));
        battle.addAlly(new Cyndaquil("Cyndaquil", 10));
        battle.addFoe(new Skorupi("Skorupi", 10));
        battle.addFoe(new Drapion("Drapion", 10));
        battle.addFoe(new Hippowdon("Hippowdon", 10));
        battle.go();

    }
}
