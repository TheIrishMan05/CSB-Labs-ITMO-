package ru.ifmo.commands;

import ru.ifmo.exceptions.ElementNotFoundException;
import ru.ifmo.exceptions.EmptyCollectionException;
import ru.ifmo.exceptions.IncorrectScriptInputException;
import ru.ifmo.exceptions.WrongArgsInputException;
import ru.ifmo.managers.CollectionManager;
import ru.ifmo.models.Coordinates;
import ru.ifmo.models.Person;
import ru.ifmo.models.Semester;
import ru.ifmo.models.StudyGroup;
import ru.ifmo.utils.Command;
import ru.ifmo.utils.StudyGroupInterrogator;

import java.time.LocalDateTime;

public class UpdateId extends Command {
    private CollectionManager collectionManager;
    private StudyGroupInterrogator studyGroupInterrogator;

    public UpdateId(CollectionManager collectionManager, StudyGroupInterrogator studyGroupInterrogator) {
        super("update <ID> {element}", "update value of the element by its ID");
        this.collectionManager = collectionManager;
        this.studyGroupInterrogator = studyGroupInterrogator;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgsInputException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            long ID = Long.parseLong(argument);
            StudyGroup formerStudyGroup = collectionManager.getById(ID);
            if (formerStudyGroup == null) throw new ElementNotFoundException();

            String name = formerStudyGroup.getName();
            Coordinates coordinates = formerStudyGroup.getCoordinates();
            LocalDateTime creationDate = formerStudyGroup.getCreationDate();
            Integer studentsCount = formerStudyGroup.getStudentsCount();
            Integer expelledStudents = formerStudyGroup.getExpelledStudents();
            Long shouldBeExpelled = formerStudyGroup.getShouldBeExpelled();
            Semester semester = formerStudyGroup.getSemesterEnum();
            Person groupAdmin = formerStudyGroup.getGroupAdmin();

            collectionManager.removeFromCollection(formerStudyGroup);

            if (studyGroupInterrogator.answerQuestion("Do you want to change group name?"))
                name = studyGroupInterrogator.askName();
            if (studyGroupInterrogator.answerQuestion("Do you want to change group coordinates?"))
                coordinates = studyGroupInterrogator.askCoordinates();
            if (studyGroupInterrogator.answerQuestion("Do you want to change amount of students in the group?"))
                studentsCount = studyGroupInterrogator.askStudentsCount();
            if (studyGroupInterrogator.answerQuestion("Do you want to change amount of expelled students?"))
                expelledStudents = studyGroupInterrogator.askExpelledStudents();
            if (studyGroupInterrogator.answerQuestion("Do you want to change amount of students that should be expelled?"))
                shouldBeExpelled = studyGroupInterrogator.askShouldBeExpelled();
            if (studyGroupInterrogator.answerQuestion("Do you want to change the current semester of the group?"))
                semester = studyGroupInterrogator.askSemester();
            if (studyGroupInterrogator.answerQuestion("Do you want to change the admin of a group?"))
                groupAdmin = studyGroupInterrogator.askPerson();
            collectionManager.addToCollecton(new StudyGroup(
                    ID,
                    name,
                    coordinates,
                    creationDate,
                    studentsCount,
                    expelledStudents,
                    shouldBeExpelled,
                    semester,
                    groupAdmin
            ));
            System.out.println("Group have been successfully updated successfully!");
            return true;
        } catch (WrongArgsInputException exception) {
            System.out.println("Using of: '" + getName() + "'");
        } catch (EmptyCollectionException exception) {
            System.out.println("Collection is empty!");
        } catch (NumberFormatException exception) {
            System.out.println("ID must be a number!");
        } catch (ElementNotFoundException exception) {
            System.out.println("There is no group with such ID!");
        } catch (IncorrectScriptInputException exception) {
        }
        return false;
    }
}
