package utils;

/*
 * http://www.guru99.com/pdf-emails-and-screenshot-of-test-reports-in-selenium.html
 * usari itext
 */

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
	
public class Listener implements ITestListener {
	
	public static void takeScreenshot(WebDriver driver, String page){
		
	}

	@Override
	public void onFinish(ITestContext arg0) {
		System.out.println("Fim do teste(TEST)->"+arg0.getName());
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		System.out.println("Início da execução(TEST)->"+arg0.getName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		System.out.println("Teste falhou com(TEST)->"+arg0.getName());
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		System.out.println("Teste falhou(TEST)->"+arg0.getName());
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		System.out.println("Teste pulou(TEST)->"+arg0.getName());
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("Teste iniciou(TEST)->"+arg0.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.out.println("Teste bem sucedido(TEST)->"+arg0.getName());
		
	}

}
