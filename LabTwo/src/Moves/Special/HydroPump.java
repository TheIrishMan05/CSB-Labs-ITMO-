package Moves.Special;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class HydroPump extends SpecialMove
{
    public HydroPump()
    {
        super(Type.WATER, 110.0, 80.0);
    }

    protected void applyOppDamage(Pokemon opp, double damage)
    {
        opp.setMod(Stat.HP, -(int)Math.round(damage));
    }
    @Override
    protected String describe()
    {
        return "поливает водой";
    }
}
