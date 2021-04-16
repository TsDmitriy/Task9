import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;

public class CountriesPage extends MainPage {
    private List<WebElement> rows = new ArrayList<>();
    private List<WebElement> rowsPage = new ArrayList<>();

    private List<WebElement> cell = new ArrayList<>();
    private List<WebElement> cellPageList = new ArrayList<>();

    private List<String> countryNameList = new ArrayList<>();
    private List<String> zoneTextList = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();


    private Integer countryName = 5;
    private Integer hrefName = 5;
    private Integer name = 2;
    private Integer zones = 6;

    public CountriesPage getAllCountryName() {
        WebElement table = Driver.getInstance().findElement(By.name("countries_form"));
        rows.addAll(table.findElements(By.xpath(".//tr[@class=\"row\"]")));

        for (int i = 0; i < rows.size(); i++) {
            cell.addAll(rows.get(i).findElements(By.xpath("./td[" + countryName + "]")));
        }
        for (int a = 0; a < cell.size(); a++) {
            countryNameList.add(cell.get(a).getText());
        }
        return this;
    }

    public CountriesPage checkOrderByNameCountry() {
        List<String> sortCode = countryNameList.stream().collect(Collectors.toList());
        Collections.sort(sortCode);
        if (!sortCode.equals(countryNameList)) {
            throw new RuntimeException("Cтраны расположены не в алфавитном порядке");
        }
        return this;
    }

    public CountriesPage getAllZones() {
        WebElement table = Driver.getInstance().findElement(By.xpath("//*[@name=\"countries_form\"]"));
        rows.addAll(table.findElements(By.xpath(".//tr[@class=\"row\"]")));


        for (int i = 0; i < rows.size(); i++) {
            map.put(rows.get(i).findElement(By.xpath("./td[" + hrefName + "]//a")).getAttribute("href"),
                    rows.get(i).findElement(By.xpath("./td[" + zones + "]")).getAttribute("textContent"));
        }

        return this;
    }

    public void goToPageWhereZoneMoreZeroCheckOrderByCode() {
        for (Map.Entry<String, String> pair : map.entrySet()) {
            if (Integer.parseInt(pair.getValue()) > 0) {
                Driver.getInstance().get(pair.getKey());
                WebElement table = Driver.getInstance().findElement(By.id("table-zones"));
                rowsPage.addAll(table.findElements(By.xpath(".//tr")));

                for (int i = 0; i < rowsPage.size(); i++) {
                    cellPageList.addAll(rowsPage.get(i).findElements(By.xpath("./td["+name+"]")));
                }
                rowsPage.clear();
                for (int a = 0; a < cellPageList.size(); a++) {
                    zoneTextList.add(cellPageList.get(a).getText());
                }
                cellPageList.clear();
                List<String> sortCode = zoneTextList.stream().collect(Collectors.toList());
                Collections.sort(sortCode);
                if (!sortCode.equals(zoneTextList)) {
                    throw new RuntimeException("Зоны расположены не в алфавитном порядке на странице " + Driver.getInstance().getCurrentUrl());

                }
                countryNameList.clear();

            }

        }
    }
}
