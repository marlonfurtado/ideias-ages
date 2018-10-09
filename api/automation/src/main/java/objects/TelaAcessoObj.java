package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by thialves on 07/05/2017.
 */

public class TelaAcessoObj {
    private WebDriver driver;
    private static WebElement element = null;

    public static WebElement getUserNameContainer(WebDriver driver){
        element = driver.findElement(By.id("userNameContainer"));
        return element;
    }
    
    public static WebElement getEditarPerfil(WebDriver driver){
    	element = driver.findElement(By.partialLinkText("./perfil.jsp"));
    	return element;
    }
    
    public static WebElement getEditarIdeia(WebDriver driver){
    	element = driver.findElement(By.partialLinkText("./detalhes_ideia.jsp"));
    	return element;
    }

    public static WebElement getLogoutAcesso(WebDriver driver){
        element = driver.findElement(By.id("logoutAction"));
        return element;
    }
    
    public static WebElement getDropDownMenuIdeias(WebDriver driver){
    	element = driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul[1]/li"));
    	return element;
    }
    
    public static WebElement getCadastrarNovaIdeia(WebDriver driver){
    	element = driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul[1]/li/ul/li[2]/a"));
//    	element = driver.findElement(By.partialLinkText("Nova Ideia"));
    	//element = driver.findElement(By.)
    	return element;
    } 
    
    public static WebElement getListagemIdeias(WebDriver driver){
    	//element = driver.findElement(By.partialLinkText("http://www.homo.ages.pucrs.br/projetos/ideias/listar_ideias.jsp"));
    	element = driver.findElement(By.partialLinkText("./listar_ideia.jsp"));
    	return element;
    }
    
    public static WebElement getListagemIdealizadores(WebDriver driver){
    	element = driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul[1]/li[2]/ul/li/a"));
    	//element = driver.findElement(By.partialLinkText("./listar_idealizadores.jsp"));
    	return element;
    }

}
