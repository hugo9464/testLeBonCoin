import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Classe représentant la popin de login
 */
public class LoginPopin {

    private WebDriver driver;

    @FindBy(css = "[data-qa-id=authmodal-email]")
    private WebElement emailInput;

    @FindBy(css = "[data-qa-id=authmodal-password]")
    private WebElement passwordInput;

    @FindBy(css = "[data-qa-id=authmodal-login]")
    private WebElement loginButton;

    @FindBy(className = "_1zeAx")
    private WebElement errorMessage;

    public LoginPopin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Permet de taper un email
     * @param email l'email à taper
     */
    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    /**
     * Permet de taper un mot de passe
     * @param password le mot de passe à taper
     */
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    /**
     * Permet de soumettre le formulaire de connexion
     */
    public void submit() {
        loginButton.click();
    }

    /**
     * Permet de vérifier la présence du message d'erreur de connexion
     * @return true si le message est présent, sinon false
     */
    public boolean errorMessagePresent() {
        return errorMessage.isDisplayed();
    }
}