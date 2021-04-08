import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import java.util.List;

public class MainPage {

    private By ptoduct = By.xpath("//*[@class=\"image-wrapper\"]");
    private By stickerPtoduct = By.xpath(".//*[contains(@class,'sticker')]");
    private By nameProduct = By.xpath("//..//*[@class=\"name\"]");

    public MainPage checkStickerPtoduct() throws Exception {
        List<WebElement> ptoductList = new ArrayList<>();
        ptoductList.addAll(Driver.getInstance().findElements(ptoduct));
        for (int i = 0; i <ptoductList.size(); i++) {
            if (ptoductList.get(i).findElements(stickerPtoduct).size() != 1) {
                throw new Exception(String.format("Количество стикеров у продукта с именем %s не равно одному", ptoductList.get(i).findElement(nameProduct).getText()));
            }

        }
        return this;
    }
}

