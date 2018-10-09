package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GerarScreenshot {
	
	/**
	 * Método para gerar screenshot da tela. Por enquanto, apenas gera a imagem como evidência; posteriormente o conjunto de imagens
	 * será adicionado a um pdf para compor um documento de evidências.
	 * @param driver
	 * @param fileWithPath
	 * @throws Exception
	 */
	
	public static void printaTela(WebDriver driver, String fileWithPath) throws Exception{
		
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath+System.currentTimeMillis()+".png");
        FileUtils.copyFile(SrcFile, DestFile);

    }

}
