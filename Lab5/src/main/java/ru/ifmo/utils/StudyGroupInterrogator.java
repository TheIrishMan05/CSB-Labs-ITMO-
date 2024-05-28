package ru.ifmo.utils;

import ru.ifmo.exceptions.ElementOutOfBoundsException;
import ru.ifmo.exceptions.IncorrectScriptInputException;
import ru.ifmo.models.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.NoSuchElementException;
import java.util.Random;


public class StudyGroupInterrogator {

    private final long MIN_SHOULD_BE_EXPELLED = 1;
    private final int MIN_EXPELLED = 1;
    private final int MIN_COUNT_OF_STUDENTS = 1;
    private InputController inputController;
    private CoordinatesChecker coordinatesChecker;
    private LocationChecker locationChecker;
    private PersonChecker personChecker;

    public StudyGroupInterrogator(InputController inputController, CoordinatesChecker coordinatesChecker,
                                  LocationChecker locationChecker, PersonChecker personChecker) {
        this.inputController = inputController;
        this.coordinatesChecker = coordinatesChecker;
        this.locationChecker = locationChecker;
        this.personChecker = personChecker;
    }

    public InputController getInputController() {
        return inputController;
    }

    public CoordinatesChecker getCoordinatesChecker() {
        return coordinatesChecker;
    }

    public LocationChecker getLocationChecker() {
        return locationChecker;
    }

    public PersonChecker getPersonChecker() {
        return personChecker;
    }

    public String askName() throws IncorrectScriptInputException {
        String name;
        while (true) {
            try {
                System.out.println("Type name:");
                System.out.print("> ");
                name = getInputController().nextLine().trim();
                if (getInputController().getMode()) System.out.println(name);
                if (name.equals("")) throw new IllegalArgumentException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: element haven't been found");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (IllegalArgumentException exception) {
                System.out.println("Error: name can't be an empty string or null");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return name;
    }

    public Coordinates askCoordinates() throws IncorrectScriptInputException {
        Long x;
        float y;
        x = getCoordinatesChecker().askX();
        y = getCoordinatesChecker().askY();
        return new Coordinates(x, y);
    }

    public Integer askStudentsCount() throws IncorrectScriptInputException {
        String strStudentsCount;
        Integer studentsCount;
        while (true) {
            try {
                System.out.println("Type amount of students:");
                System.out.print("> ");
                strStudentsCount = getInputController().nextLine().trim();
                if (inputController.getMode()) System.out.println(strStudentsCount);
                studentsCount = Integer.parseInt(strStudentsCount);
                if (studentsCount < MIN_COUNT_OF_STUDENTS) throw new ElementOutOfBoundsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: amount of students haven't been defined.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (NumberFormatException exception) {
                System.out.println("Error: amount of students must be a number.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (ElementOutOfBoundsException exception) {
                System.out.println("Error: amount of students must be positive and integer.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return studentsCount;
    }

    public Integer askExpelledStudents() throws IncorrectScriptInputException {
        String strExpelledStudents;
        Integer expelledStudents;
        while (true) {
            try {
                System.out.println("Type amount of expelled students:");
                System.out.print("> ");
                strExpelledStudents = getInputController().nextLine().trim();
                if (inputController.getMode()) System.out.println(strExpelledStudents);
                expelledStudents = Integer.parseInt(strExpelledStudents);
                if (expelledStudents < MIN_EXPELLED) throw new ElementOutOfBoundsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: amount of expelled students haven't been defined.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (NumberFormatException exception) {
                System.out.println("Error: amount of expelled students must be a number.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (ElementOutOfBoundsException exception) {
                System.out.println("Error: amount of expelled students must be positive and integer.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return expelledStudents;
    }

    public Long askShouldBeExpelled() throws IncorrectScriptInputException {
        String strShouldBeExpelled;
        Long shouldBeExpelled;
        while (true) {
            try {
                System.out.println("Type amount of students that should be expelled:");
                System.out.print("> ");
                strShouldBeExpelled = getInputController().nextLine().trim();
                if (getInputController().getMode()) System.out.println(strShouldBeExpelled);
                shouldBeExpelled = Long.parseLong(strShouldBeExpelled);
                if (shouldBeExpelled < MIN_SHOULD_BE_EXPELLED) throw new ElementOutOfBoundsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: amount of 'expelling candidates' haven't been defined.");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (NumberFormatException exception) {
                System.out.println("Error: amount of 'expelling candidates' must be a number.");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (ElementOutOfBoundsException exception) {
                System.out.println("Error: amount of 'expelling candidates' must be positive and integer.");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return shouldBeExpelled;
    }

    public Semester askSemester() throws IncorrectScriptInputException {
        String strSemester;
        Semester semester;
        Semester[] semesters = Semester.values();
        while (true) {
            try {
                System.out.println("List of semesters:");
                for (Semester semester1 : semesters) {
                    System.out.println(semester1);
                }
                System.out.println("Type semester:");
                System.out.print("> ");
                strSemester = getInputController().nextLine().trim();
                if (inputController.getMode()) System.out.println(strSemester);
                semester = Semester.valueOf(strSemester.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: semester haven't been defined.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (IllegalArgumentException exception) {
                System.out.println("Error: list doesn't contain this semester.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return semester;
    }

    public Location askLocation() throws IncorrectScriptInputException {
        Long x = getLocationChecker().askLocationX();
        int y = getLocationChecker().askLocationY();
        String name = getLocationChecker().askLocationName();
        return new Location(x, y, name);
    }

    public Person askPerson() throws IncorrectScriptInputException {
        String name = getPersonChecker().askPersonName();
        int upBound = (int) LocalDate.of(2006, Month.JANUARY, 1).toEpochDay();
        int lowBound = (int) LocalDate.of(1999, Month.APRIL, 10).toEpochDay();
        Random random = new Random();
        long randomDay = lowBound + random.nextInt(upBound - lowBound);
        LocalDate birthday = LocalDate.ofEpochDay(randomDay);
        Color eyeColor = getPersonChecker().askColor();
        Location location = askLocation();
        return new Person(name, birthday, eyeColor, location);
    }

    public boolean answerQuestion(String question) throws IncorrectScriptInputException {
        String finalQuestion = question + " (y/n): ";
        String answer;
        while (true) {
            try {
                System.out.println(finalQuestion);
                System.out.print("> ");
                answer = getInputController().getUserScanner().nextLine().trim();
                if (getInputController().getMode()) System.out.println(answer);
                if (!answer.equals("y") && !answer.equals("n")) throw new ElementOutOfBoundsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: answer haven't been defined");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (ElementOutOfBoundsException exception) {
                System.out.println("Error: answer must be written as 'y' or 'n'.");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return answer.equals("y");
    }
}