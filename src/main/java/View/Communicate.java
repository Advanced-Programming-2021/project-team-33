package View;

import Controller.Util;

public class Communicate {
    public static void output(String output) {
        System.out.print(output + "\n");
    }
    public static String input(String output) {
        System.out.print(output + "\n");
        return Util.scanner.nextLine();
    }
}
