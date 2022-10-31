# Serviço de Criação de URL encurtada

Esse projeto foi gerado utilizando Spring Boot e Maven e utiliza Banco de Dados H2 / JPA

# Pré requisitos

Para que seja possível rodar esta aplicação, é necessário atender os requisitos:

 - Java 11+
 - Maven 3+

# Endpoints

 - Gerar URL encurtada (Se não definir a data de expiração, será gerada uma URL com 3 minutos para expirar)
````
http://localhost:8080/gerarurl
````
 - Acessar URL original a partir da URL encurtada (a variável {urlEncurtada} vem no retorno da chamada para geral URL)
````
http://localhost:8080/{urlEncurtada}
````
 - Ver número de acessos da URL gerada (a variável {urlEncurtada} vem no retorno da chamada para geral URL)
````
http://localhost:8080/acessos/{urlEncurtada}
````

# Banco de Dados

Para visualizar os dados inserido no banco de dados H2:
````
http://localhost:8080/h2-console
````
````
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
username: sa
password: sa
````