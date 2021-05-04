package View;

import Controller.Util;

public class Communicate {
    public static void output(String output) {
        System.out.println(output);
    }
    public static String input(String output) {
        System.out.println(output);
        return Util.scanner.nextLine();
    }
}
