package ru.ifmo;

import ru.ifmo.commands.*;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.managers.CommandManager;
import ru.ifmo.managers.ConsoleManager;
import ru.ifmo.managers.FileManager;
import ru.ifmo.utils.*;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Arguments size must be > 0");
            return;
        }

        try (Scanner userScanner = new Scanner(System.in)) {

            InputController inputController = new InputController(userScanner);
            CoordinatesChecker coordinatesChecker = new CoordinatesChecker(inputController);
            LocationChecker locationChecker = new LocationChecker(inputController);
            PersonChecker personChecker = new PersonChecker(inputController);
            StudyGroupInterrogator studyGroupInterrogator = new StudyGroupInterrogator(inputController,
                    coordinatesChecker, locationChecker, personChecker);

            CommandManager commandManager = getCommandManager(args, studyGroupInterrogator);
            ConsoleManager consoleManager = new ConsoleManager(commandManager, userScanner, studyGroupInterrogator);
            consoleManager.interactiveMode();
        }
    }

    private static CommandManager getCommandManager(String[] args, StudyGroupInterrogator studyGroupInterrogator) {
        CollectionManager collectionManager = new CollectionManager(new FileManager(args[0]));

        return new CommandManager(
                new Add(collectionManager, studyGroupInterrogator),
                new AddIfMax(collectionManager, studyGroupInterrogator),
                new Clear(collectionManager),
                new CountLessThan(collectionManager),
                new ExecuteScript(),
                new Exit(),
                new FilterGreaterThan(collectionManager),
                new Help(),
                new History(),
                new Info(collectionManager),
                new PrintAscending(collectionManager),
                new RemoveById(collectionManager),
                new RemoveGreater(collectionManager, studyGroupInterrogator),
                new RemoveLower(collectionManager, studyGroupInterrogator),
                new Save(collectionManager),
                new UpdateId(collectionManager, studyGroupInterrogator),
                new Show(collectionManager)
        );
    }
}