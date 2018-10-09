package objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by thialves on 07/05/2017.
 */
public class LoginPageObj {

    private WebDriver driver;
    private static WebElement element = null;

    public static WebElement setUserCpf(WebDriver driver){
        element = driver.findElement(By.id("cpf"));
        return element;
    }

    public static WebElement setUserPass(WebDriver driver){
        element = driver.findElement(By.id("password"));
        return element;
    }

    public static WebElement btnLogin(WebDriver driver){
    element = driver.findElement(By.id("submitLogin"));

        return element;
    }
    
    public static WebElement btnCadastrar(WebDriver driver){
    	element = driver.findElement(By.partialLinkText("./cadastro_idealizador.jsp"));
    	
    	return element;
    }
    
    public static WebElement btnEsqueciSenha(WebDriver driver){
    	element = driver.findElement(By.id("forgotPasswordLink"));
    	
    	return element;
    }


}
