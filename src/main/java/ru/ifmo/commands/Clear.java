package ru.ifmo.commands;

import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.utils.Command;

public class Clear extends Command {
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "clear the collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgsInputException();
            collectionManager.clearCollection();
            System.out.println("Collection has been cleared!");
            return true;
        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        }
        return false;
    }
}
