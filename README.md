# Sistema de Pedidos

Este projeto implementa um sistema de pedidos assíncrono utilizando Spring Boot, Apache Kafka e MongoDB, desenvolvido em Java 17 e gerenciado com Maven.

## Arquitetura Hexagonal

Neste projeto foi realizada a implementação da Arquitetura Hexagonal, em que as principais camadas desenvolvidas foram:

`Domain`: Define os modelos de domínio, como Order e OrderStatus.
`Application`: Nela, tem-se os serviços responsáveis pela lógica de negócio, como OrderService, OrderProducerService e OrderConsumerService.
`Infrastructure`: Nessa comada há a conexão com interfaces externas, como repositórios (OrderRepository para MongoDB) e configurações (MongoConfig).
`Controller`: Camada responsável pela interface rest da aplicação.

## Funcionalidades:
O sistema permite:

- Criação de novos pedidos via REST através do endpoint POST `/orders`.
- Consulta do status de um pedido via REST através do endpoint POST `/orders`.
- Processamento assíncrono dos pedidos utilizando Apache Kafka para comunicação entre componentes.

## Tecnologias Utilizadas
- Spring Boot
- Java 17
- Apache Kafka
- MongoDB
- Maven

## Configuração
### Docker Compose
Para facilitar o desenvolvimento e deixar disponível todas as ferramentas necessárias para a realização desse projeto, foi disponibilizar o docker-compose, em que as seguintes tecnologias foram configuradas:
- Zookeeper
- Kafka
- Schema Registry
- MongoDB

## Como executar o projeto?

Para executar o projeto, será necessário ter o Docker instalado na máquina, o Git para clonar o projeto e o JDK 17 para ser possível rodá-lo (na IDE Intellij Idea essa dinâmica é facilitada). 

### Passo a passo

- Clonar o repositório do github:

```git clone https://github.com/angelahempkmeier/order-system```

- Abrir o projeto em uma IDE para facilitar o processo, e rodar via interface da IDE ou `mvn spring-boot:run`.

- No diretório raíz do projeto, rodar o comando para subir os ambientes necessários:
```
docker-compose up
```
- Parar criar um pedido, utilize Postman ou Insomnia para facilitar o processo
`POST`: http://localhost:8080/orders
Exemplo: 
```
{
	"id": "id-string-exemplo",
	"product":"product 01",
	"quantity":45
}
```
Status esperado: `201 CREATED`
`GET`: http://localhost:8080/orders/{id}
Exemplo:
`http://localhost:8080/orders/id-string-exemplo`
Resposta esperada:
```
{
	"id": "id-string-exemplo",
	"product": "product 01",
	"quantity": 45,
	"status": "ENVIADO_TRANSPORTADORA"
}
```
Status esperado: `200 OK`
