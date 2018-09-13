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
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
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

        assertTrue("L'utilisateur n'est pas connecté alors qu'il devrait l'être.",homePage.checkIsLoggedIn());
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

        assertTrue("Le message d'erreur de connexion n'est pas présent alors qu'il devrait l'être.",loginPopin.connexionErrorPresent());
        assertFalse("L'utilisateur est connecté alors qu'il ne devrait pas.",homePage.checkIsLoggedIn());

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

        assertTrue("Le message d'erreur de connexion n'est pas présent alors qu'il devrait l'être.",loginPopin.connexionErrorPresent());
        assertFalse("L'utilisateur est connecté alors qu'il ne devrait pas.",homePage.checkIsLoggedIn());
    }

    @Test
    public void should_display_error_with_no_email () {

        String password = "azertyuiop2";

        HomePage homePage = new HomePage(driver);

        LoginPopin loginPopin = homePage.displayLoginPopin();
        loginPopin.enterPassword(password);
        loginPopin.submit();

        assertTrue("Le message d'erreur d'email vide n'est pas présent alors qu'il devrait l'être",loginPopin.missingEmailErrorPresent());
        assertFalse("L'utilisateur est connecté alors qu'il ne devrait pas",homePage.checkIsLoggedIn());
    }

    @Test
    public void should_display_error_with_no_password () {

        String email = "test_hugo_faye@mailinator.com";

        HomePage homePage = new HomePage(driver);

        LoginPopin loginPopin = homePage.displayLoginPopin();
        loginPopin.enterEmail(email);
        loginPopin.submit();

        assertTrue("Le message d'erreur de mot de passe vide n'est pas présent alors qu'il devrait l'être",loginPopin.missingPasswordErrorPresent());
        assertFalse("L'utilisateur est connecté alors qu'il ne devrait pas.",homePage.checkIsLoggedIn());
    }

}
