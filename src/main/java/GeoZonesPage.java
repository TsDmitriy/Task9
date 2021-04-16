import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GeoZonesPage {

    private List<WebElement> rows = new ArrayList<>();
    private List<WebElement> cell = new ArrayList<>();
    private List<WebElement> cellCounryPage = new ArrayList<>();
    private List<String> codeList = new ArrayList<>();
    private Integer hrefName = 3;
    private Integer zones = 3;
    private List<WebElement> zonesList = new ArrayList<>();


    public GeoZonesPage getAllPage() {
        WebElement table = Driver.getInstance().findElement(By.name("geo_zones_form"));
        rows.addAll(table.findElements(By.xpath(".//tr[@class=\"row\"]")));

        for (int i = 0; i < rows.size(); i++) {
            cell.addAll(rows.get(i).findElements(By.xpath("./td[" + hrefName + "]//a")));
        }
        for (int a = 0; a < cell.size(); a++) {
            codeList.add(cell.get(a).getAttribute("href"));
        }
        return this;
    }

    public GeoZonesPage goToPageCountryAndCheckOrderByName() {
        for (int i = 0; i < codeList.size(); i++) {
            Driver.getInstance().get(codeList.get(i));
            WebElement table = Driver.getInstance().findElement(By.id("table-zones"));
            cellCounryPage.addAll(table.findElements(By.xpath(".//tr")));

            for (int a = 0; a < cellCounryPage.size(); a++) {
                zonesList.addAll(cellCounryPage.get(i).findElements(By.xpath("./td["+zones+"]")));
            }
            cellCounryPage.clear();
            for (int b = 0; b < zonesList.size();b++) {
                codeList.add(zonesList.get(b).getText());
            }
            zonesList.clear();
            List<String> sortList = codeList.stream().collect(Collectors.toList());
            Collections.sort(sortList);
            if (!sortList.equals(codeList)) {
                throw new RuntimeException("Зоны расположены не в алфавитном порядке на странице " + Driver.getInstance().getCurrentUrl());
            }
            codeList.clear();
        }
        return this;
    }
}
