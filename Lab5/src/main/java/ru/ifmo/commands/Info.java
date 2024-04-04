package ru.ifmo.commands;

import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.utils.Command;

import java.time.LocalDateTime;

public class Info extends Command {

    private CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super("info", "print information about collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgsInputException();
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "There haven't been any initializations in this session yet." :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "There haven't been any savings in this session yet." :
                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            System.out.println("Description of collection:");
            System.out.println("- Type: " + collectionManager.collectionType());
            System.out.println("- Amount of elements: " + collectionManager.collectionSize());
            System.out.println("- Last saving date: " + lastSaveTimeString);
            System.out.println("- Last initialization date: " + lastInitTimeString);
            return true;
        } catch (WrongArgsInputException exception) {
            System.out.println("Using of '" + getName() + "'");
        }
        return false;
    }
}
