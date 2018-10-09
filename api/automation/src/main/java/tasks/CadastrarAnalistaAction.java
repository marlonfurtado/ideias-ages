package tasks;

import org.openqa.selenium.WebDriver;

import objects.CadastroAnalistaObj;

public class CadastrarAnalistaAction {

    public static void Execute(WebDriver driver, String nome, String cpf, String email, String telefone, String senha){

        CadastroAnalistaObj.getCadastrarAnalistaMenu(driver).click();
        CadastroAnalistaObj.getNomeEditText(driver).sendKeys(nome);
        CadastroAnalistaObj.getCpfEditText(driver).sendKeys(cpf);
        CadastroAnalistaObj.getEmailEditText(driver).sendKeys(email);
        CadastroAnalistaObj.getTelefoneEditText(driver).sendKeys(telefone);
        CadastroAnalistaObj.getSenhaEditText(driver).sendKeys(senha);
        CadastroAnalistaObj.getCadastrarButton(driver).click();
    }
}
