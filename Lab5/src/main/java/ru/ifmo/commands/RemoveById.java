package ru.ifmo.commands;

import ru.ifmo.exceptions.ElementNotFoundException;
import ru.ifmo.exceptions.EmptyCollectionException;
import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.models.StudyGroup;
import ru.ifmo.utils.Command;

public class RemoveById extends Command {
    private CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id <ID>", "remove element by written ID");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgsInputException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            long ID = Long.parseLong(argument);
            StudyGroup studyGroupToRemove = collectionManager.getById(ID);
            if (studyGroupToRemove == null) throw new ElementNotFoundException();
            collectionManager.removeFromCollection(studyGroupToRemove);
            System.out.println("Group has been deleted successfully!");
            return true;
        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        } catch (EmptyCollectionException exception) {
            System.out.println("Collection is empty!");
        } catch (NumberFormatException exception) {
            System.out.println("ID must be a number!");
        } catch (ElementNotFoundException exception) {
            System.out.println("There is no group with written ID!");
        }
        return false;
    }
}
