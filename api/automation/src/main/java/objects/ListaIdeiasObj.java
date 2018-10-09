package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListaIdeiasObj {
	
	private WebDriver driver;
	private static WebElement element = null;
	
	public static WebElement getListagemIdeias(WebDriver driver){
//		element = driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul[1]/li[3]/ul/li/a")); 
		element = driver.findElement(By.partialLinkText("./listar_ideia.jsp"));
		return element;
	}
	
	public static WebElement getColocarAnalise(WebDriver driver){
		element = driver.findElement(By.id("btnPutIdeaUnderAnalysis"));
		return element;
	}

	public static WebElement getListagemIdeiasLabel(WebDriver driver){
		element = driver.findElement(By.id("h2PageTitle"));
		return element;
	}
	
	public static WebElement getEscreverComentario(WebDriver driver){
		element = driver.findElement(By.id("openAddComment"));
		return element;
	}
	
	public static WebElement setIdeiaSearch(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='ideas-table_filter']/label/input"));
		return element;
	}
	
	public static WebElement getTituloTag(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='ideas-table']/thead/tr/th[1]"));
		return element;
	}
	
	public static WebElement getPalavrasChaveTag(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='ideas-table']/thead/tr/th[2]"));
		return element;
	}
	
	public static WebElement getStatusTag(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='ideas-table']/thead/tr/th[3]"));
		return element;
	}
	
	public static WebElement getDataCriacaoTag(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='ideas-table']/thead/tr/th[4]"));
		return element;
	}
	
	public static WebElement getAnalistaVinculado(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='ideas-table']/thead/tr/th[5]"));
		return element;
	}
	
	public static WebElement getColocarIdeiaEmAnaliseButton(WebDriver driver){
		element = driver.findElement(By.id("btnPutIdeaUnderAnalysis"));
		return element;
	}
	
	public static WebElement getAprovarIdeiaButton(WebDriver driver){
		element = driver.findElement(By.id("btnApproveIdea"));
		return element;
	}
	
	public static WebElement getRejeitarIdeiaButton(WebDriver driver){
		element = driver.findElement(By.id("btnRejectIdea"));
		return element;
	}

	public static WebElement getDetalhesIdeia(WebDriver driver) {
		element = driver.findElement(By.partialLinkText("./detalhes_ideia.jsp"));
		return element;

	}
}