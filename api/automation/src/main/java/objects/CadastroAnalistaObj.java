package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by thialves on 19/05/2017.
 */
public class CadastroAnalistaObj {

    private WebDriver driver;
    private static WebElement element = null;

    public static WebElement getCadastrarAnalistaMenu(WebDriver driver){
        element = driver.findElement(By.partialLinkText("cadastro_analista.jsp"));
        return element;
    }

    public static WebElement getNomeEditText(WebDriver driver){
        element = driver.findElement(By.id("name"));
        return element;
    }

    public static WebElement getCpfEditText(WebDriver driver){
        element = driver.findElement(By.id("cpf"));
        return element;
    }

    public static WebElement getEmailEditText(WebDriver driver){
        element = driver.findElement(By.id("email"));
        return element;
    }

    public static WebElement getTelefoneEditText(WebDriver driver){
        element = driver.findElement(By.id("phone"));
        return element;
    }

    public static WebElement getSenhaEditText(WebDriver driver){
        element = driver.findElement(By.id("password"));
        return element;
    }

    public static WebElement getCadastrarButton(WebDriver driver){
        element = driver.findElement(By.id("btn-cadastrar"));
        return element;
    }


}
