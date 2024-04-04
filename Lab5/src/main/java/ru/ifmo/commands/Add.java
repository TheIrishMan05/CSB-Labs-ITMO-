package ru.ifmo.commands;

import ru.ifmo.exceptions.IncorrectScriptInputException;
import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.models.StudyGroup;
import ru.ifmo.utils.Command;
import ru.ifmo.utils.StudyGroupInterrogator;

import java.time.LocalDateTime;

public class Add extends Command {
    private CollectionManager collectionManager;
    private StudyGroupInterrogator studyGroupInterrogator;

    public Add(CollectionManager collectionManager, StudyGroupInterrogator studyGroupInterrogator) {
        super("add {element}", "add new element to the collection");
        this.collectionManager = collectionManager;
        this.studyGroupInterrogator = studyGroupInterrogator;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgsInputException();
            collectionManager.addToCollecton(new StudyGroup(
                    collectionManager.generateID(),
                    studyGroupInterrogator.askName(),
                    studyGroupInterrogator.askCoordinates(),
                    LocalDateTime.now(),
                    studyGroupInterrogator.askStudentsCount(),
                    studyGroupInterrogator.askExpelledStudents(),
                    studyGroupInterrogator.askShouldBeExpelled(),
                    studyGroupInterrogator.askSemester(),
                    studyGroupInterrogator.askPerson()
            ));
            System.out.println("Group has been successfully added!");
            return true;
        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        } catch (IncorrectScriptInputException exception) {
        }
        return false;
    }

}
