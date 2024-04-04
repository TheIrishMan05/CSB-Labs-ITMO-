package ru.ifmo.utils;

import java.util.Scanner;

public class InputController {
    private Scanner userScanner;
    private boolean fileMode;

    public InputController(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    public Scanner getUserScanner() {
        return userScanner;
    }

    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    public String nextLine() {
        try {
            return userScanner.nextLine();
        } catch (Exception ex) {
            System.exit(0);
            return null;
        }
    }

    public void setFileMode() {
        fileMode = true;
    }

    public void setUserMode() {
        fileMode = false;
    }

    public boolean getMode() {
        return fileMode;
    }
}
