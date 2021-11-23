package com.samsalek.activityjournal.util.console;

public class DebugConsole {

    private static int row = 0;

    public static void regular(String text) {
        print(text, "");
    }

    public static void success(String text) {
        print(text, ConsoleColor.GREEN_BOLD);
    }

    public static void notify(String text) {
        print(text, ConsoleColor.CYAN);
    }

    public static void warning(String text) {
        print(text, ConsoleColor.YELLOW);
    }

    public static void error(String text) {
        print(text, ConsoleColor.RED_BOLD);
    }

    private static void print(String text, String color) {
        if(row == 0) {
            System.out.println();
        }
        System.out.println(row + " : " + color + text + ConsoleColor.RESET);
        row++;
    }
}
