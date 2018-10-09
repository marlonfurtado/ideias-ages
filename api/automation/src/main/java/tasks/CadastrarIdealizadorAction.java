package tasks;

import org.openqa.selenium.WebDriver;

import objects.CadastroIdealizadorObj;

/**
 * Created by thialves on 07/05/2017.
 */
public class CadastrarIdealizadorAction {

    public static void Execute(WebDriver driver, String nome, String cpf, String senha, String email, String telefone){

        CadastroIdealizadorObj.getCadastroIdealizadorButton(driver).click();
        CadastroIdealizadorObj.setNameIdealizador(driver).sendKeys(nome);
        CadastroIdealizadorObj.setCpfIdealizador(driver).sendKeys(cpf);
        CadastroIdealizadorObj.setSenhaIdealizador(driver).sendKeys(senha);
        CadastroIdealizadorObj.setEmailIdealizador(driver).sendKeys(email);
        CadastroIdealizadorObj.setTelefoneIdealizador(driver).sendKeys(telefone);
        CadastroIdealizadorObj.getCadastrarButton(driver).click();

    }
}
