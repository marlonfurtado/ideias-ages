package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditaPerfilObj {
	
	private WebDriver driver;
    private static WebElement element = null;
    
    public static WebElement setEditaNome(WebDriver driver){
    	element = driver.findElement(By.id("name"));
    	return element;
    }
    
    public static WebElement setEditaEmail(WebDriver driver){
    	element = driver.findElement(By.id("email"));
    	return element;
    } 
    
    public static WebElement setEditaPhone(WebDriver driver){
    	element = driver.findElement(By.id("phone"));
    	return element;
    } 
    
    public static WebElement getSenhaAtual(WebDriver driver){
    	element = driver.findElement(By.id("actual-password"));
    	return element;
    } 
    
    public static WebElement setSenhaNova(WebDriver driver){
    	element = driver.findElement(By.id("new-password"));
    	return element;
    } 
    
    public static WebElement setConfirmaSenhaNova(WebDriver driver){
    	element = driver.findElement(By.id("confirm-password"));
    	return element;
    } 
    
    public static WebElement getTrocarSenhaButton(WebDriver driver){
    	element = driver.findElement(By.partialLinkText("#password-change")); //confirmar!!!
    	return element;
    }
    
    public static WebElement getCancelarButton(WebDriver driver){
    	element = driver.findElement(By.id("btn-cancelar")); //confirmar!!!
    	return element;
    }
    
    public static WebElement getSalvarButton(WebDriver driver){
    	element = driver.findElement(By.id("btn-salvar")); //confirmar!!!
    	return element;
    }
    
}
