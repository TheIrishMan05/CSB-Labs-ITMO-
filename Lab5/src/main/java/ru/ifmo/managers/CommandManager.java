package ru.ifmo.managers;

import ru.ifmo.exceptions.EmptyHistoryException;
import ru.ifmo.utils.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for managing all commands of app.
 */
public class CommandManager {
    private final int HISTORY_SIZE = 14;
    private List<Command> commands = new ArrayList<>();
    private String[] commandHistory = new String[HISTORY_SIZE];
    private Command add;
    private Command addIfMax;
    private Command clear;
    private Command countLessThan;
    private Command executeScript;
    private Command exit;
    private Command filterGreaterThan;
    private Command help;
    private Command history;
    private Command info;
    private Command printAscending;
    private Command removeById;
    private Command removeGreater;
    private Command removeLower;
    private Command save;
    private Command updateId;
    private Command show;

    public CommandManager(Command add, Command addIfMax, Command clear,
                          Command countLessThan, Command executeScript, Command exit, Command filterGreaterThan,
                          Command help, Command history, Command info, Command printAscending, Command removeById,
                          Command removeGreater, Command removeLower, Command save, Command updateId, Command show) {
        this.add = add;
        this.addIfMax = addIfMax;
        this.clear = clear;
        this.countLessThan = countLessThan;
        this.executeScript = executeScript;
        this.exit = exit;
        this.filterGreaterThan = filterGreaterThan;
        this.help = help;
        this.history = history;
        this.info = info;
        this.printAscending = printAscending;
        this.removeById = removeById;
        this.removeGreater = removeGreater;
        this.removeLower = removeLower;
        this.save = save;
        this.updateId = updateId;
        this.show = show;

        commands.add(add);
        commands.add(info);
        commands.add(addIfMax);
        commands.add(clear);
        commands.add(countLessThan);
        commands.add(executeScript);
        commands.add(exit);
        commands.add(filterGreaterThan);
        commands.add(help);
        commands.add(history);
        commands.add(printAscending);
        commands.add(removeById);
        commands.add(removeGreater);
        commands.add(removeLower);
        commands.add(save);
        commands.add(updateId);
        commands.add(show);
    }

    public List<Command> getCommands() {
        return commands;
    }

    public String[] getCommandHistory() {
        return commandHistory;
    }

    public void addToHistory(String commandToStore) {
        for (Command command : commands) {
            if (command.getName().split(" ")[0].equals(commandToStore)) {
                for (int i = HISTORY_SIZE - 1; i > 0; i--) {
                    commandHistory[i] = commandHistory[i - 1];
                }
            }
            commandHistory[0] = commandToStore;
        }
    }

    public boolean noSuchCommand(String command) {
        System.out.println("Command " + command + " isn't found. Type 'help' to get information about commands.");
        return false;
    }

    public boolean helpCommand(String argument) {
        if (help.execute(argument)) {
            for (Command command : commands) {
                System.out.println(command.getName() + ": " + command.getDescription());
            }
            return true;
        } else return false;
    }

    public boolean infoCommand(String argument) {
        return info.execute(argument);
    }

    public boolean showCommand(String argument) {
        return show.execute(argument);
    }

    public boolean addCommand(String argument) {
        return add.execute(argument);
    }

    public boolean exitCommand(String argument) {
        return exit.execute(argument);
    }

    public boolean clearCommand(String argument) {
        return clear.execute(argument);
    }

    public boolean addIfMaxCommand(String argument) {
        return addIfMax.execute(argument);
    }

    public boolean countLessThanCommand(String argument) {
        return countLessThan.execute(argument);
    }

    public boolean executeScriptCommand(String argument) {
        return executeScript.execute(argument);
    }

    public boolean filterGreaterThanCommand(String argument) {
        return filterGreaterThan.execute(argument);
    }

    public boolean historyCommand(String argument) {
        if (history.execute(argument)) {
            try {
                if (commandHistory.length == 0) throw new EmptyHistoryException();
                System.out.println("Last used commands:");
                for (int i = 0; i < commandHistory.length; i++) {
                    if (commandHistory[i] != null) System.out.println(" " + commandHistory[i]);
                }
                return true;
            } catch (EmptyHistoryException exception) {
                System.out.println("There is no used commands in this moment.");
            }
        }
        return false;
    }

    public boolean printAscendingCommand(String argument) {
        return printAscending.execute(argument);
    }

    public boolean removeByIdCommand(String argument) {
        return removeById.execute(argument);
    }

    public boolean removeGreaterCommand(String argument) {
        return removeGreater.execute(argument);
    }

    public boolean removeLowerCommand(String argument) {
        return removeLower.execute(argument);
    }

    public boolean saveCommand(String argument) {
        return save.execute(argument);
    }

    public boolean updateIdCommand(String argument) {
        return updateId.execute(argument);
    }
}
