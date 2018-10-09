package tasks;

import org.openqa.selenium.WebDriver;

import objects.DetalhesIdeiaObj;
import objects.ListaIdeiasObj;

/**
 * @TODO mapear objeto de comentário e termianr task 
 */

public class EscreverComentariosAction {
	
	public static void Execute(WebDriver driver, String comentario){
		ListaIdeiasObj.getEscreverComentario(driver).click();
		DetalhesIdeiaObj.getComentar(driver).sendKeys(comentario);
		DetalhesIdeiaObj.getAddComent(driver).click();
		DetalhesIdeiaObj.getCloseMsg(driver).click();
	}
}