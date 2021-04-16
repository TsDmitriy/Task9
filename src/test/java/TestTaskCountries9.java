import org.junit.Test;


public class TestTaskCountries9 extends TestBase{


    @Test
    public void test() throws Exception {
        new LoginPage().inputLoginAndPassword()
                .goToCountriesPage()
                .getAllCountryName()
                .checkOrderByNameCountry()
                .getAllZones()
                .goToPageWhereZoneMoreZeroCheckOrderByCode();
    }


}