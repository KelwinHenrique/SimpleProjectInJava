# Simple Project with Java

This api contains:

- Unit and integration tests;
- Authorization and Authentication with JWT;
- Run with Docker

## How to run the project

Inside your project execute:

1) docker build -t my-app-image . 

2) docker network create network-my-app

3) docker-compose up

4) Run the comand bellow in your terminal:
```json
  curl --request GET \
  --url http://localhost:4040/users
```