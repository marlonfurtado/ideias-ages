package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CadastroIdeiasObj {
	
	private WebDriver driver;
	private static WebElement element = null;
	
	public static WebElement setTituloProjeto(WebDriver driver){
		element = driver.findElement(By.id("title"));
		return element;
	}
	
	public static WebElement setObjetoProjeto(WebDriver driver){
		element = driver.findElement(By.id("goal"));
		return element;
	}
	
	public static WebElement setTagsProjeto(WebDriver driver){
		element = driver.findElement(By.id("tags"));
		return element;
	}
	
	public static WebElement setDescricaoProjeto(WebDriver driver){
		element = driver.findElement(By.id("description"));
		return element;
	}
	
	public static WebElement getSalvarRascunhoButton(WebDriver driver){
		element = driver.findElement(By.id("btnSaveDraft"));
		return element;
	}
	
	public static WebElement getSalvarEnviarAnaliseButton(WebDriver driver){
		element = driver.findElement(By.id("btnSaveAndSend"));
		return element;
	}
	
	public static WebElement getCancelarButton(WebDriver driver){
		element = driver.findElement(By.id("btnCancel"));
		return element;
	}
	/**
	 * Visa desbloquear a ação do popup - verificar - selenium não está identificando popup
	 * @param driver
	 */
	public static WebElement getConfirmarSucessoCloseButton(WebDriver driver) {
		element = driver.findElement(By.id("btn-fecharModal"));
		return element;
	}

	/*
	 * public static void getConfirmarSucessoCloseButton(WebDriver driver){
		driver.switchTo().alert().accept();
	}
	 */
}
