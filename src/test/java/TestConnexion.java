import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestConnexion {

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");

        driver = new FirefoxDriver();
        driver.navigate().to("http://leboncoin.fr");
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }

    @After
    public void shutDown() {
        driver.close();
    }

    @Test
    public void should_connect_with_valid_email_and_password () {

        String email = "test_hugo_faye@mailinator.com";
        String password = "azertyuiop1";

        HomePage homePage = new HomePage(driver);

        LoginPopin loginPopin = homePage.displayLoginPopin();
        loginPopin.enterEmail(email);
        loginPopin.enterPassword(password);
        loginPopin.submit();

        assertTrue(homePage.checkIsLoggedIn());
    }

    @Test
    public void should_display_error_with_invalid_email () {

        String email = "invalid@mail.com";
        String password = "azertyuiop1";

        HomePage homePage = new HomePage(driver);

        LoginPopin loginPopin = homePage.displayLoginPopin();
        loginPopin.enterEmail(email);
        loginPopin.enterPassword(password);
        loginPopin.submit();

        assertTrue(loginPopin.connexionErrorPresent());
    }

    @Test
    public void should_display_error_with_invalid_password () {

        String email = "test_hugo_faye@mailinator.com";
        String password = "azertyuiop2";

        HomePage homePage = new HomePage(driver);

        LoginPopin loginPopin = homePage.displayLoginPopin();
        loginPopin.enterEmail(email);
        loginPopin.enterPassword(password);
        loginPopin.submit();

        assertTrue(loginPopin.connexionErrorPresent());
    }

    @Test
    public void should_display_error_with_no_email () {

        String password = "azertyuiop2";

        HomePage homePage = new HomePage(driver);

        LoginPopin loginPopin = homePage.displayLoginPopin();
        loginPopin.enterPassword(password);
        loginPopin.submit();

        assertTrue(loginPopin.missingEmailErrorPresent());
    }

    @Test
    public void should_display_error_with_no_password () {

        String email = "test_hugo_faye@mailinator.com";

        HomePage homePage = new HomePage(driver);

        LoginPopin loginPopin = homePage.displayLoginPopin();
        loginPopin.enterEmail(email);
        loginPopin.submit();

        assertTrue(loginPopin.missingPasswordErrorPresent());
    }

}
