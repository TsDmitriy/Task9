import org.junit.Test;


public class TestTask8 extends TestBase{


    @Test
    public void test() throws Exception {
        new GoToMainPage().getpage()
                .checkStickerPtoduct();
    }


}