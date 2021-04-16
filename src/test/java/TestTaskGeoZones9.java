import org.junit.Test;


public class TestTaskGeoZones9 extends TestBase{


    @Test
    public void test() throws Exception {
        new LoginPage().inputLoginAndPassword()
                .goToGeoZonesPage()
                .getAllPage()
                .goToPageCountryAndCheckOrderByName();


    }


}