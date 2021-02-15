Используемый источник для подключения постгрес:
https://java-master.com/spring-boot-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80-%D1%81-postgres-%D0%B8-jpa/

Источник по докеризации приложения + постгрес:
https://sysout.ru/spring-boot-postgresql-js-v-dosker/

Запуск спринг-приложения:
1. mvn clean -DskipTests install
2. запустить jar файл
3. сваггер доступен http://localhost:8083/swagger-ui.html#/

Запуск в докере:
0. Пересобрать приложение, положить jar в папку docker/service/app
1. перейти в docker/services
2. docker-compose up
3. запущены 2 контейнера, 1 с постгрес, 1 с приложением

БАЗУ МЕНЯТЬ В src/main/resources/application.properties

Далее надо создать БД (автоматически пока не удается). Нужное имя задано в application.properties.
Название таблицы см в контроллерах.
Как заполнить базу:
1. зайти в докер контейнер
docker exec -it 2d872e0b6180 bash
2. зайти под нужным пользователем
psql -U postgres
3. создать базу
CREATE DATABASE seconddb;
4. Проверить, что база создалась, например в программе pg_admin.
Добавить сервер, если его не было ранее (создать - новый - localhost, 5434)



На будущее:
To run the integrations tests after your unit tests, simply add maven-failsafe-plugin to your project. In addition, make sure your integration tests have IT as a postfix:

СРАВНЕНИЕ mockserver, wiremock и др
https://dernasherbrezon.com/posts/mock-server/