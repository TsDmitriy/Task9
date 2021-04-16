import org.openqa.selenium.By;

public class GoToMainPage {

    private String url = "http://localhost/litecart/admin/";
    private By ptoductForm = By.id("box-most-popular");


    public MainPage getpage() {
        Driver.getInstance().get(url);
        return new MainPage();
    }

}
