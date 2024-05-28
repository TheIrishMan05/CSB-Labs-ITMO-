package ru.ifmo.utils;

import ru.ifmo.exceptions.IncorrectScriptInputException;
import ru.ifmo.models.Color;

import java.util.NoSuchElementException;

public class PersonChecker {
    private InputController inputController;

    public PersonChecker(InputController inputController) {
        this.inputController = inputController;
    }

    public InputController getInputController() {
        return inputController;
    }

    public Color askColor() throws IncorrectScriptInputException {
        String strColor;
        Color color;
        Color[] colors = Color.values();
        while (true) {
            try {
                System.out.println("List of the eyes' colors:");
                for (Color color1 : colors) {
                    System.out.println(color1);
                }
                System.out.println("Type eyes' color:");
                System.out.print("> ");
                strColor = getInputController().nextLine().trim();
                if (getInputController().getMode()) System.out.println(strColor);
                color = Color.valueOf(strColor.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: eyes' color haven't been defined");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (IllegalArgumentException exception) {
                System.out.println("Error: there is no such color in the list");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return color;
    }

    public String askPersonName() throws IncorrectScriptInputException {
        String personName;
        while (true) {
            try {
                System.out.println("Type name of the group admin:");
                System.out.print("> ");
                personName = getInputController().nextLine().trim();
                if (getInputController().getMode()) System.out.println(personName);
                if (personName.equals("")) throw new IllegalArgumentException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Error: element isn't found");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (IllegalArgumentException exception) {
                System.out.println("Error: name can't be an empty string or null");
                if (getInputController().getMode()) throw new IncorrectScriptInputException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println("Unpredictable error!");
                System.exit(0);
            }
        }
        return personName;
    }
}
