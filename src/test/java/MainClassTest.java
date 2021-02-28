import org.junit.Assert;
import org.junit.Test;


public class MainClassTest extends MainClass
{
    @Test
    public void testGetClassString()
    {
        String string = this.getClassString();
        String substring = "hello";

            Assert.assertTrue("Метод getClassString возвращает строку, в которой нет подстроки “hello” " +
                    "или “Hello”!!!",string.toUpperCase().contains(substring.toUpperCase()));

    }

}
