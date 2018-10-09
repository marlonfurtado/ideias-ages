package tasks;

import org.openqa.selenium.WebDriver;


import objects.ListaIdeiasObj;

/**
 * 
 * @author Lucca
 *
 */

public class ListarIdeiasAction {
	
	public static void Execute(WebDriver driver){
		ListaIdeiasObj.getListagemIdeias(driver).click();
//		TelaAcessoObj.getLogoutAcesso(driver).click(); //realizar login/logout no próprio teste
	}

}