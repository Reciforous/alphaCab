package com.org.Helpers;

// Class for pretty-fying SysOut prints
public class Crayons {
    // ANSI escape codes
    private static final String BOLD = "\033[0;1m";
    private static final String RESET = "\u001B[0m";

    // Normal Colors
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    // Brighter colors
    public static final String BRIGHT_BLACK = "\033[0;90m";
    private static final String BRIGHT_RED = "\033[0;91m";
    private static final String BRIGHT_GREEN = "\033[0;92m";
    public static final String BRIGHT_YELLOW = "\033[0;93m";
    private static final String BRIGHT_BLUE = "\033[0;94m";
    public static final String BRIGHT_PURPLE = "\033[0;95m";
    public static final String BRIGHT_CYAN = "\033[0;96m";
    public static final String BRIGHT_WHITE = "\033[0;97m";

    public static String red(String string){
        return RED + string + RESET;
    }
    public static String red(String string, boolean bold){
        if(bold){
            return RED + BOLD + string + RESET;
        }
        else{
            return red(string);
        }
    }

    public static String brightRed(String string){
        return BRIGHT_RED + string + RESET;
    }
    public static String brightRed(String string, boolean bold){
        if(bold){
            return BRIGHT_RED + BOLD + string + RESET;
        }
        else{
            return brightRed(string);
        }
    }

    public static String green(String string){
        return GREEN + string + RESET;
    }
    public static String green(String string, boolean bold){
        if(bold){
            return GREEN + BOLD + string + RESET;
        }
        else{
            return green(string);
        }
    }

    public static String brightGreen(String string){
        return BRIGHT_GREEN + string + RESET;
    }
    public static String brightGreen(String string, boolean bold){
        if(bold){
            return BRIGHT_GREEN + BOLD + string + RESET;
        }
        else{
            return brightGreen(string);
        }
    }

    public static String blue(String string){
        return BLUE + string + RESET;
    }
    public static String blue(String string, boolean bold){
        if(bold){
            return BLUE + BOLD + string + RESET;
        }
        else{
            return blue(string);
        }
    }

    public static String brightBlue(String string){
        return BRIGHT_BLUE + string + RESET;
    }
    public static String brightBlue(String string, boolean bold){
        if(bold){
            return BRIGHT_BLUE + BOLD + string + RESET;
        }
        else{
            return brightBlue(string);
        }
    }

    public static String purple(String string){
        return PURPLE + string + RESET;
    }
    public static String purple(String string, boolean bold){
        if(bold){
            return PURPLE + BOLD + string + RESET;
        }
        else{
            return purple(string);
        }
    }

    public static String brightPurple(String string){
        return BRIGHT_PURPLE + string + RESET;
    }
    public static String brightPurple(String string, boolean bold){
        if(bold){
            return BRIGHT_PURPLE + BOLD + string + RESET;
        }
        else{
            return brightPurple(string);
        }
    }

    public static String cyan(String string){
        return CYAN + string + RESET;
    }
    public static String cyan(String string, boolean bold){
        if(bold){
            return CYAN + BOLD + string + RESET;
        }
        else{
            return cyan(string);
        }
    }

    public static String brightCyan(String string){
        return BRIGHT_CYAN + string + RESET;
    }
    public static String brightCyan(String string, boolean bold){
        if(bold){
            return BRIGHT_CYAN + BOLD + string + RESET;
        }
        else{
            return brightCyan(string);
        }
    }

    public static String yellow(String string){
        return YELLOW + string + RESET;
    }
    public static String yellow(String string, boolean bold){
        if(bold){
            return YELLOW + BOLD + string + RESET;
        }
        else{
            return yellow(string);
        }
    }

    public static String brightYellow(String string){
        return BRIGHT_YELLOW + string + RESET;
    }
    public static String brightYellow(String string, boolean bold){
        if(bold){
            return BRIGHT_YELLOW + BOLD + string + RESET;
        }
        else{
            return brightYellow(string);
        }
    }

    public static String white(String string){
        return WHITE + string + RESET;
    }
    public static String white(String string, boolean bold){
        if(bold) {
            return WHITE + BOLD + string + RESET;
        }
        else{
            return white(string);
        }
    }

    public static String brightWhite(String string){
        return BRIGHT_WHITE + string + RESET;
    }
    public static String brightWhite(String string, boolean bold){
        if(bold) {
            return BRIGHT_WHITE + BOLD + string + RESET;
        }
        else{
            return brightWhite(string);
        }
    }

    public static String black(String string){
        return BLACK + string + RESET;
    }
    public static String black(String string, boolean bold){
        if(bold) {
            return BLACK + BOLD + string + RESET;
        }
        else{
            return black(string);
        }
    }

    public static String brightBlack(String string){
        return BRIGHT_BLACK + string + RESET;
    }
    public static String brightBlack(String string, boolean bold){
        if(bold) {
            return BRIGHT_BLACK + BOLD + string + RESET;
        }
        else{
            return brightBlack(string);
        }
    }
}
