package Controller;

public class Util {
    private static String ERROR = "0";
    private static String SUCCESS = "1";

    public static String showError(String message) {
        return ERROR + " " + message;
    }

    public static String success(String message) {
        return SUCCESS + " " + message;
    }
}
