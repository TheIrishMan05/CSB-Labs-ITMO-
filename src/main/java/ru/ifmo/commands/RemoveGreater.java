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

public class RemoveGreater extends Command {
    private StudyGroupInterrogator studyGroupInterrogator;
    private CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager, StudyGroupInterrogator studyGroupInterrogator) {
        super("remove_greater {element}", "remove all elements where ID value is greater " +
                "than written element has.");
        this.collectionManager = collectionManager;
        this.studyGroupInterrogator = studyGroupInterrogator;
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
            collectionManager.removeGreater(studyGroupFromCollection);
            System.out.println("Группы успешно удалены!");
            return true;
        } catch (WrongArgsInputException exception) {
            System.out.println("Использование: '" + getName() + "'");
        } catch (EmptyCollectionException exception) {
            System.out.println("Коллекция пуста!");
        } catch (ElementNotFoundException exception) {
            System.out.println("Нет группы с такими параметрами в коллекции!");
        } catch (IncorrectScriptInputException exception) {
        }
        return false;
    }
}
