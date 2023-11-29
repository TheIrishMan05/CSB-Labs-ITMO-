package Pokemons;

import Moves.Physical.Frustration;
import Moves.Physical.RockTomb;
import Moves.Special.PetalDance;
import Moves.Special.ThunderShock;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
public class Servine extends Pokemon
{
    public Servine(String name, int level)
    {
        super(name, level);
        setType(Type.GRASS);
        setStats(60.0, 60.0, 75.0, 60.0, 75.0, 83.0);
        setMove(new ThunderShock(), new Frustration(), new PetalDance(), new RockTomb());
    }
}