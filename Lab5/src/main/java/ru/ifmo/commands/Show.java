package ru.ifmo.commands;

import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.utils.Command;

public class Show extends Command {
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "print all elements of the collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgsInputException();
            System.out.println(collectionManager);
            return true;
        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        }
        return false;
    }
}
