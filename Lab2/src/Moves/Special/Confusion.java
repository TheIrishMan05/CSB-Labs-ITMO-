package Moves.Special;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Confusion extends SpecialMove
{
    public Confusion()
    {
        super(Type.PSYCHIC, 50.0, 100.0);
    }
    @Override
    public void applyOppEffects(Pokemon p)
    {
        if (Math.random() <= 0.1)
        {
            Effect.confuse(p);
        }
    }

    protected String describe()
    {
        return "сбивает с толку";

    }
}
