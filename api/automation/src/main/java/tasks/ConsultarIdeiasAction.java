package tasks;

import org.openqa.selenium.WebDriver;

import objects.TelaAcessoObj;


/**
 * TODO: complementar quando sistmea tiver ideias cadastradas, para selecionar alguma ideia
 * @author thialves
 *
 */
public class ConsultarIdeiasAction {
	
	public void Execute(WebDriver driver){
		TelaAcessoObj.getListagemIdeias(driver).click();
		
	}

}
