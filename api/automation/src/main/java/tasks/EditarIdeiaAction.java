package tasks;

import org.openqa.selenium.WebDriver;

import objects.DetalhesIdeiaObj;
import objects.EditaPerfilObj;
import objects.ListaIdeiasObj;

/**
 * @TODO todo fluxo - o projeto não está pronto em Homologação
 * @author thialves
 *
 */

public class EditarIdeiaAction {
	
	public static void Execute(WebDriver driver){
		ListaIdeiasObj.getDetalhesIdeia(driver).click();
		
		try{
			DetalhesIdeiaObj.setEditaTitulo(driver).sendKeys("titulo editado");
			DetalhesIdeiaObj.setEditaObjetivo(driver).sendKeys("objetivo editado");
			DetalhesIdeiaObj.setEditaPalavraChave(driver).sendKeys("palavra chave editada");
			DetalhesIdeiaObj.setEditaDescricao(driver).sendKeys("descricao editada");
			
			DetalhesIdeiaObj.getSave(driver).click();
			} catch(Exception e){}
		}
	
	public static void AbrirDetalhes(WebDriver driver){
		ListaIdeiasObj.getDetalhesIdeia(driver).click();
	}
}
