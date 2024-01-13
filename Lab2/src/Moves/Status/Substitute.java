package Moves.Status;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

import static ru.ifmo.se.pokemon.Stat.HP;

public class Substitute extends StatusMove
{

    public Substitute()
    {
        super(Type.NORMAL, 0.0, 100.0);
    }

    @Override
    protected void applySelfEffects(Pokemon p)
    {
        p.setMod(HP, (int)p.getStat(HP));
    }
    @Override
    protected String describe()
    {
        return "кидает приманку";
    }


}
