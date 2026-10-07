# Login Seguro

Sistema web desenvolvido para a atividade prática de Login Completo, utilizando Java, Spring Boot, Spring Security, Thymeleaf e MongoDB Atlas.

O projeto implementa cadastro, autenticação, autorização por perfis e gerenciamento de usuários. A estrutura foi organizada para que a lógica de autenticação possa ser reaproveitada futuramente em outros sistemas, com possibilidade de alteração da interface sem grandes mudanças no backend.

## Tecnologias utilizadas

- Java 17
- Spring Boot 4.1.1
- Spring Security
- Spring Data MongoDB
- Spring Session
- Thymeleaf
- MongoDB Atlas
- Maven
- Bootstrap 5
- Bootstrap Icons
- HTML
- CSS
- JavaScript
- Git
- GitHub

## Funcionalidades

O sistema possui as seguintes funcionalidades:

- Cadastro de usuários
- Validação dos dados informados no cadastro
- Verificação de e-mail já cadastrado
- Validação de confirmação de senha
- Login utilizando e-mail e senha
- Logout
- Senhas armazenadas utilizando hash BCrypt
- Controle de acesso utilizando Spring Security
- Três perfis de acesso
- Controle de rotas de acordo com o perfil
- Página de acesso negado
- Persistência de usuários no MongoDB Atlas
- Persistência das sessões no MongoDB Atlas
- Gerenciamento de usuários pelo administrador
- Alteração de perfil dos usuários
- Ativação e desativação de contas
- Proteção para impedir que o administrador altere o próprio perfil ou desative a própria conta
- Exibição de opções da interface de acordo com o perfil autenticado
- Opção para mostrar e ocultar a senha nos formulários
- Interface construída com Thymeleaf e Bootstrap
- CSS separado da lógica da aplicação
- Recursos visuais compartilhados através de fragments do Thymeleaf

## Perfis de acesso

O sistema trabalha com três perfis.

### USUARIO

Perfil atribuído automaticamente a novos cadastros.

Possui acesso à área destinada aos usuários autenticados.

### MODERADOR

Possui acesso à área de usuário e à área de moderação.

### ADMIN

Possui acesso às áreas de usuário, moderação e administração.

O administrador também pode acessar a tela de gerenciamento de usuários, onde é possível:

- Alterar o perfil de um usuário
- Ativar uma conta
- Desativar uma conta

Por segurança, o administrador autenticado não pode alterar o próprio perfil nem desativar a própria conta.

## Funcionamento do controle de acesso

No momento do cadastro, todo novo usuário recebe automaticamente o perfil:


USUARIO