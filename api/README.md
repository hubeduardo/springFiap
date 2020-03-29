# Getting Started
Spring Boot + Kotlin + MongoDB rest API

## Endpoints
Ping by Test Application
```
curl -X GET \
  http://localhost:5000/ping \
  -H 'Authorization: Basic dXNlcjoxZGIyMWM5Yy1iMDI3LTQ3ZmQtODgxMS1hZDhiODcwNTg1MmQ=' \
```

Search User
```
curl -X GET \
  http://localhost:5000/v1/fiap/get-user/{doc} \
  -H 'Authorization: Basic dXNlcjoxZGIyMWM5Yy1iMDI3LTQ3ZmQtODgxMS1hZDhiODcwNTg1MmQ=' \
  -H 'Host: localhost:5000' \
```

