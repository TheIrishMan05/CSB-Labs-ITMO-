package ru.ifmo.utils;

import ru.ifmo.exceptions.IncorrectScriptInputException;

import java.util.NoSuchElementException;

public class CoordinatesChecker {
    private InputController inputController;

    public CoordinatesChecker(InputController inputController) {
        this.inputController = inputController;
    }

    public InputController getInputController() {
        return inputController;
    }

    public Long askX() throws IncorrectScriptInputException {
        String strX;
        Long x;
        while (true) {
            try {
                System.out.println("Type X coordinate:");
                System.out.print("> ");
                strX = getInputController().nextLine().trim();
                if (getInputController().getMode()) System.out.println(strX);
                x = Long.parseLong(strX);
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: coordinate X haven't been defined.");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (NumberFormatException exception) {
                System.out.println("Error: coordinate X must be a number.");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return x;
    }

    public float askY() throws IncorrectScriptInputException {
        String strY;
        float y;
        while (true) {
            try {
                System.out.println("Type Y coordinate:");
                System.out.print("> ");
                strY = getInputController().nextLine().trim();
                if (getInputController().getMode()) System.out.println(strY);
                y = Float.parseFloat(strY);
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: coordinate Y haven't been defined.");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (NumberFormatException exception) {
                System.out.println("Error: coordinate Y must be a number.");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (IllegalStateException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }
}
