package io.github.ndys.patto.utils;

public class MenuUtils {

    /**
     * Prints an ASCII box with the given menu path/title
     * Example:
     * +----------------------------+
     * | Main Menu > SubMenu        |
     * +----------------------------+
     */
    public static void printHeader(String menuPath) {
        String line = "+".repeat(menuPath.length() + 4);
        System.out.println("\n" + line);
        System.out.println("| " + menuPath + " |");
        System.out.println(line);
    }
}
