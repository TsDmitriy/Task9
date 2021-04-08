import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

    private static WebDriver instance;


    public static void setnull() {
        instance = null;
    }

    private Driver() {

    }

    /**
     * Метод проверяет есть ли instance WebDriver, если нет, то создает новый
     * @return instance
     */
    public static WebDriver getInstance() {
        if (instance == null) {

            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
            instance =  new ChromeDriver();
            instance.manage().window().maximize();
        }
        return instance;
    }

    /**
     * Метод который завершает работу браузера и сервисов
     */
    public static void stopDriver () {
        instance.quit();
        instance = null;
    }
}