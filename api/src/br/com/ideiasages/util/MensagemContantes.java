
package br.com.ideiasages.util;

/**
 * Classe de mensagens gen�ricas para o sistema.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 *
 **/
public class MensagemContantes {

	public static final String MSG_ERR_CAMPO_OBRIGATORIO = "Campo ? � obrigat�rio!";

	public static final String MSG_ERR_CAMPOS_OBRIGATORIOS = "Erro ao salvar ideia, todos os campos marcados com \"*\" s�o obrigat�rios e precisam ser preenchidos.";

	public static final String MSG_ERR_CAMPO_INVALIDO = "Campo ? inv�lido!";

	public static final String MSG_ERR_USUARIO_SENHA_INVALIDOS = "Usu�rio/Senha inv�lidos!";

	public static final String MSG_ERR_USUARIO_NAO_EXISTE = "Usu�rio n�o existe!";

	public static final String MSG_ERR_SENHA_INVALIDA = "Senha Atual inv�lida";

	public static final String MSG_ERR_SENHA_NULO = "Senha n�o pode ser nulo";

	public static final String MSG_ERR_CAMPO_EXCEDE_TAMANHO = "Campo ? excede o tamanho permitido de caracteres.";

	public static final String MSG_ERR_CAMPO_DATA_MAIOR_RECOMENDADO = "Campo ? com mais de 10 caracteres!";

	public static final String MSG_ERR_CAMPO_DATA_MENOR_RECOMENDADO = "Campo ? com menos de 10 caracteres!";

	public static final String MSG_ERR_CAMPO_DATA_INVALIDO = "Ocorreu algum problema no envio da data";	

	public static final String MSG_ERR_SENHAS_DIFERENTES = "A senha informada n�o coincide com a confirma��o de senha!";

	public static final String MSG_ERR_TELEFONE_INVALIDO = "Telefone com formato inv�lido.\n Exemplo correto: 51123456789.";

	public static final String MSG_ERR_SAVE_PASSWORD_CHANGE_REQUEST = "Ocorreu algum problema ao salvar a requisi��o de troca de senha";

	public static final String MSG_ERR_ENCRYPT_PASSWORD_CHANGE_REQUEST = "Ocorreu algum problema ao criar o token da requisi��o de troca de senha";

	public static final String MSG_ERR_EMAIL_PASSWORD_CHANGE_REQUEST = "Ocorreu algum problema ao enviar o email da requisi��o de troca de senha";
	
	public static final String MSG_ERR_IDEA_HAS_QUESTION_UNANSWERED = "Esta idéia possui um questionamento ainda não respondido. Aguarde até que o idealizador responda o último questionamento para fazer uma nova pergunta.";
	
	public static final String MSG_ERR_IDEA_NOT_FOUND = "A idéia informada não existe em nossa base de dados.";

	public static final String MSG_SUC_CADASTRO_USUARIO = "Usu�rio ? cadastrado com sucesso!";

	public static final String MSG_SUC_EDICAO_USUARIO = "Usu�rio ? atualizado com sucesso!";

	public static final String MSG_INF_DENY = "Acesso negado! Voc� precisa logar primeiro.";

	public static final String MSG_INF_EMAIL_ALREADY_REGISTERED = "J� existe um cadastro com este email.";

	public static final String MSG_INF_CPF_ALREADY_REGISTERED = "J� existe um cadastro com este CPF.";

	public static final String MSG_INF_ALLOW_ONLY_ADMINISTRATOR = "Acesso negado! Permitido somente administradores do sistema.";

	public static final String MSG_ERR_USUARIO_INATIVO = "Acesso negado! Usu�rio ? inativo.";

	public static final String MSG_IDEA_SAVED = "Ideia salva com sucesso.";

	public static final String MSG_IDEA_NOT_SAVED = "Erro ao salvar idea.";

	public static final String MSG_IDEA_COMMENT_SAVED = "Coment�rio salvo com sucesso.";

	public static final String MSG_IDEA_COMMENT_NOT_SAVED = "Erro ao salvar coment�rio.";

	public static final String MSG_IDEA_QUESTION_SAVED = "Questionamento salvo com sucesso.";

	public static final String MSG_IDEA_QUESTION_NOT_SAVED = "Erro ao salvar questionamento.";

	public static final String MSG_IDEA_IS_NOT_DRAFT = "Erro ao salvar ideia, j� foi para an�lise";

	public static final String MSG_NOT_AUTHORIZED = "Erro ao salvar ideia, voc� n�o tem permiss�o";

}

