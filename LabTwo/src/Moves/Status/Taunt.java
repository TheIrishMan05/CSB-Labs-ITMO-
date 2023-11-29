package Moves.Status;

import ru.ifmo.se.pokemon.*;

public class Taunt extends StatusMove
{
    public Taunt()
    {
        super(Type.DARK, 0.0, 100.0);
    }

    @Override
    protected void applyOppEffects(Pokemon opp)
    {
        Move sm = new StatusMove();
        Effect TauntEffect = new Effect().chance(1.0).turns(3).condition(Status.FREEZE);
        if (sm.getPriority() == 1)
        {
            opp.setCondition(TauntEffect);
        }
    }

    @Override
    protected String describe()
    {
        return "издевается над противником";
    }
}
