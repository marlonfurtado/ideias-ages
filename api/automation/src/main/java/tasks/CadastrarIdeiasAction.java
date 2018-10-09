package tasks;

import java.sql.Driver;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import objects.CadastroIdeiasObj;
import objects.TelaAcessoObj;

/**
 * 
 * @author thialves, lucca
 * 
 */

public class CadastrarIdeiasAction {
	
	public static void Execute(WebDriver driver, String titulo, String objetivo, String tags, String descricao){
		
		TelaAcessoObj.getDropDownMenuIdeias(driver).click();
		TelaAcessoObj.getCadastrarNovaIdeia(driver).click();
		CadastroIdeiasObj.setTituloProjeto(driver).sendKeys(titulo);
		CadastroIdeiasObj.setObjetoProjeto(driver).sendKeys(objetivo);
		CadastroIdeiasObj.setTagsProjeto(driver).sendKeys(tags);
		CadastroIdeiasObj.setDescricaoProjeto(driver).sendKeys(descricao);
		CadastroIdeiasObj.getSalvarEnviarAnaliseButton(driver).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try{
			ModalAndExecuteModal.Execute(driver);
		} catch(Exception e){
			e.printStackTrace();
		}
		finally {
			//segue adiante: gambi!
		}
	}
}
