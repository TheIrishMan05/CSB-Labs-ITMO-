package Moves.Physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class VineWhip extends PhysicalMove
{
    public VineWhip()
    {
        super(Type.GRASS, 45.0, 100.0);
    }


    protected void applyOppDamage(Pokemon opp, double damage) {
        opp.setMod(Stat.HP, -(int) Math.round(damage));
    }
    @Override
    protected String describe()
    {
        return "шлёпает плетью";
    }
}
