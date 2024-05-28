package ru.ifmo.commands;

import ru.ifmo.exceptions.IncorrectScriptInputException;
import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.models.StudyGroup;
import ru.ifmo.utils.Command;
import ru.ifmo.utils.StudyGroupInterrogator;

import java.time.LocalDateTime;

public class AddIfMax extends Command {
    private CollectionManager collectionManager;
    private StudyGroupInterrogator studyGroupInterrogator;

    public AddIfMax(CollectionManager collectionManager, StudyGroupInterrogator studyGroupInterrogator) {
        super("add_if_max {element}", "add new element if its ID is greater than element with max one.");
        this.collectionManager = collectionManager;
        this.studyGroupInterrogator = studyGroupInterrogator;
    }

    @Override
    public boolean execute(String argument) {
        try {

            if (!argument.isEmpty()) throw new WrongArgsInputException();
            StudyGroup studyGroupToAdd = new StudyGroup(
                    collectionManager.generateID(),
                    studyGroupInterrogator.askName(),
                    studyGroupInterrogator.askCoordinates(),
                    LocalDateTime.now(),
                    studyGroupInterrogator.askStudentsCount(),
                    studyGroupInterrogator.askExpelledStudents(),
                    studyGroupInterrogator.askShouldBeExpelled(),
                    studyGroupInterrogator.askSemester(),
                    studyGroupInterrogator.askPerson()
            );

            if (collectionManager.collectionSize() == 0 || studyGroupToAdd.compareTo(collectionManager.getLast()) > 0) {
                System.out.println("Group has been successfully added!");
                return true;
            } else {
                System.out.println("The ID of the group is less than max element!");
            }


        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        } catch (IncorrectScriptInputException ignored) {
        }


        return false;
    }

}
