import org.junit.Test;

public class MainClassTest {
    MainClass Main =new MainClass();
    int number = Main.getLocalNumber();

    @Test
    public void testGetLocalNumber(){
    if (number == 14){
        System.out.println("That`s OK");
    } else {
        System.out.println("The variable '" + number + "' is not equal to 14 translation");
    }
    }
}
