package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListaIdealizadoresObj {
	
	private WebDriver driver;
	private static WebElement element = null;

	
		public static WebElement getListagemIdealizadores(WebDriver driver){
//			element = driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul[1]/li[2]/ul/li/a")); 
			element = driver.findElement(By.partialLinkText("./listar_idealizadores.jsp"));
			return element;
		}
		
		public static WebElement getListagemIdealizadoresLabel(WebDriver driver){
			element = driver.findElement(By.id("h2PageTitle"));
			return element;
		}
		
		public static WebElement setSearchIdealizador(WebDriver driver){
			element = driver.findElement(By.xpath("//*[@id='idealizer-table_filter']/label/input"));
			return element;
		}
		
		public static WebElement getStatusAtivo(WebDriver driver){
			element = driver.findElement(By.className("status label label-danger"));
			return element;
		}
		
		public static WebElement getStatusInativo(WebDriver driver){
			element = driver.findElement(By.className("status label btn-ages-pr"));
			return element;
		}
	}
