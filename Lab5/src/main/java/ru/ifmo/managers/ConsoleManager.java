package ru.ifmo.managers;

import ru.ifmo.exceptions.ScriptRecursionException;
import ru.ifmo.utils.StudyGroupInterrogator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class for interaction with console
 */
public class ConsoleManager {
    private CommandManager commandManager;
    private Scanner userScanner;
    private StudyGroupInterrogator interrogator;
    private List<String> myScriptStack = new ArrayList<>();

    public ConsoleManager(CommandManager commandManager, Scanner userScanner, StudyGroupInterrogator interrogator) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.interrogator = interrogator;
    }


    /**
     * This method is responsible for interactive mode of program
     */
    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                System.out.print("$ ");
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandManager.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            System.out.println("User input hasn't been detected!");
        } catch (IllegalStateException exception) {
            System.out.println("Unpredictable error!");
        }
    }

    /**
     * This method is responsible for script mode of program
     *
     * @param argument (String)
     * @return Status of executed command
     */
    public int scriptMode(String argument) {
        String[] userCommand = {"", ""};
        int commandStatus;
        myScriptStack.add(argument);

        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = interrogator.getInputController().getUserScanner();
            interrogator.getInputController().setUserScanner(scriptScanner);
            interrogator.getInputController().setFileMode();

            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                System.out.println("$ " + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : myScriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());

            interrogator.getInputController().setUserScanner(tmpScanner);
            interrogator.getInputController().setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty())) {
                System.out.println("Check out that all data is valid!");
            }
            return commandStatus;
        } catch (FileNotFoundException exception) {
            System.out.println("File with script isn't found!");
        } catch (NoSuchElementException exception) {
            System.out.println("File with script is empty!");
        } catch (ScriptRecursionException exception) {
            System.out.println("Recursive execution of scripts is forbidden!");
        } catch (IllegalStateException exception) {
            System.out.println("Unpredictable error!");
            System.exit(0);
        } finally {
            myScriptStack.remove(myScriptStack.size() - 1);
        }
        return 1;
    }


    /**
     * This method is responsible for launching commands
     *
     * @param userCommand (String array)
     * @return Integer flag of command's execution status
     */
    public int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.helpCommand(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandManager.addCommand(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.showCommand(userCommand[1])) return 1;
                break;
            case "add_if_max":
                if (!commandManager.addIfMaxCommand(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clearCommand(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandManager.saveCommand(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScriptCommand(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "count_less_than_semester_enum":
                if (!commandManager.countLessThanCommand(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commandManager.exitCommand(userCommand[1])) return 1;
                else return 2;
            case "filter_greater_than":
                if (!commandManager.filterGreaterThanCommand(userCommand[1])) return 1;
                break;
            case "history":
                if (!commandManager.historyCommand(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandManager.infoCommand(userCommand[1])) return 1;
                break;
            case "print_ascending":
                if (!commandManager.printAscendingCommand(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.removeByIdCommand(userCommand[1])) return 1;
                break;
            case "remove_greater":
                if (!commandManager.removeGreaterCommand(userCommand[1])) return 1;
                break;
            case "remove_lower":
                if (!commandManager.removeLowerCommand(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandManager.updateIdCommand(userCommand[1])) return 1;
                break;
            default:
                if (!commandManager.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }

}
