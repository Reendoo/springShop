package sk.peterrendek.learn2code.springshop;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestJUnitIV {
    @Test
    public void name() {
        int a = 5;
        int b = 3;
        Assert.assertEquals(8,a+b);
    }

}
