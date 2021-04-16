import org.openqa.selenium.By;

public class LoginPage {

    public LoginPage() {
        Helpers.waitLoadPage();
    }

    private By fieldUsername = By.name("username");
    private By fieldPassword = By.name("password");
    private String userName= "admin";
    private String password= "admin";
    private By buttonSearch = By.name("login");
    private By logotypeLiteCart = By.xpath("//*[@class=\"logotype\"]");


    public MainPage inputLoginAndPassword() {
        Helpers.sendKeys(fieldUsername, userName);
        Helpers.sendKeys(fieldPassword, password);
        Helpers.click(buttonSearch);
        Helpers.presenceOfElementLocated(logotypeLiteCart);
        return new MainPage();
    }
}
