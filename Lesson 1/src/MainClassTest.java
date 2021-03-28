import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass Main = new MainClass();
    int local_number = Main.getLocalNumber();

    @Test
   public void testGetLocalNumber()
    {
        Assert.assertFalse("The 'local_number' variable != 14",local_number != 14);
    }

}
