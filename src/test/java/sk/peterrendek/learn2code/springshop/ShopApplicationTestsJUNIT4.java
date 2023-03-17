package sk.peterrendek.learn2code.springshop;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShopApplicationTestsJUNIT4 {
    @Test
    public void name() {
        int a = 5;
        int b = 3;
        Assert.assertEquals(8,a+b);
    }

}
