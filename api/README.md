# Getting Started
Spring Boot + Kotlin + MongoDB


## Efetuar autenticação para requisições
> Todas as requisições deverão ser autenticadas com usuário e password.
*   *Usuário* = user
*   *Senha* = Buscar em logs para aplicação

> Visualizar os container do docker.

$ docker container ls
```
CONTAINER ID        IMAGE               COMMAND                  CREATED              STATUS              PORTS                                                NAMES
40287cc3aa9b        fiap-api            "/bin/sh -c 'sh run.…"   About a minute ago   Up 59 seconds       0.0.0.0:5000->5000/tcp                               fiap-api
efc650fa0c5f        mongo:3.4           "docker-entrypoint.s…"   7 minutes ago        Up About a minute   0.0.0.0:12345->27017/tcp, 0.0.0.0:23456->28017/tcp   fiap-mongodb
```

> Buscar logs informando CONTAINER ID.

$ docker container logs 40287cc3aa9b | grep -i "security password"
```
Using generated security password: 2babb967-9d13-4a7a-aed9-e9d972413304
```

> O usuário e senha deverão ser informados:

Postman
<p align="center">
  <img src="https://github.com/hubeduardo/springFiap/blob/master/.github/postman.png" width="800">
</p>

Navegador

<p align="center">
  <img src="https://github.com/hubeduardo/springFiap/blob/master/.github/navegador.png" width="800">
</p>


## Endpoints Ping Test
Ping by Test Application
```
curl -X GET \
  http://localhost:5000/ping \
  -H 'Authorization: Basic dXNlcjoxZGIyMWM5Yy1iMDI3LTQ3ZmQtODgxMS1hZDhiODcwNTg1MmQ=' \
```

## Endpoints Users

Search User
```
curl -X GET \
  http://localhost:5000/v1/fiap/get-user/{doc} \
  -H 'Authorization: Basic dXNlcjoxZGIyMWM5Yy1iMDI3LTQ3ZmQtODgxMS1hZDhiODcwNTg1MmQ=' \
  -H 'Host: localhost:5000' \
```



## Endpoints Transactions

Create Transactions
```
curl -X POST \
  http://localhost:5000/v1/fiap/create-transaction \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Basic dXNlcjoxZGIyMWM5Yy1iMDI3LTQ3ZmQtODgxMS1hZDhiODcwNTg1MmQ=' \
  -H 'Host: localhost:5000' \
  -d '{
  	"user_doc": "3095564",
  	"description": "Credito de alimento",
  	"amount": 10.0
  }'
```





