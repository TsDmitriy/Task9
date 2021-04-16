import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage {

    private By countries = By.xpath("//*[@href=\"http://localhost/litecart/admin/?app=countries&doc=countries\"]");
    private By geoZones = By.xpath("//*[@href=\"http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones\"]");

    public CountriesPage goToCountriesPage() {
        Helpers.clickJs(countries);
        return new CountriesPage();
    }

    public GeoZonesPage goToGeoZonesPage() {
        Helpers.clickJs(geoZones);
        return new GeoZonesPage();
    }
}

