import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass Main = new MainClass();
    int local_number = Main.getLocalNumber();
    int class_number = Main.getClassNumber();

    @Test
   public void testGetLocalNumber()
    {
        Assert.assertFalse("The 'local_number' variable != 14!",local_number != 14);
    }

    @Test
    public void testGetClassNumber()
    {
        Assert.assertTrue("The 'class_number' variable <= 45!",class_number > 45);
    }

}
