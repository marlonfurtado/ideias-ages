package testcases;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import objects.TelaAcessoObj;
import tasks.InativarAnalista;
import tasks.ListarAnalistaAction;
import tasks.SignInAction;
import utils.Constant;

public class testInativarAnalista {

	/**
	 * @author Lucca
	 */

	public WebDriver driver = null;
	public String systemDriver = null;

	@Test
	public void testInativarAnalistas() {
		Reporter.log("Logando...");
		SignInAction.Execute(driver, utils.Constant.cpf, utils.Constant.senha);
		Reporter.log("Clicando em listar analistas...");
		ListarAnalistaAction.Execute(driver);
		Reporter.log("Clicando em Inativar analista...");
		InativarAnalista.Execute(driver, utils.Constant.cpf);
		Reporter.log("Fim de login");
		TelaAcessoObj.getUserNameContainer(driver).click();
		TelaAcessoObj.getLogoutAcesso(driver).click();
		Assert.assertEquals(true, true);
	}

	@BeforeMethod
	public void beforeMethod() {
		try {
			if (SystemUtils.IS_OS_WINDOWS_7 || SystemUtils.IS_OS_WINDOWS_8 || SystemUtils.IS_OS_WINDOWS_10) {
				systemDriver = "chromedriver.exe";
			} else if (SystemUtils.IS_OS_LINUX) {
				systemDriver = "chromedriverLinux";
			} else if (SystemUtils.IS_OS_MAC_OSX_EL_CAPITAN) {
				systemDriver = "chromedriver";
			}
		} catch (Exception e) {
			System.out.println("Sistema não identificado, tentando driver para Windows...");
			systemDriver = "chromedriver.exe";
		}

		System.setProperty("webdriver.chrome.driver", "src/main/java/utils/" + systemDriver);
		ChromeOptions op = new ChromeOptions();
		op.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		driver = new ChromeDriver(op);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constant.url);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
