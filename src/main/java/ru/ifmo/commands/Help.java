package ru.ifmo.commands;

import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.utils.Command;

public class Help extends Command {
    public Help() {
        super("help", "print information about commands");
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
