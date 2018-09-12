import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;

public class TestConnexion {

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");

        driver = new FirefoxDriver();
        driver.navigate().to("http://leboncoin.fr");
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

        assertTrue(loginPopin.errorMessagePresent());
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

        assertTrue(loginPopin.errorMessagePresent());
    }
}
