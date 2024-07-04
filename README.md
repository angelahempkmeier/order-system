# Sistema de Pedidos

Este projeto implementa um sistema de pedidos assíncrono utilizando Spring Boot, Apache Kafka e MongoDB, desenvolvido em Java 17 e gerenciado com Maven.

## Arquitetura Hexagonal

Neste projeto foi realizada a implementação da Arquitetura Hexagonal, em que as principais camadas desenvolvidas foram:

`Domain`: Define os modelos de domínio, como Order e OrderStatus.</br>
`Application`: Nessa camada adicionei os packages de controller (comunicação do usuário via REST com a aplicação), dtos (Request e Response), exception (personalização pra retornar erros mais amigáveis ao usuário) e service (cuida da lógica de negócio e comunicação com Kafka. Pelo service se comunicar com aplicações externas, decidi colocá-lo na camada de service, mas acredito que dependendo do cenário seria ideal estar na camada de Domain).</br>
`Infrastructure`: Nessa comada há a conexão com interfaces externas, como repositórios (OrderRepository para MongoDB), configurações (MongoConfig) e interação com o Kafka (OrderProducerService e OrderConsumerService).

## Funcionalidades:
O sistema permite:

- Criar novos pedidos via REST através do endpoint POST `/orders`.
- Consultar status de um pedido via REST através do endpoint POST `/orders`.
- Processamento assíncrono dos pedidos utilizando Apache Kafka para comunicação entre componentes, ou seja, ao criar um novo pedido, o sistema se comunica com o Kafka e consegue atualizar as informações de status do pedido, simulando a comunicação com a transporadora.

## Tecnologias Utilizadas
- Spring Boot
- Java 17
- Apache Kafka
- MongoDB
- Maven

## Configuração
### Docker Compose
Para facilitar o desenvolvimento e deixar disponível todas as ferramentas necessárias para a realização desse projeto, foi disponibilizado o docker-compose, em que as seguintes tecnologias foram configuradas:
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
	"product":"product 01",
	"quantity":45
}
```
Status esperado: `201 CREATED`
`GET`: http://localhost:8080/orders/{id}
Exemplo:
`http://localhost:8080/orders/2024070416523`
Resposta esperada:
```
{
	"id": "2024070416523",
	"product": "product 01",
	"quantity": 45,
	"status": "ENVIADO_TRANSPORTADORA"
}
```
Status esperado: `200 OK`

## Melhorias
Pensando na aplicação, acredito que seja bacana mapear alguns pontos de melhoria:
- Testes de integração;
- Entidade usuário responsável por pedidos;
- Validação com autenticação para apenas usuários donos do pedido poderem visualizar as informações de atualização de status;
- Pipeline CI/CD;
- Documentação da api com swagger;
- Cache pra não precisar ir ao banco pra procurar as informações do pedido;
- Etc.
