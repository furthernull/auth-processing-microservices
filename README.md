# Auth Processing Microservices

Simple project with two Spring Boot services.

## Services

- auth-api (port 8080) — auth + process
- data-api (port 8081) — text transform
- postgres — database

---

## Run

### Build

```bash
mvn -f auth-api/pom.xml clean package -DskipTests
mvn -f data-api/pom.xml clean package -DskipTests
```

### Start

```bash
docker compose up -d --build
```

### Stop

```bash
docker compose down
```

---

## Test API

### Register

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"a@a.com","password":"password123"}'
```

### Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"a@a.com","password":"password123"}'
```

### Process (with token)

```bash
curl -X POST http://localhost:8080/api/process \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"text":"hello"}'
```

---

## Env variables

- POSTGRES_URL
- POSTGRES_USER
- POSTGRES_PASSWORD
- JWT_SECRET
- INTERNAL_TOKEN
- DATA_API_URL

---


