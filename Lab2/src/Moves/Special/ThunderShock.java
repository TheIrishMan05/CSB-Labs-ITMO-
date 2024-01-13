package Moves.Special;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class ThunderShock extends SpecialMove
{
    public ThunderShock()
    {
        super(Type.ELECTRIC, 40.0, 100.0);
    }

    @Override
    protected void applyOppEffects(Pokemon p)
    {
        if (Math.random() <= 0.1)
        {
            Effect.paralyze(p);
        }
    }
    @Override
    protected String describe()
    {
        return "ударил током";
    }
}
