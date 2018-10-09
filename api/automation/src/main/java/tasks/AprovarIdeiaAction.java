package tasks;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import objects.CadastroIdeiasObj;
import objects.DetalhesIdeiaObj;
import objects.ListaIdeiasObj;
import objects.TelaAcessoObj;

public class AprovarIdeiaAction {

	
public static void Execute(WebDriver driver, String titulo){
		
		ListarIdeiasAction.Execute(driver);
		ListaIdeiasObj.getDetalhesIdeia(driver).click();
		ListaIdeiasObj.getColocarAnalise(driver).click();
		ListaIdeiasObj.getAprovarIdeiaButton(driver).click();
		DetalhesIdeiaObj.getCloseMsg(driver).click();
		
		
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
