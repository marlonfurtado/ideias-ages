package tasks;

import org.openqa.selenium.WebDriver;

import objects.LoginPageObj;

/**
 * Created by thialves on 07/05/2017.
 */
public class SignInAction {

    public static void Execute(WebDriver driver, String cpf, String senha){
        LoginPageObj.setUserCpf(driver).sendKeys(cpf);
        LoginPageObj.setUserPass(driver).sendKeys(senha);
        LoginPageObj.btnLogin(driver).click();
    }
}
