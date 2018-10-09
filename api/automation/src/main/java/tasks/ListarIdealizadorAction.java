package tasks;

import org.openqa.selenium.WebDriver;

import objects.ListaIdealizadoresObj;
import objects.TelaAcessoObj;

public class ListarIdealizadorAction {
	
	public static void Execute(WebDriver driver){
		
		ListaIdealizadoresObj.getListagemIdealizadores(driver).click();
//		TelaAcessoObj.getLogoutAcesso(driver).click(); realizar login/logout no próprio teste
	}

}
