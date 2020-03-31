# Avaliação - Spring Framework

SpringBoot + Swagger + Kotlin + MongoDB + Python + Docker

## Problema:
```
A FIAP resolveu criar seu próprio cartão de crédito para ser utilizado pelos
alunos e para isso necessita de um sistema para gerenciamento e
integração com outras empresas. Desenvolva este sistema com os
seguintes requisitos:

    - RF1 - Cadastro de Alunos
    
    - RF2 - O cadastro inicial dos potenciais clientes do cartão será realizado via
      integração com um arquivo em formato ".txt"
      
    - RF3 - As compras realizadas nos cartões dos clientes serão recebidas via
    integração com uma Autorizadora. Criar os endpoints necessários para
    receber as realizações de transações.
    
    - RF4 - Deve ser possível gerar um extrato via download (endpoint) ou
      enviado no email do cliente (pode escolher uma opção).
   
```

## Solução:
```
RF1, RF2 e RF3
    - Foi criado uma aplicação em Kotlin/[Spring Framework](https://spring.io/projects/spring-framework "Spring Framework") com utlização do [Spring Boot](https://spring.io/projects/spring-boot "Spring Boot") 
    e Banco de Dados NOSQL [MongoDB](https://www.mongodb.com/ "MongoDB") para cadastrar usuários, fazer transações e possível fazer download de PDF com extrato das transações.

RF2
    - Foi criada uma aplicação em Kotlin/[Spring Framework](https://spring.io/projects/spring-boot "Spring Framework") com utilização do [Spring Batch](https://spring.io/projects/spring-batch "Spring Batch")
    para fazer leitura de arquivos para integração do sistema.   
     
Observação:
    Existe um processo para Teste de carga massiva das aplicações sendo realizado por um script em Python.
```

## Pontos Explorados:
```
    - utilizamos a lib RxJava2, para explorar o paradigma da progração reativa;
    - utilizamos a plugin kotlinter para manter a formatação de código padrão;
    - utilizamos banco de Dados NOSQL;
    - utilizamos integração de teste com CI/CD utlizando circleCi;
    - utilizamos container para que aplicação possa funcionar em um ambiente unico;
```

# Execução - Projeto

O projeto poderá ser executado através do Docker, porém pode ser executado manualmente.


## Requisitos:
```
1. `docker-compose`
2. `java 8 JDK`
3. `Python 2.7`
```

## Executar projeto no Mac/Linux
```
cd springFiap
./start-project.sh
```

## Executar projeto no Windows
```
cd springFiap
sh start-project.sh
```

## Eliminar processo do Docker Mac/Linux e Windows
```
docker-compose down --rmi all
```



