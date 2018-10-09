package tasks;

import org.openqa.selenium.WebDriver;

import objects.EditaPerfilObj;
import objects.TelaAcessoObj;

public class EditarPerfilUsuarioAction {
	/**
	 * TODO Sobrecarga de métod para fazer ação sem trocar a senha & validação de popup
	 * @param driver
	 * @param nome
	 * @param email
	 * @param telefone
	 * @param senhaAtual
	 * @param senhaNova
	 */
public void Execute(WebDriver driver, String nome, String email, String telefone, String senhaAtual, String senhaNova){
	
	TelaAcessoObj.getEditarPerfil(driver).click();
	
	try{
	EditaPerfilObj.setEditaNome(driver).sendKeys(nome);
	EditaPerfilObj.setEditaEmail(driver).sendKeys(email);
	EditaPerfilObj.setEditaPhone(driver).sendKeys(telefone);
	
	TrocarSenha(driver, senhaAtual, senhaNova);
	
	EditaPerfilObj.getSalvarButton(driver).click();
	} catch(Exception e){}
//		finally{
//			//logout
//		}
	}

public void Execute(WebDriver driver, String nome, String email, String telefone){
TelaAcessoObj.getEditarPerfil(driver).click();
	
	try{
	EditaPerfilObj.setEditaNome(driver).sendKeys(nome);
	EditaPerfilObj.setEditaEmail(driver).sendKeys(email);
	EditaPerfilObj.setEditaPhone(driver).sendKeys(telefone);
	
	EditaPerfilObj.getSalvarButton(driver).click();
	} catch(Exception e){}
}

	public void TrocarSenha(WebDriver driver, String senhaAtual, String senhaNova){
		
		EditaPerfilObj.getTrocarSenhaButton(driver).click();
		
		EditaPerfilObj.getSenhaAtual(driver).sendKeys(senhaAtual);
		EditaPerfilObj.setSenhaNova(driver).sendKeys(senhaNova);
		EditaPerfilObj.setConfirmaSenhaNova(driver).sendKeys(senhaNova);
	}

}
