package ru.ifmo.commands;

import ru.ifmo.exceptions.ElementNotFoundException;
import ru.ifmo.exceptions.EmptyCollectionException;
import ru.ifmo.exceptions.IncorrectScriptInputException;
import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.models.StudyGroup;
import ru.ifmo.utils.Command;
import ru.ifmo.utils.StudyGroupInterrogator;

import java.time.LocalDateTime;

public class RemoveLower extends Command {
    private StudyGroupInterrogator studyGroupInterrogator;
    private CollectionManager collectionManager;

    public RemoveLower(CollectionManager collectionManager, StudyGroupInterrogator studyGroupInterrogator) {
        super("remove_lower {element}", "remove all elements from the collection" +
                " where value of ID is less than written element has.");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgsInputException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            StudyGroup studyGroupToFind = new StudyGroup(
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
            StudyGroup studyGroupFromCollection = collectionManager.getByValue(studyGroupToFind);
            if (studyGroupFromCollection == null) throw new ElementNotFoundException();
            collectionManager.removeLower(studyGroupFromCollection);
            System.out.println("Groups have been deleted successfully!");
            return true;
        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        } catch (EmptyCollectionException exception) {
            System.out.println("Collection is empty!");
        } catch (ElementNotFoundException exception) {
            System.out.println("There is no element with such parameters in collection!");
        } catch (IncorrectScriptInputException exception) {
        }
        return false;
    }
}
