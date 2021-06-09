import Controller.CardController;
import View.LoginMenu;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class mainTest {

    @Test
    @Order(1)
    public void createUser() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new LoginMenu().run("user create --username reza --nickname rezax --password 222");
        new LoginMenu().run("user create --username ali --nickname ali --password 222");
        String outputText = outContent.toString();
        Assertions.assertEquals("user created successfully!\nuser created successfully!\n",outputText);
    }

    @Test
    @Order(2)
    public void createUserError1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new LoginMenu().run("user create --username reza --nickname rezax --password 222");

        String outputText = outContent.toString();
        Assertions.assertEquals("user with username reza already exists\n",outputText);
    }

    @Test
    @Order(3)
    public void createUserError2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new LoginMenu().run("user create --username reza2 --nickname rezax --password 222");
        String outputText = outContent.toString();
        Assertions.assertEquals("user with nickname rezax already exists\n",outputText);
    }

    @Test
    @Order(4)
    public void loginUserError1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new LoginMenu().run("user login --username mamad --password 222");
        String outputText = outContent.toString();
        Assertions.assertEquals("Username and password didn't match!\n",outputText);
    }

    @Test
    @Order(5)
    public void loginUserError2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new LoginMenu().run("user login --username ali --password 2222");
        String outputText = outContent.toString();
        Assertions.assertEquals("Username and password didn't match!\n",outputText);
    }

    @Test
    @Order(6)
    public void loginUser() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        CardController.initialCards();
        new LoginMenu().run("user login --username ali --password 222");
        String outputText = outContent.toString();
        Assertions.assertEquals("user logged in successfully!\n",outputText);
    }


}
