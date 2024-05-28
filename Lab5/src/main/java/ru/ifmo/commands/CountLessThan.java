package ru.ifmo.commands;

import ru.ifmo.exceptions.EmptyCollectionException;
import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.models.Semester;
import ru.ifmo.utils.Command;

public class CountLessThan extends Command {
    private CollectionManager collectionManager;

    public CountLessThan(CollectionManager collectionManager) {
        super("count_less_than_semester_enum <semesterEnum>", "print amount of elements" +
                " where number of semester is less than written one.");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {

        try {
            if (argument.isEmpty()) throw new WrongArgsInputException();

            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            Semester semester = Semester.valueOf(argument.toUpperCase());
            Integer count = collectionManager.getCountOfStudyGroupsLS(semester);
            if (count != 0) {
                System.out.println(count);
                return true;
            } else System.out.println("There are no groups with semester number less than written one!");


        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        } catch (EmptyCollectionException exception) {
            System.out.println("Collection is empty!");
        } catch (IllegalArgumentException exception) {
            System.out.println("This semester is not in the list!");

            System.out.println("List of semesters:");
            Semester[] semesters = Semester.values();
            for (Semester semester : semesters) {
                System.out.println(semester.name());
            }
        }
        return false;
    }
}
