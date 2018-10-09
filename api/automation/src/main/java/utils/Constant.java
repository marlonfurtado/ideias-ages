package utils;

/**
 * Created by thialves on 07/05/2017.
 */

import java.util.Random;

public class Constant {
	
	public static String words = "qwertyuiopasdfghjklzxcvbnm";
	
	public static String author = "Agência Experimental de Software";
	public static String title = "Relatório de Execução de Testes Automatizados";

    public static final String url = "http://www.homo.ages.pucrs.br/projetos/ideias/login.jsp";
    public static final String cpf = "85598860015";
    public static final String senha = "123";
    public static final String nome = "fulano";
    public static final String cpf2 = "01234567890";
    public static final String cpf3 = "42928831657";
    public static final String cpfAnalista = "036.466.500-95";
    public static final String senhaAnalista = "123456";
    public static final String email = "fulano@gmail.com";
    public static final String telefone = "51996754332";
    
    public static final String titulo = "Titulo da nova ideia";
    public static final String objetivo = "Cadastro de teste";
    public static final String comentario = "este e um comentario de teste";
    public static final String tags = "teste1, teste1, teste1";
    public static final String descricao = "trata-se de apenas um teste de cadastro de ideias";
    
    public static final String dbusernmae = "";
    public static final String dbpassword = "";
    public static final String databaseURL = "";
    
    public static String getName(){
    	Random r = new Random();
    	int a = r.nextInt(words.length() + 1);
    	int b = r.nextInt(words.length() + 1);
    	String name = words.substring(b, a);
    	return name;
    }
    
    public static String getCpf(){
    	String cpf = "";
    	
    	return cpf;
    }
    
}
