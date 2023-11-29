package Moves.Physical;

import ru.ifmo.se.pokemon.*;

public class Rage extends PhysicalMove
{

    public Rage()
    {
        super(Type.NORMAL, 20, 100);
    }




    @Override
    protected void applySelfEffects(Pokemon p)
    {
        Pokemon opp = new Pokemon();
        if (checkAccuracy(opp, p))
        {
            p.setMod(Stat.ATTACK,1);
        }
    }
    @Override
    protected String describe()
    {
        return "взбесился";
    }

}
