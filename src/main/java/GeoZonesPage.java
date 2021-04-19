import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GeoZonesPage {

    private List<WebElement> rows = new ArrayList<>();
    private List<WebElement> cell = new ArrayList<>();
    private List<WebElement> cellCounryPage = new ArrayList<>();
    private List<String> codeList = new ArrayList<>();
    private List<String> listStringZone = new ArrayList<>();
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
            cellCounryPage.addAll(table.findElements(By.xpath(".//tr[not(contains(@class, 'header'))]")));
            int lastElement = cellCounryPage.size() - 1;
            cellCounryPage.remove(lastElement);

            for (int a = 0; a < cellCounryPage.size(); a++) {
                zonesList.add(cellCounryPage.get(a).findElement(By.xpath("./td[" + zones + "]//*[contains(@selected, 'selected')]")));
            }
            cellCounryPage.clear();
            for (int b = 0; b < zonesList.size(); b++) {
                listStringZone.add(zonesList.get(b).getText());
            }
            zonesList.clear();
            List<String> sortList = listStringZone.stream().collect(Collectors.toList());
            Collections.sort(sortList);
            if (!sortList.equals(listStringZone)) {
                throw new RuntimeException("Зоны расположены не в алфавитном порядке на странице " + Driver.getInstance().getCurrentUrl());
            }
            listStringZone.clear();
        }
        return this;
    }
}
