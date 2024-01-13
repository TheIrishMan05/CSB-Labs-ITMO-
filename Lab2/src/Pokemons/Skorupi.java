package Pokemons;

import Moves.Physical.Rage;
import Moves.Status.FocusEnergy;
import Moves.Status.Substitute;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Skorupi extends Pokemon
{
    public Skorupi(String name, int level)
    {
        super(name, level);
        setType(Type.POISON, Type.BUG);
        setStats(40.0, 50.0, 90.0, 30.0, 55.0, 65.0);
        setMove(new FocusEnergy(), new Substitute(), new Rage());
    }

}
