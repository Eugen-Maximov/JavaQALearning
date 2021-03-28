import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass Main = new MainClass();
    int local_number = Main.getLocalNumber();
    int class_number = Main.getClassNumber();
    String class_string = Main.getClassString();

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

    @Test
    public void testGetClassString()
    {
        boolean a = true;
        if (class_string.contains("Hello") | class_string.contains("hello")){
            a = true;
        } else {
            a = false;
            }
        Assert.assertTrue("There is no 'Hello' or ''hello' substring in the string",a == true);
    }
}
