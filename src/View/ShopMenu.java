package View;

import Controller.Util;

public class ShopMenu {

    boolean checked = false;
    public void run(String input) {
        checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        CardMenu.showCard(Util.getCommand(input, "card show (.+)"));

    }
}
