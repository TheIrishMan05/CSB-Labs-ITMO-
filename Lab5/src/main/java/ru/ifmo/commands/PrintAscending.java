package ru.ifmo.commands;

import ru.ifmo.exceptions.EmptyCollectionException;
import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.utils.Command;

public class PrintAscending extends Command {

    private CollectionManager collectionManager;

    public PrintAscending(CollectionManager collectionManager) {
        super("print_ascending", "print all elements of collection in ascending order");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgsInputException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            collectionManager.printAscending();
            return true;
        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        } catch (EmptyCollectionException exception) {
            System.out.println("Collection is empty!");
        }

        return false;
    }
}
