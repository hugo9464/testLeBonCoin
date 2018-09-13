import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Classe représentant la HomePage
 */
public class HomePage {

    private WebDriver driver;

    @FindBy(xpath = "//*[contains(text(), 'Se connecter')]")
    private WebElement loginButton;

    @FindBy(css = "[data-qa-id=profilarea-login]")
    private WebElement loggedInButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Clique sur le bouton de connexion
     * @return la popin de connexion
     */
    public LoginPopin displayLoginPopin() {
        loginButton.click();
        return new LoginPopin(driver);
    }

    /**
     * Vérifie que la page est dans un statut connecté
     * @return true si connecté, sinon false
     */
    public boolean checkIsLoggedIn() {

        try {
            loggedInButton.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }
}
