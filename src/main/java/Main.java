import Controller.CardController;
import Controller.ProgramController;
import View.MainMenu;


public class Main {

    public static void main(String[] args) {
        CardController.initialCards();
        MainMenu mainMenu = new MainMenu();
        mainMenu.menu();
    }


}
