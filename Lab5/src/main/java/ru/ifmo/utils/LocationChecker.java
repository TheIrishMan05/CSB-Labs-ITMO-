package ru.ifmo.utils;

import ru.ifmo.exceptions.IncorrectScriptInputException;

import java.util.NoSuchElementException;

public class LocationChecker {
    private final long MAX_NAME_LENGTH = 992;
    private InputController inputController;

    public LocationChecker(InputController inputController) {
        this.inputController = inputController;
    }

    public InputController getInputController() {
        return inputController;
    }

    public String askLocationName() throws IncorrectScriptInputException {
        String locationName;
        while (true) {
            try {
                System.out.println("Type location name:");
                System.out.print("> ");
                locationName = getInputController().nextLine().trim();
                if (getInputController().getMode()) System.out.println(locationName);
                if (locationName.equals("") || locationName.length() > MAX_NAME_LENGTH)
                    throw new IllegalArgumentException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: element haven't been found");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (IllegalArgumentException exception) {
                System.out.println("Error: name can't be an empty string, null or string with length greater than 992");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return locationName;
    }

    public long askLocationX() throws IncorrectScriptInputException {
        String strLocationX;
        long locationX;
        while (true) {
            try {
                System.out.println("Type X coordinate of location:");
                System.out.print("> ");
                strLocationX = getInputController().nextLine().trim();
                if (inputController.getMode()) System.out.println(strLocationX);
                locationX = Long.parseLong(strLocationX);
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: coordinate X haven't been defined.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (NumberFormatException exception) {
                System.out.println("Error: coordinate X must be a number.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return locationX;
    }

    public int askLocationY() throws IncorrectScriptInputException {
        String strLocationY;
        int locationY;
        while (true) {
            try {
                System.out.println("Type Y coordinate of location:");
                System.out.print("> ");
                strLocationY = getInputController().nextLine().trim();
                if (inputController.getMode()) System.out.println(strLocationY);
                locationY = Integer.parseInt(strLocationY);
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: coordinate Y haven't been defined.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (NumberFormatException exception) {
                System.out.println("Error: coordinate Y must be a number.");
                if (inputController.getMode()) throw new IncorrectScriptInputException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return locationY;
    }
}
