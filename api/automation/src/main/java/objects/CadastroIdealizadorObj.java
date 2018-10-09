package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by thialves on 07/05/2017.
 */
public class CadastroIdealizadorObj {

    private WebDriver driver;
    private static WebElement element = null;

    public static WebElement getCadastroIdealizadorButton(WebDriver driver){ //na tela de login
//        element = driver.findElement(By.xpath(".//*[@id='formLogin']/div[3]/div[1]/a"));
        element = driver.findElement(By.partialLinkText("./cadastro_idealizador.jsp"));
        return element;
    }

    public static WebElement setNameIdealizador(WebDriver driver){
        element = driver.findElement(By.id("name"));
        return element;
    }

    public static WebElement setCpfIdealizador(WebDriver driver){
        element = driver.findElement(By.id("cpf"));
        return element;
    }

    public static WebElement setEmailIdealizador(WebDriver driver){
        element = driver.findElement(By.id("email"));
        return element;
    }

    public static WebElement setTelefoneIdealizador(WebDriver driver){
        element = driver.findElement(By.id("phone"));
        return element;
    }

    public static WebElement setSenhaIdealizador(WebDriver driver){
        element = driver.findElement(By.id("password"));
        return element;
    }

    public static WebElement getCadastrarButton(WebDriver driver){
        element = driver.findElement(By.id("btn-cadastrar"));
        return element;
    }
}
