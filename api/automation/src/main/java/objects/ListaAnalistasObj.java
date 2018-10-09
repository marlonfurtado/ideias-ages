package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListaAnalistasObj {
	
	private WebDriver driver;
	private static WebElement element = null;

	
		public static WebElement getListagemAnalistas(WebDriver driver){
//			element = driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul[1]/li[1]/ul/li[1]/a")); 
			element = driver.findElement(By.partialLinkText("./listar_analista.jsp"));
			return element;
		}
		
		public static WebElement setSearchAnalista(WebDriver driver){
			element = driver.findElement(By.xpath("//*[@id='analystListTable_filter']/label/input"));
			return element;
		}
		
		public static WebElement getStatusInativo(WebDriver driver){
			element = driver.findElement(By.className("status label btn-ages-pr"));
			return element;
		}
		
		public static WebElement getStatusAtivo(WebDriver driver){
			element = driver.findElement(By.className("status label label-danger"));
			return element;
		}

}
