package Pokemons;

import Moves.Status.FocusEnergy;
import Moves.Status.Substitute;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Hippowdon extends Pokemon
{
    public Hippowdon(String name, int level)
    {
        super(name, level);
        setType(Type.GROUND);
        setStats(108.0, 112.0, 118.0, 68.0, 72.0, 47.0);
        setMove(new FocusEnergy(), new Substitute());
    }
}
