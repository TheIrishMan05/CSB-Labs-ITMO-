package Moves.Physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class RockTomb extends PhysicalMove
{
    public RockTomb()
    {
        super(Type.ROCK, 60.0, 95.0);
    }
    @Override
    protected void applyOppEffects(Pokemon opp)
    {
        opp.setMod(Stat.SPEED, -1);
    }

    protected void applyOppDamage(Pokemon opp, double damage)
    {
        opp.setMod(Stat.HP, -(int)Math.round(damage));
    }

    @Override
    protected String describe() {
        return "кидает надгробный камень";
    }
}
