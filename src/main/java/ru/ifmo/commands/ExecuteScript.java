package ru.ifmo.commands;

import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.utils.Command;

public class ExecuteScript extends Command {
    public ExecuteScript() {
        super("execute_script <file_name>", "execute script from the written file.");
    }


    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgsInputException();
            System.out.println("Script is being executed '" + argument + "'...");
            return true;
        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        }
        return false;
    }
}
