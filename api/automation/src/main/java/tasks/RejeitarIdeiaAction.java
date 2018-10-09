package tasks;



import org.openqa.selenium.WebDriver;

import objects.DetalhesIdeiaObj;
import objects.ListaIdeiasObj;

public class RejeitarIdeiaAction {

public static void Execute(WebDriver driver, String titulo){
		
		ListarIdeiasAction.Execute(driver);
		ListaIdeiasObj.getDetalhesIdeia(driver).click();
		ListaIdeiasObj.getColocarAnalise(driver).click();
		ListaIdeiasObj.getRejeitarIdeiaButton(driver).click();
		DetalhesIdeiaObj.getCloseMsg(driver).click();
		
	}
}
