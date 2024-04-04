package ru.ifmo.commands;

import ru.ifmo.exceptions.EmptyCollectionException;
import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.models.StudyGroup;
import ru.ifmo.utils.Command;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FilterGreaterThan extends Command {
    private CollectionManager collectionManager;

    public FilterGreaterThan(CollectionManager collectionManager) {
        super("filter_greater_that_should_be_expelled <shouldBeExpelled>",
                "print all elements where value of the field shouldBeExpelled is greater than written one.");

    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgsInputException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            Long shouldBeExpelledFilter = Long.parseLong(argument);
            TreeSet<StudyGroup> studyGroups = (TreeSet<StudyGroup>) filterByShouldBeExpelled(shouldBeExpelledFilter);
            if (studyGroups.isEmpty()) {
                System.out.println("Groups, where amount of students" +
                        " that should be expelled is more than " + shouldBeExpelledFilter + ", haven't been found .");
            } else {
                System.out.println("There are groups where" +
                        " amount of students that should be expelled is more than  " + shouldBeExpelledFilter + ": "
                        + studyGroups.size() + " шт.\n");
                for (StudyGroup studyGroup : studyGroups) {
                    System.out.println(studyGroup);
                }
            }
        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        } catch (EmptyCollectionException exception) {
            System.out.println("Collection is empty!");
        }
        return false;
    }

    private Set<StudyGroup> filterByShouldBeExpelled(Long shouldBeExpelled) {
        return collectionManager.getCollection().stream()
                .filter(studyGroup -> studyGroup.getShouldBeExpelled() > shouldBeExpelled)
                .collect(Collectors.toSet());
    }
}
