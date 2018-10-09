package testcases;

import objects.TelaAcessoObj;
import tasks.SignInAction;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Constant;
import utils.GerarScreenshot;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by thialves on 17/05/2017.
 */

@Listeners()
public class testLoginTest {

   public String systemDriver = null;	
   public WebDriver driver = null;
   public static String path = "src/main/java/relatorios/testLogin";

   @Test(priority=0,description="testReporterOne")
   public void testLoginTest() throws Exception{
	  Reporter.log("Logando...");
	  GerarScreenshot.printaTela(driver, path);
      SignInAction.Execute(driver, Constant.cpf, Constant.senha);
      GerarScreenshot.printaTela(driver, path);
      Reporter.log("Login efetuado... saindo...");
      TelaAcessoObj.getUserNameContainer(driver).click();
      GerarScreenshot.printaTela(driver, path);
      TelaAcessoObj.getLogoutAcesso(driver).click();
      Reporter.log("Fim de login");
      Assert.assertEquals(true, true);
   }
/**
 * Método que define as configurações de execução do Selenium. Note que o método não identifica o Mac OS Sierra. 
 * Caso o TestNG apresente um erro dizendo que "não é possível executar o chromedriver", verifique se o arquivo tem permissão de execução
 * em seu computador (mude com: 'chmod +x chromedriver') - o mesmo problema pode ocorrer em GNU-Linux.
 */
   @BeforeMethod
    public void beforeMethod(){
	   try{
	   if(SystemUtils.IS_OS_WINDOWS_7 || SystemUtils.IS_OS_WINDOWS_8 || SystemUtils.IS_OS_WINDOWS_10){
		   systemDriver = "chromedriver.exe";
	   } else if(SystemUtils.IS_OS_LINUX){
		   systemDriver = "chromedriverLinux";
	   } else if(SystemUtils.IS_OS_MAC_OSX_EL_CAPITAN){ //nao identifica o Sierra
		   systemDriver= "chromedriver";
	   	} 
	   } catch(Exception e){
		   e.printStackTrace();
	   } 
	   finally{
		   System.out.println("Sistema não identificado, tentando driver para Windows...");
		   systemDriver = "chromedriver"; //mudei só pra tester no meu computador :)
	   }
	   
       System.setProperty("webdriver.chrome.driver", "src/main/java/utils/"+systemDriver);
       ChromeOptions op = new ChromeOptions();
       op.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
       driver = new ChromeDriver(op); //usar RemoteDriver p/ apontar p/ container
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.get(Constant.url);
   }

   @AfterMethod
    public void afterMethod(){
        driver.quit();
   }

}