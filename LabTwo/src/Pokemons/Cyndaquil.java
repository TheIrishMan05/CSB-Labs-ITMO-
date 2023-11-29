package Pokemons;

import Moves.Special.HydroPump;
import Moves.Special.ThunderShock;
import Moves.Status.Taunt;
import ru.ifmo.se.pokemon.*;

public class Cyndaquil extends Pokemon
{
    public Cyndaquil(String name, int level)
    {
        super(name, level);
        setType(Type.FIRE);
        setStats(39.0, 52.0, 43.0, 60.0,50.0, 65.0);
        setMove(new HydroPump(), new Taunt(), new ThunderShock());
    }
}
