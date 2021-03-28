import org.junit.Test;

public class MainClassTest {
    MainClass Main = new MainClass();
    int local_number = Main.getLocalNumber();
    int c_number = Main.getClassNumber();

    @Test
   public void testGetLocalNumber(){
        if (local_number == 14){
            anExpectedResult("The variable '" + local_number + "' is equal to 14");
        } else {
            anErrorMessage("The variable '" + local_number + "' is equal to 14");
        }
    }

    @Test
    public void testGetClassNumber(){
        if (c_number > 45){
            anExpectedResult("OK");
        } else if (c_number <= 45){
            anErrorMessage("Error: " + c_number + " - is not more than 45");
        }
    }

    private int anErrorMessage(String error_message){
        System.out.println(error_message);
        return 0;
    }

    private int anExpectedResult(String expected_message){
        System.out.println(expected_message);
        return 0;
    }
}
