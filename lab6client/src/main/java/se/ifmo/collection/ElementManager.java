package se.ifmo.collection;

import se.ifmo.collection.exceptions.FieldIsNullException;
import se.ifmo.collection.models.*;
import se.ifmo.io.ConsoleInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Deque;
import java.util.function.Consumer;
import java.util.function.Function;

public class ElementManager {
    public static StudyGroup collect(Deque<String> requests) {
        try {
            StudyGroup studyGroup = new StudyGroup();

            while (!input(studyGroup::setName, Function.identity(), requests.pollFirst()));

            Coordinates coordinates = new Coordinates();

            while (!input(coordinates::setX, Long::parseLong, requests.pollFirst()));
            while (!input(coordinates::setY, Float::parseFloat, requests.pollFirst()));

            studyGroup.setCoordinates(coordinates);

            while (!input(studyGroup::setStudentsCount, Integer::parseInt, requests.pollFirst()));
            while (!input(studyGroup::setExpelledStudents, Integer::parseInt, requests.pollFirst()));
            while (!input(studyGroup::setShouldBeExpelled, Long::parseLong, requests.pollFirst()));
            while (!input(studyGroup::setSemesterEnum, Semester::valueOf, requests.pollFirst()));

            Person groupAdmin = new Person();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            while (!input(groupAdmin::setName, Function.identity(), requests.pollFirst()));
            while (!input(groupAdmin::setBirthday, s -> {
                try {
                    Date parsedDate = dateFormat.parse(s);
                    return parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }, requests.pollFirst()));
            while (!input(groupAdmin::setEyeColor, Color::valueOf, requests.pollFirst()));

            Location location = new Location();

            while (!input(location::setX, Long::parseLong, requests.pollFirst()));
            while (!input(location::setY, Integer::parseInt, requests.pollFirst()));
            while (!input(location::setName, Function.identity(), requests.pollFirst()));

            groupAdmin.setLocation(location);

            studyGroup.setGroupAdmin(groupAdmin);

            return studyGroup;
        } catch (Exception e) {
            return null;
        }
    }

    public static StudyGroup collect(ConsoleInterface consoleInterface) {
        try {
            StudyGroup studyGroup = new StudyGroup();

            while (!input("name", studyGroup::setName, s -> s, consoleInterface));

            Coordinates coordinates = new Coordinates();

            while (!input("x", coordinates::setX, Long::parseLong, consoleInterface));
            while (!input("y", coordinates::setY, Float::parseFloat, consoleInterface));

            studyGroup.setCoordinates(coordinates);

            while (!input("studentsCount", studyGroup::setStudentsCount, Integer::parseInt, consoleInterface));
            while (!input("expelledStudents", studyGroup::setExpelledStudents, Integer::parseInt, consoleInterface));
            while (!input("shouldBeExpelled", studyGroup::setShouldBeExpelled, Long::parseLong, consoleInterface));
            while (!input("semester" + Arrays.toString(Semester.values()), studyGroup::setSemesterEnum, Semester::valueOf, consoleInterface));

            Person groupAdmin = new Person();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            while (!input("name", groupAdmin::setName, Function.identity(), consoleInterface));
            while (!input("birthday", groupAdmin::setBirthday, s -> {
                try {
                    Date parsedDate = dateFormat.parse(s);
                    return parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }, consoleInterface));
            while (!input("color", groupAdmin::setEyeColor, Color::valueOf, consoleInterface));

            Location location = new Location();

            while (!input("x", location::setX, Long::parseLong, consoleInterface));
            while (!input("y", location::setY, Integer::parseInt, consoleInterface));
            while (!input("name", location::setName, Function.identity(), consoleInterface));

            studyGroup.setGroupAdmin(groupAdmin);
            return studyGroup;
        } catch (Throwable e) {
            consoleInterface.write(e.getMessage());
            return null;
        }
    }

    private static <K> boolean input(String fieldname, Consumer<K> setter,
                                     Function<String, K> parser, ConsoleInterface consoleInterface) throws Throwable {
        try {
            String line = consoleInterface.writeAndRead(" - %s ", fieldname);
            if (line == null || line.equals("return")) throw new Throwable("return is called");

            setter.accept(parser.apply(line));
            return true;
        } catch (Exception e) {
            consoleInterface.write(e.getMessage());
            return false;
        }
    }

    private static <K> boolean input(Consumer<K> setter, Function<String, K> parser, String value) throws Exception {
        try {
            if (value == null || value.equals("return")) throw new Exception("return is called");

            setter.accept(parser.apply(value));
            return true;
        } catch (FieldIsNullException | IllegalArgumentException e) {
            return false;
        }
    }
}
