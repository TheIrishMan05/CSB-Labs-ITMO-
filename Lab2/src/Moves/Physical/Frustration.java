package Moves.Physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class Frustration extends PhysicalMove
{
    public Frustration()
    {
        super(Type.NORMAL, 0.0, 100.0);
    }
    @Override
    protected String describe()
    {
        return "заставляет другого покемона разочароваться в своём хозяине. Cнижает показатель дружбы, на величину, в 2,5 раза меньшую, чем разность максимального показателя счастья и текущего показателя дружбы";

    }
}
