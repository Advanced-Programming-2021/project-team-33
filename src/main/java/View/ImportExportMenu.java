package View;

import Controller.Util;

public class ImportExportMenu {
    boolean checked = false;
    public void run(String input) {
        checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));

    }

}
