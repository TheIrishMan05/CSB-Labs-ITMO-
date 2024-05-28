package ru.ifmo.commands;


import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.utils.Command;

public class Exit extends Command {

    public Exit() {
        super("exit", "finish program running(without saving)");
    }


    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgsInputException();
            return true;
        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        }
        return false;
    }
}
