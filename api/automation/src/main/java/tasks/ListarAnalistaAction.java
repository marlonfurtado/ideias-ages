package tasks;

import org.openqa.selenium.WebDriver;

import objects.ListaAnalistasObj;

public class ListarAnalistaAction {
	
	public static void Execute(WebDriver driver){
		ListaAnalistasObj.getListagemAnalistas(driver).click();
//		TelaAcessoObj.getLogoutAcesso(driver).click(); //realizar login/logout no próprio teste
	}

}
