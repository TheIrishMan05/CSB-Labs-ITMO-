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
        try (Scanner userScanner = new Scanner(System.in)) {
            InputController inputController = new InputController(userScanner);
            CoordinatesChecker coordinatesChecker = new CoordinatesChecker(inputController);
            LocationChecker locationChecker = new LocationChecker(inputController);
            PersonChecker personChecker = new PersonChecker(inputController);
            StudyGroupInterrogator studyGroupInterrogator = new StudyGroupInterrogator(inputController,
                    coordinatesChecker, locationChecker, personChecker);
            FileManager fileManager = new FileManager(args[0]);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
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
            ConsoleManager consoleManager = new ConsoleManager(commandManager, userScanner, studyGroupInterrogator);
            consoleManager.interactiveMode();
        }
    }
}