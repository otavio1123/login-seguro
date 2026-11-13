# Login Seguro

Sistema web desenvolvido com Java, Spring Boot, Spring Security, Thymeleaf e MongoDB Atlas.

O projeto possui cadastro e autenticação de usuários, controle de acesso por perfis, gerenciamento de usuários e um **Dashboard Administrativo de chamados técnicos**, integrado a uma API REST desenvolvida no próprio Spring Boot.

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
- Chart.js
- HTML
- CSS
- JavaScript
- Git e GitHub

## Principais funcionalidades

- Cadastro e login de usuários
- Senhas protegidas com BCrypt
- Logout e controle de sessão
- Perfis `USUARIO`, `MODERADOR` e `ADMIN`
- Controle de acesso às rotas com Spring Security
- Gerenciamento de usuários pelo administrador
- Ativação e desativação de contas
- Alteração de perfis
- Persistência de usuários e sessões no MongoDB Atlas
- API REST para chamados técnicos
- Dashboard Administrativo
- Indicadores de chamados
- Gráficos por período, situação e tipo
- Filtros por data, status e tipo
- Exibição das considerações técnicas dos chamados
- Atualização dos dados do dashboard

## Conta ADMIN para avaliação

Para testar as funcionalidades administrativas e acessar o Dashboard, utilize:

**E-mail:** `admin@teste.com`  
**Senha:** `teste123`

Após o login, acesse a **Área Administrativa** e selecione **Dashboard de Chamados**.

Também é possível acessar diretamente:

```text
http://localhost:8080/dashboard
```

O Dashboard é protegido pelo Spring Security e somente usuários com perfil `ADMIN` possuem acesso.

## Como executar

Clone o repositório:

```bash
git clone https://github.com/otavio1123/login-seguro.git
```

Entre na pasta:

```bash
cd login-seguro
```

A conexão com o MongoDB Atlas é configurada através da variável de ambiente:

```text
MONGODB_URI
```

A aplicação utiliza:

```properties
spring.mongodb.uri=${MONGODB_URI}
spring.mongodb.database=login_seguro
```

No Windows, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

Em Linux/macOS:

```bash
./mvnw spring-boot:run
```

Depois acesse:

```text
http://localhost:8080
```

## Dashboard Administrativo

O Dashboard foi desenvolvido utilizando **Thymeleaf, Bootstrap, JavaScript e Chart.js**.

Os dados exibidos na tela são obtidos através de uma **API REST desenvolvida em Spring Boot**, que consulta os chamados armazenados na collection `chamados` do MongoDB Atlas.

O JavaScript utiliza `fetch` para consumir os endpoints da API e atualizar os indicadores, gráficos e tabela do Dashboard.

São apresentados:

- Total de chamados
- Chamados abertos
- Chamados em andamento
- Chamados concluídos
- Chamados cancelados
- Chamados concluídos por período
- Distribuição por situação
- Tipos de chamados mais atendidos
- Lista e consideração técnica dos chamados

Os filtros permitem consultar os chamados por **data, status e tipo** e atualizam os dados apresentados no Dashboard.

## API

Principais endpoints utilizados:

```text
GET /api/chamados
GET /api/chamados/filtro
POST /api/chamados
PUT /api/chamados/{id}/status
PUT /api/chamados/{id}/consideracao

GET /api/dashboard/indicadores
GET /api/dashboard/chamados-periodo
GET /api/dashboard/situacoes
GET /api/dashboard/tipos
```

As rotas relacionadas aos chamados e ao Dashboard são protegidas e acessíveis apenas pelo perfil `ADMIN`.

## Banco de dados

O projeto utiliza MongoDB Atlas com as principais collections:

```text
usuarios
sessions
chamados
```

- `usuarios`: dados dos usuários cadastrados
- `sessions`: sessões autenticadas
- `chamados`: chamados técnicos utilizados pela API e pelo Dashboard

## Vídeo de apresentação

O vídeo abaixo apresenta o funcionamento do sistema, o acesso com perfil de administrador e as principais funcionalidades do Dashboard Administrativo.

[▶ Assistir ao vídeo de apresentação](./video-apresentacao.mp4)

## Autor

**Otávio Ramos Ribeiro**

Sistemas de Informação  
Universidade de Mogi das Cruzes – UMC