# Quarkus e Spring

Atividade de desenvolvimento de api java. Foram desenvolvidas duas APIs, uma versão em spring e outra em quarkus.
Possuem as entidades para alunos, cursos, disciplinas e professores e endpoints para seus respectivos métodos CRUDs.

## REST

Sigfinica Representational State Transfer, inventado por Roy Fielding, um dos principais criadores do protocolo HTTP.

São princípios de aplicações distribuídas. A comunicação ocorre entre clientes e servidores. Os clientes requerem recursos por meio de solicitações, enquanto os servidores possuem esses recursos e os fornece continuamente conforme as solicitações. Os papeis das máquinas que representam o cliente e o servidor não são necessariamente fixos, eles podem variar de acordo com o uso. O Rest também é stateles. Isso significa que ele não possui estado, pois o servidor não deve guardar os dados de conexões ativas de clientes.

Os métodos HTTP mais utilizados são:
- GET;
- POST;
- PUT;
- DELETE.

As aplicações REST retornam códigos que definem os status da requisição. Os códigos podem ser no formato:
- 1XX: informacional;
- 2XX: sucesso;
- 3XX: redireção;
- 4XX: erro do cliente;
- 5XX: erro do servidor.

## Microsserviços

As aplicações mais tradicionais são do tipo monolíticas. Nesse tipo de aplicação, todo o código está em uma aplicação única e seus dados são armazenados em apenas um banco de dados. Essa é a forma mais comum e mais simples de aplicação.

Esse tipo de aplicação tem algumas desvantagens. Conforme o código cresce, aumentam as demoras no deploy. Além disso, as falhas podem fazer com que todo o sistema seja derrubado. Outro problema, é que o projeto se limita a uma única tecnologia.

Em contrapartida, a arquitetura de microsserviços organiza a aplicação dividindo-a em partes independentes. Esses pequenos serviços independentes se comunicam por meio de APIs bem definidas. Dessa forma, cada serviço pode ser mantido por uma equipe diferente e podem também utilizar tecnologias diferentes de forma mais simples. Além disso, é mais provável que a falha em um serviço não prejudique os outros. O deploy também tenderá a ser mais rápido, pois será feito de maneira separada para cada serviço.