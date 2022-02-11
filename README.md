# Vendas
Um sistema de vendas simples construído com Java e React

### Istalação e como usar

Primeiramente, você deve copiar o repositório para o seu computador:
```zsh
  foo@bar:~$ git clone https://github.com/daniortlepp/vendas.git
```

Para iniciar a API, você deve instalar o Tomcat. Após a instalação do Tomcat, você deve copiar o arquivo apivendas.war para dentro da pasta webapps do diretório de instalação do Tomcat e iniciar o aplicativo.

Para iniciar o app do sistema, você deve acessar o diretório app desse repositório via cmd ou prompt e instalar as dependências:
```zsh
  foo@bar:~$ npm install
```
Após a instalação, você pode executar o sistema através do comando:
```zsh
  foo@bar:~$ npm start
```

No arquivo **Sales.sql** tem  a estrutura do banco de dados. Também é necessário ter o MySql instalado e criar o banco de dados conforme essa estrutura.

### Sobre o sistema

O sistema consiste em 3 telas simples:

1. Tela que contém uma listagem de todos os pedidos salvos no banco de dados com filtro de pedidos por id ou nome do cliente.

![](https://files.fm/thumb_show.php?i=2t93hh5w9)

2. Tela para cadastrar um pedido novo, com filtro de produtos por id ou nome do produto ao adicionar um novo item.

![](https://files.fm/thumb_show.php?i=eddy7e3sr)

3. Tela para visualizar os detalhes do pedido.

![](https://files.fm/thumb_show.php?i=u79fk6vrd)
