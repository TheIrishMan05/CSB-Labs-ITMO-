package Moves.Special;

import ru.ifmo.se.pokemon.*;

public class PetalDance extends SpecialMove
{
    public PetalDance()
    {
        super(Type.GRASS, 120.0, 100.0);
        hits = 3;
    }
    @Override
    protected void applyOppEffects(Pokemon opp)
    {
        Effect e = new Effect().chance(1.0).turns(3).condition(Status.FREEZE);
        opp.setCondition(e);
    }
    @Override
    protected void applySelfEffects(Pokemon p)
    {
        Effect.confuse(p);
    }
    @Override
    protected String describe()
    {
        return "танцует на костях и сбивает себя с толку";
    }
}
