# Effective Mobile Bank System

Тестовое задание: REST-сервис для управления банковскими картами.

## Стек технологий
* Java 17, Spring Boot 3.5.11
* Spring Security + JWT
* Spring Data JPA + H2 Database (In-Memory)
* Liquibase (миграции БД)
* Lombok, Maven

## Как запустить
1. Склонируйте репозиторий.
2. Запустите приложение через IntelliJ IDEA (класс `BankApplication`).
3. База данных H2 создается автоматически при старте.

## Основные эндпоинты
* `POST /api/auth/login?username=Anton` — Получение JWT-токена.
* `GET /api/cards/{owner}` — Просмотр карт владельца (требуется Bearer токен).
* `POST /api/cards/transfer` — Перевод между картами.
* `PATCH /api/cards/block/{number}` — Блокировка карты.
* `Интерактивная документация (Swagger UI):` http://localhost:8080/swagger-ui/index.html (доступна после запуска приложения)

*Примечание: Номера карт маскируются в целях безопасности (Lambda-логика в CardService).*
