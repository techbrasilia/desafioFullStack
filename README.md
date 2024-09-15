# Desafiofullstack
Requisitos do desafio:

    1. Criar um sistema que gerencie usuários e perfis. [OK]
    2. Usuário possui um perfil; um perfil pode ter vários usuários. [OK]
    3. O sistema deverá ter um administrador que crie os usuários e atribua ou modifique os
    perfis. [OK]

    4. O perfil usuário comum apenas visualizará suas próprias informações, podendo editá-las,
    menos o perfil. [OK]
    - Usuario perfil ADMIN pode cadastrar, visualizar qualquer outro. [OK]
    - Usuario perfil USER somente visualiza seus dados. [OK]
    - Editar informacoes.
    
    5. Utilizar no frontend a versão Angular 4+.
    - Tela de login. [OK]
    - Tela de editar. [OK]
    
    6. Utilizar no backend o banco de sua preferência, preferencialmente JAVA 8+ com Spring
    boot.
    - Utilizei Spring boot com java 17
    - Docker: imagem mysql - usando docker-composer
    
    7. Favor passar o link do aplicativo funcionando ou instruções para subir a aplicação.
    8. Prazo para fazer o desafio 4 dias.

- O que pode melhorar:
   1. Validar se usuario alterou o proprio email e deslogar.
   2. Utilizar formBuilder para gerenciar melhor o formulario.
   3. Alterar authorization de Basic para Bearer utilizando JWT
   4. Adicionar mais funcionalidades ao sistema

Instruções (Backend):

    1. Clonar ou baixar o codigo de https://github.com/techbrasilia/desafioFullStack.git
    2. Ter o docker instalado ou usar um container na nuvem e alterar a configuracao de banco "spring.datasource" no arquivo application.properties
    3. Ter instalado o jdk 17 ou utilizar o intellij (sugestao), para rodar o codigo configurando o projeto para java 17, mavem (Bundled (Maven 3) versao 3.9.8)
    4. Acessar um terminal para rodar a imagem atraves do arquivo docker-composer executando o comando "docker-compose up" (precisa estar no diretorio raiz do projeto)
    5. Executar (subir) a aplicacao backend
    6. Caso queira utilizar uma IDE (cliente) para gerenciar o banco de dados pode utilizar o dbeaver, sqldeveloper ou outro de sua preferencia.
    7. A API normalmente roda no endereco: http://localhost:8080. Caso queira testar a API no Postman siga os passos abaixo:
    - Login: http://localhost:8080/api/auth/login - Utilizar POST
        - Passar no body um json, exemplo:
        {
            "username": "user@user.com",
            "password":"user123"
        }
        - Passar no authorization os mesmos parametros (caso teste no postman)
        - Os outros endpoints possuem validacao de autenticacao, entao o login precisa ser executado com sucesso para os proximos passos
    - Cadastro: http://localhost:8080/api/usuarios - Utilizar POST
        - Passar no body um json, exemplo:
            {
            "nome": "teste 1",
            "email": "teste4@teste.com",
            "senha": 1233442,
            "perfil": {
                "id": 2
            }
            }
    - Consulta por ID: http://localhost:8080/api/usuarios/ID (o ID dever ser um numero que representa do ID do usuario. Exemplo http://localhost:8080/api/usuarios/1) Utilizar GET
    - Editar: http://localhost:8080/api/usuarios/2 - Utilizar POST
        - Passar no body um json, exemplo:
            {
            "nome": "User 2 atualizado",
            "email": "user2@user.com"
            }

Instruções (Frontend):

    1. Clonar ou baixar o codigo de https://github.com/techbrasilia/desafioFullStack-frontend.git
    2. Ter instalado ultima versao do NodeJS, NPM e Angular 17
    3. No diretorio raiz do projeto executar npm install (para baixar dependencias)
    4. No mesmo diretorio executar ng serve
    5. Caso queira utilizar IDE recomendo VS Code, mas pode ser alguma outra de sua preferência.
    6. Testar aplicacao no browser acessando: http://localhost:4200
