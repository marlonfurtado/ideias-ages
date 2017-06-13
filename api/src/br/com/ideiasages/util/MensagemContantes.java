
package br.com.ideiasages.util;

/**
 * Classe de mensagens genéricas para o sistema.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 *
 **/
public class MensagemContantes {

	public static final String MSG_ERR_CAMPO_OBRIGATORIO = "Campo ? é obrigatório!";

	public static final String MSG_ERR_CAMPOS_OBRIGATORIOS = "Erro ao salvar ideia, todos os campos marcados com \"*\" são obrigatórios e precisam ser preenchidos.";

	public static final String MSG_ERR_CAMPO_INVALIDO = "Campo ? inválido!";

	public static final String MSG_ERR_USUARIO_SENHA_INVALIDOS = "Usuário/Senha inválidos!";

	public static final String MSG_ERR_SENHA_INVALIDA = "Senha Atual inválida";

	public static final String MSG_ERR_SENHA_NULO = "Senha não pode ser nulo";

	public static final String MSG_ERR_CAMPO_EXCEDE_TAMANHO = "Campo ? excede o tamanho permitido de caracteres.";

	public static final String MSG_ERR_CAMPO_DATA_MAIOR_RECOMENDADO = "Campo ? com mais de 10 caracteres!";

	public static final String MSG_ERR_CAMPO_DATA_MENOR_RECOMENDADO = "Campo ? com menos de 10 caracteres!";

	public static final String MSG_ERR_CAMPO_DATA_INVALIDO = "Ocorreu algum problema no envio da data";	

	public static final String MSG_ERR_SENHAS_DIFERENTES = "A senha informada não coincide com a confirmação de senha!";

	public static final String MSG_ERR_TELEFONE_INVALIDO = "Telefone com formato inválido.\n Exemplo correto: 51123456789.";

	public static final String MSG_SUC_CADASTRO_USUARIO = "Usuário ? cadastrado com sucesso!";

	public static final String MSG_SUC_EDICAO_USUARIO = "Usuário ? atualizado com sucesso!";

	public static final String MSG_INF_DENY = "Acesso negado! Você precisa logar primeiro.";

	public static final String MSG_INF_EMAIL_ALREADY_REGISTERED = "Já existe um cadastro com este email.";

	public static final String MSG_INF_CPF_ALREADY_REGISTERED = "Já existe um cadastro com este CPF.";

	public static final String MSG_INF_ALLOW_ONLY_ADMINISTRATOR = "Acesso negado! Permitido somente administradores do sistema.";

	public static final String MSG_ERR_USUARIO_INATIVO = "Acesso negado! Usuário ? inativo.";

	public static final String MSG_IDEA_SAVED = "Ideia salva com sucesso.";

	public static final String MSG_IDEA_NOT_SAVED = "Erro ao salvar idea.";

	public static final String MSG_IDEA_COMMENT_SAVED = "Comentário salvo com sucesso.";

	public static final String MSG_IDEA_COMMENT_NOT_SAVED = "Erro ao salvar comentário.";

	public static final String MSG_IDEA_IS_NOT_DRAFT = "Erro ao salvar ideia, já foi para análise";

	public static final String MSG_NOT_AUTHORIZED = "Erro ao salvar ideia, você não tem permissão";
}

