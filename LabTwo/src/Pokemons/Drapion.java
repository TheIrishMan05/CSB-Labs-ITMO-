package Pokemons;

import Moves.Special.Confusion;
import ru.ifmo.se.pokemon.*;

public class Drapion extends Skorupi
{

    public Drapion(String name, int level)
    {
        super(name, level);
        setType(Type.POISON, Type.DARK);
        setStats(70.0, 90.0, 110.0,  60.0, 75.0, 95.0);
        addMove(new Confusion());
    }
}
