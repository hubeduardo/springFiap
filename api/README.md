# Getting Started
Spring Boot + Kotlin + MongoDB

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





