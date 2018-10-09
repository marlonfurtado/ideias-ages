package tasks;

import org.openqa.selenium.WebDriver;

import objects.DetalhesIdeiaObj;
import objects.ListaIdeiasObj;

public class QuestionarIdeiaAction {
	
	public static void Execute(WebDriver driver){
		DetalhesIdeiaObj.getQuestionar(driver).click();
		DetalhesIdeiaObj.getDescriptionQuest(driver).sendKeys("Perguntei");
		DetalhesIdeiaObj.getBtEnviarQuest(driver).click();
		DetalhesIdeiaObj.getBtCancelQuest(driver).click();
	}
	
	public static void Cancel(WebDriver driver){
		DetalhesIdeiaObj.getQuestionar(driver).click();
		DetalhesIdeiaObj.getBtCancelQuest(driver).click();
	}
}
