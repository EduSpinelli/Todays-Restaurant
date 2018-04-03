## o Requisitos de ambiente necessários para compilar e rodar 
Java 8
Maven 3+

Para compilar - mvn clean install
Para rodar entrar na pasta target e executar - java - jar restaurant-today-0.0.1-SNAPSHOT.jar 

Para rodar testes mutantes mvn org.pitest:pitest-maven:mutationCoverage

## Instruções de como utilizar o sistema.
Foi contruida uma API Rest com String a mesma pode ser testa atravez do PostMan ou do navegador

Acessando a Rota localhost:8080/users são listados os usuários do sistema 
Acessando a Rota localhost:8080/restaurants são listados os restaurantes já cadastrados
Acessando a Rota localhost:8080/vote e fornecendo no formato abaixo sera acrescentado um voto para o usuário
{
"userName":"user",
"password":"user",
"restaurantName":"Alvarez"
}

Acessando a Rota localhost;8080/winners são listados os restaurantes vencedores
