# Git
- Sempre seguir o [Processo de Software](http://www.tools.ages.pucrs.br/projetos/IdeiasAges/wikis/processo)

# Requisitos de Ambiente
- Java 8
- MySql
- Tomcat 8.0

# Instalação

1. git clone http://www.tools.ages.pucrs.br/projetos/IdeiasAges.git
2. rodar o script SQL api/src/br/com/ideiasages/db/IDEIAS_SCRIPT.sql
3. copiar arquivo api/src/resources/ambiente.properties.example criando um ambiente.properties na mesma pasta
4. alterar dados de banco de dados do arquivo ambiente.properties
5. configurar 1 artifact para cada módulo: api e frontend
6. configurar tomcat com 2 application context: / para o frontend e /api para a api