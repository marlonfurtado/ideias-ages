package tasks;

import org.openqa.selenium.WebDriver;

import objects.ListaAnalistasObj;
import objects.ListaIdealizadoresObj;

public class InativarIdealizador {
	
	private static ListarIdealizadorAction listaIdealizadores = new ListarIdealizadorAction();
	
	/**
	 * TODO complementar com lougout + popups
	 * @param driver
	 * @param cpf
	 */
	
public static void Execute(WebDriver driver, String cpf){
	
	listaIdealizadores.Execute(driver);
	ListaIdealizadoresObj.setSearchIdealizador(driver).sendKeys(cpf);
	
	try{
		if(ListaIdealizadoresObj.getStatusAtivo(driver).getText().equalsIgnoreCase("Inativar")){
			ListaAnalistasObj.getStatusAtivo(driver).click();
		} else if(ListaAnalistasObj.getStatusInativo(driver).getText().equalsIgnoreCase("Ativar")){
			System.out.println("Usuário já inativo!!!");
		}
	} catch(Exception e){
		System.out.println("Analista não localizado!!!");
	}
		
	}

}
