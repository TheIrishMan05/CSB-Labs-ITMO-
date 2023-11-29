package Pokemons;

import Moves.Physical.VineWhip;
import Moves.Special.HydroPump;
import Moves.Special.ThunderShock;
import Moves.Status.Taunt;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Oddish extends Pokemon
{
    public Oddish(String name, int level)
    {
        super(name, level);
        setType(Type.GRASS, Type.POISON);
        setStats(45.0, 50.0, 55.0, 75.0, 65.0, 30.0);
        setMove(new HydroPump(), new Taunt(), new ThunderShock(), new VineWhip());

    }
}
