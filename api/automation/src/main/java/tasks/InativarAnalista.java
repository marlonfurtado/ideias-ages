package tasks;

import org.openqa.selenium.WebDriver;

import objects.ListaAnalistasObj;

public class InativarAnalista {
	
	private static ListarAnalistaAction consultaAnalista = new ListarAnalistaAction();
	
	/**
	 * TODO aproveitar método para ação de ativar/inativar, passando parâmetro na execução
	 * @param driver
	 * @param cpf
	 */
	public static void Execute(WebDriver driver, String cpf){
		
		consultaAnalista.Execute(driver);
		ListaAnalistasObj.setSearchAnalista(driver).sendKeys(cpf);
		try{
			if(ListaAnalistasObj.getStatusAtivo(driver).getText().equalsIgnoreCase("Inativar")){
				ListaAnalistasObj.getStatusAtivo(driver).click();
			} else if(ListaAnalistasObj.getStatusInativo(driver).getText().equalsIgnoreCase("Ativar")){
				System.out.println("Usuário já inativo!!!");
			}
		} catch(Exception e){
			System.out.println("Analista não localizado!!!");
		}
		
	} 

}
